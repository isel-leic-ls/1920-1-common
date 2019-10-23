package pt.isel.ls;

public final class UnexpectedException extends RuntimeException {

    public UnexpectedException(Exception e) {
        super(e);
    }
}
