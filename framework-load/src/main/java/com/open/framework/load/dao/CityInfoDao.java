package com.open.framework.load.dao;

import java.util.List;

import com.open.framework.load.dto.CityDTO;

/**
 * 城市dao
 * @author ke_xiong
 *
 */
public interface CityInfoDao {

	/**
	 * 获取所有城市列表
	 * @return
	 */
	List<CityDTO> getAllCity();

	/**
	 * 根据关键字查询城市列表
	 * @param keyword
	 * @return
	 */
	List<CityDTO> getCityListByKeyword(String keyword);

}
