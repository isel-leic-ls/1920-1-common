package pt.isel.ls.commands.exceptions;

public class CommandRequestParseException extends Exception {

    // This ensures the hierarchy cannot be extended outside of this file
    private CommandRequestParseException() {
    }

    public static final class InvalidFormat extends CommandRequestParseException {
    }

    public static final class InvalidMethod extends CommandRequestParseException {
    }

    public static final class InvalidPath extends CommandRequestParseException {
    }

    public static final class InvalidParameters extends CommandRequestParseException {
    }
}
