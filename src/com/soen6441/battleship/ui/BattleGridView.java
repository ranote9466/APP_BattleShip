package com.soen6441.battleship.ui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.soen6441.battleship.listener.BoardListener;
import com.soen6441.battleship.listener.ShipListener;
import com.soen6441.battleship.util.Constants;

/**
 * The View side of the Battleship game, based on the MVC model. Establishes the
 * frames necessary for holding the various game boards. Also, sets up any
 * necessary menu bars, combo boxes, buttons, and provides methods to add action
 * listeners to those components.
 * 
 * @author Sandeep Kaur Ranote
 * 
 * @convention
 * @correspondence Main GUI of the BattleshipGrid game
 * 
 */
public class BattleGridView extends JFrame
{
	private JFrame player1Frame;
	private JFrame mainFrame;
	private JComboBox shipType;
	private JComboBox directionType;
	private JButton placeShips;
	private JButton reset;
	private JMenuItem singlePlayer;
	private JMenuItem twoPlayer;
	private JPanel battleGridBoard;
	private JButton[][] grid = new JButton[Constants.BOARD_LETTERS.length][Constants.BOARD_NUMBERS.length];
	private String[] defaultShipType =
	{ "CARRIER" };

	public BattleGridView()
	{
		player1Frame = new JFrame();
		mainFrame = new JFrame("Battleship!");
		battleGridBoard = new JPanel();
	}

	private static String[] ships =
	{ "Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer" };
	private static String[] directions =
	{ "Vertical", "Horizontal" };

	public final void setBattleGridBoard()
	{

		battleGridBoard.setLayout(new GridLayout(Constants.BOARD_LETTERS.length, Constants.BOARD_NUMBERS.length));
		grid = new JButton[Constants.BOARD_LETTERS.length][Constants.BOARD_NUMBERS.length];

		for (int y = 0; y < Constants.BOARD_LETTERS.length; y++)
		{

			for (int x = 0; x < Constants.BOARD_NUMBERS.length; x++)
			{

				if (x != 0 && y != 0)
				{
					grid[x][y] = new JButton();
					battleGridBoard.add(grid[x][y]);
					grid[x][y].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GREEN));
					grid[x][y].setBackground(Color.black);
					grid[x][y].addActionListener(new BoardListener(this));
				}
				if (x == 0)
				{
					if (y != 0)
					{
						JTextField textField = new JTextField(Constants.BOARD_NUMBERS[y]);
						textField.setEditable(false);
						textField.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
						battleGridBoard.add(textField);

					}
					else
					{
						JTextField textField = new JTextField();
						textField.setEditable(false);
						battleGridBoard.add(textField);
					}
				}
				else if (y == 0)
				{
					JTextField textField = new JTextField(Constants.BOARD_LETTERS[x]);
					textField.setEditable(false);
					textField.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
					battleGridBoard.add(textField);
				}
			}
		}
		battleGridBoard.setLayout(new GridLayout(Constants.BOARD_LETTERS.length, Constants.BOARD_NUMBERS.length));
		battleGridBoard.setVisible(true);

	}

	public final void display()
	{
		player1Frame.setLayout(new GridLayout());
		JPanel inputPanel = new JPanel();
		JTextField textField = new JTextField();
		textField.setText("Place your ships ");
		textField.setEditable(false);
		inputPanel.add(textField);

		shipType = new JComboBox(ships);
		shipType.setSelectedIndex(0);
		shipType.setSelectedItem("CARRIER");
		TitledBorder titleBorder;
		titleBorder = BorderFactory.createTitledBorder("Ship Type");
		shipType.setBorder(titleBorder);
		inputPanel.add(shipType);

		directionType = new JComboBox(directions);
		directionType.setSelectedIndex(0);

		titleBorder = BorderFactory.createTitledBorder("Direction Type");
		directionType.setBorder(titleBorder);
		inputPanel.add(directionType);

		placeShips = new JButton("Place Ships");
		placeShips.setEnabled(true);
		inputPanel.add(placeShips);

		reset = new JButton("Reset");
		placeShips.setEnabled(true);
		inputPanel.add(reset);
		setBattleGridBoard();

		mainFrame.setLayout(new GridLayout());

		player1Frame.add(inputPanel);
		player1Frame.add(battleGridBoard);
		player1Frame.pack();
		player1Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		player1Frame.setExtendedState(MAXIMIZED_BOTH);
		player1Frame.setVisible(true);

		shipType.addActionListener(new ShipListener(this));
		directionType.addActionListener(new ShipListener(this));
		reset.addActionListener(new BoardListener(this));
		placeShips.addActionListener(new BoardListener(this));
	}

	public final JPanel getBattleGridBoard()
	{
		return battleGridBoard;
	}

	public final JFrame getPlayer1Frame()
	{
		return player1Frame;
	}

	public final JFrame getMainFrame()
	{
		return mainFrame;
	}

	public final JComboBox getShipTypeComboBox()
	{
		return shipType;
	}

	public final JComboBox getDirTypeComboBox()
	{
		return directionType;
	}

	public final JButton getGrid(int a, int b)
	{
		return grid[a][b];
	}

}
