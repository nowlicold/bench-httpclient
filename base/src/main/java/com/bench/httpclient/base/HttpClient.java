package com.bench.httpclient.base;

import com.bench.httpclient.base.exceptions.HttpClientException;
import com.bench.httpclient.base.request.HttpBytesBodyRequest;
import com.bench.httpclient.base.request.HttpInputStreamBodyRequest;
import com.bench.httpclient.base.request.HttpParameterRequest;
import com.bench.httpclient.base.request.HttpRequest;
import com.bench.httpclient.base.request.HttpStringBodyRequest;
import com.bench.httpclient.base.response.HttpResponse;

/**
 * http客户端
 * 
 * @author cold
 *
 * @version $Id: HttpClient.java, v 0.1 2018年5月8日 上午11:50:29 cold Exp $
 */
public interface HttpClient {

	/**
	 * 初始化客户端
	 */
	public void init();

	/**
	 * 执行http请求
	 * 
	 * @param url
	 * @return
	 */
	public HttpResponse executeGet(String url) throws HttpClientException;

	/**
	 * 执行http请求
	 * 
	 * @param url
	 * @return
	 */
	public HttpResponse executePost(String url) throws HttpClientException;

	/**
	 * 执行http请求
	 * 
	 * @param request
	 * @return
	 */
	public HttpResponse execute(HttpParameterRequest request) throws HttpClientException;

	/**
	 * 执行http请求
	 * 
	 * @param request
	 * @return
	 */
	public HttpResponse execute(HttpStringBodyRequest request) throws HttpClientException;

	/**
	 * 执行http请求
	 * 
	 * @param request
	 * @return
	 */
	public HttpResponse execute(HttpBytesBodyRequest request) throws HttpClientException;

	/**
	 * 执行http请求
	 * 
	 * @param request
	 * @return
	 */
	public HttpResponse execute(HttpInputStreamBodyRequest request) throws HttpClientException;

	/**
	 * 执行http请求
	 * 
	 * @param request
	 * @return
	 */
	public <REQ extends HttpRequest> HttpResponse execute(REQ request) throws HttpClientException;

}
