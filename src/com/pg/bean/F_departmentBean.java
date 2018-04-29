package com.pg.bean;
import java.sql.Timestamp;
public class F_departmentBean extends BaseBean{
	private Integer id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 创建时间（数据库自动操作，不必写入）
	 */
	private Timestamp createtime;
	/**
	 * 更新时间（数据库自动操作，不必写入）
	 */
	private Timestamp updateTime;
	/**
	 * 默认-1，其他值存在上级
	 */
	private Integer parentid;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 经度
	 */
	private String longitude;
	/**
	 * 纬度
	 */
	private String latitude;
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
	*设置创建时间（数据库自动操作，不必写入）
	*/
	public void setCreatetime(Timestamp createtime){
		this.createtime = createtime;
	}
	/**
	*获取创建时间（数据库自动操作，不必写入）
	*/
	public Timestamp getCreatetime(){
		return createtime;
	}
	/**
	*设置更新时间（数据库自动操作，不必写入）
	*/
	public void setUpdateTime(Timestamp updateTime){
		this.updateTime = updateTime;
	}
	/**
	*获取更新时间（数据库自动操作，不必写入）
	*/
	public Timestamp getUpdateTime(){
		return updateTime;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	private Integer type;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
}