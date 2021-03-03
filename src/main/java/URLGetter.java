import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class use BufferedReader for input URL from keyboard
 */
public class URLGetter {

    public String getURL() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String readURL = reader.readLine();
        if (readURL == null || readURL.isEmpty()) {
            throw new IllegalArgumentException("URL suppose to be not empty");
        }
        return readURL;
    }
}
