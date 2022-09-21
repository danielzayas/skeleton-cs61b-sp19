import org.junit.Assert;
import org.junit.Test;

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
}
