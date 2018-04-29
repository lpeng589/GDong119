package com.pg.servlet.wx;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pg.bean.EventBean;
import com.pg.bean.MsgBean;
import com.pg.bean.P_eventlogBean;
import com.pg.bean.UserBean;
import com.pg.gzhqy.media.Media;
import com.pg.gzhqy.media.MediaResultBean;
import com.pg.gzhqy.message.Message;
import com.pg.mapper.CodeMapper;
import com.pg.mapper.CodedetailMapper;
import com.pg.mapper.EventMapper;
import com.pg.mapper.F_departmentMapper;
import com.pg.mapper.F_streetMapper;
import com.pg.mapper.H_workuserMapper;
import com.pg.mapper.P_eventlogMapper;
import com.pg.searchbean.CodeSearchBean;
import com.pg.searchbean.CodedetailSearchBean;
import com.pg.searchbean.EventSearchBean;
import com.pg.searchbean.F_departmentSearchBean;
import com.pg.searchbean.F_streetSearchBean;
import com.pg.searchbean.H_workuserSearchBean;
import com.pg.searchbean.MSearchBean;
import com.pg.searchbean.P_eventlogSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.Config;
import com.pg.util.GetLocation;
import com.pg.util.PgFilter;
import com.pg.util.ServletUtil;
import com.pg.util.UploadFile;
@Controller
public class WXEventAction {
	
	  /**
	   * 登陆
	   */
	  @RequestMapping("wxlogin.htm")
	  public void wxlogin(HttpServletRequest request, HttpServletResponse response,UserBean bean) {
		  if(bean.getKey()!=null && !"".equals(bean.getKey())){
			  //检查是否有这个二维码
			  CodeMapper codemapper =ApplicationContextUtil.getMapper(CodeMapper.class);  
			  CodeSearchBean codeSearchBean = new CodeSearchBean();
			  codeSearchBean.setId(Integer.valueOf(bean.getKey()));
			  if(codemapper.getById(codeSearchBean)!=null){
				  //插入一条扫描详情
				  CodedetailMapper mapper =ApplicationContextUtil.getMapper(CodedetailMapper.class);
				  CodedetailSearchBean detailbean = new CodedetailSearchBean();
				  detailbean.setCodeid(Integer.valueOf(bean.getKey()));
				  detailbean.setUserid(PgFilter.getUser(request).getId());
				  mapper.addCodedetail(detailbean);//新增详情
			    }
		 }
	     ServletUtil.sendJsonBean(response, new MsgBean("0", "success"));
	  }
	  
