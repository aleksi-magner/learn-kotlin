import kotlin.math.hypot

fun main() {
    /**
     * Объявление функций
     */
    sayHello()

    printStaticNumber()

    val result1 = sum(2, 5)

    println(result1) // 7

    val result2 = sum(result1, 4)

    println(result2) // 11

    println("Is positive number: ${isPositive(42)}")

    println(getLastDigit(-512))

    /**
     * Декомпозиция функций
     *
     * Подход к разделению сложной программы на ряд функций называется функциональной декомпозицией.
     *
     * Каждая функция выполняет определённую задачу, которую мы можем выполнять последовательно, чтобы получить нужные нам результаты.
     *
     * Рассматривая проблему, нам необходимо определить действия, которые будут многократно повторяться или, наоборот, выполняться по отдельности.
     *
     * Так мы получаем желаемые функции, которые легче читать, понимать, повторно использовать, тестировать и отлаживать.
     */
    calculatorWithFourFunctions()

    /**
     * Idiom with when
     */
    val colorNumber1: Int = transform1("Blue")
    val colorNumber2: Int = transform2("Yellow")

    println(colorNumber1) // 2
    println(colorNumber2) // -1

    /**
     * Аргументы по умолчанию и именованные аргументы
     */
    printLine() // пустая строка, как println()
    printLine("Kotlin") // "Kotlin" с завершающим символом новой строки
    printLine("Hello, Kotlin", "!!!\n") // "Hello, Kotlin!!!"
    printLine(end = "!!!\n", line = "Hello, Kotlin") // "Hello, Kotlin!!!"

    println(perimeter()) // 0.0
    println(perimeter(0.0, 0.0, 12.0, 0.0, 0.0, 5.0)) // 30.0
}

/**
 * Idiom.
 * The function just returns 3
 */
fun get3(): Int = 3

/**
 * The function returns its argument
 */
fun identity(a: Int): Int {
    return a
}

/**
 * Idiom.
 * The function returns the sum of two Ints
 */
fun sum(a: Int, b: Int): Int = a + b

/**
 * Print static numbers
 */
fun printStaticNumber() {
    println(get3()) // 3
    println(identity(1000)) // 1000
}

// Idiom
fun sayHello(): Unit = println("Hello")

// Idiom
fun isPositive(number: Int): Boolean = number > 0

/**
 * Возвращает последнее число
 */
fun getLastDigit(number: Int): Char = number.toString().last()

fun transform1(color: String): Int {
    return when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> 2
        else -> -1
    }
}

fun transform2(color: String): Int = when (color) {
    "Red" -> 0
    "Green" -> 1
    "Blue" -> 2
    else -> -1
}

fun calculatorWithFourFunctions() {
    print("Calc: ")

    val (_, value1, operator, value2) = readln().split("")

    val number1: Long = value1.toLong()
    val number2: Long = value2.toLong()

    when (operator) {
        "+" -> sumTwoNumbers(number1, number2)
        "-" -> subtractTwoNumbers(number1, number2)
        "*" -> multiplyTwoNumbers(number1, number2)
        "/" -> divideTwoNumbers(number1, number2)
    }
}

/**
 * Сложение
 */
fun sumTwoNumbers(a: Long, b: Long): Unit = println(a + b)

/**
 * Вычитание
 */
fun subtractTwoNumbers(a: Long, b: Long): Unit = println(a - b)

/**
 * Умножение
 */
fun multiplyTwoNumbers(a: Long, b: Long): Unit = println(a * b)

/**
 * Целочисленное деление
 */
fun divideTwoNumbers(a: Long, b: Long): Unit = println(if (b == 0L) "Division by 0!" else a / b)

fun printLine(line: String = "", end: String = "\n"): Unit = print("$line$end")

/**
 * Функция для вычисления периметра многоугольника с 3 или 4 вершинами, представленного координатами X и Y. Вершины проходятся последовательно.
 *
 * Функция Math.hypot(x, y) для вычисления длины отрезка по теореме Пифагора.
 */
fun perimeter(
    x1: Double = 0.0,
    y1: Double = 0.0,
    x2: Double = 0.0,
    y2: Double = 0.0,
    x3: Double = 0.0,
    y3: Double = 0.0,
    x4: Double = x1,
    y4: Double = y1
): Double {
    val segment1: Double = hypot(x2 - x1, y2 - y1)
    val segment2: Double = hypot(x3 - x2, y3 - y2)
    val segment3: Double = hypot(x4 - x3, y4 - y3)
    val segment4: Double = hypot(x1 - x4, y1 - y4)

    return segment1 + segment2 + segment3 + segment4
}
