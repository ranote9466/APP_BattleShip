package com.soen6441.battleship.model.ship;

import java.util.ArrayList;
import java.util.List;

import com.soen6441.battleship.model.Location;
import com.soen6441.battleship.view.util.Constants;
import com.soen6441.battleship.view.util.DirectionType;
import com.soen6441.battleship.view.util.ShipType;

public abstract class Ship
{
	private Integer size;
	private String name;
	private String state;

	private List<Location> locationsHit;
	private List<Location> locationsOccupied;

	// ***
	private int vertStart, vertEnd;
	private int horzStart, horzEnd;
	private int length;

	/**
	 * Variable used to store the amount of hits the ship has taken.
	 */
	private int hitsTaken;

	/**
	 * Variable used to store the hits the ship has remaining.
	 */
	private int hitsLeft;

	/**
	 * Variable storing the type of the ship.
	 */
	private ShipType type;

	private boolean isPlaced = false;

	/**
	 * 
	 */
	private boolean isSunk = false;
	private DirectionType dirType = DirectionType.VERTICAL;

	/**
	 * Constructor used to initialize the ship according to the passed in type.
	 * 
	 * @param s
	 *            type of ship to initialize the object to
	 * 
	 * @requires s is in ShipEnum
	 * @ensures this = s
	 */

	// ***

	public Ship(String name, Integer size)
	{
		this.size = size;
		this.name = name;
		this.state = Constants.SHIP_STATE_OK;
		this.locationsHit = new ArrayList<Location>();
		this.locationsOccupied = new ArrayList<Location>();
	}

	public boolean validateAttack(Location location)
	{
		if (this.locationsOccupied.contains(location))
		{
			this.locationsOccupied.remove(location);
			this.locationsHit.add(location);

			if (this.locationsOccupied.isEmpty() && this.locationsHit.size() == this.size)
			{
				this.state = Constants.SHIP_STATE_DESTROYED;
			}
			else
			{
				this.state = Constants.SHIP_STATE_HIT;
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	public void setLocationHit(Location location)
	{
		this.locationsHit.add(location);
	}

	public void setLocationOccupied(List<Location> list)
	{
		this.locationsOccupied.addAll(list);
	}

	public List<Location> getLocationsOccupied()
	{
		return this.locationsOccupied;
	}

	public String getState()
	{
		return this.state;
	}

	public String getName()
	{
		return this.name;
	}

	public Integer getSize()
	{
		return this.size;
	}
}
