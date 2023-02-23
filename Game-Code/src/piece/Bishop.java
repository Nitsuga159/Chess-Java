package piece;

import java.util.ArrayList;
import java.util.*;

import board.Board;
import board.Cell;

public class Bishop extends Piece {

	public Bishop(Board board, UUID teamColor, boolean isEndTeam, int row, int column) {
		super(BISHOP, teamColor, isEndTeam, row, column);
		
		this.board = board;
	}
	
	public ArrayList<Coords> getAllMoves() {
		ArrayList<Coords> coords = new ArrayList<Coords>();
		int[] actualCoords = getCoords();
		int actualRow = actualCoords[0];
		int actualColumn = actualCoords[1];
		
		for(int i = 0; i < 4; i++) {
			int row = i == 0 || i == 3
					? actualRow - 1
					: actualRow + 1;
			
			int column = i == 0 || i == 2
					? actualColumn - 1
					: actualColumn + 1;
			
			
			while(true) {
				if(row < 0 || row >= Board.LENGTH_BOARD || column < 0 || column >= Board.LENGTH_BOARD) break;
				
				Piece isPiece = board.getCellPiece(row, column);
				
				if(isPiece == null) {
					coords.add(new Coords(row, column));
					if (i == 0) {
						column--;
						row--;
					}
					else if (i == 1) {
						column++;
						row++;
					}
					else if (i == 2) {
						row++;
						column--;
					}
					else {
						row--;
						column++;
					}
					
					
					continue;
				}
				
				if(!isPiece.getTeamColor().equals(getTeamColor())) 
					coords.add(new Coords(row, column));
				
				break;
			}
		}
		
		
		
		return coords;
	}

	private Board board;
}
