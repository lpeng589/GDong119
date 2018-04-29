package test.com.pg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import com.google.gson.Gson;
import com.pg.gzhqy.department.Department;
import com.pg.gzhqy.user.User;
import com.pg.gzhqy.user.UserResultBean;
import com.pg.gzhqy.util.BaseResult;
import com.pg.mapper.F_departmentMapper;
import com.pg.mapper.H_workuserMapper;
import com.pg.searchbean.F_departmentSearchBean;
import com.pg.searchbean.H_workuserSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.Config;
import com.pg.util.GetLocation;
import com.pg.util.Tools;



public class ImportLDZ extends TestBase
{

	/**
	 * 导入街道
	 * @return
	 */
//	@Test
//    public void Test(){
//    	    F_departmentMapper mapper =ApplicationContextUtil.getMapper(F_departmentMapper.class);
//			String str2 = "";
//			String str3 = "";
//			String str4 = "";
//			String str5 = "";
//    	    try {
//    	    	File file = new File("D:\\workespace\\随手拍\\src\\test\\com\\pg\\Z-J.xlsx"); //街道
//    			List<Map> list =readXls(file);
//    			if(list!=null){
//    				for (int i = 0; i < list.size(); i++) {
//    					Map<String, Object> map = list.get(i);
//    					if(map.get("parentid")==null|| "".equals(map.get("parentid"))){
//							str3 = str3+"上级为空的======="+map.get("name");
//    						System.out.println("上级为空的======="+map.get("name"));
//    						return;
//    					}
//    					if(mapper.getByName((String)map.get("parentid"))==null){
//							str4 = str4 +"数据库没有这个上级的======="+map.get("parentid");
//    						System.out.println("数据库没有这个上级的======="+map.get("parentid"));
//    						return;
//    					}
//    					if(mapper.getByName((String)map.get("name"))!=null){
//							str5 = str5 +"数据库存在======="+map.get("name");
//    						System.out.println("数据库存在=============================="+map.get("name"));
//    						continue;
//    					}
//    					if(mapper.getByName((String)map.get("name"))==null){
//    						System.out.println("数据库没有的"+(String)map.get("name"));
//    						F_departmentSearchBean bean = new F_departmentSearchBean();
//    						 bean.setName(((String)map.get("name")).trim());
//    						 bean.setAddress((String)map.get("address"));
//    						 bean.setType(2); //街道
//    						 bean.setParentid(mapper.getByName((String)map.get("parentid")).getId());
//    						 Map<String, String> map2 = GetLocation.getItudeByTengxun(bean.getAddress());
//    							if (map2!=null&&map2.size()>0) {
//    								bean.setLatitude(map2.get("lat"));
//    								bean.setLongitude(map2.get("lng"));
//    							}
//    	    		    	 int result=mapper.addF_department(bean);
//    	    		    	 Thread.sleep(50);
//    	    		    	 //企业微信新增部门
//    	    		    	Department department = new Department(Config.getQyWxTXZSKey());
//    	    		        BaseResult baseResult = department.create(bean.getName(), bean.getParentid(), bean.getId());
//							str2 = str2+ baseResult.getErrcode()+baseResult.getErrmsg()+baseResult.getMenuid();
//    					}
//    				}
//					System.out.println("str2="+str2);
//					System.out.println("str3="+str3);
//					System.out.println("str4="+str4);
//					System.out.println("str5="+str5);
//    			}
//    		} catch (Exception e) {
//    			e.printStackTrace();
//    		}
//    }
    /**
	 * 导入村社
	 * @return
	 */
//    @Test
//    public void Test2(){
//    	    F_departmentMapper mapper =ApplicationContextUtil.getMapper(F_departmentMapper.class);
//			String str2 = "";
//			String str3 = "";
//			String str4 = "";
//			String str5 = "";
//    	    try {
//        		File file = new File("D:\\workespace\\随手拍\\src\\test\\com\\pg\\J-C.xlsx"); //村
//    			List<Map> list =readXls(file);
//    			if(list!=null){
//    				for (int i = 0; i < list.size(); i++) {
//    					Map<String, Object> map = list.get(i);
//    					if(map.get("parentid")==null|| "".equals(map.get("parentid"))){
//							str3 = str3+"上级为空的======="+map.get("name");
//    						System.out.println("上级为空的======="+map.get("name"));
//    						return;
//    					}
//    					if(mapper.getByName((String)map.get("parentid"))==null){
//							str4 = str4 +"数据库没有这个上级的======="+map.get("parentid");
//    						System.out.println("数据库没有这个上级的======="+map.get("parentid"));
//    						return;
//    					}
//    					if(mapper.getByName((String)map.get("name"))!=null){
//							str5 = str5 +"数据库存在======="+map.get("name");
//    						System.out.println("数据库存在=============================="+map.get("name"));
//    						continue;
//    					}
//    					if(mapper.getByName((String)map.get("name"))==null){
//    						System.out.println("数据库没有的"+(String)map.get("name"));
//    						F_departmentSearchBean bean = new F_departmentSearchBean();
//    						 bean.setName(((String)map.get("name")).trim());
//    						 bean.setAddress((String)map.get("address"));
//    						 bean.setType(3); //村
//    						 bean.setParentid(mapper.getByName((String)map.get("parentid")).getId());
//    						 Map<String, String> map2 = GetLocation.getItudeByTengxun(bean.getAddress());
//    							if (map2!=null&&map2.size()>0) {
//    								bean.setLatitude(map2.get("lat"));
//    								bean.setLongitude(map2.get("lng"));
//    							}
//    	    		    	 int result=mapper.addF_department(bean);
//    	    		    	 Thread.sleep(50);
//    	    		    	 //企业微信新增部门
//    	    		    	Department department = new Department(Config.getQyWxTXZSKey());
//    	    		        BaseResult baseResult = department.create(bean.getName(), bean.getParentid(), bean.getId());
//							str2 = str2+ baseResult.getErrcode()+baseResult.getErrmsg()+baseResult.getMenuid();
//    					}
//    				}
//					System.out.println("str2="+str2);
//					System.out.println("str3="+str3);
//					System.out.println("str4="+str4);
//					System.out.println("str5="+str5);
//    			}
//    		} catch (Exception e) {
//    			e.printStackTrace();
//    		}
//    }
    /**
	 * 导入巡查员
	 * @return
	 */
    @Test
    public void Test3(){
    	F_departmentMapper mapper =ApplicationContextUtil.getMapper(F_departmentMapper.class);
    	H_workuserMapper h_workuserMapper = ApplicationContextUtil.getMapper(H_workuserMapper.class);
    	String str2 = "";
    	String str3 = "";
    	String str4 = "";
    	String str5 = "";
 	    try {
     		File file = new File("D:\\workespace\\随手拍\\src\\test\\com\\pg\\C-X.xlsx"); //巡查员
 			List<Map> list =readXlsXCY(file);
 			if(list!=null){
 				for (int i = 0; i < list.size(); i++) {
 					Map<String, Object> map = list.get(i);
 					if(map.get("department")==null|| "".equals(map.get("department"))){
 						str3 = str3+"部门为空的======="+map.get("name");
 						System.out.println("部门为空的======="+map.get("name"));
 						return;
 					}
 					if(mapper.getByName((String)map.get("department"))==null){
 						str4 = str4 +"数据库没有这个部门的======="+map.get("department");
 						System.out.println("数据库没有这个部门的======="+map.get("department"));
 						return;
 					}
 					if(h_workuserMapper.getByPhone2((String)map.get("phone"))!=null){
 						str5 = str5 +"数据库存在======="+map.get("phone");
 						System.out.println("数据库存在======="+map.get("phone"));
 						continue;
 					}
 					if(h_workuserMapper.getByPhone2((String)map.get("phone"))==null){
 						System.out.println("数据库没有的"+(String)map.get("phone"));
 						H_workuserSearchBean bean = new H_workuserSearchBean();
 						 bean.setStatus(1);
 						 bean.setName((String)map.get("name"));
 						 bean.setPhone((String)map.get("phone"));
 						 bean.setDepartmentid(mapper.getByName((String)map.get("department")).getId());
 						 bean.setType(1); //巡查员
 						 bean.setRemark((String)map.get("remark"));
 						 int result=h_workuserMapper.addH_workuser(bean);
 	    		    	 if(result>0){
 	    		    		String code = Tools.MD5(bean.getId()+"_workuser_"+System.currentTimeMillis());
 	 	    		    	bean.setCode(code);
 	 	    		    	h_workuserMapper.UpdateCode(bean);
 	 	    		    //企业微信新增用户
 	 	    		    	User User = new User(Config.getQyWxTXZSKey());
 	 	    		    	UserResultBean userbean = new UserResultBean();
 	 	    		    	userbean.setUserid(code);
 	 	    		    	userbean.setName(bean.getName());
 	 	    		    	userbean.setMobile(bean.getPhone());
 	 	    		    	Integer department[]={bean.getDepartmentid()};
 	 	    		    	userbean.setDepartment(department);
 	 	    		        BaseResult baseResult = User.createUser(userbean);
 	 	    		        str2 = str2+ baseResult.getErrcode()+baseResult.getErrmsg()+baseResult.getMenuid();
 	    		    	 }
 	    		    	 Thread.sleep(50);
 					}
 				}
 				System.out.println("str2="+str2);
 				System.out.println("str3="+str3);
 				System.out.println("str4="+str4);
 				System.out.println("str5="+str5);
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
    }
    public static void main(String[] args) {

	}
    /**
     * 街道、村社表格
     * @param file
     * @return
     * @throws IOException
     */
	 public static List<Map> readXls(File file) throws IOException {
		 //街道、村社表格
		 String [] fields = {"name","address","parentid",};
		 List<Map> list = new ArrayList<Map>();
		 InputStream A=  new FileInputStream(file);
		 XSSFWorkbook wb = new XSSFWorkbook(A);
		 XSSFSheet sheet=wb.getSheetAt(0);
		 Integer flag_int = 1;
		 if(sheet!=null){
				int k=0;
				Gson json = new Gson();
				for (Row row : sheet) {
					if(k==0){
						k++;
						continue;
					}
					flag_int++;
					Map<String, Object> map = new HashMap<>();
					int i=0;
					for (String field : fields) {
						map.put(field, getCellValue(row.getCell(i)));
						i++;
					}
					
					list.add(map);
				}
		 }
         return list;
         
     }
	 /**
	  * 巡查员表格
	  * @param file
	  * @return
	  * @throws IOException
	  */
	 public static List<Map> readXlsXCY(File file) throws IOException {
		//巡查员表格
		 String [] fields = {"name","phone","idcard","department","a","b","c","d","e","f","g","remark",};
		 List<Map> list = new ArrayList<Map>();
		 InputStream A=  new FileInputStream(file);
		 XSSFWorkbook wb = new XSSFWorkbook(A);
		 XSSFSheet sheet=wb.getSheetAt(0);
		 Integer flag_int = 1;
		 if(sheet!=null){
				int k=0;
				Gson json = new Gson();
				for (Row row : sheet) {
					if(k==0){
						k++;
						continue;
					}
					flag_int++;
					Map<String, Object> map = new HashMap<>();
					int i=0;
					for (String field : fields) {
						map.put(field, getCellValue(row.getCell(i)));
						i++;
					}
					
					list.add(map);
				}
		 }
         return list;
	 }

	 
	    /**
	  	 * 获取cell值
	  	 * @param cell
	  	 * @return
	  	 */
	  	private static Object getCellValue(Cell cell) {
	  		if(cell==null) {
	  			return null;
	  		}
	  		switch(cell.getCellType()){
	  			case Cell.CELL_TYPE_STRING :
	  				if(cell.getStringCellValue().equals("")){
	  					return null;
	  				}
	  				return cell.getStringCellValue();
	  			case Cell.CELL_TYPE_NUMERIC :
	  				return cell.getNumericCellValue();
	  			case Cell.CELL_TYPE_BOOLEAN :
	  				return cell.getBooleanCellValue();
	  			default:
	  				if(cell==null||cell.getStringCellValue().equals("")){
	  					return null;
	  				}
	  				return cell.getStringCellValue();
	  		}
	  	}
	  	
}
