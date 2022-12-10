import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;


public class YatzyTest {
    @ParameterizedTest(name = "[score sum of all dices]: should return {5} when dice has {0}, {1}, {2}, {3}, {4} dices value")
    @CsvSource({
        "2,3,4,5,1, 15",
    })
    public void should_test_yatzy_chance_scores_sum_of_all_dice(int dice1, int dice2, int dice3, int dice4, int dice5, int result) {
        // GIVEN
        Dices dices = new Dices(dice1,dice2,dice3,dice4,dice5);

        // WHEN
        int resultScore = Yatzy.chance(dices);

        // THEN
        assertThat(resultScore).isEqualTo(result);
    }
    @ParameterizedTest(name = "[score 50 rules]: should return {5} when dice has {0}, {1}, {2}, {3}, {4} dices value")
    @CsvSource({
        "6,6,6,6,6, 50",
        "5,5,5,5,5, 50",
        "6,6,6,6,3, 0",
    })
    public void should_test_yatzy_scores_50(int dice1, int dice2, int dice3, int dice4, int dice5, int result) {
        // GIVEN
        Dices dices = new Dices(dice1,dice2,dice3,dice4,dice5);

        // WHEN
        int resultScore = Yatzy.yatzy(dices);

        // THEN
        assertThat(resultScore).isEqualTo(result);
    }


    @ParameterizedTest(name = "should return {6} when dice has {1}, {2}, {3}, {4}, {5} dices value")
    @CsvSource({
        "1  ,1,2,1,4,5, 2",
        "1  ,1,1,1,1,1, 5",
        "1  ,6,2,2,4,5, 0",

        "2  ,1,2,3,2,6, 4",
        "2  ,2,2,2,2,2, 10",

        "3  ,1,2,3,2,3, 6",
        "3  ,2,3,3,3,3, 12",

        "4  ,4,4,4,5,5, 12",
        "4  ,4,4,5,5,5, 8",

        "5  ,4,4,4,5,5, 10",
        "5  ,4,4,5,5,5, 15",
        "5  ,4,5,5,5,5, 20",

        "6  ,4,4,4,5,5, 0",
        "6  ,4,4,6,5,5, 6",

    })
    public void should_test_yatzy_sum_same_element_by_value(int number, int dice1, int dice2, int dice3, int dice4, int dice5, int result) {
        // GIVEN
        Dices dices = new Dices(dice1,dice2,dice3,dice4,dice5);

        // WHEN
        int resultScore = dices.sumSameElementByValue(dices.getDices(),number, 0);

        // THEN
        assertThat(resultScore).isEqualTo(result);
    }

    @ParameterizedTest(name = "[score One Pair]: should return {5} when dice has {0}, {1}, {2}, {3}, {4} dices value")
    @CsvSource({
        "1,4,3,5,6, 0",
        "3,3,3,4,1, 6",
        "3,3,3,3,1, 6",
        "3,3,3,4,4, 8",
        "1,1,6,2,6, 12"
    })
    public void should_test_yatzy_one_pair(int dice1, int dice2, int dice3, int dice4, int dice5, int result) {
        // GIVEN
        Dices dices = new Dices(dice1,dice2,dice3,dice4,dice5);

        // WHEN
        int resultScore = Yatzy.scorePair(dices);

        // THEN
        assertThat(resultScore).isEqualTo(result);
    }


    @ParameterizedTest(name = "[score Two Pair]: should return {5} when dice has {0}, {1}, {2}, {3}, {4} dices value")
    @CsvSource({
        "1,1,2,3,4, 0",
        "3,3,3,3,1, 0",
        "1,1,2,3,3, 8",
        "3,3,5,5,5, 16",
    })
    public void should_test_yatzy_two_Pair(int dice1, int dice2, int dice3, int dice4, int dice5, int result) {
        // GIVEN
        Dices dices = new Dices(dice1,dice2,dice3,dice4,dice5);

        // WHEN
        int resultScore = Yatzy.twoPair(dices);

        // THEN
        assertThat(resultScore).isEqualTo(result);
    }


