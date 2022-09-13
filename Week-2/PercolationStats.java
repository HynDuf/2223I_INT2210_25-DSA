import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] result;
    private int numTrials;


    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats p = new PercolationStats(n, t);
        System.out.println("mean = " + p.mean());
        System.out.println("stddev = " + p.stddev());
        System.out.println(
                "95% confidence interval = [" + p.confidenceLo() + ", " + p.confidenceHi() + "]");
    }

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        result = new double[trials];
        numTrials = trials;
        for (int iTrial = 0; iTrial < trials; ++iTrial) {
            Percolation percolation = new Percolation(n);
            while (true) {
                int row = getRandomNumber(1, n);
                int col = getRandomNumber(1, n);
                // System.out.println(row + ", " + col);
                percolation.open(row, col);
                if (percolation.percolates()) {
                    result[iTrial] = (double) percolation.numberOfOpenSites() / (n * n);
                    // System.out.println("res: " + result[iTrial]);
                    break;
                }
            }
        }
    }

    private int getRandomNumber(int min, int max) {
        return StdRandom.uniformInt(min, max + 1);
    } 

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(result);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(result);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(numTrials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(numTrials));
    }



}

