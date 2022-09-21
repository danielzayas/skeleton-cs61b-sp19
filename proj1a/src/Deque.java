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
}
