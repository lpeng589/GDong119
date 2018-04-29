package com.pg.bean;
/**
 * 模块属性bean
 * @author Administrator
 *
 */
public class ModuleAttrBean extends BaseBean{
	private Integer id;
	private Integer moduleid;
	private String modulename;
	private String attribute;
	private String paramname;
	private String paramdesc;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getModuleid() {
		return moduleid;
	}
	public void setModuleid(Integer moduleid) {
		this.moduleid = moduleid;
	}
	public String getModulename() {
		return modulename;
	}
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getParamname() {
		return paramname;
	}
	public void setParamname(String paramname) {
		this.paramname = paramname;
	}
	public String getParamdesc() {
		return paramdesc;
	}
	public void setParamdesc(String paramdesc) {
		this.paramdesc = paramdesc;
	}
}
