package com.bench.httpclient.base.upload.file;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.bench.lang.base.array.utils.ArrayUtils;

/**
 * 基于字节的上传文件对象
 * 
 * @author cold
 * 
 * @version $Id: BytesUploadFile.java, v 0.1 2013-8-26 下午4:22:21 cold Exp $
 */
public class BytesUploadFile extends AbstractUploadFile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2223557266511946968L;

	/**
	 * 文件内容
	 */
	private byte[] content;

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	@Override
	public InputStream getInputStream() {
		// TODO Auto-generated method stub
		return new ByteArrayInputStream(content);
	}

	@Override
	public long getLength() {
		// TODO Auto-generated method stub
		return ArrayUtils.getLength(content);
	}

}
