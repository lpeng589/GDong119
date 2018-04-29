package com.pg.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pg.bean.EmployeeBean;
import com.pg.bean.MessageBean;
import com.pg.bean.MessageModelBean;
import com.pg.mapper.MessageMapper;
import com.pg.searchbean.EmployeeSearchBean;

public class Message {
	/**
	 * 消息模板集
	 */
	private static HashMap<String, MessageModelBean> messageModelMap ;
	/**
	 * 根据key值获得消息模板
	 * @param key
	 * @return
	 */
	private static MessageModelBean getMessageModel(String key) {
		if(messageModelMap==null){
			messageModelMap=new HashMap<>();
			List<MessageModelBean> list = ApplicationContextUtil.getMapper(MessageMapper.class).listModel();
			if(list!=null){
				for (MessageModelBean bean : list) {
					messageModelMap.put(bean.getKey(), bean);
				}
			}
		}
		return messageModelMap.get(key);
	}
	/**
	 * 发送消息
	 * @param key  消息模板key
	 * @param employee  消息对应职员
	 * @param obj  消息对应bean
	 * @return
	 */
	public static boolean send(String key,EmployeeSearchBean employee,Object obj) {
		List<EmployeeSearchBean> employeeList = new ArrayList<>();
		employeeList.add(employee);
		return send(key, employeeList, obj);
	}
	
	/**
	 * 发送消息
	 * @param key  消息模板key
	 * @param employeeList  消息对应职员列表
	 * @param obj  消息对应bean
	 * @return
	 */
	public static boolean send(String key,List<EmployeeSearchBean> employeeList,Object obj) {
		MessageModelBean messageModelBean = getMessageModel(key);
		if(messageModelBean==null){
			return false;
		}
		try {
			Map<String, Object> map = Tools.changeBeanToMap(obj);
			MessageBean bean = new MessageBean();
			if(messageModelBean.getTitle()!=null && messageModelBean.getTitle()!="")bean.setTitle(Tools.getMatch(map, messageModelBean.getTitle()));
			if(messageModelBean.getContent()!=null && messageModelBean.getContent()!="")bean.setContent(Tools.getMatch(map, messageModelBean.getContent()));
			if(messageModelBean.getLink()!=null && messageModelBean.getLink()!="")bean.setLink(Tools.getMatch(map, messageModelBean.getLink()));
			MessageMapper mapper = ApplicationContextUtil.getMapper(MessageMapper.class);
			for (EmployeeBean employeeBean : employeeList) {
				bean.setEmployeeid(employeeBean.getId());
				mapper.add(bean);
				ManageCache.reflashMessage(employeeBean.getId());//刷新他的消息
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 发送消息
	 * @param key  消息模板key
	 * @param 消息对应职员
	 * @param obj  消息对应bean
	 * @return
	 */
	public static boolean send(String key,Integer employid,Map<String, Object> map) {
		MessageModelBean messageModelBean = getMessageModel(key);
		if(messageModelBean==null){
			return false;
		}
		try {
			MessageBean bean = new MessageBean();
			if(messageModelBean.getTitle()!=null && messageModelBean.getTitle()!="")bean.setTitle(Tools.getMatch(map, messageModelBean.getTitle()));
			if(messageModelBean.getContent()!=null && messageModelBean.getContent()!="")bean.setContent(Tools.getMatch(map, messageModelBean.getContent()));
			if(messageModelBean.getLink()!=null && messageModelBean.getLink()!="")bean.setLink(Tools.getMatch(map, messageModelBean.getLink()));
			MessageMapper mapper = ApplicationContextUtil.getMapper(MessageMapper.class);
			bean.setEmployeeid(employid);
			mapper.add(bean);
			ManageCache.reflashMessage(employid);//刷新他的消息
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * 发送消息
	 * @param key  消息模板key
	 * @param employeeList  消息对应职员列表
	 * @param obj  消息对应bean
	 * @return
	 */
	public static boolean send(String key,List<EmployeeSearchBean> employeeList,Map<String, Object> map) {
		MessageModelBean messageModelBean = getMessageModel(key);
//		System.out.println(messageModelBean.getContent());
		if(messageModelBean==null){
			return false;
		}
		try {
//			System.out.println("map:"+map);
//			System.out.println("employeeList:"+employeeList.size());
			MessageBean bean = new MessageBean();
			if(messageModelBean.getTitle()!=null && messageModelBean.getTitle()!="")bean.setTitle(Tools.getMatch(map, messageModelBean.getTitle()));
			if(messageModelBean.getContent()!=null && messageModelBean.getContent()!="")bean.setContent(Tools.getMatch(map, messageModelBean.getContent()));
			if(messageModelBean.getLink()!=null && messageModelBean.getLink()!="")bean.setLink(Tools.getMatch(map, messageModelBean.getLink()));
			MessageMapper mapper = ApplicationContextUtil.getMapper(MessageMapper.class);
			for (EmployeeBean employeeBean : employeeList) {
				bean.setEmployeeid(employeeBean.getId());
				mapper.add(bean);
				ManageCache.reflashMessage(employeeBean.getId());//刷新他的消息
			}
		} catch (Exception e) {
//			e.printStackTrace();
			return false;
		}
		return true;
	}

}
