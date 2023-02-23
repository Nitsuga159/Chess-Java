package piece;

import java.util.*;

import javax.swing.*;

import board.*;

public class King extends Piece {

	public King(Board board, UUID teamColor, boolean isEndTeam, int row, int column) {
		super(KING, teamColor, isEndTeam, row, column);
		
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
				
				if((restRow == 1 && restColumn == 0) || (restRow == 0 && restColumn == 1) || (restRow == 1 && restColumn == 1)) {
					Piece isPiece = board.getCellPiece(newRow, newColumn);
					
					if(isPiece == null || !isPiece.getTeamColor().equals(getTeamColor())) 
						coords.add(new Coords(newRow, newColumn));
				}
			
			}
		
		checkSide(coords, true, actualRow, actualColumn);
		checkSide(coords, false, actualRow, actualColumn);
		
		return coords;
	}
	
	public void checkSide(ArrayList<Coords> coords, boolean left, int row, int column) {
		Piece rook = board.getCellPiece(
				row, 
				left ? 0 : Board.LENGTH_BOARD - 1
		);
		if(!isFirstMove || !(rook != null && rook.getType() == Piece.ROOK && ((Rook) rook).isFirstMove())) return;
		
		int subColumn = column;
		
		while(true) {
			if(left) subColumn--;
			else subColumn++;
			
			if(subColumn <= 0 || subColumn >= Board.LENGTH_BOARD - 1) break;
				
				if(board.getCellPiece(row, subColumn) != null) return;
		}
		
		coords.add(
			new Coords(row, left ? column - 2 : column + 2)
		);
		
		rookCellTarget = board.getCell(
				row, 
				left ? 0 : Board.LENGTH_BOARD - 1
		);
		
		rookCoords = new int[]{
				row,
				left ? column - 1 : column + 1
		};
	}

	public void afterPieceMoved(MouseEventPressed eventInstance) {
		if(isFirstMove) isFirstMove = false;
		
		if(rookCellTarget != null) {
			changeRookPosition();
			rookCellTarget = null;
		}
		
	}
	
	public void changeRookPosition() {
		Cell newCellRook = board.getCell(rookCoords[0], rookCoords[1]); 
		
		newCellRook.setPiece(rookCellTarget.getPiece());
		
		JLabel currentLabel = rookCellTarget.getLabel();
		Icon rookIcon = currentLabel.getIcon();
		newCellRook.getLabel().setIcon(rookIcon);
		
		currentLabel.setIcon(null);
		rookCellTarget.setPiece(null);
		
		newCellRook.getPiece().setCoords(rookCoords[0], rookCoords[1]);
		
	}
	
	private int[] rookCoords;
	private Cell rookCellTarget;
	private Board board;
	private boolean isFirstMove = true;
}
