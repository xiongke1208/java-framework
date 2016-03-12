package com.open.framework.base.page;

import java.util.List;
import java.util.Map;


/**
 * 分页dto
 *
 */
public class PageDTO<T> implements java.io.Serializable{

	private static final long serialVersionUID = 5468738105958263810L;
	private long page;
    private long recordCount;
    private int pageSize;
	
    private List<T> result;

	/** 查询参数 **/
	private Map params;
    
    public PageDTO() {
        this(1L, 10);
    }

    public PageDTO(int pageSize) {
        this(1L, pageSize);
    }

    public PageDTO(long page, int pageSize) {
        this.page = 1L;
        recordCount = 0L;
        this.pageSize = Math.max(1, pageSize);
        setPage(page);
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = Math.max(1L, page);
    }

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
    	this.pageSize = Math.max(1, pageSize);
    }

    public long getPageCount() {
        return recordCount <= (long)pageSize ? 1L : ((recordCount + (long)pageSize) - 1L) / (long)pageSize;
    }

    public long getStartNum() {
		 return (getPage() - 1) * getPageSize();
	}
    
    public long getEndNum() {
		return (getPage()) * getPageSize();
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public Map getParams() {
		return params;
	}

	public void setParams(Map params) {
		this.params = params;
	}
    
}
