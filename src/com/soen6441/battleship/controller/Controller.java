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
	private static Controller instance;
	
	private Game game; //Need to add Singleton to ensure that only one game class can be created.
	
	
	public static Controller getInstance() {
		if (instance == null)
			instance = new Controller();
		
		return instance;
	}
	
	public Controller() {
		this.game = new Game();
		List<Location> locations = new ArrayList<>();
		locations.add(new Location(1, 1));  
		locations.add(new Location(1, 2));
		locations.add(new Location(1, 3));
		locations.add(new Location(1, 4));
		
		Map<String, List<Location>> shiptobeplaced = new HashMap<>();
		shiptobeplaced.put(Constants.SHIP_NAME_BATTLESHIP, locations);
		
		//placing ships
			for (Player p: this.game.getPlayers()) {
				try {
					this.placeShips(p, shiptobeplaced);
				} catch (gameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	public Game start() throws gameException {
		
		; //give this game to the game listener to handle our requests
		
//		Player firstPlayer = this.game.determineFirstTurn();
//		
//		
//		//create the locations
		
//			Turn turn = this.play(firstPlayer, 1, 3);
//			
//			
//			System.out.println("result" + turn.result.keySet());
//			for (Ship s: turn.result.values()) {
//				System.out.println("you have " + s.getState() + " " + turn.getAttackedPlayer().getName() + "'s " + s.getName());
//				
//			}
			
			return this.game;
		
	}
	
	public Turn play(Player player, Integer x, Integer y) throws gameException {
		//Location location = new Location(1,1);
		System.out.println("Locations: " + x + " " + y);
		return this.game.play(player, new Location(x,y));
		
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
