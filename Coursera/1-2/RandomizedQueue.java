import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;

    private int numElements = 0;

    // construct an empty deque
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
    }

    // is the deque empty?
    public boolean isEmpty() {
        return numElements == 0;
    }

    // return the number of items on the deque
    public int size() {
        return numElements;
    }

    private void refactor(int size) {
        Item[] nItems = (Item[]) new Object[size];
        for (int i = 0; i < numElements; ++i) {
            nItems[i] = items[i];
        }
        items = nItems;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        // System.out.println(numElements);
        items[numElements++] = item;
        if (numElements == items.length) {
            refactor(numElements * 2);
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int randPos = StdRandom.uniformInt(numElements);
        Item item = items[randPos];
        items[randPos] = items[--numElements];
        items[numElements] = null;
        if (numElements > 0 && numElements == items.length / 4) {
            refactor(numElements * 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return items[StdRandom.uniformInt(numElements)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RQIterator();
    }

    private class RQIterator implements Iterator<Item> {
        private int[] perm;
        private int current = 0;

        public RQIterator() {
            perm = new int[numElements];
            for (int i = 0; i < numElements; ++i) {
                perm[i] = i;
            }
            StdRandom.shuffle(perm);
        }

        public boolean hasNext() {
            return current < numElements;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                return items[perm[current++]];
            }
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> r = new RandomizedQueue<Integer>();

        r.enqueue(1);
        r.enqueue(317);

        System.out.println("Size: " + r.size());
        System.out.println("");

        System.out.println("Sample 1: " + r.sample());
        System.out.println("Sample 2: " + r.sample());
        System.out.println("");

        Iterator<Integer> i = r.iterator();
        while (i.hasNext())
            System.out.println("Iterator: " + i.next());
        System.out.println("");

        System.out.println("Remove all elements...");
        while (!r.isEmpty())
            System.out.println("Dequeue: " + r.dequeue());
        System.out.println("");

        System.out.println("Size: " + r.size());
    }

}

