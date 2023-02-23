package board;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import piece.*;

import main.Window;

public class Board extends JLayeredPane {
	public Board() {
		
		boardPanel.setLayout(new GridLayout(LENGTH_BOARD, LENGTH_BOARD));
		boardPanel.setBounds(0, 0, SIZE, SIZE);
		
		initCells();
		initPieces();
		drawBoard();

		add(boardPanel, 1);
		
	}
	
	private void initCells() {
		for(int row = 0; row < LENGTH_BOARD; row++) {
			cells[row] = new Cell[8];
			for(int column = 0; column < LENGTH_BOARD; column++)
				cells[row][column] = new Cell();
		}

	}
	
	private void drawBoard() {
		
		for(int row = 0; row < LENGTH_BOARD; row++) {
			Color selectedColor = row % 2 == 0 ? Cell.WHITE : Cell.BROWN;
			for(int column = 0; column < LENGTH_BOARD; column++) {
				
				JPanel cell = new JPanel();
				
				Cell currentCell = cells[row][column];
				
				cell.setBackground(selectedColor);
				currentCell.setDefaultColor(selectedColor);
				
				selectedColor = selectedColor == Cell.WHITE ? Cell.BROWN : Cell.WHITE;
				
				Piece piece = currentCell.getPiece();
				
				JLabel labelIcon = new JLabel();
				labelIcon.setHorizontalAlignment(SwingConstants.CENTER);
				labelIcon.setVerticalAlignment(SwingConstants.CENTER);
				
				if(piece != null)
					labelIcon.setIcon(
						new ImageIcon(
							getClass().getResource(piece.getImageURL())
						)
					);
				
				
				currentCell.setLabel(labelIcon);
				cell.setLayout(new BorderLayout());
				cell.add(labelIcon, BorderLayout.CENTER);
				
				currentCell.setSheet(cell);
				
				
				cell.addMouseListener(new MouseEventPressed(this, currentCell, row, column));
				
				boardPanel.add(cell);
				
				
			}
		}
		
	}
	
	private void initPieces() {
		for(int row = 0; row < LENGTH_BOARD; row++)
			for(int column = 0; column < LENGTH_BOARD; column++) 
				cells[row][column].setPiece(null);
		
	
		Random rand = new Random();
		
		int randomNumber = rand.nextInt(2);
		
		fill(randomNumber == 0 ? Piece.BLACK : Piece.WHITE, false);
		fill(randomNumber == 0 ? Piece.WHITE : Piece.BLACK, true);
	}

		public void fill(UUID color, boolean inEnd) {
			int row = inEnd ? LENGTH_BOARD - 1 : 0;
			
			cells[row][0].setPiece(new Rook(this, color, inEnd, row, 0));
			cells[row][1].setPiece(new Knight(this, color, inEnd, row, 1));
			cells[row][2].setPiece(new Bishop(this, color, inEnd, row, 2));
			cells[row][3].setPiece(new Queen(this, color, inEnd, row, 3));
			cells[row][4].setPiece(new King(this, color, inEnd, row, 4));
			cells[row][5].setPiece(new Bishop(this, color, inEnd, row, 5));
			cells[row][6].setPiece(new Knight(this, color, inEnd, row, 6));
			cells[row][7].setPiece(new Rook(this, color, inEnd, row, 7));
			
			int secondRow = row == 0 ? row + 1 : row - 1;
			
			for(int i = 0; i < LENGTH_BOARD; i++)
				cells[secondRow][i].setPiece(new Pawn(this, color, inEnd, secondRow, i));
		}
	
	
	public Cell[][] getCells() {
		return this.cells;
	}
	
	public Cell getCell(int row, int column) {
		if(row < 0 || row > LENGTH_BOARD) return null;
		if(column < 0 || column > LENGTH_BOARD) return null;
		
		return this.cells[row][column];
	}
	
	public Piece getCellPiece(int row, int column) {
		if(row < 0 || row > LENGTH_BOARD) return null;
		if(column < 0 || column > LENGTH_BOARD) return null;
		
		return this.cells[row][column].getPiece();
	}
	
	public JLabel getCellLabel(int row, int column) {
		if(row < 0 || row > LENGTH_BOARD) return null;
		if(column < 0 || column > LENGTH_BOARD) return null;
		
		return this.cells[row][column].getLabel();
	}
	
	public JPanel getCellSheet(int row, int column) {
		if(row < 0 || row > LENGTH_BOARD) return null;
		if(column < 0 || column > LENGTH_BOARD) return null;
		
		return this.cells[row][column].getSheet();
	}
	
	public UUID getWinner() {
		return this.winner;
	}
	
	public void setWinner(UUID winner) {
		this.winner= winner;
		add(new Winner(winner.equals(Piece.BLACK) ? "black" : "white", this), 0);
	}
	
	public void restartBoard() {
		removeAll();
		boardPanel.removeAll();
		
		this.winner = null; 
		initPieces();
		drawBoard();
		MouseEventPressed.initStaticProperties();

		add(boardPanel, 1);
		
		repaint();
	}
 	
	public final static int SIZE = Toolkit.getDefaultToolkit().getScreenSize().height > 1000 ? 800 : 600 ;
	public final static int LENGTH_BOARD = 8;
	private UUID winner;
	private Cell[][] cells = new Cell[LENGTH_BOARD][];
	private JPanel boardPanel = new JPanel();
}



