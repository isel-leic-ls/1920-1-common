package pt.isel.ls.html;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public final class Element implements Node {

    private final String name;
    private final List<Attribute> attrs = new LinkedList<>();
    private final List<Node> children;

    public Element(String name, Node... children) {
        this.name = name;
        this.children = Arrays.asList(children);
    }

    public Element withAttr(String name, String value) {
        attrs.add(new Attribute(name, value));
        return this;
    }

    public Element with(Node... n) {
        children.addAll(Arrays.asList(n));
        return this;
    }

    @Override
    public void writeTo(Writer writer) throws IOException {
        writer.write("<");
        writer.write(name);
        writer.write(" ");
        for (Attribute attr : attrs) {
            attr.writeTo(writer);
            writer.write(" ");
        }
        writer.write(">");
        for (Node node : children) {
            node.writeTo(writer);
        }
        writer.write(String.format("</%s>", name));
    }
}
