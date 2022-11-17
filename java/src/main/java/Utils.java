import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Utils {
    /**
     * generic function to compute sum same element with limits
     * @param dices
     * @param n
     * @param limits
     * @return
     */
    public static int sumSameElementByValue(List<Integer> dices, int n, int limits) {
        limits = limits == 0 ? dices.size() : limits;
        int finalLimits = limits*n;
        return dices.stream()
            .filter(i -> i == n)
            .mapToInt(i -> i)
            .reduce(0, (a, b) -> a+b > finalLimits ? a : a + b);
    }

    /**
     * generic function uses for n of kind function with theirs limits
     * @param dices
     * @param limit
     * @param limitOnSameElement
     * @return
     */
    public static int sumElementsByValueWithLimits(List<Integer> dices, int limit, int limitOnSameElement) {
        return dices.stream().distinct().collect(Collectors.toList()).size() == dices.size() ? 0:
            dices
                .stream()
                .sorted(Comparator.reverseOrder())
                .distinct()
                .map(element -> sumSameElementByValue(dices, element, limitOnSameElement))
                .filter(value -> dices.contains(value / limitOnSameElement) && value % limitOnSameElement == 0)
                .limit(limit).mapToInt(Integer::intValue).sum();
    }

}