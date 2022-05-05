package com.bench.httpclient.base.enums;


import com.bench.common.enums.EnumBase;

/**
 * HTTP方法枚举
 * 
 * @author cold
 *
 * @version $Id: HttpMethodEnum.java, v 0.1 2018年5月8日 下午2:06:48 cold Exp $
 */
public enum HttpMethodEnum implements EnumBase {

	GET,

	HEAD,

	POST,

	PUT,

	PATCH,

	DELETE,

	OPTIONS,

	TRACE;

	@Override
	public String message() {
		// TODO Auto-generated method stub
		return name();
	}

	@Override
	public Number value() {
		// TODO Auto-generated method stub
		return null;
	}

}
