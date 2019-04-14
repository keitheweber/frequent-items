package frequent.items.io;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.TreeSet;

public class OutputFile {

    public static void writeFile(String file, List<TreeSet<Integer>> frequentPairs, LocalDateTime startTime) throws IOException {
        LocalDateTime endTime = LocalDateTime.now();

        long milliseconds = startTime.until(endTime, ChronoUnit.MILLIS);
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
            writer.write("Start Time: " + DateTimeFormatter.ISO_DATE_TIME.format(startTime) + System.lineSeparator());
            writer.write("End Time: " + DateTimeFormatter.ISO_DATE_TIME.format(endTime) + System.lineSeparator());
            writer.write("Total milliseconds: " + milliseconds + System.lineSeparator());
            for (int i = 0; i < frequentPairs.size(); i++) {
                writer.write("Pair: " + frequentPairs.get(i).toString() + System.lineSeparator());
            }
        }
    }
}
