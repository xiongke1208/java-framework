package com.open.framework.load.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.open.framework.load.dao.DictInfoDao;
import com.open.framework.load.dto.DictDTO;

/**
 * 字典dao实现类
 * @author ke_xiong
 *
 */
public class DictInfoDaoImpl implements DictInfoDao{

	/**
	 * 用于读操作的Template
	 */
    private SqlMapClientTemplate readTemplate;

	/**
	 * 获取所有的字典信息
	 */
	public List<DictDTO> getAllDict() {
		return (List<DictDTO>) readTemplate.queryForList("getAllDict", Collections.EMPTY_MAP);
	}

}
