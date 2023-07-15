import java.io.IOException;
import java.net.*;

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
