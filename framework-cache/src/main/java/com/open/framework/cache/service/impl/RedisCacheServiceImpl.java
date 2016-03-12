package com.open.framework.cache.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisOperations;

import com.open.framework.cache.service.CacheService;

public class RedisCacheServiceImpl implements CacheService {
	protected RedisOperations<String, Object> redisTemplate;

	public void setData(String key, Serializable data, int expriation) {
		BoundValueOperations<String, Object> boundValueOps = redisTemplate.boundValueOps("memcachedmock." + key);
		boundValueOps.set(data);
		boundValueOps.expire(expriation, TimeUnit.SECONDS);
	}

	public void deleteData(String key) {
		redisTemplate.delete("memcachedmock." + key);
	}

	public Serializable getData(String key) {
		return (Serializable) redisTemplate.opsForValue().get("memcachedmock." + key);
	}

	public long incrData(String key, int incr, int expriation) {
		BoundValueOperations<String, Object> boundValueOps = 
				redisTemplate.boundValueOps("memcachedmock." + key);
		Long increment = boundValueOps.increment(incr);
		boundValueOps.expire(expriation, TimeUnit.SECONDS);
		return increment;
	}

	public Object addValueForList(String key, Integer maxSize, Serializable data, int expriation) {
		Object result = null;
		
		BoundListOperations boundListOps = 
				redisTemplate.boundListOps("memcachedmock." + key);
		long size = boundListOps.size();
		boundListOps.leftPush(data);
		if(maxSize != null && size >= maxSize) {//只保留指定位数
			result = boundListOps.rightPop();
		}
		boundListOps.expire(expriation, TimeUnit.SECONDS);
		return result;
	}

	
	public void setValueForList(String key, long index, 
			Serializable data, int expriation) {
		BoundListOperations boundListOps = 
				redisTemplate.boundListOps("memcachedmock." + key);
		boundListOps.set(index, data);
		boundListOps.expire(expriation, TimeUnit.SECONDS);
	}

	
	public List rangeForList(String key, long start, long end) {
		BoundListOperations boundListOps = 
				redisTemplate.boundListOps("memcachedmock." + key);
		return boundListOps.range(start, end);
	}

	
	public List getListForList(String key) {
		BoundListOperations boundListOps = 
				redisTemplate.boundListOps("memcachedmock." + key);
		return boundListOps.range(0, boundListOps.size());
	}

	
	public long getSizeForList(String key) {
		BoundListOperations boundListOps = 
				redisTemplate.boundListOps("memcachedmock." + key);
		return boundListOps.size();
	}
	
	
	public void delValueForList(String key, long index) {
		BoundListOperations boundListOps = 
				redisTemplate.boundListOps("memcachedmock." + key);
		Object o = boundListOps.index(index);
		boundListOps.remove(index, o);
	}

	
	public void setValueForMap(String key, 
			String mapKey, Serializable data, int expriation) {
		BoundHashOperations boundHashOps = 
				redisTemplate.boundHashOps("memcachedmock." + key);
		boundHashOps.put(mapKey, data);
		boundHashOps.expire(expriation, TimeUnit.SECONDS);
	}

	
	public Object getValueForMap(String key, String mapKey) {
		BoundHashOperations boundHashOps = 
				redisTemplate.boundHashOps("memcachedmock." + key);
		return boundHashOps.get(mapKey);
	}

	
	public void delValueForMap(String key, String mapKey) {
		BoundHashOperations boundHashOps = 
				redisTemplate.boundHashOps("memcachedmock." + key);
		boundHashOps.delete(mapKey);
	}

	
	public Map getMapForMap(String key) {
		BoundHashOperations boundHashOps = 
				redisTemplate.boundHashOps("memcachedmock." + key);
		return boundHashOps.entries();
	}
	
	public void setRedisTemplate(RedisOperations<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

}
