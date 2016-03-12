package com.open.framework.idCreate.seq.impl;

import com.open.framework.cache.service.face.CacheServiceFaced;
import com.open.framework.idCreate.seq.SeqGenerator;

/**
 * 序列号生成器 基于缓存服务的实现方案
 * @author ke_xiong
 *
 */
public class SeqGeneratorCacheImpl implements SeqGenerator{

	/** id类型 **/
	private String idType;
	/** 缓存外观类 **/
	private CacheServiceFaced cacheServiceFaced;
	
	/** id缓存key **/
	public static final String ID_CREATE = "idCreate_";
	
	/**
	 * 从缓存中生成序列号
	 * @return
	 * @throws Exception
	 */
	public String generatorSeqNo() {
		//最长4位，超过继续从0开始算
		int len = 4;
		Integer seqNo = (Integer)cacheServiceFaced.getData(ID_CREATE, idType);
		//为空或超过最大值 则序列号继续从0开始
		if(seqNo == null || String.valueOf(seqNo).length()>len) {
			seqNo = 1;
		}
		//序列号从1开始，存入缓存过期时间暂为1小时
		cacheServiceFaced.setData(ID_CREATE, idType, seqNo+1, 1000*60*60);
		return fillSeqNo(seqNo);
	}
	
	/**
	 * 从缓存中生成序列号
	 * @return
	 * @throws Exception
	 */
	public String[] generatorSeqNo(int num) {
		String[] result = new String[num];
		//最长4位，超过继续从0开始算
		int len = 4;
		Integer seqNo = (Integer)cacheServiceFaced.getData(
				ID_CREATE, idType);
		//为空或超过最大值 则序列号继续从0开始
		if(seqNo == null || String.valueOf(seqNo+num-1).length()>len) {
			seqNo = 1;
		}
		//序列号从1开始，存入缓存过期时间暂为1小时
		cacheServiceFaced.setData(
				ID_CREATE, 
				idType, seqNo+num, 1000*60*60);
		for(int i=0; i<num; i++) {
			result[i]=fillSeqNo(seqNo+i);
		}
		return result;
	}
	
	private String fillSeqNo(Integer seqNo) {
		StringBuffer sb = new StringBuffer(String.valueOf(seqNo));
		if(sb.length()<4) {
			for (int i=sb.length(); i<4; i++) {
				sb.insert(0, "0");
			}
		}
		return sb.toString();
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public CacheServiceFaced getCacheServiceFaced() {
		return cacheServiceFaced;
	}

	public void setCacheServiceFaced(CacheServiceFaced cacheServiceFaced) {
		this.cacheServiceFaced = cacheServiceFaced;
	}
	
}
