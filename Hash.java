/**
 * Movie.java
 * @author Uriel Garcia
 * @author Andrew Nowinski
 * CIS 22C, Lab 7
 */
import java.util.ArrayList;

public class Hash<T extends Comparable<T>> {

    private int numElements;
    private ArrayList<List<T> > Table;

    /**
     * Constructor for the Hash.java
     * class. Initializes the Table to
     * be sized according to value passed
     * in as a parameter
     * Inserts size empty Lists into the
     * table. Sets numElements to 0
     * @param size the table size
     */
    public Hash(int size) {

        numElements = 0;

        Table = new ArrayList<List<T>>();

        for(int i = 0; i < size; i++) {

            List<T>list = new List<T>();

            Table.add(i, list);

        }

    }

/**Accessors*/

    /**
     * Returns the hash value in the Table
     * for a given key by taking modulus
     * of the hashCode value for that key
     * and the size of the table
     * @param t the key
     * @return the index in the Table
     */
    private int hash(T t) {

        int code = t.hashCode();

        return code % Table.size();

    }

    /**
     * Counts the number of keys at this index
     * @param index the index in the Table
     * @precondition 0 <= index < Table.length
     * @return the count of keys at this index
     * @throws IndexOutOfBoundsException
     */
    public int countBucket(int index) throws IndexOutOfBoundsException{

        if(index <= 0 && index > Table.size()) {

            throw new IndexOutOfBoundsException("countBucket(): index out of range.");

        } else {

            return Table.get(index).getLength();

        }

    }

    /**
     * Returns total number of keys in the Table
     * @return total number of keys
     */
    public int getNumElements() {

        return numElements;
    }

    /**
     * Searches for a specified key in the Table
     * @param t the key to search for
     * @return whether the key is in the Table
     */
    public boolean search(T t) {                                                 ///////////////

        int index = hash(t);

        Table.get(index).pointIterator();

        for(int i = 0; i < Table.get(index).getLength(); i++) {

            if(t.equals(Table.get(index).getIterator())) {

                return true;

            } else {

                Table.get(index).advanceIterator();

            }

        }

        return false;


    }


/**Manipulation Procedures*/

    /**
     * Inserts a new key in the Table
     * calls the hash method to determine placement
     * @param t the key to insert
     */
    public void insert(T t) {

        int index = hash(t);

        Table.get(index).addLast(t);

        numElements++;

    }


    /**
     * removes the key t from the Table
     * calls the hash method on the key to
     * determine correct placement
     * has no effect if t is not in
     * the Table
     * @param t the key to remove
     */
    public void remove(T t) {                                               //Good to go

        int index = hash(t);

        if(Table.get(index).linearSearch(t) == -1) {

            System.out.println("not found ");
            return;

        } else {

            Table.get(index).pointIterator();

            for(int i = 0; i < Table.get(index).getLength(); i++) {

                if(t.equals(Table.get(index).getIterator())) {

                    Table.get(index).removeIterator();
                } else {

                    Table.get(index).advanceIterator();

                }
            }

        }

        numElements--;
    }

/**Additional Methods*/

    /**
     * Prints all the keys at a specified
     * bucket in the Table. Each key displayed
     * on its own line, with a blank line
     * separating each key
     * Above the keys, prints the message
     * "Printing bucket #<bucket>:"
     * Note that there is no <> in the output
     * @param bucket the index in the Table
     */
    public void printBucket(int bucket) {

        System.out.println("Printing bucket #" + bucket + ":");

        System.out.println();

        Table.get(bucket).pointIterator();

        for(int i = 0; i < Table.get(bucket).getLength(); i++) {

            System.out.println(Table.get(bucket).getIterator());

            System.out.println();

            Table.get(bucket).advanceIterator();

        }

    }

    /**
     * Prints the first key at each bucket
     * along with a count of the total keys
     * with the message "+ <count> -1 more
     * at this bucket." Each bucket separated
     * with two blank lines. When the bucket is
     * empty, prints the message "This bucket
     * is empty." followed by two blank lines
     */
    public void printTable(){

        for(int i = 0; i < Table.size(); i++) {

            if(Table.get(i).isEmpty()) {

                System.out.println("This bucket is empty.\n\n");

            } else {

                System.out.println(Table.get(i).getFirst() + "\n+ " + (Table.get(i).getLength() - 1) + " more at this bucket.");

                System.out.print("\n\n");

            }


        }
    }
}