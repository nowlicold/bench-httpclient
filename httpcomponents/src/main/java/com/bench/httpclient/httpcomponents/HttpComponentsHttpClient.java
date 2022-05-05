/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.bench.httpclient.httpcomponents;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import com.bench.common.enums.error.CommonErrorCodeEnum;
import com.bench.common.exception.BenchRuntimeException;
import com.bench.httpclient.base.enums.HttpMethodEnum;
import com.bench.lang.base.RequestUtils;
import com.bench.lang.base.file.FileItem;
import com.bench.lang.base.utils.IOUtils;
import com.bench.lang.base.web.cookie.HeaderNameEnum;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.FormBodyPartBuilder;
import org.apache.http.entity.mime.MIME;
import org.apache.http.entity.mime.MinimalField;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import com.bench.httpclient.base.AbstractHttpClient;
import com.bench.httpclient.base.enums.HttpClientErrorCodeEnum;
import com.bench.httpclient.base.exceptions.HttpClientException;
import com.bench.httpclient.base.head.HeadersUtils;
import com.bench.httpclient.base.request.AbstractHttpBodyRequest;
import com.bench.httpclient.base.request.AbstractHttpRequest;
import com.bench.httpclient.base.request.HttpBytesBodyRequest;
import com.bench.httpclient.base.request.HttpInputStreamBodyRequest;
import com.bench.httpclient.base.request.HttpParameterRequest;
import com.bench.httpclient.base.request.HttpRequest;
import com.bench.httpclient.base.request.HttpStringBodyRequest;
import com.bench.httpclient.base.request.enums.CookieSpecEnum;
import com.bench.httpclient.base.response.HttpResponse;
import com.bench.httpclient.base.ssl.NoopTrustManager;
import com.bench.httpclient.base.upload.file.AbstractUploadFile;
import com.bench.lang.base.Constants;
import com.bench.lang.base.instance.annotations.Default;
import com.bench.lang.base.object.utils.ObjectUtils;
import com.bench.lang.base.string.utils.StringUtils;
import com.bench.lang.base.uri.utils.URIUtils;

/**
 * 基于apache httpcomponents的httpclient
 * 
 * @author cold
 *
 * @version $Id: HttpComponentsHttpClient.java, v 0.1 2020年3月18日 上午8:59:52 cold Exp $
 */
@Default
public class HttpComponentsHttpClient extends AbstractHttpClient<CloseableHttpClient> {

