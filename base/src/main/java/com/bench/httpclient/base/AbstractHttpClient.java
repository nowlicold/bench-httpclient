/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.bench.httpclient.base;

import com.bench.common.enums.error.CommonErrorCodeEnum;
import com.bench.httpclient.base.enums.HttpMethodEnum;
import com.bench.httpclient.base.exceptions.HttpClientException;
import com.bench.httpclient.base.request.HttpBytesBodyRequest;
import com.bench.httpclient.base.request.HttpInputStreamBodyRequest;
import com.bench.httpclient.base.request.HttpParameterRequest;
import com.bench.httpclient.base.request.HttpRequest;
import com.bench.httpclient.base.request.HttpStringBodyRequest;
import com.bench.httpclient.base.response.HttpResponse;
import com.bench.lang.base.Constants;

/**
 * 抽象的httpclient
 * 
 * @author cold
 *
 * @version $Id: AbstractHttpClient.java, v 0.1 2018年5月8日 下午12:23:06 cold Exp $
 */
public abstract class AbstractHttpClient<RAW_CLIENT> implements HttpClient {

	/**
	 * 请求连接超时
	 */
	protected int connectionRequestTimeout = 10000;

	/**
	 * 连接超时
	 */
	protected int connectTimeout = 5000;

	/**
	 * socket超时
	 */
	protected int socketTimeout = 20000;

	/**
	 * 最大连接数
	 */
	protected int maxConnections = 200;

	/**
	 * 每路由的最大连接数,即每个网站的最大连接数
	 */
	protected int defaultMaxPerRoute = 50;

	/**
	 * 字符集合
	 */
	protected String charset = Constants.DEFAULT_CHARSET;

	/**
	 * 
	 */
	protected RAW_CLIENT rawClient;

	/**
	 * 内部初始化
	 */
	protected abstract void internalInit();

	/**
	 * 返回原始的Client
	 * 
	 * @return
	 */
	public RAW_CLIENT getRawClient() {
		return rawClient;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		if (rawClient != null) {
			return;
		}
		internalInit();
	}

	/**
	 * 确保init执行
	 */
	protected void ensureInited() {
		if (rawClient == null) {
			synchronized (this) {
				if (rawClient == null) {
					init();
				}
			}
		}
	}

	@Override
	public <REQ extends HttpRequest> HttpResponse execute(REQ request) throws HttpClientException {
		// TODO Auto-generated method stub
		if (request instanceof HttpStringBodyRequest) {
			return execute((HttpStringBodyRequest) request);
		} else if (request instanceof HttpBytesBodyRequest) {
			return execute((HttpBytesBodyRequest) request);
		}
		if (request instanceof HttpInputStreamBodyRequest) {
			return execute((HttpInputStreamBodyRequest) request);
		}
		if (request instanceof HttpParameterRequest) {
			return execute((HttpParameterRequest) request);
		}
		throw new HttpClientException(CommonErrorCodeEnum.SYSTEM_ERROR, "不支持的request:" + request.getClass());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bench.lang.base.http.client.HttpClient#executeGet(java.lang.String)
	 */
	@Override
	public HttpResponse executeGet(String url) throws HttpClientException {
		// TODO Auto-generated method stub
		HttpParameterRequest request = new HttpParameterRequest();
		request.setUrl(url);
		request.setMethod(HttpMethodEnum.GET);
		return this.execute(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bench.lang.base.http.client.HttpClient#executePost(java.lang.String)
	 */
	public HttpResponse executePost(String url) throws HttpClientException {
		// TODO Auto-generated method stub
		HttpParameterRequest request = new HttpParameterRequest();
		request.setUrl(url);
		request.setMethod(HttpMethodEnum.POST);
		return this.execute(request);
	}

	public void setConnectionRequestTimeout(int connectionRequestTimeout) {
		this.connectionRequestTimeout = connectionRequestTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}

	public void setDefaultMaxPerRoute(int defaultMaxPerRoute) {
		this.defaultMaxPerRoute = defaultMaxPerRoute;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public int getConnectionRequestTimeout() {
		return connectionRequestTimeout;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public int getMaxConnections() {
		return maxConnections;
	}

	public int getDefaultMaxPerRoute() {
		return defaultMaxPerRoute;
	}

}
