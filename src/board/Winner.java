package board;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

import piece.Piece;

public class Winner extends JPanel {
	public Winner(String teamColor, Board board) { 
		
		setBounds(0, 0, Board.SIZE, Board.SIZE);
		setBackground(new Color(10, 10, 10, 200));
		
		setLayout(null);
		
		String winnerText = teamColor.equals("white") ? "WHITE WON" : "BLACK WON";
		
		//SETEAR EL TEXTO GANADOR
		JLabel labelText = new JLabel(winnerText);
		labelText.setForeground(Color.WHITE);
		
		Font labelTextFont = new Font(Font.DIALOG, Font.BOLD, 32);
		labelText.setFont(labelTextFont);
		
		FontMetrics metrics = getFontMetrics(labelTextFont);
		int widthLabelText = metrics.stringWidth(winnerText);
		
		int heightLabelText = 100;
		
		labelText.setBounds(
				half - widthLabelText / 2, 
				half - heightLabelText / 2 * 4, 
				widthLabelText, 
				heightLabelText);
		
		
		//SETEAR ICONO GANADOR
		int sizeLabelIcon = 150;
		Image scaledImage = 
				new ImageIcon(getClass().getResource("/" + teamColor + "-" + Piece.KING + "-big.png"))
				.getImage()
				.getScaledInstance(sizeLabelIcon, sizeLabelIcon, Image.SCALE_DEFAULT);
		
		JLabel labelIcon = 
				new JLabel(new ImageIcon(scaledImage));
		
		
		labelIcon.setBounds(
				half - sizeLabelIcon / 2, 
				half - sizeLabelIcon / 2, 
				sizeLabelIcon, 
				sizeLabelIcon);
		
		//SETEAR BUTTON
		JButton restartButton = new JButton("NEW GAME");
		restartButton.setBounds(half - 50, half + 150, 100, 50);
		restartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		restartButton.setBackground(Cell.BROWN);
		restartButton.setForeground(Color.WHITE);
		restartButton.setFocusPainted(false);
		
		//SET BUTTON EVENT
		
		restartButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				board.restartBoard();
			}
		});
		
		add(labelText);
		add(labelIcon);
		add(restartButton);
	}
	
	private int half = Board.SIZE / 2;
}
