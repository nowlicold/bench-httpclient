/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.bench.httpclient.base.request;


import com.bench.lang.base.web.enums.CommonMimeEnum;

import java.io.InputStream;


/**
 * 输入流主体请求
 * 
 * @author cold
 *
 * @version $Id: HttpInputStreamBodyRequest.java, v 0.1 2018年5月8日 上午11:48:43 cold Exp $
 */
public class HttpInputStreamBodyRequest extends AbstractHttpBodyRequest<InputStream> {
	public HttpInputStreamBodyRequest() {
		super();
		// TODO Auto-generated constructor stub
		this.mimeType = CommonMimeEnum.OCTET_STREAM.type();
	}

}
