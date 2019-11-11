package pt.isel.ls;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;


public class FirstHttpServer {

    /*
     * TCP port where to listen.
     * Standard port for HTTP is 80 but might be already in use
     */
    private static final int LISTEN_PORT = 8080;

    public static void main(String[] args) throws Exception {

        System.out.println("Starting main...");

        String portDef = System.getenv("PORT");
        int port = portDef != null ? Integer.valueOf(portDef) : LISTEN_PORT;
        System.out.println("Listening on port " + port);

        Server server = new Server(port);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        ExampleServlet servlet = new ExampleServlet();
        handler.addServletWithMapping(new ServletHolder(servlet), "/*");
        server.start();

        System.out.println("Server started");
        server.join();

        System.out.println("main ends.");
    }

    private static class ExampleServlet extends HttpServlet {

        @Override
        protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
            System.out.println(
                String.format("[%s]Request received with method '%s' and path '%s'",
                    Thread.currentThread().getName(),
                    request.getMethod(),
                    request.getPathInfo()));

            try {
                Thread.sleep(60 * 1000);
            } catch (InterruptedException e) {
                // ignoring it
            }

            Instant now = Instant.now();
            String nowString = String.format("<h1>%s</h1>", now.toString());
            byte[] nowBytes = nowString.getBytes(StandardCharsets.UTF_8);

            response.setStatus(200);
            response.setContentType("text/plain;charset=utf-8");
            response.setContentLength(nowBytes.length);

            System.out.println("ending request");
            response.getOutputStream().write(nowBytes);
        }
    }
}
