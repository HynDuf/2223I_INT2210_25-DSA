import java.util.Arrays;
import java.util.TreeMap;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Topological;

public class WordNet {
    private TreeMap<String, SET<Integer>> idSets;
    private TreeMap<Integer, String> synsetsId;
    private Digraph g;
    private boolean[] isRoot;
    private SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) {
            throw new IllegalArgumentException();
        }
        loadSynsets(synsets);
        loadHypernyms(hypernyms);
    }

    private void loadSynsets(String file) {
        idSets = new TreeMap<>();
        synsetsId = new TreeMap<>();
        In in = new In(file);
        while (in.hasNextLine()) {
            String[] parts = in.readLine().split(",");
            int id = Integer.parseInt(parts[0]);
            synsetsId.put(id, parts[1]);
            String[] nouns = parts[1].split(" ");
            for (String noun : nouns) {
                if (!idSets.containsKey(noun)) {
                    idSets.put(noun, new SET<>());
                }
                idSets.get(noun).add(id);
            }
        }
    }

    private int numSynsets() {
        return synsetsId.size();
    }

    private void loadHypernyms(String file) {
        g = new Digraph(numSynsets());
        isRoot = new boolean[numSynsets()];
        Arrays.fill(isRoot, Boolean.TRUE);
        In in = new In(file);
        while (in.hasNextLine()) {
            String[] parts = in.readLine().split(",");
            int idU = Integer.parseInt(parts[0]);
            for (int i = 1; i < parts.length; ++i) {
                int idV = Integer.parseInt(parts[i]);
                g.addEdge(idU, idV);
                isRoot[idU] = false;
            }
        }
        if (!isRootedDAG()) {
            throw new IllegalArgumentException();
        }
        sap = new SAP(g);
    }

    private boolean isRootedDAG() {
        int numRoots = 0;
        for (int i = 0; i < numSynsets(); ++i) {
            if (isRoot[i]) {
                numRoots++;
            }
        }
        if (numRoots != 1) {
            return false;
        }
        Topological topo = new Topological(g);
        return topo.hasOrder();
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return idSets.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) {
            throw new IllegalArgumentException();
        }
        return idSets.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null || !isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        SET<Integer> sA = idSets.get(nounA);
        SET<Integer> sB = idSets.get(nounB);
        return sap.length(sA, sB);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null || !isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        SET<Integer> sA = idSets.get(nounA);
        SET<Integer> sB = idSets.get(nounB);
        return synsetsId.get(sap.ancestor(sA, sB));

    }

    // do unit testing of this class
    public static void main(String[] args) {

    }
}
