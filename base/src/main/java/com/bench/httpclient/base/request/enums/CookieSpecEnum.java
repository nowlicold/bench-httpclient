package com.bench.httpclient.base.request.enums;


import com.bench.common.enums.EnumBase;

/**
 * cookie方式
 * 
 * @author cold
 *
 * @version $Id: CookieSpecEnum.java, v 0.1 2020年4月22日 下午4:55:43 cold Exp $
 */
public enum CookieSpecEnum implements EnumBase {

	DEFAULT,

	IGNORE_COOKIES,
	;

	@Override
	public String message() {
		return null;
	}

	@Override
	public Number value() {
		return null;
	}
}
