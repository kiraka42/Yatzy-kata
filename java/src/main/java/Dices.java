import exceptions.DiceLimitValueException;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


class Dices {
    private static final List<Integer> SMALL_STRAIGHT = Arrays.asList(1, 2, 3, 4, 5);
    private static final List<Integer> LARGE_STRAIGHT = Arrays.asList(2, 3, 4, 5, 6);

    private final Predicate<Dices> isThreeOfAKindScore = (dices) -> dices.threeOfAKind() != 0;
    private final Predicate<Dices> isFourOfAKindScore = (dices) -> dices.fourOfAKind() != 0;

    private final Predicate<Dices> isTwoPairScore = (dices) -> dices.twoPair() != 0;

    private final Predicate<Dices> isStraightScore = (dices) -> dices.largeStraight() != 0;

    private final Predicate<Dices> isSmallStraightScore = (dices) -> dices.smallStraight() != 0;
    private final Predicate<Dices> isYatziScore = (dices) -> dices.yatzy() != 0;


    private final Map<Predicate<Dices>, Function<Dices, Integer >> rules = new LinkedHashMap<>() {{
        put(isThreeOfAKindScore, Dices::threeOfAKind);
        put(isFourOfAKindScore, Dices::fourOfAKind);
        put(isTwoPairScore, Dices::twoPair);
        put(isStraightScore, Dices::largeStraight);
        put(isSmallStraightScore, Dices::smallStraight);
        put(isYatziScore, Dices::yatzy);
    }};
    private final List<Integer> dices;

    /**
     * Instantiates a new Dices .
     *
     * @param dice1 the dice 1
     * @param dice2 the dice 2
     * @param dice3 the dice 3
     * @param dice4 the dice 4
     * @param dice5 the dice 5
     */
    public Dices(int dice1, int dice2, int dice3, int dice4, int dice5) {
        this.dices = Arrays.asList(dice1, dice2, dice3, dice4, dice5);
        this.dices.forEach(dice -> {
            if(dice>6 || dice <0)  throw new DiceLimitValueException("dice value should be between 1 and 6");
        });
    }

    public int chance() {
        return this.dices.stream().mapToInt(Integer::intValue).sum();
    }

    public  int yatzy() {
        return this.dices.stream()
            .distinct()
            .count() <= 1 ? 50: 0;
    }

    public int ones() {
        return sumElementsByValueWithLimitsShort(this.dices,1,0);
    }

    public int twos() {
        return sumElementsByValueWithLimitsShort(this.dices,2,0);
    }

    public int threes() {
        return sumElementsByValueWithLimitsShort(dices,3,0);
    }
    public int fours() {
        return sumElementsByValueWithLimitsShort(dices,4,0);
    }

    public int fives() {
        return sumElementsByValueWithLimitsShort(dices,5,0);
    }

    public int sixes() {
        return sumElementsByValueWithLimitsShort(this.dices,6,0);
    }

    public int scorePair() {
        return sumElementsByValueWithLimitsShort(this.dices,1,2);
    }

    public int twoPair() {
        return sumElementsByValueWithLimitsShort(this.dices,2,2);
    }

    public int threeOfAKind() {
        //return sumElementsByValueWithLimits(dices,1,3);
        return sumElementsByValueWithLimitsShort(dices, 1,3);
    }

    public  int fourOfAKind() {
        return sumElementsByValueWithLimitsShort(dices,1,4);
    }

    public int smallStraight() {
        return dices.stream().sorted().collect(Collectors.toList()).equals(SMALL_STRAIGHT) ? 15: 0;
    }

    public int largeStraight() {
        return dices.stream().sorted().collect(Collectors.toList()).equals(LARGE_STRAIGHT) ? 20: 0;
    }

    public int fullHouse() {
        int threeOfKind = threeOfAKind();
      /*  if (threeOfKind != 0) {
            int sum = chance() - threeOfKind;
            if (sum % 2 == 0) {
                if (dices.contains(sum/2) && sum/2 != threeOfKind/3) {
                    return threeOfKind + sum;
                }
                return 0;
            }
        }
        return 0;*/
       // optimized verison:
        int sum = chance() - threeOfKind;
        return threeOfKind != 0 ?  (sum % 2 == 0 && dices.contains(sum/2) && sum/2 != threeOfKind/3) ? threeOfKind + sum : 0 : 0;
    }

    /**
     * short version of the main function
     * @param dices
     * @param limit
     * @param limitOnSameElement
     * @return
     */
    public int sumElementsByValueWithLimitsShort(List<Integer> dices, int limit, int limitOnSameElement) {
        List<Integer> filteredArray = dices
            .stream()
            .distinct()
            .map(element -> Collections.frequency(dices, element) >= limitOnSameElement ?
                element*limitOnSameElement: 0)
            .sorted(Comparator.reverseOrder())
            .filter(element -> element != 0)
            .collect(Collectors.toList());
        return (filteredArray.size() >= limit) ?
            filteredArray.stream().limit(limit).mapToInt(Integer::intValue).sum(): 0;
    }

    /**
     * match any rules on yatzi and sum all score
     * @return
     */
    public Integer getScore() {
        return rules.entrySet()
            .stream()
            .filter(rule -> rule.getKey().test(this))
            .map(Map.Entry::getValue)
            .map(renderAppliedRule -> renderAppliedRule.apply(this))
            .mapToInt(Integer::intValue)
            .sum();
    }
    public List<Integer> getDices() {
        return dices;
    }
//=======================================================================================
//============================First version==============================================

    /**
     * generic function to compute sum same element with limits
     * @param dices
     * @param value
     * @param limitsSum
     * @return
     */
    public int sumSameElementByValue(List<Integer> dices, int value, int limitsSum) {
        if (value % 2 == 0 && Collections.frequency(dices, value) == 1 && limitsSum != 0) return 0;
        limitsSum = limitsSum == 0 ? dices.size() : limitsSum;
        int finalLimits = limitsSum*value;
        return dices.stream()
            .filter(i -> i == value)
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
    public int sumElementsByValueWithLimits(List<Integer> dices, int limit, int limitOnSameElement) {
        Supplier<Stream<Integer>> streamSupplier
            = () -> dices
            .stream()
            .sorted(Comparator.reverseOrder())
            .distinct()
            .map(element -> sumSameElementByValue(dices, element, limitOnSameElement))
            .filter(value -> dices.contains(value / limitOnSameElement) && value % limitOnSameElement == 0
                && Collections.frequency(dices,value / limitOnSameElement) >= limitOnSameElement);

        if (dices.stream().distinct().count() == dices.size()) return 0;
        return 1 == limit || streamSupplier.get().count() != 1
            ? streamSupplier.get().limit(limit).mapToInt(Integer::intValue).sum(): 0 ;
    }
}