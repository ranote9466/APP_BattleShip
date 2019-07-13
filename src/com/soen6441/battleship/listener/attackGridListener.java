package com.soen6441.battleship.listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.soen6441.battleship.controller.Controller;
import com.soen6441.battleship.exceptions.gameException;
import com.soen6441.battleship.model.Player;
import com.soen6441.battleship.model.ship.Ship;
import com.soen6441.battleship.model.turns.Turn;
import com.soen6441.battleship.ui.AttackGridView;
import com.soen6441.battleship.view.util.Constants;
import com.soen6441.battleship.view.util.randomGen;

public class AttackGridListener implements ActionListener
{
	private int grid, x, y;
	private String playername;
	private String type;
	private JButton button;
	private Boolean state;
	private AttackGridView attackGridView = AttackGridView.getInstance();

	// Turn turn;

	public AttackGridListener(int grid, int xCoordinate, int yCoordinate) throws gameException
	{
		this.grid = grid;
		this.x = xCoordinate;
		this.y = yCoordinate;

	}

	private void processEvent(Player player, Integer x, Integer y, Integer grid)
	{
		System.out.println("PROCESS EVENT  pLAYER IS " + player);
		try
		{
			Turn turn = Controller.getInstance().play(player, x, y);

			if (turn.result.containsKey(true))
			{
				for (Ship s : turn.result.values())
				{
					state = true;
					System.out.println(s.getState());
				}
			}
			else
			{
				state = false;
				System.out.println("MISS");
			}

			updateUI(turn, grid);
		}
		catch (gameException e)
		{
			e.printStackTrace();
		}
	}

	private void updateUI(Turn t, Integer grid) throws gameException
	{
		System.out.println(" inside UPDATE UI ATTACK GRID");

		if (grid == 1)
		{

			if (state == true)
			{
				try
				{
					button = attackGridView.getPlayerTwoGrid(t.getlocation().getX(), t.getlocation().getY());
					button.setBackground(Color.MAGENTA);
					button.setEnabled(false);
				}
				catch (Exception e)
				{
				}
			}
			else
			{

				button = attackGridView.getPlayerTwoGrid(t.getlocation().getX(), t.getlocation().getY());
				button.setBackground(Color.white);
				button.setEnabled(false);
			}

		}

		else
		{
			if (state == true)
			{
				button = attackGridView.getPlayerOneGrid(t.getlocation().getX(), t.getlocation().getY());
				button.setBackground(Color.MAGENTA);
				button.setEnabled(false);
			}
			else
			{
				button = attackGridView.getPlayerOneGrid(t.getlocation().getX(), t.getlocation().getY());
				button.setBackground(Color.white);
			}
			button.setEnabled(false);
		}

		if (t.getAttacker().getType() == Constants.GAME_PLAYER_HUMAN_TYPE)
		{
			this.processEvent(Controller.getInstance().start().getPlayers().get(0), randomGen.generate(Constants.BOARD_SIZE), randomGen.generate(Constants.BOARD_SIZE), 0);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		button = new JButton();
		if (grid == 1)
		{
			this.processEvent(Controller.getInstance().start().getPlayers().get(1), x, y, grid);
		}
		else
		{
			this.processEvent(Controller.getInstance().start().getPlayers().get(0), null, null, grid);
		}
	}

}
