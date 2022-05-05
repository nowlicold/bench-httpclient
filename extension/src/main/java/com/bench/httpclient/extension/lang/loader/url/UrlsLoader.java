/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.bench.httpclient.extension.lang.loader.url;

import java.util.HashMap;
import java.util.Map;

import com.bench.httpclient.base.HttpClient;
import com.bench.httpclient.base.HttpClientFactory;
import com.bench.httpclient.base.enums.HttpMethodEnum;
import com.bench.httpclient.base.exceptions.HttpClientException;
import com.bench.httpclient.base.request.HttpParameterRequest;
import com.bench.httpclient.base.response.HttpResponse;
import com.bench.lang.base.loader.exceptions.LoadException;
import com.bench.lang.base.loader.merageable.MergeableChannelLoader;

/**
 * 基于Url的loader
 * 
 * @author cold
 *
 * @version $Id: UrlLoader.java, v 0.1 2020年5月19日 下午3:22:56 cold Exp $
 */
public interface UrlsLoader<LOAD_DATA> extends MergeableChannelLoader<String, LOAD_DATA> {

	/**
	 * 返回httpMethod的方式
	 * 
	 * @return
	 */
	default HttpMethodEnum getMethod() {
		return HttpMethodEnum.GET;
	}

	@Override
	default Map<String, String> readChannelContent(String channelPath) throws LoadException {
		// TODO Auto-generated method stub
		Map<String, String> returnMap = new HashMap<String, String>();

		// 获取httpclient
		HttpClient httpClient = HttpClientFactory.getInstance().getDefaultHttpClient();
		// 构造请求
		HttpParameterRequest request = new HttpParameterRequest();
		request.setUrl(channelPath);
		request.setMethod(getMethod());
		try {
			// 执行请求
			HttpResponse response = httpClient.execute(request);
			String content = response.getResponseBodyAsString();
			returnMap.put(channelPath, content);
		} catch (HttpClientException e) {
			throw new LoadException("从url加载异常,url=" + channelPath + ",loader=" + this, e);
		}
		// 返回
		return returnMap;
	}

}
