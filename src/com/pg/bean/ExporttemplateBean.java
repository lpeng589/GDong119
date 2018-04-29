package com.pg.bean;

/**
 * 导出模板bean
 * @author Administrator
 *
 */
public  class ExporttemplateBean extends BaseBean{
	private Integer id;
	private String key;
	private String desc;
	private String heads;
	private String fields;
	protected String maps;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getHeads() {
		return heads;
	}
	public void setHeads(String heads) {
		this.heads = heads;
	}
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	public String getMaps() {
		return maps;
	}
	public void setMaps(String maps) {
		this.maps = maps;
	}
}