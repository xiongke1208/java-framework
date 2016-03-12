package com.open.framework.cache.service.face;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.open.framework.cache.service.CacheService;

/**
 * 缓存外观类，bug:新添加缓存服务时数据迁移会很不方便（等于要对缓存里的数据全部都重新整理分配），所以没改好前暂时不建议配置多个或直接使用CacheService类
 * @author ke_xiong
 * 
 */
public class CacheServiceFaced {

	private Log LOGGER = LogFactory.getLog(CacheServiceFaced.class);

	/** 缓存服务数量 **/
	private int cacheServerNum = 1;

	/** 缓存服务容器 **/
	private Map<String, CacheService> cacheServiceMap;

	private String cachePrefix;

	/**
	 * 存储缓存数据
	 * 
	 * @param prefix
	 *            前缀
	 * @param key
	 *            唯一标识符
	 * @param data
	 *            存储对象
	 * @param expriation
	 *            过期时间秒
	 */
	public void setData(String prefix, String key, Serializable data,
			int expriation) {
		// 根据唯一标符计算使用哪个缓存服务
		CacheService cacheService = getCahceService(key);
		// 缓存数据
		cacheService.setData(cachePrefix + prefix + key, data, expriation);
	}

	/**
	 * 删除缓存数据
	 * 
	 * @param prefix
	 *            前缀
	 * @param key
	 *            唯一标识符
	 */
	public void deleteData(String prefix, String key) {
		// 根据唯一标符计算使用哪个缓存服务
		CacheService cacheService = getCahceService(key);
		// 删除缓存数据
		cacheService.deleteData(cachePrefix + prefix + key);
	}

	/**
	 * 获取缓存数据
	 * 
	 * @param prefix
	 *            前缀
	 * @param key
	 *            唯一标识符
	 * @return
	 */
	public Serializable getData(String prefix, String key) {
		// 根据唯一标符计算使用哪个缓存服务
		CacheService cacheService = getCahceService(key);
		// 获取缓存数据
		return cacheService.getData(cachePrefix + prefix + key);
	}

	/**
	 * 添加值到List,并维护指定数量的大小，超过则删除最旧的数据，并返回删除的对象
	 * 
	 * @param prefix
	 *            前缀
	 * @param key
	 *            唯一标识符
	 * @param maxSize
	 *            最大个数，为空则不限制
	 * @param expriation
	 *            过期时间
	 */
	public Object addValueForList(String prefix, String key, Integer maxSize,
			Serializable data, int expriation) {
		// 根据唯一标符计算使用哪个缓存服务
		CacheService cacheService = getCahceService(key);
		return cacheService.addValueForList(cachePrefix + prefix + key,
				maxSize, data, expriation);
	}

	/**
	 * 设置list中的某个值
	 * 
	 * @param prefix
	 *            前缀
	 * @param key
	 *            唯一标识符
	 * @param index
	 *            最大个数，为空则不限制
	 * @param expriation
	 *            过期时间
	 */
	public void setValueForList(String prefix, String key, long index,
			Serializable data, int expriation) {
		// 根据唯一标符计算使用哪个缓存服务
		CacheService cacheService = getCahceService(key);
		cacheService.setValueForList(cachePrefix + prefix + key, index,
				data, expriation);
	}

	/**
	 * 获取指定范围的list
	 * 
	 * @param prefix
	 *            前缀
	 * @param key
	 *            唯一标识符
	 * @param start
	 *            从第几个开始取
	 * @param end
	 *            取到第几个
	 * @return
	 */
	public List rangeForList(String prefix, String key, long start, long end) {
		// 根据唯一标符计算使用哪个缓存服务
		CacheService cacheService = getCahceService(key);
		return cacheService.rangeForList(cachePrefix + prefix + key,
				start, end);
	}

	/**
	 * 获取list
	 * 
	 * @param prefix
	 *            前缀
	 * @param key
	 *            唯一标识符
	 * @return
	 */
	public List getListForList(String prefix, String key) {
		// 根据唯一标符计算使用哪个缓存服务
		CacheService cacheService = getCahceService(key);
		return cacheService.getListForList(cachePrefix + prefix + key);
	}

