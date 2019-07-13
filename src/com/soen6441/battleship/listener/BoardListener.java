package com.soen6441.battleship.listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.soen6441.battleship.controller.Controller;
import com.soen6441.battleship.exceptions.gameException;
import com.soen6441.battleship.model.Game;
import com.soen6441.battleship.model.Location;
import com.soen6441.battleship.model.Player;
import com.soen6441.battleship.service.ShipDetails;
import com.soen6441.battleship.ui.AttackGridView;
import com.soen6441.battleship.ui.BattleGridView;
import com.soen6441.battleship.view.util.Constants;
import com.soen6441.battleship.view.util.DirectionType;
import com.soen6441.battleship.view.util.ShipType;

public class BoardListener implements ActionListener

{
	private static BattleGridView battleGrid = BattleGridView.getInstance();
	private static ShipType[][] placeShip = new ShipType[Constants.BOARD_NUMBERS.length][Constants.BOARD_LETTERS.length];
	private AttackGridView attackGrid = AttackGridView.getInstance();

	private static ShipType activeShip = ShipType.CARRIER;
	private ShipDetails[] ships = new ShipDetails[Constants.NUM_OF_SHIPS];

	private static int shipsPlaced = 0;
	private static DirectionType dirType = DirectionType.VERTICAL;
	private Location location;
	private Location locationForCalculations;
	private Controller controller;
	private Game game;
	private List<Location> shipPlacedLocList = new ArrayList<Location>();

	private static Map<String, List<Location>> shipPlacedTypeMap = new HashMap<String, List<Location>>();

	public BoardListener(BattleGridView battleGrid)
	{
		this.battleGrid = battleGrid;
	}

	@Override
	public void actionPerformed(final ActionEvent event)
	{
		Object source = (JButton) event.getSource();

		String action = event.getActionCommand();

		if ("Place Ships".equals(action))
		{
			if (shipsPlaced >= 5)
			{
				try
				{
					game = Controller.getInstance().start();
					Player humanPlayer = game.getPlayers().get(1);
					controller = controller.getInstance();
					controller.placeShips(humanPlayer, shipPlacedTypeMap);
				}
				catch (gameException e)
				{
					e.printStackTrace();
				}
				battleGrid.getPlayer1Frame().setVisible(false);
				try
				{
					attackGrid.setgameBoard();
				}
				catch (gameException e)
				{
					e.printStackTrace();
				}
			}

		}
		if ("Reset".equals(action))
		{
			shipsPlaced = 0;
			battleGrid.getBattleGridBoard().removeAll();
			for (int j = 0; j < Constants.BOARD_LETTERS.length; j++)
			{
				for (int i = 0; i < Constants.BOARD_NUMBERS.length; i++)
				{
					placeShip[i][j] = null;

				}
			}
			battleGrid.setBattleGridBoard();
			battleGrid.getPlayer1Frame().add(battleGrid.getBattleGridBoard());
			shipPlacedTypeMap.remove(shipPlacedLocList);
			shipPlacedLocList.clear();
		}
		else
		{
			{
				if (shipsPlaced < Constants.NUM_OF_SHIPS)
				{
					int i = 0, j = 0;

					for (int b = 1; b < Constants.BOARD_LETTERS.length; b++)
					{
						for (int a = 1; a < Constants.BOARD_NUMBERS.length; a++)
						{
							if (source == battleGrid.getGrid(a, b))
							{
								i = a;
								j = b;
								a = Constants.BOARD_NUMBERS.length + 1;
								b = Constants.BOARD_LETTERS.length + 1;
							}
						}
					}
					int index = 0;
					ships[Constants.DESTINDEX] = new ShipDetails(ShipType.DESTROYER);
					ships[Constants.SUBMINDEX] = new ShipDetails(ShipType.SUBMARINE);
					ships[Constants.CRUSINDEX] = new ShipDetails(ShipType.CRUISER);
					ships[Constants.BATTINDEX] = new ShipDetails(ShipType.BATTLESHIP);
					ships[Constants.CARRINDEX] = new ShipDetails(ShipType.CARRIER);

					switch (activeShip)
					{
					case CARRIER:
					{
						index = 4;
						break;
					}
					case BATTLESHIP:
					{
						index = 3;
						break;
					}
					case CRUISER:
					{
						index = 2;
						break;
					}
					case SUBMARINE:
					{
						index = 1;
						break;
					}
					case DESTROYER:
					{
						index = 0;
						break;
					}
					default:
					{

					}
					}
					if (!ships[index].isPlaced())
					{
						switch (dirType)
						{
						case HORIZONTAL:
						{
							if ((ships[index].getLength() + i) > placeShip.length)
							{
								JFrame frame = new JFrame();
								JOptionPane.showMessageDialog(frame, "Sorry, a " + ships[index].getName() + " can't be placed here, its too long.");
							}
							else
							{
								boolean test = false;

								for (int k = 0; k < ships[index].getLength(); k++)
								{
									if (placeShip[i + k][j] != null)
									{
										test = true;
									}
								}
								if (test)
								{
									JFrame frame = new JFrame();
									JOptionPane.showMessageDialog(frame, "Sorry, you can't put a " + ships[index].getName() + " there because there is" + " already a ship there!");
								}
								else
								{
									// ****
									placeShipIntoBoard(activeShip, dirType, i, j);

									shipsPlaced++;
									if (shipsPlaced >= 5)
									{
										JFrame frame = new JFrame();
										JOptionPane.showMessageDialog(frame, "All your ships have " + "been placed. Deploy now!");
									}
								}
							}
							break;
						}
						case VERTICAL:
						{
							if ((ships[index].getLength() + j) > placeShip.length)
							{
								JFrame frame = new JFrame();
								JOptionPane.showMessageDialog(frame, "Sorry, a " + ships[index].getName() + " can't be placed here.");
							}
							else
							{
								boolean test = false;
								for (int k = 0; k < ships[index].getLength(); k++)
								{
									if (placeShip[i][j + k] != null)
									{
										test = true;
									}
								}

								if (test)
								{
									JFrame frame = new JFrame();
									JOptionPane.showMessageDialog(frame, "Sorry, you can't put a " + ships[index].getName() + " there because there is " + "already a ship there!");
								}
								else
								{
									placeShipIntoBoard(activeShip, dirType, i, j);
									shipsPlaced++;
									if (shipsPlaced >= 5)
									{
										JFrame frame = new JFrame();
										JOptionPane.showMessageDialog(frame, "All your ships are " + "ready to be placed!!");
									}
								}
							}
							break;
						}
						default:
						{

						}
						}

					}
				}
			}
		}
	}

