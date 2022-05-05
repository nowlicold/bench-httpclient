package com.bench.httpclient.base.exceptions;

import com.bench.common.enums.error.ErrorEnum;
import com.bench.common.error.ErrorCode;
import com.bench.common.exception.BenchException;
import com.bench.common.exception.BenchRuntimeException;

/**
 * Cookie解析异常
 * 
 * @author cold
 *
 * @version $Id: CookieParseException.java, v 0.1 2020年4月21日 下午6:27:20 cold Exp $
 */
public class CookieParseException extends BenchException {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -65480180683158130L;

	/**
	 * 
	 */
	public CookieParseException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param <E>
	 * @param exception
	 */
	public <E extends BenchException> CookieParseException(E exception) {
		super(exception);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param <E>
	 * @param exception
	 */
	public <E extends BenchRuntimeException> CookieParseException(E exception) {
		super(exception);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 * @param message
	 * @param throwable
	 */
	public CookieParseException(ErrorCode errorCode, String message, Throwable throwable) {
		super(errorCode, message, throwable);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 * @param message
	 */
	public CookieParseException(ErrorCode errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 * @param throwable
	 */
	public CookieParseException(ErrorCode errorCode, Throwable throwable) {
		super(errorCode, throwable);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 */
	public CookieParseException(ErrorCode errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorEnum
	 * @param message
	 * @param throwable
	 */
	public CookieParseException(ErrorEnum errorEnum, String message, Throwable throwable) {
		super(errorEnum, message, throwable);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorEnum
	 * @param message
	 */
	public CookieParseException(ErrorEnum errorEnum, String message) {
		super(errorEnum, message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorEnum
	 * @param throwable
	 */
	public CookieParseException(ErrorEnum errorEnum, Throwable throwable) {
		super(errorEnum, throwable);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorEnum
	 */
	public CookieParseException(ErrorEnum errorEnum) {
		super(errorEnum);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param <E>
	 * @param message
	 * @param exception
	 */
	public <E extends BenchException> CookieParseException(String message, E exception) {
		super(message, exception);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param <E>
	 * @param message
	 * @param exception
	 */
	public <E extends BenchRuntimeException> CookieParseException(String message, E exception) {
		super(message, exception);
		// TODO Auto-generated constructor stub
	}

}
