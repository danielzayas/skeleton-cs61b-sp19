public class ArrayDeque<T> implements Deque<T>{
    /**
     Double-ended queue implementation that uses arrays to store values.

     for example:
     [null, null, null, null, null, null, null, null, ]  // initialize values as null
      ^ next_i                                  ^ prev_i

     [5, 6, 7, null, null, null, 3, 4, ] // addFirst x2. addLast x3.

     [null, 6, 7, null, null, null, null, null, ] // removeFirst x2.
      ^ prev_i    ^ next_i
     Load factor is 0.25, so don't re-size down (yet).

     [5, 6, 7, null, 1, 2, 3, 4, ]
               ^ prev_i
               ^ next_i
     Now we re-size up.

     Alternate re-sizing scenarios
     [5, 6, 7, 8, 9, 10, 11, null, ]
                             ^
     [null, 6, 7, 8, 9, 10, 11, 12, ]
      ^

     When re-sizing
       1) copy the range [prev_i + 1, length)
       2) copy the range [0, next_i)
     */
    private T[] values = (T[]) new Object[8];
    private int next_i; // index after the last value. addLast inserts here
    private int prev_i; // index preceding the first value. addFirst inserts here
    private int size; // net number of values added
    private final double min_load_factor = 0.25;  // if size < 0.25 * values.length, resize

    /**
     * @param value
     */
    @Override
    public void addFirst(T value) {

    }

    /**
     * @param value
     */
    @Override
    public void addLast(T value) {

    }

    /**
     * @return
     */
    @Override
    public T removeFirst() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public T removeLast() {
        return null;
    }

    /**
     * @param i
     * @return
     */
    @Override
    public T get(int i) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public int size() {
        return 0;
    }
}
