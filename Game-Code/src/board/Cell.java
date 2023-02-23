package board;

import java.awt.*;
import java.util.*;
import javax.swing.*;

import piece.Coords;
import piece.Piece;

public class Cell {
	
	public Piece getPiece() {
		return this.piece;
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public JLabel getLabel() {
		return this.label;
	}
	
	public void setLabel(JLabel label) {
		this.label = label;
	}
	
	public JPanel getSheet() {
		return this.sheet;
	}
	
	public void setSheet(JPanel sheet) {
		this.sheet = sheet;
	}
	
	public Color getDefaultColor() {
		return this.defaultColor;
	}
	
	public void setDefaultColor(Color defaultColor) {
		this.defaultColor = defaultColor;
	}
	
	public static final void paintCellsValids(ArrayList<Coords> coords, Cell[][] cells, boolean unpaint) {

		for(Coords coord : coords) {
			int[] currentCoord = coord.getCoords();
			int rowCoord = currentCoord[0];
			int columnCoord = currentCoord[1];
			
			Cell tempCell = cells[rowCoord][columnCoord];
			
			if(unpaint) 
				tempCell.getSheet().setBackground(tempCell.getDefaultColor());
			else
			tempCell.getSheet().setBackground(
					tempCell.getDefaultColor() == WHITE ? WHITE_SELECTED : BROWN_SELECTED
			);
		}
	}
	
	public final static Color WHITE = Color.white;
	public final static Color BROWN = new Color(132, 99, 51);
	public final static Color WHITE_SELECTED = new Color(150, 150, 150);
	public final static Color BROWN_SELECTED = new Color(100, 100, 100);
	private Piece piece;
	private JLabel label;
	private JPanel sheet;
	private Color defaultColor;
}
