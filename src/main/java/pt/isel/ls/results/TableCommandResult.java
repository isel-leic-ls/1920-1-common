package pt.isel.ls.results;

import pt.isel.ls.commands.CommandResult;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class TableCommandResult implements CommandResult {

    private final String[] headings;
    private final List<String[]> rows = new ArrayList<>();

    public TableCommandResult(String... headings) {

        this.headings = headings;
    }

    public TableCommandResult addLine(String... line) {
        if (line.length != headings.length) {
            throw new IllegalArgumentException("invalid line size");
        }
        rows.add(line);
        return this;
    }

    @Override
    public void printTo(PrintStream ps) {
        StringBuilder sb = new StringBuilder();
        for (String heading : headings) {
            sb.append(heading).append(" | ");
        }
        sb.append("\n------------------------\n");
        for (String[] row : rows) {
            for (String cell : row) {
                sb.append(cell).append(" | ");
            }
            sb.append("\n");
        }
        ps.print(sb.toString());
    }

    public String[] getHeadings() {
        return headings;
    }

    public List<String[]> getRows() {
        return rows;
    }
}
