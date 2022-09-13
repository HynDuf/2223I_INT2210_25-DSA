import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import edu.princeton.cs.algs4.In;

public class ClosestNumbers {
    /*
     * Complete the 'closestNumbers' function below.
     *
     * The function is expected to return an INTEGER_ARRAY. The function accepts INTEGER_ARRAY arr
     * as parameter.
     */

    public static List<Integer> closestNumbers(List<Integer> arr) {
        // Write your code here
        int n = arr.size();
        Collections.sort(arr);
        int minDiff = arr.get(n - 1) - arr.get(0);
        for (int i = 1; i < n; ++i) {
            minDiff = Math.min(minDiff, arr.get(i) - arr.get(i - 1));
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < n; ++i) {
            if (arr.get(i) - arr.get(i - 1) == minDiff) {
                res.add(arr.get(i - 1));
                res.add(arr.get(i));
            }
        }

        return res;
    }
}
