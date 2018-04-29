package com.pg.util;

import java.util.HashMap;
import java.util.List;

import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.pg.bean.ConfigBean;
import com.pg.gzh.util.GZHSetup;
import com.pg.gzhqy.util.GZHQySetup;
import com.pg.mapper.ConfigMapper;

/**
 * 系统配置bean
 * @author Administrator
 *
 */
public class Config{
	/**
	 * 配置参数集
	 */
	public static HashMap<String, String> configMap ;
	/**
	 * 平台公用百度短信smsid
	 */
	public final static String CONFIG_SMSID = "smsid";
	/**
	 * 平台公用百度短信smssecret
	 */
	public final static String CONFIG_SMSSECRET = "smssecret";
	/**
	 * 平台公用百度短信smsvalid
	 */
	public final static String CONFIG_SMSVALID = "smsvalid";
	/**
	 * 平台公用微信appid
	 */
	public final static String CONFIG_WXAPPID = "wxappid";
	/**
	 * 小程序APPID
	 */
	public final static String CONFIG_WXCXAPPID = "wxcxappid";
	/**
	 * 平台公用微信secret
	 */
	public final static String CONFIG_WXSECRET = "wxsecret";
	/**
	 * 小程序APPID secret
	 */
	public final static String CONFIG_WXCXSECRET = "wxcxsecret";
	
	public final static String CONFIG_QYWXCORPID = "qywxcorpID";
	/**
	 * 平台OssPublicPictureUrlPrefix
	 */
	public final static String CONFIG_OSSPREFIX = "ossPrefix";
	/**
	 * 平台ossaccessid
	 */
	public final static String CONFIG_OSSACCESSID = "ossAccessID";
	/**
	 * 平台ossaccesskey
	 */
	public final static String CONFIG_OSSACCESSKEY = "ossAccessKey";
	/**
	 * 平台ossendpoint
	 */
	public final static String CONFIG_OSSENDPOINT = "ossEndPoint";
	/**
	 * 平台bucketName
	 */
	public final static String CONFIG_OSSBUCKETNAME = "ossBucketName";
	
	/**
	 * 平台ossStatus
	 */
	public final static String CONFIG_OSSSTATUS = "ossStatus";
	/**
	 * 平台uploadpath
	 */
	public final static String CONFIG_UPLOADPATH = "uploadpath";
	/**
	 * 平台imageRootPath
	 */
	public final static String CONFIG_IMAGEROOTPATH = "imageRootPath";
	/**
	 * 是否锁库存1：锁 0 不锁
	 */
	public final static String IS_LOCK = "islock";
	
	/**
	 * 平台公用微信key
	 */
	private static String configWxKey ;
	
	/**
	 * 平台公用小程序key
	 */
	private static String configWxcxKey ;
	
	/**
	 * 企业微信应用key
	 */
	public static HashMap<String, String> WXQyKeyMap ;
	/**
	 * 返回公众号key
	 * @return
	 */
	public static String getWxKey() {
		return getConfigWxKey();
	}
	/**
	 * 返回小程序key
	 * @return
	 */
	public static String getWxcxKey() {
		return getConfigWxcxKey();
	}
	/**
	 * 根据key获取配置值
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		if(configMap==null){
			configMap=new HashMap<>();
			List<ConfigBean> list = ApplicationContextUtil.getMapper(ConfigMapper.class).list();
			if(list!=null){
				for (ConfigBean configBean : list) {
					configMap.put(configBean.getKey(), configBean.getValue());
				}
			}
		}
		return configMap.get(key);
	}
	
	/**
	 * 根据secret获取key
	 * @param  企业微信应用secret
	 * @return
	 */
	public static String getWXQyKey(String secret) {
		if(WXQyKeyMap==null){
			WXQyKeyMap=new HashMap<>();
			WXQyKeyMap.put(secret, GZHQySetup.register(getValue(CONFIG_QYWXCORPID), secret));
		}
		String key = WXQyKeyMap.get(secret);
		if (key==null||"".equals(key)) {
			WXQyKeyMap.put(secret, GZHQySetup.register(getValue(CONFIG_QYWXCORPID), secret));
		}
		return WXQyKeyMap.get(secret);
	}
	
	
	/**
	 * 根据key获取配置值
	 * @param key
	 * @return
	 */
	public static void refleshValue() {
			configMap=new HashMap<>();
			List<ConfigBean> list = ApplicationContextUtil.getMapper(ConfigMapper.class).list();
			if(list!=null){
				for (ConfigBean configBean : list) {
					configMap.put(configBean.getKey(), configBean.getValue());
				}
			}
		return;
	}

	
	/**
	 * 获取平台微信key
	 * @return
	 */
	private static String getConfigWxKey() {
		if(configWxKey==null){
			configWxKey=GZHSetup.register(getValue(CONFIG_WXAPPID),getValue(CONFIG_WXSECRET));
		}
		return configWxKey;
	}
	/**
	 * 获取小程序key
	 * @return
	 */
	private static String getConfigWxcxKey() {
		if(configWxcxKey==null){
			configWxcxKey=com.pg.xcx.util.GZHSetup.register(getValue(CONFIG_WXCXAPPID),getValue(CONFIG_WXCXSECRET));
		}
		return configWxcxKey;
	}
	/**
	 * 获得 Scheduler 对象 ,用于定时任务;单例模式
	 * @return
	 */
	private static final SchedulerFactory schedulerFactory = new StdSchedulerFactory();

	public static Scheduler getInstance() {
		Scheduler scheduler = null;
		try {
			 scheduler = schedulerFactory.getScheduler();
		} catch (Exception e) {
		}
		
		return scheduler;
	}
	//登录的用户:账户，sessionid
	public static HashMap<String, String> userloginMap =  new  HashMap<String, String>();
	
	
	
	/**
	 * 企业微信key
	 */
	private static String configQyWxKey ;
	private static String configQyWxKeyFenbo ;
	/**
	 * 获取企业微信key--案件处理  
	 * @return
	 */
	public static String getQyWxKey() {
		if(configQyWxKey==null){
			String str1 = getValue("qywxcorpid");
			String str2 = getValue("qywxsecret");
			if(str1!=null&&str2!=null&&!"".equals(str1)&&!"".equals(str2)){
				configQyWxKey=GZHQySetup.register(str1,str2);
			}
		}
		return configQyWxKey;
	}
	
	/**
	 * 获取企业微信key--案件处理  
	 * @return
	 */
	public static String getQyWxKeyFenbo() {
		if(configQyWxKeyFenbo==null){
			String str1 = getValue("qywxcorpid");
			String str2 = getValue("qywxsecretFenbo");
			if(str1!=null&&str2!=null&&!"".equals(str1)&&!"".equals(str2)){
				configQyWxKeyFenbo=GZHQySetup.register(str1,str2);
			}
		}
		return configQyWxKeyFenbo;
	}
	
	/**
	 * 企业微信key
	 */
	private static String configQyWxTXZSKey ;
	
	/**
	 * 获取企业微信key  
	 * @return
	 */
	public static String getQyWxTXZSKey() {
		if(configQyWxTXZSKey==null){
			String str1 = getValue("qywxcorpid");
			String str2 = getValue("qywxTXZSsecret");
			if(str1!=null&&str2!=null&&!"".equals(str1)&&!"".equals(str2)){
				configQyWxTXZSKey=GZHQySetup.register(str1,str2);
			}
		}
		return configQyWxTXZSKey;
	}
	
	
	
}