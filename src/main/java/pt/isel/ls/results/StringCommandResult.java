package pt.isel.ls.results;

import pt.isel.ls.commands.CommandResult;

import java.io.PrintStream;

public class StringCommandResult implements CommandResult {

    private final String str;

    public StringCommandResult(String str) {
        this.str = str;
    }

    public String getString() {
        return str;
    }

    @Override
    public void printTo(PrintStream ps) {
        ps.println(str);
    }

    public static Builder builder(String title) {
        return new Builder(title);
    }

    public static class Builder {
        private final StringBuilder sb;

        public Builder(String title) {
            sb = new StringBuilder(title).append('\n');
        }

        public Builder add(String name, String value) {
            sb.append(" ").append(name).append(':').append(value).append('\n');
            return this;
        }

        public StringCommandResult build() {
            return new StringCommandResult(sb.toString());
        }
    }
}
