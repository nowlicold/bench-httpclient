/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.bench.httpclient.base;

import com.bench.lang.base.clasz.utils.ClassUtils;
import com.bench.lang.base.instance.BenchClassFactory;
import com.bench.lang.base.instance.BenchInstanceFactory;

/**
 * HttpClient工厂
 * 
 * @author cold
 *
 * @version $Id: HttpClientFactory.java, v 0.1 2020年3月18日 上午11:29:58 cold Exp $
 */
public class HttpClientFactory {

	/**
	 * 默认的HttpClient
	 */
	private HttpClient defaultHttpClient;

	/**
	 * 
	 */
	private HttpClientFactory() {
		super();
		Class<HttpClient> defaultHttpClientClass = BenchClassFactory.getFirstImplementsClass(HttpClient.class);
		defaultHttpClient = ClassUtils.newInstance(defaultHttpClientClass);
	}

	/**
	 * 获取单例
	 * 
	 * @return
	 */
	public static HttpClientFactory getInstance() {
		return BenchInstanceFactory.getInstance(HttpClientFactory.class);
	}

	public HttpClient getDefaultHttpClient() {
		return defaultHttpClient;
	}

}
