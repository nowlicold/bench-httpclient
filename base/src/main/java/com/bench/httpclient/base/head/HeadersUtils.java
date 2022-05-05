package com.bench.httpclient.base.head;

import com.bench.common.http.enums.UserAgentEnum;
import com.bench.lang.base.web.cookie.HeaderNameEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 头部工具类
 * 
 * @author cold
 *
 * @version $Id: HeadersUtils.java, v 0.1 2018年5月8日 下午6:33:40 cold Exp $
 */
/**
 * 
 * @author cold
 *
 * @version $Id: HeadersUtils.java, v 0.1 2018年5月8日 下午6:35:09 cold Exp $
 */
public class HeadersUtils {

	private static final String HEADER_UESR_AGENT = "User-Agent";

	private static final Map<String, String> COMMON_NORMALIZE_MAP = new HashMap<String, String>();
	static {
		COMMON_NORMALIZE_MAP.put(HeaderNameEnum.ACCEPT.headerName(), "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		COMMON_NORMALIZE_MAP.put(HeaderNameEnum.ACCEPT_ENCODING.headerName(), "gzip, deflate, br");
		COMMON_NORMALIZE_MAP.put(HeaderNameEnum.ACCEPT_LANGUAGE.headerName(), "zh-CN,zh;q=0.9");
		COMMON_NORMALIZE_MAP.put(HeaderNameEnum.CACHE_CONTROL.headerName(), "max-age=0");
		COMMON_NORMALIZE_MAP.put(HeaderNameEnum.CONNECTION.headerName(), "keep-alive");
		COMMON_NORMALIZE_MAP.put(HeaderNameEnum.PRAGMA.headerName(), "no-cache");
	}

	/**
	 * 得到下载文件名的头部信息
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getDownloadFileNameHeaderValue(String fileName) {
		return "attachment; filename=" + fileName;
	}

	/**
	 * 标准化头部信息
	 * 
	 * @param headers
	 */
	public static void normalize(Headers headers) {
		for (Map.Entry<String, String> entry : COMMON_NORMALIZE_MAP.entrySet()) {
			if (!headers.hasValue(entry.getKey())) {
				headers.add(entry.getKey(), entry.getValue());
			}
		}
		if (!headers.hasValue(HEADER_UESR_AGENT)) {
			headers.add(HEADER_UESR_AGENT, UserAgentEnum.FIREFOX.userAgent());
		}
	}
}
