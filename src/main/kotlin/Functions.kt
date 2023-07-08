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

    val (value1, operator, value2) = readln().split(" ")

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
fun sumTwoNumbers(a: Long, b: Long) = println(a + b)

/**
 * Вычитание
 */
fun subtractTwoNumbers(a: Long, b: Long) = println(a - b)

/**
 * Умножение
 */
fun multiplyTwoNumbers(a: Long, b: Long) = println(a * b)

/**
 * Целочисленное деление
 */
fun divideTwoNumbers(a: Long, b: Long) = println(if (b == 0L) "Division by 0!" else a / b)
