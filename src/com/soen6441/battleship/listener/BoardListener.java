package com.soen6441.battleship.listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.soen6441.battleship.service.ShipDetails;
import com.soen6441.battleship.ui.AttackGridView;
import com.soen6441.battleship.ui.BattleGridView;
import com.soen6441.battleship.util.Constants;
import com.soen6441.battleship.util.DirectionType;
import com.soen6441.battleship.util.ShipType;

public class BoardListener implements ActionListener

{
	private BattleGridView battleGrid = new BattleGridView();
	private ShipType[][] placeShip = new ShipType[Constants.BOARD_NUMBERS.length][Constants.BOARD_LETTERS.length];
	private boolean[][] hitOrMiss = new boolean[Constants.BOARD_NUMBERS.length][Constants.BOARD_LETTERS.length];
	private AttackGridView attackGrid = new AttackGridView();
	private static ShipType activeShip = ShipType.CARRIER;
	private ShipDetails[] ships = new ShipDetails[5];
	private static int shipsPlaced = 0;
	private static DirectionType dirType = DirectionType.VERTICAL;

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
				for (int j = 0; j < Constants.BOARD_LETTERS.length; j++)
				{
					for (int i = 0; i < Constants.BOARD_NUMBERS.length; i++)
					{
						hitOrMiss[i][j] = (placeShip[i][j] != null);
					}
				}
				battleGrid.getPlayer1Frame().setVisible(false);
				attackGrid.setgameBoard();
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
			// battleGrid.getPlayer1Frame().setExtendedState(MAXIMIZED_BOTH);

