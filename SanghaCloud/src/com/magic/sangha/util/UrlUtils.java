package com.magic.sangha.util;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2016/7/13 0013.
 */
public class UrlUtils {

    private static final String secretKey = "893ff8a2ab604d8bab9407bd91376ee3";
    private static final String app = "Care_app";
    private static final String type = "json";
    
    /**请求所有电视台的初始URL*/
    public static final String GETALLTV_URL = "http://120.76.165.97/sobey/center/openservice/station";
    /** 请求设备列表列表*/
    public static final String GET_DEVICELIST = "http://120.76.165.97/sobey/center/openservice/host";
    /**请求 最新告警信息*/
    public static final String GET_NEWEVENT = "http://120.76.165.97/sobey/center/openservice/alert/new";
    
    public static final String GET_COUNT = "http://120.76.165.97/sobey/center/openservice/stats/station";

    /**
     *  生成 完成请求的URL  
     * @param map 参数
     * @param url 初始URL
     * @return
     */
    public static String geturl(Map<String, String> map, String url) {
        String para = "";
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                para += key + "=" + value + "&";
            }
        }
        return url + "?" + getafturl(para);
    }

    private static String getafturl(String pp) {
        String timestamp = System.currentTimeMillis() + "";
        String para = pp + "app=" + app + "&timestamp=" + timestamp + "&type=" + type;
        String usign = (secretKey + para).toLowerCase();
        String sign = MD5Util.md5(usign);

        String afturl = para + "&sign=" + sign;
        return afturl;
    }

    /**
     * String url= "http://120.76.165.97/sobey/center/openservice/stats/station";
     * HashMap<String, String> map = new HashMap<>();
     * map.put("station","CCTV");
     * String newurl= UrlUtils.geturl(map, url);
     */
    
    public static void main(String[] args) {
    	Map<String,String> data= new HashMap<String, String>();
//    	SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    	try {
//			Date date = format.parse("2016-08-02 16:38:34");
////    		Date date = new Date();
//			data.put("time",  date.getTime()+"");
//			data.put("count", 10000+"");
////			data.put("station", "TCTC_20160527");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
    	
//    	data.put("station", "TCTC_20160527");
    	System.out.println(UrlUtils.geturl(data, GET_DEVICELIST));
    	
	}
}
