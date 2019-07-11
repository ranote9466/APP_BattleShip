package com.soen6441.battleship.ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.soen6441.battleship.util.Constants;


public class AttackGridView
{
	private JPanel[] gameBoard = new JPanel[2];
	private JFrame frame = new JFrame();
	private JButton[][] grid = new JButton[Constants.BOARD_LETTERS.length][Constants.BOARD_NUMBERS.length];

	public final void setgameBoard()
	{
		frame.setLayout(new GridLayout());
		
			for(int x=0; x<2; x++ ) {
				
				gameBoard[x] = new JPanel();
				
				gameBoard[x].setLayout(new GridLayout(Constants.BOARD_LETTERS.length, Constants.BOARD_NUMBERS.length));
		grid = new JButton[Constants.BOARD_LETTERS.length][Constants.BOARD_NUMBERS.length];
		for (int j = 0; j < Constants.BOARD_LETTERS.length; j++)
		{

			for (int i = 0; i < Constants.BOARD_NUMBERS.length; i++)
			{

				if (i != 0 && j != 0)
				{
					grid[i][j] = new JButton();
					grid[i][j].setForeground(Color.GREEN);
					if(x==0 ) {grid[i][j].setBorder(BorderFactory.createMatteBorder(
                            1, 1, 1, 1, Color.RED));}
					else {grid[i][j].setBorder(BorderFactory.createMatteBorder(
                            1, 1, 1, 1, Color.BLUE));}

					grid[i][j].setBackground(Color.black);
					gameBoard[x] .add(grid[i][j]);
					// grid[i][j].addActionListener(new BoardListener());
				}
				if (i == 0)
				{
					if (j != 0)
					{
						JTextField t = new JTextField(Constants.BOARD_NUMBERS[j]);
						t.setEditable(false);
						t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
						gameBoard[x] .add(t);
					}
					else
					{
						JTextField t = new JTextField();
						t.setEditable(false);
						gameBoard[x] .add(t);
					}
				}
				else if (j == 0)
				{
					JTextField t = new JTextField(Constants.BOARD_LETTERS[i]);
					t.setEditable(false);
					t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
					gameBoard[x].add(t);
				}
			}
		}
		// gameBoard[x].setSize(250, 250);
		gameBoard[x] .setVisible(true);
			
		frame.add(gameBoard[x]);
			}
			frame.setSize(1280, 768);
		frame.setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
		new AttackGridView().setgameBoard();
	}

}