import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FactorialTest {

    @DataProvider(name = "factorialData")
    public Object[][] createData() {
        return new Object[][]{
                {0, 1L},
                {1, 1L},
                {2, 2L},
                {3, 6L},
                {4, 24L},
                {5, 120L},
                {-1, null}, // Проверка исключения
        };
    }

    @Test(dataProvider = "factorialData")
    public void testFactorial(int input, Long expected) {
        if (expected == null) {
            try {
                Factorial.factorial(input);
                fail("Expected an exception but none was thrown.");
            } catch (IllegalArgumentException e) {
                assertTrue(true); // Исключение было успешно вызвано
            }
        } else {
            long actual = Factorial.factorial(input);
            assertEquals(actual, expected);
        }
    }
}