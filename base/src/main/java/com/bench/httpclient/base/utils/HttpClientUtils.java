package com.bench.httpclient.base.utils;

import com.bench.lang.base.web.cookie.HeaderNameEnum;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * 
 * @author cold
 *
 * @version $Id: HttpClientUtils.java, v 0.1 2016年2月24日 下午12:44:14 cold Exp $
 */
public class HttpClientUtils {

	/**
	 * 使用HttpClient重定向时，忽略的头部信息
	 */
	public static final String[] REDIRECT_IGNORE_HEADER_NAMES = new String[] { HeaderNameEnum.CONTENT_LENGTH.headerName(), HeaderNameEnum.HOST.headerName() };

	/**
	 * 创建win10 搜狗浏览器的头部文件
	 */
	public static Map<String, String> createBasicRequestHeaderMap() {
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		headerMap.put("Accept-Encoding", "gzip,deflate");
		headerMap.put("Accept-Language", "zh-CN,zh;q=0.8");
		headerMap.put("Cache-Control", "max-age=0");
		headerMap.put("Connection", "keep-alive");
		headerMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36 SE 2.X MetaSr 1.0");
		return headerMap;
	}
}
