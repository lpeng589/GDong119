package com.pg.servlet.wx;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.AbstractExecutorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.mysql.jdbc.Util;
import com.pg.bean.AlarmBean;
import com.pg.bean.AlarmdetailBean;
import com.pg.bean.MsgBean;
import com.pg.bean.UserBean;
import com.pg.gzh.util.BaseResult;
import com.pg.gzh.util.GZHSetup;
import com.pg.gzhqy.media.Media;
import com.pg.gzhqy.media.MediaResultBean;
import com.pg.gzhqy.message.Message;
import com.pg.gzhqy.util.HttpRequest;
import com.pg.mapper.AlarmMapper;
import com.pg.mapper.AlarmdetailMapper;
import com.pg.mapper.EmployeeMapper;
import com.pg.mapper.PoslinkMapper;
import com.pg.mapper.UserMapper;
import com.pg.searchbean.AlarmSearchBean;
import com.pg.searchbean.AlarmdetailSearchBean;
import com.pg.searchbean.EmployeeSearchBean;
import com.pg.searchbean.MSearchBean;
import com.pg.searchbean.UserSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.Config;
import com.pg.util.PgFilter;
import com.pg.util.ServletUtil;
import com.pg.util.Tools;
import com.pg.util.UploadFile;
import com.pg.wxsign.AES;
@Controller
public class WXUserAction 
{
	public static String tokenkey = "guangdongshenzhencqxfxcx";
	
