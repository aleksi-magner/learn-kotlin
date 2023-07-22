fun main() {
    // val within: Boolean = c in a..b // a <= c && c <= b

    /**
     * Здесь a..b — это диапазон чисел от a до b (включая оба граничных значения),
     * in — это специальное ключевое слово, которое используется для проверки того, находится ли значение в диапазоне.
     */

    println(5 in 5..15)  // true
    println(12 in 5..15) // true
    println(15 in 5..15) // true
    println(20 in 5..15) // false
    println(-12 in 5..15) // false

    /**
     * Если нужно исключить правую границу, можно вычесть из неё 1:
     */
    // val withinExclRight: Boolean = c in a..b - 1 // a <= c && c < b
    println(14 in 5..15 - 1) // true
    println(15 in 5..15 - 1) // false

    /**
     * Если нужно проверить, что значение не находится в диапазоне, просто добавьте ! (не) перед in.
     */
    val notWithin: Boolean = 100 !in 10..99

    println(notWithin) // true

    /**
     * Вы можете комбинировать диапазоны, используя стандартные логические операторы.
     *
     * Код ниже проверяет, находится ли 'c' в одном из трёх диапазонов.
     */
    val c = 23

    // true если c находится в пределах хотя бы одного диапазона
    val within: Boolean = c in 5..10 || c in 20..30 || c in 40..50

    println(within) // true

    /**
     * Вы можете назначить диапазон переменной и использовать его позже
     */
    val range: IntRange = 100..200

    println(174 in range) // true

    /**
     * В дополнение к целочисленным диапазонам вы также можете использовать диапазоны символов и даже строк (в соответствии со словарным порядком).
     */
    println('b' in 'a'..'c') // true
    println('k' in 'a'..'e') // false

    println("hello" in "he".."hi") // true
    println("abc" in "aab".."aac") // false

    workingHours()
    countNumbers()
}

const val WORKS_FROM = 9
const val WORKS_TO = 18
const val LUNCH_FROM = 13
const val LUNCH_TO = 14

/**
 * Автомойка работает с 9 до 18, а с 13 до 14 её сотрудники обедают.
 *
 * В качестве входных данных наша программа считывает с консоли целое положительное число, которое является желаемым временем для бронирования автомойки, и проверяет, входит ли введённое время в рабочее время.
 *
 * Если время находится в диапазоне рабочих часов, верните true, но если время обеденное или нерабочее время, верните false.
 */
fun workingHours() {
    val workingHoursRange: IntRange = WORKS_FROM..WORKS_TO
    val lunchRange: IntRange = LUNCH_FROM..LUNCH_TO

    print("На какое время желаете записаться, чтобы помыть машину? ")

    val desiredTime: Int = readln().toInt()

    val isFreeTime: Boolean = desiredTime in workingHoursRange && desiredTime !in lunchRange

    println(if (isFreeTime) "Записываем на сегодня, в $desiredTime ч" else "Это время недоступно для записи")
}

/**
 * Считывает числа a, b и n и выводит, сколько чисел в диапазоне от a до b (a < b) делятся на n
 */
fun countNumbers() {
    print("Введите начало диапазона: ")

    val startRange: Int = readln().toInt()

    print("Введите окончание диапазона: ")

    val endRange: Int = readln().toInt()

    val range: IntRange = startRange..endRange

    print("Введите делитель: ")

    val divisor: Int = readln().toInt()

    println(range.count { it % divisor == 0 })
}