			// battleGrid.getPlayer1Frame().pack();
		}
		else
		{
			{
				// System.out.println(" event source is " + event.getSource());
				if (shipsPlaced < 5)
				{
					int i = 0, j = 0;

					for (int b = 1; b < Constants.BOARD_LETTERS.length; b++)
					{
						for (int a = 1; a < Constants.BOARD_NUMBERS.length; a++)
						{
							if (source == battleGrid.getGrid(a, b))
							{
								// System.out.println(" get grid value  a and b "
								// + a + " " + b);
								i = a;
								j = b;
								a = Constants.BOARD_NUMBERS.length + 1;
								b = Constants.BOARD_LETTERS.length + 1;
							}
						}
					}
					// *************************
					int index = 0;
					ships[Constants.DESTINDEX] = new ShipDetails(ShipType.DESTROYER);
					ships[Constants.SUBMINDEX] = new ShipDetails(ShipType.SUBMARINE);
					ships[Constants.CRUSINDEX] = new ShipDetails(ShipType.CRUISER);
					ships[Constants.BATTINDEX] = new ShipDetails(ShipType.BATTLESHIP);
					ships[Constants.CARRINDEX] = new ShipDetails(ShipType.CARRIER);

					// **************
					// System.out.println(" battleGrid.getShipTypeComboBox().getSelectedItem() "
					// + battleGrid.getShipTypeComboBox().getSelectedItem());

					// activeShip =
					// battleGrid.getShipTypeComboBox().getSelectedItem();
					// **************

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

					// Now, if the ship hasn't already been placed, then try and
					// place
					// it at the selected location.
					if (!ships[index].isPlaced())
					{
						// System.out.println(" For ship the dir is " + dirType
						// + " shiptype" + activeShip);
						// dirType = ships[index].getDirection();
						// System.out.println(" dirType based on ge direction "
						// + dirType);
						switch (dirType)
						{
						// Horizontal case
						case HORIZONTAL:
						{
							// If it is too long to be placed then notify the
							// user
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
									System.out.println("placeShip[i][j + k] is  " + placeShip[i][j + k] + " for horizontal  ");
									if (placeShip[i + k][j] != null)
									{
										test = true;
									}
								}
								// Else, if there is a ship already there, then
								// notify
								// the user
								if (test)
								{
									JFrame frame = new JFrame();
									System.out.println(" COLLIDED horizontal ");
									JOptionPane.showMessageDialog(frame, "Sorry, you can't put a " + ships[index].getName() + " there because there is" + " already a ship there!");
								}
								else
								{
									// Finally, if the ship is not too long and
									// there is
									// no ship there yet then place the ship
									// System.out.println(" value of i " + i +
									// " j is " + j);

									// placeShip(activeShip,
									// ships[index].getDirection(), i, j);
									placeShip(activeShip, dirType, i, j);

									shipsPlaced++;
									// System.out.println(" value of ship placed "
									// +
									// shipsPlaced);
									// If, all the player's hips have been
									// placed
									// then
									// notify them that it is time to deploy.
									if (shipsPlaced >= 5)
									{
										JFrame frame = new JFrame();
										JOptionPane.showMessageDialog(frame, "All your ships have " + "been placed. Deploy now!");
									}
								}
							}
							break;
						}
						// Vertical case
						case VERTICAL:
						{
							// If it is too long to be placed then notify the
							// user
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
									System.out.println("placeShip[i][j + k] is  " + placeShip[i][j + k] + " for vertical ");
									if (placeShip[i][j + k] != null)
									{
										test = true;
									}
								}
								// Else, if there is a ship already there, then
								// notify
								// the user
								if (test)
								{
									JFrame frame = new JFrame();
									System.out.println(" COLLIDING vertical ");
									JOptionPane.showMessageDialog(frame, "Sorry, you can't put a " + ships[index].getName() + " there because there is " + "already a ship there!");
								}
								else
								{
									// Finally, if the ship is not too long and
									// there is
									// no ship there yet then place the ship
									// System.out.println(" 2nd else value of i "
									// +
									// i + " j is " + j + " value of dirct is "
									// +
									// dirType);
									placeShip(activeShip, dirType, i, j);
									// placeShip(activeShip,
									// ships[index].getDirection(), i, j);

									shipsPlaced++;
									// If, all the player's hips have been
									// placed
									// then
									// notify them that it is time to deploy.
									if (shipsPlaced >= 5)
									{
										JFrame frame = new JFrame();
										JOptionPane.showMessageDialog(frame, "All your ships have " + "been placed. Place the ships now!");
									}
								}
							}
							break;
						}
						default:
						{

						}
						}

						// *************************

					}
				}
			}
		}
	}

	public final void placeShip(ShipType shipType, DirectionType dirType, int startX, int startY)
	{
		// System.out.println(" inside placeShip " + shipType);

		int length = 0;
		int index = 0;

		switch (shipType)
		{
		case CARRIER:
		{
			length = Constants.CARRIERLENGTH;
			index = Constants.CARRINDEX;
			break;
		}
		case BATTLESHIP:
		{
			length = Constants.BATTLESHIPLENGTH;
			index = Constants.BATTINDEX;
			break;
		}
		case CRUISER:
		{
			length = Constants.CRUISERLENGTH;
			index = Constants.CRUSINDEX;
			break;
		}
		case SUBMARINE:
		{
			length = Constants.SUBMARINELENGTH;
			index = Constants.SUBMINDEX;
			break;
		}
		case DESTROYER:
		{
			length = Constants.DESTROYERLENGTH;
			index = Constants.DESTINDEX;
			break;
		}
		default:
		{

		}
		}
		// System.out.println(" before orange the dir is " + dirType +
		// " for ship " + activeShip);
		switch (dirType)
		{

		case VERTICAL:
		{
			for (int k = 0; k < length; k++)
			{
				placeShip[startX][startY + k] = shipType;
				// System.out.println(" Battle grid is start x  " + startX +
				// " start y + k " + (startY + k));
				battleGrid.getGrid(startX, startY + k).setBackground(Color.ORANGE);
			}
			break;
		}
		case HORIZONTAL:
		{

			for (int k = 0; k < length; k++)
			{
				placeShip[startX + k][startY] = shipType;
				battleGrid.getGrid(startX + k, startY).setBackground(Color.ORANGE);
			}
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
}
