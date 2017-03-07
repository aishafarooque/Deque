import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// Takes a command-line integer k; reads in a sequence of strings from 
// standard input; and prints out exactly k of them, uniformly at random. 
// Note that each item from the sequence is printed out at most once.
public class Subset {
    public static void main(String[] args) {
        /* Create a new RandomQueue, 
         * enqueue the input, 
         * then dequeue k
         * and have it printed out
         */
        String input;
        int k = Integer.parseInt(args[0]);
        
        //create new ResizingArrayRandomQueue made of strings.  
        ResizingArrayRandomQueue<String> q =
        new ResizingArrayRandomQueue<String>();
        
        for (int x = 0; x < k; x++) {
            // read strings and insert into q
            String read = StdIn.readString();
            q.enqueue(read); // insert to q
            String y = q.dequeue(); // dequee=ue
            StdOut.println(y); //print dequeue
        }
    }
}
