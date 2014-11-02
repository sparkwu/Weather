package com.wyj.coolweather.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class CoolWeatherOpenHelper extends SQLiteOpenHelper{
	
	public String CREATE_PROVINCE_SQL = null;
	public String CREATE_CITY_SQL = null;
	public String CREATE_COUNTY_SQL = null;
	
	public CoolWeatherOpenHelper(Context context, String name,CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	public CoolWeatherOpenHelper(Context context, String name,CursorFactory factory, int version,DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
	}
	
	StringBuffer sql = new StringBuffer();
	private void getProvinceSQL(){
		sql.delete(0, sql.length());
		sql.append("CREATE TABLE IF NOT EXISTS table_province(  "); 
		sql.append("       id INTEGER PRIMARY KEY AUTOINCREMENT,"); 
		sql.append("       province_name TEXT,                  "); 
		sql.append("       province_code TEXT                   "); 
		sql.append(")                                           "); 
		CREATE_PROVINCE_SQL = sql.toString();
	}
	
	private void getCitySQL(){
		sql.delete(0, sql.length());
		sql.append("CREATE TABLE IF NOT EXISTS table_city(      ");
		sql.append("       id INTEGER PRIMARY KEY AUTOINCREMENT,");
		sql.append("       city_name TEXT,                      ");
		sql.append("       city_code TEXT,                      ");
		sql.append("       province_id INTEGER                  ");
		sql.append(")                                           ");
		CREATE_CITY_SQL = sql.toString();
	}
	
	private void getCountySQL(){
		sql.delete(0, sql.length());
		sql.append("CREATE TABLE IF NOT EXISTS table_county(    ");    
		sql.append("       id INTEGER PRIMARY KEY AUTOINCREMENT,");    
		sql.append("       county_name TEXT,                    ");    
		sql.append("       county_code TEXT,                    ");    
		sql.append("       city_id INTEGER                      ");    
		sql.append(")                                           ");    
		CREATE_COUNTY_SQL = sql.toString();
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		getProvinceSQL();
		getCitySQL();
		getCountySQL();
		db.execSQL(CREATE_PROVINCE_SQL);
		db.execSQL(CREATE_CITY_SQL);
		db.execSQL(CREATE_COUNTY_SQL);		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
