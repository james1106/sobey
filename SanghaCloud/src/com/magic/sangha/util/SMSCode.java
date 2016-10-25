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
	
	/**手机注册时,发送验证码话术 {0} 验证码*/
	public static String MOBILE_REGIST="【口袋小贝】您手机注册的验证码是{0}";
	
	/**找回密码时，手机验证码  {0}验证码*/
	public static String FORGET_PASSWORD = "【口袋小贝】您的验证码是{0}，为了账号安全，请勿将验证码告诉他人。";
	
	/**后台添加用户后，通知该用户*/
	public static String ADMIN_REGISTER = "【索贝轻融】亲爱的{0}，您的临时登录密码为{1}。小贝温馨提示：登陆平台，个人信息菜单里可快速修改密码。";
	
	/**帐号审核通过后 {0} 姓名*/
	public static String ACCOUNT_PASS = "【口袋小贝】亲爱的{0}，您已通过小贝的审核，赶快来体验吧~";
	
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
