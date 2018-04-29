package com.pg.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pg.bean.BaseBean;
import com.pg.bean.CompareBean;
import com.pg.mapper.FlowNodeMapper;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.Dict;
import com.pg.util.HttpAnno;
@Controller
public class CompareAction {
	/**
	 * 对比  XCH
	 * @param request
	 * @param response
	 * @param id
	 */
	@HttpAnno(module="commonrule.htm",log=HttpAnno.UNLOG)
	@RequestMapping("compare.htm")
	public String compare(HttpServletRequest request, HttpServletResponse response,CompareBean bean) {
		String jsp ="comparelist.jsp";
		Integer id =  bean.getId();
		String tablename =  bean.getTablename();
		if("".equals(id) || "".equals(tablename) ||  id==null || tablename==null){
			request.setAttribute("erro", "数据错误");
			return jsp;
		}
		FlowNodeMapper flowNodeMapper =ApplicationContextUtil.getMapper(FlowNodeMapper.class);
		HashMap<String, Object> map = flowNodeMapper.getAllByTableName(Integer.valueOf(id),tablename);
		if(!((Integer) map.get("status")==Dict.STATUS_6 || (Integer) map.get("status")==Dict.STATUS_5 || (Integer) map.get("status")==Dict.STATUS_7)){
			request.setAttribute("erro", "只能查看审核修改中的");
			return jsp;
		}
		Integer idpre = (Integer) map.get("idpre");
		HashMap<String, Object> mappre = flowNodeMapper.getAllByTableName(idpre,tablename);
		if(mappre==null){
			request.setAttribute("erro", "数据错误");
			return jsp;
		}
		HashMap<String, Object> comparemap = new HashMap<String, Object>();
		List<CompareBean> list  =  new ArrayList<>();
		
		//对比mappre 和 map
		HashSet<String> set = new HashSet<>();//不需要对比的
		set.add("employid");
		set.add("createtime");
		set.add("id");
		set.add("status");
		set.add("auditremark");
		set.add("idpre");
		set.add("code");
		set.add("updatetime");
		for (Map.Entry<String, Object> entry : map.entrySet()) {  
			if(!entry.getValue().equals(mappre.get(entry.getKey())) && !set.contains(entry.getKey())){//不同
				if(("".equals(entry.getValue())||entry.getValue()==null) && ( mappre.get(entry.getKey())==null||"".equals(mappre.get(entry.getKey())) )){
					
				}else{
					bean =  new CompareBean();
					bean.setValue(entry.getValue());
					bean.setValuepre(mappre.get(entry.getKey()));
					bean.setValuekey(entry.getKey());
					list.add(bean);
				}
			}
		}  
		request.setAttribute("keyname", tablename);
		request.setAttribute("list", list);
		return jsp;
	}
}

