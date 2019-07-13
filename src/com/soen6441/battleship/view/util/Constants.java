package com.soen6441.battleship.view.util;

public class Constants
{

	public static final Integer GAME_STATE_IN_PROGRESS = 1;
	public static final Integer GAME_STATE_OVER = 0;
	public static final String GAME_PLAYER_COMPUTER_NAME = "Computer";
	public static final String GAME_PLAYER_HUMAN_NAME = "Emmanuel";
	public static final String GAME_PLAYER_COMPUTER_TYPE = "AI";
	public static final String GAME_PLAYER_HUMAN_TYPE = "Human";
	public static final String SHIP_STATE_OK = "Ok";
	public static final String SHIP_STATE_HIT = "Hit";
	public static final String SHIP_STATE_DESTROYED = "Destroyed";
	public static final Integer SHIP_SIZE_CARRIER = 5;
	public static final Integer SHIP_SIZE_BATTLESHIP = 4;
	public static final Integer SHIP_SIZE_CRUISER = 3;
	public static final Integer SHIP_SIZE_SUBMARINE = 3;
	public static final Integer SHIP_SIZE_DESTROYER = 2;
	public static final String SHIP_NAME_CARRIER = "CARRIER";
	public static final String SHIP_NAME_BATTLESHIP = "BATTLESHIP";
	public static final String SHIP_NAME_CRUISER = "CRUISER";
	public static final String SHIP_NAME_SUBMARINE = "SUBMARINE";
	public static final String SHIP_NAME_DESTROYER = "DESTROYER";

	public static final Integer BOARD_SIZE = 10;

	public static final int DESTINDEX = 0;
	public static final int SUBMINDEX = 1;
	public static final int CRUSINDEX = 2;
	public static final int BATTINDEX = 3;
	public static final int CARRINDEX = 4;
	public static final int COMBX_DESTINDEX = 4;
	public static final int COMBX_SUBMINDEX = 3;
	public static final int COMBX_CRUSINDEX = 2;
	public static final int COMBX_BATTINDEX = 1;
	public static final int COMBX_CARRINDEX = 0;
	public static final int NUM_OF_SHIPS = 5;

	/**
	 * Array used in labeling the columns of the player's game/input board.
	 */
	public static final String[] BOARD_LETTERS =
	{ " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
	/**
	 * Array used in labeling the rows of the player's game/input
	 * board.Constants
	 */
	public static final String[] BOARD_NUMBERS =
	{ " ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

}
