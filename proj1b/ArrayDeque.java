public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /**
     * Create an empty list.
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 0;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(2 * size);
        }
        items[nextFirst] = item;
        size++;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        nextLast = (nextFirst + size + 1) % items.length;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(2 * size);
        }
        items[nextLast] = item;
        size++;
        nextLast = (nextLast + 1) % items.length;
        nextFirst = (nextLast - size - 1 + 2 * items.length) % items.length;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    @Override
    public void printDeque() {
        if ((nextFirst + 1) % items.length >= nextLast) {
            for (int i = (nextFirst + 1) % items.length; i < items.length; i++) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i < nextLast; i++) {
                System.out.print(items[i] + " ");
            }
        } else {
            for (int i = (nextFirst + 1) % items.length; i < nextLast; i++) {
                System.out.print(items[i] + " ");
            }
        }
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (size < items.length / 2) {
            resize(items.length / 2);
        }

        /** The nextFirst + 1 maybe bigger than length of the array. */
        int index = (nextFirst + 1) % items.length;
        T ret = items[index];
        items[index] = null;
        nextFirst = index;
        size--;
        return ret;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (size < items.length / 2) {
            resize(items.length / 2);
        }

        /** The nextLast + 1 maybe smaller than 0. */
        int index = (nextLast - 1 + items.length) % items.length;
        T ret = items[index];
        items[index] = null;
        nextLast = index;
        size--;
        return ret;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such item exists, returns null. Must not alter the deque!
     */
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[(index + nextFirst + 1) % items.length];
    }

    /**
     * Resizes the array.
     */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        if ((nextFirst + 1) % items.length >= nextLast) {
            System.arraycopy(items, (nextFirst + 1) % items.length, a,
                    0, items.length - nextFirst - 1);
            System.arraycopy(items, 0, a, items.length - nextFirst - 1,
                    size - (items.length - nextFirst - 1));
        } else {
            System.arraycopy(items, (nextFirst + 1) % items.length, a, 0, size);
        }
        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }
}
