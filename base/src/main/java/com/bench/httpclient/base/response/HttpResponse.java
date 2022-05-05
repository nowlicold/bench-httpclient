package com.bench.httpclient.base.response;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.bench.common.enums.error.CommonErrorCodeEnum;
import com.bench.common.http.enums.HttpStatusEnum;
import com.bench.httpclient.base.exceptions.HttpClientException;
import com.bench.httpclient.base.head.Headers;
import com.bench.lang.base.CompressUtils;
import com.bench.lang.base.charset.CharsetConstants;
import com.bench.lang.base.charset.utils.CharsetUtils;
import com.bench.lang.base.string.build.ToStringObject;
import com.bench.lang.base.string.utils.StringUtils;
import com.bench.lang.base.web.cookie.Cookie;
import com.bench.lang.base.web.cookie.CookieParseUtils;
import com.bench.lang.base.web.cookie.HeaderNameEnum;
import com.bench.lang.base.zip.ZipUtils;

/**
 * HTTP响应
 * 
 * @author cold
 *
 * @version $Id: HttpResponse.java, v 0.1 2018年5月8日 下午12:19:00 cold Exp $
 */
public class HttpResponse extends ToStringObject {

	private static final String CHARSET_TAG = "charset=";

	private static final String DEFAULT_ENCODING = CharsetConstants.UTF_8.name();

	private static final String CONTENT_ENCODING_GZIP = "gzip";

	private static final String CONTENT_ENCODING_BR = "br";

	private static final String CONTENT_ENCODING = "Content-Encoding";

	private int responseCode;

	private byte[] responseBody;

	/**
	 * 头部信息
	 */
	private Headers headers = new Headers();

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public byte[] getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(byte[] responseBody) {
		this.responseBody = responseBody;
	}

	public boolean isReponseCode200() {
		return this.responseCode == HttpStatusEnum.SC_OK.status();
	}

	public String getResponseBodyAsString() throws HttpClientException {
		return getResponseBodyAsString((String) null);
	}

	public String getResponseBodyAsString(String encoding) throws HttpClientException {
		byte[] byteContent = (byte[]) responseBody;
		if (byteContent == null) {
			return null;
		}
		if (byteContent.length == 0) {
			return StringUtils.EMPTY_STRING;
		}
		// 如果是gzip头，则解压
		if (StringUtils.equals(headers.getFirst(CONTENT_ENCODING), CONTENT_ENCODING_GZIP)) {
			byteContent = ZipUtils.extraGZIP(byteContent);
		}
		// 如果是BR头，则解压
		else if (StringUtils.equals(headers.getFirst(CONTENT_ENCODING), CONTENT_ENCODING_BR)) {
			byteContent = CompressUtils.extraBR(byteContent);
		}
		if (!StringUtils.isEmpty(encoding)) {
			return new String(byteContent, Charset.forName(encoding));
		} else {
			// 获取字符集
			String targetEncoding = StringUtils.defaultIfEmpty(this.getCharset());
			// 自动检测一次
			if (StringUtils.isEmpty(targetEncoding)) {
				targetEncoding = CharsetUtils.getCharset(byteContent);
			}
			// 为空，则使用默认值
			if (StringUtils.isEmpty(targetEncoding)) {
				targetEncoding = DEFAULT_ENCODING;
			}
			return new String(byteContent, Charset.forName(targetEncoding));
		}
	}

	public String getResponseBodyAsString(Charset charset) throws HttpClientException {
		return getResponseBodyAsString(charset.name());
	}

	/**
	 * 返回响应的cookie
	 * 
	 * @return
	 */
	public List<Cookie> getCookies() {
		List<Cookie> returnList = new ArrayList<Cookie>();
		for (String value : headers.get("Set-Cookie")) {
			returnList.addAll(CookieParseUtils.parseFromSetCookieHeader(value));
		}
		return returnList;
	}

	public String getContentType() {
		return headers.getFirst(HeaderNameEnum.CONTENT_TYPE.headerName());
	}

	public String getCharset() {
		// Content-Type:application/xhtml+xml;charset=UTF-8
		String contentType = getContentType();
		contentType = StringUtils.toLowerCase(contentType);
		if (StringUtils.indexOf(contentType, CHARSET_TAG) > 0) {
			return StringUtils.substringAfter(contentType, CHARSET_TAG);
		}
		return null;

	}

	public Headers getHeaders() {
		return headers;
	}

	public void setHeaders(Headers headers) {
		this.headers = headers;
	}
}
