import java.math.BigInteger
import java.math.BigDecimal
import java.math.RoundingMode

import kotlin.math.*

fun main() {
    // Binary operators
    /**
     * Бинарные операторы принимают два значения в качестве операндов.
     * Операнд — это значение или переменная, к которой применяется оператор.
     * Например, в выражении 1 + 3 1 и 3 — операнды, а + — оператор.
     *
     * Сложение +
     * Вычитание -
     * Умножение *
     * Деление /
     * Остаток от деления %
     */
    println(13 + 25) // 38
    println(20 + 70) // 90

    println(70 - 30) // 40
    println(30 - 70) // -40

    println(21 * 3)  // 63
    println(20 * 10) // 200

    println(8 / 3)  // 2
    println(-8 / 3)  // -2, т.к. исходно Int
    println((-8).toDouble() / 3.toDouble())  // -2.66666666665
    println(-8.0 / 3.0)  // -2.66666666665
    println(-8f / 3f)  // -2.6666667
    println(41 / 5) // 8
    println(41 / -5) // -8, т.к. исходно Int
    println((41).toDouble() / (-5).toDouble()) // -8,2
    println(8.78 / 3.97)  // 2.2115869017632237
    println(8.78 / 3)  // 2.9266666666666663

    println(10 % 3) // 1
    println(12 % 4) // 0

    println(1 + 3 * 4 - 2) // 11
    println((1 + 3) * (4 - 2)) // 8

    // Unary operators
    println(+5) // 5
    println(+(-5)) // -5

    println(-8)  // -8
    println(-(100 + 4)) // -104

    // Приоритеты
    /**
     * 1. Скобки
     * 2. Унарные операторы (знаки -, +)
     * 3. Умножение, деление, остаток от деления
     * 4. Сложение, вычитание
     */

    mathLibrary()
    bigNumbers()
    roundingModeBigNumbers()
    binary()

    sumOfDigits()
    timeDifference()
    percentageBigNumbers()
    roundAndPower()
}

/**
 * Docs: https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.math/
 *
 * Подключение всех функций: import kotlin.math.*
 * Подключение конкретной функции: import kotlin.math.pow
 *
 * Следующие функции работают с типами Int, Long, Float и Double (оба a и b должны быть одного типа):
 *
 * - abs(n) - возвращает абсолютное значение своего аргумента
 *
 * - min(a, b) - возвращает меньшее значение из двух аргументов
 *
 * - max(a, b) - возвращает большее значение двух аргументов
 *
 * - x.pow(n) - возвращает значение x, возведенное в степень n, где x может иметь значение Float или Double, а n может быть Int, Long, Float или Double.
 *
 * Работают с типами Float и Double:
 *
 * - sqrt(x) - возвращает квадратный корень своего аргумента
 *
 * - exp(x) - возвращает экспоненциальную функцию от x
 *
 * - ln(x) - возвращает натуральный логарифм x
 *
 * - log(x, base) - возвращает логарифм x по основанию
 *
 * - sin(x) - возвращает тригонометрический синус заданного угла в радианах
 *
 * - cos(x) - возвращает тригонометрический косинус заданного угла в радианах
 *
 * - tan(x) - возвращает тригонометрический тангенс заданного угла в радианах
 *
 * - hypot(a, b) - вычисление гипотенузы треугольника
 *
 * - floor(x) - возвращает наибольшее значение Double, которое меньше или равно его аргументу и равно целому числу (округление к меньшему)
 *
 * - ceil(x) - возвращает наименьшее значение Double, которое больше или равно его аргументу и равно целому числу (округление к большему)
 *
 * - round(x) - возвращает ближайшее значение Double, равное целому числу. Числа типа 3.5 или 4.5 округляются до ближайшего чётного целого числа.
 *
 * Кроме того, библиотека Math предоставляет константы:
 *
 * - E - является основанием натурального логарифма
 * - PI - представляет собой отношение длины окружности к ее диаметру
 */
