package main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import manager.Handler;
import manager.KeyInput2;

public class Frame {
	public JFrame frame;
	public JPanel panel;
	
	public Frame(int width, int height, Game game) {		
		frame = new JFrame();
		panel = new JPanel();
		panel.add(game);
		//frame.add(panel);
		
		game.setPreferredSize(new Dimension(width, height));
		//asdgame.setFocusable(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Practice!");
		frame.setResizable(false);
		
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
	}
}