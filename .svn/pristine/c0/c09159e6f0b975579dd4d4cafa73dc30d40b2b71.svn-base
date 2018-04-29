package com.pg.servlet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pg.bean.MsgBean;
import com.pg.mapper.FlowLogMapper;
import com.pg.mapper.FlowNodeMapper;
import com.pg.searchbean.FlowLogSearchBean;
import com.pg.searchbean.FlowNodeSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.Check;
import com.pg.util.Dict;
import com.pg.util.HttpAnno;
import com.pg.util.Message;
import com.pg.util.ServletUtil;
import com.pg.util.Tools;
/**
 * 工作流操作
 * @author XCR
 *
 */
@Controller
public class FlowCommonAction
{
	/**
	 * 公用审核  XCR
	 * @param request
	 * @param response
	 * @param bean :ids, updateStatus:1为审核成功 2为失败;statusnow:现在的状态;"hjjhelp",//表明;opinion备注；file：上传文件
	 */
	@HttpAnno(module="commonrule.htm",log=HttpAnno.UNLOG)
	@RequestMapping("common_audit.htm")
	public void common_audit(HttpServletRequest request, HttpServletResponse response,FlowNodeSearchBean bean) {
		ServletUtil.sendJsonBean(response, check(request, bean));
		return;
	}
	/**
	 * 基本审核 -- 其他业务审核调用此方法扩展
	 * 上一步 :自己的methodafter ，上一节点的 method
	 * 下一步:自己的methodafter .下一节点的 method
	 * isaudit:0 不能配置不能审核，1可以配置可以审核  2  只能自己审核  3 可以配置，但是不能审核  4、上一步不能审核  5下一步不能审核
	 * nodepre:：-1和0都是结束
	 * nodenext:：-1和0都是结束
	 * 
	 * @param response
	 * @param bean 
	 * @param beannow
	 * @param beanpreornext
	 * @return
	 */
	private static MsgBean check(HttpServletRequest request,FlowNodeSearchBean bean) {
		if((bean.getUpdateStatus()!=Dict.FLOEWLOG_1&&bean.getUpdateStatus()!=Dict.FLOEWLOG_2)||bean.getIds().size()==0)
			return new MsgBean("-1", "审核失败");
		FlowNodeMapper flowNodeMapper =ApplicationContextUtil.getMapper(FlowNodeMapper.class);
		FlowNodeSearchBean beannow = new FlowNodeSearchBean();//现节点
		//获得现在节点bean
		beannow = flowNodeMapper.getByTableNameAndStatus(bean.getStatusnow(),bean.getTablename());
		beannow.setFlag_int(bean.getFlag_int());//审核时候额外的参数
		beannow.setFlag_str(bean.getFlag_str());//审核时候额外的参数
		if(beannow==null){
			return new MsgBean("-1", "无此节点，审核失败");
		}
		if(beannow.getIsaudit()==Dict.ISAUDIT_0 || beannow.getIsaudit()==Dict.ISAUDIT_3){//Isaudit = 0和3 ；无需审核的，和可以配置，但是不能审核的
			return new MsgBean("-1", beannow.getDescriptionnow()+" 状态无需审核");
		}
		if(beannow.getIsaudit()==Dict.ISAUDIT_1 && checkRule(request, beannow)){//Isaudit = 1； 需要审核的，根据节点ID检查是否有操作权限
			return new MsgBean("-1", beannow.getDescriptionnow()+" 状态无权限审核");
		}
		if(bean.getUpdateStatus()==Dict.FLOEWLOG_1 &&   beannow.getIsaudit()==Dict.ISAUDIT_5){//Isaudit = 5； 下一步 + 下一步不能审核的
			return new MsgBean("-1", beannow.getDescriptionnow()+" 状态无需审核");
		}
		if(bean.getUpdateStatus()==Dict.FLOEWLOG_2 &&   beannow.getIsaudit()==Dict.ISAUDIT_4){//Isaudit = 4；上一步 + 上一步不能审核的
			return new MsgBean("-1", beannow.getDescriptionnow()+" 状态无需审核");
		}
		if(bean.getUpdateStatus()==Dict.FLOEWLOG_1 && beannow.getNodenext()==0){//下一步  + 结束节点的
			return new MsgBean("-1", beannow.getDescriptionnow()+" 状态不能手动操作到下一步");
		}
		if(bean.getUpdateStatus()!=Dict.FLOEWLOG_1 && (beannow.getNodepre()==0||beannow.getNodepre()==-1)){//上一步 和 首节点和结束节点的
			return new MsgBean("-1", beannow.getDescriptionnow()+" 状态不能回到上一步");
		}
		
		FlowNodeSearchBean beanpreornext = new FlowNodeSearchBean();//上一节点或下一节点
		for (int i = 0; i < bean.getIds().size(); i++) {
			HashMap<String, Object> hashMap= flowNodeMapper.getAllByTableName(Integer.valueOf(bean.getIds().get(i)), beannow.getTablename());//在修改前数据，用于审核失败时候发站内消息：获取改变成现在状态的人的id
			//检查状态是否是当前状态（可能未刷新页面就开始审核）
			if(bean.getStatusnow()!=Integer.valueOf(hashMap.get("status").toString())){
				return new MsgBean("-1", "状态不对，请先刷新页面再送审");
			}
			if(beannow.getIsaudit()==Dict.ISAUDIT_2){//Isaudit = 2：只能自己审核
				if(Check.getEmployee(request).getId()!=Integer.valueOf(hashMap.get("employid").toString())){
					return new MsgBean("-1", "编号："+hashMap.get("code")+"只能是录入人审核");
				}
			}
			if(bean.getUpdateStatus()==Dict.FLOEWLOG_1){//成功
				beanpreornext = flowNodeMapper.getById(beannow.getNodenext());//下一步
				beanpreornext.setRollback("n");
				
			}else if(bean.getUpdateStatus()==Dict.FLOEWLOG_2) {//失败
				beanpreornext = flowNodeMapper.getById(beannow.getNodepre());//上一步
				beanpreornext.setRollback("y");//审核失败回滚
			}
			
				//状态修改前需要做的操作：现在的Methodafter
				String methodafter = beannow.getMethodafter();
				if(methodafter!=null && !"".equals(methodafter) && methodafter.length()!=0){
					try {
					//操作成为状态前	
					Class<?> c=Class.forName(methodafter.substring(0, methodafter.indexOf("#")));
		        	Object obj=c.newInstance();
		        	java.lang.reflect.Method method = c.getMethod(methodafter.substring(methodafter.indexOf("#")+1,methodafter.length()),  new Class[]{HttpServletRequest.class,Integer.class,Integer.class,FlowNodeSearchBean.class,FlowNodeSearchBean.class});
		        	beanpreornext =(FlowNodeSearchBean) method.invoke(obj, new Object[]{request,Integer.valueOf(bean.getIds().get(i)),bean.getUpdateStatus(),beanpreornext,beannow});
					}catch (Exception e) {
						e.printStackTrace();
						return new MsgBean("-1", "实例化状态后方法错误："+methodafter);
					}
				}
				if(beanpreornext.getErrorinfo()!=null && !"".equals(beanpreornext.getErrorinfo())){
					return new MsgBean("-1", beanpreornext.getErrorinfo());//特殊方法抛出的错误
				}
				
				//修改成某个状态前需要做的操作：下一步的Method
				String methodString =	beanpreornext.getMethod();
				if(methodString!=null && !"".equals(methodString) && methodString.length()!=0){
					try {
					//操作成为状态前	
					Class<?> c=Class.forName(methodString.substring(0, methodString.indexOf("#")));
		        	Object obj=c.newInstance();
		        	java.lang.reflect.Method method = c.getMethod(methodString.substring(methodString.indexOf("#")+1,methodString.length()),  new Class[]{HttpServletRequest.class,Integer.class,Integer.class,FlowNodeSearchBean.class,FlowNodeSearchBean.class});
		        	beanpreornext =(FlowNodeSearchBean) method.invoke(obj, new Object[]{request,Integer.valueOf(bean.getIds().get(i)),bean.getUpdateStatus(),beanpreornext,beannow});
					}catch (Exception e) {
						e.printStackTrace();
						return new MsgBean("-1", "实例化状态前方法错误："+methodString);
					}
				}
			
			
			if(beanpreornext.getErrorinfo()!=null && !"".equals(beanpreornext.getErrorinfo())){
				return new MsgBean("-1", beanpreornext.getErrorinfo());//特殊方法抛出的错误
			}
			beanpreornext.setId(Integer.valueOf(bean.getIds().get(i)));
			beanpreornext.setEmployid_base(Check.getEmployee(request).getId());
			
			if(flowNodeMapper.updateTableStatus(beanpreornext)<1)return new MsgBean("-1", "审核失败");
			//填写工作流日志
			if(beannow.getIsflowlog()==1){
				writelog(request, bean, beannow, beanpreornext,Integer.valueOf(bean.getIds().get(i)));
			}
			 //发送站内消息
			if(beannow.getIsmessage()==1){
				sendMessage(request, beannow,beanpreornext,flowNodeMapper,bean.getUpdateStatus(),Integer.valueOf(bean.getIds().get(i)),hashMap); 
			}
				
		}
		return new MsgBean("0", "审核成功");
	}
	/**
	 * 工作流审核后发送提醒消息
	 * @param request
	 * @param beannow
	 * @param flowNodeMapper
	 * @param getUpdateStatus
	 * @param id
	 */
	private static void sendMessage(HttpServletRequest request,FlowNodeSearchBean beannow,FlowNodeSearchBean beannext,FlowNodeMapper flowNodeMapper,Integer getUpdateStatus,Integer id,HashMap<String, Object> fali_hashMap) {
		//根据tablename 查出 送审的职员id
		@SuppressWarnings("unchecked")
		HashMap<String, Object> hashMap= flowNodeMapper.getAllByTableName(id, beannow.getTablename());
		if(hashMap!=null){
//			String rollback =  hashMap.get("rollback").toString();
			//不管上一步还是下一步，都要通知提交人。
			hashMap.put("remark", beannow.getRemark());
			hashMap.put("status", beannow.getDescriptionnow());
			hashMap.put("link", beannow.getLinkurl()+beannow.getStatusnow());
			if(getUpdateStatus==Dict.FLOEWLOG_1){//下一步
					//通知提交人
					Message.send("ordersuccess", Integer.valueOf(fali_hashMap.get("submitpersonid").toString()), hashMap);
			}else if(getUpdateStatus==Dict.FLOEWLOG_2) {//上一步
				//通知提交人
				Message.send("orderfail", Integer.valueOf(fali_hashMap.get("submitpersonid").toString()), hashMap);
			}
			
			//使用下一节点的信息
			hashMap.put("remark", beannext.getRemark());
			hashMap.put("status", beannext.getDescriptionnow());
			hashMap.put("link", beannext.getLinkurl()+beannext.getStatusnow());
			//通知下一个要审核的人，需要审核了
			//服务订单和销售订单 给指定人采购，beannow.getFlag_int() 参数存放的是指定采购人的id
			if(beannow.getFlag_int()!=null && ("hjjorderservice".equals(beannow.getTablename()) || "hjjordersale".equals(beannow.getTablename()) )){
				Message.send("ordercheck", beannow.getFlag_int(), hashMap);
			}else{//非指派
				if(beannext.getIsaudit()==Dict.ISAUDIT_1 || (getUpdateStatus==Dict.FLOEWLOG_1 && beannow.getIsaudit()==Dict.ISAUDIT_4)  || (getUpdateStatus==Dict.FLOEWLOG_2 && beannow.getIsaudit()==Dict.ISAUDIT_5)){//他人审核  为4的时候，是下一步的；为5 的时候，是上一步的
					Set<String> set = Tools.getAllEmployeeids(beannext.getRoleid(), beannext.getEmployeeid());//获得employeeids的set
			        Iterator i = set.iterator();//先迭代出来  
				    while(i.hasNext()){//遍历  
				       Message.send("ordercheck", Integer.valueOf((String)i.next()), hashMap);
				    }  
				}else if(beannext.getIsaudit()==Dict.ISAUDIT_2){//录入人审核的
					   Message.send("ordercheck", Integer.valueOf(hashMap.get("employid").toString()), hashMap);
				}
			}
			
		}
		return;
	}
	/***
	 * 根据节点ID检查是否有操作权限
	 * @param request
	 * @param response
	 * @param nodeid 节点id
	 * @return true 为无权限
	 */
	public static Boolean checkRule(HttpServletRequest request,FlowNodeSearchBean beannow) {
		if(beannow.getId()==null)return true;
		HashMap<String, FlowNodeSearchBean> flownodemap = Check.getFlowNodeMap(request);
		FlowNodeSearchBean flowNodeSearchBean =flownodemap.get(beannow.getId().toString());
		if(flowNodeSearchBean!=null){
			Set<String> set = Tools.getAllEmployeeids(beannow.getRoleid(), beannow.getEmployeeid());//获得employeeids的set
	        Iterator i = set.iterator();//先迭代出来  
	        while(i.hasNext()){//遍历  
	            if(Check.getEmployee(request).getId()==Integer.valueOf((String)i.next())){
	            	return false;
	            }
	        }  
		}      
	   return true;
	}
	
