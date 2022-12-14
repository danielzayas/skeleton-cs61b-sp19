import org.junit.Assert;
import org.junit.Test;

import static org.apache.commons.math3.util.IntegerSequence.range;

public class ArrayDequeTest {
    /* Test instantiation, size of an empty deque, remove from empty deque */
    @Test
    public void TestEmpty() {
        Deque<Integer> d = new ArrayDeque<>();
        Assert.assertEquals(0, d.size());
        Assert.assertNull(d.removeLast());
        Assert.assertNull(d.removeFirst());
    }

    @Test
    public void TestAddGetRemove() {
        ArrayDeque<Integer> d = new ArrayDeque<>();

        /* Test addFirst x2, addLast */
        d.addFirst(4);
        d.addFirst(3);
        d.addLast(5);

        /* Test size */
        Assert.assertEquals(3, d.size());

        /* Test get */
        Assert.assertEquals(Integer.valueOf(3), d.get(0));
        Assert.assertEquals(Integer.valueOf(5), d.get(2));

        /* Test remove */
        Assert.assertEquals(Integer.valueOf(5), d.removeLast());
        Assert.assertEquals(Integer.valueOf(4), d.removeLast());
        Assert.assertEquals(Integer.valueOf(3), d.removeFirst());

        /* Test size */
        Assert.assertEquals(0, d.size());
    }

    @Test
    public void TestAddLastResize() {
        ArrayDeque<Integer> d = new ArrayDeque<>();

        for (int x : range(0, 9)) {
            d.addLast(x);
        }

        for (int y : range(0, 9)) {
            Assert.assertEquals(Integer.valueOf(y), d.removeFirst());
        }
    }

    @Test
    public void TestAddFirstResize() {
        ArrayDeque<Integer> d = new ArrayDeque<>();

        for (int x : range(9, 0, -1)) {
            d.addFirst(x);
        }
        Assert.assertEquals(Integer.valueOf(9), d.removeLast());
        Assert.assertEquals(Integer.valueOf(8), d.removeLast());

        for (int y : range(0, 7)) {
            Assert.assertEquals(Integer.valueOf(y), d.removeFirst());
        }
    }

    @Test
    public void TestIterable() {
        ArrayDeque<Integer> d = new ArrayDeque<>();

        for (int x : range(0, 9)) {
            d.addLast(x);
        }

        Integer i = Integer.valueOf(0);
        for (Object x : d) {
            Integer y = (Integer) x;
            Assert.assertEquals(i, y);
            i += 1;
        }

    }
}
