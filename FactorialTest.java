import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void testFactorial(int input) {
        assertEquals(factorial(input), Factorial.factorial(input));
    }

    private long factorial(int n) {
        switch (n) {
            case 0:
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 6;
            case 4:
                return 24;
            case 5:
                return 120;
            default:
                throw new UnsupportedOperationException("Не поддерживаемое значение n: " + n);
        }
    }

    @Test
    void negativeInputThrowsException() {
        int n = -1;
        assertThrows(IllegalArgumentException.class, () -> Factorial.factorial(n));
    }
}