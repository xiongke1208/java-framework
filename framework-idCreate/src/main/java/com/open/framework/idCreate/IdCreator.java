package com.open.framework.idCreate;

/**
 * id生成器
 * @author ke_xiong
 *
 */
public interface IdCreator {

	/**
	 * 生成一个id
	 * @return
	 * @throws Exception 
	 */
	String create ();
	
	/**
	 * 生成一组id
	 * @param num
	 * @return
	 * @throws Exception 
	 */
	String [] create(int num);
	
}
