import java.util.Arrays;
import edu.princeton.cs.algs4.*;

public class BinarySearch {
    public static int binarySearch(int[] a, int number) {
        int left = 0, right = a.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] > number) {
                right = mid - 1;
            } else if (a[mid] < number) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = StdRandom.uniformInt(30);
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = StdRandom.uniformInt(1, 40);
        }
        Arrays.sort(a);
        System.out.print("Array a = [");
        for (int i = 0; i < n; ++i) {
            System.out.print(a[i]);
            if (i == n - 1) {
                System.out.println("]");
            } else {
                System.out.print(", ");
            }
        }
        int number = StdRandom.uniformInt(10);
        System.out.println("Position of " + number + " in a is: " + binarySearch(a, number));
    }
}
