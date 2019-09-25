package pt.isel.ls;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestUtils {

    public static <E extends Exception> E expect(Class<E> clazz, ThrowableRunnable runnable) {
        try {
            runnable.run();
            fail("expected exception was not thrown");
            // will never reach here
            return null;
        } catch (Exception e) {
            assertEquals(clazz, e.getClass());
            return clazz.cast(e);
        }
    }

    @FunctionalInterface
    public interface ThrowableRunnable {
        public void run() throws Exception;
    }
}
