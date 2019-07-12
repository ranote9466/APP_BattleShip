package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class attackGridListener implements ActionListener{
		int player;
		int x;
		int y;
		String playername;
		String type;
		
	
	public attackGridListener(int plr ,int xCoordinate, int yCoordinate ) {
		// TODO Auto-generated constructor stub
		player= plr;
		x = xCoordinate;
		y = yCoordinate;
		
		if(player == 0) {	playername="PLAYER1"; type = "HUMAN";	}
		else {	playername="PLAYER2";	type ="COMPUTER"; }
		
		
		
		
	} 
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
