import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class ConsListListTests {
    private ConsListList<Integer> list;

    @BeforeEach
    void start() {
        list = new ConsListList<>();
    }

    @Test
    void testAddSize() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add(1);
        assertFalse(list.isEmpty());
    }

    @Test
    void testGet() {
        list.add(10);
        list.add(20);
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
    }

    @Test
    void testSet() {
        list.add(5);
        list.add(6);
        int old = list.set(1, 10);
        assertEquals(6, old);
        assertEquals(10, list.get(1));
    }

    @Test
    void testRemoveObj() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertTrue(list.remove(Integer.valueOf(2)));
        assertEquals(2, list.size());
        assertFalse(list.contains(2));
        assertEquals(1,list.get(0));
        assertEquals(3,list.get(1));
    }

    @Test
    void testContains() {
        list.add(9);
        list.add(7);
        assertTrue(list.contains(9));
        assertFalse(list.contains(3));
    }

    @Test
    void testContainsAll() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertTrue(list.containsAll(Arrays.asList(1, 2)));
        assertFalse(list.containsAll(Arrays.asList(1, 4)));
    }

    @Test
    void testRemoveAll() {
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.removeAll(Arrays.asList(2,3));
        assertFalse(list.contains(2));
        assertEquals(1, list.size());
        assertEquals(1,list.get(0));
    }

    @Test
    void testRetainAll() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.retainAll(Arrays.asList(3, 4));
        assertEquals(1, list.size());
        assertTrue(list.contains(3));
    }

    @Test
    void testClear() {
        list.add(1);
        list.add(2);
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    void testAddThroughIndex() {
        list.add(1);
        list.add(3);
        list.add(1, 2);
        assertEquals(1,list.get(0));
        assertEquals(2,list.get(1));
        assertEquals(3,list.get(2));
    }

    @Test
    void testRemoveByIndex() {
        list.add(10);
        list.add(20);
        list.add(30);
        assertEquals(20, list.remove(1));
        assertEquals(2, list.size());
        assertEquals(10,list.get(0));
        assertEquals(30,list.get(1));
    }

    @Test
    void testIndexOf() {
        list.add(5);
        list.add(6);
        list.add(5);
        assertEquals(0, list.indexOf(5));
    }

    @Test
    void testLastIndexOf() {
        list.add(5);
        list.add(6);
        list.add(5);
        assertEquals(2, list.lastIndexOf(5));
    }

    @Test
    void testIterator() {
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals(1, it.next());
        assertEquals(2, it.next());
        assertEquals(3, it.next());
        assertFalse(it.hasNext());
    }

}