fun mathLibrary() {
    println("--- Math library ---")

    println("ABS: ${abs(-10)}") // 10
    println("ABS Double: ${abs(-10.33)}") // 10.33

    println("Min: ${min(11, 81)}") // 11
    println("Max: ${max(20, 30)}") // 30

    println("Квадратный корень: ${sqrt(2.0)}") // 1.4142135623730951
    println("5^2: ${5.0.pow(2.0)}") // 25.0
    println("2^3: ${2.0.pow(3.0)}") // 8.0

    val e = E

    println("E: $e") // 2.718281828459045
    println("ln(E): ${ln(e)}") // 1.0
    println("Log 16(4): ${log(16.0, 4.0)}") // 2.0
    println("Log sum: ${ln(exp(2.0) * exp(3.0))}") // 5.0

    val pi = PI

    println("PI: $pi") // 3.141592653589793

    println("Sin: ${sin(pi / 2)}") // 1.0
    println("Cos: ${cos(pi)}") // -1.0
    println("Tan: ${tan(pi / 4)}") // 0.9999999999999999 (неточный результат)

    println("Гипотенуза: ${hypot(3.0, 4.0)}") // 5.0

    println("Floor 3.78: ${floor(3.78)}") // 3.0
    println("Floor -3.78: ${floor(-3.78)}") // -4.0

    println("Ceil 4.15: ${ceil(4.15)}") // 5.0
    println("Ceil -4.15: ${ceil(-4.15)}") // -4.0

    println("Round 4.15: ${round(4.15)}") // 4.0
    println("Round 4.15 to Int: ${round(4.15).toInt()}") // 4
    println("Round 3.5: ${round(3.5)}") // 4.0
    println("Round 4.5: ${round(4.5)}") // 4.0
    println("Round 4.51: ${round(4.51)}") // 5.0
    println("Round 4.501: ${round(4.501)}") // 5.0
    println("Round 4.75: ${round(4.75)}") // 5.0

    /**
     * Как известно, стороны равностороннего треугольника имеют одинаковую длину, а все углы равны 60°.
     *
     * Попробуем проверить это с помощью закона косинусов: c^2 = a^2 + b^2 − 2ab * cos(γ)
     * где a и b — стороны треугольника, а γ — угол между ними
     */
    val a = 6.0
    val b = 6.0

    val angle = 60
    val radianAngle = angle * PI / 180 // cos требует угла в радианах

    val c = sqrt(a.pow(2.0) + b.pow(2.0) - 2 * a * b * cos(radianAngle))

    println("Сторона треугольника: $c") // 5.999999999999999, неточный, но правильный результат

    println(heronFormula(3, 4, 5)) // 6.0
}

fun bigNumbers() {
    val a: BigInteger = (-9999999999999999).toBigInteger()
    val b: BigInteger = BigInteger("10000000000000000")
    val c: BigInteger = BigInteger.valueOf(20000000000000000)
    val d: BigInteger = "9999999999999999".toBigInteger()

    val result: BigInteger = (-a) * b + c - d

    println(result) // 100000000000000000000000000000001

    val exbibyte: BigDecimal = BigDecimal(1)
    val bits: BigDecimal = exbibyte * 2.toBigDecimal().pow(63)

    println(bits) // 9223372036854775808

    // Функция divAndRemainder возвращает массив, состоящий из двух чисел: результата целочисленного деления и остатка.
    val (division, remainder) = 110.toBigInteger().divideAndRemainder(9.toBigInteger())

    println("division: $division") // 12
    println("remainder: $remainder") // 2

    // Функция gcd возвращает наибольший общий делитель двух чисел.
    val eight: BigInteger = BigInteger.valueOf(8)
    val six: BigInteger = BigInteger.valueOf(6)

    println(eight.gcd(six)) // 2

    // Average
    val bigNumbers1 = arrayOf(
        BigDecimal(4),
        BigDecimal(2),
        BigDecimal(6)
    )

    val average1: BigDecimal = bigNumbers1.sumOf { it } / BigDecimal(bigNumbers1.size)
    val roundedAverage1 = average1.setScale(0, RoundingMode.DOWN)

    println("Average: $roundedAverage1") // 4

    val bigNumbers2 = arrayOf(
        BigDecimal("34364356335.00000001"),
        BigDecimal(-6),
        BigDecimal("100000000000000000000.000000000000001")
    )

    val average2: BigDecimal = bigNumbers2.sumOf { it } / BigDecimal(bigNumbers2.size)
    val roundedAverage2 = average2.setScale(0, RoundingMode.DOWN)

    println("Average: $roundedAverage2") // 33333333344788118776
}

