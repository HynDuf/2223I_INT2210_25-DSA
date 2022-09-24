import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private Stack<Board> answer;
    private boolean solvable;

    private class Node implements Comparable<Node> {
        private Board board;
        private Node parent;
        private int numMoves;
        private int manhattan;

        public Node(Board board, Node parent) {
            this.board = board;
            this.parent = parent;
            this.manhattan = board.manhattan();
            if (parent == null) {
                this.numMoves = 0;
            } else {
                this.numMoves = parent.numMoves + 1;
            }
        }

        @Override
        public int compareTo(Node oth) {
            int diff = (this.manhattan + this.numMoves) - (oth.manhattan + oth.numMoves);
            return (diff == 0) ? (this.manhattan - oth.manhattan) : diff;
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }
        solvable = false;
        MinPQ<Node> pq = new MinPQ<>();
        pq.insert(new Node(initial, null));
        pq.insert(new Node(initial.twin(), null));

        while (!pq.isEmpty()) {
            Node cur = pq.delMin();
            if (cur.board.isGoal()) {
                answer = new Stack<>();
                while (cur.parent != null) {
                    answer.push(cur.board);
                    cur = cur.parent;
                }
                answer.push(cur.board);
                solvable = cur.board.equals(initial);
                break;
            }
            for (Board v : cur.board.neighbors()) {
                if (cur.parent == null || !cur.parent.board.equals(v)) {
                    pq.insert(new Node(v, cur));
                }
            }
        }

    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (isSolvable()) {
            return answer.size() - 1;
        }
        return -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) {
            return null;
        }
        return answer;
    }


    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}
