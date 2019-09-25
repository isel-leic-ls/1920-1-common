package pt.isel.ls.commands;

import java.util.Optional;

public enum Method {
    GET,
    POST,
    EXIT;

    public static Optional<Method> parse(String s) {
        if (s == null) {
            return Optional.empty();
        }
        try {
            return Optional.of(Method.valueOf(s));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
