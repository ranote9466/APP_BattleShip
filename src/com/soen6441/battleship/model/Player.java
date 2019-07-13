package com.soen6441.battleship.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.soen6441.battleship.model.ship.Ship;
import com.soen6441.battleship.model.turns.Turn;
import com.soen6441.battleship.view.util.Constants;
import com.soen6441.battleship.view.util.randomGen;

public class Player
{
	private String name;
	private String type;
	private Turn currentTurn;
	private List<Turn> turns;
	private Board board;

	public Player(String name, String type)
	{
		this.name = name;
		this.type = type;
		this.turns = new ArrayList<Turn>();
		this.board = new Board();

		if (type == Constants.GAME_PLAYER_COMPUTER_TYPE)
		{
			this.placeComputerShips();
		}
	}

	public void sendAttack(Turn turn, Location attackPosition, Player attacked)
	{
		Attack attack = this.createAttack(attackPosition);
		attacked.recieveAttack(turn, attack);
	}

	public Attack createAttack(Location attackPosition)
	{
		if (type == Constants.GAME_PLAYER_HUMAN_TYPE && attackPosition != null)
		{
			Attack attack = new humanAttack(attackPosition);
			return attack;
		}
		else
		{
			Attack attack = new ComputerAttack();
			return attack;
		}
	}

	public void recieveAttack(Turn turn, Attack attack)
	{

		Map<Boolean, Ship> validation = board.validateAttack(attack.getAttackPosition());

		// if (validation.size() > 0) {
		// System.out.println("HIT");
		// attack.setState(true);
		// turn.setStatus(true);
		//
		// }else {
		// System.out.println("MISS");
		// attack.setState(false);
		// turn.setStatus(false);
		// }

		turn.setResult(validation);
	}

	private void placeComputerShips()
	{
		System.out.println("Trying to place computer ships" + this.getBoard().getShips().size());
		List<Location> locations = new ArrayList<Location>();

		for (Ship ship : this.board.getShips())
		{
			boolean allocationComplete = false;
			System.out.println(ship.getName());
			// tempLocation to hold temporary locations that may apply to ship
			// if all conditions are met.
			List<Location> tempLocation = new ArrayList<Location>();

			// we continue to find locations until the ship is fully allocated.
			while (!allocationComplete)
			{
				boolean axis = randomGen.generate(2) == 1;
				Integer x = randomGen.generate(Constants.BOARD_SIZE);
				Integer y = randomGen.generate(Constants.BOARD_SIZE);

				// iterate ship size to find grid locations horizontally or
				// vertically.
				for (int i = 0; i < ship.getSize(); i++)
				{

					Location location = null;

					if (axis)
					{
						// we do horizontal
						location = new Location(x + i, y);

					}
					else
					{
						// we do vertical
						location = new Location(x, y + i);
					}

					if (locations.contains(location) || location.getX() > Constants.BOARD_SIZE || location.getY() > Constants.BOARD_SIZE)
					{
						tempLocation = new ArrayList<Location>();
						allocationComplete = false; // reset allocation flag
													// here so past results
													// don't affect operations.
						break;
					}
					else
					{
						tempLocation.add(location);
						allocationComplete = true;
					}
				}
			}

			ship.setLocationOccupied(tempLocation);
			locations.addAll(tempLocation);

		}

		this.board.getOccupied().addAll(locations);

		for (Location l : locations)
		{
			System.out.println("locations " + l.getX() + " " + l.getY());
		}

	}

	private boolean validateAttack(Attack attack)
	{
		return false;
	}

	public String getName()
	{
		return this.name;
	}

	public String getType()
	{
		return this.type;
	}

	public Board getBoard()
	{
		return this.board;
	}

	public List<Turn> getTurns()
	{
		return this.turns;
	}

}
