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

    divide()
    moduloDivision()

    mathLibrary()
    bigNumbers()
    roundingModeBigNumbers()
    binary()
    bitwiseAndBitShiftOperators()

    sumOfDigits()
    timeDifference()
    percentageBigNumbers()
    roundAndPower()
    leastCommonMultiple()
    quadraticEquation()
}

/**
 * По умолчанию в Котлин используется Евклидово деление и отбрасывание дробной части при использовании типа Int
 */
fun divide() {
    println("--- Division ---")

    println(8 / 3)  // 2
    println(-8 / 3)  // -2, т.к. исходно Int
    println((-8).toDouble() / 3.toDouble())  // -2.66666666665
    println(-8.0 / 3.0)  // -2.66666666665
    println(-8f / 3f)  // -2.6666667
    println(41 / 5) // 8
    println(41 / -5) // -8, т.к. исходно Int
    println((41).toDouble() / (-5).toDouble()) // -8.2
    println(8.78 / 3.97)  // 2.2115869017632237
    println(8.78 / 3)  // 2.9266666666666663

    // Когда вы разделите -12 на 5, результат будет -2.4, который округляется до -3, поскольку это ближайшее целое число, меньшее -2.4.
    // Делит это значение на другое значение, усекая результат до целого числа, близкого к нулю.
    println(-12 / 5) // -2
    println((-12).div(5)) // -2

    // Делит это значение на другое значение, сводя результат к целому числу, близкому к отрицательной бесконечности.
    println((-12).floorDiv(5)) // -3

    // Вычисляет остаток от деления этого значения (дивиденда) на другое значение (делитель).
    // Результат либо равен нулю, либо имеет тот же знак, что и делитель, и имеет абсолютное значение меньше абсолютного значения делителя.
    println((-12).mod(5)) // 3
}

fun moduloDivision() {
    // 29 % 8
    // 29 / 8 = 3.625
    //
    // dividend = (divisor * quotient) + remainder
    // remainder = dividend − (divisor * quotient)
    // remainder = 29 − (8 * 3)
    // remainder = 29 − 24
    // remainder = 5

    println("--- Modulo division with positive numbers ---")

    // 10 / 3 = 3.333
    // dividend = (divisor * quotient) + remainder
    // remainder = dividend − (divisor * quotient)
    // remainder = 10 − (3 * 3)
    // remainder = 10 − 9
    // remainder = 1
    println(10 % 3) // 1

    // 12 / 4 = 3
    // dividend = (divisor * quotient) + remainder
    // remainder = dividend − (divisor * quotient)
    // remainder = 12 − (4 * 3)
    // remainder = 12 − 12
    // remainder = 0
    println(12 % 4) // 0

    println("--- Modulo division with negative numbers ---")

    // -42 / 10 = -4.2
    // dividend = (divisor * quotient) + remainder
    // remainder = dividend − (divisor * quotient)
    // remainder = -42 − (10 * -4)
    // remainder = -42 − -40
    // remainder = -2
    println((-42) % 10) // -2

    // 42 / -10 = -4.2
    // dividend = (divisor * quotient) + remainder
    // remainder = dividend − (divisor * quotient)
    // remainder = 42 − (-10 * -4)
    // remainder = 42 − 40
    // remainder = 2
    println(42 % (-10)) // 2
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

    println("--- Decimal to binary by division ---")

    // Преобразование десятичного числа в двоичного с помощью деления.
    // Нужно делить десятичное число на 2 пока делится, а остаток и будет часть двоичного числа (с конца к началу)
    //
    // На примере числа 42 -> 101010
    //
    // Действие | Частное | Остаток
    // 42 / 2   | 21      | 0
    // 21 / 2   | 10      | 1
    // 10 / 2   | 5       | 0
    // 5 / 2    | 2       | 1
    // 2 / 2    | 1       | 0
    // 1 / 2    | 0       | 1
    //
    // 010101.reverse() -> 101010

    println("--- Decimal to binary by subtraction ---")

    // Преобразование десятичного числа в двоичного с помощью вычитания.
    // Сначала нам нужно найти наибольшую степень двойки, которая всё ещё меньше исходного числа.
    //
    // На примере числа 42 -> 101010
    //
    // Наибольшая степень двойки 32.
    // Теперь перечислим все меньшие степени двойки в одну таблицу:
    // 32 | 16 | 8 | 4 | 2 | 1
    // 1  | 0  | 1 | 0 | 1 | 0
    //
    // В первый столбец мы поместили цифру 1, потому что в 42 может поместиться только одна цифра 32.
    // 42 - 32 = 10
    // Теперь посмотрим на второй столбец и решим, сколько 16 может поместиться в результате вычитания (то есть 10). Ответ 0.
    // Следующее число 8, более перспективно: 10 больше 8.
    // 10 - 8 = 2
    // Следующее число 4, может поместиться 0 раз в 2
    // Следующее число 2, может поместиться 1 раз в 2
    // 2 - 2 = 0
    // Следующее число 1, может поместиться 0 раз в 0
}

