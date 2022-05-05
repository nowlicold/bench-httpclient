package com.bench.httpclient.base.request;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bench.lang.base.map.multi.ArrayListValuedHashMap;
import com.bench.lang.base.map.multi.MultiValuedMap;
import com.bench.lang.base.string.build.ToStringObject;
import com.bench.lang.base.web.cookie.Cookie;

/**
 * 请求的Cookie信息
 * 
 * @author cold
 *
 * @version $Id: RequestCookies.java, v 0.1 2018年12月14日 下午4:20:57 cold Exp $
 */
public class RequestCookies extends ToStringObject {

	/**
	 * cookie信息
	 */
	protected MultiValuedMap<String, String> cookieMap = new ArrayListValuedHashMap<String, String>();

	/**
	 * 返回cookie数量
	 */
	public int size() {
		return cookieMap.size();
	}

	/**
	 * 移除
	 * 
	 * @param name
	 * @return
	 */
	public Collection<String> remove(String name) {
		return this.cookieMap.remove(name);
	}

	/**
	 * 所有的name集合
	 * 
	 * @return
	 */
	public Set<String> names() {
		return cookieMap.keySet();
	}

	/**
	 * 移除
	 * 
	 * @param name
	 * @return
	 */
	public boolean contains(String name) {
		return this.cookieMap.containsKey(name);
	}

	/**
	 * 获取头部值集合
	 * 
	 * @param name
	 * @return
	 */
	public Collection<String> get(String name) {
		return this.cookieMap.get(name);
	}

	/**
	 * 获取第一个头部值
	 * 
	 * @param name
	 * @return
	 */
	public String getFirst(String name) {
		Collection<String> values = this.cookieMap.get(name);
		return values == null || values.size() == 0 ? null : values.iterator().next();
	}

	/**
	 * 增加头部信息
	 * 
	 * @param name
	 * @param value
	 */
	public void add(String name, String value) {
		cookieMap.put(name, value);
	}

	/**
	 * 增加头部信息
	 * 
	 * @param name
	 * @param value
	 */
	public void add(Cookie cookie) {
		add(cookie.getName(), cookie.getValue());
	}

	/**
	 * 增加头部信息
	 * 
	 * @param cookie
	 */
	public void set(Cookie cookie) {
		set(cookie.getName(), cookie.getValue());
	}

	/**
	 * 重新设置头部信息
	 * 
	 * @param name
	 * @param value
	 */
	public void set(String name, String value) {
		this.cookieMap.remove(name);
		this.cookieMap.put(name, value);
	}

	/**
	 * 重新设置头部信息
	 * 
	 * @param name
	 * @param values
	 */
	public void set(String name, List<String> values) {
		this.cookieMap.remove(name);
		this.cookieMap.putAll(name, values);
	}

	/**
	 * 转换为头部值
	 * 
	 * @return
	 */
	public String toHeaderValue() {
		StringBuffer cookieBuffer = new StringBuffer();
		for (Map.Entry<String, String> entry : this.cookieMap.entries()) {
			cookieBuffer.append(entry.getKey()).append("=").append(entry.getValue()).append("; ");
		}
		return cookieBuffer.toString();
	}
}
