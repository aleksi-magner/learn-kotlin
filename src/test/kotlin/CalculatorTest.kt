import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

@DisplayName("Check Calculator")
class CalculatorTest {
    @Test
    @DisplayName("Check add")
    fun add() {
        assertEquals(1, Calculator.add(-1, 2))
        assertEquals(3, Calculator.add(1, 2))
        assertEquals(0, Calculator.add(0, 0))
    }

    @Test
    @Disabled
    fun `when adding 1 and 2 expect 3`() {
        val result = Calculator.add(1, 2)

        assertEquals(3, result)
    }

    @Test
    @DisplayName("Check subtract")
    fun subtract() {
        assertEquals(0, Calculator.subtract(-1, -1))
        assertEquals(1, Calculator.subtract(3, 2))
    }

    @Test
    @DisplayName("Check multiply")
    fun multiply() {
        assertEquals(6, Calculator.multiply(2, 3))
    }

    @Test
    @DisplayName("Check divide")
    fun divide() {
        assertEquals(2, Calculator.divide(6, 3))

        assertThrows<IllegalArgumentException> {
            Calculator.divide(10, 0)
        }
    }

    @Test
    @DisplayName("Check if even")
    fun isEven() {
        assertTrue(Calculator.isEven(2))
        assertFalse(Calculator.isEven(3))
    }
}
