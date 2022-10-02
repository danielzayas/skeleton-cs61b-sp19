package es.datastructur.synthesizer;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testArrayRingBuffer() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer<>(4);

        assertTrue(arb.isEmpty());       //                       (returns true)

        arb.enqueue(9.3);    // 9.3
        arb.enqueue(15.1);   // 9.3  15.1
        arb.enqueue(31.2);   // 9.3  15.1  31.2
        assertEquals(3, arb.fillCount());
        assertFalse(arb.isFull());        // 9.3  15.1  31.2       (returns false)

        arb.enqueue(-3.1);   // 9.3  15.1  31.2  -3.1
        assertEquals(4, arb.fillCount());
        assertTrue(arb.isFull());        // 9.3  15.1  31.2  -3.1 (returns true)

        assertEquals(Double.valueOf(9.3), arb.dequeue());       // 15.1 31.2  -3.1       (returns 9.3)

        assertEquals(Double.valueOf(15.1), arb.peek());          // 15.1 31.2  -3.1       (returns 15.1)
    }

    @Test
    public void testARBIterator() {
        int capacity = 4;
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer<>(capacity);
        List<Double> list = List.of(new Double[] {9.3, 15.1, 31.2});

        for (double x : list) {
            arb.enqueue(x);
        }

        int i = 0;
        for (Double y : arb) {
            assertEquals(list.get(i), y);
            i += 1;
        }
    }

    @Test
    public void testARBEquals() {
        int capacity = 4;
        List<Double> list = List.of(new Double[] {9.3, 15.1, 31.2});

        ArrayRingBuffer<Double> arb1 = new ArrayRingBuffer<>(capacity);
        ArrayRingBuffer<Double> arb2 = new ArrayRingBuffer<>(capacity);

        for (Double x : list) {
            arb1.enqueue(x);
            arb2.enqueue(x);
        }

        assertTrue(arb1.equals(arb1));

        assertTrue(arb1.equals(arb2));
        assertTrue(arb2.equals(arb1));
        assertEquals(arb1.hashCode(), arb2.hashCode());

        arb2.enqueue(99.9);
        assertFalse(arb2.equals(arb1));
    }
}
