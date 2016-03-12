package com.open.framework.load.dto;


/**
 * 字典对象
 * 
 * @author ke_xiong
 * 
 */
public class DictDTO implements java.io.Serializable{

	private static final long serialVersionUID = 6337764043371832142L;
	
	
	/** 字典id **/
	private String id;
	/** 字典类型 **/
	private String typeCode;
	/** 字典类型描述 **/
	private String typeDesc;
	/** code **/
	private String dictCode;
	/** 名称 **/
	private String dictName;
	/** 删除标致 **/
	private String delFlag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}
