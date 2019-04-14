package frequent.items.processing;

import frequent.items.models.Basket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class PCY {
    private static final Logger LOGGER = LoggerFactory.getLogger(APriori.class);
    private int support;
    private List<Basket> baskets;
    private List<Integer> frequentItems;
    private List<TreeSet<Integer>> frequentPairs;

    private int[] itemCounts;
    private BitSet bitSet;

    public List<TreeSet<Integer>> getFrequentPairs() {
        return frequentPairs;
    }


    public PCY(int support, List<Basket> baskets, int maxItem) {
        this.support = support;
        this.baskets = baskets;
        itemCounts = new int[maxItem+1];
        for (int i = 0; i < itemCounts.length; i++) {
            itemCounts[i] = 0;
        }
    }

    public void computeFrequentPairs() {
        firstPass();
        secondPass();
    }

    private void firstPass() {
        int[] buckets = new int[itemCounts.length];

        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = 0;
        }
        for (Basket basket : baskets) {
            for (Integer item : basket.getItems()) {
                itemCounts[item]++;
            }
            List<Integer> items = new ArrayList<>(basket.getItems());
            for (int i = 0; i < items.size(); i++) {
                for (int j = i + 1; j < items.size(); j++) {
                    int[] pair = new int[]{items.get(i), items.get(j)};
                    int index = pair.hashCode() % buckets.length;
                    buckets[index]++;
                }
            }
        }

        bitSet = new BitSet(itemCounts.length);
        for (int i = 0; i < buckets.length; i++) {
            bitSet.set(i, buckets[i] >= support);
        }

        frequentItems = new ArrayList<>();
        for (int i = 0; i < itemCounts.length; i++) {
            if (itemCounts[i] >= support) {
                frequentItems.add(i);
            }
        }
    }

    private void secondPass() {
        Map<List<Integer>, Integer> candidates = new HashMap<>();
        for (Basket basket : baskets) {
            List<Integer> items = new ArrayList<>(basket.getItems());
            for (int i = 0; i < items.size(); i++) {
                for (int j = i + 1; j < items.size(); j++) {
                    int item1 = items.get(i);
                    int item2 = items.get(j);
                    int[] pair = new int[]{item1, item2};
                    int index = pair.hashCode() % bitSet.size();
                    if (itemCounts[item1] >= support && itemCounts[item2] >= support && bitSet.get(index)) {
                        int count;
                        List<Integer> key = new ArrayList<>();
                        key.add(item1);
                        key.add(item2);
                        if (candidates.containsKey(key)) {
                            count = candidates.get(key) + 1;
                        } else {
                            count = 1;
                        }
                        candidates.put(key, count);
                    }
                }
            }
        }

        frequentPairs = new ArrayList<>();
        for (Map.Entry<List<Integer>, Integer> entry : candidates.entrySet()) {
            if (entry.getValue() >= support) {
                frequentPairs.add(new TreeSet<>(entry.getKey()));
            }
        }
    }
}
