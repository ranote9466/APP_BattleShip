package com.soen6441.battleship.controller;

import java.util.List;
import java.util.Map;

import com.soen6441.battleship.exceptions.gameException;
import com.soen6441.battleship.model.Game;
import com.soen6441.battleship.model.Location;
import com.soen6441.battleship.model.Player;
import com.soen6441.battleship.model.turns.Turn;

public class Controller implements IController
{
	private static Controller instance;

	private Game game; // Need to add Singleton to ensure that only one game
						// class can be created.

	public static Controller getInstance()
	{
		if (instance == null)
			instance = new Controller();

		return instance;
	}

	public Controller()
	{
		this.game = new Game();
	}

	public Game start()
	{

		return this.game;

	}

	public Turn play(Player player, Integer x, Integer y) throws gameException
	{
		return this.game.play(player, new Location(x, y));

	}

	public void placeShips(Player player, Map<String, List<Location>> shipLocations) throws gameException
	{
		Player placingPlayer = this.game.getPlayer(player);
		if (placingPlayer == null)
		{
		}
		else
		{

			placingPlayer.getBoard().placeShip(shipLocations);
		}
	}

}
