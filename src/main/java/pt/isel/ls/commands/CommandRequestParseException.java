package pt.isel.ls.commands;

public class CommandRequestParseException extends Exception {

    public static class InvalidFormat extends CommandRequestParseException {
    }

    public static class InvalidMethod extends CommandRequestParseException {
    }

    public static class InvalidPath extends CommandRequestParseException {
    }
}
