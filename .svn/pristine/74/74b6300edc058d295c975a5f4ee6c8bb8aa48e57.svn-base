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
import com.pg.gzhqy.user.User;
import com.pg.gzhqy.user.UserResultBean;
import com.pg.gzhqy.util.BaseResult;
import com.pg.mapper.F_departmentMapper;
import com.pg.mapper.H_workuserMapper;
import com.pg.searchbean.F_departmentSearchBean;
import com.pg.searchbean.H_workuserSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.Config;
import com.pg.util.Tools;



public class ImportLDZdetail extends TestBase
{

	/**
	 * 导入楼栋长
	 * @return
	 */
	
    @Test
    public void Test(){
    	    F_departmentMapper demapper =ApplicationContextUtil.getMapper(F_departmentMapper.class);
    	    try {
        		File file = new File("E:\\workplace\\shop\\横岗\\src\\test\\com\\pg\\ldz.xlsx");
    			List<Map> list =readXls(file);
    			if(list!=null){
    				for (int i = 0; i < list.size(); i++) {
    					Map<String, Object> map = list.get(i);
    					if(map.get("phone")==null|| "".equals(map.get("phone"))){
    						return;
    					}
    					F_departmentSearchBean debean = demapper.getByName((String)map.get("wangluo"));
    					if(debean!=null){
    					    H_workuserMapper mapper =ApplicationContextUtil.getMapper(H_workuserMapper.class);
    					    H_workuserSearchBean workuserSearchBean = mapper.getByPhone((String)map.get("phone"));
    					    if(workuserSearchBean!=null){
    					       continue;
    					    }
    					    H_workuserSearchBean bean =  new H_workuserSearchBean();
    					    bean.setType(1);
    					    bean.setDepartmentid(debean.getId());
    					    bean.setName((String)map.get("name"));
    					    bean.setPhone((String)map.get("phone"));
    					    int result=mapper.addH_workuser(bean);
    					    if(result>0){
    					    	//添加CODE
    					    	String code = Tools.MD5(bean.getId()+"_workuser_"+System.currentTimeMillis());
    					    	bean.setCode(code);
    					    	mapper.UpdateCode(bean);
    					    	//企业微信新增用户
    					    	User User = new User(Config.getQyWxTXZSKey());
    					    	UserResultBean userbean = new UserResultBean();
    					    	userbean.setUserid(code);
    					    	userbean.setName(bean.getName());
    					    	userbean.setMobile(bean.getPhone());
    					    	Integer department[]={bean.getDepartmentid()};
    					    	userbean.setDepartment(department);
    					    	userbean.setEnable(1);
    					        BaseResult baseResult = User.createUser(userbean);		  
    					    }else{
    					        
    					    	
    					    }
    					}
    				}
    			}
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	
    	
    }
    public static void main(String[] args) {
    	try {
    		File file = new File("E:\\workplace\\shop\\横岗\\src\\test\\com\\pg\\ldz.xlsx");
//			System.out.println(file.getSize());
			List<Map> list =readXls(file);
//			System.out.println(list.size());
			if(list!=null){
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i));
				}
				
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
	}
    
	 public static List<Map> readXls(File file) throws IOException {
		 String [] fields = {"no","name","sex","phone","wangluo","xiaoqu","loudong",};
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
