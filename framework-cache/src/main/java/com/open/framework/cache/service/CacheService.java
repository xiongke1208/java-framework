package com.open.framework.cache.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface CacheService {

	/**
	 ******************************************************************************* 
	 * @function : 向缓存存放数据。<br/>
	 * @param key
	 *            数据的key标识
	 * @param data
	 *            数据
	 * @param expriation
	 *            过期时间，单位为秒
	 ******************************************************************************* 
	 */
	void setData(String key, Serializable data, int expriation);

	/**
	 ******************************************************************************* 
	 * @function : 删除缓存数据。<br/>
	 * @param key
	 ******************************************************************************* 
	 */
	void deleteData(String key);

	/**
	 ******************************************************************************* 
	 * @function : 从缓存取得数据。<br/>
	 * @param key
	 *            数据的key标识
	 * @return
	 ******************************************************************************* 
	 */
	Serializable getData(String key);

	/**
	 ******************************************************************************* 
	 * @function : 缓存数据加值。<br/>
	 *           如果数据不是数字会报错，请注意。<br/>
	 * @param key
	 *            数据的key标识
	 * @param incr
	 *            加值的大小
	 * @return
	 ******************************************************************************* 
	 */
	long incrData(String key, int incr, int expriation);

	/**
	 * 添加值到list,并维护指定数量的大小，超过则删除最旧的数据，并返回删除的对象
	 * @param key
	 * @param maxSize
	 * @param data
	 */
	Object addValueForList(String key, 
			Integer maxSize, Serializable data, int expriation);

	/**
	 * 设定list中的某个值
	 * @param key
	 * @param index
	 * @param data
	 * @param expriation 
	 */
	void setValueForList(String key, 
			long index, Serializable data, int expriation);

	/**
	 * 获取指定范围内list
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	List rangeForList(String key, long start, long end);

	/**
	 * 获取整个list
	 * @param key
	 * @return
	 */
	List getListForList(String key);

	/**
	 * 删除list中的某个值
	 * @param key
	 * @param index
	 * @return
	 */
	void delValueForList(String key, long index);

	/**
	 * 设置key-value到map
	 * @param key
	 * @param mapKey
	 * @param data
	 * @param expriation 
	 */
	void setValueForMap(String key,
			String mapKey, Serializable data, int expriation);

	/**
	 * 从map中获取某个key值
	 * @param key
	 * @param mapKey
	 * @return
	 */
	Object getValueForMap(String key, String mapKey);

	/**
	 * 从map中删除某个值
	 * @param key
	 * @param mapKey
	 * @return
	 */
	void delValueForMap(String key, String mapKey);

	/**
	 * 获取整个map
	 * @param key
	 * @return
	 */
	Map getMapForMap(String key);

	/**
	 * 获取list中的对象数量
	 * @param string
	 * @return
	 */
	long getSizeForList(String string);

}
