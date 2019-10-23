package pt.isel.ls.results;

import pt.isel.ls.commands.CommandResult;

import java.io.PrintStream;

public class OkResult implements CommandResult {
    @Override
    public void printTo(PrintStream ps) {
        ps.println("Command executed successfully");
    }
}
