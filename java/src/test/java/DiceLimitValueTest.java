import exceptions.DiceLimitValueException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DiceLimitValueTest {
    @Test
    public void should_throw_exception_when_dice_value_is_not_valid() {
        Exception exception = assertThrows(DiceLimitValueException.class, () -> new Dices(22,1,2,3,4));

        assertThat(exception.getMessage()).isEqualTo("dice value should be between 1 and 6");
    }
}
