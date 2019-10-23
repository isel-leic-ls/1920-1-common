package pt.isel.ls.commands;

import pt.isel.ls.commands.exceptions.CommandHandlerException;

public interface CommandHandler {

    CommandResult execute(Parameters parameters) throws CommandHandlerException;

}
