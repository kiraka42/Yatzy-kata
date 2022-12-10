import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SumSameElementByValueTest {
    @Test
    public void should_test_sumElementsByValueWithLimits_when_there_is_no_occurence() {
    //GIVEN
        Dices dices = new Dices(1,2,3,4,5);
    //WHEN
        int resultScore = dices.sumElementsByValueWithLimits(dices.getDices(), 1,1);
    //THEN
        assertThat(resultScore).isEqualTo(0);
    }

    @Test
    public void should_test_sumElementsByValueWithLimits_when_filter_contains_one_element() {
        //GIVEN
        Dices dices = new Dices(1,1,3,4,5);
        //WHEN
        int resultScore = dices.sumElementsByValueWithLimits(dices.getDices(), 2,2);
        //THEN
        assertThat(resultScore).isEqualTo(0);
    }
}