	/***
	 * 填写工作流日志
	 * @param request 
	 * @param bean 页面的传过来的bean。包含参数：ids;statusnow:现在的状态;//表明;opinion备注；file：上传文件
	 * @param beannow 当前节点bean
	 * @param beanpreornext 修改后节点bean
	 * @return
	 */
	private static Integer writelog(HttpServletRequest request,FlowNodeSearchBean bean,FlowNodeSearchBean beannow,FlowNodeSearchBean beanpreornext,Integer id) {
		FlowNodeMapper flowNodeMapper =ApplicationContextUtil.getMapper(FlowNodeMapper.class);
		FlowLogMapper flowLogMapper =ApplicationContextUtil.getMapper(FlowLogMapper.class);
		FlowLogSearchBean flowLogSearchBean = new FlowLogSearchBean();
		flowLogSearchBean.setStatus(bean.getUpdateStatus());//成功 或 失败
		flowLogSearchBean.setEmployeeid(Check.getEmployee(request).getId());//审核人id
		flowLogSearchBean.setOpinion(bean.getOpinion());//审核备注
		flowLogSearchBean.setFile(bean.getFile());//审核附件
		flowLogSearchBean.setTablename(beannow.getTablename());//关联表名
		flowLogSearchBean.setTableid(id);
		flowLogSearchBean.setStatusnow(beannow.getStatusnow());//现在状态
		flowLogSearchBean.setNodenow(beannow.getId());//现在节点id
		flowLogSearchBean.setStatusnext(beanpreornext.getStatusnow());//下一步或者上一部节点状态
		flowLogSearchBean.setNodenext(beanpreornext.getId());//下一步或者上一部节点id
		flowLogSearchBean.setIssys(beannow.getIssys());//节点是否为系统节点
		flowLogSearchBean.setLinkurl(beannow.getLinkurl());//节点链接
		flowLogSearchBean.setRemark(beannow.getRemark());//节点备注
		flowLogSearchBean.setDescriptionnow(beannow.getDescriptionnow());
		flowLogSearchBean.setFlowid(beannow.getFlowid());
		flowLogSearchBean.setMethod(beannow.getMethod());
		flowLogSearchBean.setIsmessage(beannow.getIsmessage());
		flowLogSearchBean.setIsreturn(bean.getUpdateStatus());
		if(beannow.getValuetemplate()!=""&&beannow.getValuetemplate()!=null){
			//插入模版的值,根据id 查询表 得到map，过滤！
			@SuppressWarnings("unchecked")
			HashMap<String, Object> map = flowNodeMapper.getAllByTableName(id,beannow.getTablename());
			String valueafter =Tools.formatContent(beannow.getValuetemplate(),map);//替换值
			flowLogSearchBean.setValue(valueafter);
		}
		Integer resultInteger =	flowLogMapper.addFlowLog(flowLogSearchBean);
		return resultInteger;
	}
	
