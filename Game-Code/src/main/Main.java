package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.*;

import board.Board;

public class Main {
	
	public static void main(String args[]) {
		MyWindow myWindow = new MyWindow();
		
		myWindow.setTitle("Chess");
		myWindow.setIconImage(
			MyWindow.getImageIcon("/chess-icon.png")
			.getImage()
		);

		myWindow.add(new Board());
		
		
		 
	
		
		myWindow.initWindow(Board.SIZE, Board.SIZE);
	}
}

