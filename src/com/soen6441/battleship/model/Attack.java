package com.soen6441.battleship.model;

public abstract class Attack {
	private boolean state;
	
	private Location attackPosition;
	
	
	
	public Attack(Location location) {
		attackPosition = location;
	}

	
	public Attack() {
		this.createLocation();
	}

	private void createLocation() {
		attackPosition = new Location(1,1);
	}


	public void setState(boolean state) {
		this.state = state;
	}
	
	public Location getAttackPosition() {
		return this.attackPosition;
	}
}
