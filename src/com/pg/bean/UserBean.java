package com.pg.bean;
import java.sql.Timestamp;
public class UserBean extends BaseBean{
	private Integer id;
	/**
	 * 会员状态：1-正常，0-冻结
	 */
	private Integer status;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 头像URL(微信登录拿到后存自己OSS的url)
	 */
	private String headimgurl;
	/**
	 * 创建时间（数据库自动操作，不必写入）
	 */
	private Timestamp insertTime;
	/**
	 * 微信openId
	 */
	private String openid;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 性别:男1女2 未知0
	 */
	private String sex;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 国家
	 */
	private String country;
	private String wxopenid;
	private String name;
	private String phone;
	
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
	*设置会员状态：1-正常，0-冻结
	*/
	public void setStatus(Integer status){
		this.status = status;
	}
	/**
	*获取会员状态：1-正常，0-冻结
	*/
	public Integer getStatus(){
		return status;
	}
	/**
	*设置昵称
	*/
	public void setNickname(String nickname){
		this.nickname = nickname;
	}
	/**
	*获取昵称
	*/
	public String getNickname(){
		return nickname;
	}
	/**
	*设置头像URL(微信登录拿到后存自己OSS的url)
	*/
	public void setHeadimgurl(String headimgurl){
		this.headimgurl = headimgurl;
	}
	/**
	*获取头像URL(微信登录拿到后存自己OSS的url)
	*/
	public String getHeadimgurl(){
		return headimgurl;
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
	*设置微信openId
	*/
	public void setOpenid(String openid){
		this.openid = openid;
	}
	/**
	*获取微信openId
	*/
	public String getOpenid(){
		return openid;
	}
	/**
	*设置城市
	*/
	public void setCity(String city){
		this.city = city;
	}
	/**
	*获取城市
	*/
	public String getCity(){
		return city;
	}
	/**
	*设置性别:男1女2 未知0
	*/
	public void setSex(String sex){
		this.sex = sex;
	}
	/**
	*获取性别:男1女2 未知0
	*/
	public String getSex(){
		return sex;
	}
	/**
	*设置省份
	*/
	public void setProvince(String province){
		this.province = province;
	}
	/**
	*获取省份
	*/
	public String getProvince(){
		return province;
	}
	/**
	*设置国家
	*/
	public void setCountry(String country){
		this.country = country;
	}
	/**
	*获取国家
	*/
	public String getCountry(){
		return country;
	}
	public String getWxopenid() {
		return wxopenid;
	}
	public void setWxopenid(String wxopenid) {
		this.wxopenid = wxopenid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	private String key;//二维码key

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	private String sign_id;
	private String sign_name;
	private String sign_phone;
	private String sign_sex;
	private String sign_nation;
	private String sign_ID_address;
	private String sign_ID_birth;
	private String sign_ID_authority;
	private String sign_ID_valid_date;
	private String sign_validatedata;
	private String sign_frontpic;
	private String sign_backpic;
	private String sign_yt_errorcode;
	private String sign_yt_errormsg;
	private String sign_livestatus;
	private String sign_livemsg;
	private String sign_comparestatus;
	private String sign_comparemsg;
	private String sign_type;
	private String sign_status;
	
	public String getSign_status() {
		return sign_status;
	}
	public void setSign_status(String sign_status) {
		this.sign_status = sign_status;
	}
	public String getSign_id() {
		return sign_id;
	}
	public void setSign_id(String sign_id) {
		this.sign_id = sign_id;
	}
	public String getSign_name() {
		return sign_name;
	}
	public void setSign_name(String sign_name) {
		this.sign_name = sign_name;
	}
	public String getSign_phone() {
		return sign_phone;
	}
	public void setSign_phone(String sign_phone) {
		this.sign_phone = sign_phone;
	}
	public String getSign_sex() {
		return sign_sex;
	}
	public void setSign_sex(String sign_sex) {
		this.sign_sex = sign_sex;
	}
	public String getSign_nation() {
		return sign_nation;
	}
	public void setSign_nation(String sign_nation) {
		this.sign_nation = sign_nation;
	}
	public String getSign_ID_address() {
		return sign_ID_address;
	}
	public void setSign_ID_address(String sign_ID_address) {
		this.sign_ID_address = sign_ID_address;
	}
	public String getSign_ID_birth() {
		return sign_ID_birth;
	}
	public void setSign_ID_birth(String sign_ID_birth) {
		this.sign_ID_birth = sign_ID_birth;
	}
	public String getSign_ID_authority() {
		return sign_ID_authority;
	}
	public void setSign_ID_authority(String sign_ID_authority) {
		this.sign_ID_authority = sign_ID_authority;
	}
	public String getSign_ID_valid_date() {
		return sign_ID_valid_date;
	}
	public void setSign_ID_valid_date(String sign_ID_valid_date) {
		this.sign_ID_valid_date = sign_ID_valid_date;
	}
	public String getSign_validatedata() {
		return sign_validatedata;
	}
	public void setSign_validatedata(String sign_validatedata) {
		this.sign_validatedata = sign_validatedata;
	}
	public String getSign_frontpic() {
		return sign_frontpic;
	}
	public void setSign_frontpic(String sign_frontpic) {
		this.sign_frontpic = sign_frontpic;
	}
	public String getSign_backpic() {
		return sign_backpic;
	}
	public void setSign_backpic(String sign_backpic) {
		this.sign_backpic = sign_backpic;
	}
	public String getSign_yt_errorcode() {
		return sign_yt_errorcode;
	}
	public void setSign_yt_errorcode(String sign_yt_errorcode) {
		this.sign_yt_errorcode = sign_yt_errorcode;
	}
	public String getSign_yt_errormsg() {
		return sign_yt_errormsg;
	}
	public void setSign_yt_errormsg(String sign_yt_errormsg) {
		this.sign_yt_errormsg = sign_yt_errormsg;
	}
	public String getSign_livestatus() {
		return sign_livestatus;
	}
	public void setSign_livestatus(String sign_livestatus) {
		this.sign_livestatus = sign_livestatus;
	}
	public String getSign_livemsg() {
		return sign_livemsg;
	}
	public void setSign_livemsg(String sign_livemsg) {
		this.sign_livemsg = sign_livemsg;
	}
	public String getSign_comparestatus() {
		return sign_comparestatus;
	}
	public void setSign_comparestatus(String sign_comparestatus) {
		this.sign_comparestatus = sign_comparestatus;
	}
	public String getSign_comparemsg() {
		return sign_comparemsg;
	}
	public void setSign_comparemsg(String sign_comparemsg) {
		this.sign_comparemsg = sign_comparemsg;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	
	private String token;
	private Timestamp tokentime;

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Timestamp getTokentime() {
		return tokentime;
	}
	public void setTokentime(Timestamp tokentime) {
		this.tokentime = tokentime;
	}
	
	
}