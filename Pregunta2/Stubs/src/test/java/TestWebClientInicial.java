import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWebClientInicial{
    static Server server;

    @BeforeAll
    public static void setUp() throws Exception {
        server = new Server(8081);
        ServletContextHandler root = new ServletContextHandler(server, "/");
        root.setResourceBase("./pom.xml");
        root.setHandler(new ResourceHandler());
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

        String fileContent = readFile("./pom.xml");

        assertEquals(fileContent, workingContent);
    }

    public String readFile(String filePath){

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    stringBuilder.append(line);
                    firstLine = false;
                } else {
                    stringBuilder.append("\n").append(line);
                }
            }
            return stringBuilder.toString();

        } catch (IOException e) {
            throw new RuntimeException("Error de lectura");
        }
    }
}