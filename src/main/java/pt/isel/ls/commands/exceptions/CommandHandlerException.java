package pt.isel.ls.commands.exceptions;

public class CommandHandlerException extends Exception {

    public CommandHandlerException(Exception cause) {
        super(cause);
    }

    public CommandHandlerException(String msg) {
        super(msg);
    }

    public CommandHandlerException() {
        super();
    }
}
