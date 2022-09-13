import java.util.List;

public class NewYearChaos {
    /*
     * Complete the 'minimumBribes' function below.
     *
     * The function accepts INTEGER_ARRAY q as parameter.
     */

    public static void minimumBribes(List<Integer> q) {
        // Write your code here
        int n = q.size();
        int result = 0;
        for (int i = 0; i < n; ++i) {
            q.set(i, q.get(i) - 1);
        }
        for (int i = n - 1; i >= 0; --i) {
            boolean found = false;
            for (int j = i; j >= 0 && j >= i - 2; --j) {
                if (q.get(j) == i) {
                    found = true;
                    if (j == i - 1) {
                        int x = q.get(i);
                        q.set(j, x);
                        q.set(i, i);
                        result++;
                    } else if (j == i - 2) {
                        int x = q.get(i);
                        int y = q.get(i - 1);
                        q.set(i - 2, y);
                        q.set(i - 1, x);
                        q.set(i, i);
                        result += 2;
                    }
                    break;
                }
            }
            if (found == false) {
                System.out.println("Too chaotic");
                return;
            }
        }
        System.out.println(result);

    }
}
