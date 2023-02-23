package board;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import main.MyWindow;
import piece.*;

public class SelectNewPiece extends JPanel {
	public SelectNewPiece(UUID teamColor, Cell cell, Board board, MouseEventPressed eventInstance) {
		this.color = teamColor.equals(Piece.BLACK) ? "black" : "white";
		
		setLayout(new GridLayout(1, 4));
		setBounds(X, Y, WIDTH, HEIGHT);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setBackground(new Color(200, 200, 200, 200));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        
        selectedItemEvent = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				
				int typeSelected = Integer.parseInt(
						((JLabel) e.getSource()).getName()
						);
				
				Piece newPiece;
				int[] coords = cell.getPiece().getCoords();
				boolean isEnd = cell.getPiece().getIsEndTeam();
				
				if(typeSelected == Piece.ROOK)
					newPiece = new Rook(board, teamColor, isEnd, coords[0], coords[1]);
				else if(typeSelected == Piece.KNIGHT)
					newPiece = new Knight(board, teamColor, isEnd, coords[0], coords[1]);
				else if(typeSelected == Piece.KNIGHT)
					newPiece = new Knight(board, teamColor, isEnd, coords[0], coords[1]);
				else if(typeSelected == Piece.BISHOP)
					newPiece = new Bishop(board, teamColor, isEnd, coords[0], coords[1]);
				else
					newPiece = new Queen(board, teamColor, isEnd, coords[0], coords[1]);
				
				cell.setPiece(newPiece);
				
				ImageIcon newImageIcon = MyWindow.getImageIcon("/" + color + "-" + typeSelected + ".png");
				
				cell.getLabel().setIcon(newImageIcon);
				
				eventInstance.removeSelectNewPiece();
			}
		};
		
		createOption(Piece.ROOK);
		createOption(Piece.KNIGHT);
		createOption(Piece.BISHOP);
		createOption(Piece.QUEEN);
		
		
	}
	
	public void createOption(int type) {
		
		JLabel labelIcon = MyWindow.getLabelIcon("/" + color + "-" + type + ".png");
		
		labelIcon.setName(String.valueOf(type));
		
		labelIcon.addMouseListener(selectedItemEvent);
		
		add(labelIcon);
	}
	
	
	
	private MouseAdapter selectedItemEvent;
	private String color;
	private final static int WIDTH = Board.SIZE / 2;
	private final static int HEIGHT = 100;
	private final static int X = Board.SIZE / 4;
	private final static int Y = Board.SIZE / 2 - HEIGHT / 2;
}
