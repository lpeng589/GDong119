//package com.pg.servlet;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.pg.bean.MsgBean;
//import com.pg.mapper.CustomMapper;
//import com.pg.mapper.ImportMapper;
//import com.pg.mapper.ParamvalueMapper;
//import com.pg.mapper.ProductMapper;
//import com.pg.mapper.StockMapper;
//import com.pg.mapper.SupplierMapper;
//import com.pg.searchbean.CustomSearchBean;
//import com.pg.searchbean.ParamvalueSearchBean;
//import com.pg.searchbean.ProductSearchBean;
//import com.pg.searchbean.StockSearchBean;
//import com.pg.searchbean.SupplierSearchBean;
//import com.pg.util.ApplicationContextUtil;
//import com.pg.util.Check;
//import com.pg.util.HttpAnno;
//import com.pg.util.ManageCache;
//import com.pg.util.ServletUtil;
//import com.pg.util.Tools;
///**
// * 基础资料初始化
// * @author XCR
// *
// */
//@Controller
//public class ImportAction extends BaseAction
//{
//	
//	/**
//	 * 批量导入 供应商
//	 * @param request
//	 * @param response
//	 * @param file
//	 * @param bean
//	 */
//	@HttpAnno(module="supplier_add",log=HttpAnno.UNLOG)
//	@RequestMapping("hjjsupplier_import.htm")
//	public void importExcel(HttpServletRequest request, HttpServletResponse response,@RequestParam("filein") MultipartFile file,SupplierSearchBean bean) {
//		int i=0;
//		List<SupplierSearchBean> list = super.importExcel(SupplierSearchBean.class,file, "hjjsupplier_import");
//		if(list.size()>0){
//			SupplierMapper mapper = ApplicationContextUtil.getMapper(SupplierMapper.class);
//			ImportMapper ImportMapper = ApplicationContextUtil.getMapper(ImportMapper.class);
//			for (SupplierSearchBean bean2 : list) {
//				if(bean2.getName()==null){
//					continue;
//				}
//				Integer qudaoid = ImportMapper.getTableid(bean2.getPlacename(),"hjjplace");
//				if (qudaoid!=null) {
//					bean2.setPlaceid(qudaoid);
//				}else {
//					bean2.setPlaceid(0);
//				}
//				if (!"".equals(bean2.getOriginname())) {
//				if ("国内".equals(bean2.getOriginname())) {
//					bean2.setOrigin(1);
//					bean2.setCountry("中国");
//				}else {
//					bean2.setOrigin(2);
//					bean2.setCountry(bean2.getOriginname());
//				}
//				}else{
//					bean2.setOrigin(1);
//					bean2.setCountry("中国");
//				}
//				Integer jiesuanid = ImportMapper.getTableid(bean2.getSettlementname(),"hjjparamvalue");
//				if (jiesuanid!=null) {
//					bean2.setSettlement(String.valueOf(jiesuanid));
//				}else {
//					bean2.setSettlement("");
//				}
//				String code = "";
//				//供应商编码 ：渠道类别+（国/外区别）+序列号（6位）
//				char t;
//				t=(char)((int)bean2.getPlaceid()%26+65); //渠道类别
//				code = t+"";
//				code = code+Tools.getCode("hjjsupplier", bean2.getOrigin()+"", false, 1); //国/外区别
//				bean2.setCode(Tools.getCode("hjjsupplier", code, false, 6));
//				bean2.setEmployid(Check.getEmployee(request).getId());
//				bean2.setStatus(3);
//				if (!"".equals(bean2.getPhone()) && bean2.getPhone()!=null) {
//					BigDecimal bd = new BigDecimal(bean2.getPhone());  
//					bean2.setPhone(bd.toPlainString());
//				}
//				if ("".equals(bean2.getName())) {
//					bean2.setName("无");
//				}
//				if ("".equals(bean2.getLinkman())) {
//					bean2.setLinkman("无");
//				}
//				if ("".equals(bean2.getTelephone())) {
//					bean2.setTelephone("无");
//				}
//				if ("".equals(bean2.getFaxnumber())) {
//					bean2.setPhone("无");
//				}
//				if ("".equals(bean2.getCustomemail())) {
//					bean2.setCustomemail("无");
//				}
////				bean2.setServices(Check.getEmployee(request).getLoginname());
//				mapper.addSupplier(bean2);
//				i++;
//			}
//		}
//		ManageCache.supplierupdate =true;//获得缓存时候刷新缓存
//		ServletUtil.sendJsonBean(response, new MsgBean("0", "导入成功", i));
//	}
//	
//	
//	/**
//	 * 批量导入客户
//	 * @param request
//	 * @param response
//	 * @param file
//	 * @param bean
//	 */
//	@HttpAnno(module="custom_add",log=HttpAnno.UNLOG)
//	@RequestMapping("hjjcustom_import.htm")
//	public void hjjcustom_import(HttpServletRequest request, HttpServletResponse response,@RequestParam("filein") MultipartFile file,CustomSearchBean bean) {
//		int i=0;
//		List<CustomSearchBean> list = super.importExcel(CustomSearchBean.class,file, "hjjcustom_import");
//		if(list.size()>0){
//			CustomMapper CustomMapper = ApplicationContextUtil.getMapper(CustomMapper.class);
//			ImportMapper ImportMapper = ApplicationContextUtil.getMapper(ImportMapper.class);
//			List<CustomSearchBean> addList=new ArrayList<>();
//			for (CustomSearchBean bean2 : list) {
//				Integer zibeanid = ImportMapper.getTableid(bean2.getZibenname(),"hjjparamvalue");
//				if (zibeanid!=null) {
//					bean2.setParamvalueid(zibeanid);
//				}else {
//					bean2.setParamvalueid(0);
//				}
//				Integer tradeid = ImportMapper.getTableid(bean2.getTradename(),"hjjtrade");
//				if (tradeid!=null) {
//					bean2.setTradeid(tradeid);
//					Integer Tradelowid = ImportMapper.getTableid2(bean2.getTradelowname(),"hjjtradelow","and tradeid="+tradeid);
//					if (Tradelowid!=null) {
//						bean2.setTradelowid(Tradelowid);
//					}else {
//						bean2.setTradelowid(0);
//					}
//				}else {
//					bean2.setTradeid(0);
//					bean2.setTradelowid(0);
//				}
//				if (bean2.getOriginname()!=null&&"".equals(bean2.getOriginname())) {
//					if (bean2.getOriginname().equals("国内")) {
//						bean2.setOrigin(1);
//						bean2.setCountry("中国");
//					}else {
//						bean2.setOrigin(2);
//						bean2.setCountry(bean2.getOriginname());
//					}
//				}else{
//					bean2.setOrigin(1);
//					bean2.setCountry("中国");
//				}
//				if (bean2.getCooperatetype().equals("一般贸易")) {
//					bean2.setCooperatetype("B");
//				}else {
//					bean2.setCooperatetype("A");
//				}
//				String code = "";
//				//用户编码 ：公司资本性质编码（1位）+行业编码（3位）+序列号（6位）
//				ParamvalueMapper paramvalueMapper = ApplicationContextUtil.getMapper(ParamvalueMapper.class);
//				ParamvalueSearchBean paramvalueSearchBean = paramvalueMapper.getById(bean2.getParamvalueid());
//				if(paramvalueSearchBean!=null){
//					code = paramvalueSearchBean.getValue(); //公司资本性质编码（1位）
//				}
//				char t;
//				t=(char)((int)bean2.getTradeid()%26+65); //行业类别
//				code = code+Tools.getCode("hjjcustom", t+"", false, 2);
//				bean2.setCode(Tools.getCode("hjjcustom", code, false, 6));
//				bean2.setEmployid(Check.getEmployee(request).getId());
//				bean2.setStatus(3);
//				if ("".equals(bean2.getName())) {
//					bean2.setName("无");
//				}
//				if ("".equals(bean2.getBusiness())) {
//					bean2.setBusiness("无");
//				}
//				if ("".equals(bean2.getLinkman())) {
//					bean2.setLinkman("无");
//				}
//				if ("".equals(bean2.getTelephone())) {
//					bean2.setTelephone("无");
//				}
//				if ("".equals(bean2.getPhone()) || bean2.getPhone()==null) {
////					bean2.setPhone("无");
//				}else{
//					if(bean2.getPhone().contains("E")){
//						BigDecimal bd = new BigDecimal(bean2.getPhone()); 
//						bean2.setPhone(bd.toPlainString());
//					}
//				}
////				System.out.println("------------"+bean2.getTaxcode());
//				if ("".equals(bean2.getTaxcode()) || bean2.getTaxcode()==null) {
////					bean2.setPhone("无");
//				}else{
//					try {
//						if(bean2.getTaxcode().contains("E")){
//							BigDecimal bd = new BigDecimal(bean2.getTaxcode()); 
//							bean2.setTaxcode(bd.toPlainString());
//						}
//					} catch (Exception e) {
//						
//					}
//				}
//				
//				
//				if ("".equals(bean2.getCustomemail())) {
//					bean2.setCustomemail("无");
//				}
//				if (!"".equals(bean2.getAccount()) && bean2.getAccount()!=null) {
//					BigDecimal bd = new BigDecimal(bean2.getAccount());  
//					bean2.setAccount(bd.toPlainString());
//				}
////				bean2.setServices(Check.getEmployee(request).getLoginname());
//				bean2.setCity("-".equals(bean2.getCity())?"":bean2.getCity()+"市");
//				CustomMapper.addCustom(bean2);
////				addList.add(bean2);
//				i++;
//			}
////			if (addList!=null&&addList.size()>0) {
////				CustomMapper.addCustomList(addList);   //批量insert模版消息
////			}
//		}
//		ManageCache.customupdate =true;//获得缓存用户时候刷新用户缓存
//		ServletUtil.sendJsonBean(response, new MsgBean("0", "导入成功", i));
//	}
//	
//	
//	/**
//	 * 批量导入库存
//	 * @param request
//	 * @param response
//	 * @param file
//	 * @param bean
//	 */
//	@HttpAnno(module="stock_import",log=HttpAnno.UNLOG)
//	@RequestMapping("hjjstock_import.htm")
//	public String hjjstock_import(HttpServletRequest request, HttpServletResponse response,@RequestParam("filein") MultipartFile file,StockSearchBean bean) {
//		int i=0;
//		List<StockSearchBean> list = super.importExcel(StockSearchBean.class,file, "hjjstock_import");
//		ProductMapper productmapper = ApplicationContextUtil.getMapper(ProductMapper.class);
//		ProductSearchBean productSearchBean = new ProductSearchBean();
//		//查询产品
//		/*
//		productSearchBean.setStatus(3);
//		productSearchBean.setLimitFlag(false);
//		List<ProductSearchBean> productlists= productmapper.list2(productSearchBean);
//		HashMap<String,ProductSearchBean>  productMap = new HashMap();
//		for (int j = 0; j < productlists.size(); j++) {
//			productSearchBean = productlists.get(j);
//			productMap.put(productSearchBean.getBrand()+productSearchBean.getModel()+productSearchBean.getSuppliername(), productSearchBean);
//		}*/
//		//查询供应商
//		SupplierSearchBean SupplierSearchBean = new SupplierSearchBean();
////		SupplierSearchBean.setStatus(3);
////		SupplierSearchBean.setLimitFlag(false);
////		SupplierSearchBean.setSelectsql("");
////		SupplierMapper SupplierMapper = ApplicationContextUtil.getMapper(SupplierMapper.class);
//		List<SupplierSearchBean> SupplierSearchBeanlists= ManageCache.getsupplierlist();
//		HashMap<String,SupplierSearchBean>  SupplierMap = new HashMap();
//		for (int j = 0; j < SupplierSearchBeanlists.size(); j++) {
//			SupplierSearchBean = SupplierSearchBeanlists.get(j);
//			SupplierMap.put(SupplierSearchBean.getName().trim(), SupplierSearchBean);
//		}
//		
//		//查询币种
//		ParamvalueMapper paramvalueMapper = ApplicationContextUtil.getMapper(ParamvalueMapper.class);
//		ParamvalueSearchBean ParamvalueSearchBean1 = new ParamvalueSearchBean();
//		ParamvalueSearchBean1.setLimitFlag(false);
//		ParamvalueSearchBean1.setPayremarks("币种");
//		List<ParamvalueSearchBean> Paramvaluelist =  paramvalueMapper.list(ParamvalueSearchBean1);
//		HashMap<String,Integer>  ParamvalueMap = new HashMap();
//		for (int j = 0; j < Paramvaluelist.size(); j++) {
//			ParamvalueSearchBean1 = Paramvaluelist.get(j);
//			ParamvalueMap.put(ParamvalueSearchBean1.getName(), ParamvalueSearchBean1.getId());
//		}
//		List<StockSearchBean> errlist = new ArrayList<StockSearchBean>();
//		
//		if(list.size()>0){
//			StockMapper StockMapper = ApplicationContextUtil.getMapper(StockMapper.class);
//			ImportMapper ImportMapper = ApplicationContextUtil.getMapper(ImportMapper.class);
//			for (StockSearchBean bean2 : list) {
//				if(bean2.getSuppliername()==null || "".equals(bean2.getSuppliername())){
//					bean2.setErrmsg("供应商不能为空");
//					errlist.add(bean2);continue;
//				}
//				if(SupplierMap.get(bean2.getSuppliername().trim())==null){
//					bean2.setErrmsg("该供应商不存在");
//					errlist.add(bean2);continue;
//				}
//				
//				
//				if(bean2.getBrand()==null || "".equals(bean2.getBrand())){
//					bean2.setErrmsg("品牌不能为空");
//					errlist.add(bean2);continue;
//				}
//				if(bean2.getModel()==null || "".equals(bean2.getModel())){
//					bean2.setErrmsg("型号不能为空");
//					errlist.add(bean2);continue;
//				}
//				if(bean2.getPrice100()==null || "".equals(bean2.getPrice100())){
//					bean2.setErrmsg("采购价格不能为空");
//					errlist.add(bean2);continue;
//				}
//				if(bean2.getCurrencyname()==null || "".equals(bean2.getCurrencyname())){
//					bean2.setErrmsg("币种不能为空");
//					errlist.add(bean2);continue;
//				}
//				//检查产品
//				/*ProductSearchBean product=  productMap.get(bean2.getBrand()+bean2.getModel()+bean2.getSuppliername());
//				if (product!=null) {
//					bean2.setProductid(product.getId());
//				}else {
//					bean2.setErrmsg("找不到该供应商的 品牌和型号");
//					errlist.add(bean2);continue;
//				}*/
//				productSearchBean.setSupplierid(SupplierMap.get(bean2.getSuppliername().trim()).getId());
//				productSearchBean.setBrand(bean2.getBrand());
//				productSearchBean.setModel(bean2.getModel());
//				Integer productid = productmapper.getbyBM(productSearchBean);
////				Integer productid = ImportMapper.getTableid3("model='"+bean2.getModel()+"'","hjjproduct","and brand='"+bean2.getBrand()+"' and ");
//				if (productid!=null) {
//					bean2.setProductid(productid);
//				}else {
//					bean2.setErrmsg("找不到该供应商的 品牌和型号");
//					errlist.add(bean2);continue;
//				}
//				//检查币种
//				Integer bizhongid =  ParamvalueMap.get(bean2.getCurrencyname());
//				if (bizhongid!=null) {
//					bean2.setCurrency(bizhongid);
//				}else {
//					bean2.setErrmsg("找不到该币种");
//					errlist.add(bean2);continue;
//				}
//				Double priceDouble = bean2.getPrice100()*100;
//				bean2.setPrice(priceDouble.intValue());
////				bean2.setCode(Tools.getCode("hjjstock", "KC", true, 4));
//				bean2.setCode(Tools.getCode2("KC1608160001", "KC", false, 8,i));
//				bean2.setStatus(3);
//				bean2.setEmployeeid(Check.getEmployee(request).getId());
//				bean2.setRemark("Excel导入库存");
////				StockMapper.addStock(bean2);
//				i++;
//			}
//			//判断是否有错误的数据
//			if(errlist.size()>0){
//				request.setAttribute("errlist", errlist);
//				return "stockimporterr.jsp";
//			}else {
//				for (StockSearchBean bean2 : list) {
//					StockMapper.addStock(bean2);
//				}
//			}
//			
//		}
////		ServletUtil.sendJsonBean(response, new MsgBean("0", "导入成功", i));
//		request.setAttribute("msg", "成功导入<span style=\"color:red\">"+i+"</span>条数据");
//		request.setAttribute("url", "stock.htm?operate=list");//返回跳转的链接
//		return "succeed100.jsp";
//		
//	}
//	
//	
//	@Override
//	protected Integer getListCount(Object cond) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected List<Object> getListData(Object cond) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
//
