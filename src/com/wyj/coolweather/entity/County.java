package com.wyj.coolweather.entity;

import java.io.Serializable;

public class County implements Serializable {
	private static final long serialVersionUID = -4054524793159340881L;
	private int id;
	private String county_code;
	private String county_name;
	private int city_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCounty_code() {
		return county_code;
	}

	public void setCounty_code(String county_code) {
		this.county_code = county_code;
	}

	public String getCounty_name() {
		return county_name;
	}

	public void setCounty_name(String county_name) {
		this.county_name = county_name;
	}

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

}
