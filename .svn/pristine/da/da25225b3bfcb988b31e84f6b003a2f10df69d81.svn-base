package com.pg.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pg.bean.UserBean;
import com.pg.searchbean.UserSearchBean;
public interface UserMapper
{
/**
 * 新增
 */
public Integer addUser(UserBean bean);
/**
 * 删除
 */
public Integer delUser(UserBean bean);
/**
 * 批量删除
 */
public Integer delManyUser(UserBean bean);
/**
 * 修改
 */
public Integer updateUser(UserBean bean);
/**
 * 修改不为空的
 */
public Integer updateNeedUser(UserBean bean);
/**
 * 批量修改
 */
public Integer updateManyUser(UserBean bean);
/**
 * 查询所有
 */
public List<UserSearchBean> list(UserSearchBean bean);
/**
 * 查询所有条数
 */
public Integer listCount(UserSearchBean bean);
/**
 * 查询by关键字
 */
public UserSearchBean getById(UserSearchBean bean);
public UserSearchBean getByOpenid(UserSearchBean bean);
public UserSearchBean getBywxopenid(UserSearchBean bean);

public Integer updateSign(UserBean bean);//实名认证
public UserBean getByToken(@Param("token")String token);
public Integer updateToken(UserBean bean);

}