import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class WebClientTest {

    @Test
    void testGetContent() {
        String urlString = "https://www.wikipedia.org/";
        String content = new WebClient().getContent(new MockURL(urlString));
        Assertions.assertNotNull(content);
        Assertions.assertNotEquals("", content);
    }

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

}