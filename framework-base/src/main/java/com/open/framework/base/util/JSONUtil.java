package com.open.framework.base.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author ke_xiong
 *
 */
public class JSONUtil {

	/**
	 * 将jsonObj转换成map
	 * @param obj
	 * @return
	 */
	public static Map jsonObjToMap(JSONObject obj){
		Map map = new HashMap();
		Iterator it = obj.keySet().iterator();
		while(it.hasNext()){
			Object key = it.next();
			Object value = obj.get((String)key);
			if(value instanceof JSONObject){
				map.put(key, jsonObjToMap((JSONObject)value));
			}else if(value instanceof JSONArray){
				map.put(key, jsonArrToList((JSONArray)value));
			}else{
				map.put(key, value);
			}
		}
		return map;
	}
	
	
	/**
	 * 将JSONArray转换成List
	 * @param arr
	 * @return
	 */
	public static List jsonArrToList(JSONArray arr) {
		List list = new ArrayList();
		for(Object obj:arr){
			if(obj instanceof JSONObject){
				list.add(jsonObjToMap((JSONObject)obj));
			}else if(obj instanceof JSONArray){
				list.add(jsonArrToList((JSONArray)obj));
			}else{
				list.add(obj);
			}
		}
		return list;
	}
	
	/**
	 * 将obj转成String
	 * @param obj
	 * @return
	 */
	public static String strValueOf (Object obj){
		if(obj == null){
			return "";
		}else{
			return String.valueOf(obj);
		}
	}

	/**
	 * 查找 jsonObject是否有指定属性，如果有则返回
	 * @param field
	 * @param obj
	 * @return List 一个JSON对象可能在不同的层次有相同的属性名
	 */
	public static List findPropertyInMap(String field, Map obj) {
		List result = new ArrayList();
		if(obj.containsKey(field)){//如果包含指定属性则添加
			result.add(obj);
		}
		Set<Object> set = obj.keySet();
		for(Object key : set){//然后递归遍历整个jsonObject的属性
			Object value = obj.get((String)key);
			if(value instanceof Map){
				result.addAll(findPropertyInMap(field, (Map)value));
			}else if(value instanceof List){
				result.addAll(findPropertyInList(field, (List)value));
			}
		}
		return result;
	}
	
	/**
	 * 在jsonArr中查找指定属性
	 * @param field
	 * @param arr
	 * @return
	 */
	public static List findPropertyInList(String field, List arr){
		List result = new ArrayList();
		for(Object obj : arr){
			if(obj instanceof Map){
				result.addAll(findPropertyInMap(field, (Map)obj));
			} else if(obj instanceof List){
				result.addAll(findPropertyInList(field, (List)obj));
			}
		}
		return result;
	}
	
	/**
	 * 查找 jsonObject是否有指定属性，如果有则返回
	 * @param field
	 * @param obj
	 * @return Map<String,List> 一个JSON对象可能在不同的层次有相同的属性名
	 */
	public static Map<String,List> findPropertyInMap(List<String> fieldList, Map obj) {
		Map<String,List> result = new HashMap();
		for(String field : fieldList){
			if(obj.containsKey(field)){//如果包含指定属性则添加
				List<Map> list = result.get(field);
				if(list == null){
					list = new ArrayList();
				}
				list.add(obj);
				result.put(field, list);
			}
		}
		Set<Object> set = obj.keySet();
		for(Object key : set){//然后递归遍历整个jsonObject的属性
			Object value = obj.get((String)key);
			if(value instanceof Map){
				mergerMap(result, findPropertyInMap(fieldList, (Map)value));
			}else if(value instanceof List){
				mergerMap(result, findPropertyInList(fieldList, (List)value));
			}
		}
		return result;
	}
	
	/**
	 * 在jsonArr中查找指定属性
	 * @param field
	 * @param arr
	 * @return
	 */
	public static Map findPropertyInList(List<String> fieldList, List arr){
		Map<String,List> result = new HashMap();
		for(Object obj : arr){
			if(obj instanceof Map){
				mergerMap(result, findPropertyInMap(fieldList, (Map)obj));
			} else if(obj instanceof List){
				mergerMap(result, findPropertyInList(fieldList, (List)obj));
			}
		}
		return result;
	}
	
	/**
	 * 合并两个Map
	 * @param result
	 * @param findPropertyInMap
	 * @return
	 */
	private static void mergerMap(Map<String, List> result,
			Map<String, List> findPropertyInMap) {
		for(String key : findPropertyInMap.keySet()){
			if(result.containsKey(key)){//如果存在则添加到key所有对应的List中
				List list = result.get(key);
				list.addAll(findPropertyInMap.get(key));
			}else{//如果不存在则直接将List put到Map中
				List list = findPropertyInMap.get(key);
				result.put(key, list);
			}
		}
	}
	
}
