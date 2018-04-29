package com.pg.bean;
import java.sql.Timestamp;
public class QuartzBean extends BaseBean{
	private Integer id;
	/**
	 * 任务名
	 */
	private String name;
	/**
	 * 任务名
	 */
	private String jobname;
	/**
	 * 任务组
	 */
	private String jobgroup;
	/**
	 * 任务执行类
	 */
	private String operatetemplate;
	/**
	 * 触发器名
	 */
	private String triggername;
	/**
	 * 触发器组
	 */
	private String triggergroup;
	/**
	 * 触发时间
	 */
	private String triggertime;
	/**
	 * 0系统定义 1用户自定义
	 */
	private Integer issys;
	/**
	 * 资料更新时间
	 */
	private Timestamp updatetime;
	/**
	 * 创建时间
	 */
	private Timestamp createtime;
	/**
	 * 职员id
	 */
	private Integer employeeid;
	/**
	 * 定时任务描述
	 */
	private String description;
	/**
	 * 是否启动：0未启动，1启动
	 */
	private Integer status;
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
	*设置任务名
	*/
	public void setJobname(String jobname){
		this.jobname = jobname;
	}
	/**
	*获取任务名
	*/
	public String getJobname(){
		return jobname;
	}
	/**
	*设置任务组
	*/
	public void setJobgroup(String jobgroup){
		this.jobgroup = jobgroup;
	}
	/**
	*获取任务组
	*/
	public String getJobgroup(){
		return jobgroup;
	}
	/**
	*设置任务执行类
	*/
	public void setOperatetemplate(String operatetemplate){
		this.operatetemplate = operatetemplate;
	}
	/**
	*获取任务执行类
	*/
	public String getOperatetemplate(){
		return operatetemplate;
	}
	/**
	*设置触发器名
	*/
	public void setTriggername(String triggername){
		this.triggername = triggername;
	}
	/**
	*获取触发器名
	*/
	public String getTriggername(){
		return triggername;
	}
	/**
	*设置触发器组
	*/
	public void setTriggergroup(String triggergroup){
		this.triggergroup = triggergroup;
	}
	/**
	*获取触发器组
	*/
	public String getTriggergroup(){
		return triggergroup;
	}
	/**
	*设置触发时间
	*/
	public void setTriggertime(String triggertime){
		this.triggertime = triggertime;
	}
	/**
	*获取触发时间
	*/
	public String getTriggertime(){
		return triggertime;
	}
	/**
	*设置0系统定义 1用户自定义
	*/
	public void setIssys(Integer issys){
		this.issys = issys;
	}
	/**
	*获取0系统定义 1用户自定义
	*/
	public Integer getIssys(){
		return issys;
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
	*设置职员id
	*/
	public void setEmployeeid(Integer employeeid){
		this.employeeid = employeeid;
	}
	/**
	*获取职员id
	*/
	public Integer getEmployeeid(){
		return employeeid;
	}
	/**
	*设置定时任务描述
	*/
	public void setDescription(String description){
		this.description = description;
	}
	/**
	*获取定时任务描述
	*/
	public String getDescription(){
		return description;
	}
	/**
	*设置是否启动：0未启动，1启动
	*/
	public void setStatus(Integer status){
		this.status = status;
	}
	/**
	*获取是否启动：0未启动，1启动
	*/
	public Integer getStatus(){
		return status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}