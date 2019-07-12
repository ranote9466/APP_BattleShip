package com.soen6441.battleship.view.listener;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import com.soen6441.battleship.controller.Controller;
import com.soen6441.battleship.controller.IController;
import com.soen6441.battleship.exceptions.gameException;
import com.soen6441.battleship.model.Player;
import com.soen6441.battleship.model.ship.Ship;
import com.soen6441.battleship.model.turns.Turn;
import com.soen6441.battleship.view.util.Constants;
import com.soen6441.battleship.view.util.randomGen;


public class attackGridListener implements ActionListener{
private int grid,x, y;
		String playername;
		String type;
		//Turn turn;
		
	
	public attackGridListener(int grid ,int xCoordinate, int yCoordinate ) throws gameException {
		// TODO Auto-generated constructor stub
this.grid=grid;
this.x=xCoordinate;
this.y=yCoordinate;

		
	} 
	
	
	private void processEvent(Player player, Integer x, Integer y, Integer grid) {
		try {
			Turn turn = Controller.getInstance().play(player, x, y);

			
			if (turn.result.containsKey(true)){
				for (Ship s : turn.result.values()) {
					System.out.println(s.getState());
				}
			}else {
				System.out.println("MISS");
			}
			
			
			updateUI(turn, grid);
		} catch (gameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void updateUI(Turn t, Integer grid) throws gameException {
		
		if (t.getAttacker().getType() == Constants.GAME_PLAYER_HUMAN_TYPE) {
			System.out.println("came here");
			System.out.println(t.getAttackedPlayer().getType());
			this.processEvent(Controller.getInstance().start().getPlayers().get(0), randomGen.generate(Constants.BOARD_SIZE), randomGen.generate(Constants.BOARD_SIZE), grid);
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (grid ==1) {
			try {
				this.processEvent(Controller.getInstance().start().getPlayers().get(1), x, y, grid);
			} catch (gameException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else {
			try {
				this.processEvent(Controller.getInstance().start().getPlayers().get(0), null, null, grid);
			} catch (gameException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}	
	}

}