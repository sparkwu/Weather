package com.wyj.coolweather.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wyj.coolweather.entity.City;
import com.wyj.coolweather.entity.County;
import com.wyj.coolweather.entity.Province;

public class CoolWeatherDB {
	//���ݿ�����
	public static final String DB_NAME = "coolweather_db";
	//���ݿ�汾��
	public static final int VERSION = 1;
	
	private static CoolWeatherDB coolWeatherDB;
	private SQLiteDatabase db;
	/**
	 * �����췽��˽�л�
	 * @param context
	 */
	private CoolWeatherDB(Context context){
		CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	}
	/**
	 * ��ȡCoolWeather��ʵ��
	 * @param context
	 * @return
	 */
	public synchronized static CoolWeatherDB getInstance(Context context){
		if(coolWeatherDB == null){
			coolWeatherDB = new CoolWeatherDB(context);
		}
		return coolWeatherDB;
	}
	/**
	 * ��Provinceʵ���浽���ݿ���ȥ
	 * @param province
	 */
	public void saveProvince(Province province){
		if(province != null){
			//��ֵ��
			ContentValues values = new ContentValues();
			values.put("province_name", province.getProvince_name());
			values.put("province_code", province.getProvince_code());
			db.insert("table_province", null, values);
		}
	}
	/**
	 * �����ݿ��ж�ȡȫ������ʡ����Ϣ
	 * @return
	 */
	public List<Province> loadProvince(){
		List<Province> list = new ArrayList<Province>();
		Cursor cursor = db.query("table_province", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				Province province = new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvince_code(cursor.getString(cursor.getColumnIndex("province_code")));
				province.setProvince_name(cursor.getString(cursor.getColumnIndex("province_name")));
				list.add(province);
			}while(cursor.moveToNext());
		}
		return list;
	}
	/**
	 * ��City��ʵ���浽���ݿ���
	 * @param city
	 */
	public void saveCity(City city){
		if(city != null){
			ContentValues values = new ContentValues();
			values.put("city_name", city.getCity_name());
			values.put("city_code", city.getCity_code());
			values.put("province_id", city.getProvince_id());
		}
	}
	/**
	 * �����ݿ��ж�ȡĳʡ��ȫ��������Ϣ
	 * @param province_id
	 * @return
	 */
	public List<City> loadCity(int province_id){
		List<City> list = new ArrayList<City>();
		Cursor cursor = db.query("table_city", null, "province_id=?", new String[]{String.valueOf(province_id)}, null,null,null);
		if(cursor.moveToFirst()){
			do{
				City city = new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCity_code(cursor.getString(cursor.getColumnIndex("city_code")));
				city.setCity_name(cursor.getString(cursor.getColumnIndex("city_name")));
				city.setProvince_id(province_id);
				list.add(city);
			}while(cursor.moveToNext());
		}
		return list;
	}
	
	/**
	 * ��County��ʵ���浽���ݿ���
	 * @param county
	 */
	public void saveCounty(County county){
		if(county != null){
			ContentValues values = new ContentValues();
			values.put("county_name", county.getCounty_name());
			values.put("county_code", county.getCounty_code());
			values.put("city_id", county.getCity_id());
		}
	}
	
	/**
	 * �����ݿ��ж�ȡĳ�����������ص���Ϣ
	 * @param city_id
	 * @return
	 */
	public List<County> loadCounty(int city_id){
		List<County> list = new ArrayList<County>();
		Cursor cursor = db.query("table_county", null, "city_id=?", new String[]{String.valueOf(city_id)}, null,null,null);
		if(cursor.moveToFirst()){
			do{
				County county = new County();
				county.setId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCounty_code(cursor.getString(cursor.getColumnIndex("county_code")));
				county.setCounty_name(cursor.getString(cursor.getColumnIndex("county_name")));
				county.setCity_id(city_id);
				list.add(county);
			}while(cursor.moveToNext());
		}
		return list;
	}
	
}
