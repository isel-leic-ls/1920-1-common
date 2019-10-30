package pt.isel.ls.html;

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static pt.isel.ls.html.Dsl.body;
import static pt.isel.ls.html.Dsl.h;
import static pt.isel.ls.html.Dsl.head;
import static pt.isel.ls.html.Dsl.html;
import static pt.isel.ls.html.Dsl.map;
import static pt.isel.ls.html.Dsl.p;
import static pt.isel.ls.html.Dsl.t;
import static pt.isel.ls.html.Dsl.table;
import static pt.isel.ls.html.Dsl.td;
import static pt.isel.ls.html.Dsl.th;
import static pt.isel.ls.html.Dsl.title;
import static pt.isel.ls.html.Dsl.tr;


public class DslTests {

    // Non-unsupervised tests :(

    @Test
    public void test0() throws IOException {
        try (Writer wr = new FileWriter("index.html")) {
            Node node =
                html(
                    head(title("the title")),
                    body(
                        table(
                            tr(th("Number"), td("Name")),
                            map(students0, s -> tr(
                                td(Integer.toString(s.getNumber())),
                                td(s.getName())))
                        ).withAttr("border", "1")
                    )
                );
            node.writeTo(wr);
        }
    }

    private static Function<Node, Node> layout0(String title, String heading) {
        return content -> html(
            head(title(title)),
            body(
                h(1, t(heading)),
                content,
                p(t("some footer"))
            )
        );
    }

    private static Node studentTable(List<Student> students) {
        return table(
            tr(th("Number"), th("Name")),
            map(students, s -> tr(
                td(Integer.toString(s.getNumber())),
                td(s.getName())))
        ).withAttr("border", "1");
    }

    private void write(String fileName, Node node) throws IOException {
        try (Writer wr = new FileWriter(fileName)) {
            node.writeTo(wr);
        }
    }

    @Test
    public void test1() throws IOException {
        Function<Node, Node> layout = layout0("The title", "Some heading");
        write("table1.html", layout.apply(studentTable(students0)));
        write("table2.html", layout.apply(studentTable(students1)));
    }


    private static class Student {
        private final int number;
        private final String name;

        public Student(int number, String name) {

            this.number = number;
            this.name = name;
        }

        public int getNumber() {
            return number;
        }

        public String getName() {
            return name;
        }
    }

    private final List<Student> students0 = Arrays.asList(
        new Student(123, "Alice"),
        new Student(456, "Bruno"));

    private final List<Student> students1 = Arrays.asList(
        new Student(789, "Carol"),
        new Student(987, "David"),
        new Student(222, "Eleanor"));

}
