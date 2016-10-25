package com.magic.sangha.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * HTTP GET请求工具类
 * 
 * @author QimouXie
 *
 */
public class HttpGetRequest {
	

	public static  String sendRequest(String url) {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		Map<String,Object> data = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		try {
			
			HttpGet get = new HttpGet(url);
			CloseableHttpResponse httpResponse = null;
			//发送get请求
            httpResponse = httpClient.execute(get);
            try {
				
            	HttpEntity entity = httpResponse.getEntity();
                if (null != entity){
                    data.put("code", httpResponse.getStatusLine());
//                    System.out.println("响应内容:" + EntityUtils.toString(entity));
                   sb.append(EntityUtils.toString(entity));            
                }
			} catch (Exception e) {
			}finally{
                httpResponse.close();
            }
		} catch (Exception e) {
		}finally{
            try{
            	closeHttpClient(httpClient);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
		data.put("data", sb.toString());
		String str = new String(sb);
		return str;
	}
	
	private static  void closeHttpClient(CloseableHttpClient client) throws IOException{
        if (client != null){
            client.close();
        }
    }
	
	public static void main(String[] args) {
		String data = HttpGetRequest.sendRequest(UrlUtils.geturl(null, UrlUtils.GETALLTV_URL));
		System.out.println("------"+data);
	}

}
