package com.pg.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 一级菜单bean
 * @author dzp
 *
 */
public class MenuBean extends BaseBean{
	
	private Integer id;
	private String name;
	private List<ModuleBean> submenu=new ArrayList<>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ModuleBean> getSubmenu() {
		return submenu;
	}
	public void setSubmenu(List<ModuleBean> submenu) {
		this.submenu = submenu;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
