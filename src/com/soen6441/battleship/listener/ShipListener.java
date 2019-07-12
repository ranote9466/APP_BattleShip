package com.soen6441.battleship.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.soen6441.battleship.ui.BattleGridView;
import com.soen6441.battleship.util.Constants;
import com.soen6441.battleship.util.DirectionType;
import com.soen6441.battleship.util.ShipType;

public class ShipListener implements ActionListener
{
	private BattleGridView battleGrid = new BattleGridView();
	private BoardListener boardlistener = new BoardListener(battleGrid);

	public ShipListener(BattleGridView battleGrid)
	{
		this.battleGrid = battleGrid;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// System.out.println("Entered shiplistener");
		// Get the index of the selected ship from the combo box
		int shipIndex = battleGrid.getShipTypeComboBox().getSelectedIndex();
		int i = shipIndex;
		ShipType shipType = null;
		// Figure out which ship is associated with the index of the combo
		// box
		switch (shipIndex)
		{
		case Constants.COMBX_CARRINDEX:
		{
			i = Constants.CARRINDEX;
			shipType = ShipType.CARRIER;
			break;
		}
		case Constants.COMBX_BATTINDEX:
		{
			i = Constants.BATTINDEX;
			shipType = ShipType.BATTLESHIP;
			break;
		}
		case Constants.COMBX_CRUSINDEX:
		{
			i = Constants.CRUSINDEX;
			shipType = ShipType.CRUISER;
			break;
		}
		case Constants.COMBX_SUBMINDEX:
		{
			i = Constants.SUBMINDEX;
			shipType = ShipType.SUBMARINE;
			break;
		}
		case Constants.COMBX_DESTINDEX:
		{
			i = Constants.DESTINDEX;
			shipType = ShipType.DESTROYER;
			break;
		}
		default:
		{

		}
		}
		// Figure out the current direction selected in the direction combo
		// box

		boardlistener.setActiveShip(shipType);
		// System.out.println(" value in board lis is set" +
		// boardlistener.getActiveShip());
		int dirIndex = battleGrid.getDirTypeComboBox().getSelectedIndex();
		DirectionType dirType = null;
		switch (dirIndex)
		{
		case 0:
		{
			dirType = DirectionType.VERTICAL;
			break;
		}
		case 1:
		{
			dirType = DirectionType.HORIZONTAL;
			break;
		}
		default:
		{

		}
		}
		// Set the currently selected ship's direction to the currently
		// selected direction

		boardlistener.setDirType(dirType);
		// System.out.println("Value in board is set for dir " +
		// boardlistener.getDirType());
	}
}