	  /**
	   * 创建事件
	   * @throws UnsupportedEncodingException 
	   * @throws MalformedURLException 
	   */
	  @RequestMapping("wxeventadd.htm")
	  public void wxeventadd(HttpServletRequest request, HttpServletResponse response,EventBean bean) throws UnsupportedEncodingException, MalformedURLException {
//		  if (bean.getType()==null) {
//			  ServletUtil.sendJsonBean(response, new MsgBean("1", "类型不能为空"));
//			  return;
//		  }
//		  System.out.println("-------------------"+bean.getAddress());
//		  System.out.println("-------------------"+new String(bean.getAddress().getBytes("iso8859-1"),"UTF-8"));
			  if (bean.getName()!=null&&!"".equals(bean.getName())) {
				  if (bean.getName().equals("undefined")) {
					  bean.setName("");
				  }
				  bean.setName(new String(bean.getName().getBytes("iso8859-1"),"UTF-8"));
			  }
			  if (bean.getContent()!=null&&!"".equals(bean.getContent())) {
				  if (bean.getContent().equals("undefined")) {
					  bean.setContent("");
				  }
//				  bean.setContent(new String(bean.getContent().getBytes("iso8859-1"),"UTF-8"));
			  }
			  if (bean.getJubao()!=null&&!"".equals(bean.getJubao())) {
				  if (bean.getJubao().equals("undefined")) {
					  bean.setJubao("");
				  }
//				  bean.setJubao(new String(bean.getJubao().getBytes("iso8859-1"),"UTF-8"));
			  }
			  if (bean.getPhone()!=null&&!"".equals(bean.getPhone())) {
				  if (bean.getPhone().equals("undefined")) {
					  bean.setPhone("");
				  }
//				  bean.setPhone(new String(bean.getPhone().getBytes("iso8859-1"),"UTF-8"));
			  }
			  if (bean.getAddress()!=null&&!"".equals(bean.getAddress())) {
				  if (bean.getAddress().equals("undefined")) {
					  bean.setAddress("");
				  }
				  bean.setAddress(new String(bean.getAddress().getBytes("iso8859-1"),"UTF-8"));
			  }
//			  if (bean.getLatitude()!=null&&!"".equals(bean.getLatitude())&&bean.getLongitude()!=null&&!"".equals(bean.getLongitude())) {
//				  bean.setAddress(GetLocation.getAddressByTengxun(bean.getLatitude(),bean.getLongitude()));
//			  }
		  EventMapper eventMapper = ApplicationContextUtil.getMapper(EventMapper.class);
		  bean.setUserid(PgFilter.getUser(request).getId());
		  bean.setName(PgFilter.getUser(request).getSign_name());
		  bean.setPhone(PgFilter.getUser(request).getSign_phone());
//		  int s = eventMapper.addEvent(bean);
//		  if (s>0) {
			  //事件分拨
			  if (bean.getLatitude()!=null&&!"".equals(bean.getLatitude())&&bean.getLongitude()!=null&&!"".equals(bean.getLongitude())) {
				  //经纬度不空，根据经纬度获取地址
				  Map<String, String> addressHashMap = GetLocation.getAddressMapByTengxun(bean.getLatitude(),bean.getLongitude());
				  if (addressHashMap!=null&&addressHashMap.size()>0) {
					  //地址不为空 先计算是否重庆省
					  String province = addressHashMap.get("province");
					  //检查是否是那2个街道的，不是的话，要提醒不能提交
					  String district = addressHashMap.get("district");
					  String town = addressHashMap.get("town");
//					  System.out.println("-------addressHashMap----------------"+addressHashMap);
					  if (district!=null&&!"".equals(district)) {
						  bean.setAddress(new String(bean.getAddress().getBytes("iso8859-1"),"UTF-8"));
						  eventMapper.addEvent(bean);//插入案件
						  P_eventlogMapper p_eventlogMapper = ApplicationContextUtil.getMapper(P_eventlogMapper.class);  //事件日志表
						  P_eventlogBean p_eventlogBean = new P_eventlogBean();
						  p_eventlogBean.setEventid(bean.getId());
						  p_eventlogBean.setEventlogid(0);
						  
						  Integer status = 1;		//分拨状态
						  Integer dealid = null;       //当前处理id
						  Integer next = null;	//下一步处理角色：1部门处理)2巡查员处理（h_workuser）
						  Integer next_id = null;	//下一步处理人id
						  Integer workuserid = null;	
						  Integer department1 = null;	
						  
						  String street = addressHashMap.get("street"); 
						  //先判断是否有该街道
/*						  F_streetSearchBean streetSearchBean = null;
						  if(street!=null && !"".equals(street) ){
							  streetSearchBean = ApplicationContextUtil.getMapper(F_streetMapper.class).getByName(street);
						  }
						  if (streetSearchBean!=null) {
							  department1=streetSearchBean.getDepartmentid(); 
						  }else{*/
							  //如果没有找到该街道 根据距离计算最近街道办
							  F_departmentMapper departmentMapper = ApplicationContextUtil.getMapper(F_departmentMapper.class);
							  F_departmentSearchBean getByName = departmentMapper.getTownByName(town);
							  if(town==null||"".equals(town)){
								  
							  }
							  if(getByName!=null){//已经定位到街道
								  department1 = getByName.getId();
							  }else{
								  List<F_departmentSearchBean> list = departmentMapper.getByType(2);
								  if (list!=null&&list.size()>0) {
									  Double distance=0.0;
									  for (F_departmentSearchBean f_departmentSearchBean : list) {
											if(f_departmentSearchBean.getLongitude()!=null && f_departmentSearchBean.getLatitude()!=null){
												Double distance2=GetLocation.getDistance(bean.getLongitude(),bean.getLatitude(), f_departmentSearchBean.getLongitude(),f_departmentSearchBean.getLatitude());
//												if(distance2==0.0){
//													continue;
//												}
												if(distance==0.0){ 
													distance=distance2;
													department1 = f_departmentSearchBean.getId();
												}
												if(distance>distance2){
													distance=distance2;
													department1 = f_departmentSearchBean.getId();
												}
											}
									  }
								  }
							  }
							  
//						  }
//						  department1=18; xxxxxxxxxxxx
						  //无法获得街道办
						  if( department1 == null){
								 ServletUtil.sendJsonBean(response, new MsgBean("-1", "系统错误")); 
								 return;
						  }else{
							  //修改为随机分配该街道的工作人员
							    F_departmentMapper F_departmentMapper =ApplicationContextUtil.getMapper(F_departmentMapper.class);
							    F_departmentSearchBean  f_departmentSearchBean= new F_departmentSearchBean();
							    f_departmentSearchBean.setId(department1);
							    f_departmentSearchBean = F_departmentMapper.getById(f_departmentSearchBean);
							    
//							  H_workuserSearchBean workuserSearchBeans = ApplicationContextUtil.getMapper(H_workuserMapper.class).getMinByDepartmentid(department1);
							  List<H_workuserSearchBean>  wlist= ApplicationContextUtil.getMapper(H_workuserMapper.class).getByDepartmentid(department1);
							  for (int i = 0; i < wlist.size(); i++) {
								  H_workuserSearchBean  workuserSearchBeans=wlist.get(i);
								  next=2;
								  next_id=workuserSearchBeans.getId();
								  status = 1;//待分拨
								  workuserid=workuserSearchBeans.getId();
								    //给他发消息，分拨
								    H_workuserMapper h_workuserMapper = ApplicationContextUtil.getMapper(H_workuserMapper.class);
								  	H_workuserSearchBean h_workuserSearchBean = new H_workuserSearchBean();
								  	h_workuserSearchBean.setId(workuserid);
								  	h_workuserSearchBean=h_workuserMapper.getById(h_workuserSearchBean);
								  	String key= Config.getQyWxKeyFenbo();
								  	Message message = new Message(key);
								  	message.textSingle(h_workuserSearchBean.getCode(), "", "您有一个案件需要分拨处理。\n地点："+bean.getAddress()+";\n案件内容:"+bean.getContent()+""+"\n<a href=\""+Config.getValue("url")+"wxeventdetailfenbo.do?id="+p_eventlogBean.getEventid()+"\">点击查看详情</a>", 1000003);
								  
									  p_eventlogBean.setDeal_opinion("");
									  p_eventlogBean.setStatus(status);
									  p_eventlogBean.setDeal(0);//系统处理
									  p_eventlogBean.setDealid(null);//系统处理
									  p_eventlogBean.setNext(next);   //原来参数是next_id
									  p_eventlogBean.setNext_id(next_id);
									  p_eventlogBean.setWorkuserid(workuserid);
									  p_eventlogBean.setDepartment2(department1);//街道办的
									  p_eventlogBean.setDeal_opinion("等待"+f_departmentSearchBean.getName()+"的管理员"+h_workuserSearchBean.getName()+"手动分拨");
									  p_eventlogMapper.addP_eventlog(p_eventlogBean);
							   }
							  //判断该街道办下是否有巡查员，现在是查询一个
							  if (wlist.size()==0) {
								  next=1;
								  next_id=department1;
								  status = 1;//待分拨
								  
								  p_eventlogBean.setDeal_opinion("");
								  p_eventlogBean.setStatus(status);
								  p_eventlogBean.setDeal(0);//系统处理
								  p_eventlogBean.setDealid(null);//系统处理
								  p_eventlogBean.setNext(next);   //原来参数是next_id
								  p_eventlogBean.setNext_id(next_id);
								  p_eventlogBean.setWorkuserid(workuserid);
								  p_eventlogBean.setDepartment2(department1);
								  p_eventlogBean.setDeal_opinion("等待乡镇街道"+f_departmentSearchBean.getName()+"手动分拨");
								  p_eventlogMapper.addP_eventlog(p_eventlogBean);
							  }
							  ServletUtil.sendJsonBean(response, new MsgBean("0", "提交成功"));
							  return;
						  }
						  
					  }else{
						 ServletUtil.sendJsonBean(response, new MsgBean("-1", "你所在地区暂未开放，如遇紧急情况可以拨打96119")); 
						 return;
					  }
				  }else{
						 ServletUtil.sendJsonBean(response, new MsgBean("-1", "定位失败")); 
						 return;
				  }
			  }else{
					 ServletUtil.sendJsonBean(response, new MsgBean("-1", "定位失败")); 
					 return;
			  }
//		  }else {
//			  ServletUtil.sendJsonBean(response, new MsgBean("1", "提交失败"));
//		  }
	  }
	  
