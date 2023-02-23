package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.*;

import board.Board;

public class Main {
	
	public static void main(String args[]) {
		Window myWindow = new Window();
		
		myWindow.setTitle("Chess");
		myWindow.setIconImage(
			new ImageIcon(
				new Main().getClass().getResource("/chess-icon.png")
			).getImage()
		);

		myWindow.add(new Board());
		
		
		 
	
		
		myWindow.initWindow(Board.SIZE, Board.SIZE);
	}
}

