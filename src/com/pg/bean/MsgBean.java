package com.pg.bean;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * ajax返回信息bean
 * @author Administrator
 *
 */
public class MsgBean{
	/**
	 * 错误代码 0 正确  -1业务上错误  -1000 未登录,-1001无权限
	 */
	private String code;
	/**
	 * 错误描述
	 */
	private String msg;
	/**
	 * 数据
	 */
	private Object data;
	/**
	 * 返回结果
	 * @param errcode
	 * @param errmsg
	 */
	public MsgBean(String code,String msg){
		this.setCode(code);
		this.setMsg(msg);
	}
	/**
	 * 返回错误结果
	 * @param errcode
	 * @param errmsg
	 */
	public MsgBean(String code,String msg,Object data){
		this.setCode(code);
		this.setMsg(msg);
		this.setData(data);
	}
	public MsgBean(Object data){
		this.setCode("0");
		this.setMsg("成功");
		this.setData(data);
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
//		if(code.equals("-1")){
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //代码执行事务回滚
//		}
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}