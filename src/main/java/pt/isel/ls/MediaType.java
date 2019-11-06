package pt.isel.ls;

public enum MediaType {

    TEXT_PLAIN("text/plain;charset=utf-8"),
    TEXT_HTML("text/html;charset=utf-8");

    private final String identifier;

    MediaType(String identifier) {
        this.identifier = identifier;
    }

    String getIdentifier() {
        return identifier;
    }
}
