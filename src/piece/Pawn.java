package piece;

import java.util.*;

import board.Board;
import board.Cell;
import board.MouseEventPressed;

public class Pawn extends Piece {

	public Pawn(Board board, UUID teamColor, boolean isEndTeam, int row, int column) {
		super(PAWN, teamColor, isEndTeam, row, column);
		
		this.board = board;
	}
	
	public ArrayList<Coords> getAllMoves() {
		ArrayList<Coords> coords = new ArrayList<Coords>();
		
		int[] actualCoords = getCoords();
		int actualRow = actualCoords[0];
		int actualColumn = actualCoords[1];
		int quantity = 0;
		
		if(getIsEndTeam()) {
			Piece isPiece = board.getCellPiece(actualRow - 1, actualColumn);
			if(isPiece == null) quantity = - 1;
			findEnemy(coords, actualRow - 1, actualColumn);
			
		} else {
			Piece isPiece = board.getCellPiece(actualRow + 1, actualColumn);
			if(isPiece == null) quantity = 1;
			findEnemy(coords, actualRow + 1, actualColumn);
		}
		
		if(quantity != 0) {
			coords.add(new Coords(actualRow + quantity, actualColumn));
			
			if(isFirstMove) {
				Piece isPiece = board.getCellPiece(actualRow + quantity * 2, actualColumn);
				if(isPiece == null) coords.add(new Coords(actualRow + quantity * 2, actualColumn));
			}
			
		}
		
		return coords;
	}
	
	private void findEnemy(ArrayList<Coords> coords, int newRow, int actualColumn) {
		Piece isLeftEnemyPiece = 
				actualColumn - 1 >= 0 
				? board.getCellPiece(newRow, actualColumn - 1) : null;
				
		Piece isRightEnemyPiece = 
				actualColumn + 1 < Board.LENGTH_BOARD 
				? board.getCellPiece(newRow, actualColumn + 1) : null;
		
		if(isLeftEnemyPiece != null && !isLeftEnemyPiece.getTeamColor().equals(getTeamColor()))
			coords.add(new Coords(newRow, actualColumn - 1));
		
		if(isRightEnemyPiece != null && !isRightEnemyPiece.getTeamColor().equals(getTeamColor()))
			coords.add(new Coords(newRow, actualColumn + 1));
	}
	
	public void afterPieceMoved(MouseEventPressed eventInstance) {
		if(isFirstMove) isFirstMove = false;
		
		int[] actualCoords = getCoords();
		int row = actualCoords[0];
		int column = actualCoords[1];
		
		if(row <= 0 || row >= Board.LENGTH_BOARD - 1)
			eventInstance.setActiveSelectNewPiece(getTeamColor(), row, column);
	}
	
	private boolean isFirstMove = true;
	private Board board;

}
