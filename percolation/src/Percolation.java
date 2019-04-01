import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int size;
	private boolean[][] openArray;
	private WeightedQuickUnionUF qu;
	private int top;
	private int bottom;
	private int openItemsCount = 0;

	public Percolation(int n) { // create n-by-n grid, with all sites blocked
		if (n <= 0) {
			throw new IllegalArgumentException("N must be at least 1");
		}

		size = n;
		openArray = new boolean[n][n];

		qu = new WeightedQuickUnionUF(n * n + 2);

		top = 0;
		bottom = n * n + 1;
	}

	public void open(int row, int col) {
		if (openArray[row][col])
			return;

		openArray[row][col] = true;

		connect(row, col, row - 1, col);
		connect(row, col, row + 1, col);
		connect(row, col, row, col - 1);
		connect(row, col, row, col + 1);

		openItemsCount++;
	}

	public boolean isOpen(int row, int col) {
		return openArray[row][col];
	}

	public boolean isFull(int row, int col) {
		return qu.connected(top, getIndex(row, col));
	}

	public int numberOfOpenSites() {
		return openItemsCount;
	}

	public boolean percolates() {
		return qu.connected(top, bottom);
	}

	public static void main(String[] args) {
		// test client (optional)
	}

	private void connect(int row, int col, int row2, int col2) {

		try {
			if (isOpen(row2, col2)) {
				qu.union(getIndex(row, col), getIndex(row2, col2));
			}
		} catch (IndexOutOfBoundsException e) {
			// don't connect field with field outside grid
		}
	}

	private int getIndex(int row, int col) {
		return row * size + col + 1;
	}
}