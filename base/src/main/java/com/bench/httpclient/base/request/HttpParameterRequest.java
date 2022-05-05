/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.bench.httpclient.base.request;

import com.bench.lang.base.map.multi.ArrayListValuedHashMap;
import com.bench.lang.base.map.multi.MultiValuedMap;

/**
 * 基于参数的请求
 * 
 * @author cold
 *
 * @version $Id: HttpParameterRequest.java, v 0.1 2018年5月8日 上午11:19:12 cold Exp $
 */
public class HttpParameterRequest extends AbstractHttpRequest {

	/**
	 * 参数集合
	 */
	private MultiValuedMap<String, Object> parameters = new ArrayListValuedHashMap<String, Object>();

	/**
	 * 是否发送空值的parameter
	 */
	private boolean sendEmptyParameterValue = false;

	/**
	 * 是否一直发送multipart/form-data<br>
	 * 1、如果为true，则一直发送multipart/form-data<br>
	 * 2、如果是false，则有文件类型的，才发送multipart/form-data
	 */
	private boolean alwaysMultipart = false;

	public HttpParameterRequest() {
		super();
	}

	public MultiValuedMap<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(MultiValuedMap<String, Object> parameters) {
		this.parameters = parameters;
	}

	public boolean isSendEmptyParameterValue() {
		return sendEmptyParameterValue;
	}

	public void setSendEmptyParameterValue(boolean sendEmptyParameterValue) {
		this.sendEmptyParameterValue = sendEmptyParameterValue;
	}

	public boolean isAlwaysMultipart() {
		return alwaysMultipart;
	}

	public void setAlwaysMultipart(boolean alwaysMultipart) {
		this.alwaysMultipart = alwaysMultipart;
	}

}
