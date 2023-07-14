import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;


public class Jetty {


    public static void main(String[] args) throws Exception {

        Server server = new Server(8081);
        ServletContextHandler root = new ServletContextHandler(server, "/");
        root.setResourceBase("./pom.xml");
        root.setHandler(new ResourceHandler());
        server.setStopAtShutdown(true);
        server.start();
    }

}


