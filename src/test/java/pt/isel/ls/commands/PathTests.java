package pt.isel.ls.commands;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PathTests {

    @Test
    public void can_parse_valid_paths() {

        Path p0 = Path.of("/");
        assertEquals(0, p0.getSize());

        Path p1 = Path.of("/aa");
        assertEquals(1, p1.getSize());
        assertEquals("aa", p1.getSegmentAt(0));

        Path p2 = Path.of("/aa/bbb");
        assertEquals(2, p2.getSize());
        assertEquals("aa", p2.getSegmentAt(0));
        assertEquals("bbb", p2.getSegmentAt(1));

        Path p3 = Path.of("//bbb");
        assertEquals(2, p3.getSize());
        assertEquals("", p3.getSegmentAt(0));
        assertEquals("bbb", p3.getSegmentAt(1));
    }

    @Test
    public void can_handle_invalid_paths() {

        assertFalse(Path.parse("").isPresent());
        assertFalse(Path.parse("a/").isPresent());
        assertFalse(Path.parse("a/b").isPresent());
        assertFalse(Path.parse(" /b").isPresent());
        assertFalse(Path.parse("/a/").isPresent());
    }

}
