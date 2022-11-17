import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Dices {
    private static final List<Integer> SMALL_STRAIGHT = Arrays.asList(1, 2, 3, 4, 5);
    private static final List<Integer> LARGE_STRAIGHT = Arrays.asList(2, 3, 4, 5, 6);

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
        return Utils.sumSameElementByValue(this.dices,1,0);
    }

    public int twos() {
        return Utils.sumSameElementByValue(this.dices,2,0);
    }

    public int threes() {
        return Utils.sumSameElementByValue(dices,3,0);
    }
    public int fours() {
        return Utils.sumSameElementByValue(dices,4,0);
    }

    public int fives() {
        return Utils.sumSameElementByValue(dices,5,0);
    }

    public int sixes() {
        return Utils.sumSameElementByValue(this.dices,6,0);
    }

    public int scorePair() {
        return Utils.sumElementsByValueWithLimits(this.dices,1,2);
    }

    public int twoPair() {
        return Utils.sumElementsByValueWithLimits(this.dices,2,2);
    }

    public int threeOfAKind() {
        return Utils.sumElementsByValueWithLimits(dices,1,3);
    }

    public  int fourOfAKind() {
        return Utils.sumElementsByValueWithLimits(dices,1,4);
    }

    public int smallStraight() {
        return dices.stream().sorted().collect(Collectors.toList()).equals(SMALL_STRAIGHT) ? 15: 0;
    }

    public int largeStraight() {
        return dices.stream().sorted().collect(Collectors.toList()).equals(LARGE_STRAIGHT) ? 20: 0;
    }

    public int fullHouse() {
        int threeOfKind = threeOfAKind();
        int twoOfKind = Utils.sumElementsByValueWithLimits(dices,1,2);
        return threeOfKind != 0 && twoOfKind != 0 ? threeOfKind + twoOfKind : 0;
    }
}