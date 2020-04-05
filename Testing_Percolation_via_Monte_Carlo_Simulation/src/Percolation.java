import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private boolean[][] arr;
	private final WeightedQuickUnionUF q;
	private int numOfOpen = 0;
	private final int length ;
	public Percolation(int n) {
		arr = new boolean[n][n];
		for (int i=0 ; i<n ; i++) {
			for (int j=0 ; j<n ;j++) {
				arr[i][j] = false;
			}
		}
		q = new WeightedQuickUnionUF(n*n+2);
		length = n;
	}
	private int encode (int x, int y) {
		return (x*length)+y+1;
	}
	private boolean isInGrid(int row,int col) {
		if (row >= 0 && row < length && col >= 0 && col < length )
		return true;
		else return false;
	}
	public boolean isOpen(int row,int col) {
		if (!isInGrid(row,col))
			throw new IllegalArgumentException("element not in grid");
		if (arr[row][col] == true )
			return true;
		else return false;
	}
	public void open(int row,int col) {
		if (!isInGrid(row,col))
			throw new IllegalArgumentException("element not in grid");
		arr[row][col] = true;
		numOfOpen++;
		int var = encode(row,col);
		if (row == 0) {
			q.union(var,0);
		}
		else if(row == length-1) {
			q.union(var,(length*length)+1);
		}
		else {
			if ( isInGrid(row,col-1) && isOpen(row,col-1))
				q.union(var, encode(row,col-1));
			if ( isInGrid(row,col+1) && isOpen(row,col+1))
				q.union(var, encode(row,col+1));
			if ( isInGrid(row-1,col) && isOpen(row-1,col))
				q.union(var, encode(row-1,col));
			if ( isInGrid(row+1,col) && isOpen(row+1,col))
				q.union(var, encode(row+1,col));
		}
	}
	public boolean isFull(int row,int col) {
		if(isOpen(row,col)) {
			if(q.find(encode(row,col)) == q.find(0))
				return true;
		}
		return false;
	}
	public int numberOfOpenSites() {
		return numOfOpen;
	}
	public boolean percolates() {
		if (q.find(0) == q.find(length*length+1)) {
			return true;
		}
		else return false;
	}
    
	public static void main(String[] args) {
	//Empty Method	
	}
}
