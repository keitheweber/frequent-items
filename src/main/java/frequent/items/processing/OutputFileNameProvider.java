package frequent.items.processing;

import frequent.items.models.CliOptions;
import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class OutputFileNameProvider {

    public static String getOutputFileName(CliOptions options) {
        File file = new File(options.inputFile);
        return FilenameUtils.removeExtension(file.getAbsolutePath()) + "_" + options.algorithm + "_" + options.support + ".txt";
    }
}
