package com.pg.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;



public class GetLocation {  
      
	/**
	 * 阿里API 根据经纬度获取地址
	 * @param log
	 * @param lat
	 * @return
	 */
    public static String getAdd(String log, String lat ){  
        //lat 小  log  大  
        //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)  
        String urlString = "http://gc.ditu.aliyun.com/regeocoding?l="+lat+","+log+"&type=010";  
        String res = "";     
        try {     
            URL url = new URL(urlString);    
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();    
            conn.setDoOutput(true);    
            conn.setRequestMethod("POST");    
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));    
            String line;    
           while ((line = in.readLine()) != null) {    
               res += line+"\n";    
         }    
            in.close();    
        } catch (Exception e) {    
            System.out.println("error in wapaction,and e is " + e.getMessage());    
        }   
        return res;    
    }  
    
    /**
     * 百度API 根据经纬度获取地址
     * @param latitude 纬度
     * @param longitude 经度
     * @throws MalformedURLException
     */
    public static String getAddressByBaidu(String latitude,String longitude) throws MalformedURLException{
        BufferedReader in = null;
        URL tirc = new URL("http://api.map.baidu.com/geocoder/v2/?ak=LyLwGZNGxFvkdedDYEHvcUk71oPBjGKi&location="+latitude+","+longitude+"&output=json"); 
        String address = "";
        try {
        in = new BufferedReader(new InputStreamReader(tirc.openStream(),"UTF-8"));
        String res;  
        StringBuilder sb = new StringBuilder("");  
        while((res = in.readLine())!=null){  
            sb.append(res.trim());  
        }  
        String str = sb.toString();
//        System.out.println(str);
        JSONObject jb = JSONObject.fromObject(str);
        if (jb.getString("status").equals("0")) {
        	JSONObject js = JSONObject.fromObject(jb.getString("result"));
			address = js.getString("formatted_address")+js.getString("sematic_description");
		}
	    } catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	    } catch (IOException e) {
	    e.printStackTrace();
	    }
        return address;
  }
    
    
    /**
     * 腾讯API 根据经纬度获取地址
     * @param latitude 纬度
     * @param longitude 经度
     * @param return 返回位置描述
     * @throws MalformedURLException
     */
    public static String getAddressByTengxun(String latitude,String longitude) throws MalformedURLException{
        BufferedReader in = null;
        URL tirc = new URL("http://apis.map.qq.com/ws/geocoder/v1/?location="+latitude+","+longitude+"&key=DV6BZ-E3AW3-55Y3X-3GGLC-KFFEF-XMF4U&coord_type=1"); 
        String address = "";
        try {
        in = new BufferedReader(new InputStreamReader(tirc.openStream(),"UTF-8"));
        String res;  
        StringBuilder sb = new StringBuilder("");  
        while((res = in.readLine())!=null){  
            sb.append(res.trim());  
        }  
        String str = sb.toString();
        JSONObject jb = JSONObject.fromObject(str);
        if (jb.getString("status").equals("0")) {
        	JSONObject js = JSONObject.fromObject(jb.getString("result"));
        	JSONObject ja = JSONObject.fromObject(js.getString("formatted_addresses"));
			address = ja.getString("recommend");
		}
	    } catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	    } catch (IOException e) {
	    e.printStackTrace();
	    }
        return address;
  }
    
    /**
     * 腾讯API 根据经纬度获取地址
     * @param latitude 纬度
     * @param longitude 经度
     * @param return 返回位置描述
     */
    public static Map<String, String> getAddressMapByTengxun(String latitude,String longitude){
    	Map<String, String> map = new HashMap<>();
    	try {
    		 BufferedReader in = null;
    	        URL tirc = new URL("http://apis.map.qq.com/ws/geocoder/v1/?location="+latitude+","+longitude+"&key=DV6BZ-E3AW3-55Y3X-3GGLC-KFFEF-XMF4U&coord_type=1"); 
    	        in = new BufferedReader(new InputStreamReader(tirc.openStream(),"UTF-8"));
    	        String res;  
    	        StringBuilder sb = new StringBuilder("");  
    	        while((res = in.readLine())!=null){  
    	            sb.append(res.trim());  
    	        }  
    	        String str = sb.toString();
    	        JSONObject jb = JSONObject.fromObject(str);
    	        if (jb.getString("status").equals("0")) {
    	        	JSONObject js = JSONObject.fromObject(jb.getString("result"));
    	        	JSONObject ja = JSONObject.fromObject(js.getString("address_component"));
    	        	map.put("nation", ja.getString("nation"));
    	        	map.put("province", ja.getString("province"));
    	        	map.put("city", ja.getString("city"));
    	        	map.put("district", ja.getString("district"));
    	        	map.put("street", ja.getString("street"));
    	        	map.put("street_number", ja.getString("street_number"));
    	        	map.put("address", js.getString("address"));
    	        	JSONObject ja2 = JSONObject.fromObject(js.getString("address_reference"));
    	        	JSONObject town = JSONObject.fromObject(ja2.getString("town"));
    	        	map.put("town", town.getString("title"));
    			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return map;
  }
    
    
    /**
     * 腾讯API 地址--->坐标
     * @param latitude 纬度
     * @param longitude 经度
     * @param return 返回位置描述
     * @throws MalformedURLException
     */
    public static   Map<String, String> getItudeByTengxun(String address) {
        BufferedReader in = null;
        URL tirc = null;
		try {
			tirc = new URL("http://apis.map.qq.com/ws/geocoder/v1/?address="+URLEncoder.encode(address, "UTF-8")+"&key="+"DV6BZ-E3AW3-55Y3X-3GGLC-KFFEF-XMF4U");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
        Map<String, String> map = new HashMap<String, String>();
        try {
        in = new BufferedReader(new InputStreamReader(tirc.openStream(),"UTF-8"));
        String res=null;  
        StringBuilder sb = new StringBuilder("");  
        while((res = in.readLine())!=null){  
            sb.append(res.trim());  
        }  
        String lat="";
        String lng="";
        String str = sb.toString();
        JSONObject jb = JSONObject.fromObject(str);
        if (jb.getString("status").equals("0")) {
        	JSONObject js = JSONObject.fromObject(jb.getString("result"));
        	JSONObject ja = JSONObject.fromObject(js.getString("location"));
        	lat = ja.getString("lat");
        	lng = ja.getString("lng");
        	map.put("lat", lat);
        	map.put("lng", lng);
		}
	    } catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	    } catch (IOException e) {
	    e.printStackTrace();
	    }
        return map;
  } 
    
   private static double EARTH_RADIUS = 6378.137; 
    
    private static double rad(double d) { 
        return d * Math.PI / 180.0; 
    }
     
    
    /**
     * 根据两个位置的经纬度，来计算两地的距离（单位为KM）
     * 参数为String类型
     * @param lat1 用户经度
     * @param lng1 用户纬度
     * @param lat2 商家经度
     * @param lng2 商家纬度
     * @return
     */
    public static Double getDistance(String lat1Str, String lng1Str, String lat2Str, String lng2Str) {
        Double lat1 = Double.parseDouble(lat1Str);
        Double lng1 = Double.parseDouble(lng1Str);
        Double lat2 = Double.parseDouble(lat2Str);
        Double lng2 = Double.parseDouble(lng2Str);
         
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double difference = radLat1 - radLat2;
        double mdifference = rad(lng1) - rad(lng2);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(mdifference / 2), 2)));
        distance = distance * EARTH_RADIUS;
        distance = Math.round(distance * 10000) / 10000;
        String distanceStr = distance+"";
        distanceStr = distanceStr.
            substring(0, distanceStr.indexOf("."));
         
        return  Double.valueOf(distanceStr);
    }  
    
    
    public static void main(String[] args) throws MalformedURLException {  
        // lat 39.97646       
        //log 116.3039   
//        String add = getAdd("116.3039", "39.97646");  
//        JSONObject jsonObject = JSONObject.fromObject(add);  
//        JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("addrList"));  
//        JSONObject j_2 = JSONObject.fromObject(jsonArray.get(0));  
//        String allAdd = j_2.getString("admName");  
//        String arr[] = allAdd.split(",");  116.403958,39.915049
//        System.out.println("省："+arr[0]+"\n市："+arr[1]+"\n区："+arr[2]);  
//    	System.out.println(getAddressByTengxun("22.525719","113.934799"));
//    	System.out.println(getItudeByTengxun("二郎路15号") );
//    	System.out.println(getItudeByTengxun("重庆市九龙坡区华岩镇石堰村五社天灯堡") );
//    	getposition("116.403958","39.915049");
//    	System.out.println(getAddressByTengxun("22.525719","113.934799"));
//    	System.out.println(getItudeByTengxun("重庆市九龙坡区科园一街57号"));
    	
//		  Map<String, String> addressHashMap = GetLocation.getAddressMapByTengxun("29.53111","106.49334");
//		  System.out.println(addressHashMap);
		  
//		  System.out.println(getAddressMapByTengxun("29.53111","106.49334"));
//		  System.out.println(getAddressMapByTengxun("22.525719","113.934799"));
//		  System.out.println(getAddressMapByTengxun("29.47400799589596","106.4353474522088"));
//		  System.out.println(getAddressMapByTengxun("29.43325","106.4489"));
    }  
}  