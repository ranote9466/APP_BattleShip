package com.soen6441.battleship.service;

import com.soen6441.battleship.view.util.Constants;
import com.soen6441.battleship.view.util.DirectionType;
import com.soen6441.battleship.view.util.ShipType;

public class ShipDetails implements IShipDetails
{
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
	 * Variable storing the name of the ship.
	 */
	private String name;

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
	public ShipDetails(ShipType type)
	{
		this.type = type;
		switch (type)
		{
		case CARRIER:
		{
			name = "Carrier";
			length = Constants.SHIP_SIZE_CARRIER;
			hitsLeft = length;
			break;
		}
		case BATTLESHIP:
		{
			name = "Battleship";
			length = Constants.SHIP_SIZE_BATTLESHIP;
			hitsLeft = length;
			break;
		}
		case CRUISER:
		{
			name = "Cruiser";
			length = Constants.SHIP_SIZE_CRUISER;
			hitsLeft = length;
			break;
		}
		case SUBMARINE:
		{
			name = "Submarine";
			length = Constants.SHIP_SIZE_SUBMARINE;
			hitsLeft = length;
			break;
		}
		case DESTROYER:
		{
			name = "Destroyer";
			length = Constants.SHIP_SIZE_DESTROYER;
			hitsLeft = length;
			break;
		}
		default:
		{

		}
		}
	}

	public final boolean isPlaced()
	{
		return isPlaced;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void place()
	{
		isPlaced = true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void unPlace()
	{
		isPlaced = false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isSunk()
	{
		if (hitsTaken >= length)
		{
			isSunk = true;
		}
		return isSunk;
	}

	@Override
	public final DirectionType getDirection()
	{
		return dirType;
	}

	@Override
	public final int getLength()
	{
		return length;
	}

	@Override
	public final String getName()
	{
		return name;
	}
}
