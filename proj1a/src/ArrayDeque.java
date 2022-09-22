public class ArrayDeque<T> implements Deque<T>{
    /**
     Double-ended queue implementation that uses arrays to store values.

     [null, null, null, null, null, null, null, null, ]  // initialize values as null
      ^ next_i                                  ^ prev_i
     */

    /**
     Class attributes

     startingValuesLength will be used to instantiate an array of values for each instance.
     minLoadFactor is used for re-sizing the values array.
    */
    private final int startingValuesLength = 8;
    private final double minLoadFactor = 0.25;

    /**
     Instance attributes
    */
    private T[] values = (T[]) new Object[startingValuesLength];  // array of values
    private int nextI = 0; // index after the last value. addLast inserts here
    private int prevI = startingValuesLength - 1; // index preceding the first value. addFirst inserts here
    private int size = 0; // number of values in the deque

    public ArrayDeque() {}

    /**
     * Add a value to the front of the deque.
     * @param value to add.
     */
    @Override
    public void addFirst(T value) {
        values[prevI] = value;
        updatePrevI(-1); // prevI moves left
        size += 1;

        resizeIfNeeded();
    }

    /**
     * Add a value to the back of the deque.
     * @param value to add.
     */
    @Override
    public void addLast(T value) {
        values[nextI] = value;
        updateNextI(1); // nextI moves right
        size += 1;

        resizeIfNeeded();
    }

    /**
     * @return value at the front of the deque.
     * Updates prevI and size.
     * Decided not to set array element to null after removal.
     */
    @Override
    public T removeFirst() {
        updatePrevI(1); // Now points to the value to remove.
        T value = values[prevI];
        size -= 1;

        resizeIfNeeded();

        return value;
    }

    /**
     * @return value at the front of the deque.
     * Updates nextI and size.
     * Decided not to set array element to null after removal.
     */
    @Override
    public T removeLast() {
        updateNextI(-1);
        T value = values[nextI];
        size -= 1;

        resizeIfNeeded();

        return value;
    }

    /**
     * @param i: index of the value to return.
     * @return value at index i.
     * Does not support negative index values.
     */
    @Override
    public T get(int i) {
        if ((i < 0) || (size <= i)) {
            throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds", i));
        }

        int j = getIndex(prevI + 1 + i);
        return values[j];
    }

    /**
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /* Only resize values array if needed, otherwise no-op */
    private void resizeIfNeeded() {
        double factor = getResizeFactor();
        if (factor != 1.0) {
            resize(factor);
        }
    }

    /**
     When re-sizing the array of values
     0) create a newValues array
     1a) If prev_i >= next_i
     copy the range [prev_i + 1, length)
     copy the range [0, next_i)
     1b) If prev_i < next_i
     copy the range [prev_i + 1, next_i)
     2) update values, next_i, prev_i
     Note the number of values does not change.

     Example re-size up scenario:
     [5, 6, 7, null, 1, 2, 3, 4, ]
               ^ prev_i
               ^ next_i
     Now we re-size up.
     [1, 2, 3, 4, 5, 6, 7, null x9... ]

     Alternate re-size up scenarios:
     [5, 6, 7, 8, 9, 10, 11, null, ]
     ^
     [null, 6, 7, 8, 9, 10, 11, 12, ]
     ^
     */
    private void resize(double factor) {
        int new_length = (int) Math.ceil(values.length * factor);
        T[] newValues = (T[]) new Object[new_length];

        int firstI = getIndex(prevI + 1);
        int lastI = getIndex(nextI - 1);

        // Copy the range [firstI, stopI], inclusive.
        int stopI = Math.min(values.length - 1, lastI);
        int n = stopI + 1 - firstI;
        System.arraycopy(values, firstI, newValues, 0, n);


        if (lastI < firstI) {
            // Copy the range [0, lastI], inclusive.
            int n2 = lastI + 1;
            System.arraycopy(values, 0, newValues, n, n2);
            n += n2;
        }

        // update values, next_i, prev_i
        values = newValues;
        prevI = values.length - 1;
        nextI = size;
        assert size == n; // TODO: remove
    }

    /**
     If should re-size down, return a factor < 1.0
     Elif should re-size up, return a factor > 1.0
     Else, return 1.0
     */
    private double getResizeFactor() {
        // Check criteria for re-sizing down
        if (size < Math.floor(minLoadFactor * values.length)) {
            if (values.length <= startingValuesLength) {
                return 1.0;
            } else {
                return 0.5;
            }

        }

        // Check criteria for re-sizing up
        if (values.length - 1 == size) {
            assert nextI == prevI; // TODO: delete
            return 2.0;
        }

        // Otherwise, don't resize
        return 1.0;
    }

    /* Get an in-bounds index by wrapping any out-of-bounds candidateIndex */
    private int getIndex(int candidateIndex) {
        if (candidateIndex < 0) {
            assert candidateIndex == -1; // TODO: delete
            int remainder = candidateIndex % values.length;
            return values.length + remainder;
        } else if (values.length <= candidateIndex) {
            assert candidateIndex == values.length; // TODO: delete
            int remainder = candidateIndex % values.length;
            return remainder;
        } else {
            // The candidateIndex is in-bounds
            return candidateIndex;
        }
    }

    /**
     * Update nextI, which may wrap around to index 0.
     * @param step is -1 to step left or 1 to step right.
     */
    private void updateNextI(int step) {
        int candidateIndex = nextI + step;
        nextI = getIndex(candidateIndex);
    }

    /**
     * Update prevI, which may wrap around to the last index.
     * @param step is -1 to step left or 1 to step right.
     */
    private void updatePrevI(int step) {
        int candidateIndex = prevI + step;
        prevI = getIndex(candidateIndex);
    }
}
