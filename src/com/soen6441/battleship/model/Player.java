package com.soen6441.battleship.model;

import java.util.ArrayList;
import java.util.List;

import com.soen6441.battleship.model.turns.Turn;

public class Player{
	private String name;
	private String type;
	private List<Turn> turns;
	private Board board;
	
	public Player(String name, String type) {
		this.name = name;
		this.type = type;
		this.turns = new ArrayList<Turn>();
		this.board = new Board();
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
		// TODO Auto-generated method stub
		return this.turns;
	}
	
	
}
