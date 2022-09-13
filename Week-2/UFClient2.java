import edu.princeton.cs.algs4.*;

public class UFClient2 {
    public static void main(String[] args) {
        int N = StdIn.readInt();
        UF uf = new UF(N);
        int cnt = 0, num = 0;
        while (!StdIn.isEmpty()) {
            num++;
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.find(p) != uf.find(q)) {
                cnt++;
                uf.union(p, q);
                if (cnt == N - 1) {
                    System.out.println(num);
                    return;
                }
            }
        }
        System.out.println("FAILED");
    }
}
