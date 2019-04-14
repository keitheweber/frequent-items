package frequent.items.models;

import com.google.devtools.common.options.Option;
import com.google.devtools.common.options.OptionsBase;

public class CliOptions extends OptionsBase {
    @Option(
            name = "Input File",
            abbrev = 'i',
            help = "Path to input file.",
            defaultValue = ""

    )
    public String inputFile;

    @Option(
            name = "Algorithm",
            abbrev = 'a',
            help = "Algorithm: APriori or PCY",
            defaultValue = "APriori"

    )
    public String algorithm;


    @Option(
            name = "Generate input file",
            abbrev = 'g',
            help = "flag to generate input file",
            defaultValue = "false"
    )
    public boolean generateInputFile;

    @Option(
            name = "Basket count",
            abbrev = 'b',
            help = "Number of baskets to generate when generateInputFile is true",
            defaultValue = "1000"
    )
    public int numberOfBaskets;

    @Option(
            name = "Support",
            abbrev = 's',
            help = "APriori support",
            defaultValue = "2"
    )
    public int support;
}
