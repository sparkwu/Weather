package com.wyj.coolweather.util;

import android.text.TextUtils;

import com.wyj.coolweather.db.CoolWeatherDB;
import com.wyj.coolweather.entity.City;
import com.wyj.coolweather.entity.County;
import com.wyj.coolweather.entity.Province;

/**
 * 解析和处理服务器返回的数据
 * @author wuyangjun
 *
 */
public class Utility {
	/**
	 * 解析和处理服务器返回的省级数据
	 * @param coolWeatherDB
	 * @param response
	 * @return
	 */
	public synchronized static boolean handleProvincesResponse( CoolWeatherDB coolWeatherDB,String response){
		if(!TextUtils.isEmpty(response)){
			String allProvince[] = response.split(",");
			if(allProvince != null && allProvince.length >= 0){
				for(String p : allProvince){
					String[] array = p.split("\\|");
					Province province = new Province();
					province.setProvince_code(array[0]);
					province.setProvince_name(array[1]);
					coolWeatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}
	/**
	 * 解析和处理服务器返回市级数据
	 * @param coolWeatherDB
	 * @param response
	 * @param province_id
	 * @return
	 */
	public static boolean handleCityResponse(CoolWeatherDB coolWeatherDB, String response, int province_id){
		if(!TextUtils.isEmpty(response)){
			String allCities[] = response.split(",");
			if(allCities != null && allCities.length > 0){
				for(String c : allCities){
					String[] array = c.split("\\|");
					City city = new City();
					city.setCity_code(array[0]);
					city.setCity_name(array[1]);
					city.setProvince_id(province_id);
					coolWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean handleCountyResponse(CoolWeatherDB coolWeatherDB, String response, int city_id){
		if(!TextUtils.isEmpty(response)){
			String[] allCounty = response.split(",");
			if(allCounty != null && allCounty.length > 0){
				for(String c : allCounty){
					String[] array = c.split("\\|");
					County county = new County();
					county.setCity_id(city_id);
					county.setCounty_code(array[0]);
					county.setCounty_name(array[1]);
					coolWeatherDB.saveCounty(county);
				}
				return true;
			}
		}
		
		return false;
	}
}
