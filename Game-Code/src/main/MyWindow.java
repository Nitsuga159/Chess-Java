package main;

import java.awt.*;

import javax.swing.*;

import board.Board;

public class MyWindow extends JFrame {
	
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
	
	public static ImageIcon getImageIcon(String path) {
		ImageIcon image;
		try {
			image = new ImageIcon(new MyWindow().getClass().getResource(path));
		} catch(NullPointerException e) {
			image = new ImageIcon("pieces-image" + path);
		}
			
		
		return image;
	}
	
	public static JLabel getLabelIcon(String path) {
		return new JLabel(getImageIcon(path));
	}
	
	public final static Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
	
}
