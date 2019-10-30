package pt.isel.ls.html;

import java.io.IOException;
import java.io.Writer;

public class ListNode implements Node {

    private final Node[] nodes;

    public ListNode(Node... nodes) {
        this.nodes = nodes;
    }

    @Override
    public void writeTo(Writer writer) throws IOException {
        for (Node node : nodes) {
            node.writeTo(writer);
        }
    }
}
