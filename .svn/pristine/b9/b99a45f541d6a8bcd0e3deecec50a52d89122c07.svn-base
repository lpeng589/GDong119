package com.pg.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.pg.gzh.util.BaseResult;
import com.pg.gzh.util.HttpRequest;
import com.pg.mapper.ConfigMapper;
import com.pg.mapper.EmployeeMapper;
import com.pg.mapper.EventMapper;
import com.pg.mapper.FlowLogMapper;
import com.pg.mapper.FlowMapper;
import com.pg.mapper.FlowNodeMapper;
import com.pg.mapper.P_eventlogMapper;
import com.pg.searchbean.EmployeeSearchBean;
import com.pg.searchbean.EventSearchBean;
import com.pg.searchbean.F_departmentSearchBean;
import com.pg.searchbean.FlowLogSearchBean;
import com.pg.searchbean.FlowNodeSearchBean;
import com.pg.searchbean.P_eventlogSearchBean;
import com.pg.servlet.FlowCommonAction;
import com.pg.xcx.util.GZHSetup;

public class Tools {
	
	   private static final char a[] = {
	        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
	        'a', 'b', 'c', 'd', 'e', 'f'
	    };
	    //密文再加密
	    public static final String bytes2HexStr(byte abyte0[])
	    {
	        StringBuffer stringbuffer = new StringBuffer(abyte0.length * 2);
	        for(int i = 0; i < abyte0.length; i++)
	        {
	            stringbuffer.append(a[abyte0[i] >>> 4 & 15]);
	            stringbuffer.append(a[abyte0[i] & 15]);
	        }
	        return stringbuffer.toString();
	    }
	    //密文再解密
	    public static final byte[] hexStr2Bytes(String s)
	    {
	        byte abyte0[] = new byte[s.length() / 2];
	        for(int i = 0; i < abyte0.length; i++)
	            abyte0[i] = (byte)Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
	        return abyte0;
	    }
	   
		public static void decryptPasskey() {
			//加密
			String passText = "123456";
			new Crypto();
			String passCiper = Crypto.encrypt(passText);
			System.out.println("原文文："+passText);
			System.out.println("加密密文："+passCiper);
			
			//解密
			String passKey = "7d442eeafbd404bffab1d5e18221730cc07d3f935cb3d1a0aa5e8eb77cc0089c18e020f40b819b97ed0b80b32cf0fbd1";
			String pass = Crypto.decrypt(passKey);
			System.out.println("密文："+passKey);
			System.out.println("解密原文："+pass);
		}
	    
	/**
	 * 消息模板内容匹配
	 * @param map
	 * @param content
	 * @return
	 */
	public static String getMatch(Map<String, Object> map,String content) {
		if(map==null)return content;
		StringBuilder sb = new StringBuilder(content);
		Pattern p = Pattern.compile("#\\{(.*?)\\}");
		Matcher m = p.matcher(content);
		int offset = 0;
		while (m.find()) {
			Object tmpObject  = map.get(m.group(1));
			String tmp = "";
			if (tmpObject != null){
				tmp = tmpObject.toString();
			}
			sb.replace(m.start() + offset, m.end() + offset, tmp);
			offset += tmp.length() - (m.end() - m.start());
		}
		return sb.toString();
	}
	
