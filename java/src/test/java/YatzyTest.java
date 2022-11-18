import org.junit.*;


import static org.junit.Assert.*;

public class YatzyTest {

    @Test
    public void chance_scores_sum_of_all_dice() {
        assertEquals(15, Yatzy.chance(new Dices(2,3,4,5,1)));
    }

    @Test
    public void yatzy_scores_50() {
        assertEquals(50, Yatzy.yatzy(new Dices(6,6,6,6,6)));
        assertEquals(0, Yatzy.yatzy(new Dices(6,6,6,6,3)));
    }

    @Test
    public void test_ones() {
        assertEquals(2, Yatzy.ones(new Dices(1,2,1,4,5)));
        assertEquals(0, Yatzy.ones(new Dices(6,2,2,4,5)));
        assertEquals(5, Yatzy.ones(new Dices(1,1,1,1,1)));
    }

    @Test
    public void test_twos() {
        assertEquals(4, Yatzy.twos(new Dices(1,2,3,2,6)));
        assertEquals(10, Yatzy.twos(new Dices(2,2,2,2,2)));
    }

    @Test
    public void test_threes() {
        assertEquals(6, Yatzy.threes(new Dices(1,2,3,2,3)));
        assertEquals(12, Yatzy.threes(new Dices(2,3,3,3,3)));
    }

    @Test
    public void test_fours()
    {
        assertEquals(12, Yatzy.fours(new Dices(4,4,4,5,5)));
        assertEquals(8, Yatzy.fours(new Dices(4,4,5,5,5)));
        assertEquals(4, Yatzy.fours(new Dices(4,5,5,5,5)));
    }

    @Test
    public void test_fives() {
        assertEquals(10, Yatzy.fives(new Dices(4,4,4,5,5)));
        assertEquals(15, Yatzy.fives(new Dices(4,4,5,5,5)));
        assertEquals(20, Yatzy.fives(new Dices(4,5,5,5,5)));
    }

    @Test
    public void test_sixes() {
        assertEquals(0, Yatzy.sixes(new Dices(4,4,4,5,5)));
        assertEquals(6, Yatzy.sixes(new Dices(4,4,6,5,5)));
        assertEquals(18, Yatzy.sixes(new Dices(6,5,6,6,5)));
    }

    @Test
    public void one_pair() {
        assertEquals(0, Yatzy.score_pair(new Dices(1,4,3,5,6)));
        assertEquals(6, Yatzy.score_pair(new Dices(3,3,3,4,1 )));
        assertEquals(6, Yatzy.score_pair(new Dices(3,3,3,3,1)));
        assertEquals(8, Yatzy.score_pair(new Dices(3,3,3,4,4)));
        assertEquals(12, Yatzy.score_pair(new Dices(1,1,6,2,6)));
    }

    @Test
    public void two_Pair() {
        assertEquals(0, Yatzy.two_pair(new Dices(1,1,2,3,4)));
        assertEquals(0, Yatzy.two_pair(new Dices(3,3,3,3,1)));
        assertEquals(8, Yatzy.two_pair(new Dices(1,1,2,3,3)));
        assertEquals(16, Yatzy.two_pair(new Dices(3,3,5,5,5)));
        assertEquals(6, Yatzy.two_pair(new Dices(1,1,2,2,2)));
    }

    @Test
    public void three_of_a_kind() 
    {
        assertEquals(0, Yatzy.four_of_a_kind(new Dices(1,2,3,4,5)));
        assertEquals(0, Yatzy.three_of_a_kind(new Dices(3,3,4,5,6)));
        assertEquals(15, Yatzy.three_of_a_kind(new Dices(5,3,5,4,5)));
        assertEquals(9, Yatzy.three_of_a_kind(new Dices(3,3,3,3,5)));
    }

    @Test
    public void four_of_a_knd() {
        assertEquals(0, Yatzy.four_of_a_kind(new Dices(2,2,2,5,5)));
        assertEquals(0, Yatzy.four_of_a_kind(new Dices(1,2,3,4,5)));
        assertEquals(12, Yatzy.four_of_a_kind(new Dices(3,3,3,3,5)));
        assertEquals(20, Yatzy.four_of_a_kind(new Dices(5,5,5,4,5)));
    }

    @Test
    public void smallStraight() {
        assertEquals(15, Yatzy.smallStraight(new Dices(1,2,3,4,5)));
        assertEquals(15, Yatzy.smallStraight(new Dices(2,3,4,5,1)));
        assertEquals(0, Yatzy.smallStraight(new Dices(1,2,2,4,5)));
    }

    @Test
    public void largeStraight() {
        assertEquals(20, Yatzy.largeStraight(new Dices(6,2,3,4,5)));
        assertEquals(20, Yatzy.largeStraight(new Dices(2,3,4,5,6)));
        assertEquals(0, Yatzy.largeStraight(new Dices(1,2,2,4,5)));
    }

    @Test
    public void fullHouse() {
        assertEquals(18, Yatzy.fullHouse(new Dices(6,2,2,2,6)));
        assertEquals(0, Yatzy.fullHouse(new Dices(2,3,4,5,6)));
    }
}
