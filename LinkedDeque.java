import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Deque implementation using a linked list.
public class LinkedDeque<Item> implements Iterable<Item> {
    private Node first; // head
    private Node last; // tail
    private int N; // size

    // Helper doubly-linked list class.
    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        public Node(Item item) {
            this.item = item;
        }

    }
    // Construct an empty deque.
    public LinkedDeque() {    
        // Initialize instance variable
        first = null;
        last = null;
        N = 0;
    }

    // Is the dequeue empty?
    public boolean isEmpty() {
        // Return whether it is empty or not
        return N == 0;
    }

    // The number of items on the deque.
    public int size() {
        // Return the size
        return N;
    }

    // Add item to the front of the deque.
    public void addFirst(Item item) {
        // Exception
        if (item == null) {
            throw new NullPointerException("Error");
        }

        // Add item at the head of deque
        if (isEmpty() == true) {
            first = new Node(item);
            last = first;
        }

        else {
            Node node = new Node(item);
            node.next = first; //resetting the pointer
            first.prev = node;
            first = node;
        }

        // Add one
        N++;
    }

    // Add item to the end of the deque.
    public void addLast(Item item) {
        //Exception 

        if (item == null) {
            throw new NullPointerException("Error");
        }

        // Add item at the tail of deque
        if (isEmpty()) {
            last = new Node(item);
            first = new Node(item);
        }

        else {
            Node node = new Node(item);
            node.prev = last;
            last.next = node;
            last = node;
        }
        //Increase by one
        N++;
    }

    // Remove and return item from the front of the deque.
    public Item removeFirst() {
        Item item = first.item;

        // Exception
        if (isEmpty()) {
            throw new NoSuchElementException("Error");
        }

        // Remove item from head
        if (N == 1) { //reset
            first = null;
            last = null;
        }

        else {
            first = first.next;
        }

        // Decrease by one
        N--;
        return item;
    }

    // Remove and return item from the end of the deque.
    public Item removeLast() {
        Item item = last.item;

        // Exception
        if (isEmpty()) {
            throw new NoSuchElementException("Error");
        }

        // Remove item from tail
        if (N == 1) { //reset
            last = null;
            first = null;
        }

        else {
            last = last.prev;
        }

        //Decrease by one

        N--;
        return item;
    }

    // An iterator over items in the queue in order from front to end.
    public Iterator<Item> iterator() {
        // Return object of type DequeIterator
        return new DequeIterator();
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class DequeIterator implements Iterator<Item> {
        // Pointer to current Node
        private Node current;

        DequeIterator() {
            // Initialize instance variable
            this.current = first;
        }

        public boolean hasNext()  { 
            // Return wheather iterable or not
            return current != null;
        }

        public void remove() { 
            // Exception
            throw new UnsupportedOperationException(); 
        }

        public Item next() {
            // Return current item and advance current to the next
            // node
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // A string representation of the deque.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString().substring(0, s.length() - 1);
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        LinkedDeque<Character> deque = new LinkedDeque<Character>();
        String quote = "There is grandeur in this view of life, with its " 
            + "several powers, having been originally breathed into a few " 
            + "forms or into one; and that, whilst this planet has gone " 
            + "cycling on according to the fixed law of gravity, from so " 
            + "simple a beginning endless forms most beautiful and most " 
            + "wonderful have been, and are being, evolved. ~ " 
            + "Charles Darwin, The Origin of Species";
        int r = StdRandom.uniform(0, quote.length());
        for (int i = quote.substring(0, r).length() - 1; i >= 0; i--) {
            deque.addFirst(quote.charAt(i));
        }
        for (int i = 0; i < quote.substring(r).length(); i++) {
            deque.addLast(quote.charAt(r + i));
        }
        StdOut.println(deque.isEmpty());
        StdOut.printf("(%d characters) ", deque.size());
        for (char c : deque) {
            StdOut.print(c);
        }
        StdOut.println();
        double s = StdRandom.uniform();
        for (int i = 0; i < quote.length(); i++) {
            if (StdRandom.bernoulli(s)) {
                deque.removeFirst();
            }
            else {
                deque.removeLast();
            }
        }
        StdOut.println(deque.isEmpty());
    }
}
