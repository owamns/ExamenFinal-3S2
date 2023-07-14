import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Prueba {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080); // Puerto en el que Jetty escuchará las solicitudes

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        // Registra tu controlador personalizado
        context.addServlet(new ServletHolder(new MiControlador()), "/mi-controlador");

        server.setHandler(context);

        server.start();
        server.join();
    }
}

