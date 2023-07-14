import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.ByteArrayISO8859Writer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Testwebclient {
    static Server server;

    @BeforeAll
    public static void setUp() throws Exception {
        server = new Server(8081);
        ServletContextHandler root = new ServletContextHandler(server, "/");
        root.setHandler(new TestGetContentOkHandler());
        server.setStopAtShutdown(true);
        server.start();
    }

    @AfterAll
    public static void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void testGetContentOk() throws MalformedURLException {
        WebClient client = new WebClient();
        String workingContent = client.getContent(new URL("http://localhost:8081"));
        assertEquals("Esto trabaja", workingContent);
    }

    @Test
    public void testHttpGet() throws IOException {
        URL.setURLStreamHandlerFactory(new StubStreamHandlerFactory());
        //URL url = new URL("http://www.example.com");
        URL url = new URL("http://localhost:8081");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String response = reader.readLine();

        assertEquals("Esto funciona", response);
    }



    private static class TestGetContentOkHandler extends AbstractHandler {

        @Override
        public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
            OutputStream out = httpServletResponse.getOutputStream();
            ByteArrayISO8859Writer writer = new ByteArrayISO8859Writer ();
            writer.write("Esto trabaja");
            writer.flush();
            httpServletResponse.setIntHeader(s, writer.size());
            writer.writeTo(out);
            out.flush();
        }
    }

    private static class StubStreamHandlerFactory implements URLStreamHandlerFactory{
        @Override
        public URLStreamHandler createURLStreamHandler(String protocol) {
            return new StubHttpURLStreamHandler();
        }
    }
    private static class StubHttpURLStreamHandler extends URLStreamHandler {
        @Override
        protected URLConnection openConnection(URL url) throws IOException {
            return new StubHttpURLConnection(url);
        }
    }

}
