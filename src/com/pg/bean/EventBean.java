package com.pg.bean;
import java.sql.Timestamp;
public class EventBean extends BaseBean{
	private String formId;
	private String jubao;
	private String replypre;  //消防员反馈内容
	private Integer deptuserid;
	private Integer id;
	/**
	 * 事件类型1、不文明曝光台2、安全举报有奖3、欠薪投诉4、困难求助5、建言献策6、环境保护随手拍7、工程投诉8、市容随手拍9、系统使用建议
	 */
	private Integer type;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 诉求内容
	 */
	private String content;
	/**
	 * 图片1
	 */
	private String pic1;
	/**
	 * 图片2
	 */
	private String pic2;
	/**
	 * 图片3
	 */
	private String pic3;
	/**
	 * 视频
	 */
	private String video;
	/**
	 * 创建时间（数据库自动操作，不必写入）
	 */
	private Timestamp insertTime;
	/**
	 * 处理时间
	 */
	private Timestamp updateTime;
	/**
	 * 处理人
	 */
	private Integer employid;
	/**
	 * 处理回复
	 */
	private String reply;
	/**
	 * 处理评分1到5
	 */
	private Integer level;
	/**
	 * 处理评价
	 */
	private String appraise;
	private Integer userid;
	private String voice;
	private String address;
	private String Latitude;
	private String Longitude;
	
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
	*设置事件类型1、不文明曝光台2、安全举报有奖3、欠薪投诉4、困难求助5、建言献策6、环境保护随手拍7、工程投诉8、市容随手拍9、系统使用建议
	*/
	public void setType(Integer type){
		this.type = type;
	}
	/**
	*获取事件类型1、不文明曝光台2、安全举报有奖3、欠薪投诉4、困难求助5、建言献策6、环境保护随手拍7、工程投诉8、市容随手拍9、系统使用建议
	*/
	public Integer getType(){
		return type;
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
	*设置手机号码
	*/
	public void setPhone(String phone){
		this.phone = phone;
	}
	/**
	*获取手机号码
	*/
	public String getPhone(){
		return phone;
	}
	/**
	*设置诉求内容
	*/
	public void setContent(String content){
		this.content = content;
	}
	/**
	*获取诉求内容
	*/
	public String getContent(){
		return content;
	}
	/**
	*设置图片1
	*/
	public void setPic1(String pic1){
		this.pic1 = pic1;
	}
	/**
	*获取图片1
	*/
	public String getPic1(){
		return pic1;
	}
	/**
	*设置图片2
	*/
	public void setPic2(String pic2){
		this.pic2 = pic2;
	}
	/**
	*获取图片2
	*/
	public String getPic2(){
		return pic2;
	}
	/**
	*设置图片3
	*/
	public void setPic3(String pic3){
		this.pic3 = pic3;
	}
	/**
	*获取图片3
	*/
	public String getPic3(){
		return pic3;
	}
	/**
	*设置视频
	*/
	public void setVideo(String video){
		this.video = video;
	}
	/**
	*获取视频
	*/
	public String getVideo(){
		return video;
	}
	/**
	*设置创建时间（数据库自动操作，不必写入）
	*/
	public void setInsertTime(Timestamp insertTime){
		this.insertTime = insertTime;
	}
	/**
	*获取创建时间（数据库自动操作，不必写入）
	*/
	public Timestamp getInsertTime(){
		return insertTime;
	}
	/**
	*设置处理时间
	*/
	public void setUpdateTime(Timestamp updateTime){
		this.updateTime = updateTime;
	}
	/**
	*获取处理时间
	*/
	public Timestamp getUpdateTime(){
		return updateTime;
	}
	/**
	*设置处理人
	*/
	public void setEmployid(Integer employid){
		this.employid = employid;
	}
	/**
	*获取处理人
	*/
	public Integer getEmployid(){
		return employid;
	}
	/**
	*设置处理回复
	*/
	public void setReply(String reply){
		this.reply = reply;
	}
	/**
	*获取处理回复
	*/
	public String getReply(){
		return reply;
	}
	/**
	*设置处理评分1到5
	*/
	public void setLevel(Integer level){
		this.level = level;
	}
	/**
	*获取处理评分1到5
	*/
	public Integer getLevel(){
		return level;
	}
	/**
	*设置处理评价
	*/
	public void setAppraise(String appraise){
		this.appraise = appraise;
	}
	/**
	*获取处理评价
	*/
	public String getAppraise(){
		return appraise;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getVoice() {
		return voice;
	}
	public void setVoice(String voice) {
		this.voice = voice;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getReplypre() {
		return replypre;
	}
	public void setReplypre(String replypre) {
		this.replypre = replypre;
	}
	public Integer getDeptuserid() {
		return deptuserid;
	}
	public void setDeptuserid(Integer deptuserid) {
		this.deptuserid = deptuserid;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getJubao() {
		return jubao;
	}
	public void setJubao(String jubao) {
		this.jubao = jubao;
	}
}