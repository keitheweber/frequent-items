package frequent.items.processing;

import frequent.items.io.BasketsFile;
import frequent.items.models.Basket;

import java.io.IOException;
import java.util.List;

public class CreateInputFile {

    public static void createFile(String file, int numberOfBaskets) throws IOException {
        List<Basket> baskets = GenerateRandomBaskets.generateBaskets(numberOfBaskets);
        BasketsFile.writeFile(baskets, file);
    }
}