	/**
	 * 公用添加工作流日志
	 * @param request
	 * @param bean  工作流bean
	 * @param opinion  意见
	 * @param file   附件
	 * @param status   状态  0首次提交 1审核通过 2审核不通过
	 * @param isreturn  1下一步，2上一步
	 * @param  id   订单ID
	 * @return
	 */
	public static Integer addlog(HttpServletRequest request,FlowNodeSearchBean bean,String opinion,String file,Integer status,Integer isreturn,Integer id) {
		FlowNodeMapper flowNodeMapper =ApplicationContextUtil.getMapper(FlowNodeMapper.class);
		FlowLogMapper flowLogMapper =ApplicationContextUtil.getMapper(FlowLogMapper.class);
		FlowLogSearchBean flowLogSearchBean = new FlowLogSearchBean();
		flowLogSearchBean.setStatus(status);//成功 或 失败
		flowLogSearchBean.setEmployeeid(Check.getEmployee(request).getId());//审核人id
		flowLogSearchBean.setOpinion(opinion);//审核备注
		flowLogSearchBean.setFile(file);//审核附件
		flowLogSearchBean.setTablename(bean.getTablename());//关联表名
		flowLogSearchBean.setTableid(id);
		flowLogSearchBean.setStatusnow(bean.getStatusnow());//现在状态
		flowLogSearchBean.setNodenow(bean.getId());//现在节点id
		flowLogSearchBean.setStatusnext(bean.getStatusnow());//下一步或者上一部节点状态
		flowLogSearchBean.setNodenext(bean.getId());//下一步或者上一部节点id
		flowLogSearchBean.setIssys(bean.getIssys());//节点是否为系统节点
		flowLogSearchBean.setLinkurl(bean.getLinkurl());//节点链接
		flowLogSearchBean.setRemark(bean.getRemark());//节点备注
		flowLogSearchBean.setDescriptionnow(bean.getDescriptionnow());
		flowLogSearchBean.setFlowid(bean.getFlowid());
		flowLogSearchBean.setMethod(bean.getMethod());
		flowLogSearchBean.setIsmessage(bean.getIsmessage());
		flowLogSearchBean.setIsreturn(isreturn);
		if(bean.getValuetemplate()!=""&&bean.getValuetemplate()!=null){
			//插入模版的值,根据id 查询表 得到map，过滤！
			@SuppressWarnings("unchecked")
			HashMap<String, Object> map = flowNodeMapper.getAllByTableName(id,bean.getTablename());
			String valueafter =Tools.formatContent(bean.getValuetemplate(),map);//替换值
			flowLogSearchBean.setValue(valueafter);
		}
		Integer resultInteger =	flowLogMapper.addFlowLog(flowLogSearchBean);
		return resultInteger;
	}
	
	
	/**
	 * 公用方法：根据表名和现在状态获得下一个状态的bean
	 * @param request
	 * @param id
	 */
	public static FlowNodeSearchBean getnextFlowNode(Integer status,String tablename) {
		FlowNodeMapper flowNodeMapper =ApplicationContextUtil.getMapper(FlowNodeMapper.class);
		FlowNodeSearchBean beannow = flowNodeMapper.getByTableNameAndStatus(status, tablename);
		if(beannow==null)return null;
		return flowNodeMapper.getById(beannow.getNodenext());
	}
}