	  /**
	   * 事件列表
	   */
	  @RequestMapping("wxeventlist.htm")
	  public void wxeventlist(HttpServletRequest request, HttpServletResponse response,MSearchBean bean) {
		  EventMapper eventMapper = ApplicationContextUtil.getMapper(EventMapper.class);
		  bean.setUserid(PgFilter.getUser(request).getId());
		  List<EventSearchBean> wxlist = eventMapper.wxlist(bean);
	      ServletUtil.sendJsonBean(response, new MsgBean(wxlist));
	  }
  
	  /**
	   * 事件评价
	   * @throws UnsupportedEncodingException 
	   */
	  @RequestMapping("wxeventupdate.htm")
	  public void wxeventupdate(HttpServletRequest request, HttpServletResponse response,EventBean bean) throws UnsupportedEncodingException {
		  if (bean.getId()==null) {
			  ServletUtil.sendJsonBean(response, new MsgBean("1", "非法请求"));
			  return;
		  }
		  if (bean.getLevel()==null) {
			  ServletUtil.sendJsonBean(response, new MsgBean("1", "评分不能为空"));
			  return;
		  }
//		  if (request.getParameter("_code")!=null) {
			  if (bean.getAppraise()!=null&&!"".equals(bean.getAppraise())) {
				  bean.setAppraise(bean.getAppraise());
			  }
//		  }
		  EventMapper eventMapper = ApplicationContextUtil.getMapper(EventMapper.class);
		  EventSearchBean searchBean = new EventSearchBean();
		  searchBean.setId(bean.getId());
		  EventSearchBean searchBean2 = eventMapper.getById(searchBean);
		  if (searchBean2==null||searchBean2.getUserid().intValue()!=PgFilter.getUser(request).getId().intValue()) {
			  ServletUtil.sendJsonBean(response, new MsgBean("1", "非法请求"));
			  return;
		  }
		  int s = eventMapper.updateEvent(bean);
		  if (s>0) {
			  ServletUtil.sendJsonBean(response, new MsgBean("0", "提交成功"));
		  }else {
			  ServletUtil.sendJsonBean(response, new MsgBean("1", "提交失败"));
		  }
	  }
	  