/**
 * Когда нам нужно настроить точность (количество цифр после точки), на помощь приходит setScale(). Это позволяет нам регулировать точность больших дробных чисел:
 * `bigDecimal.setScale(newScale, RoundingMode)`
 *
 * Первый параметр — newScale. Он устанавливает количество цифр после десятичной точки.
 *
 * Второй параметр — roundingMode — позволяет нам управлять режимом округления.
 *
 * Чтобы его использовать, необходимо выполнить импорт: `import java.math.RoundingMode`
 *
 * В таблице ниже перечислены все возможные режимы округления BigDecimal вместе с их краткими описаниями.
 *
 * - CEILING - Округление в сторону положительной бесконечности. Если результат положительный, поведение аналогично UP; если оно отрицательное, ведёт себя как DOWN
 *
 * - DOWN - Округление к нулю. Никогда не увеличивает цифру до отброшенной дроби (т.е. усекает)
 *
 * - FLOOR - Округление в сторону отрицательной бесконечности. Если результат положительный, поведение аналогично DOWN; если оно отрицательное, ведёт себя как UP
 *
 * - HALF_DOWN - Округление в сторону «ближайшего соседа», если оба соседа не равноудалены, в этом случае округление в меньшую сторону. Ведёт себя так же, как UP, если отброшенная дробь > 0.5; в противном случае ведёт себя как DOWN.
 *
 * - HALF_EVEN - Округление в сторону «ближайшего соседа», если только оба соседа не равноудалены, в этом случае округление в сторону чётного соседа. Ведёт себя так же, как HALF_UP, если цифра слева от отброшенной дроби нечётная; ведёт себя как HALF_DOWN, если она чётная. Обратите внимание, что это режим округления, который статистически минимизирует совокупную ошибку при многократном применении в последовательности вычислений. Иногда его называют «банкирским округлением» и в основном используют в США. Этот режим округления аналогичен политике округления, используемой для арифметических операций с плавающей запятой и двойной арифметики в Java.
 *
 * - HALF_UP - Округление в сторону «ближайшего соседа», если оба соседа не равноудалены, в этом случае округление в большую сторону. Ведёт себя так же, как UP, если отброшенная дробь ≥ 0.5; в противном случае ведёт себя как DOWN. Режим округления, который обычно преподают в школе.
 *
 * - UP - Округление от нуля. Всегда увеличивает цифру, предшествующую ненулевой отброшенной дроби
 *
 * - UNNECESSARY - Режим округления, позволяющий утверждать, что запрошенная операция имеет точный результат, поэтому округление не требуется. Если этот режим округления указан для операции, которая даёт неточный результат, создаётся ArithmeticException.
 *
 * Обратите внимание, что UNNECESSARY добавит к числу несущественные нули, если вы указали слишком много цифр в setScale(). Но если вы укажете слишком мало цифр, произойдет ошибка.
 */
fun roundingModeBigNumbers() {
    // Получение текущей точности числа (количество символов после запятой)
    val fractionalNumber: BigDecimal = 1234.5678.toBigDecimal()

    println(fractionalNumber.scale()) // 4

    val modes = arrayOf("CEILING", "DOWN", "FLOOR", "HALF_DOWN", "HALF_EVEN", "HALF_UP", "UP", "UNNECESSARY")

    val numbers = doubleArrayOf(3.5, 2.5, 1.6, 1.1, 1.0, -1.0, -1.1, -1.6, -2.5, -3.5)

    println()

    val header = buildString {
        append(String.format("%-11s ", "Number"))

        for (mode in modes) {
            append(String.format("| %-11s ", mode))
        }
    }

    println(header)

    numbers.forEach {
        val row = buildString {
            append(String.format("%-11s ", it))

            val bigDecimal = BigDecimal(it)

            for (mode in modes) {
                val td = try {
                    bigDecimal.setScale(0, RoundingMode.valueOf(mode)).toString()
                } catch (error: Exception) {
                    "Error"
                }

                append(String.format("| %-11s ", td))
            }
        }

        println(row)
    }

    println()
}

