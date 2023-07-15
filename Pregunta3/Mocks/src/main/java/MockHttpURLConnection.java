import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
