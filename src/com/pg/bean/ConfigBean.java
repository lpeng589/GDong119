package com.pg.bean;
public class ConfigBean  extends BaseBean{
	private Integer id;
	/**
	 * 配置key
	 */
	private String key;
	/**
	 * 配置value
	 */
	private String value;

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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}