import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Utils {
    /**
     * generic function to compute sum same element with limits
     * @param dices
     * @param n
     * @param limits
     * @return
     */
    public static int sumSameElementByValue(List<Integer> dices, int n, int limits) {
        if (n % 2 == 0 && Collections.frequency(dices, n) == 1 && limits != 0)) return 0;
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
        Supplier<Stream<Integer>> streamSupplier
            = () -> dices
            .stream()
            .sorted(Comparator.reverseOrder())
            .distinct()
            .map(element -> sumSameElementByValue(dices, element, limitOnSameElement))
            .filter(value -> dices.contains(value / limitOnSameElement) && value % limitOnSameElement == 0);

        return dices.stream().distinct().count() == dices.size() ? 0:
            1 == limit || streamSupplier.get().count() != 1  ? streamSupplier.get().limit(limit).mapToInt(Integer::intValue).sum(): 0 ;
    }

}
