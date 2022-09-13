import java.util.Collections;
import java.util.List;

public class Pairs {
    /*
     * Complete the 'pairs' function below.
     *
     * The function is expected to return an INTEGER. The function accepts following parameters: 1.
     * INTEGER k 2. INTEGER_ARRAY arr
     */

    public static int pairs(int k, List<Integer> arr) {
        // Write your code here
        Collections.sort(arr);
        int result = 0;
        int l = 0, r = 0;
        for (int i = 0; i < arr.size(); ++i) {
            while (l < arr.size() && arr.get(l) < arr.get(i) - k) {
                l++;
            }
            while (r < arr.size() && arr.get(r) <= arr.get(i) - k) {
                r++;
            }
            result += r - l;
        }
        return result;
    }
}
