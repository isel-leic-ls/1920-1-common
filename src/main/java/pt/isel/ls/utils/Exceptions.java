package pt.isel.ls.utils;

import pt.isel.ls.UnexpectedException;

public class Exceptions {

    public static RuntimeException wrap(Exception e) {
        return new RuntimeException(e);
    }

    public static <T extends Exception> T unwrap(RuntimeException e, Class<T> eclass) {
        if (!eclass.isInstance(e.getCause())) {
            throw new UnexpectedException(e);
        }
        return eclass.cast(e.getCause());
    }

}
