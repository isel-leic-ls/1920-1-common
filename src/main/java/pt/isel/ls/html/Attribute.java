package pt.isel.ls.html;

import java.io.IOException;
import java.io.Writer;

public final class Attribute implements Writable {

    private final String name;
    private final String value;

    public Attribute(String name, String value) {

        this.name = name;
        this.value = value;
    }

    @Override
    public void writeTo(Writer writer) throws IOException {
        writer.write(name);
        writer.write("=");
        writer.write(value);
    }
}
