package com.open.framework.load.dao;

import java.util.List;

import com.open.framework.load.dto.DictDTO;

/**
 * 字典信息dao
 * @author ke_xiong
 *
 */
public interface DictInfoDao {

	/**
	 * 获取所有的字典信息
	 * @return
	 */
	List<DictDTO> getAllDict();
	
}
