package main;

import java.awt.*;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		Window myWindow = new Window();
		
		myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
}

class Window extends JFrame {
	public Window() {
		setBounds(200, 200, 800, 800);
		setVisible(true);
		
		add(new Board());
	}
	
	private class Board extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Dimension windowSize = getSize();
			
			int width = (int) 800 / 8;
			int height = (int) 800 / 8;
			
			Color white = Color.WHITE;
			Color brown = new Color(130, 84, 20);
			
			for(int i = 0; i < 8; i++) {
				Color colorSelected = i % 2 == 0 ? white : brown;
				for(int j = 0; j < 8; j++) {
					g.setColor(colorSelected);
					Rectangle rect = new Rectangle(width * i, height * j, width, height);
					Graphics2D g2d = (Graphics2D) g;
					g2d.fill(rect);
					
					colorSelected = colorSelected == white ? brown : white;
				}
			}
			
		}
	}
}




