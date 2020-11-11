/**
 * Movie.java
 * @author Uriel Garcia
 * @author Andrew Nowinski
 * CIS 22C, Lab 7
 */
import java.util.NoSuchElementException;

public class List<T extends Comparable<T> > {

    private class Node {
        private T data;
        private Node next;
        private Node prev;

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private int length;
    private Node first;
    private Node last;
    private Node iterator;

    /****CONSTRUCTOR****/

    /**
     * Instantiates a new List with default values
     * @postcondition A new empty list is created
     */
    public List() {

        first = null;

        last = null;

        iterator = null;

        length = 0;
    }

    /**
     * Instantiates a new List by copying another List
     * @param original the List to make a copy of
     * @postcondition a new List object, which is an identical
     * but separate copy of the List original
     */
    public List(List original) {

        if (original == null) {

            return;

        } else if (original.length == 0) {

            length = 0;

            first = null;

            last = null;

            iterator = null;

        } else {

            Node temp = original.first;

            while (temp != null) {

                addLast(temp.data);

                temp = temp.next;
            }

            iterator = null;

        }
    }

    /****ACCESSORS****/                                             //Accessors

    /**
     * Returns the index of the iterator
     * from 1 to n. Note that there is
     * no index 0. Does not use recursion.
     * @precondition
     * @return the index of the iterator
     * @throws NullPointerException when
     * the precondition is violated
     */
    public int getIndex() throws NullPointerException{          // new

        if(iterator == null) {

            throw new NullPointerException("getIndex(): cannot get index when iterator is null");

        }

        int n = 1;

        Node temp = first;

        while(!temp.equals(iterator)) {

            temp = temp.next;
            n++;
        }

        return n;
    }

    /**
     * Returns the index from 1 to length
     * where value is located in the List
     * by calling the private helper method
     * binarySearch
     * @param value the value to search for
     * @return the index where value is
     * stored from 1 to length, or -1 to
     * indicate not found
     * @precondition isSorted()
     * @postcondition the position of the
     * iterator must remain unchanged!
     * @throws IllegalStateException when the
     * precondition is violated.
     */
    public int binarySearch(T value) throws IllegalStateException {         //new

        int low = 0;

        int high = length;

        if(!inSortedOrder()) {

            throw new IllegalStateException("binarySearch(): Unable to preform BinarySearch when the items are"
                    + " not sorted");

        }

        return binarySearch(low, high, value);

    }

    /**
     * Searches for the specified value in
     * the List by implementing the recursive
     * binarySearch algorithm
     * @param low the lowest bounds of the search
     * @param high the highest bounds of the search
     * @param value the value to search for
     * @return the index at which value is located
     * or -1 to indicate not found
     * @postcondition the location of the iterator
     * must remain unchanged
     */
    private int binarySearch(int low, int high, T value) {      //new

        int mid = low + (high - low) /2;

        advanceToIndex(mid);

        if(high < low) {

            return -1; // not found

        }

        if(iterator.data == value) {

            return getIndex();

        } else if (value.compareTo(getIterator()) < 0) {

            return binarySearch(low, mid - 1, value);

        } else {

            return binarySearch(mid + 1, high, value);
        }

    }

    /**
     * Uses the iterative linear search
     * algorithm to locate a specific
     * element in the list
     * @param element the value to search for
     * @return the location of value in the
     * List or -1 to indicate not found
     * Note that if the List is empty we will
     * consider the element to be not found
     * @postcondition: position of the iterator remains
     * unchanged!
     */
    public int linearSearch(T element) {            //new

        Node temp = first;

        for(int i = 0; i < length; i++) {

            if(temp.data == getIterator()) {

                return getIndex();
            }
            temp = temp.next;


        }

        return -1;

    }
    /**
     * Determines whether a List is sorted
     * by calling its recursive helper method
     * isSorted
     * Note: An empty List can be
     * considered to be (trivially) sorted
     * @return whether this List is sorted
     */
    public boolean inSortedOrder() {                    //new

        if (length == 0) {

            return true;

        } else {

            return inSortedOrder(first);
        }

    }
    /**
     * Helper method to inSortedOrder
     * Determines whether a List is
     * sorted in ascending order recursively
     * @return whether this List is sorted
     */
    private boolean inSortedOrder(Node node) {

        if(node.next == null) {

            return true;

        } else if(node.data.compareTo(node.next.data) < 0){

            node = node.next;

            return inSortedOrder(node);
        }

        return false;


    }

