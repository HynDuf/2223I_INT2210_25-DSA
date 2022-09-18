import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int numElements = 0;

    private class Node {
        Item item;
        Node next;
        Node last;

        private Node(Item item) {
            this.item = item;
            next = null;
            last = null;
        }
    }

    private Node head = null;
    private Node tail = null;

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return numElements == 0;
    }

    // return the number of items on the deque
    public int size() {
        return numElements;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        numElements++;
        Node nItem = new Node(item);
        if (head == null) {
            head = nItem;
            tail = nItem;
        } else {
            head.last = nItem;
            nItem.next = head;
            head = nItem;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        numElements++;
        Node nItem = new Node(item);
        if (head == null) {
            head = nItem;
            tail = nItem;
        } else {
            nItem.last = tail;
            tail.next = nItem;
            tail = nItem;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item first = head.item;
        numElements--;
        if (numElements > 0) {
            head = head.next;
            head.last.next = null;
            head.last = null;
        } else {
            head = null;
            tail = null;
        }
        return first;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item last = tail.item;
        numElements--;
        if (numElements > 0) {
            tail = tail.last;
            tail.next.last = null;
            tail.next = null;
        } else {
            head = null;
            tail = null;
        }
        return last;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = null;

        public boolean hasNext() {
            if (current == null) {
                return !isEmpty();
            } else {
                return current.next != null;
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current == null) {
                if (isEmpty()) {
                    throw new NoSuchElementException();
                } else {
                    current = head;
                }
            } else {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    current = current.next;
                }
            }
            return current.item;
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> d = new Deque<>();
        d.addFirst("D");
        d.addFirst("A");
        d.addLast("X");
        d.addFirst("K");
        d.addLast("T");
        System.out.println("Size: " + d.size());
        Iterator<String> i = d.iterator();
        while (i.hasNext())
            System.out.println("Iterator: " + i.next());
        for (String e : d) {
            System.out.println(e);
        }
        d.removeLast();
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        System.out.println("Size: " + d.size());
        for (String e : d) {
            System.out.println(e);
        }
    }

}

