# Pregunta 3

Pregunta:

Implementa código que abre una conexión HTTP a una URL determinada y lea el contenido de esa
URL. Suponga que este código es un método de una aplicación más grande que queremos probar
unitariamente (mira la clase WebClient).

Respuesta:

La implementacion de la clase `MockURl` es la siguiente:
```
public class MockURL {
    private String urlString;
    private MockHttpURLConnection mockConnection;

    public MockURL(){

    }

    public MockURL(String urlString) {
        this.urlString = urlString;
    }

    public URLConnection openConnection() throws IOException {
        if (mockConnection != null) {
            return mockConnection;
        }

        URL url = new URL(urlString);
        return url.openConnection();
    }

    public void setupOpenConnection(MockHttpURLConnection mockConnection) {
        this.mockConnection = mockConnection;
    }
}
```

La implementacion de la clase `WebClient` es la siguiente:

```
public class WebClient {
    public String getContent(MockURL url) {
        StringBuffer content = new StringBuffer();
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            InputStream is = connection.getInputStream();
            byte[] buffer = new byte[2048];
            int count;
            while (-1 != (count = is.read(buffer))) {
                content.append(new String(buffer, 0, count));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content.toString();
    }
}
```
Se hace un prueba para comprobar que el contenido obtenido no sea nulo o vacio:
```
 @Test
    void testGetContent() {
        String urlString = "https://www.wikipedia.org/";
        String content = new WebClient().getContent(new MockURL(urlString));
        Assertions.assertNotNull(content);
        Assertions.assertNotEquals("", content);
    }
```

<h1 align="center">
  <img src="https://raw.githubusercontent.com/owamns/ExamenFinal-3S2/main/Pregunta3/files/p1.png">
</h1>

Pregunta:

Prueba el método getContent independientemente de una conexión HTTP real a un servidor web. Esto
significa escribir un mock URL en el que el método url.openConnection devuelve un mock
HttpURLConnection . La clase MockHttpURLConnection proporcionaría una implementación que
permite que la prueba decida qué devuelve el método getInputStream.

Respuesta:

Dado que `MockHttpURLConnection` extiende de la clase `HttpURLConnection`, se tendran que usar sus métodos
abstractos, entonces la implementacion de la clase `MockHttpURLConnection` es la siguiente:
```
public class MockHttpURLConnection extends HttpURLConnection {
    private InputStream inputStream;

    protected MockHttpURLConnection() throws MalformedURLException {
        //super(new URL("http://www.example.com"));
        super(null);
    }

    @Override
    public void disconnect() {

    }

    @Override
    public boolean usingProxy() {
        return false;
    }

    @Override
    public void connect() throws IOException {
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return inputStream;
    }

    public void setupGetInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
```

Se hace la siguiente prueba:

```
@Test
public void testGetContentOk() throws Exception {
    MockHttpURLConnection mockConnection = new MockHttpURLConnection();
    mockConnection.setupGetInputStream(new ByteArrayInputStream("Esto funciona".getBytes()));
    MockURL mockURL = new MockURL();
    mockURL.setupOpenConnection(mockConnection);
    WebClient client = new WebClient();
    String workingContent = client.getContent(mockURL);
    assertEquals("Esto funciona", workingContent);
}
```

La prueba pasa dado que se se esperaba una respuesta "Esto funciona".
Simula una solicitud HTTP utilizando clases y métodos simulados para probar la funcionalidad de la clase WebClient
y el funcionamiento de MockHttpURLConnection.

<h1 align="center">
  <img src="https://raw.githubusercontent.com/owamns/ExamenFinal-3S2/main/Pregunta3/files/r2.png">
</h1>

Pregunta:

Refactorizar el método get-Content es una técnica que usa objetos mocks. Este método hace dos
cosas: obtiene un objeto HttpURLConnection y luego lee su contenido. Utiliza esta refactorización y
modifica la clase WebCliente por WebCliente1. Indica cual es la parte que recuperó el objeto
HttpURLConnection.

Respuesta:

Al refactorizar se separan en 3 metodos los cuales son `getContent`, `createHttpURLConnection` y `readContent`.
-createHttpURLConnection() recupera el objeto HttpURLConnection.
-readContent() retorna el contenido de la url.
-getContent() retorna el contenido de la url haciendo uso del metodo createHttpURLConnection y readContent.

Por lo tanto la implementacion de la clase `WebCliente1` es la siguiente:
```
public class WebClient1 {
    public String getContent(MockURL url) {
        HttpURLConnection connection = createHttpURLConnection(url);
        return readContent(connection);
    }

    protected HttpURLConnection createHttpURLConnection(MockURL url) {
        try {
            return (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected String readContent(HttpURLConnection connection) {
        StringBuffer content = new StringBuffer();
        try {
            connection.setDoInput(true);
            InputStream is = connection.getInputStream();
            byte[] buffer = new byte[2048];
            int count;
            while (-1 != (count = is.read(buffer))) {
                content.append(new String(buffer, 0, count));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content.toString();
    }
}
```
