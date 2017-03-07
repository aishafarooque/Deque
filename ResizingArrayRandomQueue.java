import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Random queue implementation using a resizing array.
public class ResizingArrayRandomQueue<Item> implements Iterable<Item> {
    private int N; //size of current queue
    private Item[] queue;

    // Construct an empty queue.
    public ResizingArrayRandomQueue() {
        // Initialize instance variable
        queue = (Item[]) new Object[101];
        N = 0;
    }

    // Is the queue empty?
    public boolean isEmpty() {
        // Return whether empty or not
        return N == 0;
    }

    // The number of items on the queue.
    public int size() {
        // Return size
        return N;
    }

    // Add item to the queue.
    public void enqueue(Item item) {
        
        /* If q is full, resize to twice
         * Insert given item at N index
         * Add one to N
         */
        
        //throw exceptions if it's null
        if (item == null) {
            throw new NullPointerException();
        }
        
        int lnght = queue.length;
        if (N == queue.length) {
            resize(2 * lnght);
        }
        queue[N] = item;
        N++;
    }

    // Remove and return a random item from the queue.
    public Item dequeue() {
        int lol = StdRandom.uniform(N);
        int lng = queue.length;
        Item item = queue[lol];
        
        if (N == 0) {
            throw new NoSuchElementException("Error");
        }
        
        if (lol != N-1) {
            queue[lol] = queue[N-1];
        }
        
        queue[N-1] = null;
        N--;
        
        if (N > 0 && N == lng / 4) {
            resize(lng / 2);
        }
        
        return item;
    }

    // Return a random item from the queue, but do not remove it.
    public Item sample() {
        if (N == 0) {
            throw new NoSuchElementException("Error");
        }
        int lol = StdRandom.uniform(N);
        return queue[lol];
    }

    // An independent iterator over items in the queue in random order.
    public Iterator<Item> iterator() {
        // Return an object of type RandomQueueIterator
        return new RandomQueueIterator();
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class RandomQueueIterator implements Iterator<Item> {
        private Item[] randomQueue;
        private int cheque;

        RandomQueueIterator() {
            //Store the array
            randomQueue = (Item[]) new Object[N];
            for (int i = 0; i < N; ++i) {
                randomQueue[i] = queue[i];
            }
            // Reset
            cheque = 0;
        }

        public boolean hasNext()  { 
            // Return whether there are more items to iterate
            return cheque < randomQueue.length;
        }

        public void remove() { throw new UnsupportedOperationException(); }

        public Item next() {
            // Return items at current and 
            // increase current by one
            Item item = randomQueue[cheque];
            cheque++;
            return item;
        }
    }

    // A string representation of the queue.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString().substring(0, s.length() - 1);
    }

    // Helper method for resizing the underlying array.
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            if (queue[i] != null) {
                temp[i] = queue[i];
            }
        }
        queue = temp;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        ResizingArrayRandomQueue<Integer> q = 
            new ResizingArrayRandomQueue<Integer>();
        while (!StdIn.isEmpty()) {
            q.enqueue(StdIn.readInt());
        }
        int sum1 = 0;
        for (int x : q) {
            sum1 += x;
        }
        int sum2 = sum1;
        for (int x : q) {
            sum2 -= x;
        }
        int sum3 = 0;
        while (q.size() > 0) {
            sum3 += q.dequeue();
        }
        StdOut.println(sum1);
        StdOut.println(sum2);
        StdOut.println(sum3);
        StdOut.println(q.isEmpty());
    }
}
