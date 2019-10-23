package pt.isel.ls.commands;

import org.junit.Test;
import pt.isel.ls.commands.exceptions.ParameterException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ParameterTests {

    @Test
    public void testInvalid() {
        String[] strings = {"", "foo", "=bar", "=", "====", "a===b"};
        for (String s : strings) {
            assertFalse(Parameters.parse(s).isPresent());
        }
    }

    @Test
    public void testValid() throws ParameterException {
        Optional<Parameters> op = Parameters.parse("a=b");
        Parameters p = op.get();
        assertEquals("b", p.getMandatoryString("a"));

        op = Parameters.parse("x=y&a=b");
        p = op.get();
        assertEquals("b", p.getMandatoryString("a"));
        assertEquals("y", p.getOptionalString("x").get());
        assertFalse(p.getOptionalString("foo").isPresent());
    }
}
