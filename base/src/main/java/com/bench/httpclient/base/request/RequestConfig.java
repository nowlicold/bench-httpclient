package com.bench.httpclient.base.request;

import java.net.InetAddress;
import java.util.Collection;

import com.bench.httpclient.base.model.HttpProxy;
import com.bench.httpclient.base.request.enums.CookieSpecEnum;

/**
 * 请求配置
 * 
 * @author cold
 *
 * @version $Id: RequestConfig.java, v 0.1 2018年5月8日 上午10:38:11 cold Exp $
 */
public class RequestConfig {

	/**
	 * 代理
	 */
	private HttpProxy proxy;

	/**
	 * 字符集合
	 */
	private String charset;

	private boolean expectContinueEnabled;

	private InetAddress localAddress;

	private boolean staleConnectionCheckEnabled;

	private CookieSpecEnum cookieSpec;

	private boolean redirectsEnabled;

	private boolean relativeRedirectsAllowed;

	private boolean circularRedirectsAllowed;

	private int maxRedirects;

	private boolean authenticationEnabled;

	private Collection<String> targetPreferredAuthSchemes;

	private Collection<String> proxyPreferredAuthSchemes;

	private int connectionRequestTimeout = -1;

	private int connectTimeout = -1;

	private int socketTimeout = -1;

	private boolean contentCompressionEnabled = true;

	/**
	 * Intended for CDI compatibility
	 */
	protected RequestConfig() {

	}

	public HttpProxy getProxy() {
		return proxy;
	}

	public void setProxy(HttpProxy proxy) {
		this.proxy = proxy;
	}

	public boolean isExpectContinueEnabled() {
		return expectContinueEnabled;
	}

	public InetAddress getLocalAddress() {
		return localAddress;
	}

	public boolean isStaleConnectionCheckEnabled() {
		return staleConnectionCheckEnabled;
	}

	public CookieSpecEnum getCookieSpec() {
		return cookieSpec;
	}

	public boolean isRedirectsEnabled() {
		return redirectsEnabled;
	}

	public boolean isRelativeRedirectsAllowed() {
		return relativeRedirectsAllowed;
	}

	public boolean isCircularRedirectsAllowed() {
		return circularRedirectsAllowed;
	}

	public int getMaxRedirects() {
		return maxRedirects;
	}

	public boolean isAuthenticationEnabled() {
		return authenticationEnabled;
	}

	public Collection<String> getTargetPreferredAuthSchemes() {
		return targetPreferredAuthSchemes;
	}

	public Collection<String> getProxyPreferredAuthSchemes() {
		return proxyPreferredAuthSchemes;
	}

	public int getConnectionRequestTimeout() {
		return connectionRequestTimeout;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public boolean isContentCompressionEnabled() {
		return contentCompressionEnabled;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public void setExpectContinueEnabled(boolean expectContinueEnabled) {
		this.expectContinueEnabled = expectContinueEnabled;
	}

	public void setLocalAddress(InetAddress localAddress) {
		this.localAddress = localAddress;
	}

	public void setStaleConnectionCheckEnabled(boolean staleConnectionCheckEnabled) {
		this.staleConnectionCheckEnabled = staleConnectionCheckEnabled;
	}

	public void setCookieSpec(CookieSpecEnum cookieSpec) {
		this.cookieSpec = cookieSpec;
	}

	public void setRedirectsEnabled(boolean redirectsEnabled) {
		this.redirectsEnabled = redirectsEnabled;
	}

	public void setRelativeRedirectsAllowed(boolean relativeRedirectsAllowed) {
		this.relativeRedirectsAllowed = relativeRedirectsAllowed;
	}

	public void setCircularRedirectsAllowed(boolean circularRedirectsAllowed) {
		this.circularRedirectsAllowed = circularRedirectsAllowed;
	}

	public void setMaxRedirects(int maxRedirects) {
		this.maxRedirects = maxRedirects;
	}

	public void setAuthenticationEnabled(boolean authenticationEnabled) {
		this.authenticationEnabled = authenticationEnabled;
	}

	public void setTargetPreferredAuthSchemes(Collection<String> targetPreferredAuthSchemes) {
		this.targetPreferredAuthSchemes = targetPreferredAuthSchemes;
	}

	public void setProxyPreferredAuthSchemes(Collection<String> proxyPreferredAuthSchemes) {
		this.proxyPreferredAuthSchemes = proxyPreferredAuthSchemes;
	}

	public void setConnectionRequestTimeout(int connectionRequestTimeout) {
		this.connectionRequestTimeout = connectionRequestTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public void setContentCompressionEnabled(boolean contentCompressionEnabled) {
		this.contentCompressionEnabled = contentCompressionEnabled;
	}

}
