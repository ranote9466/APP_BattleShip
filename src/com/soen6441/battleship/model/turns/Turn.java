package com.soen6441.battleship.model.turns;

import java.util.Map;

import com.soen6441.battleship.exceptions.gameException;
import com.soen6441.battleship.model.Game;
import com.soen6441.battleship.model.Location;
import com.soen6441.battleship.model.Player;
import com.soen6441.battleship.model.ship.Ship;

public class Turn {
	private boolean status;
	
	private Player attacker;
	
	private Player attacked;
	
	private Location attackPosition;
	
	public Map<Boolean, Ship> result;

	public Turn(Player attacker, Player attacked, Location attackPosition) {
		this.attacker = attacker;
		this.attacked = attacked;
		this.attackPosition = attackPosition;
	}
	
	public void playTurn() throws gameException {
//		this.validateTurn();
		this.attacker.sendAttack(this, attackPosition, attacked);
		this.saveTurn();
	}
	
//	private void validateTurn() throws gameException {
//
//		if (Game.turns.get(Game.turns.size() - 1).attacker.getName() == attacker.getName()) {
//			throw new gameException("Please wait it is not your turn");
//		}
//	}
	
	private void saveTurn() {
		this.attacker.getTurns().add(this);
	}
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public Player getAttackedPlayer() {
		return attacked;
	}
	
	public Player getAttacker() {
		return attacker;
	}
	
	public void setResult(Map<Boolean, Ship> result) {
		this.result = result;
	}


}
