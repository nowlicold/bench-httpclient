/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.bemch.httpclient.test;


import com.bench.httpclient.base.HttpClient;
import com.bench.httpclient.base.HttpClientFactory;
import com.bench.httpclient.base.request.HttpParameterRequest;
import com.bench.httpclient.base.request.enums.CookieSpecEnum;
import com.bench.httpclient.base.response.HttpResponse;
import org.junit.Test;

/**
 * 测试HttpComponentsHttpClient
 * 
 * @author cold
 *
 * @version $Id: HttpComponentsHttpClient.java, v 0.1 2020年3月18日 上午11:08:08 cold Exp $
 */

public class HttpClientTest {

	private HttpClient httpClient = HttpClientFactory.getInstance().getDefaultHttpClient();

	@Test
	public void baidu() throws Exception {
		String url = "https://www.baidu.com";
		HttpResponse response = httpClient.executeGet(url);
		// System.out.println(response.getResponseBodyAsString());
		System.out.println("第1次响应COOKIE：");
		response.getCookies().forEach(p -> System.out.println(p));
		response = httpClient.executeGet(url);
		System.out.println("不传递请求cookie，响应Cookie：");
		response.getCookies().forEach(p -> System.out.println(p));
		/**
		 * 自行传递cookie测试
		 */
		HttpParameterRequest request = new HttpParameterRequest();
		// 增加cookie
		response.getCookies().forEach(p -> request.getCookies().add(p));
		request.setUrl(url);
		response = httpClient.execute(request);
		System.out.println("传递cookie，响应Cookie1：");
		response.getCookies().forEach(p -> System.out.println(p));
		request.getRequestConfig().setCookieSpec(CookieSpecEnum.IGNORE_COOKIES);
		response = httpClient.execute(request);
		System.out.println("IgnoreCookie，响应Cookie2：");
		response.getCookies().forEach(p -> System.out.println(p));
	}

}
