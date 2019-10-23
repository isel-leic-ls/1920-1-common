package pt.isel.ls.commands;

import org.junit.Test;
import pt.isel.ls.commands.exceptions.CommandRequestParseException;
import pt.isel.ls.commands.exceptions.ParameterException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class RouterTests {

    @Test
    public void tests() throws CommandRequestParseException, ParameterException {
        CommandHandler h1 = prms -> null;
        CommandHandler h2 = prms -> null;

        Router router = new Router();
        router
            .add(Method.parse("GET").get(), PathTemplate.of("/projects/{pid}"), h1)
            .add(Method.parse("GET").get(), PathTemplate.of("/projects/{pid}/tasks/{tid}"), h2);

        CommandRequest request = CommandRequest.parse("GET /projects/123");
        Optional<Router.Result> routeResult = router.find(request);
        assertEquals(h1, routeResult.get().getHandler());
        assertEquals(123, routeResult.get().getPathParameters().getMandatoryInt("pid"));
    }
}
