package pt.isel.ls;

import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.commands.CommandRequest;
import pt.isel.ls.commands.CommandResult;
import pt.isel.ls.commands.Method;
import pt.isel.ls.commands.Parameters;
import pt.isel.ls.commands.PathTemplate;
import pt.isel.ls.commands.Router;
import pt.isel.ls.commands.exceptions.CommandHandlerException;
import pt.isel.ls.commands.exceptions.CommandRequestParseException;
import pt.isel.ls.commands.exceptions.InfrastructureException;
import pt.isel.ls.commands.exceptions.ParameterException;
import pt.isel.ls.handlers.Exit;
import pt.isel.ls.handlers.GetStudents;
import pt.isel.ls.handlers.PostStudent;

import javax.sql.DataSource;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class App {

    private static final String CONNECTION_STRING_ENV_VAR_NAME = "PGCS";

    private final Router router;
    private final AtomicBoolean exitSwitch;

    private App(Router router, AtomicBoolean exitSwitch) {
        this.router = router;
        this.exitSwitch = exitSwitch;
    }

    public static App build() throws InfrastructureException {
        Router router = new Router();
        AtomicBoolean exitSwitch = new AtomicBoolean();
        DataSource ds = buildDataSource();
        router.add(Method.GET, PathTemplate.of("/students"), new GetStudents(ds));
        router.add(Method.POST, PathTemplate.of("/students"), new PostStudent(ds));
        router.add(Method.EXIT, PathTemplate.of("/"), new Exit(exitSwitch));

        return new App(router, exitSwitch);
    }

    private static DataSource buildDataSource() throws InfrastructureException {
        String connectionString = System.getenv(CONNECTION_STRING_ENV_VAR_NAME);
        if (connectionString == null) {
            throw new InfrastructureException(String.format("'%s' enviroment variable is missing",
              CONNECTION_STRING_ENV_VAR_NAME));
        }
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setUrl(connectionString);
        return ds;
    }

    void run() {
        Scanner sc = new Scanner(System.in);
        while (!exitSwitch.get()) {
            System.out.print("$ ");
            String line = sc.nextLine();
            try {
                CommandRequest commandRequest = CommandRequest.parse(line);
                Optional<Router.Result> maybeResult = router.find(commandRequest);
                if (maybeResult.isPresent()) {
                    Router.Result result = maybeResult.get();
                    Parameters prms = commandRequest.getParameters().join(result.getPathParameters().getMap());
                    CommandResult cmdResult = result.getHandler().execute(prms);
                    cmdResult.printTo(System.out);
                    continue;
                }
                System.out.println("Command not found");
            } catch (CommandRequestParseException e) {
                System.out.println("Invalid command syntax");
            } catch (ParameterException e) {
                System.out.println("Invalid parameters");
            } catch (InfrastructureException e) {
                System.out.println("Application is not available, please try again later");
            } catch (CommandHandlerException e) {
                System.out.println("Unexpected error, sorry!");
            }
        }
    }

    public static void main(String[] args) {
        try {
            App app = App.build();
            app.run();
        } catch (InfrastructureException e) {
            System.out.println(String.format("Sorry, however the application cannot run: %s", e.getMessage()));
        }
    }
}
