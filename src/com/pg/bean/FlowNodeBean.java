package com.pg.bean;
import java.sql.Timestamp;
public class FlowNodeBean extends BaseBean{
	private Integer id;
	/**
	 * 对应工作流id
	 */
	private Integer flowid;
	/**
	 * 上一节点id：首节点为-1
	 */
	private Integer nodepre;
	/**
	 * 现在节点状态
	 */
	private Integer statusnow;
	/**
	 * 现在节点描述
	 */
	private String descriptionnow;
	/**
	 * 下一节点id，结束节点为0
	 */
	private Integer nodenext;
	/**
	 * 角色id 多个用逗号隔开
	 */
	private String roleid;
	/**
	 * 职员id 多个用逗号隔开
	 */
	private String employeeid;
	/**
	 * 0系统节点 1用户自定义节点
	 */
	private Integer issys;
	/**
	 * 节点简介
	 */
	private String remark;
	/**
	 * XXX对应表名(去掉头部)可以对应XXXMapper
	 */
	private String tablename;
	/**
	 * 资料更新时间
	 */
	private Timestamp updatetime;
	/**
	 * 创建时间
	 */
	private Timestamp createtime;
	/**
	 * 记录的属性模板#{},用于记录原值和修改后值
	 */
	private String valuetemplate;
	/**
	 * 审核点击后的链接。模板#{}
	 */
	private String linkurl;
	/**
	 * 是否需要审核：0否，1是
	 */
	private Integer isaudit;
	/**
	 * 该状态下操作的方法、null或空的时候，调用公用方法
	 */
	private String method;
	/**
	 * 是否需要发站内消息：0否，1是
	 */
	private Integer ismessage;
	/**
	 * 是否需要写日志：0否，1是
	 */
	private Integer isflowlog;
	
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
	*设置对应工作流id
	*/
	public void setFlowid(Integer flowid){
		this.flowid = flowid;
	}
	/**
	*获取对应工作流id
	*/
	public Integer getFlowid(){
		return flowid;
	}
	/**
	*设置上一节点id：首节点为-1
	*/
	public void setNodepre(Integer nodepre){
		this.nodepre = nodepre;
	}
	/**
	*获取上一节点id：首节点为-1
	*/
	public Integer getNodepre(){
		return nodepre;
	}
	/**
	*设置现在节点状态
	*/
	public void setStatusnow(Integer statusnow){
		this.statusnow = statusnow;
	}
	/**
	*获取现在节点状态
	*/
	public Integer getStatusnow(){
		return statusnow;
	}
	/**
	*设置现在节点描述
	*/
	public void setDescriptionnow(String descriptionnow){
		this.descriptionnow = descriptionnow;
	}
	/**
	*获取现在节点描述
	*/
	public String getDescriptionnow(){
		return descriptionnow;
	}
	/**
	*设置下一节点id，结束节点为0
	*/
	public void setNodenext(Integer nodenext){
		this.nodenext = nodenext;
	}
	/**
	*获取下一节点id，结束节点为0
	*/
	public Integer getNodenext(){
		return nodenext;
	}
	/**
	*设置角色id 多个用逗号隔开
	*/
	public void setRoleid(String roleid){
		this.roleid = roleid;
	}
	/**
	*获取角色id 多个用逗号隔开
	*/
	public String getRoleid(){
		return roleid;
	}
	/**
	*设置职员id 多个用逗号隔开
	*/
	public void setEmployeeid(String employeeid){
		this.employeeid = employeeid;
	}
	/**
	*获取职员id 多个用逗号隔开
	*/
	public String getEmployeeid(){
		return employeeid;
	}
	/**
	*设置0系统节点 1用户自定义节点
	*/
	public void setIssys(Integer issys){
		this.issys = issys;
	}
	/**
	*获取0系统节点 1用户自定义节点
	*/
	public Integer getIssys(){
		return issys;
	}
	/**
	*设置节点简介
	*/
	public void setRemark(String remark){
		this.remark = remark;
	}
	/**
	*获取节点简介
	*/
	public String getRemark(){
		return remark;
	}
	/**
	*设置XXX对应表名(去掉头部)可以对应XXXMapper
	*/
	public void setTablename(String tablename){
		this.tablename = tablename;
	}
	/**
	*获取XXX对应表名(去掉头部)可以对应XXXMapper
	*/
	public String getTablename(){
		return tablename;
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
	*设置记录的属性模板#{},用于记录原值和修改后值
	*/
	public void setValuetemplate(String valuetemplate){
		this.valuetemplate = valuetemplate;
	}
	/**
	*获取记录的属性模板#{},用于记录原值和修改后值
	*/
	public String getValuetemplate(){
		return valuetemplate;
	}
	/**
	*设置审核点击后的链接。模板#{}
	*/
	public void setLinkurl(String linkurl){
		this.linkurl = linkurl;
	}
	/**
	*获取审核点击后的链接。模板#{}
	*/
	public String getLinkurl(){
		return linkurl;
	}
	/**
	*设置是否需要审核：0否，1是
	*/
	public void setIsaudit(Integer isaudit){
		this.isaudit = isaudit;
	}
	/**
	*获取是否需要审核：0否，1是
	*/
	public Integer getIsaudit(){
		return isaudit;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Integer getIsmessage() {
		return ismessage;
	}
	public void setIsmessage(Integer ismessage) {
		this.ismessage = ismessage;
	}
	public Integer getIsflowlog() {
		return isflowlog;
	}
	public void setIsflowlog(Integer isflowlog) {
		this.isflowlog = isflowlog;
	}
	private String methodafter;

	public String getMethodafter() {
		return methodafter;
	}
	public void setMethodafter(String methodafter) {
		this.methodafter = methodafter;
	}
}