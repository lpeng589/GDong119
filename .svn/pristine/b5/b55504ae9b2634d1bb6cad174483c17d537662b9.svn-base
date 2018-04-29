package com.pg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pg.bean.ModuleAttrBean;
import com.pg.bean.ModuleBean;



public interface ModuleMapper {
/**
 * 获得模块列表
 */
public static String getModule="getModule";
/**
 * 获得所有模块列表
 */
public static String getAllModule="getAllModule";
/**
 * 获得对应模块日志属性
 */
public static String getLogParam="getLogParam";
/**
 * 获得所有权限模块
 */
public static String getAllCategory="getAllCategory";
/**
 * 获得模块列表
 * @param userid
 * @return
 */
public List<ModuleBean> getModule(@Param("roleids")String roleids);
/**
 * 获得所有模块列表
 * @param userid
 * @return
 */
public List<ModuleBean> getAllModule();
/**
 * 获得所有权限模块
 * @return
 */
public List<String> getAllCategory();

/**
 * 获得菜单列表
 * @param userid
 * @return
 */
public List<ModuleBean> getAllMenu();
/**
 * 获得Category
 * @param userid
 * @return
 */
public List<ModuleBean> getCategory();


}