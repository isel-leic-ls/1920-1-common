package pt.isel.ls.commands.exceptions;

public class InfrastructureException extends CommandHandlerException {

    public InfrastructureException(Exception cause) {
        super(cause);
    }

    public InfrastructureException(String msg) {
        super(msg);
    }

}
