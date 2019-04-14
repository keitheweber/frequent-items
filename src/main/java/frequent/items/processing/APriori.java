package frequent.items.processing;

import frequent.items.models.Basket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class APriori {

    private static final Logger LOGGER = LoggerFactory.getLogger(APriori.class);
    private int support;
    private List<Basket> baskets;
    private List<TreeSet<Integer>> frequentPairs;
    private List<Integer> frequencyPairCount;
    private int maxBasketSize = 0;

    public List<Integer> getFrequencyPairCount() {
        return frequencyPairCount;
    }

    public List<TreeSet<Integer>> getFrequentPairs() {
        return frequentPairs;
    }



    public APriori(int support, List<Basket> baskets) {
        this.support = support;
        this.baskets = baskets;
    }

    public void computeFrequentPairs() {
        maxBasketSize = getMaxBasketSize(baskets);
        List<TreeSet<Integer>> candidatePairs = getUniqueItems(baskets);

        int k = 1;

        while (candidatePairs.size() > 0 && k <= maxBasketSize) {
            List<TreeSet<Integer>> currentFrequentPairs = new ArrayList<>();
            List<Integer> currentCounts = new ArrayList<>();
            for (TreeSet<Integer> candidate : candidatePairs) {
                int count = 0;
                for (Basket basket : baskets) {
                    if (candidateExists(basket, candidate)) {
                        count++;
                    }
                }
                if (count >= support) {
//                    LOGGER.debug("Frequent pair found. Pair {}, count {}, support {}", candidate, count, support);
                    currentFrequentPairs.add(candidate);
                    currentCounts.add(count);
                } else {
//                    LOGGER.debug("Candidate pair not frequent. Pair {}, count {}, support {}", candidate, count, support);
                }
            }
            if (currentFrequentPairs.size() > 0) {
                frequentPairs = currentFrequentPairs;
                frequencyPairCount = currentCounts;
            }
            candidatePairs = getCandidates(currentFrequentPairs);
            k++;
        }
    }

    private static int getMaxBasketSize(List<Basket> baskets) {
        int max = 0;
        for (Basket basket : baskets) {
            max = Math.max(max, basket.getItems().size());
        }
        return max;
    }

    private static boolean candidateExists(Basket basket, TreeSet<Integer> candidate) {
        for (Integer item : candidate) {
            if (!basket.getItems().contains(item)) {
                return false;
            }
        }
        return true;
    }

    private static List<TreeSet<Integer>> getUniqueItems(List<Basket> baskets) {
        TreeSet<Integer> uniqueItems = new TreeSet<>();
        for (Basket basket : baskets) {
            uniqueItems.addAll(basket.getItems());
        }
//        LOGGER.debug("Unique items: ", uniqueItems);
        List<TreeSet<Integer>> uniqueItemsList = new ArrayList<>();
        for (Integer item : uniqueItems) {
            TreeSet<Integer> treeSet = new TreeSet<>();
            treeSet.add(item);
            uniqueItemsList.add(treeSet);
        }
        return uniqueItemsList;
    }

    private static List<TreeSet<Integer>> getCandidates(List<TreeSet<Integer>> frequentPairs) {
        Map<String, TreeSet<Integer>> candidatePairs = new HashMap<>();
        for (int i = 0; i < frequentPairs.size(); i++) {
            for (int j = i + 1; j < frequentPairs.size(); j++) {
                TreeSet<Integer> candidate = new TreeSet<>();

                TreeSet<Integer> nextSet = frequentPairs.get(j);
                candidate.addAll(frequentPairs.get(i));
                for (Integer val : nextSet) {
                    if (!candidate.contains(val)) {
                        candidate.add(val);
                        candidatePairs.put(candidate.toString(), candidate);
                        break;
                    }
                }
            }
        }
        return new ArrayList<>(candidatePairs.values());
    }

}
