package com.bench.httpclient.httpcomponents;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;

/**
 * <p>
 * HttpDeleteWithBody 默认的HttpDelete对象不支持写入body
 * <a href="https://stackoverflow.com/questions/3773338/httpdelete-with-body">...</a>
 * </p>
 *
 * @author Karl
 * @date 2022/8/11 16:28
 */
public class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "DELETE";

    public HttpDeleteWithBody() {
    }

    public HttpDeleteWithBody(URI uri) {
        this.setURI(uri);
    }

    public HttpDeleteWithBody(String uri) {
        this.setURI(URI.create(uri));
    }

    public String getMethod() {
        return "DELETE";
    }
}