package com.open.framework.cache.page;

/**
 * 根据id加载对象
 * @author ke_xiong
 *
 * @param <T>
 */
public interface LoadObjectById<T> {

	T load(String id);
	
}
