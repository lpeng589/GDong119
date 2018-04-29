package com.pg.bean;
public class WorkBean extends BaseBean{
	private Integer id;
	/**
	 * 用来标识模块（唯一）
	 */
	private String idstr;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 排序用，如果小于1则在权限设置上不显示。
	 */
	private Integer orderid;
	/**
	 * 跳转链接
	 */
	private String menuurl;
	/**
	 * 对应表名
	 */
	private String tablename;
	/**
	*设置
	*/
	public void setId(Integer id){
		this.id = id;
	}
	/**
	*获取
	*/
	public Integer getId(){
		return id;
	}
	/**
	*设置用来标识模块（唯一）
	*/
	public void setIdstr(String idstr){
		this.idstr = idstr;
	}
	/**
	*获取用来标识模块（唯一）
	*/
	public String getIdstr(){
		return idstr;
	}
	/**
	*设置名称
	*/
	public void setName(String name){
		this.name = name;
	}
	/**
	*获取名称
	*/
	public String getName(){
		return name;
	}
	/**
	*设置排序用，如果小于1则在权限设置上不显示。
	*/
	public void setOrderid(Integer orderid){
		this.orderid = orderid;
	}
	/**
	*获取排序用，如果小于1则在权限设置上不显示。
	*/
	public Integer getOrderid(){
		return orderid;
	}
	/**
	*设置跳转链接
	*/
	public void setMenuurl(String menuurl){
		this.menuurl = menuurl;
	}
	/**
	*获取跳转链接
	*/
	public String getMenuurl(){
		return menuurl;
	}
	/**
	*设置对应表名
	*/
	public void setTablename(String tablename){
		this.tablename = tablename;
	}
	/**
	*获取对应表名
	*/
	public String getTablename(){
		return tablename;
	}
}