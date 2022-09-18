import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> d = new RandomizedQueue<> ();        
        while (!StdIn.isEmpty()) {
            String cur = StdIn.readString();
            d.enqueue(cur);
        }
        for (String e : d) {
            if (k == 0) {
                break;
            }
            StdOut.println(e);
            k--;
        }
    }
}
