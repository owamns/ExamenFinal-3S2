import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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
