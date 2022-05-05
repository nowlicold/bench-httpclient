package com.bench.httpclient.base.request;

import com.bench.common.http.enums.HttpVersionEnum;
import com.bench.httpclient.base.enums.HttpMethodEnum;
import com.bench.httpclient.base.head.Headers;
import com.bench.lang.base.string.build.ToStringObject;

/**
 * 抽象的HTTP请求
 * 
 * @author cold
 *
 * @version $Id: AbstractHttpRequest.java, v 0.1 2018年1月27日 下午11:31:05 cold Exp $
 */
public abstract class AbstractHttpRequest extends ToStringObject implements HttpRequest {

	/**
	 * http版本
	 */
	protected HttpVersionEnum version = HttpVersionEnum.HTTP_1_1;

	/**
	 * 请求地址
	 */
	protected String url;

	/**
	 * 方法类型,默认GET
	 */
	protected HttpMethodEnum method = HttpMethodEnum.GET;

	/**
	 * 请求配置
	 */
	protected RequestConfig requestConfig;

	/**
	 * 头部信息
	 */
	private Headers headers = new Headers();

	/**
	 * cookie信息
	 */
	private RequestCookies requestCookies = new RequestCookies();

	public HttpVersionEnum getVersion() {
		return version;
	}

	public void setVersion(HttpVersionEnum version) {
		this.version = version;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public RequestConfig getRequestConfig() {
		if (requestConfig == null) {
			requestConfig = new RequestConfig();
		}
		return requestConfig;
	}

	public void setRequestConfig(RequestConfig requestConfig) {
		this.requestConfig = requestConfig;
	}

	public HttpMethodEnum getMethod() {
		return method;
	}

	public void setMethod(HttpMethodEnum method) {
		this.method = method;
	}

	public Headers getHeaders() {
		return headers;
	}

	public void setHeaders(Headers headers) {
		this.headers = headers;
	}

	public RequestCookies getCookies() {
		return requestCookies;
	}
}
