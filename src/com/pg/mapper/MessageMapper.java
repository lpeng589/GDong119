package com.pg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pg.bean.MessageBean;
import com.pg.bean.MessageModelBean;
import com.pg.searchbean.MessageSearchBean;

/**
 * 消息处理
 * @author Administrator
 *
 */
public interface MessageMapper {
	
	/**
	 * 消息列表
	 * @param bean
	 * @return
	 */
	public List<MessageBean> list(MessageSearchBean bean);
	
	/**
	 * 消息数量
	 * @param bean
	 * @return
	 */
	public int listCount(MessageSearchBean bean);
	
	/**
	 * 添加消息
	 * @param bean
	 * @return
	 */
	public int add(MessageBean bean);
	
	/**
	 * 删除消息
	 * @param ids
	 * @return
	 */
	public int delete(MessageSearchBean bean);
	
	/**
	 * 设为已读
	 * @param ids
	 * @return
	 */
	public int updatestatus(MessageSearchBean bean);
	public int updateAllstatus(MessageSearchBean bean);
	/**
	 * 通过id获取消息
	 * @param id
	 * @return
	 */
	public MessageSearchBean getById(@Param("id")String id);
	
	public List<MessageModelBean> listModel();
}
