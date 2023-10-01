/**
 * Kotlin позволяет вам определять собственные операторы для определённых типов. Эти операторы имеют предопределённое символическое представление, например «+» или «+=», и приоритет.
 *
 * Чтобы определить пользовательский оператор, мы должны выполнить следующие требования:
 *
 * - Предоставьте функцию-член или функцию расширения с определённым именем для соответствующего типа.
 *
 * - Этот тип становится левым типом для двоичных операций и типом аргумента для унарных операций.
 *
 * Чтобы настроить оператор, мы должны пометить функцию модификатором operator.
 *
 * Также вы можете комбинировать operator с модификатором infix.
 *
 *      Expression          | Translated to
 * `+a`                     | a.unaryPlus()
 * `-a`                     | a.unaryMinus()
 * `!a`                     | a.not()
 * `a++`                    | a.inc()
 * `a--`                    | a.dec()
 * `a + b`                  | a.plus(b)
 * `a - b`                  | a.minus(b)
 * `a * b`                  | a.times(b)
 * `a / b`                  | a.div(b)
 * `a % b`                  | a.rem(b)
 * `a..b`                   | a.rangeTo(b)
 * `a in b`                 | b.contains(a)
 * `a !in b`                | !b.contains(a)
 * `a[i]`                   | a.get(i)
 * `a[i, j]`                | a.get(i, j)
 * `a[i_1, ..., i_n]`       | a.get(i_1, ..., i_n)
 * `a[i] = b`               | a.set(i, b)
 * `a[i, j] = b`            | a.set(i, j, b)
 * `a[i_1, ..., i_n] = b`   | a.set(i_1, ..., i_n, b)
 * `a()`                    | a.invoke()
 * `a(i)`                   | a.invoke(i)
 * `a(i, j)`                | a.invoke(i, j)
 * `a(i_1, ..., i_n)`       | a.invoke(i_1, ..., i_n)
 * `a += b`                 | a.plusAssign(b)
 * `a -= b`                 | a.minusAssign(b)
 * `a *= b`                 | a.timesAssign(b)
 * `a /= b`                 | a.divAssign(b)
 * `a %= b`                 | a.remAssign(b)
 * `a == b`                 | a?.equals(b) ?: (b === null)
 * `a != b`                 | !(a?.equals(b) ?: (b === null))
 * `a > b`                  | a.compareTo(b) > 0
 * `a < b`                  | a.compareTo(b) < 0
 * `a >= b`                 | a.compareTo(b) >= 0
 * `a <= b`                 | a.compareTo(b) <= 0
 */
operator fun String.unaryMinus(): String = this.reversed()

infix operator fun String.times(n: Int): String = this.repeat(n)

operator fun Pair<Int, Int>.unaryMinus(): Pair<Int, Int> = Pair(-first, -second)

operator fun Pair<Int, Int>.inc(): Pair<Int, Int> = Pair(first + 1, second + 1)

operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> =
    Pair(first + other.first, second + other.second)

operator fun Pair<Int, Int>.contains(n: Int): Boolean = n in first..second

operator fun Pair<Int, Int>.get(n: Int): Int = when (n) {
    0 -> first
    1 -> second
    else -> throw IndexOutOfBoundsException()
}

operator fun Pair<Int, Int>.invoke(newLine: Boolean) {
    print("($first, $second)")

    if (newLine) {
        println()
    }
}

operator fun StringBuilder.plusAssign(other: String) {
    this.append(other)
}

class Point(val x: Int, val y: Int) {
    override fun equals(other: Any?): Boolean {
        if (other is Point) {
            return other.x == x && other.y == y
        }

        return false
    }
}

class Point2(val x: Int, val y: Int) : Comparable<Point2> {
    override fun compareTo(other: Point2): Int =
        if (x == other.x) {
            y.compareTo(other.y)
        } else {
            x.compareTo(other.x)
        }
}

fun main() {
    val name = "Kotlin"

    println(-name) // niltoK

    println(name * 3) //KotlinKotlinKotlin

    var p = Pair(1, 2)

    println(-p) // (-1, -2)
    println(++p) // (2, 3)

    val point1 = Pair(1, 2)
    val point2 = Pair(3, 4)

    println(point1 + point2) // (4, 6)

    println(1 in point1) // true

    println(point1[0]) // 1
    println(point1[1]) // 2

    point1(true) // (1, 2)

    val string = StringBuilder("Kotlin")

    string += " is awesome"

    println(string) // Kotlin is awesome

    val p1 = Point(1, 2)
    val p2 = Point(1, 2)

    println(p1 == p2) // true

    val p3 = Point2(1, 2)
    val p4 = Point2(1, 2)

    println(p3 < p4) // false
    println(p3 <= p4) // true

    listOfStudentGrades()
}

operator fun List<Double>.invoke(markLimit: Double): Double {
    return this.filter { it > markLimit }.average()
}

/**
 * Получите среднюю оценку студентов, сдавших экзамен.
 *
 * Вы должны перегрузить функцию вызова с помощью markLimit, чтобы получить среднее значение всех оценок, превышающих этот предел.
 */
fun listOfStudentGrades() {
    val listOfGrades = listOf(5.5, 4.6, 3.0, 2.5, 10.0, 9.9, 8.75)

    println(listOfGrades(5.0)) // 8.5375
}
