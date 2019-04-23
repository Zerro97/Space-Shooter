package main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Frame {
	// JFrame
	public JFrame frame;
	
	public Frame(int width, int height, Game game) {
		frame = new JFrame();
		
		game.setPreferredSize(new Dimension(width, height));
		game.setFocusable(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Practice!");
		frame.setResizable(false);

		frame.add(game);
		frame.pack();
		frame.setVisible(true);
	}
}