/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.bench.httpclient.extension.lang.loader.url;

import com.bench.lang.base.loader.merageable.MergeableListLoader;

import java.util.List;


/**
 * 基于URL的List类型的数据loader
 * 
 * @author cold
 *
 * @version $Id: UrlsListLoader.java, v 0.1 2020年5月19日 下午3:22:56 cold Exp $
 */
public interface UrlsListLoader<E> extends UrlsLoader<List<E>>, MergeableListLoader<E> {

}
