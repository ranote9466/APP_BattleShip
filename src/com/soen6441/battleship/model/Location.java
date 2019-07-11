package com.soen6441.battleship.model;

public class Location {
	private Integer x;
	
	private Integer y;
	
	
	public Location(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}
	
	public Integer getX() {
		return this.x;
	}
	
	public Integer getY() {
		return this.y;
	}
	
}
