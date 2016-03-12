package com.open.framework.load.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.open.framework.load.dao.CityInfoDao;
import com.open.framework.load.dto.CityDTO;

/**
 * 城市dao实现
 * @author ke_xiong
 *
 */
public class CityInfoDaoImpl implements CityInfoDao{

	/**
	 * 用于读操作的Template
	 */
    private SqlMapClientTemplate readTemplate;
    
	/**
	 * 获取所有的城市
	 */
	public List<CityDTO> getAllCity() {
		return (List<CityDTO>) readTemplate.queryForList("getAllCity", null);
	}

	/**
	 * 根据关键字查询城市列表
	 */
	public List<CityDTO> getCityListByKeyword(String keyword) {
		Map params = new HashMap();
		params.put("keyword", keyword);
		return (List<CityDTO>) readTemplate.queryForList("getCityListByKeyword", params);
	}

	public SqlMapClientTemplate getReadTemplate() {
		return readTemplate;
	}

	public void setReadTemplate(SqlMapClientTemplate readTemplate) {
		this.readTemplate = readTemplate;
	}

	
}