	public static String MD5(String source) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source.getBytes());
			byte[] b = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte c : b) {
				int val = ((int) c) & 0xff;
				if (val < 16)
					sb.append("0");
				sb.append(Integer.toHexString(val));
			}
			return sb.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 过滤掉utf8mb4
	 * @param text
	 * @return
	 */
	 public static String filterOffUtf8Mb4(String text){	
		try {
			byte[] bytes;
			bytes = text.getBytes("utf-8");
			ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
			int i = 0;
			while (i < bytes.length) {
				short b = bytes[i];
				if (b > 0) {
					buffer.put(bytes[i++]);
					continue;
				}
				b += 256;
				if ((b ^ 0xC0) >> 4 == 0) {
					buffer.put(bytes, i, 2);
					i += 2;
				} else if ((b ^ 0xE0) >> 4 == 0) {
					buffer.put(bytes, i, 3);
					i += 3;
				} else if ((b ^ 0xF0) >> 4 == 0) {
					i += 4;
				}
			}
			buffer.flip();
			return new String(buffer.array(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";	
	}
	 
	/*
	* 判断输入的字符串是否是空
	* 
	* */
	public static boolean isNullOrEmpty(String inStr) {
	       return (inStr == null || inStr.trim().length() == 0);
	} 
	
	/**
	 * 根据roleid的字符串 和 employeeid 的字符串 查出所有职员 id。过滤掉重复的。
	 * 返回一个职员list
	 * @param roleid
	 * @param employeeid
	 * @author XCR
	 * @return
	 */
	public static Set<String> getAllEmployeeids(String roleid,String employeeid) {
	    Set<String>  set = new HashSet<String>(); //建立 
		if(roleid!=null && !roleid.equals("") ){//
			String roles[] = roleid.split(",");
			EmployeeMapper employMapper =ApplicationContextUtil.getMapper(EmployeeMapper.class);
			EmployeeSearchBean employeeSearchBean = new EmployeeSearchBean();
			for (int i = 0; i < roles.length; i++) {
				List<EmployeeSearchBean> result=employMapper.getEmployeeidByRoleid(","+roles[i]+",");
				for (int j = 0; j < result.size(); j++) {
					employeeSearchBean = result.get(j);
				    set.add(employeeSearchBean.getId().toString());
				}
			}
		}
		if(employeeid!=null && !employeeid.equals("") ){
			String employees[] =  employeeid.split(",");
			String employee = null;
			for (int i = 0; i < employees.length; i++) {
				employee = employees[i];
				set.add(employee);
//				System.out.println("employee"+i+":"+employee);
			}
			
		}
	    return set;
	} 

	/**
	 * 把bean转化成map
	 * @param obj
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> changeBeanToMap(Object obj) {
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);

					map.put(key, value);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
			
	
	/**
	 * 把bean转化成map
	 * @param obj
	 * @return String
	 * @author XCR
	 */
	public static String changeBeanToString(Object obj, String logname) {
		if (obj == null)return null;
		if (logname == null || logname.length()==0  || "".equals(logname))return "";
		String [] s = logname.split(",");
		String string="";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);

					map.put(key, value);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < s.length; i++) {
			if(map.get(s[i])!=null && !"".equals(map.get(s[i]))){
				string = string+s[i]+":"+map.get(s[i])+";   ";
			}
		}
		return string;
	}
	
	/*
	* 生成库存编号
	* 
	* */
	public static String getStockCode() {
        Random t= new Random();
        int x= t.nextInt(899999999)+1000; 
	    return String.valueOf(x);
	}   
	
	/**
	 * 送审插入工作量日志
	 * @param updateStatus 审核成功1或失败2
	 * @param ids  批量送审
	 * @param flowkey  工作流key
	 * @param employeeid 职员id
	 * @param opinion  备注
	 * @param file 文件
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static  void  addFlowLog(List<String> ids,String tablename,Integer statusnow ,HttpServletRequest request, String  opinion, String  file ,Integer flowlog_status) {
		FlowMapper flowMapper =ApplicationContextUtil.getMapper(FlowMapper.class);
//		FlowSearchBean flowBean = flowMapper.getByFlowKey(flowkey);//根据flowkey 查出 Firstnodeid
		FlowNodeMapper flowNodeMapper =ApplicationContextUtil.getMapper(FlowNodeMapper.class);
//		FlowNodeSearchBean beannow = flowNodeMapper.getById(flowBean.getFirstnodeid());
		FlowNodeSearchBean beannow = flowNodeMapper.getByTableNameAndStatus(statusnow,tablename);
		
		if(beannow==null) return;
		FlowNodeSearchBean beanpreornext = flowNodeMapper.getById(beannow.getNodenext());//下一步
		if(beanpreornext==null) return;
		FlowLogMapper flowLogMapper =ApplicationContextUtil.getMapper(FlowLogMapper.class);
		FlowLogSearchBean flowLogSearchBean = new FlowLogSearchBean();
		for (int i = 0; i < ids.size(); i++) {
			flowLogSearchBean.setStatus(flowlog_status);
			flowLogSearchBean.setEmployeeid(Check.getEmployee(request).getId());//审核人id
			flowLogSearchBean.setOpinion(opinion);//审核备注
			flowLogSearchBean.setFile(file);//审核附件
			flowLogSearchBean.setTablename(beannow.getTablename());//关联表名
			flowLogSearchBean.setTableid(Integer.valueOf(ids.get(i)));
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
			flowLogSearchBean.setIsreturn(1);//下一步
			if(beannow.getValuetemplate()!=""&&beannow.getValuetemplate()!=null){
				//插入模版的值 ,根据id查询得到map，过滤！
				HashMap map = flowNodeMapper.getAllByTableName(Integer.valueOf(ids.get(i)),beannow.getTablename());
				String valueafter =formatContent(beannow.getValuetemplate(),map);//替换值
				flowLogSearchBean.setValue(valueafter);
			}
			flowLogMapper.addFlowLog(flowLogSearchBean);
		}
	    return ;
	} 
	
	
	/**
	 * 格式化消息内容
	 * 
	 * @param 把#{XXX}对应map内容换掉
	 * @param content 带属性的字符串
	 * @return
	 */
	public static String formatContent(String  content,Map<String, Object> mp) {
		if (content == null)
			return null;
		if(mp==null)return content;
		StringBuilder sb = new StringBuilder(content);
		Pattern p = Pattern.compile("#\\{(.*?)\\}");
		Matcher m = p.matcher(content);
		int offset = 0;
		String parString = "";
		while (m.find()) {
			parString =	content.substring(m.start() +2,  m.end() -1)	;
			if( mp.get(parString)!=null && !"".equals(mp.get(parString).toString())){
				sb.replace(m.start() + offset, m.end() + offset, mp.get(parString).toString());
			    offset += mp.get(parString).toString().length() - (m.end() - m.start());
			}
			
		}
		return sb.toString();
	}
	/**
	 * 
	 * @param tableName  表名
	 * @param head   编码头部
	 * @param timeFlag 是否需要日期
	 * @param bodyLength 序列号长度
	 * @return
	 */
	public static String getCode(String tableName,String head,boolean timeFlag,int bodyLength) {
        String time = "";
		if(timeFlag){
			time=new SimpleDateFormat("yyMMdd").format(new Date());
		}
		int body =1;
		String headCond = head;
		if(head!=null&&!head.equals("")){
			headCond=head+"%";
		}
		String maxCode =  ApplicationContextUtil.getMapper(ConfigMapper.class).getMaxCode(tableName,headCond);
		if(maxCode!=null&&(timeFlag&&maxCode.substring(head.length(),head.length()+6).equals(time)||!timeFlag)){
        	body = Integer.valueOf(maxCode.substring(maxCode.length()-bodyLength))+1;
        }
        String code ="0000000000"+body;
	    return head+time+code.substring(code.length()-bodyLength,code.length());
	}
	/**
	 * 
	 * @param tableName  表名
	 * @param head   编码头部
	 * @param timeFlag 是否需要日期
	 * @param bodyLength 序列号长度
	 * @return
	 */
	public static String getCode2(String maxCode,String head,boolean timeFlag,int bodyLength,Integer k) {
        String time = "";
		if(timeFlag){
			time=new SimpleDateFormat("yyMMdd").format(new Date());
		}
		int body =1;
		String headCond = head;
		if(head!=null&&!head.equals("")){
			headCond=head+"%";
		}
		if(maxCode!=null&&(timeFlag&&maxCode.substring(head.length(),head.length()+6).equals(time)||!timeFlag)){
        	body = Integer.valueOf(maxCode.substring(maxCode.length()-bodyLength))+k;
        }
        String code ="0000000000"+body;
	    return head+time+code.substring(code.length()-bodyLength,code.length());
	}
	/**
	 * 根据主项ID，获得子项的code
	 * @param tableName  表名
	 * @param head   编码头部
	 * @param timeFlag 是否需要日期
	 * @param bodyLength 序列号长度
	 * @return
	 */
	public static String getCodeById(String maintableName,String tableName,Integer id,Integer bodyLength) {
		if(id==null||maintableName==null||tableName==null|| bodyLength==null){
			return null;
		}
		int body =1;
		String code =  ApplicationContextUtil.getMapper(ConfigMapper.class).getCodeById(maintableName,id);
//		String code="KC1606010001";
		String maxCode =  ApplicationContextUtil.getMapper(ConfigMapper.class).getMaxlikeCode(tableName,code);
//		String maxCode = "KC1606010001-001";
		if(maxCode!=null){
        	body = Integer.valueOf(maxCode.substring(code.length()+1))+1;
        }
		String bodycode ="0000000000"+body;
	    return code+"-"+bodycode.substring(bodycode.length()-bodyLength,bodycode.length());
	}
	/*
	* 获得随机数
	* 
	* */
	public static String getRandomCode() {
        Random t= new Random();
        int x= t.nextInt(899999); 
	    return String.valueOf(x);
	} 
	/**
	 * 过滤字符串
	 * @param str  字符串
	 * @param regEx  需要过滤的
	 * @return
	 */
	public   static   String StringFilter(String   str,String regEx)        {     
	  if(str==null)return str;
	  Pattern   p   =   Pattern.compile(regEx);     
	  Matcher   m   =   p.matcher(str);     
	  return   m.replaceAll("").trim();     
    }
	
	/*
	* 工作流送审发消息
	* 
	* */
	public static void sendMessageWhenAudit(Integer statusnow,String tablename,String messagemodel,List<String>  ids) {
		FlowNodeMapper flowNodeMapper =ApplicationContextUtil.getMapper(FlowNodeMapper.class);
		FlowNodeSearchBean beannow = flowNodeMapper.getByTableNameAndStatus(statusnow,tablename);
		if(beannow==null)return;
		for (int i = 0; i < ids.size(); i++) {
			HashMap<String, Object> hashMap= flowNodeMapper.getAllByTableName(Integer.valueOf(ids.get(i)), beannow.getTablename());
			if(hashMap!=null){
				hashMap.put("remark", beannow.getRemark());
				hashMap.put("status", beannow.getDescriptionnow());
				hashMap.put("link", beannow.getLinkurl()+beannow.getStatusnow());
				Set<String> set = Tools.getAllEmployeeids(beannow.getRoleid(), beannow.getEmployeeid());//获得employeeids的set
		        Iterator iterator = set.iterator();//先迭代出来  
		        while(iterator.hasNext()){//遍历  
		            Message.send(messagemodel, Integer.valueOf((String)iterator.next()), hashMap);
		        }   
				
			}
			
			
		}
	    return ;
	} 
	
	/***
	 * 根据ID和表名，检查是否有操作权限
	 * @return true 为无权限
	 */
	public static Boolean checkRuleByIdAndTable(HttpServletRequest request,String tablename,Integer id) {
		if(tablename==null||"".equals(tablename))return true;
		if(id==null)return true;
		FlowNodeMapper flowNodeMapper =ApplicationContextUtil.getMapper(FlowNodeMapper.class);
		HashMap<String, Object> hashMap= flowNodeMapper.getAllByTableName(id, tablename);
		if(hashMap==null)return true;
		Integer status =  Integer.valueOf(hashMap.get("status").toString());
		FlowNodeSearchBean beannow = flowNodeMapper.getByTableNameAndStatus(status,tablename);	
		if(beannow==null)return true;		
	    return FlowCommonAction.checkRule( request, beannow);
	}
	
	 /** 
     * 把金额阿拉伯数字转换为汉字表示，小数点后四舍五入保留两位 
     * 还有一种方法可以在转换的过程中不考虑连续0的情况，然后对最终的结果进行一次遍历合并连续的零 
     */  
    public static String [] ChineseNum = new String[]{"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};  
    public static String NumToChinese(double num){  
        if(num > 99999999999999.99 || num < -99999999999999.99)  
            throw new IllegalArgumentException("参数值超出允许范围 (-99999999999999.99 ～ 99999999999999.99)！");  
        boolean negative = false;//正负标号  
        if(num<0){  
            negative = true;  
            num = num*(-1);  
        }  
        long temp = Math.round(num*100);  
        int numFen=(int)(temp%10);//分  
        temp=temp/10;  
        int numJiao = (int)(temp%10);//角  
        temp=temp/10;  
        //此时temp只包含整数部分  
        int [] parts =new int[20];//将金额整数部分分为在0-9999之间数的各个部分  
        int numParts = 0;//记录把原来金额整数部分分割为几个部分   
        for(int i=0;;i++){  
            if(temp == 0)  
                break;  
            int part = (int)(temp%10000);  
            parts[i] =part;  
            temp = temp/10000;  
            numParts++;  
        }  
        boolean beforeWanIsZero = true;//标志位，记录万的下一级是否为0  
        String chineseStr = "";  
        for(int i=0;i<numParts;i++){  
            String partChinese = partConvert(parts[i]);  
            if(i%2==0){  
                if("".equals(partChinese))  
                    beforeWanIsZero = true;  
                else  
                    beforeWanIsZero = false;  
            }  
            if(i!=0){  
                if(i%2==0)//亿的部分  
                    chineseStr = "亿"+chineseStr;  
                else{  
                    if("".equals(partChinese)&&!beforeWanIsZero)// 如果“万”对应的 part 为 0，而“万”下面一级不为 0，则不加“万”，而加“零”  
                        chineseStr = "零"+chineseStr;  
                    else{  
                        if(parts[i-1]<1000&&parts[i-1]>0)//如果万的部分不为0，而万前面的部分小于1000大于0，则万后面应该跟零  
                            chineseStr = "零"+chineseStr;  
                        chineseStr = "万"+chineseStr;  
                    }  
                }  
            }  
            chineseStr = partChinese + chineseStr;  
        }  
        if("".equals(chineseStr))//整数部分为0，则表示为零元  
            chineseStr = ChineseNum[0];  
        else if(negative)//整数部分部位0，但是为负数  
            chineseStr = "负" +chineseStr;  
        chineseStr = chineseStr + "元";  
        if(numFen==0&&numJiao==0){  
            chineseStr = chineseStr +"整";  
        }  
        else if(numFen==0){//0分  
            chineseStr = chineseStr +ChineseNum[numJiao] + "角";  
        }  
        else{  
            if(numJiao==0)  
                chineseStr = chineseStr + "零" + ChineseNum[numFen] + "分";  
            else  
                chineseStr = chineseStr + ChineseNum[numJiao] + "角" + ChineseNum[numFen] + "分";  
        }  
        return chineseStr;  
    }  
    //转换拆分后的每个部分，0-9999之间  
    public static String partConvert(int partNum){  
        if(partNum<0||partNum>10000){  
            throw new IllegalArgumentException("参数必须是大于等于0或小于10000的整数");  
        }  
        String [] units = new String[]{"","拾","佰","仟"};  
        int temp = partNum;  
        String partResult = new Integer(partNum).toString();  
        int partResultLength = partResult.length();  
        boolean lastIsZero = true;//记录上一位是否为0  
        String chineseStr = "";  
        for(int i=0;i<partResultLength;i++){  
            if(temp == 0)//高位无数字  
                break;  
            int digit = temp%10;  
            if(digit == 0){  
                if(!lastIsZero)//如果前一个数字不是0则在当前汉字串前加零  
                    chineseStr = "零"+chineseStr;  
                lastIsZero = true;  
            }  
            else{  
                chineseStr = ChineseNum[digit] + units[i] +chineseStr;  
                lastIsZero = false;  
            }  
            temp =temp/10;  
        }  
        return chineseStr;    
    }  
    
    //根据表明和状态，获得在工作流表的 状态描述
    public static String getNameByStatusAndTablename(HttpServletRequest request,Integer status, String tablename){
    	if(status==null || "".equals(status))return "";
    	if(tablename==null || "".equals(tablename))return "";
	    HashMap<String, FlowNodeSearchBean>  map = Check.getFlownodebyStaAndTableAndId(request);
	    FlowNodeSearchBean flowNodeSearchBean = map.get(tablename+status);
	    if(flowNodeSearchBean==null) return "";
        return flowNodeSearchBean.getDescriptionnow();    
    }  

	/**
	 * 假如全部是数字，则转为正常数字（非科学计算）
	 * 非数字的返回
	 * @param code
	 * @return
	 */
	public static String formatNum(String code){
		if(code==null || "".equals(code))return "";
		
		if(!code.contains(".")){
			return code;
		}
		BigDecimal db = new BigDecimal(code);
	    return db.toPlainString();    
	}
	
	// 判断一个字符是否是中文
	public static boolean isChinese(char c) {
	      return c >= 0x4E00 &&  c <= 0x9FA5;// 根据字节码判断
	}
	// 判断一个字符串是否含有中文
	public static boolean isChinese(String str) {
	    if (str == null) return false;
	    for (char c : str.toCharArray()) {
	        if (isChinese(c)) return true;// 有一个中文字符就返回
	    }
	    return false;
	}
	
	/**
	 * 发送模板消息 
	 * @param key  
	 * @param json  模板bean内容json
	 * @return
	 */
	public static BaseResult send(String json) {
		String key=GZHSetup.register(Config.getValue("wxcxappid"), Config.getValue("wxcxsecret"));
//		System.out.println("GZHSetup.getToken(key)-------------"+GZHSetup.getToken(key));
		String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="+GZHSetup.getToken(key)+"";
		String result=HttpRequest.post(url,json);
		return new Gson().fromJson(result,BaseResult.class);
	}
	
	public static void sendxcxMessagd(Integer eventid) {
		EventMapper mapper = ApplicationContextUtil.getMapper(EventMapper.class);
		EventSearchBean eventSearchBean =  new EventSearchBean();
		eventSearchBean.setId(eventid);
		eventSearchBean = mapper.getById(eventSearchBean);
		if(eventSearchBean!=null){
			P_eventlogMapper p_eventlogMapper = ApplicationContextUtil.getMapper(P_eventlogMapper.class);
			P_eventlogSearchBean logbean = new P_eventlogSearchBean();
			logbean.setId(eventSearchBean.getEventlogid());
			logbean = p_eventlogMapper.getById(logbean);
			
			Map<String, Object> map=new HashMap<>();
			map.put("touser",eventSearchBean.getOpenid());
			map.put("template_id","kvOa5k3mWhDqroPirsIj9fpOmNLZhM9YNU2E--Fil6A");
			map.put("form_id",eventSearchBean.getFormId());
			map.put("page","pages/eventdetail/eventdetail?id="+eventid);	
			Map<String, HashMap<String, String>>  data = new  HashMap<>();
			
			HashMap<String, String> d=new HashMap<>();
			d.put("value",eventSearchBean.getContent());
			d.put("color","#0A0A0A");
			data.put("keyword1",d);
			
			
			 String statusString ="";
	       	  if(logbean.getStatus()==6){
	       		  statusString="已结案";
	       	  }else if(logbean.getStatus()==7){
	       		  statusString="未整改";
	       	  }else if(logbean.getStatus()==8){
	       		  statusString="无效案件"; 
	       	  }
       		statusString=statusString+"   "+logbean.getDeal_opinion();
			  
			d=new HashMap<>();
			d.put("value",statusString);
			d.put("color","#0A0A0A");
			data.put("keyword2",d);
			
			d=new HashMap<>();
			d.put("value",logbean.getCreatetime().toString());
			d.put("color","#0A0A0A");
			data.put("keyword3",d);
			map.put("data", data);
			System.out.println(new Gson().toJson(map));
			send(new Gson().toJson(map));
		
		}
		
	}
	
	
	
	
/*	public static void main(String[] args) {
		Map<String, Object> map=new HashMap<>();
		map.put("touser","oZw_y0NDhcQtybGyLgE8yILCWuc0");
		map.put("template_id","kvOa5k3mWhDqroPirsIj9fpOmNLZhM9YNU2E--Fil6A");
		map.put("form_id","5f127a0f7c3ee25c4e35584f4f8cb30f");
		Map<String, HashMap<String, String>>  data = new  HashMap<>();
		
		HashMap<String, String> d=new HashMap<>();
		d.put("value","举报原因");
		d.put("color","#0A0A0A");
		data.put("keyword1",d);
		
		d=new HashMap<>();
		d.put("value","处理结果");
		d.put("color","#0A0A0A");
		data.put("keyword2",d);
		
		d=new HashMap<>();
		d.put("value","处理时间");
		d.put("color","#0A0A0A");
		data.put("keyword3",d);
		map.put("data", data);
		
		
		System.out.println(new Gson().toJson(map));
		send(new Gson().toJson(map));
		
	}*/
	public static void main(String[] args) {
	
	}
}
