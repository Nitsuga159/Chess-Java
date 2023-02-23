package piece;

import java.awt.*;
import java.util.*;

public class Piece extends ActionPiece {
	
	public Piece(int type, UUID teamColor, boolean isEndTeam, int row, int column) {
		String currentColor = teamColor == BLACK ? "black" : "white";
		
		this.imageURL = "/" + currentColor + "-" + type + ".png";
		this.type = type;
		this.teamColor = teamColor;
		this.coords = new Coords(row, column);
		this.isEndTeam = isEndTeam;
	}
	
	
	public String getImageURL() {
		return imageURL;
	}

	public int getType() {
		return type;
	}

	public UUID getTeamColor() {
		return teamColor;
	}
	
	public int[] getCoords() {
		return coords.getCoords();
	}
	
	public void setCoords(int row, int column) {
		coords = new Coords(row, column);
	}
	
	public boolean getIsEndTeam() {
		return this.isEndTeam;
	}

	private String imageURL;
	private int type;
	private UUID teamColor;
	private Coords coords;
	
	public final static int ROOK = 0;
	public final static int KNIGHT = 1;
	public final static int BISHOP = 2;
	public final static int QUEEN = 3;
	public final static int KING = 4;
	public final static int PAWN = 5;
	
	public final static UUID BLACK = UUID.randomUUID();
	public final static UUID WHITE = UUID.randomUUID();
	
	private boolean isEndTeam;
}