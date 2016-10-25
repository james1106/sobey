package com.magic.sangha.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;





public class SMSCode {
	
	private static String URI_SEND_SMS = "https://sms.yunpian.com/v1/sms/send.json";
	
	/**�ֻ�ע��ʱ,������֤�뻰�� {0} ��֤��*/
	public static String MOBILE_REGIST="���ڴ�С�������ֻ�ע�����֤����{0}";
	
	/**�һ�����ʱ���ֻ���֤��  {0}��֤��*/
	public static String FORGET_PASSWORD = "���ڴ�С����������֤����{0}��Ϊ���˺Ű�ȫ��������֤��������ˡ�";
	
	/**��̨�����û���֪ͨ���û�*/
	public static String ADMIN_REGISTER = "���������ڡ��װ���{0}��������ʱ��¼����Ϊ{1}��С����ܰ��ʾ����½ƽ̨��������Ϣ�˵���ɿ����޸����롣";
	
	/**�ʺ����ͨ���� {0} ����*/
	public static String ACCOUNT_PASS = "���ڴ�С�����װ���{0}������ͨ��С������ˣ��Ͽ��������~";
	
	public static boolean sendMessage(String text,String mobile){
		boolean isSend = false;
		return true;
//		try {
//			String result = sendSms("4846e1be82ad01a4ee9dd6fbdbf72df0",text,mobile);
//			
//			JSONObject js = JSONObject.fromObject(result);
//			if(0 == js.getInt("code")){
//				isSend = true;
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return isSend;
	}
	
	public static String createRandomCode(){
		
		String vcode = "" ;
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int)(Math.random() * 9);
        }
        return vcode;
	}
	
private  static String sendSms(String apikey, String text, String mobile) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("text", text);
        params.put("mobile", mobile);
        return post(URI_SEND_SMS, params);
    }
	
private static String post(String url, Map<String, String> paramsMap) {
		
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity( new UrlEncodedFormEntity(paramList, "UTF-8"));
            }
            response = client.execute((HttpUriRequest) method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseText;
    }
	
	
}