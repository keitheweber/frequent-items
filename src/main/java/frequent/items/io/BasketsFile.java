package frequent.items.io;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import frequent.items.models.Basket;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class BasketsFile {

    private static final String DELIMITER = ",";
    private static final Splitter SPLITTER = Splitter.on(DELIMITER);
    private static final Joiner JOINER = Joiner.on(DELIMITER);


    public static List<Basket> readFile(String file) throws IOException {
        List<Basket> baskets = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> segments = SPLITTER.splitToList(line);
                TreeSet<Integer> items = new TreeSet<>();
                for (String segment : segments) {
                    items.add(Integer.parseInt(segment));
                }
                if (items.size() > 0) {
                    baskets.add(new Basket(items));
                }
            }
        }
        return baskets;
    }

    public static void writeFile(List<Basket> baskets, String file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
            for (Basket basket : baskets) {
                writer.write(JOINER.join(basket.getItems()) + System.lineSeparator());
            }
        }
    }
}