    @ParameterizedTest(name = "[score Three of a kind]: should return {5} when dice has {0}, {1}, {2}, {3}, {4} dices value")
    @CsvSource({
        "1,2,3,4,5, 0",
        "3,3,4,5,6, 0",
        "5,3,5,4,5, 15",
        "3,3,3,3,5, 9",
        "3,2,2,3,1,0",
        "1,2,2,1,3,0",
    })
    public void should_test_yatzy_three_of_a_kind(int dice1, int dice2, int dice3, int dice4, int dice5, int result) {
        // GIVEN
        Dices dices = new Dices(dice1,dice2,dice3,dice4,dice5);

        // WHEN
        int resultScore = Yatzy.threeOfAKind(dices);

        // THEN
        assertThat(resultScore).isEqualTo(result);
    }


    @ParameterizedTest(name = "[score Four of a kind]: should return {5} when dice has {0}, {1}, {2}, {3}, {4} dices value")
    @CsvSource({
        "2,2,2,5,5, 0",
        "1,2,3,4,5, 0",
        "3,3,3,3,5, 12",
        "5,5,5,4,5, 20",
    })
    public void should_test_yatzy_four_of_a_kind(int dice1, int dice2, int dice3, int dice4, int dice5, int result) {
        // GIVEN
        Dices dices = new Dices(dice1,dice2,dice3,dice4,dice5);

        // WHEN
        int resultScore = Yatzy.fourOfAKind(dices);

        // THEN
        assertThat(resultScore).isEqualTo(result);
    }


    @ParameterizedTest(name = "[score Small Straight]: should return {5} when dice has {0}, {1}, {2}, {3}, {4} dices value")
    @CsvSource({
        "1,2,3,4,5, 15",
        "2,3,4,5,1, 15",
        "1,2,2,4,5, 0",
    })
    public void should_test_yatzy_small_straight(int dice1, int dice2, int dice3, int dice4, int dice5, int result) {
        // GIVEN
        Dices dices = new Dices(dice1,dice2,dice3,dice4,dice5);

        // WHEN
        int resultScore = Yatzy.smallStraight(dices);

        // THEN
        assertThat(resultScore).isEqualTo(result);
    }

    @ParameterizedTest(name = "[score Large Straight]: should return {5} when dice has {0}, {1}, {2}, {3}, {4} dices value")
    @CsvSource({
        "6,2,3,4,5, 20",
        "2,3,4,5,6, 20",
        "1,2,2,4,5, 0",
    })
    public void should_test_yatzy_large_straight(int dice1, int dice2, int dice3, int dice4, int dice5, int result) {
        // GIVEN
        Dices dices = new Dices(dice1,dice2,dice3,dice4,dice5);

        // WHEN
        int resultScore = Yatzy.largeStraight(dices);

        // THEN
        assertThat(resultScore).isEqualTo(result);
    }

    @ParameterizedTest(name = "[score fullHouse]: should return {5} when dice has {0}, {1}, {2}, {3}, {4} dices value")
    @CsvSource({
        "6,2,2,2,6, 18",
        "2,3,4,5,6, 0",
        "2,6,6,6,2, 22",
        "1,1,3,3,3 , 11",
        "4,4,5,4,6, 0",
        "1,1,2,2,2, 8",
        "1,1,2,2,3, 0"
    })
    public void should_test_yatzy_full_house(int dice1, int dice2, int dice3, int dice4, int dice5, int result) {
        // GIVEN
        Dices dices = new Dices(dice1,dice2,dice3,dice4,dice5);

        // WHEN
        int resultScore = Yatzy.fullHouse(dices);

        // THEN
        assertThat(resultScore).isEqualTo(result);
    }

    @ParameterizedTest(name = "[FInal Score]: should return {5} when dice has {0}, {1}, {2}, {3}, {4} dices value")
    @CsvSource({
        "6,2,2,2,6, 22",
        "2,3,4,5,6, 20",
        "1,2,3,4,5, 15",
    })
    public void should_test_get_score(int dice1, int dice2, int dice3, int dice4, int dice5, int result) {
        // GIVEN
        Dices dices = new Dices(dice1,dice2,dice3,dice4,dice5);

        // WHEN
        int resultScore = dices.getScore();

        // THEN
        assertThat(resultScore).isEqualTo(result);
    }
}
