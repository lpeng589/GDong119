package com.pg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pg.bean.ConfigBean;

public interface ConfigMapper {



/**
 * 查找配置值
 * @return
 */
public List<ConfigBean> list();
/**
 * 根据表名获得最大code
 * @param tableName
 * @return
 */
public String getMaxCode(@Param("tableName")String tableName,@Param("head")String head);

public int lockstock(ConfigBean bean);
/**
 * 根据ID获得code
 * @param tableName
 * @return
 */
public String getCodeById(@Param("tableName")String tableName,@Param("id")Integer id);
public String getMaxlikeCode(@Param("tableName")String tableName,@Param("code")String code);

}