/**
 * Побитовые операторы и операторы побитового сдвига
 *
 * Язык Kotlin предоставляет несколько операторов для управления отдельными битами целых чисел. Эти операции представляют собой быстрые и простые действия, напрямую поддерживаемые процессором.
 *
 * Они особенно важны в программировании нижнего уровня, например, в драйверах устройств, низкоуровневой графике, сетевой связи, шифровании и сжатии.
 *
 * Побитовые операторы
 *
 * - inv() (побитовое НЕ, инверсия, дополнение) — это унарный оператор, который инвертирует биты в двоичном формате числа, превращая каждый 0 в 1 и каждую 1 в 0. Он также меняет знаковый бит значения
 * Для любого положительного целого числа n инверсия n будет равна -(n+1). Например, инверсия числа 35 будет -36.
 * Для любого отрицательного числа -n инверсия равна n-1. Например, инверсия числа -35 будет 34.
 *
 * - and (побитовое И) — это бинарный оператор, выполняющий поразрядное И: цифра результата равна 1, если обе цифры операнда равны 1, в противном случае — 0
 *
 * - or (побитовое ИЛИ) — это бинарный оператор, который выполняет поразрядное ИЛИ: цифра результата равна 1, если хотя бы одна цифра операнда равна 1, в противном случае это 0
 *
 * - xor (побитовое исключающее ИЛИ) — это бинарный оператор, выполняющий побитовое исключающее ИЛИ: цифра результата равна 1, если ровно один операнд равен 1, в противном случае — 0
 *
 * Каждый из этих операторов проходит по всем битам обоих операндов (числа) один за другим (т.е. поразрядно) и в результате выдает новое число.
 *
 * Перечисленные операторы могут применяться как к целочисленным, так и к логическим операндам. Если оба операнда являются целыми числами, то будут выполняться побитовые операции. Если оба операнда являются логическими значениями, выполняются соответствующие логические операции (кроме ~).
 *
 *
 * Операторы побитового сдвига
 *
 * Используются для перемещения бит целого числа из одной позиции в другую:
 *
 * - shl сдвигает представление битов влево на определённое количество заданных битов, при этом нулевые биты сдвигаются в младшие позиции
 *
 * - shr сдвигает битовое представление вправо на определённое количество указанных битов. Он заполняет пустое место значением знакового бита (сдвигает биты вправо)
 *
 * - ushr беззнаковый оператор битового сдвига, который сдвигает битовую комбинацию вправо на определённое количество указанных битов. Это почти похоже на shr, но сдвинутые значения заполняются нулями. Результат оператора ushr всегда положительный (сдвигается вправо, но заполняет самый левый бит нулём)
 *
 *
 * Приоритет побитовых операций и операций побитового сдвига
 *
 * Подобно арифметическим операторам, побитовые операторы и операторы побитового сдвига следуют так называемым правилам приоритета, которые определяют порядок выполнения и группировки операций в выражении. Операции с более высоким приоритетом выполняются раньше операций с более низким приоритетом. Взгляните на список операций в порядке убывания приоритета:
 *
 * - Круглые скобки: (expr)
 * - Постфиксное увеличение/уменьшение: expr++, expr--
 * - Унарный плюс/минус, увеличение/уменьшение префикса: -expr, ++expr, --expr
 * - Умножение, деление и остаток от деления: *, /, %
 * - Сложение и вычитание: +, -
 * - Операции присвоения: =, +=, -=, *=, /=, %=
 * - Побитовые операторы и операторы побитового сдвига: and, or, xor, shr, shl, ushr
 *
 * Когда операторы имеют одинаковый приоритет, используется другое правило, чтобы определить, следует ли выполнять оценку слева направо или наоборот. Это называется ассоциативностью.
 *
 * Все рассмотренные нами до сих пор операторы оцениваются слева направо, то есть наиболее привычным для вас способом.
 *
 * Это означает, что в следующих двух выражениях: `first or second and third` и `first or (second and third)` операции будут выполняться в разном порядке и, следовательно, результаты могут различаться.
 *
 * Если вы вернётесь к приведённому примеру `left + right shr 1` во фрагменте кода, то увидите, что здесь нам не нужно использовать скобки, в отличие от случая его эквивалента `(left + right) / 2`, поскольку сложение имеет более высокий приоритет, чем все операции побитового сдвига. Помните об этих приоритетах, когда комбинируете арифметические операции с операциями над битами.
 */
