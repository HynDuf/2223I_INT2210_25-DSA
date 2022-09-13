import java.util.Arrays;
import edu.princeton.cs.algs4.*;

public class ThreeSumSuperFast {
    public static int countThreeSum(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        int result = 0;
        for (int i = 0; i < n; ++i) {
            int l = 0, r = 0;
            for (int j = i - 1; j > 0; --j) {
                while (l < i && a[i] + a[j] + a[l] < 0) {
                    l++;
                }
                while (r < i && a[i] + a[j] + a[r] <= 0) {
                    r++;
                }
                result += Math.max(0, Math.min(r, j) - l);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] a = in.readAllInts();

        Stopwatch timer = new Stopwatch();
        int count = countThreeSum(a);
        StdOut.println("elapsed time = " + timer.elapsedTime());
        StdOut.println(count);
    }

}
