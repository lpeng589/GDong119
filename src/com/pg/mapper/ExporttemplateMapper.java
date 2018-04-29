package com.pg.mapper;

import com.pg.bean.ExporttemplateBean;
/**
 * 导出模板mapper
 * @author Administrator
 *
 */
public interface ExporttemplateMapper {



/**
 * 根据导出模板key找出模板bean
 * @param key
 * @return
 */
public ExporttemplateBean getByKey(String key);


}