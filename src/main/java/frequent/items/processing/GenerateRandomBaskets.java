package frequent.items.processing;

import frequent.items.models.Basket;

import java.util.ArrayList;
import java.util.List;

public class GenerateRandomBaskets {

    public static List<Basket> generateBaskets(int count) {
        List<Basket> baskets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            baskets.add(GenerateRandomBasket.createBasket());
        }
        return baskets;
    }
}
