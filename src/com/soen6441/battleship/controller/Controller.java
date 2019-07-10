package com.soen6441.battleship.controller;

import com.soen6441.battleship.model.Game;

public class Controller implements IController {
	private Game game; //Need to add Singleton to ensure that only one game class can be created.
	
	public void start() {
		this.game = new Game();
		//return this.game; //give this game to the game listener to handle our requests
	}
	
}
