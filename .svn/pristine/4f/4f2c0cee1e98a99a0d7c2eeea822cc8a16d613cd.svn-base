package com.pg.util;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
/**
 * 定时任务2
 * 
 * @author xcr
 * 
 */
public class QuartzProductAlert2 implements Job {


    public void execute(JobExecutionContext context) throws JobExecutionException {
  /*     
//    	System.out.println("----------------QuartzProductAlert2------------");
        ProductSearchBean bean = new ProductSearchBean();
        ProductMapper mapper = ApplicationContextUtil.getMapper(ProductMapper.class);
        FlowNodeMapper flowNodeMapper = ApplicationContextUtil.getMapper(FlowNodeMapper.class);
        FlowMapper FlowMapper = ApplicationContextUtil.getMapper(FlowMapper.class);
        FlowSearchBean FlowSearchBean = FlowMapper.getByFlowKey(Dict.PRODUCT_DATE);
        Set<String> set = Tools.getAllEmployeeids(FlowSearchBean.getRoleid(), FlowSearchBean.getEmployeeid());//获得employeeids的set
        bean.setStatus(3);
        bean.setLimitFlag(false);
        bean.setTime_false(1);  //不为空时只查询预警时间和有效时间 值不能为的产品
        List<ProductSearchBean> list = mapper.list(bean);
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        Date dNow = new Date();   //当前时间
        Calendar calendar = Calendar.getInstance(); //得到日历
        
        String codesString ="";
        Integer numInteger =0;
        for (int i = 0; i < list.size(); i++) {
        	try {
        		  bean = list.get(i);
        		 if(list.get(i).getValiditytime()==null || "".equals(list.get(i).getValiditytime()))
        			 continue;
				 Date date = sdf.parse(list.get(i).getValiditytime());   //有效日期
				 Date dBefore = new Date();
			     calendar.setTime(date);//把当前时间赋给日历
			     calendar.add(Calendar.DAY_OF_MONTH, -list.get(i).getTimevalue());  //设置多少天前
			     dBefore = calendar.getTime();   //得到多少天前的时间              预警日期
			     boolean flag = dBefore.before(dNow);
			     if(flag){
			    	 numInteger++;
			    	 codesString = codesString +"<a  target=\"_blank\" href=\"product.htm?operate=list&code="+bean.getCode()+"\" >"+bean.getCode()+"</a> "+" ,";
			     }
			} catch (Exception e) {
//				System.out.println("---------------QuartzProductAlert2-------error------");
//				e.printStackTrace();
			}
		}
        
        if(!"".equals(codesString)){
        	HashMap<String, Object> hashMap= new HashMap();
        	  Iterator i1 = set.iterator();//先迭代出来  
        	  hashMap.put("num", numInteger);
        	  hashMap.put("code", codesString);
//        	  hashMap.put("link", "product.htm?operate=list&codes="+codesString);
	           while(i1.hasNext()){//遍历  
	            	Message.send("producttime", Integer.valueOf((String)i1.next()), hashMap);
	            }
        	
        }*/
        
    }

   
}