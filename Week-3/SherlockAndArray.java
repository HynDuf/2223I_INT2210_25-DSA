import java.util.List;

public class SherlockAndArray {
    /*
     * Complete the 'balancedSums' function below.
     *
     * The function is expected to return a STRING. The function accepts INTEGER_ARRAY arr as
     * parameter.
     */

    public static String balancedSums(List<Integer> arr) {
        // Write your code here
        int n = arr.size();
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += arr.get(i);
        }
        int pre = 0;
        for (int i = 0; i < n; ++i) {
            sum -= arr.get(i);
            if (pre == sum) {
                return "YES";
            }
            pre += arr.get(i);
        }
        return "NO";
    }
}