	/**
	 * 获取当前list中对象数量
	 * 
	 * @param prefix
	 * @param key
	 * @return
	 */
	public long getSizeForList(String prefix, String key) {
		// 根据唯一标符计算使用哪个缓存服务
		CacheService cacheService = getCahceService(key);
		return cacheService.getSizeForList(cachePrefix + prefix + key);
	}

	/**
	 * 从list中删除指定对象
	 * 
	 * @param prefix
	 *            前缀
	 * @param key
	 *            唯一标识符
	 * @param index
	 *            删除第几个
	 * @return
	 */
	public void delValueForList(String prefix, String key, long index) {
		// 根据唯一标符计算使用哪个缓存服务
		CacheService cacheService = getCahceService(key);
		cacheService.delValueForList(cachePrefix + prefix + key, index);
	}

	/**
	 * 添加值到Map
	 * 
	 * @param prefix
	 *            前缀
	 * @param key
	 *            唯一标识符
	 * @param mapKey
	 *            存入map中的key
	 * @param data
	 *            存入map中的value
	 * @param expriation
	 *            过期时间
	 */
	public void setValueForMap(String prefix, String key, String mapKey,
			Serializable data, int expriation) {
		// 根据唯一标符计算使用哪个缓存服务
		CacheService cacheService = getCahceService(key);
		cacheService.setValueForMap(cachePrefix + prefix + key, mapKey,
				data, expriation);
	}

	/**
	 * 从Map中获取值
	 * 
	 * @param prefix
	 *            前缀
	 * @param key
	 *            唯一标识符
	 * @param mapKey
	 *            存入map中的key
	 * @return
	 */
	public Object getValueForMap(String prefix, String key, String mapKey) {
		// 根据唯一标符计算使用哪个缓存服务
		CacheService cacheService = getCahceService(key);
		return cacheService.getValueForMap(cachePrefix + prefix + key,
				mapKey);
	}

	/**
	 * 从Map中删除某值
	 * 
	 * @param prefix
	 *            前缀
	 * @param key
	 *            唯一标识符
	 * @param mapKey
	 *            存入map中的key
	 * @return
	 */
	public void delValueForMap(String prefix, String key, String mapKey) {
		// 根据唯一标符计算使用哪个缓存服务
		CacheService cacheService = getCahceService(key);
		cacheService.delValueForMap(cachePrefix + prefix + key, mapKey);
	}

	/**
	 * 获取整个map
	 * 
	 * @param prefix
	 *            前缀
	 * @param key
	 *            唯一标识符
	 * @return
	 */
	public Map getMapForMap(String prefix, String key) {
		// 根据唯一标符计算使用哪个缓存服务
		CacheService cacheService = getCahceService(key);
		return cacheService.getMapForMap(cachePrefix + prefix + key);
	}

	/**
	 * 增长缓存时间
	 * 
	 * @param prefix
	 * @param key
	 * @param incr
	 * @return
	 */
	public long incrData(String prefix, String key, int incr, int expriation) {
		// 根据唯一标符计算使用哪个缓存服务
		CacheService cacheService = getCahceService(key);
		// 增长缓存时间
		return cacheService.incrData(cachePrefix + prefix + key, incr,
				expriation);
	}

	/**
	 * 根据唯一标符计算使用哪个缓存服务
	 * 
	 * @param key
	 * @return
	 */
	private CacheService getCahceService(String key) {
		int hashCode = key.hashCode();
		int i = Math.abs(hashCode) % cacheServerNum;
		CacheService cacheService = cacheServiceMap.get(String.valueOf(i));
		if (cacheService == null) {
			LOGGER.error("没有找到对应的缓存服务，key值：" + key);
			cacheService = cacheServiceMap.get("0");
		}
		return cacheService;
	}

	public void setCacheServerNum(int cacheServerNum) {
		this.cacheServerNum = cacheServerNum;
	}

	public void setCacheServiceMap(Map<String, CacheService> cacheServiceMap) {
		this.cacheServiceMap = cacheServiceMap;
	}

	public void setCachePrefix(String cachePrefix) {
		this.cachePrefix = cachePrefix;
	}
	
}