	public void placeShipIntoBoard(ShipType shipType, DirectionType dirType, Integer startX, Integer startY)
	{

		int length = 0;
		int index = 0;

		switch (shipType)
		{
		case CARRIER:
		{
			length = Constants.SHIP_SIZE_CARRIER;
			index = Constants.CARRINDEX;
			break;
		}
		case BATTLESHIP:
		{
			length = Constants.SHIP_SIZE_BATTLESHIP;
			index = Constants.BATTINDEX;
			break;
		}
		case CRUISER:
		{
			length = Constants.SHIP_SIZE_CRUISER;
			index = Constants.CRUSINDEX;
			break;
		}
		case SUBMARINE:
		{
			length = Constants.SHIP_SIZE_SUBMARINE;
			index = Constants.SUBMINDEX;
			break;
		}
		case DESTROYER:
		{
			length = Constants.SHIP_SIZE_DESTROYER;
			index = Constants.DESTINDEX;
			break;
		}
		default:
		{

		}
		}
		switch (dirType)
		{

		case VERTICAL:
		{
			for (Integer k = 0; k < length; k++)
			{
				location = new Location(startX, startY + k);
				locationForCalculations = new Location(startY + k, startX);
				placeShip[startX][startY + k] = shipType;
				battleGrid.getGrid(startX, startY + k).setBackground(Color.ORANGE);
				shipPlacedLocList.add(locationForCalculations);

			}
			if (shipPlacedTypeMap.containsKey(shipType.toString()))
			{

			}
			else
			{
				shipPlacedTypeMap.put(shipType.toString(), shipPlacedLocList);
			}

			break;
		}
		case HORIZONTAL:
		{
			for (int k = 0; k < length; k++)
			{
				location = new Location(startX + k, startY);

				locationForCalculations = new Location(startY, startX + k);

				placeShip[startX + k][startY] = shipType;
				battleGrid.getGrid(startX + k, startY).setBackground(Color.ORANGE);
				shipPlacedLocList.add(locationForCalculations);

			}

			shipPlacedTypeMap.put(shipType.toString(), shipPlacedLocList);

			break;
		}
		default:
		{

		}
		}

		ships[index].place();

	}

	public BattleGridView getBattleGrid()
	{
		return battleGrid;
	}

	public void setBattleGrid(BattleGridView battleGrid)
	{
		this.battleGrid = battleGrid;
	}

	public ShipType[][] getPlaceShip()
	{
		return placeShip;
	}

	public void setPlaceShip(ShipType[][] placeShip)
	{
		this.placeShip = placeShip;
	}

	public ShipType getActiveShip()
	{
		return activeShip;
	}

	public void setActiveShip(ShipType activeShip)
	{
		this.activeShip = activeShip;
	}

	public ShipDetails[] getShips()
	{
		return ships;
	}

	public void setShips(ShipDetails[] ships)
	{
		this.ships = ships;
	}

	public int getShipsPlaced()
	{
		return shipsPlaced;
	}

	public void setShipsPlaced(int shipsPlaced)
	{
		this.shipsPlaced = shipsPlaced;
	}

	public DirectionType getDirType()
	{
		return dirType;
	}

	public void setDirType(DirectionType dirType)
	{
		this.dirType = dirType;
	}

	public List<Location> getShipPlacedLoc()
	{
		return shipPlacedLocList;
	}

}