	  /**
	   * 事件详情
	   */
	  @RequestMapping("wxeventdetail.htm")
	  public void wxeventdetail(HttpServletRequest request, HttpServletResponse response,EventSearchBean bean) {
		  if (bean.getId()==null) {
			  ServletUtil.sendJsonBean(response, new MsgBean("1", "非法请求"));
			  return;
		  }
		  EventMapper eventMapper = ApplicationContextUtil.getMapper(EventMapper.class);
		  bean.setUserid(PgFilter.getUser(request).getId());
		  EventSearchBean searchBean = eventMapper.getById(bean);
		  if (searchBean==null) {
			  ServletUtil.sendJsonBean(response, new MsgBean("1", "非法请求"));
			  return;
		  }
		  if(searchBean!=null&&searchBean.getLogstatus()!=null&&(searchBean.getLogstatus()==6||searchBean.getLogstatus()==7||searchBean.getLogstatus()==8)){
			  searchBean.setStatus(1);
		  }else{
			  searchBean.setStatus(0);
		  }
		  P_eventlogMapper eventlogMapper = ApplicationContextUtil.getMapper(P_eventlogMapper.class);
		  P_eventlogSearchBean logbean = new P_eventlogSearchBean();
		  logbean.setId(searchBean.getEventlogid());
		  logbean = eventlogMapper.getById(logbean);
		  if(logbean!=null){
			  String statusString ="";
        	  if(logbean.getStatus()==6){
        		  statusString="已结案";
        	  }else if(logbean.getStatus()==7){
        		  statusString="未整改";
        	  }else if(logbean.getStatus()==8){
        		  statusString="无效案件"; 
        	  }
			  searchBean.setReply(statusString+"   "+logbean.getDeal_opinion());
		  }
		  searchBean.setUser_id(PgFilter.getUser(request).getId());
	      ServletUtil.sendJsonBean(response, new MsgBean(searchBean));
	  }
	  
	  /**
	   * 获取地址
	 * @throws MalformedURLException 
	   */
	  @RequestMapping("wxgetaddress.htm")
	  public void wxgetaddress(HttpServletRequest request, HttpServletResponse response,EventBean bean) throws MalformedURLException {
		  String address = "";
		  if (bean.getLatitude()!=null&&!"".equals(bean.getLatitude())&&bean.getLongitude()!=null&&!"".equals(bean.getLongitude())) {
			  address = GetLocation.getAddressByTengxun(bean.getLatitude(),bean.getLongitude());
		  }
	      ServletUtil.sendJsonBean(response, new MsgBean(address));
	  }
	  
	  @RequestMapping("send.htm")
	  public void send(HttpServletRequest request, HttpServletResponse response) {
		  String key = Config.getWXQyKey(Config.getValue("qywxalarmsecret"));
		  Message message =  new Message(key);
		  String path = UploadFile.downloadPicture(request, "http://pnkooweibo.oss-cn-shenzhen.aliyuncs.com/attached/wxfile/201612/A2D45398BE9AD2B2E0E2439C10A39508.silk");
		  if (path!=null&&!"".equals(path)) {
			  MediaResultBean mediaResultBean = new Media(key).uploadTemp("voice", path);
			  if (mediaResultBean.getErrcode().equals("0")) {
				  message.voiceSingle("@all", "", mediaResultBean.getMedia_id(), Integer.valueOf(Config.getValue("qywxalarmid")));
			  }
		  }
	  }

