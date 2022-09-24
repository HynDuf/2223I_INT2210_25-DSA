import java.awt.Color;
import edu.princeton.cs.algs4.Picture;

public class SeamCarver {

    private Picture picture;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException();
        }
        this.picture = new Picture(picture);
    }

    // current picture
    public Picture picture() {
        return new Picture(picture);
    }

    // width of current picture
    public int width() {
        return picture.width();
    }

    // height of current picture
    public int height() {
        return picture.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || y < 0 || x >= width() || y >= height()) {
            throw new IllegalArgumentException();
        }
        if (x == 0 || y == 0 || x == width() - 1 || y == height() - 1) {
            return 1000;
        }
        Color left = picture.get(x - 1, y);
        Color right = picture.get(x + 1, y);
        int rx = left.getRed() - right.getRed();
        int gx = left.getGreen() - right.getGreen();
        int bx = left.getBlue() - right.getBlue();
        int delta2x = rx * rx + gx * gx + bx * bx;

        Color up = picture.get(x, y - 1);
        Color down = picture.get(x, y + 1);
        int ry = up.getRed() - down.getRed();
        int gy = up.getGreen() - down.getGreen();
        int by = up.getBlue() - down.getBlue();
        int delta2y = ry * ry + gy * gy + by * by;

        return Math.sqrt((double) delta2x + delta2y);

    }

    private void rotate() {
        Picture a = new Picture(height(), width());
        for (int i = 0; i < width(); ++i) {
            for (int j = 0; j < height(); ++j) {
                a.set(j, i, picture.get(i, j));
            }
        }
        picture = a;
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        double[][] dp = new double[width()][height()];
        for (int j = 0; j < height(); ++j) {
            dp[0][j] = 1000.0;
        }
        for (int i = 1; i < width(); ++i) {
            for (int j = 0; j < height(); ++j) {
                double cost = energy(i, j);
                dp[i][j] = dp[i - 1][j] + cost;
                if (j > 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + cost);
                }
                if (j < height() - 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j + 1] + cost);
                }
            }
        }
        int[] answer = new int[width()];
        double minCost = dp[width() - 1][0];
        int posy = 0;
        for (int j = 1; j < height(); ++j) {
            if (minCost > dp[width() - 1][j]) {
                minCost = dp[width() - 1][j];
                posy = j;
            }
        }
        for (int i = width() - 1; i >= 0; --i) {
            answer[i] = posy;
            if (i == 0) {
                break;
            }
            double cost = energy(i, posy);
            if (posy > 0 && dp[i - 1][posy - 1] + cost == dp[i][posy]) {
                posy--;
            } else if (posy < height() - 1 && dp[i - 1][posy + 1] + cost == dp[i][posy]) {
                posy++;
            }
        }
        return answer;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        rotate();
        int[] ret = findHorizontalSeam();
        rotate();
        return ret;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null || seam.length != width()) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < width(); ++i) {
            if (seam[i] < 0 || seam[i] >= height()
                    || (i > 0 && Math.abs(seam[i] - seam[i - 1]) > 1)) {
                throw new IllegalArgumentException();
            }
        }
        Picture a = new Picture(width(), height() - 1);
        for (int i = 0; i < width(); ++i) {
            for (int j = 0; j < seam[i]; ++j) {
                a.set(i, j, picture.get(i, j));
            }
            for (int j = seam[i] + 1; j < height(); ++j) {
                a.set(i, j - 1, picture.get(i, j));
            }
        }
        picture = a;
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        rotate();
        removeHorizontalSeam(seam);
        rotate();
    }

    // unit testing (optional)
    public static void main(String[] args) {

    }

}

