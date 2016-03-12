package com.open.framework.base.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.CharBuffer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class HttpClientUtils {

	private static Log logger = LogFactory.getLog(HttpClientUtils.class);

	/**
	 * http get请求 并返回结果
	 * @param urlStr
	 * @param cookie
	 * @return
	 * @throws Exception 
	 */
	public static String doHttpGet(String urlStr) throws Exception{
		
		String result = null;
		URL url = null; 
		URLConnection httpUrlConnection = null;
		BufferedReader reader = null;
		InputStream in = null;
		try {
			url = new URL(urlStr); 
			httpUrlConnection = url.openConnection();
			//超时时间 3秒
			httpUrlConnection.setConnectTimeout(3000);
			httpUrlConnection.setDoOutput(true); 

			//设置请求的cookie
			httpUrlConnection.setRequestProperty("Cookie", "");
			
			// 设置是否从httpUrlConnection读入，默认情况下是true; 
			httpUrlConnection.setDoInput(true); 
			// Post 请求不能使用缓存 
			httpUrlConnection.setUseCaches(false); 
			// 连接，从上述第2条中url.openConnection()至此的配置必须要在connect之前完成， 
	        httpUrlConnection.connect();
	        in = httpUrlConnection.getInputStream(); 
	        
	    	CharBuffer buff = CharBuffer.allocate(1024);
	    	StringBuilder data = new StringBuilder();
	        reader = new BufferedReader(new InputStreamReader(in));
    	    while ((reader.read(buff)) >= 0) {
	    		buff.flip();
	    		data.append(new String(buff.toString().getBytes(),"utf-8"));
	    		buff.clear();
    	    }
    	    result =  data.toString();   
		} catch (Exception e) {
			throw e;
		} finally {
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					logger.error("http工具类get请求异常：",e);
				}
			}
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					logger.error("http工具类get请求异常：",e);
				}
			}
		}
		return result;
	}
	
}
