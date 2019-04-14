package frequent.items.processing;

import frequent.items.models.Basket;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;
import java.util.TreeSet;

public class GenerateRandomBasket {

    private static final Random RANDOM = new Random(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

    private static final int ITEMS_PER_BASKET_PART = 5;
    private static final int PART1_MIN = 1;
    private static final int PART1_MAX = 100;
    private static final int PART2_MIN = 101;
    private static final int PART2_MAX = 2000;

    private static TreeSet<Integer> createBasket(int min, int max, int count) {
        TreeSet<Integer> items = new TreeSet<>();
        for (int i = 0; i < count; i++) {
            int value = RANDOM.nextInt(max + 1 - min) + min;
            while (items.contains(value)) {
                value = RANDOM.nextInt(max + 1 - min) + min;
            }
            items.add(value);
        }
        return items;
    }

    public static Basket createBasket() {
        TreeSet<Integer> items = createBasket(PART1_MIN, PART1_MAX, ITEMS_PER_BASKET_PART);
        items.addAll(createBasket(PART2_MIN, PART2_MAX, ITEMS_PER_BASKET_PART));
        return new Basket(items);
    }

}
