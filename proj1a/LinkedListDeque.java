public class LinkedListDeque<T> {
    /** Nested class. */
    private class Node {
        Node prev;
        T item;
        Node next;

        Node(Node p, T x, Node n) {
            prev = p;
            item = x;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    /** Create an empty list. */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T x) {
        sentinel.next = new Node(sentinel, x, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T x) {
        sentinel.prev = new Node(sentinel.next, x, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        Node ptr = sentinel.next;
        while (ptr != sentinel) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T ret = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return ret;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T ret = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return ret;
    }

    /**  Gets the item at the given index, where 0 is the front, 1 is the next item,
     *  and so forth. If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        Node ptr = sentinel.next;
        while (index > 0) {
            ptr = ptr.next;
            index--;
        }
        return ptr.item;
    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        return getRecursive(sentinel.next, index);
    }

    /** Helper */
    private T getRecursive(Node ptr, int index) {
        if (index == 0) {
            return ptr.item;
        }
        return getRecursive(ptr.next, index - 1);
    }
}