    /**
     * Returns the value stored in the first node
     * @precondition length != 0
     * @return the value stored at node first
     * @throws NoSuchElementException when precondition is violated
     */
    public T getFirst() throws NoSuchElementException{ // check

        if (length == 0) {

            throw new NoSuchElementException("getFirst: List is Empty. No data to access!");

        }

        return first.data;
    }

    /**
     * Returns the value stored in the last node
     * @precondition length != 0
     * @return the value stored in the node last
     * @throws NoSuchElementException when precondition is violated
     */
    public T getLast() throws NoSuchElementException{ // check
        if (length == 0) {

            throw new NoSuchElementException("getLast: List is Empty. No data to access!");

        }
        return last.data;
    }

    /**
     * Returns the current length of the list
     * @return the length of the list from 0 to n
     */
    public int getLength() {
        return length;
    } // check

    /**
     * Returns whether the list is currently empty
     * @return whether the list is empty
     */
    public boolean isEmpty() {
        return length == 0;
    } // check

    /**
     * Returns the element currently pointed to by the iterator
     * @precondition iterator != null
     * @return the value pointed at by the iterator
     * @throws NullPointerException when the precondition is violated
     */
    public T getIterator() throws NullPointerException {

        if(offEnd()) {

            throw new NoSuchElementException("getIterator(): the iterator is off end of the List." +
                    "Can not return data.");

        }

        return iterator.data;

    }

    /**
     * Determines wheter the 2 Lists have the same data in the same order
     * @param o the Object to compare in this List
     * @return whether the 2 lists are equal
     */
    @SuppressWarnings("unchecked")
    @Override public boolean equals(Object o) {

        if (o == this) {

            return true;

        } else if (!(o instanceof List)) {

            return false;

        } else {

            List L = (List) o;

            if(this.length != L.length) {

                return false;

            } else {

                Node temp1 = this.first;

                Node temp2 = L.first;

                while(temp1 != null) {

                    if(!(temp1.data.equals(temp2.data))) {

                        return false;

                    }
                    temp1 = temp1.next;

                    temp2 = temp2.next;

                }
                return true;
            }
        }
    }

    /****MUTATORS****/                                          // mutators

    /**
     * Places the iterator at first
     * and then iteratively advances
     * it to the specified index
     * no recursion
     * @param index the index where
     * the iterator should be placed
     * @precondition 1 <= index <= length
     * @throws IndexOutOfBoundsException
     * when precondition is violated
     */
    public void advanceToIndex(int index) throws IndexOutOfBoundsException{         // new

        if(index < 1 || index > length) {

            throw new IndexOutOfBoundsException("advanceToIndex() " + "cannot return index when it is out of bounds");
        }

        pointIterator();

        for(int i = 1; i < index; i++) {

            advanceIterator();
        }

    }
    /**
     * Creates a new first element
     * @param data the data to insert at the
     * front of the list
     * @postcondition a new first node is created and length is increased by 1
     */
    public void addFirst(T data) {      //O(1)      //Check

        if (length == 0) {

            first = last = new Node(data);

        } else {

            Node N = new Node(data);

            N.next = first;

            first.prev = N;

            first = N;

        }

        length++;
    }

    /**
     * Creates a new last element
     * @param data the data to insert at the
     * end of the list
     * @postcondition a new last node is created and length is increased by 1
     */
    public void addLast(T data) {                   // check

        Node N = new Node(data);

        if (length == 0) { // if there are no nodes in the list

            first = last = N;

        } else {

            last.next = N;

            N.prev = last;

            last = N;
        }

        length++; // increase the number of nodes in are list
    }

    /**
     * Removes the element at the front of the list
     * @precondition first != null
     * @postcondition assign a new first node and decrease the length by 1 or error message is displayed
     * @throws NoSuchElementException when precondition is violated
     */
    public void removeFirst() throws NoSuchElementException{ // check

        if (length == 0) {

            throw new NoSuchElementException("removeFirst(): Cannot remove from an empty List!");

        } else if (length == 1) {

            first = last = null;

        } else {

            first = first.next;

            first.prev = null;

        }

        length--;
    }

