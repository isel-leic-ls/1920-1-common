package pt.isel.ls.commands;

import pt.isel.ls.MediaType;

import java.io.IOException;
import java.io.PrintStream;

public interface CommandResult {
    
    void printTo(PrintStream ps) throws IOException;

    MediaType getMediaType();
}
