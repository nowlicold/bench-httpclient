/**
 * benchcode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.bench.httpclient.base.exceptions;

import com.bench.common.enums.error.ErrorEnum;
import com.bench.common.exception.BenchException;

/**
 * 
 * @author cold
 *
 * @version $Id: HttpClientException.java, v 0.1 2010-8-18 上午11:36:59 cold Exp $
 */
public class HttpClientException extends BenchException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8837458263119222093L;

	/**
	 * 
	 */
	public HttpClientException() {
		// TODO Auto-generated constructor stub
	}

	public HttpClientException(ErrorEnum errorEnum, String message, Throwable throwable) {
		super(errorEnum, message, throwable);
		// TODO Auto-generated constructor stub
	}

	public HttpClientException(ErrorEnum errorEnum, String message) {
		super(errorEnum, message);
		// TODO Auto-generated constructor stub
	}

	public HttpClientException(ErrorEnum errorEnum, Throwable throwable) {
		super(errorEnum, throwable);
		// TODO Auto-generated constructor stub
	}

	public HttpClientException(ErrorEnum errorEnum) {
		super(errorEnum);
		// TODO Auto-generated constructor stub
	}

}
