package main;

import java.awt.*;

import javax.swing.*;

import board.Board;

public class Window extends JFrame {
	
	public void initWindow(int width, int height) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		Insets margins = getInsets();
		
		setSize(
				width + margins.left + margins.right,
				height + margins.top + margins.bottom
		);
		
		
		setLocationRelativeTo(null);
		
		
	}
	
	
	
	public final static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
}
