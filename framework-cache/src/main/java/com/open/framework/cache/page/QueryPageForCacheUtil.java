package com.open.framework.cache.page;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.open.framework.base.page.PageDTO;
import com.open.framework.cache.service.face.CacheServiceFaced;

/**
 * 从缓存中分页查询数据,如果缓存中数据不全则拿数据库中的数据补齐
 * @author ke_xiong
 *
 */
public class QueryPageForCacheUtil {

	private CacheServiceFaced cacheServiceFaced;
	
	/**
	 * 从缓存与数据库中获取数据并分页
	 * @param idListKey 缓存中的idListKey
	 * @param key 缓存中的主题key
	 * @param cacheMaxSize 当要取的记录位置大于该值则需要从数据库中获取
	 * @param pageReq 分页对象
	 * @param objectLoader 将id转换成对象
	 * @param queryHandler 数据库查询操作
	 * @param delWrongData 如果idListKey中的数据不存在缓存则删除
	 * @return
	 */
	public <T>List<T> queryPage(
			String idListKey,
			String key,
			int cacheMaxSize,
			PageDTO pageReq,
			LoadObjectById<T> objectLoader,
			DBDataQueryHandler<T> queryHandler,
			boolean delWrongData)  {
		
		List<T> result = null;
		
		long cacheSize =  cacheServiceFaced.getSizeForList(idListKey, key);
		
		if(cacheSize > pageReq.getEndNum()-1) {
			
			result = loadObjectListFromCache(
					cacheServiceFaced,
					idListKey,
					key,
					pageReq.getStartNum(),
					pageReq.getEndNum()-1,
					objectLoader,
					delWrongData);
			
		} else if(cacheSize > pageReq.getStartNum()) {
			//可以从缓存中取到部份数据
			result = new ArrayList<T>();
			List tempList = loadObjectListFromCache(
					cacheServiceFaced, 
					idListKey, 
					key, 
					pageReq.getStartNum(),
					cacheSize-1,
					objectLoader,
					delWrongData);
			result.addAll(tempList);
			//从缓存中取到条数
			int row = (int)(cacheSize - pageReq.getStartNum());
			
			if(cacheSize >= cacheMaxSize) {
				//还有部份数据需要从db中获取
				long dbStartNum = pageReq.getStartNum() + row;
				List<T> dbList =  queryHandler.query(
						dbStartNum,
						pageReq.getEndNum());
				result.addAll(dbList);
			}
		} if(cacheSize >= cacheMaxSize) {
			//所有数据都需要从db中获取
			result = queryHandler.query(
					pageReq.getStartNum(),
					pageReq.getEndNum());
		}
		
		return result;
	}

	/**
	 * 从缓存中加载对象列表
	 * @param cacheServiceFaced
	 * @param idListKey
	 * @param key
	 * @param startNum
	 * @param endNum
	 * @param objectLoader
	 * @param delWrongData
	 * @return
	 * @throws Exception
	 */
	private static <T>List<T> loadObjectListFromCache(
			CacheServiceFaced cacheServiceFaced,
			String idListKey,
			String key,
			long startNum,
			long endNum,
			LoadObjectById<T> objectLoader,
			boolean delWrongData) {
		List<T> result = null;
		//可以从缓存中取到全部数据
		List<String> idList = 
				cacheServiceFaced.rangeForList(
						idListKey,
						key,
						startNum,
						endNum);
		
		//将idList装载成objList
		if(CollectionUtils.isNotEmpty(idList)) {
			result = new ArrayList<T>();
			for(int i=0; i<idList.size(); i++) {
				String id = idList.get(i);
				//加载对象
				T obj = objectLoader.load(id);
				if(obj != null) {
					result.add(obj);
				} else {
					if(delWrongData) {
						//如果该数据已不存在数据库中则删除
						cacheServiceFaced.delValueForList(
								idListKey, 
								key, 
								startNum+i);
					}
				}
			}
		}
		
		return result;
	}
	
	
}
