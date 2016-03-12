package com.open.framework.idCreate.seq;

/**
 * 序列号生成器
 * @author ke_xiong
 *
 */
public interface SeqGenerator {

	/**
	 * 生成一个序列号
	 * @return
	 */
	String generatorSeqNo();

	/**
	 * 创建一组序列号
	 * @param num
	 * @return
	 */
	String[] generatorSeqNo(int num);

}
