import java.util.Arrays;
import java.util.Iterator;

import static org.apache.commons.math3.util.IntegerSequence.range;

public interface Deque<T> {

    void addFirst(T value);
    void addLast(T value);

    T removeFirst();
    T removeLast();

    T get(int i);

    int size();
    default boolean isEmpty() {
        return size() == 0;
    }

    default void printDeque() {
        System.out.println("printDeque is not implemented");
    }

    default Iterator<T> iterator() {
        int n = size();
        T[] values = (T[]) new Object[n];
        for (int i : range(0, n-1, 1)) {
            values[i] = get(i);
        }

        return Arrays.stream(values).iterator();
    }
}
