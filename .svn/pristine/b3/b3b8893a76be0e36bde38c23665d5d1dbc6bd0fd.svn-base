package com.pg.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.pg.bean.BaseBean;
import com.pg.bean.ExporttemplateBean;
import com.pg.bean.ResultListBean;
import com.pg.mapper.ExporttemplateMapper;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.Tools;


public abstract class BaseAction{
	/**
	 * 获取列表数量
	 * @param cond
	 * @return
	 */
	protected abstract Integer getListCount(Object cond);
	/**
	 * 获取列表集
	 * @param cond
	 * @return
	 */
	protected abstract List<Object> getListData(Object cond);
	/**
	 * 获取并返回结果
	 * @return
	 */
	protected ResultListBean getList(HttpServletRequest request,Object cond) {
		BaseBean base = (BaseBean) cond;
		Integer page= base.getPage();
		String is_page=request.getParameter("is_page");//假如不是翻页的，查询时候要清掉页数
		if(is_page==null){
			base.setPage(1);
		}
		RequestMapping mapping = this.getClass().getAnnotation(RequestMapping.class);
		String url=mapping.value()[0]+"?operate="+request.getParameter("operate");
		String sort_page=request.getParameter("sort_page");
		if (sort_page!=null) {
			cond=request.getSession().getAttribute(url+"_cond");
			base = (BaseBean) cond;
			base.setPage(page);
		}else{
			request.getSession().setAttribute(url+"_cond", cond);
		}	
		url+="&sort_page=true&page=";
		ResultListBean result =new ResultListBean();
		result.setCond(cond);
		 //如果有指定的searchAction，则用指定的searchAction查询数据
        if(base.getSearchAction()!=null)
        {
            result.setCount(base.getSearchAction().getListCount(cond));
            result.setList(base.getSearchAction().getListData(cond));
        }
        else
        {
            result.setCount(getListCount(cond));
            result.setList(getListData(cond));
        }
		result.setPages((int)Math.ceil(Double.valueOf(result.getCount())/Double.valueOf(base.getLimitSize())));
		request.setAttribute("data", result);
		return result;
	}

