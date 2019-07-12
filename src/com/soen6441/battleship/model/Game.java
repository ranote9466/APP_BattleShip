package com.soen6441.battleship.model;

import java.util.ArrayList;
import java.util.List;

import com.soen6441.battleship.exceptions.gameException;
import com.soen6441.battleship.model.turns.Turn;
import com.soen6441.battleship.view.util.Constants;
import com.soen6441.battleship.model.Location;
public class Game {
	private List<Player> players;
	public static Integer state;
	public static List<Turn> turns;
	private Player attacker;
	private Player attacked;

	public Game() {
		System.out.println("BattleShips");
		this.state = Constants.GAME_STATE_IN_PROGRESS;
		//create new players in the players array
		this.players = new ArrayList<Player>();
		Game.turns = new ArrayList<Turn>();
		this.players.add(new Player(Constants.GAME_PLAYER_COMPUTER_NAME, Constants.GAME_PLAYER_COMPUTER_TYPE));
		this.players.add(new Player(Constants.GAME_PLAYER_HUMAN_NAME, Constants.GAME_PLAYER_HUMAN_TYPE));
		
		for (Player player : players)
			System.out.println("type " + player.getName());
		
		//OBSERVER PATTERN - register to receive updated information about the player boards
	}

	public Turn play(Player attacker, Location attackPosition) throws gameException {
		
		if (state != null && this.state == Constants.GAME_STATE_OVER) {
			throw new gameException("game over");
		}
		
		if (turns.size() == 0) {
			this.determineFirstTurn();
		}
		

		if (attacker.getType() == Constants.GAME_PLAYER_COMPUTER_TYPE) {
			this.attacked = players.get(1);
		}else {
			this.attacked = players.get(0);
		}
		
		System.out.println("Attacker type: " + attacker.getType());
		System.out.print("Attacked: "+ this.attacked.getName());
		Turn turn = new Turn(attacker, this.attacked, attackPosition);
		turn.playTurn();
		
//		this.swapPlayers();
		
		
		return turn;
	}
	
	public Player determineFirstTurn(){
		//we could do this at random but we take the first element in the player array
		this.attacker = players.get(1);
		this.attacked = players.get(0);
		
		return this.attacker;
	}
	
	public Player getPlayer(Player player) {
		
		for (Player p : this.players) {
			if (p == player) {
				return p;
			}
		}
		
		return null;
	}
	
	public List<Player> getPlayers() {
		return this.players;
	}
	
	
	public static Integer gameOver() throws gameException {
		//announce winner
		//end game
		
		return state;
	}
	//the update method for the observed to send information
}
