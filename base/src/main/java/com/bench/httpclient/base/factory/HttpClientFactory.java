/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.bench.httpclient.base.factory;

import com.bench.httpclient.base.HttpClient;

/**
 * HttpClient工厂
 * 
 * @author cold
 *
 * @version $Id: HttpClientFactory.java, v 0.1 2020年3月18日 上午10:41:15 cold Exp $
 */
public interface HttpClientFactory<CLIENT extends HttpClient> {

	/**
	 * 返回客户端
	 * 
	 * @return
	 */
	CLIENT getClient();

}
