package pt.isel.ls.results;

import pt.isel.ls.commands.CommandResult;

public class ErrorResult {

    public static CommandResult studentAlreadyExists() {
        return new StringCommandResult("Unable to perform command, student already exists");
    }
}
