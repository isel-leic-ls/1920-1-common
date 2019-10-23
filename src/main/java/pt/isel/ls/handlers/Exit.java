package pt.isel.ls.handlers;

import pt.isel.ls.commands.CommandHandler;
import pt.isel.ls.commands.CommandResult;
import pt.isel.ls.commands.exceptions.ParameterException;
import pt.isel.ls.commands.Parameters;
import pt.isel.ls.results.StringCommandResult;

import java.util.concurrent.atomic.AtomicBoolean;

public class Exit implements CommandHandler {

    private final AtomicBoolean exitSwitch;

    public Exit(AtomicBoolean exitSwitch) {

        this.exitSwitch = exitSwitch;
    }

    @Override
    public CommandResult execute(Parameters prms) throws ParameterException {
        exitSwitch.set(true);
        return StringCommandResult.builder("exiting...").build();
    }
}
