package com.bench.httpclient.base.model;

import com.bench.lang.base.string.build.ToStringObject;

/**
 * Http代理
 * 
 * @author cold
 *
 * @version $Id: HttpProxy.java, v 0.1 2018年1月27日 下午11:34:55 cold Exp $
 */
public class HttpProxy extends ToStringObject {

	/**
	 * 代理地址
	 */
	private String host;

	/**
	 * 端口
	 */
	private int port;

	/**
	 * 代理协议，默认是HTTP
	 */
	private String schemeName = "HTTP";

	/**
	 * 代理用户
	 */
	private String userName;

	/**
	 * 代理密码
	 */
	private String password;

	public HttpProxy() {
		super();
	}

	public HttpProxy(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
