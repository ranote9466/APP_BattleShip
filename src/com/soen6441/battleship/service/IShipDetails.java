package com.soen6441.battleship.service;

import com.soen6441.battleship.view.util.DirectionType;

public interface IShipDetails
{

	void unPlace();

	void place();

	boolean isSunk();

	DirectionType getDirection();

	int getLength();

	String getName();

}
