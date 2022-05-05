package com.bench.httpclient.base.upload.file;

import java.io.InputStream;
import java.io.Serializable;

/**
 * 抽象的上传文件对象
 * 
 * @author cold
 * 
 * @version $Id: AbstractUploadFile.java, v 0.1 2013-8-26 下午4:22:21 cold Exp $
 */
public abstract class AbstractUploadFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2223557266511946968L;

	/**
	 * 文件名
	 */
	private String name;

	/**
	 * 获取输入流
	 * 
	 * @return
	 */
	public abstract InputStream getInputStream();

	/**
	 * 得到文件长度
	 * 
	 * @return
	 */
	public abstract long getLength();

	public AbstractUploadFile() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
