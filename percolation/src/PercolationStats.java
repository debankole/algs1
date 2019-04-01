import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	public PercolationStats(int n, int trials) {

		if (n < 1 || trials < 1) {
			throw new java.lang.IllegalArgumentException();
		}
		
		results = new double[trials];

		for (int i = 0; i < trials; i++) {

			Percolation p = new Percolation(n);

			while (!p.percolates()) {

				int r = Math.round(StdRandom.uniform(1, n+1));
				int c = Math.round(StdRandom.uniform(1, n+1));

				// StdOut.println("open "+ r + " " + c);

				p.open(r, c);

				// printState(p, N);

				// StdOut.println("count "+ p.numberOfOpenSites());

				// StdOut.println("percolates "+ p.percolates());

				// Thread.sleep(100);
			}
			results[i] = (double) p.numberOfOpenSites() / (n * n);
		}
	}

	private double[] results;
	private double _mean;
	private double _stddev;
	
	public double mean() {
		_mean = StdStats.mean(results);
		return _mean;
		// sample mean of percolation threshold
	}

	public double stddev() {
		_stddev = StdStats.stddev(results);
		return _stddev;
		// sample standard deviation of percolation threshold
	}

	public double confidenceLo() {
		return _mean - 1.96 * _stddev / Math.sqrt(results.length);
		// low endpoint of 95% confidence interval
	}

	public double confidenceHi() {
		return _mean + 1.96 * _stddev / Math.sqrt(results.length);
		// high endpoint of 95% confidence interval
	}

	// private static void printState(Percolation p, int n) {
	//
	// for (int i = 0; i < n; i++) {
	// for (int j = 0; j < n; j++) {
	// if (p.isOpen(i, j)) {
	// StdOut.print("* ");
	// } else {
	// StdOut.print("- ");
	// }
	// }
	// StdOut.println("");
	// }
	//
	// }

	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);

		if (N < 1 || T < 1) {
			throw new java.lang.IllegalArgumentException();
		}

		PercolationStats p = new PercolationStats(N, T);

		StdOut.println("mean " + p.mean());
		StdOut.println("stddev " + p.stddev());
		StdOut.println("95% confidence interval " + p.confidenceLo() + ", " + p.confidenceHi());
	}
}