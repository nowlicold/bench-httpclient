/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.bench.httpclient.base.request;

/**
 * 抽象的基于body的请求
 * 
 * @author cold
 *
 * @version $Id: AbstractHttpBodyRequest.java, v 0.1 2018年5月8日 上午11:19:12
 *          cold Exp $
 */
public abstract class AbstractHttpBodyRequest<T> extends AbstractHttpRequest {

	/**
	 * 主体
	 */
	protected T body;

	protected String mimeType;

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
}
