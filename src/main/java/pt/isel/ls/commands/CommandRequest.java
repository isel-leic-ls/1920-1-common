package pt.isel.ls.commands;

public class CommandRequest {

    private final Method method;
    private final Path path;

    private CommandRequest(Method method, Path path) {

        this.method = method;
        this.path = path;
    }

    public static CommandRequest parse(String requestString) throws CommandRequestParseException {
        requestString = requestString.trim();
        String[] parts = requestString.split(" ");
        if (parts.length != 2) {
            throw new CommandRequestParseException.InvalidFormat();
        }
        Method method = Method.parse(parts[0]).orElseThrow(() -> new CommandRequestParseException.InvalidMethod());
        Path path = Path.of(parts[1]).orElseThrow(() -> new CommandRequestParseException.InvalidPath());
        return new CommandRequest(method, path);
    }

    public Method getMethod() {
        return method;
    }

    public Path getPath() {
        return path;
    }
}
