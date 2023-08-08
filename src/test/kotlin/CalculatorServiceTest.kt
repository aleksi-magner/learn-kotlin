import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Assertions.*
import io.mockk.MockKAnnotations
import io.mockk.mockk
import io.mockk.every
import io.mockk.impl.annotations.*

@DisplayName("Check Calculator service")
class CalculatorServiceTest {
    /**
     * Мы хотим протестировать класс с именем CalculatorService, который использует интерфейс CalculatorInterface, но мы не хотим использовать реальную реализацию калькулятора для нашего теста. Вместо этого мы хотим создать фиктивный объект Calculator с помощью MockK.
     */
    @Test
    @DisplayName("Check mocking calculator")
    fun testingMockCalculator() {
        val mockCalculator = mockk<CalculatorInterface>()

        every { mockCalculator.add(2, 3) } returns 5
        every { mockCalculator.subtract(2, 3) } returns -1

        val calculatorService = CalculatorService(mockCalculator)
        val result = calculatorService.addAndSubtract(2, 3)

        assert(result == 6) // MockK
        assertTrue(result == 6) // JUnit
    }

    /**
     * Мы хотим написать несколько тестов, чтобы убедиться, что он работает правильно, но нам нужно имитировать зависимости, на которые он опирается. В этом случае предположим, что Calculator полагается на MathService для выполнения своих вычислений.
     */
    // Создаёт mock MathService
    // @MockK
    // lateinit var mathService: MathService
    //
    // // Создаёт экземпляр CalculatorInterface, внедрив фиктивный MathService
    // @InjectMockK
    // lateinit var calculator: CalculatorInterface
    //
    // // Настраивает фиктивное поведение для MathService с помощью MockK API
    // @Before
    // fun setUp() {
    //     MockKAnnotations.init(this)
    //
    //     every { mathService.addWithMath(any(), any()) } returns 5
    // }
    //
    // // Проверяет правильно ли наш экземпляр CalculatorInterface использует смоделированный MathService.
    // @Test
    // @DisplayName("Check addWithMath method")
    // fun add() {
    //     val result = calculator.addWithMath(2, 3)
    //
    //     assertEquals(5, result)
    // }
}
