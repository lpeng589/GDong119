package com.pg.servlet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import com.pg.servlet.BaseAction;
import com.pg.util.ApplicationContextUtil;
import com.pg.bean.ParamvalueBean;
import com.pg.mapper.ParamvalueMapper;
import com.pg.searchbean.ParamvalueSearchBean;
@Controller
public class ParamvalueAction extends BaseAction
{
	@Override
 	protected Integer getListCount(Object cond)
	{
		ParamvalueMapper mapper = ApplicationContextUtil.getMapper(ParamvalueMapper.class);
		return mapper.listCount((ParamvalueSearchBean)cond);
	}
	@Override
 	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<Object> getListData(Object cond)
	{
		ParamvalueMapper mapper = ApplicationContextUtil.getMapper(ParamvalueMapper.class);
		 return (List)mapper.list((ParamvalueSearchBean)cond);
	}

}