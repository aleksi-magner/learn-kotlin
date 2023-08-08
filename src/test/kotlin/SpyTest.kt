import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import io.mockk.every
import io.mockk.spyk

class SpyTest {
    @Test
    @DisplayName("Check add")
    fun add() {
        val example = spyk(Example())

        every { example.method1() } returns "spy1"

        assertEquals("spy1", example.method1()) // Returns "spy1"

        assertEquals("method2", example.method2()) // Returns "method2"
    }
}
