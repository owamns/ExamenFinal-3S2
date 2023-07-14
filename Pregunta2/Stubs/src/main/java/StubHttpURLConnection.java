import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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
