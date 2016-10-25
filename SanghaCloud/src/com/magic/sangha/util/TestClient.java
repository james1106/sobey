package com.magic.sangha.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class TestClient {

	public static void main(String[] args) {

		
		
		TestClient test = new TestClient();
		
		String url = UrlUtils.geturl(null, UrlUtils.GETALLTV_URL);
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			
			HttpGet get = new HttpGet(url);
			System.out.println("执行get请求:...."+get.getURI());
			CloseableHttpResponse httpResponse = null;
			//发送get请求
            httpResponse = httpClient.execute(get);
            try {
				
            	HttpEntity entity = httpResponse.getEntity();
                if (null != entity){
                    System.out.println("响应状态码:"+ httpResponse.getStatusLine());
                    System.out.println("-------------------------------------------------");
                    System.out.println("响应内容:" + EntityUtils.toString(entity));
                    System.out.println("-------------------------------------------------");                    
                }
			} catch (Exception e) {
			}finally{
                httpResponse.close();
            }
			
			
		} catch (Exception e) {
		}finally{
            try{
            	test.closeHttpClient(httpClient);
            } catch (IOException e){
                e.printStackTrace();
            }
        }

	}
	private void closeHttpClient(CloseableHttpClient client) throws IOException{
        if (client != null){
            client.close();
        }
    }

}
