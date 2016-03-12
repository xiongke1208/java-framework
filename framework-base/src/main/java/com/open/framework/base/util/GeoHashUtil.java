package com.open.framework.base.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import ch.hsr.geohash.GeoHash;

public class GeoHashUtil {

	/**
	 * 根据坐坐标字符串获取相邻区域的所有坐标
	 * @param position
	 * @return
	 */
	public static List<String> 
		getAllAdjacentGeoHash (String position) {
		List<String> result = null;
		
		//获取地理位置hash码
		String[] arr = position.split(",");
		if(arr.length == 2) {
			GeoHash geo = GeoHash.withCharacterPrecision(
					Double.valueOf(arr[1]), 
					Double.valueOf(arr[0]), 8);
			String geohash = geo.toBase32();
			
			if(!StringUtil.isEmpty(geohash)) {
				result = new ArrayList(9);
				result.add(geohash);
				
				//获取相邻格子的坐标
				GeoHash[] adjacentArr = geo.getAdjacent();
				if(adjacentArr != null) {
					for(GeoHash adjacent : adjacentArr) {
						if(adjacent != null) {
							String hash = adjacent.toBase32();
							if(!StringUtil.isEmpty(hash)) {
								result.add(hash);
							}
						}
					}
				}
			}
		}
		
		filterRepeatValue(result);
		
		return result;
	}

	/**
	 * 去除重复的值，
	 * @param list
	 */
	private static void filterRepeatValue(
			List<String> list) {
		if(CollectionUtils.isNotEmpty(list)) {
			//从最后一个值开始遍历
			for(int i=list.size()-1; i>0; i--) {
				String str = list.get(i);
				for(int j=i-1; j>=0; j--) {
					String strJ = list.get(j);
					//发现有重复的则删除，这样就不会影响到当前遍历结点前面结点的顺序
					if(str.contains(strJ.substring(0, 7))) {
						list.remove(i);
						break;
					}
				}
			}
		}
	}

	/**
	 * 根据两点的经纬度计算换算出距离
	 * @param lat1
	 * @param lat2
	 * @param lon1
	 * @param lon2
	 * @return
	 */
	public static double getDistatce(
			double lat1, double lat2, double lon1, double lon2) {
        double R = 6371;
        double distance = 0.0;
        double dLat = (lat2 - lat1) * Math.PI / 180;
        double dLon = (lon2 - lon1) * Math.PI / 180;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(lat1 * Math.PI / 180)
                * Math.cos(lat2 * Math.PI / 180) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        distance = (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))) * R;
        return distance;
    }
	
}
