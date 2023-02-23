package piece;

import java.util.*;

import board.Cell;
import board.MouseEventPressed;

public abstract class ActionPiece {
	public boolean isValidMove(ArrayList<Coords> coords, int row, int column) {
		for(Coords coord : coords) {
			int[] currentCoord = coord.getCoords();
			
			if(row == currentCoord[0] && column == currentCoord[1]) return true;
		}
		
		return false;
	}
	
	public void afterPieceMoved(MouseEventPressed eventInstance) {
	}
	
	public ArrayList<Coords> getAllMoves() {
		return null;
	}
}
