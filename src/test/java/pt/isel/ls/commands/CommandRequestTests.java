package pt.isel.ls.commands;

import org.junit.Test;
import pt.isel.ls.TestUtils;

import static org.junit.Assert.assertEquals;

public class CommandRequestTests {

    @Test
    public void can_parse_valid_commands() throws CommandRequestParseException {
        CommandRequest req = CommandRequest.parse("GET /projects");
        assertEquals(Method.GET, req.getMethod());
        assertEquals(1, req.getPath().getSize());
        assertEquals("projects", req.getPath().getSegmentAt(0));

        req = CommandRequest.parse("POST /projects/123/tasks");
        assertEquals(Method.POST, req.getMethod());
        assertEquals(3, req.getPath().getSize());
        assertEquals("tasks", req.getPath().getSegmentAt(2));

        req = CommandRequest.parse("EXIT /");
        assertEquals(Method.EXIT, req.getMethod());
        assertEquals(0, req.getPath().getSize());
    }

    @Test
    public void can_handle_invalid_cases() {
        TestUtils.expect(CommandRequestParseException.InvalidFormat.class, () -> CommandRequest.parse(""));
        TestUtils.expect(CommandRequestParseException.InvalidFormat.class, () -> CommandRequest.parse("a"));
        TestUtils.expect(CommandRequestParseException.InvalidFormat.class, () -> CommandRequest.parse("a b c"));

        TestUtils.expect(CommandRequestParseException.InvalidMethod.class, () -> CommandRequest.parse("FOO /bar"));
        TestUtils.expect(CommandRequestParseException.InvalidPath.class, () -> CommandRequest.parse("GET bar"));
    }
}
