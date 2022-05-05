/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.bench.httpclient.extension.lang.loader.url;

import java.util.Map;

import com.bench.lang.base.loader.merageable.MergeableMapLoader;

/**
 * 基于URL的Map类型数据的loader
 * 
 * @author cold
 *
 * @version $Id: FilesMapLoader.java, v 0.1 2020年5月19日 下午3:22:56 cold Exp $
 */
public interface UrlsMapLoader<K, V> extends UrlsLoader<Map<K, V>>, MergeableMapLoader<K, V> {

}
