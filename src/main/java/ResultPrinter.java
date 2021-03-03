import java.util.Comparator;
import java.util.Map;

/**
 * This class output counted words and sort them from most recurring to less
 */
public class ResultPrinter {

    public void output(Map<String, Integer> result) {
        if (result != null && !result.isEmpty()) {

            result.entrySet()
                    .stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEach(System.out::println);
        } else {
            System.out.println("Result is empty. Program supports only HTML files");
        }
    }
}
