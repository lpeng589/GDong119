package com.pg.bean;

import java.util.List;

/**
 * 查询列表结果集
 * @author Administrator
 *
 */
public class ResultListBean{
	/**
	 * 条件
	 */
	private Object cond;
	/**
	 * 结果集
	 */
	private List<Object> list;
	/**
	 * 结果数量
	 */
	private Integer count;
	/**
	 * 分页
	 */
	private Integer pages;
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Object getCond() {
		return cond;
	}
	public void setCond(Object cond) {
		this.cond = cond;
	}
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
}