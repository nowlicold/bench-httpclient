package com.bench.httpclient.base.head;

import com.bench.lang.base.map.multi.ArrayListValuedHashMap;
import com.bench.lang.base.map.multi.MultiValuedMap;
import com.bench.lang.base.object.ToStringObject;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * 头部Map对象
 * 
 * @author cold
 *
 * @version $Id: Headers.java, v 0.1 2018年5月8日 下午4:59:35 cold Exp $
 */
public class Headers extends ToStringObject {

	/**
	 * 头部信息
	 */
	protected MultiValuedMap<String, String> headerMap = new ArrayListValuedHashMap<String, String>();

	/**
	 * 返回所有的头部名称
	 * 
	 * @return
	 */
	public Set<String> getNames() {
		return headerMap.keySet();
	}

	/**
	 * 返回kv条目集合
	 * 
	 * @return
	 */
	public Collection<Map.Entry<String, String>> entries() {
		return headerMap.entries();
	}

	/**
	 * 是否有头部值
	 * 
	 * @param name
	 * @return
	 */
	public boolean hasValue(String name) {
		Collection<String> value = headerMap.get(name);
		return value != null && value.size() > 0;
	}

	/**
	 * 移除
	 * 
	 * @param name
	 * @return
	 */
	public Collection<String> remove(String name) {
		return this.headerMap.remove(name);
	}

	/**
	 * 移除
	 * 
	 * @param name
	 * @return
	 */
	public boolean contains(String name) {
		return this.headerMap.containsKey(name);
	}

	/**
	 * 获取头部值集合
	 * 
	 * @param name
	 * @return
	 */
	public Collection<String> get(String name) {
		return this.headerMap.get(name);
	}

	/**
	 * 获取第一个头部值
	 * 
	 * @param name
	 * @return
	 */
	public String getFirst(String name) {
		Collection<String> values = this.headerMap.get(name);
		return values == null || values.size() == 0 ? null : values.iterator().next();
	}

	/**
	 * 增加头部信息
	 * 
	 * @param name
	 * @param value
	 */
	public void add(String name, String value) {
		headerMap.put(name, value);
	}

	/**
	 * 重新设置头部信息
	 * 
	 * @param name
	 * @param value
	 */
	public void set(String name, String value) {
		this.headerMap.remove(name);
		this.headerMap.put(name, value);
	}

	/**
	 * 重新设置头部信息
	 * 
	 * @param name
	 * @param values
	 */
	public void set(String name, List<String> values) {
		this.headerMap.remove(name);
		this.headerMap.putAll(name, values);
	}
}
