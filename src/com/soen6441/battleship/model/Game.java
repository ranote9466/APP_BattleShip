package com.soen6441.battleship.model;

import java.util.ArrayList;
import java.util.List;

import com.soen6441.battleship.model.turns.Turn;

public class Game {
	private List<Player> players;
	private Integer state;
	private List<Turn> turns;
	private Player attacker;
	private Player attacked;

	public Game() {
		System.out.println("BattleShips");
		this.state = Constants.GAME_STATE_IN_PROGRESS;
		//create new players in the players array
		this.players = new ArrayList<Player>();
		this.players.add(new Player(Constants.GAME_PLAYER_COMPUTER_NAME, Constants.GAME_PLAYER_COMPUTER_TYPE));
		this.players.add(new Player(Constants.GAME_PLAYER_HUMAN_NAME, Constants.GAME_PLAYER_HUMAN_TYPE));
		
		for (Player player : players)
			System.out.println("type " + player.getName());
		//OBSERVER PATTERN - register to receive updated information about the player boards
	}

	public void play() {
		if (turns.size() == 0) {
			this.determineFirstTurn();
		}
		
		
		
	}
	
	public void determineFirstTurn(){
		//we could do this at random but we take the first element in the player array
		this.attacker = players.get(0);
		this.attacked = players.get(1);
	}
	
	public void swapPlayers() {
		
	}
	//the update method for the observed to send information
}