    /**
     * Removes the element at the end of the list
     * @precondition last != null
     * @postcondition assign a new last node and decrease the length by 1 or error message is displayed
     * @throws NoSuchElementException when precondition is violated
     */
    public void removeLast() throws NoSuchElementException {        //check

        if(length == 0) {

            throw new NoSuchElementException("removeLast(): Cannot remove from an empty List!");
        }
        else if (length == 1) {

            first = last = null;

        } else {
            last = last.prev;

            last.next = null;
        }

        length--;
    }

    /**
     * Return whether the iterator is off the end of the list
     * @return whether iterator equals null
     */
    public boolean offEnd() {

        return iterator == null;

    }

    /**
     * Remove the node pointed to by the Iterator
     * @precondition iterator != null
     * @postcondition set the Iterator = null
     * @throws NullPointerException when the precondition is violated
     */

    public void removeIterator() throws NullPointerException {

        //precondition

        if(iterator == null) {

            throw new NullPointerException("removeIterator(): cannot remove Iterator from an empty list "

                    + "or Iterator has not been initialized");

        } else if(iterator == last) { // removing the node if the iterator is pointing to the last node

            removeLast();

        } else if(iterator == first) { // removing the node if the iterator is pointing to the first node

            removeFirst();

        } else {

            iterator.prev.next = iterator.next;
            iterator.next.prev = iterator.prev;
            length--;

        }

        iterator = null;
    }

    /**
     * Inserts a node following the one currently being referenced by the iterator
     * @param data the new data to insert
     * @precondition iterator != null
     * @throws NullPointerException when precondition is violated
     */
    public void addIterator(T data) throws NullPointerException {

        Node N = new Node(data);

        if (offEnd()) {

            throw new NullPointerException("addIterator(): iterator is off end of the list."

                    + " Cannot insert new data.");

        } else if (iterator == last) {

            addLast(data);

        } else {

            N.next = iterator.next;

            N.prev = iterator;

            iterator.next.prev = N;
            iterator.next = N;

            length++;

        }
    }

    /**
     *  Moves the iterator to point to the first Node of the List
     */
    public void pointIterator() {

        iterator = first;

    }

    /**
     * Advances the iterator by one Node in the List towards the last node
     * @precondition iterator != null
     * @throws NullPointerException when the precondition is violated
     */
    public void advanceIterator() throws NullPointerException {

        if (offEnd()) {

            throw new NullPointerException("advanceIterator(): iterator is off end of the List."

                    + "Can not advance.");
        }

        iterator = iterator.next;
    }

    /**
     * Reverses the iterator by one Node in the List towards the first node
     * @precondition iterator != null
     * @throws NullPointerException when the precondition is violated
     */
    public void reverseIterator() throws NullPointerException {

        if (offEnd()) {

            throw new NullPointerException("advanceIterator(): iterator is off end of the List."

                    + "Can not reverse.");

        }

        iterator = iterator.prev;
    }



    /****ADDITIONAL OPERATIONS****/                             //Additional Operations




    /**
     * Prints a linked list to the console
     * in reverse by calling the private
     * recursive helper method printInReverse
     */
    public void printInReverse() {


        printInReverse(last);

        System.out.print("");


    }

    /**
     * Recursively prints a linked list to the console
     * in reverse order from last to first (no loops)
     * Each element separated by a space
     * Should print a new line after all
     * elements have been displayed
     */

    private void printInReverse(Node node) {


        if(node != null) {

            System.out.print(node.data + " ");

            printInReverse(node.prev);

        }



    }


    /**
     * List with each value separated by a space
     * At the end of the List is a new line
     * @return the List as a String for display
     * // O(n)
     */
    @Override public String toString() { // O(n)

        String result = "";

        Node temp = first;

        while(temp != null) {

            result += temp.data + " ";

            temp = temp.next;
        }

        System.out.println();

        return result;
    }

    /**
     * Prints the contents of the linked list to the screen in the format
     * #. element followed by a new line
     * @return the List as a String for display
     */
    public String printNumberedList() {

        String result = "";

        Node temp = first;
        int i = 0;

        while(temp != null) {

            result += i + ". " + temp.data;

            temp = temp.next;
            i++;
        }
        System.out.println();
        return result;
    }


}