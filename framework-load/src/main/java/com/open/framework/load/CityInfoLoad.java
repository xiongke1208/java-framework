package com.open.framework.load;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.open.framework.load.dao.CityInfoDao;
import com.open.framework.load.dto.CityDTO;
import com.open.framework.load.dto.CityDTO.CityType;

/**
 * 城市信息加载
 * 
 * @author ke_xiong
 * 
 */
public class CityInfoLoad {

	private CityInfoDao cityInfoDao;

	/** 第一级城市列表 **/
	private List<CityDTO> firstLevelList = new ArrayList<CityDTO>();
	/** 一个城市id对应一个城市对象 **/
	private Map<String, CityDTO> cityMap = new HashMap();
	/** 一个索引字母对应一个城市列表 **/
	private Map<String, List<CityDTO>> indexCityMap = new HashMap();

	/**
	 * 加载城市信息
	 */
	public void load() {
		firstLevelList.clear();
		cityMap.clear();
		indexCityMap.clear();

		List<CityDTO> list = cityInfoDao.getAllCity();

		if (!CollectionUtils.isEmpty(list)) {
			for (CityDTO city : list) {
				// key值对列表
				cityMap.put(city.getId(), city);

				// 一级城市列表
				String fatherId = city.getFatherId();
				if (fatherId == null || "".equals(fatherId)) {
					firstLevelList.add(city);
				}

				// 字母索引结构
				String index = (String) city.getFirstLetter();
				index = index == null || "".equals(index) ? "other" : index;
				List<CityDTO> cityList = indexCityMap.get(index);
				if (cityList == null) {
					cityList = new ArrayList();
					indexCityMap.put(index, cityList);
				}
				cityList.add(city);
			}

			// 维护城市之间的父子关系
			for (CityDTO city : list) {
				String fatherId = city.getFatherId();
				if (fatherId != null || !"".equals(fatherId)) {
					CityDTO fatherCity = cityMap.get(fatherId);
					if (fatherCity != null) {
						fatherCity.addChildCity(city);
					}
				}
			}

		}
	}

	/**
	 * 获取一级城市列表
	 * 
	 * @return
	 */
	public List<CityDTO> getFirstLeverList() {
		return firstLevelList;
	}

	/**
	 * 根据索引字母获取城市列表
	 * 
	 * @return
	 */
	public List<CityDTO> getCityListByIndex(String index) {
		return indexCityMap.get(index);
	}

	/**
	 * 根据城市id获取城市对象
	 * 
	 * @param id
	 * @return
	 */
	public CityDTO getCityById(String id) {
		return cityMap.get(id);
	}

	/**
	 * 根据城市id获取城市名称
	 * 
	 * @param proviceId
	 * @return
	 */
	public String getCityNameById(String proviceId) {
		CityDTO city = cityMap.get(proviceId);
		if (city != null) {
			return city.getName();
		}
		return null;
	}

	
	public CityWrap getCityWrapById(String id) {
		
		CityWrap wrap = null;
		
		CityDTO currentCity = cityMap.get(id);
		
		if(CityType.POST_AREA_CLASS_TYPE_PROVINCE
				.toString().equals(currentCity.getClassType())) {
			//省
			wrap = new CityWrap();
			wrap.setProvinceCity(currentCity);
			
		} else if(CityType.POST_AREA_CLASS_TYPE_CITY
				.toString().equals(currentCity.getClassType())) {

			//市
			String provinceId = currentCity.getFatherId();
			CityDTO provinceCity = cityMap.get(provinceId);
			
			wrap = new CityWrap();
			wrap.setProvinceCity(provinceCity);
			wrap.setCity(currentCity);
			
			
		} else if(CityType.POST_AREA_CLASS_TYPE_DISTRICT
				.toString().equals(currentCity.getClassType())){
			
			//区
			String cityId = currentCity.getFatherId();
			CityDTO city = cityMap.get(cityId);
			CityDTO provinceCity = null;
			if(city != null) {
				provinceCity = cityMap.get(id);
			}

			wrap = new CityWrap();
			wrap.setProvinceCity(provinceCity);
			wrap.setCity(city);
			wrap.setDistrictity(currentCity);
			
		}
		
		return wrap;
	}
	
	public class CityWrap {

		/** 省 **/
		private CityDTO provinceCity;
		/** 市 **/
		private CityDTO city;
		/** 区 **/
		private CityDTO districtity;

		public CityDTO getProvinceCity() {
			return provinceCity;
		}

		public void setProvinceCity(CityDTO provinceCity) {
			this.provinceCity = provinceCity;
		}

		public CityDTO getCity() {
			return city;
		}

		public void setCity(CityDTO city) {
			this.city = city;
		}

		public CityDTO getDistrictity() {
			return districtity;
		}

		public void setDistrictity(CityDTO districtity) {
			this.districtity = districtity;
		}

	}

	public void setCityInfoDao(CityInfoDao cityInfoDao) {
		this.cityInfoDao = cityInfoDao;
	}

}
