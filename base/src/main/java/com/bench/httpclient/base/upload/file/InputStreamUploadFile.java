package com.bench.httpclient.base.upload.file;

import java.io.InputStream;

/**
 * 基于流的上传文件
 * 
 * @author cold
 * 
 * @version $Id: InputStreamUploadFile.java, v 0.1 2013-8-26 下午4:22:21 cold Exp $
 */
public class InputStreamUploadFile extends AbstractUploadFile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2223557266511946968L;

	/**
	 * 输入流
	 */
	private InputStream inputStream;

	/**
	 * 输入流
	 */
	private long length;

	/**
	 * @param inputStream
	 * @param length
	 */
	public InputStreamUploadFile(InputStream inputStream, long length) {
		super();
		this.inputStream = inputStream;
		this.length = length;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	public long getLength() {
		// TODO Auto-generated method stub
		return length;
	}

}
