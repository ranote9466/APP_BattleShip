package com.soen6441.battleship.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soen6441.battleship.exceptions.gameException;
import com.soen6441.battleship.model.ship.Ship;
import com.soen6441.battleship.model.ship.ShipFactory;
import com.soen6441.battleship.view.util.Constants;

public class Board
{
	private List<Ship> ships;

	private Integer remainingShips;

	private List<Location> locationsHit;
	private List<Location> locationsVisited;
	private List<Location> occupied;

	public Board()
	{
		this.ships = new ArrayList<Ship>();
		this.locationsHit = new ArrayList<Location>();
		this.locationsVisited = new ArrayList<Location>();
		this.occupied = new ArrayList<Location>();
		this.createShips();
	}

	public void createShips()
	{
		this.ships.add(ShipFactory.create(Constants.SHIP_NAME_BATTLESHIP));
		this.ships.add(ShipFactory.create(Constants.SHIP_NAME_CARRIER));
		this.ships.add(ShipFactory.create(Constants.SHIP_NAME_CRUISER));
		this.ships.add(ShipFactory.create(Constants.SHIP_NAME_DESTROYER));
		this.ships.add(ShipFactory.create(Constants.SHIP_NAME_SUBMARINE));
	}

	public Integer getRemainingShips()
	{
		return this.remainingShips;
	}

	public void placeShip(Map<String, List<Location>> shipLocations)
	{
		System.out.println(" placeShip IN BOARD CLASS " + shipLocations.size());
		for (Ship ship : ships)
		{
			if (shipLocations.containsKey(ship.getName()))
			{
				ship.setLocationOccupied(shipLocations.get(ship.getName()));
				this.occupied.addAll(shipLocations.get(ship.getName()));
			}

			for (Location l : ship.getLocationsOccupied())
			{
				System.out.println(ship.getName() + " " + l.getX() + " - " + l.getY());
			}
		}
	}

	public Map<Boolean, Ship> validateAttack(Location location)
	{

		Map<Boolean, Ship> validation = new HashMap<>();

		validation.put(false, null);
		if (this.occupied.contains(location) && !this.locationsVisited.contains(location))
		{

			for (Ship ship : this.ships)
			{
				if (ship.validateAttack(location))
				{
					validation.clear();
					validation.put(true, ship);
					this.locationsHit.add(location);
					this.occupied.remove(location);

					if (this.occupied.isEmpty())
					{
						try
						{

							Game.gameOver();
						}
						catch (gameException e)
						{
							this.locationsVisited.add(location);

							e.printStackTrace();
							return validation;
						}
					}
				}
			}
		}
		this.locationsVisited.add(location);
		return validation;
	}

	public List<Location> getOccupied()
	{
		return this.occupied;
	}

	public boolean checkOccupied(Location location)
	{
		return this.occupied.contains(location) && !this.locationsVisited.contains(location);
	}

	public List<Ship> getShips()
	{
		return this.ships;
	}
}
