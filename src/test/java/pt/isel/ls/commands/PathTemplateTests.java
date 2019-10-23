package pt.isel.ls.commands;

import org.junit.Test;
import pt.isel.ls.commands.exceptions.ParameterException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class PathTemplateTests {

    @Test
    public void matchTests() throws ParameterException {
        PathTemplate template = PathTemplate.of("/projects/{pid}/tasks/{tid}");

        Path path = Path.of("/");
        Optional<PathParameters> matchResult = template.match(path);
        assertFalse(matchResult.isPresent());

        path = Path.of("/projects/123/tasks/456");
        matchResult = template.match(path);
        assertTrue(matchResult.isPresent());
        assertEquals("123", matchResult.get().getMandatoryString("pid"));
        assertEquals(123, matchResult.get().getMandatoryInt("pid"));
        assertEquals("456", matchResult.get().getMandatoryString("tid"));

    }
}
