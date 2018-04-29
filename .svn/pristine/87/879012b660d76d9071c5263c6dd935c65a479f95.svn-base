package com.pg.util;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
/**
 * 定时任务1
 * 
 * @author xcr
 * 
 */
public class QuartzProductAlert implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {
    	/*try {
//    	        System.out.println("---------------QuartzProductAlert1-------------");
    		//聚合数据  账户 13450256178
        String url="http://web.juhe.cn:8080/finance/exchange/rmbquot?key=0930e66baddf07b3e724dfd7657d2a98";
        String result = SinaHttpConnect.get(url.toString());
	    JSONObject jsStr = JSONObject.fromObject(result);
	    RateMapper mapper = ApplicationContextUtil.getMapper(RateMapper.class);
	    RateBean bean = new RateBean();
	    if(jsStr.getInt("error_code")==0){
            JSONArray arr = (JSONArray) jsStr.get("result");
    	    for (Object object : arr) {
	    	JSONObject  jsonObject = (JSONObject) object;
	    	for (Iterator iter = jsonObject.keys(); iter.hasNext();) { //先遍历整个 people 对象  
        	    String key = (String)iter.next();  
        	    JSONObject data = jsonObject.getJSONObject(key);
		    	bean.setName(data.getString("name"));
		    	bean.setRate(Double.valueOf(data.getString("bankConversionPri"))/100);
		    	bean.setStatus(-1);
		    	bean.setPublishtime(data.getString("date")+" "+data.getString("time"));
				mapper.addrate(bean);//插入和更新
        	}  
		}
      }else{
  	    System.out.println("---------------QuartzProductAlert1-------error1------");
      }
	  //顺便刷新下一些缓存
	  ManageCache.wuliaoupdate=true;
	  ManageCache.getwuliaoByCustomid(0);//物料缓存  
	    
	    
	    
   } catch (Exception e) {
	    System.out.println("---------------QuartzProductAlert1---------error2----");
			e.printStackTrace();
   }*/
  }
}