	  /**
	   * 签名
	   */
	  @RequestMapping("wxsign.htm")
	  public void wxsign(HttpServletRequest request, HttpServletResponse response) {
//		 String a = Config.getValue(Config.CONFIG_WXCXAPPID);
//		  System.out.println("------------88888--------------------------");
//		  System.out.println("------------UserBean--------------------------"+PgFilter.getUser(request));
//		  System.out.println("------------openid--------------------------"+PgFilter.getUser(request).getOpenid());
		 String a="4423" ;
		  
		 String m="appauth";
		 String t=System.currentTimeMillis()/1000+"";
//		 String e="300";
		 String e="600";//		 String Expired="String";
//		 String secret = Config.getValue(Config.CONFIG_WXCXSECRET);
		 String secret = "6600b8aa3e311e2a6ce17a5d8d8fe47b";
		 String ResultKey="26c8d82cfdce7bc8b9bff1671023a7bf";
		 String plainText = "a="+a+"&m="+m+"&t="+t+"&e="+e;
		 //编码
		 String signature="";
		 try {
			 signature=com.pg.wxsign.Util.getSignature(secret, plainText);
		 } catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		 }
		 //编码
		 Map<String, String> map  = new HashMap<>();
		 map.put("appid", a);
//		 map.put("uid", "oX64I0UeUxFRTQm6GwfgeWoTEIqc");
		 map.put("uid", PgFilter.getUser(request).getOpenid());
		 map.put("signature",signature);
		  System.out.println("------------999--------------------------"+map);
	     ServletUtil.sendJsonBean(response, new MsgBean("0", "success",map));
	  }
	  
	  //事件详情
	  @RequestMapping("wxeventdetailpage.do")
	  public String wxeventdetailpage(HttpServletRequest request, HttpServletResponse response,P_eventlogSearchBean bean) {
		  EventMapper eventMapper = ApplicationContextUtil.getMapper(EventMapper.class);
		  EventSearchBean eventSearchBean = new EventSearchBean();
		  eventSearchBean.setId(bean.getId());
		  eventSearchBean = eventMapper.getById(eventSearchBean);
//		  System.out.println("1111111111111111eventSearchBean.getNext_id():"+eventSearchBean.getNext_id());
//		  System.out.println("222222222222PgFilter.getWorkUser(request).getId():"+PgFilter.getWorkUser(request).getId());
		  if(eventSearchBean.getNext_id()!=null&&(eventSearchBean.getNext_id().intValue()==PgFilter.getWorkUser(request).getId())){
			  eventSearchBean.setFlag(true);
		  }
		  P_eventlogMapper p_eventlogMapper = ApplicationContextUtil.getMapper(P_eventlogMapper.class);
		  bean.setOperate2(-1);
		  p_eventlogMapper.updateNeedP_eventlog(bean);
		  request.setAttribute("bean", eventSearchBean);
		  
		  //所有同级成员
		  H_workuserMapper H_workuserMapper = ApplicationContextUtil.getMapper(H_workuserMapper.class);
    	  H_workuserSearchBean H_workuserSearchBean =  new  H_workuserSearchBean();
    	  H_workuserSearchBean.setDepartmentid(PgFilter.getWorkUser(request).getDepartmentid());
    	  H_workuserSearchBean.setStatus(1);
    	  request.setAttribute("workuserlist", H_workuserMapper.list(H_workuserSearchBean));  //所有子部门
    	  request.setAttribute("workuserid", PgFilter.getWorkUser(request).getId());
		  return "mobile/wxeventdetail.jsp";
	  }
	  //事件处理详情
	  @RequestMapping("wxeventdetailhandle.do")
	  public void wxeventdetailhandle(HttpServletRequest request, HttpServletResponse response,P_eventlogSearchBean bean) {
		  P_eventlogMapper p_eventlogMapper = ApplicationContextUtil.getMapper(P_eventlogMapper.class);
		  bean.setEventid(Integer.parseInt(request.getParameter("id")));
		  ServletUtil.sendJsonBean(response, new MsgBean("0", "success",p_eventlogMapper.detail(bean)));
	  }
	  
	  //事件处理
	  @RequestMapping("wxeventreply.do")
	  public void wxeventreply(HttpServletRequest request, HttpServletResponse response,P_eventlogSearchBean bean) {
		  if(bean.getStatus()!=2){
			  ServletUtil.sendJsonBean(response, new MsgBean("-1","非法参数"));
			  return ;
		  }
		  if(bean.getDeal_file()!=null){
			  String arr[] = bean.getDeal_file().split(",");
			  bean.setDeal_file("");
			  for (String string : arr) {
				if(string!=null&&!"undefined".equals(string)&&!"".equals(string)){
					bean.setDeal_file(bean.getDeal_file()+","+UploadFile.uploadQyWxMedia(string,request));
				}
			}
		  }
		  P_eventlogMapper p_eventlogMapper = ApplicationContextUtil.getMapper(P_eventlogMapper.class);
		  P_eventlogSearchBean p_eventlogSearchBean = new P_eventlogSearchBean();
		  p_eventlogSearchBean.setId(bean.getEventlogid());
		  p_eventlogSearchBean=p_eventlogMapper.getById(p_eventlogSearchBean);

		  F_departmentMapper mapper =ApplicationContextUtil.getMapper(F_departmentMapper.class);
		  F_departmentSearchBean f_departmentSearchBean = new F_departmentSearchBean();
		  f_departmentSearchBean.setId(PgFilter.getWorkUser(request).getDepartmentid());
		  f_departmentSearchBean = mapper.getById(f_departmentSearchBean);
		  if(f_departmentSearchBean.getType()==3){//上级是村社的，更改为街道办
			  bean.setDepartment2(f_departmentSearchBean.getParentid());//
		  }else if(f_departmentSearchBean.getType()==2){//上级是街道办
			  bean.setDepartment2(PgFilter.getWorkUser(request).getDepartmentid());
		  }else if(f_departmentSearchBean.getType()==1){
			  bean.setDepartment1(PgFilter.getWorkUser(request).getDepartmentid());//上级是支队
		  }
		  bean.setDeal(2);
		  bean.setDealid(PgFilter.getWorkUser(request).getId());
		  if(bean.getNext_id()!=null&&bean.getNext_id()>0){//移交给其他人的
			  bean.setWorkuserid(bean.getNext_id());//巡查员处理
			  bean.setStatus(2);
			  bean.setNext(2);
			  bean.setNext_id(bean.getNext_id());
			  
			    H_workuserMapper H_workuserMapper = ApplicationContextUtil.getMapper(H_workuserMapper.class);
				H_workuserSearchBean H_workuserSearchBean =  new  H_workuserSearchBean();
				H_workuserSearchBean.setId(bean.getNext_id());
				H_workuserSearchBean = H_workuserMapper.getById( H_workuserSearchBean);
			    bean.setDeal_opinion("移交给"+H_workuserSearchBean.getName()+"："+ bean.getDeal_opinion());
			  //发送微信消息
			    EventMapper EventMapper = ApplicationContextUtil.getMapper(EventMapper.class);
				EventSearchBean eventSearchBean =  new EventSearchBean();
				eventSearchBean.setId(bean.getEventid());
				eventSearchBean = EventMapper.getById(eventSearchBean);
				
				String key= Config.getQyWxKeyFenbo();
			  	Message message = new Message(key);
			  	message.textSingle(H_workuserSearchBean.getCode(), "", "您有一个案件需要分拨处理。\n地点："+eventSearchBean.getAddress()+";\n案件内容:"+eventSearchBean.getContent()+""+"\n<a href=\""+Config.getValue("url")+"wxeventdetailpage.do?id="+eventSearchBean.getId()+"\">点击查看详情</a>", 1000002);
			  
			  
			  
		  }else{//返回给街道办的
			  bean.setNext(1);//默认是部门
			  bean.setStatus(3);
			  if(f_departmentSearchBean.getType()==3){//上级是村社的，更改为街道办
				  bean.setNext_id(PgFilter.getWorkUser(request).getDepartparentid());
			  }else{//上级是街道办
				  bean.setNext_id(PgFilter.getWorkUser(request).getDepartmentid());
			  }
			  bean.setDeal_opinion("反馈给"+f_departmentSearchBean.getName()+"："+ bean.getDeal_opinion());
		  }
		  p_eventlogMapper.addP_eventlog(bean);
		  ServletUtil.sendJsonBean(response, new MsgBean("0","处理成功"));
	  }
	  
		/**
		 * 案件列表
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping("qywxeventlist.do")
		public String mnewslist1(HttpServletRequest request, HttpServletResponse response) {
			return "mobile/wxeventlist.jsp";
		}
		
		/**
		 * 获取案件列表
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping("qywxeventlistget.do")
		public void mnewslistget(HttpServletRequest request, HttpServletResponse response) {
			P_eventlogMapper p_eventlogMapper = ApplicationContextUtil.getMapper(P_eventlogMapper.class);
			P_eventlogSearchBean bean = new P_eventlogSearchBean();
			bean.setWorkuserid(PgFilter.getWorkUser(request).getId());
			List<P_eventlogSearchBean> list = p_eventlogMapper.getByWorkuserid(bean);
			if(list!=null){
				for (P_eventlogSearchBean p_eventlogSearchBean : list) {
					if(p_eventlogSearchBean.getNext_id()!=null&&(p_eventlogSearchBean.getNext_id().intValue()==bean.getWorkuserid().intValue())){
						p_eventlogSearchBean.setFlag(true);
					}
				}
			}
			ServletUtil.sendJsonBean(response, new MsgBean("0", "success",list));
		}
		
		
		@RequestMapping("wxeventget.do")
	    public void mnewslist1get(HttpServletRequest request, HttpServletResponse response,MSearchBean bean) {
			EventMapper mapper = ApplicationContextUtil.getMapper(EventMapper.class);
//			System.out.println("PgFilter.getWorkUser(request).getId()-------------"+PgFilter.getWorkUser(request).getId());
			bean.setDeptuserid(PgFilter.getWorkUser(request).getId());
			ServletUtil.sendJsonBean(response, new MsgBean(mapper.mlist(bean)));
		}
		
		
		  //事件分拨详情
		  @RequestMapping("wxeventdetailfenbo.do")
		  public String wxeventdetailfenbo(HttpServletRequest request, HttpServletResponse response,P_eventlogSearchBean bean) {
			  EventMapper eventMapper = ApplicationContextUtil.getMapper(EventMapper.class);
			  EventSearchBean eventSearchBean = new EventSearchBean();
			  eventSearchBean.setId(bean.getId());
			  eventSearchBean = eventMapper.getById(eventSearchBean);
			  if(eventSearchBean.getNext_id()!=null&&eventSearchBean.getNext_id().intValue()==PgFilter.getWorkUser(request).getId()){
				  eventSearchBean.setFlag(true);
			  }
			  P_eventlogMapper p_eventlogMapper = ApplicationContextUtil.getMapper(P_eventlogMapper.class);
			  bean.setOperate2(-1);
			  p_eventlogMapper.updateNeedP_eventlog(bean);
			  request.setAttribute("bean", eventSearchBean);
			  //分拨给其他社区的人
				eventSearchBean =  new EventSearchBean();
				eventSearchBean.setId(bean.getId());
				eventSearchBean = eventMapper.getById(eventSearchBean);
				
			  F_departmentMapper mapper =ApplicationContextUtil.getMapper(F_departmentMapper.class);
			  F_departmentSearchBean f_departmentSearchBean = new F_departmentSearchBean();
			  f_departmentSearchBean.setLimitFlag(false);
			  f_departmentSearchBean.setParentid(PgFilter.getWorkUser(request).getDepartmentid());
			  List<F_departmentSearchBean> list =  mapper.list(f_departmentSearchBean);
			  Integer departmentid = null;
//			  Double distance=0.0;
			  for (F_departmentSearchBean departmentBean : list) {
					if(eventSearchBean.getLongitude()!=null && eventSearchBean.getLatitude()!=null && departmentBean.getLongitude()!=null && departmentBean.getLatitude()!=null){
						Double distance=GetLocation.getDistance(eventSearchBean.getLongitude(),eventSearchBean.getLatitude(), departmentBean.getLongitude(),departmentBean.getLatitude());
						departmentBean.setDistance(distance);
					}else{
						departmentBean.setDistance(100000.0);
					}
			  }
			  Collections.sort(list, new DisComparator()); //排序
			  request.setAttribute("departmentlist",list);
			  
			  return "mobile/wxeventdetailfenbo.jsp";
		  }
		  
			class DisComparator implements Comparator<Object> {
		    	@SuppressWarnings("rawtypes")
				public int compare(Object a, Object b) {
		    		F_departmentSearchBean hashA = (F_departmentSearchBean)a;
		    		F_departmentSearchBean hashB = (F_departmentSearchBean)b;
		    		return (hashA.getDistance()).compareTo(hashB.getDistance())	;
		    	}
			}
		      
			/**
			 * 案件分拨列表
			 * @param request
			 * @param response
			 * @return
			 */
			@RequestMapping("qywxeventfenbolist.do")
			public String qywxeventfenbolist(HttpServletRequest request, HttpServletResponse response) {
				return "mobile/wxeventfenbolist.jsp";
			}
			
			/**
			 * 获取案件列表
			 * @param request
			 * @param response
			 * @return
			 */
			@RequestMapping("qywxeventfenbolistget.do")
			public void qywxeventfenbolistget(HttpServletRequest request, HttpServletResponse response) {
				P_eventlogMapper p_eventlogMapper = ApplicationContextUtil.getMapper(P_eventlogMapper.class);
				P_eventlogSearchBean bean = new P_eventlogSearchBean();
				List<P_eventlogSearchBean> list = p_eventlogMapper.getfenbolist(PgFilter.getWorkUser(request).getId());
//				List<P_eventlogSearchBean> list = p_eventlogMapper.getfenbolist(24);
				if(list!=null){
					for (P_eventlogSearchBean p_eventlogSearchBean : list) {
						if(p_eventlogSearchBean.getNext_id()!=null&&p_eventlogSearchBean.getNext_id().intValue()==PgFilter.getWorkUser(request).getId().intValue()){
							p_eventlogSearchBean.setFlag(true);
						}
					}
				}
				ServletUtil.sendJsonBean(response, new MsgBean("0", "success",list));
			}
			
			 //事件分拨
			  @RequestMapping("wxeventreplyfenbo.do")
			  public void wxeventreplyfenbo(HttpServletRequest request, HttpServletResponse response,P_eventlogSearchBean bean) {
				  if(bean.getStatus()!=1){
					  ServletUtil.sendJsonBean(response, new MsgBean("-1","非法参数"));
					  return ;
				  }
//				  bean.setDeal_file("4KTBvADIu5zu0v5Vj-_qx9hB6wNjY9FmZuS78fVt5_LIUR2LdPeCBnU8ywugEqq3,undefined,undefined,");
//				  System.out.println("-----------bean.getDeal_file()-------"+bean.getDeal_file());
				  if(bean.getDeal_file()!=null){
					  String fileString="";
					  String arr[] = bean.getDeal_file().split(",");
					  for (String string : arr) {
						if(string!=null&&!"undefined".equals(string)&&!"".equals(string)){
							fileString=fileString+","+UploadFile.uploadQyWxMedia(string,request);
						}
						bean.setDeal_file(fileString);
					}
				  }
				  //获得村社的一个巡查员
				  H_workuserSearchBean workuserSearchBeans = ApplicationContextUtil.getMapper(H_workuserMapper.class).getMinByDepartmentid(bean.getDepartmentid());
				  if(workuserSearchBeans==null){
						ServletUtil.sendJsonBean(response, new MsgBean("-1", "该村社不存在巡查员"));
						return ;
				  }else{
					  P_eventlogMapper p_eventlogMapper = ApplicationContextUtil.getMapper(P_eventlogMapper.class);
					  P_eventlogSearchBean p_eventlogSearchBean = new P_eventlogSearchBean();
					  p_eventlogSearchBean.setId(bean.getEventlogid());
					  p_eventlogSearchBean=p_eventlogMapper.getById(p_eventlogSearchBean);
					  
					  bean.setStatus(2);
					  bean.setDeal(2);
					  bean.setDealid(PgFilter.getWorkUser(request).getId());
					  bean.setWorkuserid(workuserSearchBeans.getId());//街道办职员分拨
					  bean.setDepartment2(PgFilter.getWorkUser(request).getDepartmentid());//街道办ID 
					  if(workuserSearchBeans.getDeparttype()==3){//假如是上级是村社
						  bean.setDepartment2(workuserSearchBeans.getDepartparentid());//街道办ID 
					  }else if(workuserSearchBeans.getDeparttype()==2){//上级是街道办
						  bean.setDepartment2(PgFilter.getWorkUser(request).getDepartmentid());//街道办ID 
					  }else if(workuserSearchBeans.getDeparttype()==1){//上级是支队
						  bean.setDepartment1(PgFilter.getWorkUser(request).getDepartmentid());//支队ID 
					  }
					  
					  bean.setNext(2);
					  bean.setNext_id(workuserSearchBeans.getId());//分拨到村社巡查员
					  
						H_workuserMapper h_workuserMapper = ApplicationContextUtil.getMapper(H_workuserMapper.class);
					  	H_workuserSearchBean h_workuserSearchBean = new H_workuserSearchBean();
					  	h_workuserSearchBean.setId(workuserSearchBeans.getId());
					  	h_workuserSearchBean=h_workuserMapper.getById(h_workuserSearchBean);
					  	
					  bean.setDeal_opinion("分拨给"+h_workuserSearchBean.getName()+"："+ bean.getDeal_opinion());
					  
					  p_eventlogMapper.addP_eventlog(bean);
					  
				       //发信息给村社巡查员
					    EventMapper mapper = ApplicationContextUtil.getMapper(EventMapper.class);
						EventSearchBean eventSearchBean =  new EventSearchBean();
						eventSearchBean.setId(bean.getEventid());
						eventSearchBean = mapper.getById(eventSearchBean);
					    
					  	String key= Config.getQyWxKey();
					  	Message message = new Message(key);
					  	message.textSingle(h_workuserSearchBean.getCode(), "", "您收到了一条新的案件。\n地点："+eventSearchBean.getAddress()+";\n案件内容:"+eventSearchBean.getContent()+""+"\n<a href=\""+Config.getValue("url")+"wxeventdetailpage.do?id="+bean.getEventid()+"\">点击查看详情</a>", 1000002); 
						  	
					  ServletUtil.sendJsonBean(response, new MsgBean("0","分拨成功"));
				  }
			  }  
}