fun bitwiseAndBitShiftOperators() {
    println("--- Bitwise operators ---")

    /**
     * Предположим, у нас есть два целых числа: 15 и 10. Первое число имеет двоичное представление 1111, второе — 1010.
     *
     * Обратите внимание, для краткости мы будем игнорировать неважные нули в числах.
     *
     * Пример ниже иллюстрирует использование операторов and, or и xor:
     */
    val first = 15  // binary format 1111
    val second = 10 // binary format 1010

    val bitwiseAnd = first and second // 1111 & 1010 = 1010, the result is 10
    val bitwiseOr = first or second // 1111 | 1010 = 1111, the result is 15
    val bitwiseXor = first xor second // 1111 ^ 1010 = 0101, the result is 5

    println(bitwiseAnd) // 10
    println(bitwiseOr) // 15
    println(bitwiseXor) // 5

    // Два примера инверсии:
    val third = 35 // binary format 0000 0000 0010 0011
    val fourth = -35 // binary format 1111 1111 1101 1101

    val inverseFirst = third.inv() // ~ 0000 0000 0010 0011 = 1111 1111 1101 1100, the result is -36
    val inverseSecond = fourth.inv() // ~ 1111 1111 1101 1101 = 0000 0000 0010 0010, the result is 34

    println(inverseFirst) // -36
    println(inverseSecond) // 34

    println("--- Bit-shift operators ---")

    /**
     * Следующий пример иллюстрирует, как выполнить быстрое умножение и деление на два с помощью операторов побитового сдвига:
     */
    var value = 25 // binary: 0001 1001, decimal: 25

    println(value) // 25

    value = value shl 1 // binary: 0011 0010, decimal: 50

    println(value) // 50

    value = value shl 2 // binary: 1100 1000, decimal: 200

    println(value) // 200

    var newVal = 25 // binary: 0001 1001, decimal: 25

    println(newVal) // 25

    newVal = newVal shl 1 // 25 * 2^1 = 50

    println(newVal) // 50

    newVal = newVal shl 3 // 50 * 2^3 = 400

    println(newVal) // 400

    newVal = newVal shr 2 // 400 / 2^2 = 100

    println(newVal) // 100

    var anotherVal = 14 // binary: 1110, decimal: 14

    println(anotherVal) // 14

    anotherVal = anotherVal shr 1 // binary: 0111, decimal: 7

    println(anotherVal) // 7

    // Вычисление середины интервала положительных целых чисел.
    // Конечно, эта магия даёт тот же результат, что и `(left + right) / 2`, но версия с битовым сдвигом часто считается более быстрым способом сделать это.
    val left = 10
    val right = 20

    val mid = (left + right) shr 1 // this is 15!

    println(mid) // 15

    // Ещё один пример иллюстрирует разницу между shr и ushr:
    val number1 = 5 // 0000 0000 0000 0101
    val number2 = -5 // 1111 1111 1111 1011

    println("SHR | USHR: $number1 | $number2") // 5 | -5

    val shrNumber1 = number1 shr 1 // 0000 0000 0000 0010, the result is 2
    val ushrNumber1 = number1 ushr 1 // 0000 0000 0000 0010, the result is 2

    println("SHR | USHR: $shrNumber1 | $ushrNumber1") // 2 | 2

    val shrNumber2 = number2 shr 1 // 1111 1111 1111 1101, the result is -3
    val ushrNumber2 = number2 ushr 1 // 0111 1111 1111 1101, the result is 2147483645

    println("SHR | USHR: $shrNumber2 | $ushrNumber2") // -3 | 2147483645

    // Начиная с Kotlin 1.6, вы можете использовать rotateLeft() и rotateRight() для сдвига на определённое количество указанных битов:
    val value1 = 25 // 0 000 0000 0001 1001

    // 0 000 0000 0011 0010 -> 50
    println(value1 shl 1) // 50
    println(value1.rotateLeft(1)) // 50

    // 0 000 0000 0110 0100 -> 100
    println(value1 shl 2) // 100
    println(value1.rotateLeft(2)) // 100

    // RotateRight() странным образом изменил наше значение. В чём дело? Это происходит потому, что тип Int представляет собой 32-битное целое значение.
    // 1 000 0000 0000 1100 -> -32756 (16 бит)
    println(value1 shr 1) // 12 -> 0 000 0000 0000 1100
    println(value1 ushr 1) // 12
    println(value1.rotateRight(1)) // -2147483636
    println(value1.toByte().rotateRight(1)) // -116
    println(value1.toShort().rotateRight(1)) // -32756
    println(value1.toLong().rotateRight(1)) // -9223372036854775796

    // 0 100 0000 0000 0110
    println(value1 shr 2) // 6 -> 0 000 0000 0000 0110
    println(value1 ushr 2) // 6
    println(value1.rotateRight(2)) // 1073741830
    println(value1.toByte().rotateRight(2)) // 70
    println(value1.toShort().rotateRight(2)) // 16390
    println(value1.toLong().rotateRight(2)) // 4611686018427387910

    // Подробное объяснение как происходит сдвиг
    // Наша переменная 32бит. Последний бит (31, нумерация начинается с 0) кодирует знак номера. Если 0, то число положительное, если 1, то число отрицательное. Также положительные числа кодируются прямым кодом, а отрицательные – дополнительным кодом. Чтобы получить дополнительный код числа, необходимо инвертировать все его биты и добавить 1.
    // Таким образом, в двоичном 32-битном номере 5 выглядит как:
    // 0 000 0000 0000 0000 0000 0000 0000 0101,
    // а -5 выглядит как 1 111 1111 1111 1111 1111 1111 1111 1011
    // Крайние левые биты являются знаком числа.
    // Теперь ushr перемещает весь наш бит вправо и заполняет самый левый бит нулём.
    // Таким образом, `5 ushr 1` становится
    // 0 000 0000 0000 0000 0000 0000 0000 0010,
    // результат равен 2.
    // И -5 становится
    // 0 111 1111 1111 1111 1111 1111 1111 1101.
    // И это число положительное (самый левый бит положительный), и его значение:
    // 1*2^30 + 1*2^29 + 1*2^28 + ... + 1*2^3 + 1*2^2 +0*2^1+ 1*2^0. И это значение = 2147483645.
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

/**
 * Считывает два больших положительных целых числа и вычисляет наименьшее положительное число, которое делится на них обоих.
 */
fun leastCommonMultiple() {
    val a: BigInteger = "6".toBigInteger()
    val b: BigInteger = "8".toBigInteger()

    // gcd() вычисляет наибольший общий делитель
    val result = a * b / a.gcd(b)

    println("Least common multiple (LCM): $result") // 24
}

fun solveQuadraticEquation(a: Double, b: Double, c: Double): List<Double> {
    val roots = mutableListOf<Double>()

    val discriminant: Double = b * b - 4 * a * c

    when {
        // 2 разных корня
        discriminant > 0 -> {
            val root1: Double = (-b + sqrt(discriminant)) / (2 * a)
            val root2: Double = (-b - sqrt(discriminant)) / (2 * a)

            roots.add(root1)
            roots.add(root2)
        }

        // Комплексные корни
        discriminant < 0 -> {
            val realPart = -b / (2 * a)
            val imagPart = sqrt(-discriminant) / (2 * a)

            println("Discriminant < 0. Корни квадратного уравнения: $realPart + ${imagPart}i и $realPart - ${imagPart}i")
        }

        // 2 одинаковых корня
        else -> {
            val root: Double = -b / (2 * a)

            roots.add(root)
            roots.add(root)
        }
    }

    return roots.sorted().toList()
}

/**
 * Существуют действительные числа a, b, c, где a ≠ 0.
 *
 * Решите квадратное уравнение `(a * x^2) + (b * x) + c = 0` и выведите все его корни.
 */
fun quadraticEquation() {
    val cases = arrayOf(
        doubleArrayOf(1.0, -1.0, -2.0), // > 0, -1.0 2.0
        doubleArrayOf(1.0, -7.5, 3.0), // > 0, 0.423966 7.07603
        doubleArrayOf(1.0, 6.0, 5.0), // > 0, -5.0, -1.0
        doubleArrayOf(1.0, -6.0, 9.0), // == 0, 3.0, 3.0
        doubleArrayOf(1.0, -3.0, 10.0), // < 0
    )

    cases.forEach {
        val (a, b, c) = it

        val result = solveQuadraticEquation(a, b, c)

        println("Quadratic equation: ${result.joinToString(" ")}")
    }
}
