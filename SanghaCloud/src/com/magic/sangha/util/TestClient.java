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
			System.out.println("ִ��get����:...."+get.getURI());
			CloseableHttpResponse httpResponse = null;
			//����get����
            httpResponse = httpClient.execute(get);
            try {
				
            	HttpEntity entity = httpResponse.getEntity();
                if (null != entity){
                    System.out.println("��Ӧ״̬��:"+ httpResponse.getStatusLine());
                    System.out.println("-------------------------------------------------");
                    System.out.println("��Ӧ����:" + EntityUtils.toString(entity));
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
