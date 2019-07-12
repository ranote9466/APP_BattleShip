package com.soen6441.battleship.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class StartGame extends JFrame
{

	JFrame frame;
	JPanel jp;
	Image image;
	JLabel label;
	ImageObserver imageObserver;
	JButton button;
	private JMenuItem singlePlayer;
	private JMenuItem twoPlayer;

	public StartGame()
	{

		jp = new JPanel();
		label = new JLabel();

		setTitle("Battleship");
		setVisible(true);
		setSize(1280, 720);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		ImageIcon imageicon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/Battleship_icon2.jpg")));
		image = imageicon.getImage();
		imageObserver = imageicon.getImageObserver();

		//
		// ImageIcon img = new ImageIcon(image2);
		label.setIcon(imageicon);

		// button = new JButton();
		// button.setText("Click to play...");
		// button.setOpaque(false);
		// button.setContentAreaFilled(false);
		// button.setBorderPainted(false);
		// button.setForeground(Color.red);
		// button.setFont(new Font("serif",Font.ITALIC,16));
		// button.setAlignmentX(CENTER_ALIGNMENT);
		//
		// jp.add(button, new Integer(0));
		jp.add(label);
		label.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				dispose();
				new BattleGridView().display();
			}

		});
		;
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Battle Menu");
		menuBar.add(menu);
		JMenuItem menuItem = new JMenu("New Game");
		menu.add(menuItem);
		singlePlayer = new JMenuItem("Single Player");
		twoPlayer = new JMenuItem("Two Player");
		menuItem.add(singlePlayer);
		menuItem.add(twoPlayer);
		setJMenuBar(menuBar);

		singlePlayer.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose();
				new BattleGridView().display();
			}
		});

		add(jp);

		validate();

		// frame = new JFrame("Battleship");
		// label = new JLabel();
		// label.setBounds(50,50,300,300);
		//
		// //"BattleShip_L4/BattleShip_L4/Images/Battleship_icon2.jpg"
		//
		// ImageIcon imageicon = new
		// ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/Battleship_icon2.jpg")));
		// Image image = imageicon.getImage();
		//
		// Image image2 = image.getScaledInstance(label.getWidth(),
		// label.getHeight(), image.SCALE_SMOOTH);
		// ImageIcon img = new ImageIcon(image2);
		// label.setIcon(img);
		//
		// frame.getContentPane().add(label);
		//
		// frame.setSize(300,300);
		// frame.getContentPane().setLayout(null);
		// frame.setVisible(true);
		//

		// JPanel panel = new JPanel();
		// getContentPane().add(panel, BorderLayout.CENTER);
	}

	public void paint(Graphics g)
	{

		super.paint(g);

		g.drawImage(image, 00, 40, getWidth(), getHeight(), imageObserver);

	}

	public static void main(String[] args)
	{
		new StartGame();
	}
}