/**
 * Программисты используют двоичные вычисления для ускорения выполнения программ, для работы с хеш-таблицами, для обработки закодированной информации, для вычисления адресов в памяти компьютера и т.д.
 */
fun binary() {
    println("--- Binary addition ---")

    // Сложение столбиком вручную
    // Большее число сверху, меньшее снизу с выравниванием по правой стороне
    // Расчёты начинаются с единиц, двигаясь справа налево.
    // Для удобства мы также можем дополнить ведущие цифры чисел нулями.
    // 0+0=0
    // 0+1=1
    // 1+0=1
    // 1+1=10
    //
    // Примеры сложения двоичных чисел
    //  100
    // +010
    // ----
    //  110
    //
    //  1011
    // +0111
    // -----
    // 10010
    //
    //  100011100
    // +   111010
    // ----------
    // 101010110

    println("--- Binary subtraction ---")

    // Вычитание столбиком вручную
    // 0−0=0
    // 1−1=0
    // 1−0=1
    // 10−1=1
    //
    // 110
    // - 1
    // ---
    // 101
}

/**
 * Вычисляет площадь треугольника по трём его сторонам!
 */
fun heronFormula(a: Int, b: Int, c: Int): Double {
    val semiPerimeter: Double = (a + b + c) / 2.0

    return sqrt(semiPerimeter * (semiPerimeter - a) * (semiPerimeter - b) * (semiPerimeter - c))
}

/**
 * Существует трёхзначное целое число (от 100 до 999).
 *
 * Найдите сумму его цифр.
 */
fun sumOfDigits() {
    val number: Int = readln().sumOf { it.digitToInt() }

    println(number)
}

/**
 * Мы будем рассматривать два момента времени, которые произошли в один и тот же день — часы, минуты и секунды.
 *
 * Известно, что второй момент произошёл не раньше первого. Найдите, сколько секунд прошло между двумя моментами.
 *
 * Программа получает три целых числа: часы, минуты, секунды первого момента и три целых числа второго момента.
 *
 * Выведите количество секунд между этими двумя моментами.
 */
fun timeDifference() {
    val daysAmount = 2
    val seconds: MutableList<Int> = MutableList(daysAmount) { 0 }

    repeat(daysAmount) { day ->
        println("Введите с новой строки часы, минуты, секунды:")

        repeat(3) {
            seconds[day] += readln().toInt() * 60.0.pow(2 - it).toInt()
        }
    }

    println(seconds.last() - seconds.first()) // 3661
}

/**
 * Считывает два больших натуральных числа и вычисляет процентную долю каждого числа в их сумме. При расчёте следует игнорировать дробную часть процента.
 */
fun percentageBigNumbers() {
    val a1: BigInteger = "2".toBigInteger()
    val b1: BigInteger = "3".toBigInteger()

    val sum1 = a1 + b1

    println(a1 * 100.toBigInteger() / sum1) // 40
    println(b1 * 100.toBigInteger() / sum1) // 60

    val a2: BigInteger = "985703706758760345756".toBigInteger()
    val b2: BigInteger = "38573952062739408530852".toBigInteger()

    val sum2 = a2 + b2

    println(a2 * 100.toBigInteger() / sum2) // 2
    println(b2 * 100.toBigInteger() / sum2) // 97
}

/**
 * Прочтите целые числа: power и mode, а также большое рациональное число number.
 *
 * Округлите number в сторону минимального значения (floor) до десятичных знаков mode, затем возведите его в степень power и выведите результат.
 */
fun roundAndPower() {
    val power1 = 2
    val mode1 = 0
    val number1: BigDecimal = "67.45354675".toBigDecimal()

    val result = number1.setScale(mode1, RoundingMode.FLOOR).pow(power1)

    println(result) // 4489
}
