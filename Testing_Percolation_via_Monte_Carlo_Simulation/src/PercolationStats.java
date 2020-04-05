import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;


public class PercolationStats {
	 private final double Xbar ,Sigma,CIHigh,CILow;
	 
    public PercolationStats(int n, int trials) {
    	if (n <= 0 || trials <= 0 )
    		throw new IllegalArgumentException();
    	double[] TestResults = new double[trials];
    	for (int i=0 ; i<trials; i++) {
    		Percolation p = new Percolation(n);
    		while (!p.percolates()) {
        		int row = StdRandom.uniform(n);
        		int col = StdRandom.uniform(n);
        		if(!p.isOpen(row,col)) {
        			p.open(row, col);
        		}
        	}
    		TestResults[i] = (p.numberOfOpenSites()/(double)(n*n));
    	}
    	Xbar = StdStats.mean(TestResults);
    	Sigma = StdStats.stddev(TestResults);
    	double confidenceFraction = (1.96 * stddev()) / Math.sqrt(trials);
        CILow = Xbar - confidenceFraction;
        CIHigh = Sigma + confidenceFraction;
    }
    
    
    public double mean() {
    	return Xbar;
    }

    
    public double stddev() {
    	return Sigma;
    }

    public double confidenceLo() {
    	return CILow;
    }

    
    public double confidenceHi() {
    	return CIHigh;
    }

   public static void main(String[] args) {
	   int n = Integer.parseInt(args[0]);
       int t = Integer.parseInt(args[1]);
       PercolationStats percolationStats = new PercolationStats(n, t);

       StdOut.printf("mean                    = %f\n", percolationStats.mean());
       StdOut.printf("stddev                  = %f\n", percolationStats.stddev());
       StdOut.println("95% confidence interval = " + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi());
   }
}
