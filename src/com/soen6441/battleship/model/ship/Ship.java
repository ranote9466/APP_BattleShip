package com.soen6441.battleship.model.ship;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.soen6441.battleship.model.Attack;
import com.soen6441.battleship.model.Location;
import com.soen6441.battleship.view.util.Constants;

public abstract class Ship {
	private Integer size;
	private String name;
	private String state;
	
	private List<Location> locationsHit;
	private List<Location> locationsOccupied;
	
	public Ship(String name, Integer size) {
		this.size = size;
		this.name = name;
		this.state = Constants.SHIP_STATE_OK;
		this.locationsHit = new ArrayList<Location>();
		this.locationsOccupied = new ArrayList<Location>();
	}
	
	public boolean validateAttack(Location location) {
		if(this.locationsOccupied.contains(location)) {
			this.locationsOccupied.remove(location);
			this.locationsHit.add(location);
			
			if (this.locationsOccupied.isEmpty() && this.locationsHit.size() == this.size) {
				this.state = Constants.SHIP_STATE_DESTROYED;
			}else {
				this.state = Constants.SHIP_STATE_HIT;
			}
			return true;
		}else {
			return false;
		}
	}
	
	public void setLocationHit(Location location) {
		this.locationsHit.add(location);
	}
	
	public void setLocationOccupied(List<Location> list) {
		this.locationsOccupied.addAll(list);
	}
	
	public List<Location> getLocationsOccupied(){
		return this.getLocationsOccupied();
	}
	
	public String getState() {
		return this.state;
	}
	
	public String getName() {
		return this.name;
	}
}

	