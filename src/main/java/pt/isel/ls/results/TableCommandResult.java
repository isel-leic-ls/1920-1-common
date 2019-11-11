package pt.isel.ls.results;

import pt.isel.ls.MediaType;
import pt.isel.ls.commands.CommandResult;
import pt.isel.ls.html.Node;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static pt.isel.ls.html.Dsl.body;
import static pt.isel.ls.html.Dsl.h;
import static pt.isel.ls.html.Dsl.head;
import static pt.isel.ls.html.Dsl.html;
import static pt.isel.ls.html.Dsl.map;
import static pt.isel.ls.html.Dsl.t;
import static pt.isel.ls.html.Dsl.table;
import static pt.isel.ls.html.Dsl.td;
import static pt.isel.ls.html.Dsl.th;
import static pt.isel.ls.html.Dsl.title;
import static pt.isel.ls.html.Dsl.tr;

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
    public void printTo(PrintStream ps) throws IOException {
        Node html = html(
            head(title("The title")), // TODO
            body(
                h(1, t("The heading")), // TODO
                table(
                    tr(
                        map(headings, heading -> th(heading)),
                        map(rows, row -> tr(map(row, cell -> td(cell))))
                    )
                )
            )
        );
        try (PrintWriter writer = new PrintWriter(ps)) {
            html.writeTo(writer);
        }
    }

    @Override
    public MediaType getMediaType() {
        return MediaType.TEXT_HTML;
    }

    public String[] getHeadings() {
        return headings;
    }

    public List<String[]> getRows() {
        return rows;
    }
}
