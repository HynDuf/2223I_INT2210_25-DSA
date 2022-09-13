import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufFull;
    private int numN;
    private int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    private int numOpenSites;
    private boolean isOpen[][];

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        uf = new WeightedQuickUnionUF(n * n + 2);
        ufFull = new WeightedQuickUnionUF(n * n + 2);
        numN = n;
        isOpen = new boolean[n][n];
        numOpenSites = 0;
    }

    private int getId(int row, int col) {
        return row * numN + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        row--;
        col--;
        if (row < 0 || col < 0 || row >= numN || col >= numN) {
            throw new IllegalArgumentException();
        }
        if (!isOpen(row + 1, col + 1)) {
            numOpenSites++;
            isOpen[row][col] = true;
            for (int t = 0; t < 4; ++t) {
                int x = row + dx[t];
                int y = col + dy[t];
                if (0 <= x && x < numN && 0 <= y && y < numN && isOpen(x + 1, y + 1)) {
                    uf.union(getId(row, col), getId(x, y));
                    ufFull.union(getId(row, col), getId(x, y));
                }
                if (x == -1) {
                    uf.union(getId(row, col), numN * numN);
                    ufFull.union(getId(row, col), numN * numN);
                }
                if (x == numN) {
                    uf.union(getId(row, col), numN * numN + 1);
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        row--;
        col--;
        if (row < 0 || col < 0 || row >= numN || col >= numN) {
            throw new IllegalArgumentException();
        }
        return isOpen[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        row--;
        col--;
        if (row < 0 || col < 0 || row >= numN || col >= numN) {
            throw new IllegalArgumentException();
        }
        return isOpen(row + 1, col + 1) && ufFull.find(getId(row, col)) == ufFull.find(numN * numN);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(numN * numN) == uf.find(numN * numN + 1);
    }

}
