# Pregunta 2

Pregunta:

Utiliza el código Jetty.java y realiza experimentos de cómo iniciar desde Java y cómo definir una raíz de
documento (/) desde la cual comenzar a servir archivos. Si inicias el programa y escribes en el
navegador http://localhost:8081, deberías poder ver el contenido del archivo pom.xml

Obtiene un efecto similar cambiando el código para establecer la base como root.setResourceBase ("."),
reiniciando el servidor y luego navegando a http://localhost:8081/.

Los desarrolladores de la empresa escriben pruebas para verificar que pueden llamar a una URL válida
y obtener su contenido.

Estas pruebas son los primeros pasos para verificar la funcionalidad de las aplicaciones web que
interactúan con clientes externos. Ver TestWebClientInicial.java.

Respuesta:

La implementacion de la clase `Jetty` para poder visualizar el contenido del archivo `pom.xml` 
en el navegador http://localhost:8081 es la siguiente:

```
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
```
Tambien se creo una prueba en la clase `TestWebClientInicial` en la cuel se obtiene el contenido 
de la url y se verifica si se envio el contenido del archivo `pom.xml`, la prueba es la siguiente:

```
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
.
.
.
}
```

Pregunta:

Explica los resultados de ejecutar este código.
Alternativamente, podemos configurar Jetty para usar un controlador personalizado que devuelva la
cadena "Esto trabaja" en lugar de obtenerla de un archivo. Esta técnica es mucho más poderosa porque
nos permite realizar pruebas unitarias cuando el servidor HTTP remoto devuelve un código de error a
la aplicación cliente WebClient.
```
private static class TestGetContentOkHandler extends AbstractHandler {
  @Override
  public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException{
    OutputStream out = response.getOutputStream();
    ByteArrayISO8859Writer writer = new ByteArrayISO8859Writer ();
    writer.write("Esto funciona");
    writer.flush();
    response.setIntHeader(s, writer.size());
    writer.writeTo(out);
    out.flush();
  }
}
```
Respuesta:

El código escribe la cadena "Esto trabaja" en el cuerpo de la respuesta HTTP. Luego, envía la respuesta HTTP completa al cliente.

Pregunta:

Ahora que este controlador está escrito, podemos decirle a Jetty que lo use llamando a context.set-
Handler(new TestGetContentOkHandler()). Escribe una prueba llamada Testwebclient.java y explica
los resultados.

Respuesta:

La implementacion de la prueba en la clase `TestwebClient` es de la siguiente forma:
```
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
}
```
En la prueba se espera que se obtenga el String `Esto trabaja` dado que el código de la clase 
privada `TestGetContentOkHandler` escribe la cadena `Esto trabaja` en el cuerpo de la respuesta HTTP.
La prueba pasa.

Pregunta:

Analiza el uso de stubs en conexión HTTP. Se quiere probar el código de forma aislada. Las pruebas
funcionales o de integración probarán la conexión en una etapa posterior.

Sugerencia: cuando se trata de usar stub en la conexión sin cambiar el código, nos beneficiamos de las
clases Java URL y HttpURLConnection, que nos permiten conectar controladores de protocolo
personalizados para procesar cualquier tipo de protocolo de comunicación. Podemos hacer que
cualquier llamada a la clase HttpURLConnection sea redirigida a la propia clase, que devolverá lo que
necesitemos para la prueba.

Respuesta:

El uso de stubs en la conexión HTTP es una estrategia útil para probar el código de forma aislada, controlar los escenarios de respuesta y acelerar el ciclo de desarrollo. Sin embargo, es necesario complementar estas pruebas con pruebas funcionales o de integración para validar la conexión real con los servidores y garantizar un funcionamiento adecuado en un entorno de producción.

Pregunta:

Para implementar un controlador de protocolo de URL personalizado, se llama al método estático de
URL setURLStreamHandlerFactory y se le pada un URLStreamHandlerFactory personalizado. Realiza la
implementación de código auxiliar del controlador de flujo de URL de manera que cada vez que se
llama al método URL openConnection, se llama a la clase URLStreamHandlerFactory para devolver un
URLStream-Handler.

Respuesta:

La implementación de código auxiliar del controlador de flujo de URL seria de la siguiente manera:

```
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
```

Pregunta:

Realiza una implementación stub de la clase HttpURLConnection para que podamos devolver cualquier
valor que queramos para la prueba. Esta implementación simple devuelve la cadena "Esto funciona".
¿Se pasa la prueba?.

Respuesta:

La implementación stub de la clase HttpURLConnection es de la siguiente manera:
```
public class StubHttpURLConnection extends HttpURLConnection {
    private boolean connected;
    protected StubHttpURLConnection(URL u) {
        super(u);
    }
    
    @Override
    public void disconnect() {
        connected = false;
    }
    
    @Override
    public boolean usingProxy() {
        return false;
    }
    
    @Override
    public void connect() throws IOException {
        connected = true;
    }
    
    @Override
    public InputStream getInputStream() throws IOException {
        String response = "Esto funciona";
        return new ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_8));
    }
}
```
y la prueba se hace en la clase `TestWebClient`:
```
@Test
public void testHttpGet() throws IOException {
    URL.setURLStreamHandlerFactory(new StubStreamHandlerFactory());
    URL url = new URL("http://localhost:8081");
    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
    String response = reader.readLine();
    assertEquals("Esto funciona", response);
}
```
