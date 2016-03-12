package com.open.framework.cache.page;

import java.util.List;

/**
 * 数据库查询handler
 * @author ke_xiong
 *
 * @param <T>
 */
public interface DBDataQueryHandler<T> {

	/**
	 * 查询数据列表
	 * @param start 开始页
	 * @param end 结束页
	 * @return
	 */
	List<T> query(long start, long end);
	
}
