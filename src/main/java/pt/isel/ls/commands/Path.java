package pt.isel.ls.commands;

import java.util.Optional;

public class Path {

    private static final Optional<Path> root = Optional.of(new Path(new String[0]));

    private final String[] segments;

    private Path(String[] segments) {

        this.segments = segments;
    }

    public int getSize() {
        return segments.length;
    }

    public String getSegmentAt(int i) {
        return segments[i];
    }

    public static Optional<Path> of(String value) {
        if (value == null || value.equals("")) {
            return Optional.empty();
        }
        // needs to start with a '/' and not end with a '/'
        if (value.equals("/")) {
            return root;
        }
        if (value.charAt(0) != '/' || (value.length() > 1 && value.charAt(value.length() - 1) == '/')) {
            return Optional.empty();
        }
        return Optional.of(new Path(value.substring(1).trim().split("/")));
    }

}
