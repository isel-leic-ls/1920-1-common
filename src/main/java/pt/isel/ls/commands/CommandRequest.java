package pt.isel.ls.commands;

import pt.isel.ls.commands.exceptions.CommandRequestParseException;

public class CommandRequest {

    private final Method method;
    private final Path path;
    private final Parameters parameters;

    public CommandRequest(Method method, Path path, Parameters parameters) {
        this.method = method;
        this.path = path;
        this.parameters = parameters;
    }

    public static CommandRequest parse(String requestString) throws CommandRequestParseException {
        requestString = requestString.trim();
        String[] parts = requestString.split(" ");
        if (parts.length < 2 || parts.length > 3) {
            throw new CommandRequestParseException.InvalidFormat();
        }
        Method method = Method.parse(parts[0]).orElseThrow(CommandRequestParseException.InvalidMethod::new);
        Path path = Path.parse(parts[1]).orElseThrow(CommandRequestParseException.InvalidPath::new);
        if (parts.length == 2) {
            return new CommandRequest(method, path, Parameters.empty());
        }
        Parameters prms = Parameters.parse(parts[2]).orElseThrow(CommandRequestParseException.InvalidParameters::new);
        return new CommandRequest(method, path, prms);
    }

    public Method getMethod() {
        return method;
    }

    public Path getPath() {
        return path;
    }

    public Parameters getParameters() {
        return parameters;
    }
}
