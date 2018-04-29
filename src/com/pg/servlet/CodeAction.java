package com.pg.servlet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.pg.bean.MsgBean;
import com.pg.bean.CodeBean;
import com.pg.mapper.CodeMapper;
import com.pg.searchbean.CodeSearchBean;
import com.pg.util.ApplicationContextUtil;
import com.pg.util.Config;
import com.pg.util.HttpAnno;
import com.pg.util.ServletUtil;
import com.pg.xcx.createwxaqrcode.CreatewxaqrCode;
import com.pg.xcx.createwxaqrcode.CreatewxaqrCodeBean;
@Controller
@RequestMapping("code.htm")
public class CodeAction extends BaseAction
{
  /**
   * 列表
   */
  @HttpAnno(value=HttpAnno.VIEW,module="code_list",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=list")
  public String list(HttpServletRequest request, HttpServletResponse response,CodeSearchBean bean) {
	super.getList(request, bean);
    return "codelist.jsp";
  }
  /**
   * 新增
   */
  @HttpAnno(module="code_add",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=add")
  public void add(HttpServletRequest request, HttpServletResponse response,CodeSearchBean bean) {
    CodeMapper mapper =ApplicationContextUtil.getMapper(CodeMapper.class);
    int result=mapper.addCode(bean);
    if(result>0){
    	CreatewxaqrCode template = new CreatewxaqrCode(Config.getWxcxKey());
    	String savePath = request.getServletContext().getRealPath("/") + "attached/code/";
    	Boolean aBoolean =template.create("pages/index/index?key="+bean.getId(), savePath+bean.getId()+".jpg");
    	bean.setUrl( "attached/code/"+bean.getId()+".jpg");
    	if(!aBoolean){
    	      ServletUtil.sendJsonBean(response, new MsgBean("-1", "新增失败：二维码生成失败"));
    	      return;
    	}
    	mapper.updateUrl(bean);
      ServletUtil.sendJsonBean(response, new MsgBean("0", "新增成功"));
      return;
    }else{
      ServletUtil.sendJsonBean(response, new MsgBean("-1", "新增失败"));
      return;
    }
  }
  /**
   * 删除
   */
  @HttpAnno(module="code_delete")
  @RequestMapping(params = "operate=delete")
  public void delete(HttpServletRequest request, HttpServletResponse response,CodeSearchBean bean) {
    CodeMapper mapper =ApplicationContextUtil.getMapper(CodeMapper.class);
    if(mapper.getCount(bean)>0){
    	 ServletUtil.sendJsonBean(response, new MsgBean("-1", "删除失败,已被扫描过的二维码不能删除"));
         return;
    }
    int result=mapper.delCode(bean);
    if(result>0){
      ServletUtil.sendJsonBean(response, new MsgBean("0", "删除成功"));
      return;
    }else{
      ServletUtil.sendJsonBean(response, new MsgBean("-1", "删除失败"));
      return;
    }
  }
  /**
   * 批量删除
   */
 /* @HttpAnno(module="code_delete")
  @RequestMapping(params = "operate=deletemany")
  public void deletemany(HttpServletRequest request, HttpServletResponse response,CodeSearchBean bean) {
    CodeMapper mapper =ApplicationContextUtil.getMapper(CodeMapper.class);
    int result=mapper.delManyCode(bean);
    if(result>0){
      ServletUtil.sendJsonBean(response, new MsgBean("0", "删除成功"));
      return;
    }else{
      ServletUtil.sendJsonBean(response, new MsgBean("-1", "删除失败"));
      return;
    }
  }*/
  /**
   * 修改前
   */
  @HttpAnno(module="code_update",log=HttpAnno.UNLOG)
  @RequestMapping(params = "operate=updatepre")
  public void updatepre(HttpServletRequest request, HttpServletResponse response,CodeSearchBean bean) {
    CodeMapper mapper =ApplicationContextUtil.getMapper(CodeMapper.class);
    ServletUtil.sendJsonBean(response,new MsgBean(mapper.getById(bean)));
  }
  /**
   * 修改
   */
  @HttpAnno(module="code_update")
  @RequestMapping(params = "operate=update")
  public void update(HttpServletRequest request, HttpServletResponse response,CodeSearchBean bean) {
    CodeMapper mapper =ApplicationContextUtil.getMapper(CodeMapper.class);
    CodeSearchBean bean2 = mapper.getById(bean);
    if (bean2!=null) {
       int result=mapper.updateCode(bean);
       if(result>0){
          ServletUtil.sendJsonBean(response, new MsgBean("0", "修改成功"));
          return;
        }else{
          ServletUtil.sendJsonBean(response, new MsgBean("-1", "修改失败"));
          return;
        }
    }else{
      ServletUtil.sendJsonBean(response, new MsgBean("-1", "记录不存在"));
      return;
    }
  }
  /**
   * 批量修改
   */
 /* @HttpAnno(module="code_update")
  @RequestMapping(params = "operate=_updatemany")
  public void updatemany(HttpServletRequest request, HttpServletResponse response,CodeSearchBean bean) {
   CodeMapper mapper =ApplicationContextUtil.getMapper(CodeMapper.class);
     int result=mapper.updateManyCode(bean);
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
		CodeMapper mapper = ApplicationContextUtil.getMapper(CodeMapper.class);
		return mapper.listCount((CodeSearchBean)cond);
	}
	@Override
 	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<Object> getListData(Object cond)
	{
		CodeMapper mapper = ApplicationContextUtil.getMapper(CodeMapper.class);
		 return (List)mapper.list((CodeSearchBean)cond);
	}

}