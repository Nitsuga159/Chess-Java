package piece;

import java.util.*;

import board.*;

public class Knight extends Piece {

	public Knight(Board board, UUID teamColor, boolean isEndTeam, int row, int column) {
		super(KNIGHT, teamColor, isEndTeam, row, column);
		
		this.board = board;
	}
	
	
	public ArrayList<Coords> getAllMoves() {
		ArrayList<Coords> coords = new ArrayList<Coords>();
		
		int[] actualCoords = getCoords();
		int actualRow = actualCoords[0];
		int actualColumn = actualCoords[1];
		
		for(int newRow = 0; newRow < Board.LENGTH_BOARD; newRow++)
			for(int newColumn = 0; newColumn < Board.LENGTH_BOARD; newColumn++) {
				int restRow = Math.abs(actualRow - newRow);
				int restColumn = Math.abs(actualColumn - newColumn);
				
				if((restRow == 1 && restColumn == 2) || (restRow == 2 && restColumn == 1)) {
					Piece isPiece = board.getCellPiece(newRow, newColumn);
					
					if(isPiece == null || !isPiece.getTeamColor().equals(getTeamColor())) 
						coords.add(new Coords(newRow, newColumn));
				}
			
			}
		
		return coords;
	}

	private Board board;
}
