package com.open.framework.load.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 城市对象
 * 
 * @author ke_xiong
 * 
 */
public class CityDTO implements java.io.Serializable {

	private static final long serialVersionUID = 6913380338861744373L;
	
	/** 城市id **/
	private String id;
	/** 首字母 **/
	private String firstLetter;
	/** 首字母 **/
	private String name;
	/** 删除标致 **/
	private String delFlag;
	/** 父id **/
	private String fatherId;
	/** 城市类别 省市区 **/
	private String classType;
	/** 子级城市列表 **/
	private List<CityDTO> childrenList;
	
	/**
	 * 添加子城市到该城市下
	 * @param city
	 */
	public void addChildCity(CityDTO city) {
		childrenList = childrenList==null?new ArrayList():childrenList;
		childrenList.add(city);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getFatherId() {
		return fatherId;
	}

	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}

	public List<CityDTO> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<CityDTO> childrenList) {
		this.childrenList = childrenList;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}
	
	
	public enum CityType {
		/**
		 * 省
		 */
		POST_AREA_CLASS_TYPE_PROVINCE,
		/**
		 * 市
		 */
		POST_AREA_CLASS_TYPE_CITY,
		/**
		 * 区
		 */
		POST_AREA_CLASS_TYPE_DISTRICT;
	}
	
}
