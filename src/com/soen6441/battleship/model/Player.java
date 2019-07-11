package com.soen6441.battleship.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.soen6441.battleship.model.turns.Turn;
import com.soen6441.battleship.model.ship.*;
import com.soen6441.battleship.view.util.Constants;

public class Player{
	private String name;
	private String type;
	private Turn currentTurn;
	private List<Turn> turns;
	private Board board;
	
	public Player(String name, String type) {
		this.name = name;
		this.type = type;
		this.turns = new ArrayList<Turn>();
		this.board = new Board();
	}
	
	public void sendAttack(Turn turn, Location attackPosition, Player attacked) {
		Attack attack = this.createAttack(attackPosition);
		attacked.recieveAttack(turn, attack);
	}
	
	public Attack createAttack(Location attackPosition) {
		if (type == Constants.GAME_PLAYER_HUMAN_TYPE && attackPosition != null) {
			Attack attack = new humanAttack(attackPosition);
			return attack;
		}else {
			Attack attack = new computerAttack();
			return attack;
		}
	}
	
	
	public void recieveAttack(Turn turn, Attack attack) {
		
		Map<Boolean, Ship> validation = board.validateAttack(attack.getAttackPosition());
		
//		if (validation.size() > 0) {
//			System.out.println("HIT");
//			attack.setState(true);
//			turn.setStatus(true);
//			
//		}else {
//			System.out.println("MISS");
//			attack.setState(false);
//			turn.setStatus(false);
//		}
		
		
		turn.setResult(validation);
	}
	
	
	private boolean validateAttack(Attack attack) {
		return false;
	}

	public String getName() {
		return this.name;
	}

	public String getType() {
		return this.type;
	}

	public Board getBoard() {
		return this.board;
	}

	public List<Turn> getTurns() {
		return this.turns;
	}
	
	
}
