package pt.isel.ls.commands;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MethodTests {

    @Test
    public void can_parse_valid_methods() {
        assertEquals(Method.GET, Method.parse("GET").get());
        assertEquals(Method.POST, Method.parse("POST").get());
        assertEquals(Method.EXIT, Method.parse("EXIT").get());
    }

    @Test
    public void methods_are_case_sensitive() {
        assertFalse(Method.parse("get").isPresent());
        assertFalse(Method.parse("post").isPresent());
        assertFalse(Method.parse("exit").isPresent());
    }

    @Test
    public void parse_can_handle_edge_cases() {
        assertFalse(Method.parse("wrong").isPresent());
        assertFalse(Method.parse("").isPresent());
        assertFalse(Method.parse(null).isPresent());
        assertFalse(Method.parse("  POST  ").isPresent());
    }
}
