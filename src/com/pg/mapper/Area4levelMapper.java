package com.pg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pg.bean.Area4levelBean;

public interface Area4levelMapper {
	public Integer addArea4level(Area4levelBean bean);
	public Integer delArea4level(@Param("id")Integer id);
	public Integer updateArea4level(Area4levelBean bean);
	public List<Area4levelBean> list(Area4levelBean bean);
	public Integer listCount(Area4levelBean bean);
	public Area4levelBean getById(@Param("id")Integer id);
}
