import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * This class is used to work with the saved file
 */
public class FileParser {
    /**
     * * This constant carry expected HTML-tags
     */
    private static final List<String> CONTENT_TAGS = Arrays.asList("div", "span", "p", "title", "li", "a");

    /**
     * This method use to read in buffer downloaded HTML-file and extract text component from visible part of HTML-file
     */
    public void parsedString(String fileName, Map<String, Integer> result) {
        try (FileInputStream fis = new FileInputStream(fileName);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)) {
            String line = reader.readLine();

            while (line != null) {
                Document doc = Jsoup.parseBodyFragment(line); //Add all data, which was read in buffer, in Document
                doc.select("script, style, .hidden").remove(); //Delete notoriously invisible fields
                Elements all = doc.select("*"); // Add only suitable elements
                for (Element element : all) {
                    if (CONTENT_TAGS.contains(element.tagName())) {
                        countWords(element.text(), result);
                    }
                    if (element.tagName().equals("img")) {
                        for (Attribute attribute : element.attributes()) {
                            String attrKey = attribute.getKey();
                            if (attrKey.equals("alt")) {
                                countWords(attribute.getValue(), result);
                            }
                        }
                    }
                }
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method use to add visible text to Map and count repeated strings
     * In Map, as key will be data from HTML, and as value - number of repetitions
     */

    public static void countWords(String source, Map<String, Integer> result) {
        // Ignore blank strings
        if (StringUtils.isNotBlank(source)) {
            // Delete punctuation marks
            String[] strings = source.split("\\P{L}+");
            for (String word : strings) {
                // Ignore spaces from String[] strings
                if (StringUtils.isNotBlank(word)) {
                    Integer oldValue = result.get(word.toUpperCase());
                    if (oldValue != null) {
                        result.put(word.toUpperCase(), ++oldValue);
                    } else {
                        result.put(word.toUpperCase(), 1);
                    }
                }
            }
        }
    }
}
