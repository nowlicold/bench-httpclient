/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.bench.httpclient.base.enums;


import com.bench.common.enums.error.ErrorEnum;

/**
 * HttpClient错误代码
 * 
 * @author cold
 *
 * @version $Id: HttpClientErrorCodeEnum.java, v 0.1 2018年5月22日 上午11:17:43 cold Exp $
 */
public enum HttpClientErrorCodeEnum implements ErrorEnum {

	METHOD_REQUIRED("METHOD不能为空"),

	URL_INVALID("URL不正确"),

	EXECUTE_EXCEPTION("执行异常"),

	;

	private String message;

	private HttpClientErrorCodeEnum(String message) {
		this.message = message;
	}

	@Override
	public String message() {
		// TODO Auto-generated method stub
		return message;
	}

	@Override
	public Number value() {
		// TODO Auto-generated method stub
		return null;
	}

}
