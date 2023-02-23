package board;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import piece.Coords;
import piece.Piece;

public class MouseEventPressed extends MouseAdapter {
	public MouseEventPressed(Board board, Cell cellEvent, int row, int column) {
		this.board = board;
		this.cells = board.getCells();
		this.cellEvent = cellEvent;
		this.row = row;
		this.column = column;
	}

	public void mousePressed(MouseEvent e) {
		
		if(board.getWinner() != null || selectNewPiece != null) return;
		
		
		Piece cellEventPiece = cellEvent.getPiece();
		Piece prevCellEventPiece = prevCellEvent != null ? prevCellEvent.getPiece() : null;
		
		JPanel cellEventSheet = cellEvent.getSheet();
		JPanel prevEventSheet = prevCellEvent != null ? prevCellEvent.getSheet() : null;
		
		JLabel cellEventLabelIcon = cellEvent.getLabel();
		JLabel prevCellEventLabelIcon = prevCellEvent != null ? prevCellEvent.getLabel() : null;
		
		if(
			cellEventPiece != null &&
			!cellEventPiece.getTeamColor().equals(playerTeam) &&
			(prevCellEvent == null || !prevCellEventPiece.isValidMove(coords, row, column))
		) return;
		
		if(cellEventPiece == null && prevCellEvent == null) return;
		
		if(prevCellEvent != null) {
			prevEventSheet.setBackground(prevCellEvent.getDefaultColor());
			
			boolean isValidMove = prevCellEventPiece.isValidMove(coords, row, column);
			
			if(isValidMove) {
				Icon currentIcon = prevCellEventLabelIcon.getIcon();
				cellEventLabelIcon.setIcon(currentIcon);
				prevCellEventLabelIcon.setIcon(null);
				
				
				prevCellEventPiece.setCoords(row, column);
				
				if(cellEventPiece != null && cellEventPiece.getType() == Piece.KING) {
					if(cellEventPiece.getTeamColor().equals(Piece.WHITE))
						board.setWinner(Piece.BLACK);
					else
						board.setWinner(Piece.WHITE);
					
				} else {
					prevCellEventPiece.afterPieceMoved(this);
					
					cellEvent.setPiece(prevCellEventPiece);			
				}
				
				
				playerTeam = playerTeam.equals(Piece.WHITE) ? Piece.BLACK : Piece.WHITE;
				prevCellEvent.setPiece(null);
			}
			
			board.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			Cell.paintCellsValids(coords, cells, true);
			
			if(!isValidMove && prevCellEvent != cellEvent && cellEventPiece != null) {
				coords = cellEventPiece.getAllMoves();
				
				Cell.paintCellsValids(coords, cells, false);
				
				cellEventSheet.setBackground(cellEvent.getDefaultColor() == Cell.WHITE? Cell.WHITE_SELECTED : Cell.BROWN_SELECTED);
				board.setCursor(new Cursor(Cursor.HAND_CURSOR));
				prevCellEvent = cellEvent;
				
			} else prevCellEvent = null;
			
		} else {
			coords = cellEventPiece.getAllMoves();
			
			Cell.paintCellsValids(coords, cells, false);
			
			cellEventSheet.setBackground(cellEvent.getDefaultColor() == Cell.WHITE? Cell.WHITE_SELECTED : Cell.BROWN_SELECTED);
			board.setCursor(new Cursor(Cursor.HAND_CURSOR));
			prevCellEvent = cellEvent;
		}
		
		board.repaint();
	}
	
	public void setActiveSelectNewPiece(UUID playerColor, int row, int column) {
		selectNewPiece = new SelectNewPiece(playerColor, cells[row][column], board, this);
		board.add(selectNewPiece, 0);
	}
	
	public void removeSelectNewPiece() {
		board.remove(selectNewPiece);
		board.repaint();
		selectNewPiece = null;
	}
	
	public static void initStaticProperties() {
		prevCellEvent = null;
		coords = null;
		playerTeam = Piece.WHITE;
	}
	
	private Board board;
	private Cell[][] cells;
	private Cell cellEvent;
	private int row;
	private int column;
	private SelectNewPiece selectNewPiece;
	
	private static Cell prevCellEvent;
	private static ArrayList<Coords> coords;
	private static UUID playerTeam = Piece.WHITE;
}
