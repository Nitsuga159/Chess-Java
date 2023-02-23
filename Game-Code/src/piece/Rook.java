package piece;

import java.awt.*;
import java.util.*;

import board.Board;
import board.Cell;
import board.MouseEventPressed;

public class Rook extends Piece {
	public Rook(Board board, UUID teamColor, boolean isEndTeam, int row, int column) {
		super(ROOK, teamColor, isEndTeam, row, column);
		
		this.board = board;
	}
	
	public ArrayList<Coords> getAllMoves() {
		ArrayList<Coords> coords = new ArrayList<Coords>();
		int[] actualCoords = getCoords();
		int actualRow = actualCoords[0];
		int actualColumn = actualCoords[1];
		
		for(int i = 0; i < 4; i++) {
			int row = i == 0 
					? actualRow - 1
					: i == 1
					? actualRow + 1
					: actualRow;
			int column = i == 2 
					? actualColumn - 1
					: i == 3
					? actualColumn + 1
					: actualColumn;
			
			
			while(true) {
				if(row < 0 || row >= Board.LENGTH_BOARD || column < 0 || column >= Board.LENGTH_BOARD) break;
				
				Piece isPiece = board.getCellPiece(row, column);
				
				if(isPiece == null) {
					coords.add(new Coords(row, column));
					if (i == 0)
						row--;
					else if (i == 1)
						row++;
					else if (i == 2)
						column--;
					else column++;
					
					
					continue;
				}
				
				if(!isPiece.getTeamColor().equals(getTeamColor())) 
					coords.add(new Coords(row, column));
				
				break;
			}
		}
		
		
		
		return coords;
	}
	
	public void afterPieceMoved(MouseEventPressed eventInstance) {
		if(isFirstMove) isFirstMove = false;
	}
	
	public boolean isFirstMove() {
		return this.isFirstMove;
	}
	
	private Board board;
	private boolean isFirstMove = true;
}
