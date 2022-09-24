import java.util.Arrays;
import java.util.Iterator;

import static org.apache.commons.math3.util.IntegerSequence.range;

abstract class BaseDeque<T> implements Deque<T> {

    /**
     * @return an iterator for the values in the Deque
     */
    public Iterator<T> iterator() {
        int n = size();
        T[] values = (T[]) new Object[n];
        for (int i : range(0, n-1, 1)) {
            values[i] = get(i);
        }

        return Arrays.stream(values).iterator();
    }
}