	/**
	 * 导出excel
	 * @param response
	 * @param list  数据集
	 * @param fileName  文件名字
	 * @param heads 表头名字数组
	 * @param fields 对应字段数组
	 * @param maps 对应字段映射map
	 */
	protected boolean export(HttpServletResponse response,List<Object> list,String fileName,String[] heads,String fields [],HashMap<String, HashMap<String, String>> maps) {
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet();
		int i = 0;
		XSSFRow row = sheet.createRow(i);
		i++;
		int j=0;
		for (String head : heads) {
			row.createCell(j).setCellValue(head);
			j++;
		}
		if (list != null) {
			for (Object obj : list) {
				row = sheet.createRow(i);
				i++;
				j=0;
				Map<String, Object> map = Tools.changeBeanToMap(obj);
				for (String field : fields) {
					String[] subFields = field.split(" "); 
					StringBuilder content = new StringBuilder();
					for (String subField : subFields) {
						Object object = map.get(subField);
						String item = object==null?"":object.toString();
						if(maps!=null&&maps.get(field)!=null){
							item=maps.get(field).get(item);
							if(item==null){
								item="";
							}
						}
						content.append(item);
					}
					row.createCell(j).setCellValue(content.toString());
					j++;
				}
			}
		}
		try {
			fileName = URLEncoder.encode(fileName, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
			return false;
		}
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment ; filename=" + fileName + ".xlsx");
		try {
			OutputStream ous = response.getOutputStream();
			wb.write(ous);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * 根据模板导出excel
	 * @param response
	 * @param list  数据集
	 * @param key 模板名字
	 */
	@SuppressWarnings("unchecked")
	protected boolean export(HttpServletResponse response,List<Object> list,String key) {
		ExporttemplateMapper mapper = ApplicationContextUtil.getMapper(ExporttemplateMapper.class);
		ExporttemplateBean bean = mapper.getByKey(key);
		if(bean!=null){
			HashMap<String, HashMap<String, String>> maps =null;
			if(bean.getMaps()!=null&&!bean.getMaps().equals("")){
				maps = new Gson().fromJson(bean.getMaps(), HashMap.class);
			}
			return export(response,list,bean.getDesc(),bean.getHeads().split(","),bean.getFields().split(","),maps);
		}
		return false;
	}
	/**
	 * 根据模板导出excel
	 * @param response
	 * @param cond  数据条件
	 * @param key
	 * @return
	 */
	protected boolean export(HttpServletResponse response,Object cond,String key) {
		BaseBean base = (BaseBean) cond;
		base.setLimitFlag(false);
		List<Object> list=getListData(base);
		return export(response, list, key);
	}
	
/*	protected boolean exportOrder(HttpServletResponse response,Object cond,String key) {
		BaseBean base = (BaseBean) cond;
		base.setLimitFlag(false);
		List<Object> list=getListData(base);
		for (Object object : list) {
			Double paysum=0.0;
			OrderSearchBean bean2=(OrderSearchBean)object;
			if(bean2.getPaypre()!=null&&bean2.getPaypre().length()>0){
				String [] arr = bean2.getPaypre().replaceAll("<br>", "").split(",");
				for (int i = 0; i < arr.length; i++) {
					paysum = paysum + Double.parseDouble(arr[i]);
				}
			}
			bean2.setPaysum(paysum);
			if(bean2.getOrdertype()==1){//销售总额
				bean2.setOrdercost(bean2.getOrdersalecost());
			}else{
				bean2.setOrdercost(bean2.getOrderservicecost());
			}
		}
		return export(response, list, key);
	}
	protected boolean exportOrderBuy(HttpServletResponse response,Object cond,String key) {
		BaseBean base = (BaseBean) cond;
		base.setLimitFlag(false);
		List<Object> list=getListData(base);
		for (Object object : list) {
			Double paysum=0.0;
			OrderbuySearchBean bean2=(OrderbuySearchBean)object;
			if(bean2.getPaypre()!=null&&bean2.getPaypre().length()>0){
				String [] arr = bean2.getPaypre().replaceAll("<br>", "").split(",");
				for (int i = 0; i < arr.length; i++) {
					paysum = paysum + Double.parseDouble(arr[i]);
				}
			}
			bean2.setPaysum(paysum);
		}
		return export(response, list, key);
	}*/
	
	/**
	 * 根据上传的文件获得数据集
	 * @param file
	 * @param key
	 * @return
	 */
	@SuppressWarnings("hiding")
	protected <T> List<T> importExcel(Class<T> t,MultipartFile file,String key) {
		ExporttemplateMapper mapper = ApplicationContextUtil.getMapper(ExporttemplateMapper.class);
		ExporttemplateBean bean = mapper.getByKey(key);
		if(bean!=null){
			return importExcel(t,file,bean.getFields().split(","));
		}
		return new ArrayList<>();
	}
	
	
	/**
	 * 根据上传的文件获得数据集
	 * @param file
	 * @param fields
	 * @return
	 */
	@SuppressWarnings("hiding")
	protected <T> List<T> importExcel(Class<T> t,MultipartFile file,String fields []) {
		 List<T> list = new ArrayList<>();
		 List<T> errorlist = new ArrayList<>();
		 Integer flag_int = 1;
		 try {
			XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
			XSSFSheet sheet=wb.getSheetAt(0);
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
					
					list.add(json.fromJson(json.toJson(map),t));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> map = new HashMap<>();
			Gson json = new Gson();
			map.put("flag_base", e.getMessage());
			map.put("flag_int", flag_int);
			errorlist.add(json.fromJson(json.toJson(map),t));
		}
		return errorlist.size()>0?errorlist:list;
	}
	/**
	 * 获取cell值
	 * @param cell
	 * @return
	 */
	private Object getCellValue(Cell cell) {
		if(cell==null) {
			return null;
		}
//		System.out.println("---------"+cell.getCellType()+"------------"+cell);
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
	
        
        /**
         * 特别警告：模版Excel第一行不能是空白行
         * 专用模版导出Excel
         * @param response
         * @param bean  表头内容
         * @param objList   详情列内容
         * @param fileName    最后导出的excel文件名
         * @param theadnumber    第多少行之后就到加载动态详情内容表单   行数从0开始算起
         * @param templatename   模版excel的文件名
         */
    	@SuppressWarnings("unchecked")
		protected void ExcelExport(HttpServletResponse response,Object bean,Object objList,Integer listnum,String fileName,Integer theadnumber,String templatename) {
   		 try {
   			String rootPath=getClass().getResource("/").getFile().toString();  
   			File file = new File(rootPath+"/template/"+templatename);
   			BufferedInputStream in1 = new BufferedInputStream(new FileInputStream(file));
   			XSSFWorkbook wb = new XSSFWorkbook(in1);   //模版excel
   			Sheet  sheet=wb.getSheetAt(0);   //模版excel
   			XSSFWorkbook wb2 = new XSSFWorkbook();   //目标excel
   			Sheet  sheet2 = wb2.createSheet();   //目标excel
   			List<Object> list= (List<Object>) objList;   //详情list
   			if(sheet!=null){
   			CellRangeAddress region = null;
   			 for (int i = 0; i < sheet.getNumMergedRegions(); i++) {  //合并单元格
   				region = sheet.getMergedRegion(i);
   				 if (region.getFirstRow()<theadnumber||region.getFirstRow()==theadnumber.intValue()) {    //以中间详情 动态为边界，上面合并单元格
   	   		      if ((region.getFirstColumn() >= sheet.getFirstRowNum())
   	   		          && (region.getLastRow() <= sheet.getLastRowNum())) {
   	   		        sheet2.addMergedRegion(region);
   	   		      }
				}else if(region.getFirstRow()==(theadnumber+1)){   //合并单元格    中间详情 合并单元格
					for (int j = 0; j < list.size(); j++) {
						region.setFirstRow(theadnumber+1+j);
						region.setLastRow(theadnumber+1+j);
	   		   	   		sheet2.addMergedRegion(region);
					}
				}else {   //合并单元格    下面合并单元格
					region.setFirstRow(region.getFirstRow()+list.size()-1);
					region.setLastRow(region.getLastRow()+list.size()-1);
   		   	   		sheet2.addMergedRegion(region);
				}
   		      
   		    }
   			 
   				Map<String, Object> map = Tools.changeBeanToMap(bean);    //bean转map
   				int o=0;
   				int a=0;
   				int b=0;
   				int rowCount = sheet.getLastRowNum();// 总行数
   				int maxCellNum = 0;      //列数
   				for (int z = 0; z <=rowCount; z++) {         //循环行
   					Row  row = sheet.getRow(z);
   					if (row==null) {
   						b++;
						continue;
					}
   					maxCellNum = maxCellNum < row.getLastCellNum() ? row
   							.getLastCellNum() : maxCellNum;         //求最大列数
   					if (o==(theadnumber+1)) {     //循环添加主表内容详情
						for (int i = 0; i < list.size(); i++) {
							Row  row2 = sheet2.createRow(o+a);
							row2.setHeight(row.getHeight());   // 设置行高
							Map<String, Object> listmap = Tools.changeBeanToMap(list.get(i));
	   						for (int j = 0; j < maxCellNum; j++) {
	   							if (row.getCell(j)==null) {
	   								continue;
								}
	   							String value = Tools.getMatch(listmap, getCellValue(row.getCell(j))+"");
	   							Cell   cell2 = row.getCell(j);   //源
   								Cell   cell = row2.createCell(j); //目的
   								CellStyle alignStyle = cell2.getCellStyle();  //模版样式
   								CellStyle alignStyle2 = wb2.createCellStyle();  //目标样式
   								cell.setCellStyle(CopyCellStyle(alignStyle,alignStyle2,wb,wb2));  //复制样式
	   							if (value!=null&&!"".equals(value)&&!"null".equals(value)) {
	   								if (value.equals("index")) {     //详情序列号
	   									cell.setCellValue(i+1);
									}
	   								else {
										cell.setCellValue(value);
									}
	   							}
	   						}
	   						a++;
						}
					}else {
						if (o==(theadnumber+2)) {
							a=a-1;
						}
						Row  row2 = sheet2.createRow(o+a+b);
						row2.setHeight(row.getHeight());  // 设置行高
   						for (int j = 0; j < maxCellNum; j++) {
   							sheet2.setColumnWidth(j, sheet.getColumnWidth(j));  // 设置列宽
   							if (row.getCell(j)==null) {
   								continue;
							}
   							String value =  Tools.getMatch(map, getCellValue(row.getCell(j))+"");
							Cell   cell2 = row.getCell(j);   //源
							Cell   cell = row2.createCell(j); //目的
							CellStyle alignStyle = cell2.getCellStyle();    //模版样式
							CellStyle alignStyle2 = wb2.createCellStyle();    //目标样式
							cell.setCellStyle(CopyCellStyle(alignStyle,alignStyle2,wb,wb2));   //复制样式
   							if (value!=null&&!"".equals(value)&&!"null".equals(value)) {
								cell.setCellValue(value);
   							}
   						}
					}
   					o++;
   				}
   				//  orderbuy.xlsx  采购单插入图片    
   				if (templatename.equals("orderbuy.xlsx")) {
   					List<XSSFPictureData> pictures = wb.getAllPictures();    //get  模版excel 所有图片
   	   				for (int i = 0; i < pictures.size(); i++) {
   	   				XSSFPictureData pictureData = pictures.get(i);
   	   	   		    byte[] data = pictureData.getData();    //转换成流
   	   	   		    String ext = pictureData.suggestFileExtension();    //get  图片格式 
   	   	   		    if (ext.equals("png")) {    // 图片格式  只支持png格式
   	   	   			int pictureIdx = wb2.addPicture(data, Workbook.PICTURE_TYPE_PNG);       //添加图片   
   	   	   			CreationHelper helper = wb2.getCreationHelper();
   	   	   			Drawing drawing = sheet2.createDrawingPatriarch();
   	   	   			ClientAnchor anchor = helper.createClientAnchor();
   	   	   			anchor.setCol1(0);            // 插入到0列  
   	   	   			anchor.setRow1(1);            // 插入到1行
   	   	   			anchor.setRow2(1);            // 插入到1行
   	   	   			anchor.setCol2(0);            // 插入到0列
   	   	   			Picture pict = drawing.createPicture(anchor, pictureIdx);     //插入图片
   	   	   			pict.resize();
   	   	   		   }
   	   	   		  }
				}
   			//  orderbuy.xlsx  采购单插入图片    
   				if (templatename.equals("orderbuyENG.xlsx")) {
   					List<XSSFPictureData> pictures = wb.getAllPictures();    //get  模版excel 所有图片
   	   				for (int i = 0; i < pictures.size(); i++) {
   	   				XSSFPictureData pictureData = pictures.get(i);
   	   	   		    byte[] data = pictureData.getData();    //转换成流
   	   	   		    String ext = pictureData.suggestFileExtension();    //get  图片格式 
   	   	   		    if (ext.equals("png")) {    // 图片格式  只支持png格式
   	   	   			int pictureIdx = wb2.addPicture(data, Workbook.PICTURE_TYPE_PNG);       //添加图片   
   	   	   			CreationHelper helper = wb2.getCreationHelper();
   	   	   			Drawing drawing = sheet2.createDrawingPatriarch();
   	   	   			ClientAnchor anchor = helper.createClientAnchor();
   	   	   			anchor.setCol1(0);            // 插入到0列  
   	   	   			anchor.setRow1(1);            // 插入到1行
   	   	   			anchor.setRow2(1);            // 插入到1行
   	   	   			anchor.setCol2(0);            // 插入到0列
   	   	   			Picture pict = drawing.createPicture(anchor, pictureIdx);     //插入图片
   	   	   			pict.resize();
   	   	   		   }
   	   	   		  }
				}
   				
   				if (templatename.equals("ordersale.xlsx")) {
   					List<XSSFPictureData> pictures = wb.getAllPictures();    //get  模版excel 所有图片
   	   				for (int i = 0; i < pictures.size(); i++) {
   	   				XSSFPictureData pictureData = pictures.get(i);
   	   	   		    byte[] data = pictureData.getData();    //转换成流
   	   	   		    String ext = pictureData.suggestFileExtension();    //get  图片格式 
   	   	   		    if (ext.equals("png")) {    // 图片格式  只支持png格式
   	   	   			int pictureIdx = wb2.addPicture(data, Workbook.PICTURE_TYPE_PNG);       //添加图片   
   	   	   			CreationHelper helper = wb2.getCreationHelper();
   	   	   			Drawing drawing = sheet2.createDrawingPatriarch();
   	   	   			ClientAnchor anchor = helper.createClientAnchor();
	   	   	   		if(i==0){
		   	   	   		anchor.setCol1(0);            // 插入到0列  
		   	   			anchor.setRow1(1);            // 插入到1行
		   	   			anchor.setRow2(1);            // 插入到1行
		   	   			anchor.setCol2(0);            // 插入到0列
	   	   	   		}else{//图2
			   	   	   	anchor.setCol1(13);            // 插入到0列  
		   	   			anchor.setRow1(15+listnum);            // 插入到1行
		   	   			anchor.setRow2(15+listnum);            // 插入到1行
		   	   			anchor.setCol2(13);            // 插入到0列
	   	   	   		}	
   	   	   			Picture pict = drawing.createPicture(anchor, pictureIdx);     //插入图片
   	   	   			pict.resize();
   	   	   		   }
   	   	   		  }
				}
   				
   				
   			}
   			try {
   				fileName = URLEncoder.encode(fileName, "utf-8");
   			} catch (UnsupportedEncodingException e1) {
   				e1.printStackTrace();
   			}
   			response.setContentType("application/octet-stream");
   			response.setHeader("Content-Disposition", "attachment ; filename=" + fileName + ".xlsx");
   			try {
   				OutputStream ous = response.getOutputStream();
   				wb2.write(ous);
   			} catch (IOException e) {
   				e.printStackTrace();
   			}
   		} catch (IOException e) {
   			e.printStackTrace();
   		}
   }
    	/**
    	 * 复制样式
    	 * @param fromStyle  源样式
    	 * @param toStyle    目标样式
    	 * @param wb         源表
    	 * @param wb2                        目标表
    	 * @return
    	 */
    	public CellStyle CopyCellStyle(CellStyle fromStyle, CellStyle toStyle,XSSFWorkbook wb,XSSFWorkbook wb2)
    	{
    	toStyle.setAlignment(fromStyle.getAlignment());
    	toStyle.setBorderBottom(fromStyle.getBorderBottom());
    	toStyle.setBorderLeft(fromStyle.getBorderLeft());
    	toStyle.setBorderRight(fromStyle.getBorderRight());
    	toStyle.setBorderTop(fromStyle.getBorderTop());
    	toStyle.setTopBorderColor(fromStyle.getTopBorderColor());
    	toStyle.setBottomBorderColor(fromStyle.getBottomBorderColor());
    	toStyle.setRightBorderColor(fromStyle.getRightBorderColor());
    	toStyle.setLeftBorderColor(fromStyle.getLeftBorderColor());
    	toStyle.setFillForegroundColor(fromStyle.getFillForegroundColor());
    	toStyle.setFillPattern(fromStyle.getFillPattern());
    	toStyle.setDataFormat(fromStyle.getDataFormat());
		toStyle.setVerticalAlignment(fromStyle.getVerticalAlignment());
		toStyle.setWrapText(fromStyle.getWrapText());
		XSSFFont headfont = wb2.createFont();
		headfont.setFontName(wb.getFontAt(fromStyle.getFontIndex()).getFontName());
		headfont.setColor(wb.getFontAt(fromStyle.getFontIndex()).getXSSFColor());
		headfont.setFontHeightInPoints(wb.getFontAt(fromStyle.getFontIndex()).getFontHeightInPoints());
		headfont.setBoldweight(wb.getFontAt(fromStyle.getFontIndex()).getBoldweight());
		toStyle.setFont(headfont);
    	return toStyle;
    	}
    	
    	
}
