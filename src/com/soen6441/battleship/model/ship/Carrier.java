package com.soen6441.battleship.model.ship;

import com.soen6441.battleship.view.util.Constants;

public class Carrier extends Ship{
	public Carrier(String name) {
		super(name, Constants.SHIP_SIZE_CARRIER);
	}
}
