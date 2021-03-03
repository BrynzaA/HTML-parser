import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * This class is an entry point to program
 */
public class Application {

    public static void main(String[] args) throws IOException {
        String fileName = "loaded.html";
        Map<String, Integer> result = new TreeMap<>();

        System.out.println("Insert URL");

        URLGetter urlGetter = new URLGetter();
        URL url = new URL(urlGetter.getURL());


        HTMLDownloader downloader = new HTMLDownloader();
        downloader.download(url, fileName);

        FileParser fileParser = new FileParser();
        fileParser.parsedString(fileName, result);

        ResultPrinter output = new ResultPrinter();
        output.output(result);

    }
}
