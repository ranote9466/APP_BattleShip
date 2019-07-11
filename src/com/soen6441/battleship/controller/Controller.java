package com.soen6441.battleship.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soen6441.battleship.exceptions.gameException;
import com.soen6441.battleship.model.Game;
import com.soen6441.battleship.model.Location;
import com.soen6441.battleship.model.Player;
import com.soen6441.battleship.model.ship.Ship;
import com.soen6441.battleship.model.turns.Turn;
import com.soen6441.battleship.view.util.Constants;

public class Controller implements IController {
	private Game game; //Need to add Singleton to ensure that only one game class can be created.
	
	public void start() throws gameException {
		this.game = new Game();
		//return this.game; //give this game to the game listener to handle our requests
		
		Player firstPlayer = this.game.determineFirstTurn();
		
		
		//create the locations
		List<Location> locations = new ArrayList<>();
		locations.add(new Location(1, 1));  
		locations.add(new Location(1, 2));
		locations.add(new Location(1, 3));
		locations.add(new Location(1, 4));
		
		Map<String, List<Location>> shiptobeplaced = new HashMap<>();
		shiptobeplaced.put(Constants.SHIP_NAME_BATTLESHIP, locations);
		
		//placing ships
			for (Player p: this.game.getPlayers()) {
				this.placeShips(p, shiptobeplaced);
			}
			Turn turn = this.play(firstPlayer, new Location(1, 5));
			
			System.out.println("result" + turn.result.keySet());
			for (Ship s: turn.result.values()) {
				System.out.println("you have " + s.getState() + " " + turn.getAttackedPlayer().getName() + "'s " + s.getName());
			}
		
	}
	
	public Turn play(Player player, Location location) throws gameException {
		//Location location = new Location(1,1);
		return this.game.play(player, location);
		
	}
	
	public void placeShips(Player player, Map<String, List<Location>> shipLocations) throws gameException {
		//build random locations for now for two players
		Player placingPlayer = this.game.getPlayer(player);
		if (placingPlayer == null) {
			throw new gameException("This player does not exist");
		}else {
			placingPlayer.getBoard().placeShip(shipLocations);
		}
	}
	
}
