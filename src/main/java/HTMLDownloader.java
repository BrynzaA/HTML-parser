import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * This class download and write in file HTML page from link
 */
public class HTMLDownloader {

    public void download(URL url, String fileName) {
        try {
            InputStream in = new BufferedInputStream(url.openStream());
            FileOutputStream out = new FileOutputStream(fileName);

            System.out.println("Download start");

            // Buffer intended to overload RAM
            byte[] buffer = new byte[1024];
            int count = -1;
            while ((count = in.read(buffer)) != -1) {
                out.write(buffer, 0, count);
            }

            System.out.println("Download finish");

            in.close();
            out.close();

        } catch (RuntimeException | IOException e) {
            throw new RuntimeException("Error during processing occured", e);
        }
    }
}