	@Override
	protected void internalInit() {
		// TODO Auto-generated method stub
		Registry<ConnectionSocketFactory> register = null;
		try {
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, new TrustManager[] { new NoopTrustManager() }, null);
			SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
			register = RegistryBuilder.<ConnectionSocketFactory> create().register("http", PlainConnectionSocketFactory.getSocketFactory())
					.register("https", sslSocketFactory).build();
		} catch (Exception e) {
			throw new BenchRuntimeException(CommonErrorCodeEnum.SYSTEM_ERROR, "初始化Apache HttpClient异常", e);
		}

		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(register);
		if (maxConnections > 0) {
			connManager.setMaxTotal(maxConnections);
		}
		if (defaultMaxPerRoute > 0) {
			connManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
		}
		SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(socketTimeout).build();
		connManager.setDefaultSocketConfig(socketConfig);
		rawClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).setConnectionManager(connManager).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bench.common.client.http.HttpClient#execute(com.bench.common.client.http.request.HttpParameterRequest)
	 */
	@Override
	public HttpResponse execute(HttpParameterRequest request) throws HttpClientException {
		// TODO Auto-generated method stub
		return this.execute(request, new RequestPreparer<HttpParameterRequest>() {

			@Override
			public void prepare(HttpParameterRequest request, HttpRequestBase requestBase) throws HttpClientException {
				// TODO Auto-generated method stub
				ContentType textContentType = ContentType.create(ContentType.DEFAULT_TEXT.getMimeType(), request.getRequestConfig().getCharset());
				String usedCharset = StringUtils.defaultString(request.getRequestConfig().getCharset(), charset);
				if (!HttpEntityEnclosingRequestBase.class.isAssignableFrom(requestBase.getClass())) {
					String uri = RequestUtils.appendParameters(request.getUrl(), request.getParameters(), request.isSendEmptyParameterValue(), usedCharset);
					try {
						requestBase.setURI(new URI(uri));
					} catch (URISyntaxException e) {
						throw new HttpClientException(HttpClientErrorCodeEnum.URL_INVALID, "不正确的URL:" + uri, e);
					}
				} else {
					try {
						requestBase.setURI(new URI(request.getUrl()));
					} catch (URISyntaxException e) {
						throw new HttpClientException(HttpClientErrorCodeEnum.URL_INVALID, "不正确的URI:" + request.getUrl(), e);
					}
					HttpEntityEnclosingRequestBase entityRequestBase = (HttpEntityEnclosingRequestBase) requestBase;
					boolean containsFile = false;
					for (Collection<Object> valueCol : request.getParameters().asMap().values()) {
						if (valueCol != null) {
							for (Object valueObj : valueCol) {
								if (valueObj != null && valueObj instanceof FileItem) {
									containsFile = true;
									break;
								}
							}
						}
					}
					// 如果包含文件
					if (containsFile || request.isAlwaysMultipart()) {
						MultipartEntityBuilder builder = MultipartEntityBuilder.create();
						for (Map.Entry<String, ? extends Collection<? extends Object>> entry : request.getParameters().asMap().entrySet()) {
							// 集合为空
							if (entry.getValue().size() == 0) {
								if (!request.isSendEmptyParameterValue()) {
									continue;
								}
								builder.addTextBody(entry.getKey(), StringUtils.EMPTY_STRING);
								continue;
							}
							for (Object value : entry.getValue()) {
								if (value instanceof AbstractUploadFile) {
									AbstractUploadFile fileItem = (AbstractUploadFile) value;
									InputStreamBody inputStreamBody = new InputStreamBody(fileItem.getInputStream(), ContentType.APPLICATION_OCTET_STREAM,
											fileItem.getName());
									/***************************************
									 * FormBodyPartBuilder不支持<code>MIME.CONTENT_DISPOSITION</code>的filelength
									 *************************************/
									FormBodyPartBuilder formBodyPartBuilder = FormBodyPartBuilder.create(entry.getKey(), inputStreamBody);
									FormBodyPart formBodyPart = formBodyPartBuilder.build();
									// 增加filelength，因为没有setValue方法，重新创建一个
									MinimalField mimeField = formBodyPart.getHeader().getField(MIME.CONTENT_DISPOSITION);
									MinimalField newMinimalField = new MinimalField(mimeField.getName(),
											mimeField.getBody() + "; filelength=" + fileItem.getInputStream());
									formBodyPart.getHeader().setField(newMinimalField);
									builder.addPart(formBodyPart);
								} else {
									String stringValue = ObjectUtils.toString(value);
									if (StringUtils.isEmpty(stringValue) && !request.isSendEmptyParameterValue()) {
										continue;
									}
									builder.addTextBody(entry.getKey(), stringValue, textContentType);
								}

							}
						}
						builder.setCharset(Charset.forName(usedCharset));
						entityRequestBase.setEntity(builder.build());
					} else {
						List<NameValuePair> pairList = new ArrayList<NameValuePair>();
						for (Map.Entry<String, ? extends Collection<? extends Object>> entry : request.getParameters().asMap().entrySet()) {
							// 集合为空
							if (entry.getValue().size() == 0) {
								if (!request.isSendEmptyParameterValue()) {
									continue;
								}
								pairList.add(new BasicNameValuePair(entry.getKey(), StringUtils.EMPTY_STRING));
								continue;
							}
							for (Object value : entry.getValue()) {
								String stringValue = ObjectUtils.toString(value);
								if (StringUtils.isEmpty(stringValue) && !request.isSendEmptyParameterValue()) {
									continue;
								}
								pairList.add(new BasicNameValuePair(entry.getKey(), stringValue));
							}
						}
						UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairList, Charset.forName(usedCharset));
						entityRequestBase.setEntity(entity);
					}
				}
			}

		});
	}

	/**
	 * 内部执行方法
	 * 
	 * @param request
	 * @param requestPreparer
	 * @return
	 * @throws HttpClientException
	 */
	private <REQ extends AbstractHttpRequest> HttpResponse execute(REQ request, RequestPreparer<REQ> requestPreparer) throws HttpClientException {
		// TODO Auto-generated method stub
		ensureInited();
		HttpRequestBase requestBase = null;
		if (request.getMethod() == null) {
			throw new HttpClientException(HttpClientErrorCodeEnum.METHOD_REQUIRED, "没有指定method类型,参看" + HttpMethodEnum.class.getName());
		}
		if (request.getMethod().equals(HttpMethodEnum.GET)) {
			if (request instanceof AbstractHttpBodyRequest) {
				RequestBuilder requestBuilder = RequestBuilder.create(HttpMethodEnum.GET.name());
				requestBuilder.setEntity(new StringEntity("", Constants.DEFAULT_CHARSET));
				requestBase = (HttpRequestBase) requestBuilder.build();
			} else {
				requestBase = new HttpGet();
			}
		} else if (request.getMethod().equals(HttpMethodEnum.POST)) {
			requestBase = new HttpPost();
		} else if (request.getMethod().equals(HttpMethodEnum.DELETE)) {
			requestBase = new HttpDelete();
		} else if (request.getMethod().equals(HttpMethodEnum.PATCH)) {
			requestBase = new HttpPatch();
		} else if (request.getMethod().equals(HttpMethodEnum.HEAD)) {
			requestBase = new HttpHead();
		} else if (request.getMethod().equals(HttpMethodEnum.PUT)) {
			requestBase = new HttpPut();
		} else if (request.getMethod().equals(HttpMethodEnum.TRACE)) {
			requestBase = new HttpTrace();
		} else if (request.getMethod().equals(HttpMethodEnum.OPTIONS)) {
			requestBase = new HttpOptions();
		}
		// 标准化头部
		HeadersUtils.normalize(request.getHeaders());
		// 插入头部
		for (Map.Entry<String, String> headerEntry : request.getHeaders().entries()) {
			requestBase.addHeader(headerEntry.getKey(), headerEntry.getValue());
		}
		// 插入cookie，只有当请求是不忽略Cookie时，才处理
		boolean ignoreCookie = request.getRequestConfig().getCookieSpec() == CookieSpecEnum.IGNORE_COOKIES;
		if (!ignoreCookie && request.getCookies() != null && request.getCookies().size() > 0) {
			requestBase.addHeader(HeaderNameEnum.COOKIE.headerName(), request.getCookies().toHeaderValue());
		}
		requestBase.addHeader(HeaderNameEnum.HOST.headerName(), URIUtils.getOriginalDomain(request.getUrl()));

		// 准备请求
		requestPreparer.prepare(request, requestBase);

		if (request.getRequestConfig().getConnectionRequestTimeout() == -1) {
			request.getRequestConfig().setConnectionRequestTimeout(connectionRequestTimeout);
		}
		if (request.getRequestConfig().getConnectTimeout() == -1) {
			request.getRequestConfig().setConnectTimeout(connectTimeout);
		}
		if (request.getRequestConfig().getSocketTimeout() == -1) {
			request.getRequestConfig().setSocketTimeout(socketTimeout);
		}

		RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
		requestConfigBuilder.setAuthenticationEnabled(request.getRequestConfig().isAuthenticationEnabled());
		requestConfigBuilder.setConnectTimeout(request.getRequestConfig().getConnectTimeout());
		requestConfigBuilder.setConnectionRequestTimeout(request.getRequestConfig().getConnectionRequestTimeout());
		if (request.getRequestConfig().getCookieSpec() != null) {
			if (request.getRequestConfig().getCookieSpec() == CookieSpecEnum.DEFAULT) {
				requestConfigBuilder.setCookieSpec(CookieSpecs.DEFAULT);
			} else if (request.getRequestConfig().getCookieSpec() == CookieSpecEnum.IGNORE_COOKIES) {
				requestConfigBuilder.setCookieSpec(CookieSpecs.IGNORE_COOKIES);
			}
		}
		requestConfigBuilder.setCircularRedirectsAllowed(request.getRequestConfig().isCircularRedirectsAllowed());
		requestConfigBuilder.setContentCompressionEnabled(request.getRequestConfig().isContentCompressionEnabled());
		requestConfigBuilder.setExpectContinueEnabled(request.getRequestConfig().isExpectContinueEnabled());
		requestConfigBuilder.setLocalAddress(request.getRequestConfig().getLocalAddress());
		requestConfigBuilder.setMaxRedirects(request.getRequestConfig().getMaxRedirects());
		if (request.getRequestConfig().getProxy() != null) {
			requestConfigBuilder.setProxy(new HttpHost(request.getRequestConfig().getProxy().getHost(), request.getRequestConfig().getProxy().getPort(),
					request.getRequestConfig().getProxy().getSchemeName()));
		}
		requestConfigBuilder.setProxyPreferredAuthSchemes(request.getRequestConfig().getProxyPreferredAuthSchemes());
		requestConfigBuilder.setRedirectsEnabled(request.getRequestConfig().isRedirectsEnabled());
		requestConfigBuilder.setRelativeRedirectsAllowed(request.getRequestConfig().isRelativeRedirectsAllowed());
		requestConfigBuilder.setSocketTimeout(request.getRequestConfig().getSocketTimeout());
		requestConfigBuilder.setTargetPreferredAuthSchemes(request.getRequestConfig().getTargetPreferredAuthSchemes());
		requestBase.setConfig(requestConfigBuilder.build());
		CloseableHttpResponse responseBase = null;
		long start = System.currentTimeMillis();
		try {
			HttpResponse response = new HttpResponse();
			responseBase = rawClient.execute(requestBase);
			response.setResponseCode(responseBase.getStatusLine().getStatusCode());
			if (responseBase.getEntity() != null && responseBase.getEntity().getContent() != null) {
				response.setResponseBody(IOUtils.toByteArray(responseBase.getEntity().getContent()));
			} else {
				response.setResponseBody(new byte[] {});
			}
			if (responseBase.getAllHeaders() != null) {
				for (Header header : responseBase.getAllHeaders()) {
					response.getHeaders().add(header.getName(), header.getValue());
				}
			}
			return response;
		} catch (Exception e) {
			throw new HttpClientException(HttpClientErrorCodeEnum.EXECUTE_EXCEPTION,
					"执行Http客户端异常,cost=" + (System.currentTimeMillis() - start) + "ms,requestBase=" + requestBase, e);
		} finally {
			if (responseBase != null) {
				try {
					EntityUtils.consume(responseBase.getEntity());
				} catch (Exception e) {
					// 吃掉异常，不影响输出
				}
			}
			try {
				requestBase.releaseConnection();
			} catch (Exception e) {
				// 吃掉异常，不影响输出
			}
			try {
				requestBase.abort();
			} catch (Exception e) {
				// 吃掉异常，不影响输出
			}
			if (responseBase != null) {
				try {
					responseBase.close();
				} catch (Exception e) {
					// 吃掉异常，不影响输出
				}
			}
		}

	}

	@Override
	public HttpResponse execute(HttpStringBodyRequest request) throws HttpClientException {
		// TODO Auto-generated method stub
		return this.execute(request, new RequestPreparer<HttpStringBodyRequest>() {
			@Override
			public void prepare(HttpStringBodyRequest request, HttpRequestBase requestBase) throws HttpClientException {
				// TODO Auto-generated method stub
				if (!HttpEntityEnclosingRequestBase.class.isAssignableFrom(requestBase.getClass())) {
					throw new HttpClientException(CommonErrorCodeEnum.SYSTEM_ERROR, "当前method不支持该请求,request=" + request);
				}
				try {
					requestBase.setURI(new URI(request.getUrl()));
				} catch (URISyntaxException e) {
					throw new HttpClientException(HttpClientErrorCodeEnum.URL_INVALID, "不正确的URI:" + request.getUrl(), e);
				}
				HttpEntityEnclosingRequestBase entityRequestBase = (HttpEntityEnclosingRequestBase) requestBase;
				ContentType textContentType = ContentType.create(StringUtils.defaultString(request.getMimeType(), ContentType.DEFAULT_TEXT.getMimeType()),
						request.getRequestConfig().getCharset());
				String usedCharset = StringUtils.defaultString(request.getRequestConfig().getCharset(), charset);
				StringEntity entity = new StringEntity(request.getBody(), textContentType);
				entity.setContentEncoding(usedCharset);
				entityRequestBase.setEntity(entity);
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bench.lang.base.http.client.HttpClient#execute(com.bench.common. lang.http.client.request.HttpBytesBodyRequest)
	 */
	@Override
	public HttpResponse execute(HttpBytesBodyRequest request) throws HttpClientException {
		// TODO Auto-generated method stub
		return this.execute(request, new RequestPreparer<HttpBytesBodyRequest>() {
			@Override
			public void prepare(HttpBytesBodyRequest request, HttpRequestBase requestBase) throws HttpClientException {
				// TODO Auto-generated method stub
				if (!HttpEntityEnclosingRequestBase.class.isAssignableFrom(requestBase.getClass())) {
					throw new HttpClientException(CommonErrorCodeEnum.SYSTEM_ERROR, "当前method不支持该请求,request=" + request);
				}
				try {
					requestBase.setURI(new URI(request.getUrl()));
				} catch (URISyntaxException e) {
					throw new HttpClientException(HttpClientErrorCodeEnum.URL_INVALID, "不正确的URI:" + request.getUrl(), e);
				}
				HttpEntityEnclosingRequestBase entityRequestBase = (HttpEntityEnclosingRequestBase) requestBase;
				ContentType textContentType = ContentType.create(StringUtils.defaultString(request.getMimeType(), ContentType.DEFAULT_BINARY.getMimeType()),
						request.getRequestConfig().getCharset());
				String usedCharset = StringUtils.defaultString(request.getRequestConfig().getCharset(), charset);
				ByteArrayEntity entity = new ByteArrayEntity(request.getBody(), textContentType);
				entity.setContentEncoding(usedCharset);
				entityRequestBase.setEntity(entity);
			}
		});
	}

	@Override
	public HttpResponse execute(HttpInputStreamBodyRequest request) throws HttpClientException {
		// TODO Auto-generated method stub
		return this.execute(request, new RequestPreparer<HttpInputStreamBodyRequest>() {
			@Override
			public void prepare(HttpInputStreamBodyRequest request, HttpRequestBase requestBase) throws HttpClientException {
				// TODO Auto-generated method stub
				if (!HttpEntityEnclosingRequestBase.class.isAssignableFrom(requestBase.getClass())) {
					throw new HttpClientException(CommonErrorCodeEnum.SYSTEM_ERROR, "当前method不支持该请求,request=" + request);
				}
				try {
					requestBase.setURI(new URI(request.getUrl()));
				} catch (URISyntaxException e) {
					throw new HttpClientException(HttpClientErrorCodeEnum.URL_INVALID, "不正确的URI:" + request.getUrl(), e);
				}
				HttpEntityEnclosingRequestBase entityRequestBase = (HttpEntityEnclosingRequestBase) requestBase;
				ContentType textContentType = ContentType.create(StringUtils.defaultString(request.getMimeType(), ContentType.DEFAULT_BINARY.getMimeType()),
						request.getRequestConfig().getCharset());
				String usedCharset = StringUtils.defaultString(request.getRequestConfig().getCharset(), charset);
				InputStreamEntity entity = new InputStreamEntity(request.getBody(), textContentType);
				entity.setContentEncoding(usedCharset);
				entityRequestBase.setEntity(entity);
			}
		});
	}

	/**
	 * 请求准备接口
	 * 
	 * @author cold
	 *
	 * @version $Id: HttpClientImpl.java, v 0.1 2018年5月23日 上午10:19:36 cold Exp $
	 */
	private static interface RequestPreparer<REQ extends AbstractHttpRequest> {
		public void prepare(REQ request, HttpRequestBase requestBase) throws HttpClientException;
	}

	@Override
	public <REQ extends HttpRequest> HttpResponse execute(REQ request) throws HttpClientException {
		// TODO Auto-generated method stub
		if (request instanceof HttpStringBodyRequest) {
			return execute((HttpStringBodyRequest) request);
		} else if (request instanceof HttpBytesBodyRequest) {
			return execute((HttpBytesBodyRequest) request);
		}
		if (request instanceof HttpInputStreamBodyRequest) {
			return execute((HttpInputStreamBodyRequest) request);
		}
		if (request instanceof HttpParameterRequest) {
			return execute((HttpParameterRequest) request);
		}
		throw new HttpClientException(CommonErrorCodeEnum.SYSTEM_ERROR, "不支持的request:" + request.getClass());
	}

}
