package com.open.framework.idCreate.impl;

import java.util.Date;

import com.open.framework.base.util.DateUtil;
import com.open.framework.idCreate.IdCreator;
import com.open.framework.idCreate.seq.SeqGenerator;

public class IdCreatorImpl implements IdCreator{

	/** id前缀 **/
	private String prefix;
	/** 时间戳格式 **/
	private static final String textFormat = "yyyyMMddHHmmssS";
	/** 序列号生成器 **/
	private SeqGenerator seqGenerator;
	
	public String create() {
		StringBuffer sb = new StringBuffer();
		//前缀
		sb.append(prefix);
		//时间戳
		sb.append(DateUtil.getDateFormate(new Date(), textFormat));
		//序列号
		sb.append(seqGenerator.generatorSeqNo());
		return sb.toString();
	}

	/**
	 * 生成一组id
	 * @throws Exception 
	 */
	public String[] create(int num) {
		String [] result = new String[num];
		
		String[] seqNo = seqGenerator.generatorSeqNo(num);
		for (int i=0; i<num; i++) {
			StringBuffer sb = new StringBuffer();
			//前缀
			sb.append(prefix);
			//时间戳
			sb.append(DateUtil.getDateFormate(new Date(), textFormat));
			//序列号
			sb.append(seqNo[i]);
			result[i] = sb.toString();
		}
		
		return result;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public SeqGenerator getSeqGenerator() {
		return seqGenerator;
	}

	public void setSeqGenerator(SeqGenerator seqGenerator) {
		this.seqGenerator = seqGenerator;
	}

}
