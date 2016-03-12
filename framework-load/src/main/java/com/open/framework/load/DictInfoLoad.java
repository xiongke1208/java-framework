package com.open.framework.load;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.open.framework.load.dao.DictInfoDao;
import com.open.framework.load.dto.DictDTO;


/**
 * 字典信息加载
 * @author ke_xiong
 *
 */
public class DictInfoLoad {

	private DictInfoDao dictInfoDao;
	
	/** 一个code对应一组字典对象 **/
	private Map<String,List<DictDTO>> codeMap = new HashMap();
	/** 一个字典id对应一个字典对象 **/
	private Map<String,DictDTO> dictMap = new HashMap();
	
	/**
	 * 加载字典数据到内存
	 */
	public void load() {
		//先清除两个map中的数据
		codeMap.clear();
		dictMap.clear();
		
		List<DictDTO> dictList = dictInfoDao.getAllDict();
		
		if(CollectionUtils.isNotEmpty(dictList)) {
			for (DictDTO dict : dictList) {
				dictMap.put(dict.getId(), dict);
				
				//获取指定code的字典列表
				String code = dict.getTypeCode();
				//如果==空则为other
				code = code==null||"".equals(code)?"other":code;
				List<DictDTO> dictListByCode =  (List<DictDTO>) codeMap.get(code);
				if(dictListByCode == null) {
					dictListByCode = new ArrayList<DictDTO>();
					codeMap.put(code, dictListByCode);
				}
				dictListByCode.add(dict);
				
			}
		}
	}

	/**
	 * 返回一个字典对象
	 * @param dictId
	 * @return
	 */
	public DictDTO getDictByDictId(String dictId) {
		return dictMap.get(dictId);
	}
	
	/**
	 * 根据字典类型获取一组字典对象
	 * @param typeCode
	 * @return
	 */
	public List<DictDTO> getDictList(String typeCode) {
		return codeMap.get(typeCode);
	}
	
	public void setDictInfoDao(DictInfoDao dictInfoDao) {
		this.dictInfoDao = dictInfoDao;
	}
	
}
