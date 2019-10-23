package pt.isel.ls.commands.exceptions;

public class ParameterException extends CommandHandlerException {

    // To ensure the hierarchy is closed
    private ParameterException() {

    }

    public static class MultipleValuesParameterException extends ParameterException {
    }

    public static class InvalidParameterException extends ParameterException {
    }

    public static class MissingParameterException extends ParameterException {
    }
}
