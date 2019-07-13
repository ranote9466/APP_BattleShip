package com.soen6441.battleship.ui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.soen6441.battleship.exceptions.gameException;
import com.soen6441.battleship.listener.AttackGridListener;
import com.soen6441.battleship.view.util.Constants;

public class AttackGridView
{

	private JPanel gameBoard1, gameBoard2;

	private JFrame frame = new JFrame();
	private JButton[][] playerOneGrid = new JButton[Constants.BOARD_LETTERS.length][Constants.BOARD_NUMBERS.length];
	private JButton[][] playerTwoGrid = new JButton[Constants.BOARD_LETTERS.length][Constants.BOARD_NUMBERS.length];

	public static AttackGridView attackGridView_instance = null;

	public static AttackGridView getInstance()
	{
		if (attackGridView_instance == null)
			attackGridView_instance = new AttackGridView();

		return attackGridView_instance;
	}

	public final void setgameBoard() throws gameException
	{
		frame.setLayout(new GridLayout());

		gameBoard1 = new JPanel();
		gameBoard2 = new JPanel();

		gameBoard1.setLayout(new GridLayout(Constants.BOARD_LETTERS.length, Constants.BOARD_NUMBERS.length));
		playerOneGrid = new JButton[Constants.BOARD_LETTERS.length][Constants.BOARD_NUMBERS.length];
		for (int j = 0; j < Constants.BOARD_LETTERS.length; j++)
		{

			for (int i = 0; i < Constants.BOARD_NUMBERS.length; i++)
			{

				if (i != 0 && j != 0)
				{
					playerOneGrid[i][j] = new JButton();
					playerOneGrid[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));

					playerOneGrid[i][j].setBackground(Color.black);
					playerOneGrid[i][j].setEnabled(false);
					playerOneGrid[i][j].addActionListener(new AttackGridListener(0, i, j));
					gameBoard1.add(playerOneGrid[i][j]);

				}
				if (i == 0)
				{
					if (j != 0)
					{
						JTextField t = new JTextField(Constants.BOARD_NUMBERS[j]);
						t.setEditable(false);
						t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
						gameBoard1.add(t);
					}
					else
					{
						JTextField t = new JTextField();
						gameBoard1.add(t);
					}
				}
				else if (j == 0)
				{
					JTextField t = new JTextField(Constants.BOARD_LETTERS[i]);
					t.setEditable(false);
					t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
					gameBoard1.add(t);
				}
			}
		}
		// gameBoard[x].setSize(250, 250);

		gameBoard2.setLayout(new GridLayout(Constants.BOARD_LETTERS.length, Constants.BOARD_NUMBERS.length));
		playerTwoGrid = new JButton[Constants.BOARD_LETTERS.length][Constants.BOARD_NUMBERS.length];
		for (int j = 0; j < Constants.BOARD_LETTERS.length; j++)
		{

			for (int i = 0; i < Constants.BOARD_NUMBERS.length; i++)
			{

				if (i != 0 && j != 0)
				{
					playerTwoGrid[i][j] = new JButton();
					playerTwoGrid[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));

					playerTwoGrid[i][j].setBackground(Color.black);
					gameBoard2.add(playerTwoGrid[i][j]);
					playerTwoGrid[i][j].addActionListener(new AttackGridListener(1, i, j));
				}
				if (i == 0)
				{
					if (j != 0)
					{
						JTextField t = new JTextField(Constants.BOARD_NUMBERS[j]);
						t.setEditable(false);
						t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
						gameBoard2.add(t);
					}
					else
					{
						JTextField t = new JTextField();
						t.setEditable(false);
						gameBoard2.add(t);
					}
				}
				else if (j == 0)
				{
					JTextField t = new JTextField(Constants.BOARD_LETTERS[i]);
					t.setEditable(false);
					t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
					gameBoard2.add(t);
				}
			}
		}

		gameBoard1.setVisible(true);
		gameBoard2.setVisible(true);

		frame.add(gameBoard1);
		frame.add(gameBoard2);

		frame.setSize(1280, 768);
		frame.setVisible(true);
	}

	public JPanel getGameBoard1()
	{
		return gameBoard1;
	}

	public void setGameBoard1(JPanel gameBoard1)
	{
		this.gameBoard1 = gameBoard1;
	}

	public JPanel getGameBoard2()
	{
		return gameBoard2;
	}

	public void setGameBoard2(JPanel gameBoard2)
	{
		this.gameBoard2 = gameBoard2;
	}

	public JButton getPlayerOneGrid(Integer x, Integer y)
	{
		return playerOneGrid[x][y];
	}

	public void setPlayerOneGrid(JButton[][] playerOneGrid)
	{
		this.playerOneGrid = playerOneGrid;
	}

	public JButton getPlayerTwoGrid(Integer x, Integer y)
	{
		return playerTwoGrid[x][y];
	}

	public void setPlayerTwoGrid(JButton[][] playerTwoGrid)
	{
		this.playerTwoGrid = playerTwoGrid;
	}

	public static void main(String[] args)
	{
		try
		{
			AttackGridView.getInstance().setgameBoard();
		}
		catch (gameException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
