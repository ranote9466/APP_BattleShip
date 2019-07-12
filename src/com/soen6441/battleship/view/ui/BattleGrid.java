package com.soen6441.battleship.view.ui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.soen6441.battleship.controller.Controller;
import com.soen6441.battleship.exceptions.gameException;
import com.soen6441.battleship.view.util.Constants;

/**
 * The View side of the Battleship game, based on the MVC model. Establishes the
 * frames necessary for holding the various game boards. Also, sets up any
 * necessary menu bars, combo boxes, buttons, and provides methods to add action
 * listeners to those components.
 * 
 * @author Nathan Newman
 * 
 * @convention
 * @correspondence Main GUI of the Battleship game
 * 
 */
public class BattleGrid extends JFrame
{
	/**
	 * Frame used to hold the ship input panel for player 1.
	 */
	private JFrame player1Frame;
	private JFrame mainFrame;
	private JComboBox shipType;
	private JComboBox directionType;
	private JButton placeShips;
	private JButton reset;
	private JMenuItem singlePlayer;
	private JMenuItem twoPlayer;
	private JPanel battleGridBoard = new JPanel();
	private JButton[][] grid = new JButton[Constants.BOARD_LETTERS.length][Constants.BOARD_NUMBERS.length];

	public BattleGrid()
	{
		player1Frame = new JFrame();
		mainFrame = new JFrame("Battleship!");
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
		textField.setText("Choose a ship and its direction");
		textField.setEditable(false);
		inputPanel.add(textField);

		shipType = new JComboBox(ships);
		shipType.setSelectedIndex(0);

		TitledBorder titleBorder;
		titleBorder = BorderFactory.createTitledBorder("Ship Type");
		shipType.setBorder(titleBorder);
		inputPanel.add(shipType);

		directionType = new JComboBox(directions);
		directionType.setSelectedIndex(0);
		// dirChoice.addActionListener(new DirectionListener());

		titleBorder = BorderFactory.createTitledBorder("Direction Type");
		directionType.setBorder(titleBorder);
		inputPanel.add(directionType);

		placeShips = new JButton("Place Ships");
		placeShips.setEnabled(true);
		// deploy.addActionListener(new DeployListener());
		inputPanel.add(placeShips);

		reset = new JButton("Reset");
		placeShips.setEnabled(true);
		// clear.addActionListener(new ClearListener());
		inputPanel.add(reset);
		setBattleGridBoard();

		mainFrame.setLayout(new GridLayout());
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Battle Menu");
		menuBar.add(menu);
		JMenuItem menuItem = new JMenu("New Game");
		menu.add(menuItem);
		singlePlayer = new JMenuItem("Single Player");
		twoPlayer = new JMenuItem("Two Player");
		menuItem.add(singlePlayer);
		menuItem.add(twoPlayer);
		// mainFrame.setJMenuBar(menuBar);
		// mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		player1Frame.add(inputPanel);
		player1Frame.add(battleGridBoard);
		player1Frame.pack();
		player1Frame.setJMenuBar(menuBar);
		player1Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		player1Frame.setVisible(true);
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

	public final void addDeployListener(ActionListener dal)
	{
		placeShips.addActionListener(dal);
	}

	public final void addClearListener(ActionListener cal)
	{
		reset.addActionListener(cal);
	}

	public static void main(String[] args)
	{
		//new BattleGrid().display();
		try {
			new Controller().start();
		} catch (gameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
