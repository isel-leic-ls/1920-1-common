package pt.isel.ls.html;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Dsl {

    public static Element html(Node... nodes) {
        return new Element("html", nodes);
    }

    public static Element head(Node... nodes) {
        return new Element("head", nodes);
    }

    public static Element title(String title) {
        return new Element("title", new Text(title));
    }

    public static Element body(Node... nodes) {
        return new Element("body", nodes);
    }

    public static Element h(int n, Node... nodes) {
        return new Element("h" + n, nodes);
    }

    public static Element table(Node... nodes) {
        return new Element("table", nodes);
    }

    public static Element tr(Node... nodes) {
        return new Element("tr", nodes);
    }

    public static Element th(String s) {
        return new Element("th", t(s));
    }

    public static Element td(String s) {
        return new Element("td", t(s));
    }

    public static Text t(String t) {
        return new Text(t);
    }

    public static Element ul(Node... nodes) {
        return new Element("ul", nodes);
    }

    public static Element li(Node... nodes) {
        return new Element("li", nodes);
    }

    public static Element p(Node... nodes) {
        return new Element("p", nodes);
    }

    public static Element emph(Node... nodes) {
        return new Element("em", nodes);
    }


    public static <T> ListNode map(List<T> elems, Function<T, Node> mapper) {
        return new ListNode(elems.stream().map(mapper).toArray(n -> new Node[n]));
    }

    public static <T> ListNode map(T[] elems, Function<T, Node> mapper) {
        return new ListNode(Arrays.asList(elems).stream().map(mapper).toArray(n -> new Node[n]));
    }

    public static Node[] flatMap(Node[]... lists) {
        ArrayList<Node> l = new ArrayList<>();
        for (Node[] list : lists) {
            l.addAll(Arrays.asList(list));
        }
        return l.toArray(new Node[0]);
    }

    public static String[] headers(String... names) {
        return names;
    }

}
