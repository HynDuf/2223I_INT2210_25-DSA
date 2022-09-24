import java.util.Arrays;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {
    private Digraph g;
    private int lengthAns;
    private int ancestorAns;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        g = new Digraph(G);
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= g.V()) {
            throw new IllegalArgumentException();
        }
    }

    private void validateVertices(Iterable<Integer> sV) {
        if (sV == null) {
            throw new IllegalArgumentException();
        }
        for (Integer v : sV) {
            if (v == null) {
                throw new IllegalArgumentException();
            }
            validateVertex(v);
        }
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        SET<Integer> sV = new SET<>();
        sV.add(v);
        SET<Integer> sW = new SET<>();
        sW.add(w);
        return length(sV, sW);
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such
    // path
    public int ancestor(int v, int w) {
        SET<Integer> sV = new SET<>();
        sV.add(v);
        SET<Integer> sW = new SET<>();
        sW.add(w);
        return ancestor(sV, sW);
    }

    private int[] bfs(Iterable<Integer> s) {
        int[] dist = new int[g.V()];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new Queue<>();
        for (Integer v : s) {
            q.enqueue(v);
            dist[v] = 0;
        }
        while (!q.isEmpty()) {
            int u = q.dequeue();
            for (int v : g.adj(u)) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    q.enqueue(v);
                }
            }
        }
        return dist;
    }


    private void solve(Iterable<Integer> v, Iterable<Integer> w) {
        int[] distV = bfs(v);
        int[] distW = bfs(w);
        lengthAns = g.V();
        ancestorAns = -1;
        for (int i = 0; i < g.V(); ++i) {
            if (distV[i] != -1 && distW[i] != -1) {
                if (lengthAns > distV[i] + distW[i]) {
                    lengthAns = distV[i] + distW[i];
                    ancestorAns = i;
                }
            }
        }
        if (ancestorAns == -1) {
            lengthAns = -1;
        }
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such
    // path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        validateVertices(v);
        validateVertices(w);
        solve(v, w);
        return lengthAns;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        validateVertices(v);
        validateVertices(w);
        solve(v, w);
        return ancestorAns;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}

