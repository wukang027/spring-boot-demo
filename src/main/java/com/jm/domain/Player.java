package com.jm.domain;

import com.alibaba.fastjson.JSONObject;

public class Player {
	private Integer id;
	private String name;
	private Double points;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPoints() {
		return points;
	}
	public void setPoints(Double points) {
		this.points = points;
	}
	@Override
	public String toString(){
		return JSONObject.toJSONString(this);
	}
}