	/**
	 * 轮播图
	 * @param request
	 * @param response
	 */
	@RequestMapping("mwxxcxklogin.html")
	public void mwxxcxklogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			String code=request.getParameter("_code");
			String string = HttpRequest.get("https://api.weixin.qq.com/sns/jscode2session?appid="+Config.getValue("wxcxappid")+"&secret="+Config.getValue("wxcxsecret")+"&js_code="+code+"&grant_type=authorization_code");
			HashMap<String, String> mapwx = new Gson().fromJson(string, HashMap.class);
			String openid = mapwx.get("openid");
//			System.out.println(string+"-----------openid------------:"+openid);
//			String openid= "oX64I0UeUxFRTQm6GwfgeWoTEIqc";
			if(openid==null||openid.isEmpty()){
				ServletUtil.sendJsonBeanNull(response, new MsgBean("-1", "服务器获取用户数据错误"));  
				return;
			}
			UserMapper userMapper = ApplicationContextUtil.getMapper(UserMapper.class);
			UserSearchBean searbean = new UserSearchBean();
			searbean.setOpenid(openid);
			UserBean bean = userMapper.getByOpenid(searbean);
			if(bean==null){
				bean = new UserBean();
				bean.setOpenid(openid);
				if(request.getParameter("nickname")!=null)bean.setNickname(request.getParameter("nickname"));
				if(request.getParameter("headimgurl")!=null)bean.setHeadimgurl(request.getParameter("headimgurl"));
				if(request.getParameter("city")!=null)bean.setCity(request.getParameter("city"));
				if(request.getParameter("country")!=null)bean.setCountry(request.getParameter("country"));
				if(request.getParameter("province")!=null)bean.setProvince( request.getParameter("province"));
				if(request.getParameter("sex")!=null)bean.setSex(request.getParameter("sex"));
				if(userMapper.addUser(bean)>0){
					bean = userMapper.getByOpenid(searbean);
				}
			}else {
				
				if(request.getParameter("nickname")!=null)bean.setNickname(  request.getParameter("nickname") );
				if(request.getParameter("headimgurl")!=null)bean.setHeadimgurl(request.getParameter("headimgurl"));
				if(request.getParameter("city")!=null)bean.setCity(request.getParameter("city"));
				if(request.getParameter("country")!=null)bean.setCountry(request.getParameter("country"));
				if(request.getParameter("province")!=null)bean.setProvince(request.getParameter("province"));
				if(request.getParameter("sex")!=null)bean.setSex(request.getParameter("sex"));
				if(userMapper.updateUser(bean)>0){
					bean = userMapper.getByOpenid(searbean);
				}
			}
			if(bean==null){
				ServletUtil.sendJsonBean(response, new MsgBean("-1", "系统错误"));
				return;
			}else{
				Map<String, Object> map = new HashMap<>();
				long current = System.currentTimeMillis();
				String time = String.valueOf(current);
				String token = Tools.MD5(tokenkey+time);
				Calendar nowTime = Calendar.getInstance();
				nowTime.add(Calendar.DATE, 30); //暂时30天
				Timestamp tokentime = new Timestamp(nowTime.getTime().getTime());
				map.put("logintoken",token);
				map.put("tokentime",tokentime);
				bean.setToken(token);
				bean.setTokentime(tokentime);
				int res = userMapper.updateToken(bean);
				ServletUtil.sendJsonBeanNull(response, new MsgBean(map));
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ServletUtil.sendJsonBeanNull(response, new MsgBean("-1","非法请求"));
		}
	}
	
	
	/**
	 * 轮播图
	 * @param request
	 * @param response
	 */
	@RequestMapping("mposlink.html")
	public void mposlink(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("imageRootPath", Config.getValue("imageRootPath"));
			map.put("poslinklist", ApplicationContextUtil.getMapper(PoslinkMapper.class).jsonlist());
			ServletUtil.sendJsonBeanNull(response, new MsgBean(map));
		} catch (Exception e) {
			ServletUtil.sendJsonBeanNull(response, new MsgBean("-1","非法请求"));
		}
	}
	/**
	 * 获取用户的信息
	 */
	@RequestMapping("getuser.htm")
	public void getuser(HttpServletRequest request, HttpServletResponse response){
		String token= request.getParameter("token");
//		System.out.println("-----token------------------------"+token);
		if(token!=null && !"".equals(token)){//先调取存入数据
			 String a="4423" ;
			 String m="getdetectinfo";//拉取数据接口
			 String t=System.currentTimeMillis()/1000+"";
			 String e="600";//
			 String secret = "6600b8aa3e311e2a6ce17a5d8d8fe47b";
			 String resultKey="26c8d82cfdce7bc8b9bff1671023a7bf";//公钥
			 String plainText = "a="+a+"&m="+m+"&t="+t+"&e="+e;
			 //编码
			 String signature="";
			 try {
				 signature=com.pg.wxsign.Util.getSignature(secret, plainText);
			 } catch (Exception e1) {
					e1.printStackTrace();
			 }
//			System.out.println("signature:"+signature);
//			String url = "https://iauth-dev.wecity.qq.com/new/cgi-bin/getdetectinfo.php";//不验证签名
			String url = "https://iauth.wecity.qq.com/new/cgi-bin/getdetectinfo.php";//正式
	        Part[] parts = {  
	                new StringPart("token",token),  
	                new StringPart("crypt_type","3"),  
	                new StringPart("info_type","1"),  
	                new StringPart("appid", a),
	         }; 
			String respStr = com.pg.util.HttpRequest.post(url, parts,signature);
//			System.out.println(respStr);
			JSONObject jarr=JSONObject.parseObject(respStr);
			if("0".equals(jarr.get("errorcode").toString())){
//				System.out.println(jarr.get("data"));
				UserSearchBean bean = new UserSearchBean();
				String data =AES.getdecryptData(jarr.get("data").toString(),resultKey);//密文需要先BASE64解码后再解密
				JSONObject datajarr=JSONObject.parseObject(data);
//				System.out.println(datajarr);
				bean.setSign_id(datajarr.getString("ID"));
				bean.setSign_name(datajarr.getString("name"));
				bean.setSign_phone(datajarr.getString("phone"));
				bean.setSign_ID_address(datajarr.getString("ID_address"));
				bean.setSign_sex(datajarr.getString("sex"));
				bean.setSign_nation(datajarr.getString("nation"));
				bean.setSign_ID_birth(datajarr.getString("ID_birth"));
				bean.setSign_ID_authority(datajarr.getString("ID_authority"));
				bean.setSign_ID_valid_date(datajarr.getString("ID_valid_date"));
				bean.setSign_validatedata(datajarr.getString("validatedata"));
				bean.setSign_yt_errorcode(datajarr.getString("yt_errorcode"));
				bean.setSign_yt_errormsg(datajarr.getString("yt_errormsg"));
				bean.setSign_livestatus(datajarr.getString("livestatus"));
				bean.setSign_livemsg(datajarr.getString("livemsg"));
				bean.setSign_comparestatus(datajarr.getString("comparestatus"));
				bean.setSign_comparemsg(datajarr.getString("comparemsg"));
				bean.setSign_type(datajarr.getString("type"));
				bean.setSign_status("1");//已实名
				UserMapper mapper = ApplicationContextUtil.getMapper(UserMapper.class);
				bean.setId(PgFilter.getUser(request).getId());
				mapper.updateSign(bean);
			}else{
//				System.out.println(jarr.get("errormsg"));
			}
		}
		UserSearchBean bean = new UserSearchBean();
		bean.setId(PgFilter.getUser(request).getId());
		UserMapper mapper = ApplicationContextUtil.getMapper(UserMapper.class);
		bean = mapper.getById(bean);
		if(bean.getSign_phone()!=null){
			bean.setSign_phone( bean.getSign_phone().replaceAll("(\\d{3})\\d{4}(\\d{1})", "$1****$2")  );
		}
		if(bean.getSign_id()!=null){
			bean.setSign_id( bean.getSign_id().replaceAll("(\\d{2})\\d{12}(\\d{1})", "$1****$2")  );
		}    
	        
		ServletUtil.sendJsonBean(response, new MsgBean(bean));
	}
	public static void main(String[] args) {
		 String a="4423" ;
		 String m="getdetectinfo";
		 String t=System.currentTimeMillis()/1000+"";
		 String e="600";//		 String Expired="String";
		 String secret = "6600b8aa3e311e2a6ce17a5d8d8fe47b";
		 String plainText = "a="+a+"&m="+m+"&t="+t+"&e="+e;
		 //编码
		 String signature="";
		 try {
			 signature=com.pg.wxsign.Util.getSignature(secret, plainText);
		 } catch (Exception e1) {
				e1.printStackTrace();
		 }
		 //编码
		System.out.println("signature:"+signature);
		String url = "https://iauth.wecity.qq.com/new/cgi-bin/getdetectinfo.php";//正式
//		String url = "https://iauth-dev.wecity.qq.com/new/cgi-bin/getdetectinfo.php";//测试
//		String url = "https://iauth-test.wecity.qq.com/new/cgi-bin/getdetectinfo.php";
        Part[] parts = {  
                new StringPart("token","{47DAC220-A748-49F2-A91E-62AEEF3EA2BE}"),  
                new StringPart("crypt_type","3"),  
                new StringPart("info_type","1"),  
                new StringPart("appid", a),
         }; 
		String respStr = com.pg.util.HttpRequest.post(url, parts,signature);
//		System.out.println(respStr);
		JSONObject jarr=JSONObject.parseObject(respStr);
		if("0".equals(jarr.get("errorcode").toString())){
//			System.out.println(jarr.get("data"));
			String data =AES.getdecryptData(jarr.get("data").toString(),"26c8d82cfdce7bc8b9bff1671023a7bf");
			JSONObject datajarr=JSONObject.parseObject(data);
//			System.out.println(datajarr);
//			System.out.println(datajarr.get("ID"));
			
			
			
			
		}else{
//			System.out.println(jarr.get("errormsg"));
		}
		
		
		
		
	}
	
	
	
	@RequestMapping("yaoyiyao.htm")
	public void yaoyiyao(HttpServletRequest request, HttpServletResponse response) {
		String formId=request.getParameter("formId");
//		System.out.println("formId:--------------------------"+formId);
//		System.out.println("getUser:--------------------------"+PgFilter.getUser(request).getId());
		
		Map<String, Object> map=new HashMap<>();
		map.put("touser","oZw_y0NDhcQtybGyLgE8yILCWuc0");
		map.put("template_id","iMfWq9MOoLOaDoXbyGNxvBwBqpFqf6pNSVbxLOhQw-A");
		map.put("page","pages/index/index");
		map.put("form_id",formId);
		
		Map<String, HashMap<String, String>>  data = new  HashMap<>();
		
		HashMap<String, String> d=new HashMap<>();
		d.put("value","查询内容");
		d.put("color","#0A0A0A");
		data.put("keyword1",d);
		
		d=new HashMap<>();
		d.put("value","查询结果");
		d.put("color","#0A0A0A");
		data.put("keyword2",d);
		
		d=new HashMap<>();
		d.put("value","查询状态");
		d.put("color","#0A0A0A");
		data.put("keyword3",d);
		map.put("data", data);
		
		d=new HashMap<>();
		d.put("value","查询时间");
		d.put("color","#0A0A0A");
		data.put("keyword4",d);
		map.put("data", data);
		
//		System.out.println(new Gson().toJson(map));
//		send(new Gson().toJson(map));
		
		
		ServletUtil.sendJsonBeanNull(response, new MsgBean("0","000000000000000"));
	}
	
	@RequestMapping("sentemplate.htm")
	public void sentemplate(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map=new HashMap<>();
		map.put("touser","oZw_y0NDhcQtybGyLgE8yILCWuc0");
		map.put("template_id","iMfWq9MOoLOaDoXbyGNxvBwBqpFqf6pNSVbxLOhQw-A");
		Config.configMap=null;
		map.put("form_id",Config.getValue("form_id"));
		
		Map<String, HashMap<String, String>>  data = new  HashMap<>();
		
		HashMap<String, String> d=new HashMap<>();
		d.put("value","查询内容");
		d.put("color","#0A0A0A");
		data.put("keyword1",d);
		
		d=new HashMap<>();
		d.put("value","查询结果");
		d.put("color","#0A0A0A");
		data.put("keyword2",d);
		
		d=new HashMap<>();
		d.put("value","查询状态");
		d.put("color","#0A0A0A");
		data.put("keyword3",d);
		map.put("data", data);
		
		d=new HashMap<>();
		d.put("value","查询时间");
		d.put("color","#0A0A0A");
		data.put("keyword4",d);
		map.put("data", data);
		
		System.out.println(new Gson().toJson(map));
		send(new Gson().toJson(map));
	}
	public static BaseResult send(String json) {
		String key=GZHSetup.register("wxe235b7f508b37e51", "3bd3e0a09bfe895239b380728123d19f");
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+GZHSetup.getToken(key);
		String result=HttpRequest.post(url,json);
		return new Gson().fromJson(result,BaseResult.class);
	}
	
	
	@RequestMapping("myaoyiyao.html")
	public String myaoyiyao(HttpServletRequest request, HttpServletResponse response) {
		
		return "mobile/myaoyiyao.jsp";
	}
	
	
}