package pt.isel.ls.html;

import com.google.common.html.HtmlEscapers;

import java.io.IOException;
import java.io.Writer;

public class Text implements Node {

    private final String text;

    public Text(String text) {

        this.text = text;
    }

    @Override
    public void writeTo(Writer writer) throws IOException {
        writer.write(HtmlEscapers.htmlEscaper().escape(text));
    }
}
