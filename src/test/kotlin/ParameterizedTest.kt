import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.NullAndEmptySource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.Arguments

/**
 * Нам нужно протестировать три случая: когда первый аргумент больше второго, когда первый аргумент меньше второго и когда оба аргумента равны.
 *
 * Т.к. тесты различаются только передаваемыми параметрами, можно использовать параметризованные тесты.
 *
 * Для этого нужно установить зависимость, чтобы JUnit мог работать с параметризованными тестами:
 *
 * `dependencies {
 *     testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.0")
 * }`
 *
 * Аннотация `@ParameterizedTest` позволяет нам вызывать один тестовый метод несколько раз, передавая ему разные аргументы, и используется вместо `@Test`.
 *
 * Вывод по умолчанию состоит из текущего индекса вызова и списка аргументов.
 *
 * Мы можем указать собственный формат сообщения для теста, используя атрибуты и заполнители:
 * https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests-display-names
 *
 * Например:
 * `@ParameterizedTest(name = "{index} => maxOf({0}, {1}) == {2}")`
 *
 * Аннотация `@CsvSource` для предоставления массива аргументов.
 *
 * В JUnit имеется множество аннотаций для различных источников аргументов, таких как @ValueSource, @EnumSource, @MethodSource, @CsvSource, @CsvFileSource и @ArgumentsSource:
 * https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests-sources
 *
 * - `@ValueSource` — это источник аргументов, который предоставляет массив литеральных значений для методов тестирования с одним параметром. Такие литеральные значения могут быть любого из следующих типов: Short, Byte, Int, Long, Float, Double, Char, Boolean, String и Class.
 *
 * Для непримитивных типов вы можете использовать аннотации @EmptySource, @NullSource или @NullAndEmptySource для передачи null и пустых значений.
 *
 * Вы даже можете объединить эти аннотации со значениями @ValueSource, чтобы проверить весь диапазон тестовых случаев с помощью одного метода тестирования.
 *
 * - `@MethodSource` — аннотация позволяет вам использовать метод вашего тестового класса или внешнего класса в качестве источника аргументов. Каждый такой метод должен удовлетворять следующим требованиям: он не должен принимать никаких аргументов и должен возвращать поток, массив или коллекцию аргументов.
 *
 * - `@CsvSource` — аннотация позволяет вам предоставить список аргументов в виде значений, разделённых запятыми (формат CSV):
 * `@CsvSource({ "apple, 5", "strawberry, 10", "cherry, 6" })`
 *
 * - `@CsvFileSource` — аннотация используется для загрузки CSV-файла из пути к классам или локальной файловой системы. Каждая строка из файла CSV служит источником аргументов для одного вызова параметризованного теста.
 *
 * Вы можете пропустить нужное количество строк в файле, установив атрибут `numLinesToSkip`.
 *
 * Кроме того, если вы хотите, чтобы какие-либо строки в CSV-файле игнорировались, вы можете использовать символ `#` в начале соответствующих строк, чтобы закомментировать их.
 *
 * Вот пример файла CSV:
 * ```
 * String, Length
 * apple, 5
 * strawberry, 10
 * # commented line
 * cherry, 6
 * ```
 *
 * А вот пример аннотации @CsvFileSource:
 * `@CsvFileSource(resources = "/dataset.csv", numLinesToSkip = 1)`
 *
 * Таким образом, вы можете использовать большие наборы входных данных для своих тестов.
 */
class CalculatorWithMaxOf {
    fun maxOf(a: Int, b: Int): Int = if (a >= b) a else b
    fun isEven(a: Int): Boolean = a % 2 == 0
}

internal class ParameterizedCalculatorTest {
    @ParameterizedTest(name = "{index} => maxOf({0}, {1}) == {2}")
    @CsvSource("2, 1, 2", "1, 2, 2", "1, 1, 1")
    fun testMax(first: Int, second: Int, expected: Int) {
        val calculator = CalculatorWithMaxOf()
        val result: Int = calculator.maxOf(first, second)

        assertEquals(expected, result)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 2, 4, 1000])
    fun testIsEven(arg: Int) {
        assertTrue(CalculatorWithMaxOf().isEven(arg))
    }

    @ParameterizedTest
    @EmptySource
    fun testEmpty(arg: IntArray) {
        assertEquals(0, arg.size)
    }

    @ParameterizedTest
    @NullAndEmptySource
    fun testNullAndEmpty(arg: List<String?>?) {
        assertTrue(arg.isNullOrEmpty())
    }
}

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ParameterizedCalculatorTest2 {
    private fun stringFactory(): List<String> = listOf("apple", "banana", "lemon", "orange")

    @ParameterizedTest
    @MethodSource("stringFactory")
    fun testStrings(str: String) {
        assertFalse(str.isEmpty())
    }

    private fun argFactory(): List<Arguments?> = listOf(
        Arguments.arguments("apple", 5),
        Arguments.arguments("watermelon", 10)
    )

    @ParameterizedTest
    @MethodSource("argFactory")
    fun testStringLength(str: String, length: Int) {
        assertEquals(length, str.length)
    }
}
