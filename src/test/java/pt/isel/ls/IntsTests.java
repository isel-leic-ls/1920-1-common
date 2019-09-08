package pt.isel.ls;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IntsTests {

    @Test
    public void max_returns_greatest() {
        assertEquals(1, Ints.max(1, -2));
        assertEquals(1, Ints.max(-2, 1));
        assertEquals(-1, Ints.max(-1, -2));
        assertEquals(-1, Ints.max(-2, -1));
    }
}
