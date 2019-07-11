package com.soen6441.battleship.controller;

import java.util.List;
import java.util.Map;

import com.soen6441.battleship.exceptions.gameException;
import com.soen6441.battleship.model.Game;
import com.soen6441.battleship.model.Location;
import com.soen6441.battleship.model.Player;
import com.soen6441.battleship.model.turns.Turn;

public class Controller implements IController {
	private Game game; //Need to add Singleton to ensure that only one game class can be created.
	
	public void start() {
		this.game = new Game();
		//return this.game; //give this game to the game listener to handle our requests
		
		Player firstPlayer = this.game.determineFirstTurn();
		
		
		try {
			Turn turn = this.play(firstPlayer, new Location(1,1));
			
			System.out.println("result" + turn.result.keySet());
		} catch (gameException e) {
			e.printStackTrace();
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
