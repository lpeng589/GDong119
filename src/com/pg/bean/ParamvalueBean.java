package com.pg.bean;
import java.sql.Timestamp;
public class ParamvalueBean  extends BaseBean{
	private Integer id;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 值
	 */
	private String value;
	/**
	 * 是否启用：1-已启用，0-已注销
	 */
	private Integer status;
	/**
	 * 资料更新时间
	 */
	private Timestamp updatetime;
	/**
	 * 创建时间
	 */
	private Timestamp createtime;
	/**
	 * 创建人
	 */
	private Integer employid;
	/**
	 * 备注
	 */
	private String payremarks;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 名称
	 */
	private String name2;
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
	*设置类型
	*/
	public void setType(String type){
		this.type = type;
	}
	/**
	*获取类型
	*/
	public String getType(){
		return type;
	}
	/**
	*设置值
	*/
	public void setValue(String value){
		this.value = value;
	}
	/**
	*获取值
	*/
	public String getValue(){
		return value;
	}
	/**
	*设置是否启用：1-已启用，0-已注销
	*/
	public void setStatus(Integer status){
		this.status = status;
	}
	/**
	*获取是否启用：1-已启用，0-已注销
	*/
	public Integer getStatus(){
		return status;
	}
	/**
	*设置资料更新时间
	*/
	public void setUpdatetime(Timestamp updatetime){
		this.updatetime = updatetime;
	}
	/**
	*获取资料更新时间
	*/
	public Timestamp getUpdatetime(){
		return updatetime;
	}
	/**
	*设置创建时间
	*/
	public void setCreatetime(Timestamp createtime){
		this.createtime = createtime;
	}
	/**
	*获取创建时间
	*/
	public Timestamp getCreatetime(){
		return createtime;
	}
	/**
	*设置创建人
	*/
	public void setEmployid(Integer employid){
		this.employid = employid;
	}
	/**
	*获取创建人
	*/
	public Integer getEmployid(){
		return employid;
	}
	/**
	*设置备注
	*/
	public void setPayremarks(String payremarks){
		this.payremarks = payremarks;
	}
	/**
	*获取备注
	*/
	public String getPayremarks(){
		return payremarks;
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
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	
}