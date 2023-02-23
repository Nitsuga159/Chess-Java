package piece;

public class Coords {
	public Coords(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public int[] getCoords() {
		return new int[]{this.row, this.column};
	}
	
	private int row;
	private int column;
}
