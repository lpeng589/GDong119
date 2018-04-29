package com.pg.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class SinaHttpConnect {
	
	/**
	 * get请求
	 * @param strUrl
	 * @return
	 */
	 public static String get(String strUrl) {
		return  net(strUrl, null,null);
	 }

	   /**
    *
    * @param strUrl 请求地址
    * @param params 请求参数
    * @param method 请求方法
    * @return  网络请求字符串
    * @throws Exception
    */
   public static String net(String strUrl, Map<String, Object> params,String method) {
       HttpURLConnection conn = null;
       BufferedReader reader = null;
       String rs = null;
       try {
           StringBuffer sb = new StringBuffer();
           URL url = new URL(strUrl);
           conn = (HttpURLConnection) url.openConnection();
           if(method==null || method.equals("GET")){
               conn.setRequestMethod("GET");
           }else{
               conn.setRequestMethod("POST");
               conn.setDoOutput(true);
           }
           conn.connect();
           if (params!= null && method.equals("POST")) {
               try (DataOutputStream out = new DataOutputStream(conn.getOutputStream())) {
                   out.writeBytes(urlencode(params));
               }
           }
           InputStream is = (InputStream) conn.getInputStream();
           reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
           String strRead = null;
           while ((strRead = reader.readLine()) != null) {
               sb.append(strRead);
           }
           rs = sb.toString();
       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           if (reader != null) {
               try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
           }
           if (conn != null) {
               conn.disconnect();
           }
       }
       return rs;
   }
   
   //将map型转为请求参数型
   public static String urlencode(Map<String, ?> data) {
       StringBuilder sb = new StringBuilder();
       for (Map.Entry<String, ?> i : data.entrySet()) {
           try {
               sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","gb2312"));
           } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
           }
       }
       return sb.toString();
   }
   public static void main(String[] args) {
	   
	   
//	String url="http://api.caipiaokong.com/live/?name=dc&format=json&uid=218528&token=800b3ab5b572f9142187272680af8250f324bdbf";
//    String result =get(url.toString());
//    JSONArray jsStr = JSONObject.parseArray(result);
//    for (Object object : jsStr) {
//    	JSONObject  data = (JSONObject) object;
//    	System.out.println("+++++++++++++++++++++++++++++++++++++"+data.getString("away_team"));
//	}
	   /*
	   Timestamp ts1 = Timestamp.valueOf("2016-02-29 01:11:01");
	   Timestamp ts2 = Timestamp.valueOf("2016-02-29 01:11:00");
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		  String s1 =   formatter.format(ts1);
		  String s2 =   formatter.format(ts2);
		System.out.println("+++++++++++++++++++++++++++++++++++++"+ s1);
		System.out.println("+++++++++++++++++++++++++++++++++++++"+ s2);
	    int res=s2.compareTo(s1); 
		System.out.println("+++++++++++++++++++++++++++++++++++++"+ res);*/
//		System.out.println("+++++++++++++++++++++++++++++++++++++"+ ts2.before(ts1));
//	   System.out.println("+++++++++++++++++++++++++++++++++++++"+ ts2.before(ts1));
//	   Date currentTime = new Date();
//
//	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	   
//	   formatter.format(currentTime);
//	   System.currentTimeMillis();
	   Timestamp scurrtest2 = new Timestamp(System.currentTimeMillis());//最大时间
	   scurrtest2.setDate(24);//最大时间
	   
	   
	   Timestamp timenow = new Timestamp(System.currentTimeMillis());//现在时间
	   //现在时间是否超过十点
	   GregorianCalendar calendar=new GregorianCalendar();
	   calendar.set(calendar.HOUR_OF_DAY, 10);
	   calendar.set(calendar.MINUTE, 0);
	   calendar.set(calendar.SECOND, 0);
	   Timestamp timeten = new Timestamp(calendar.getTimeInMillis());//现在时间 的10点
	   
	   HashMap mapdate =  new HashMap();
//	   Timestamp  time2 =  new Timestamp(System.currentTimeMillis());//现在时间
		ArrayList datelist  =  new ArrayList();
	       if(timenow.compareTo(timeten)==1){//现在时间大于当天10点
			   //现在时间--次日10点
			   mapdate.put("begintime", timenow);//
			   mapdate.put("week", getWeekOfDate(calendar));
			   calendar.add(calendar.DAY_OF_MONTH, 1); //后续一天
			   mapdate.put("endtime", new Timestamp(calendar.getTimeInMillis()));
			   //时间区间去查场数
			   
			   System.out.println("+++++++++++++++++++++++++++++++++++++"+ mapdate);
			   datelist.add(mapdate);
		   }else{//现在时间小于当天10点
			   mapdate.put("begintime", timenow);//
			   //星期
			   calendar.add(calendar.DAY_OF_MONTH, -1); //前一天的星期
			   mapdate.put("week", getWeekOfDate(calendar));
			   calendar.add(calendar.DAY_OF_MONTH, 1); //前一天的星期
			   mapdate.put("endtime", new Timestamp(calendar.getTimeInMillis()));
			   //时间区间去查场数
			   
			   
			   System.out.println("+++++++++++++++++++++++++++++++++++++"+ mapdate);
			   datelist.add(mapdate);
		   }
		   while(scurrtest2.compareTo(new Timestamp(calendar.getTimeInMillis()))==1){//最大时间大于endtime
			   mapdate.put("begintime", new Timestamp(calendar.getTimeInMillis()));//
			   mapdate.put("week", getWeekOfDate(calendar));
			   calendar.add(calendar.DAY_OF_MONTH, 1); //后续一天
			   mapdate.put("endtime", new Timestamp(calendar.getTimeInMillis()));
			   //时间区间去查场数
			   
			   System.out.println("+++++++++++++++++++++++++++++++++++++"+ mapdate);
			   datelist.add(mapdate);
		   }
		   
		   
   }
   
   public static String getWeekOfDate(Calendar calendar) {
       String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
       int w = calendar.get(calendar.DAY_OF_WEEK) - 1;
       if (w < 0)
           w = 0;
       return weekDays[w];
   }
 
}
