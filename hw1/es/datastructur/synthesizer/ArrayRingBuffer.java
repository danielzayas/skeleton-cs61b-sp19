package es.datastructur.synthesizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index BEFORE the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        fillCount = 0;

        last = 0;
        first = rb.length - 1;
    }

    /**
     * @return
     */
    @Override public int capacity() {
        return rb.length;
    }

    /**
     * @return
     */
    @Override public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) throws RuntimeException {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (Objects.equals(fillCount(), capacity())) {
            throw new RuntimeException("At capacity. Cannot enqueue.");
        }

        fillCount += 1;
        rb[last] = x;
        last = stepIndex(last, 1);  // step right
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() throws RuntimeException {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (Objects.equals(fillCount(), 0)) {
            throw new RuntimeException("Cannot dequeue from an empty queue.");
        }

        fillCount -= 1;
        first = stepIndex(first, 1);

        return rb[first];
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() throws RuntimeException {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if (Objects.equals(fillCount(), 0)) {
            throw new RuntimeException("Cannot peek in an empty queue.");
        }

        int i = stepIndex(first, 1);

        return rb[i];
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (! (o instanceof ArrayRingBuffer)) {
            return false;
        }

        ArrayRingBuffer other = (ArrayRingBuffer) o;

        // stop early if the fill counts are unequal
        if (fillCount() != other.fillCount()) {
            return false;
        }

        Iterator it1 = iterator();
        Iterator it2 = other.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            if (!Objects.equals(it1.next(), it2.next())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (Object x : this) {
            result += 31 * result + x.hashCode();
        }

        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new ARBIterator(this);
    }

    private class ARBIterator<T> implements Iterator<T> {
        private int index;
        private int count;
        private ArrayRingBuffer<T> arb;

        public ARBIterator(ArrayRingBuffer<T> buffer) {
            arb = buffer;
            index = arb.stepIndex(arb.first, 1);
            count = 0;
        }

        public boolean hasNext() {return count < arb.fillCount();}

        public T next() {
            T x = arb.rb[index];
            index = arb.stepIndex(index, 1);
            count += 1;

            return x;
        }
    }

    // private methods here
    /**
     * Returns a new index j, one step from i, wrapping left or right.
     * @param i : current index
     * @param step : stepsize to increment or decrement, respectively
     */
    private int stepIndex(int i, int step) {
        int j = (i + step) % rb.length;

        if (j < 0) {
            j = rb.length + j;  // e.g. -1 wraps to the last index
        }

        return j;
    }
}
    // TODO: Remove all comments that say TODO when you're done.
