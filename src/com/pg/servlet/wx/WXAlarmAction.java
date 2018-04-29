package com.pg.servlet.wx;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pg.bean.AlarmBean;
import com.pg.bean.AlarmdetailBean;
import com.pg.bean.MsgBean;
import com.pg.gzhqy.media.Media;
import com.pg.gzhqy.media.MediaResultBean;
import com.pg.gzhqy.message.Message;
import com.pg.mapper.AlarmMapper;
import com.pg.mapper.AlarmdetailMapper;
import com.pg.mapper.EmployeeMapper;
import com.pg.searchbean.AlarmSearchBean;
import com.pg.searchbean.AlarmdetailSearchBean;
import com.pg.searchbean.EmployeeSearchBean;
import com.pg.searchbean.MSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.Config;
import com.pg.util.PgFilter;
import com.pg.util.ServletUtil;
import com.pg.util.UploadFile;
@Controller
public class WXAlarmAction 
{
	/**
	 * 获取用户的最新报警信息
	 */
	@RequestMapping("getwxalarm.htm")
	public void getwxalarm(HttpServletRequest request, HttpServletResponse response,AlarmSearchBean bean){
		bean.setUserid(PgFilter.getUser(request).getId());
		AlarmMapper alarmMapper = ApplicationContextUtil.getMapper(AlarmMapper.class);
		AlarmSearchBean alarmSearchBean = new AlarmSearchBean();
		alarmSearchBean = alarmMapper.getwxalarm(bean);
		if(alarmSearchBean!=null){
			 ServletUtil.sendJsonBean(response, new MsgBean(alarmSearchBean));
		}else{
			AlarmBean alarmbean = new AlarmBean();
			alarmbean.setUserid(bean.getUserid());
			Integer addAlarm = alarmMapper.addAlarm(alarmbean);
			if(addAlarm < 0){
				ServletUtil.sendJsonBean(response, new MsgBean("1", "系统错误"));
			}else{
				ServletUtil.sendJsonBean(response, new MsgBean(alarmbean));
			}
		}
	}
	  /**
	   * 建立报警
	   * @throws UnsupportedEncodingException 
	   */
	  @RequestMapping("wxalarmadd.htm")
	  public void wxalarmadd(HttpServletRequest request, HttpServletResponse response,AlarmBean bean) throws UnsupportedEncodingException {
		  bean.setUserid(PgFilter.getUser(request).getId());
		  AlarmMapper alarmMapper = ApplicationContextUtil.getMapper(AlarmMapper.class);
		  int r = alarmMapper.addAlarm(bean);
		  if (r>0) {
			  String address = "";
			  if (bean.getAddress()!=null&&!"".equals(bean.getAddress())) {
				  address = new String(bean.getAddress().getBytes("iso8859-1"),"UTF-8");
			  }
			  bean.setAddress(address);
			  String content = "在"+address+"位置发生了新的案情，更多信息将推送过来，请尽快处理事件";
			//  new Message(Config.getWXQyKey(Config.getValue("qywxalarmsecret"))).textSingle("@all", "", content,Integer.valueOf(Config.getValue("qywxalarmid")));
			  //插入子表
			  AlarmdetailMapper alarmdetailMapper = ApplicationContextUtil.getMapper(AlarmdetailMapper.class);
			  AlarmdetailBean  alarmdetailBean = new  AlarmdetailBean();
			  alarmdetailBean.setSendtype(0);
			  alarmdetailBean.setType(4);
			  alarmdetailBean.setAlarmid(bean.getId());
			  alarmdetailBean.setContent(address);
		      alarmdetailMapper.addAlarmdetail(alarmdetailBean);
		      //插入站内消息表
//			    HashMap<String, Object> hashMap= new HashMap<String, Object>();
//			    hashMap.put("address", address);
				EmployeeMapper mapper =ApplicationContextUtil.getMapper(EmployeeMapper.class);
				EmployeeSearchBean employeeBean = new EmployeeSearchBean();	
				employeeBean.setStatus(1);
				com.pg.util.Message.send("alerm", mapper.list(employeeBean), bean);
				
			  ServletUtil.sendJsonBean(response, new MsgBean("0", "创建成功",bean.getId()));
		  }else {
			  ServletUtil.sendJsonBean(response, new MsgBean("1", "创建失败"));
		  }
	      
	  }
	 
