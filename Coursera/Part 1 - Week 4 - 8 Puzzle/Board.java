import java.util.ArrayList;
import java.util.List;

public class Board {

    private int[][] tiles;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = clone(tiles);
    }

    private int[][] clone(int[][] a) {
        int n = a.length;
        int[][] b = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                b[i][j] = a[i][j];
            }
        }
        return b;
    }

    // string representation of this board
    public String toString() {
        StringBuilder res = new StringBuilder();
        int n = dimension();
        res.append(n).append('\n');
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                res.append(tiles[i][j]);
                if (j < n - 1) {
                    res.append(' ');
                } else {
                    res.append('\n');
                }
            }
        }
        return res.toString();
    }

    private int id(int x, int y) {
        return x * dimension() + y + 1;
    }

    private int getX(int v) {
        return (v - 1) / dimension();
    }

    private int getY(int v) {
        return (v - 1) % dimension();
    }

    // board dimension n
    public int dimension() {
        return tiles.length;
    }

    // number of tiles out of place
    public int hamming() {
        int numDiff = 0;
        for (int i = 0; i < dimension(); ++i) {
            for (int j = 0; j < dimension(); ++j) {
                if (tiles[i][j] != 0 && id(i, j) != tiles[i][j]) {
                    numDiff++;
                }
            }
        }
        return numDiff;
    }

    private int manDist(int x, int y) {
        int tx = getX(tiles[x][y]);
        int ty = getY(tiles[x][y]);
        return Math.abs(x - tx) + Math.abs(y - ty);
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int res = 0;
        for (int i = 0; i < dimension(); ++i) {
            for (int j = 0; j < dimension(); ++j) {
                if (tiles[i][j] != 0) {
                    res += manDist(i, j);
                }
            }
        }
        return res;
    }



    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null || y.getClass() != this.getClass()) {
            return false;
        }
        Board oth = (Board) y;
        if (oth.dimension() != dimension()) {
            return false;
        }
        for (int i = 0; i < dimension(); ++i) {
            for (int j = 0; j < dimension(); ++j) {
                if (oth.tiles[i][j] != this.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] swap(int x, int y, int x1, int y1) {
        int[][] a = clone(tiles);
        int tem = a[x][y];
        a[x][y] = a[x1][y1];
        a[x1][y1] = tem;
        return a;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> nei = new ArrayList<>();
        int bx = 0;
        int by = 0;
        for (int i = 0; i < dimension(); ++i) {
            for (int j = 0; j < dimension(); ++j) {
                if (tiles[i][j] == 0) {
                    bx = i;
                    by = j;
                    break;
                }
            }
        }
        if (bx > 0) {
            nei.add(new Board(swap(bx, by, bx - 1, by)));
        }
        if (bx < dimension() - 1) {
            nei.add(new Board(swap(bx, by, bx + 1, by)));
        }
        if (by > 0) {
            nei.add(new Board(swap(bx, by, bx, by - 1)));
        }
        if (by < dimension() - 1) {
            nei.add(new Board(swap(bx, by, bx, by + 1)));
        }
        return nei;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        if (tiles[0][0] == 0 || tiles[0][1] == 0) {
            return new Board(swap(1, 0, 1, 1));
        }
        return new Board(swap(0, 0, 0, 1));
    }

    // unit testing (not graded)
    public static void main(String[] args) {

    }

}
