import java.util.ArrayList;
import edu.princeton.cs.algs4.*;

public class ThreeSum {
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        while (!StdIn.isEmpty()) {
            int x = StdIn.readInt();
            a.add(x);
        }
        for (int i = 0; i < a.size(); ++i) {
            for (int j = 0; j < i; ++j) {
                for (int k = 0; k < j; ++k) {
                    if (a.get(k) + a.get(j) + a.get(i) == 0) {
                        StdOut.println(a.get(k) + " " + a.get(j) + " " + a.get(i));
                    }
                }
            }
        }
    }
}