	  /**
	   * 接收报警信息
	 * @throws UnsupportedEncodingException 
	   */
	  @RequestMapping("wxalarmdetailadd.htm")
	  public void wxalarmdetailadd(HttpServletRequest request, HttpServletResponse response,AlarmdetailBean bean) throws UnsupportedEncodingException {
		  if (bean.getAlarmid()==null) {
			  ServletUtil.sendJsonBean(response, new MsgBean("1", "非法请求，ID不能为空"));
			  return;
		  }
		  if (bean.getType()==null) {
			  ServletUtil.sendJsonBean(response, new MsgBean("1", "非法请求，类型不能为空"));
			  return;
		  }
		  if (bean.getContent()==null||"".equals(bean.getContent())) {
			  ServletUtil.sendJsonBean(response, new MsgBean("1", "非法请求，内容不能为空"));
			  return;
		  }
		  bean.setContent(new String(bean.getContent().getBytes("iso8859-1"),"UTF-8"));
		  AlarmMapper alarmMapper = ApplicationContextUtil.getMapper(AlarmMapper.class);
		  AlarmSearchBean alarmSearchBean = new AlarmSearchBean();
		  alarmSearchBean.setId(bean.getAlarmid());
		  AlarmSearchBean searchBean = alarmMapper.getById(alarmSearchBean);
		  if (searchBean==null||searchBean.getUserid().intValue()!=PgFilter.getUser(request).getId().intValue()) {
			  ServletUtil.sendJsonBean(response, new MsgBean("1", "非法请求"));
			  return;
		  }
		  if (searchBean.getStatus()==2) {
			  ServletUtil.sendJsonBean(response, new MsgBean("1", "无法提交，该报警已结束"));
			  return;
		  }
		  bean.setSendtype(0);
		  AlarmdetailMapper alarmdetailMapper = ApplicationContextUtil.getMapper(AlarmdetailMapper.class);
		  int r = alarmdetailMapper.addAlarmdetail(bean);
		  if (r>0) {
			  String key = Config.getWXQyKey(Config.getValue("qywxalarmsecret"));
			  Message message =  new Message(key);
			  if (bean.getType()==0||bean.getType()==4) {//发文本
				//  message.textSingle("@all", "", bean.getContent(),Integer.valueOf(Config.getValue("qywxalarmid")));
			  }else if (bean.getType()==1) {	//语音
				  String path = UploadFile.downloadPicture(request, bean.getContent());
				  if (path!=null&&!"".equals(path)) {
					  MediaResultBean mediaResultBean = new Media(key).uploadTemp("voice", path);
					  UploadFile.deleteFile(path);//删除本地文件
					  if (mediaResultBean.getErrcode().equals("0")) {
						  message.voiceSingle("@all", "", mediaResultBean.getMedia_id(), Integer.valueOf(Config.getValue("qywxalarmid")));
					  }
				  }
			  }else if (bean.getType()==2) {	//视频
				  String path = UploadFile.downloadPicture(request, bean.getContent());
				  if (path!=null&&!"".equals(path)) {
					  MediaResultBean mediaResultBean = new Media(key).uploadTemp("video", path);
					  UploadFile.deleteFile(path);//删除本地文件
					  if (mediaResultBean.getErrcode().equals("0")) {
						  message.videoSingle("@all", "", mediaResultBean.getMedia_id(), Integer.valueOf(Config.getValue("qywxalarmid")),"","");
					  }
				  }
			  }else if (bean.getType()==3) {	//图片
				  String path = UploadFile.downloadPicture(request, bean.getContent());
				  if (path!=null&&!"".equals(path)) {
					  MediaResultBean mediaResultBean = new Media(key).uploadTemp("image", path);
					  UploadFile.deleteFile(path);//删除本地文件
					  if (mediaResultBean.getErrcode().equals("0")) {
						  message.imageSingle("@all", "", mediaResultBean.getMedia_id(), Integer.valueOf(Config.getValue("qywxalarmid")));
					  }
				  }
			  }
			  List<AlarmdetailSearchBean> list = new ArrayList<>();
			  AlarmdetailSearchBean alarmdetailSearchBean = new AlarmdetailSearchBean();
			  alarmdetailSearchBean.setId(bean.getId());
			  list.add(alarmdetailMapper.getById(alarmdetailSearchBean));
			  searchBean.setList(list);
			  ServletUtil.sendJsonBean(response, new MsgBean("0", "发送成功",searchBean));
		  }else {
			  ServletUtil.sendJsonBean(response, new MsgBean("1", "发送失败"));
		  }
	      
	  }
	  
	  /**
	   * 报警列表
	   */
	  @RequestMapping("wxalarmlist.htm")
	  public void wxalarmlist(HttpServletRequest request, HttpServletResponse response,MSearchBean bean) {
		  AlarmMapper alarmMapper = ApplicationContextUtil.getMapper(AlarmMapper.class);
		  bean.setUserid(PgFilter.getUser(request).getId());
	      ServletUtil.sendJsonBean(response, new MsgBean(alarmMapper.wxlist(bean)));
	  }
	  
	  /**
	   * 报警详情
	   */
	  @RequestMapping("wxalarmdetail.htm")
	  public void wxalarmdetail(HttpServletRequest request, HttpServletResponse response,MSearchBean bean) {
		  if (bean.getAlarmid()==null) {
			  ServletUtil.sendJsonBean(response, new MsgBean("1", "非法请求"));
			  return;
		  }
		  AlarmMapper alarmMapper = ApplicationContextUtil.getMapper(AlarmMapper.class);
		  AlarmSearchBean alarmSearchBean = new AlarmSearchBean();
		  alarmSearchBean.setId(bean.getAlarmid());
		  AlarmSearchBean searchBean = alarmMapper.getById(alarmSearchBean);
		  if (searchBean==null||searchBean.getUserid().intValue()!=PgFilter.getUser(request).getId().intValue()) {
			  ServletUtil.sendJsonBean(response, new MsgBean("1", "非法请求"));
			  return;
		  }
		  AlarmdetailMapper alarmdetailMapper = ApplicationContextUtil.getMapper(AlarmdetailMapper.class);
		  searchBean.setList(alarmdetailMapper.wxlist(bean));
		  ServletUtil.sendJsonBean(response, new MsgBean(searchBean));
	  }
	  
}