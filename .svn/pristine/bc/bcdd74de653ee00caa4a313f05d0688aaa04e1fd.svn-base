package com.pg.servlet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pg.bean.MsgBean;
import com.pg.bean.CodedetailBean;
import com.pg.mapper.CodedetailMapper;
import com.pg.searchbean.CodedetailSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.HttpAnno;
import com.pg.util.ServletUtil;
@Controller
@RequestMapping("codedetail.htm")
public class CodedetailAction extends BaseAction
{
  /**
   * 列表
   */
  @HttpAnno(value=HttpAnno.VIEW,module="code_list",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=list")
  public String list(HttpServletRequest request, HttpServletResponse response,CodedetailSearchBean bean) {
    super.getList(request, bean);
    return "codedetaillist.jsp";
  }
  /**
   * 新增
   */
 /* @HttpAnno(module="codedetail_add",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=add")
  public void add(HttpServletRequest request, HttpServletResponse response,CodedetailSearchBean bean) {
    CodedetailMapper mapper =ApplicationContextUtil.getMapper(CodedetailMapper.class);
    int result=mapper.addCodedetail(bean);
    if(result>0){
      ServletUtil.sendJsonBean(response, new MsgBean("0", "新增成功"));
      return;
    }else{
      ServletUtil.sendJsonBean(response, new MsgBean("0", "新增失败"));
      return;
    }
  }*/
  /**
   * 删除
   */
 /* @HttpAnno(module="codedetail_delete")
  @RequestMapping(params = "operate=delete")
  public void delete(HttpServletRequest request, HttpServletResponse response,CodedetailSearchBean bean) {
    CodedetailMapper mapper =ApplicationContextUtil.getMapper(CodedetailMapper.class);
    int result=mapper.delCodedetail(bean);
    if(result>0){
      ServletUtil.sendJsonBean(response, new MsgBean("0", "删除成功"));
      return;
    }else{
      ServletUtil.sendJsonBean(response, new MsgBean("0", "删除失败"));
      return;
    }
  }*/
  /**
   * 批量删除
   */
 /* @HttpAnno(module="codedetail_delete")
  @RequestMapping(params = "operate=deletemany")
  public void deletemany(HttpServletRequest request, HttpServletResponse response,CodedetailSearchBean bean) {
    CodedetailMapper mapper =ApplicationContextUtil.getMapper(CodedetailMapper.class);
    int result=mapper.delManyCodedetail(bean);
    if(result>0){
      ServletUtil.sendJsonBean(response, new MsgBean("0", "删除成功"));
      return;
    }else{
      ServletUtil.sendJsonBean(response, new MsgBean("0", "删除失败"));
      return;
    }
  }*/
  /**
   * 修改前
   */
 /* @HttpAnno(module="codedetail_update",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=updatepre")
  public void updatepre(HttpServletRequest request, HttpServletResponse response,CodedetailSearchBean bean) {
    CodedetailMapper mapper =ApplicationContextUtil.getMapper(CodedetailMapper.class);
    ServletUtil.sendJsonBean(response,new MsgBean(mapper.getById(bean)));
  }*/
  /**
   * 修改
   */
 /* @HttpAnno(module="codedetail_update")
  @RequestMapping(params = "operate=update")
  public void update(HttpServletRequest request, HttpServletResponse response,CodedetailSearchBean bean) {
    CodedetailMapper mapper =ApplicationContextUtil.getMapper(CodedetailMapper.class);
    CodedetailSearchBean bean2 = mapper.getById(bean);
    if (bean2!=null) {
       int result=mapper.updateCodedetail(bean);
       if(result>0){
          ServletUtil.sendJsonBean(response, new MsgBean("0", "修改成功"));
          return;
        }else{
          ServletUtil.sendJsonBean(response, new MsgBean("0", "修改失败"));
          return;
        }
    }else{
      ServletUtil.sendJsonBean(response, new MsgBean("0", "记录不存在"));
      return;
    }
  }*/
  /**
   * 批量修改
   */
 /* @HttpAnno(module="codedetail_update")
  @RequestMapping(params = "operate=_updatemany")
  public void updatemany(HttpServletRequest request, HttpServletResponse response,CodedetailSearchBean bean) {
   CodedetailMapper mapper =ApplicationContextUtil.getMapper(CodedetailMapper.class);
     int result=mapper.updateManyCodedetail(bean);
     if(result>0){
        ServletUtil.sendJsonBean(response, new MsgBean("0", "修改成功"));
        return;
      }else{
        ServletUtil.sendJsonBean(response, new MsgBean("0", "修改失败"));
        return;
      }
  }*/
	@Override
 	protected Integer getListCount(Object cond)
	{
		CodedetailMapper mapper = ApplicationContextUtil.getMapper(CodedetailMapper.class);
		return mapper.listCount((CodedetailSearchBean)cond);
	}
	@Override
 	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<Object> getListData(Object cond)
	{
		CodedetailMapper mapper = ApplicationContextUtil.getMapper(CodedetailMapper.class);
		 return (List)mapper.list((CodedetailSearchBean)cond);
	}

}