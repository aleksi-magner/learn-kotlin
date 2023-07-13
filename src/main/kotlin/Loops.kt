import java.util.*
import kotlin.math.pow

fun main() {
    whileLoop()
    forLoop()
}

fun whileLoop() {
    /**
     * Выполняет блок кода пока условие condition == true.
     * Проверка условия перед выполнением.
     * while (condition) {
     *     // body: do something repetitive
     * }
     */
    var i = 0

    while (i < 5) {
        println(i)

        i += 1
    }

    println("Completed")

    var letter = 'A'

    while (letter <= 'Z') {
        print(letter)

        letter += 1
    }

    println()

    val scanner = Scanner("Kotlin is a modern language")

    while (scanner.hasNext()) {
        val next = scanner.next()

        println(next)
    }

    /**
     * Выполняет блок кода пока условие condition == true.
     * Проверка условия после выполнения.
     * Блок do выполняется минимум 1 раз.
     * do {
     *     // body: do something
     * } while (condition)
     */
    do {
        print("Введите число: ")

        val n = readln().toInt()

        println(n)
    } while (n > 0)

    sequence()
}

fun sequence() {
    print("Количество чисел последовательности: ")

    val amount = readln().toInt()

    var repetitions = 0
    var number = 1

    fun isValid(): Boolean = repetitions < amount

    while (isValid()) {
        repeat(number) {
            if (isValid()) {
                print("$number ")
            }

            repetitions += 1
        }

        number += 1
    }
}

const val START_RANGE: Int = 0
const val END_RANGE: Int = 1000

fun forLoop() {
    /**
     * Kotlin предоставляет цикл for для перебора диапазонов, массивов и других коллекций элементов.
     *
     * Тело этого цикла состоит из одного или нескольких операторов, которые выполняются для каждого элемента из указанного источника.
     *
     * Цикл останавливается после обработки последнего элемента.
     */
    println("--- Range loop ---")

    for (number in 1..4) {
        println("Range: $number")
    }

    /**
     * Итерация в обратном порядке
     */
    for (number in 4 downTo 1) {
        println("Backward order: $number")
    }

    /**
     * Без учёта верхнего предела
     */
    for (number in 1 until 4) {
        println("Excluding the upper limit: $number")
    }

    /**
     * Указание шага
     *
     * Если мы не указываем шаг, предполагается, что шаг равен единице (например, 1, 2, 3,...). Хотя если мы хотим изменить шаг, нам нужно указать его явно.
     */
    for (number in 1..7 step 2) {
        println("Specifying a step: $number")
    }

    for (number in 7 downTo 1 step 2) {
        println("Specifying a step (backward): $number")
    }

    println("--- Chars loop ---")

    for (char in 'a'..'d') {
        println(char)
    }

    println("--- String loop ---")

    val string = "Hello!"

    for (char in string) {
        println(char)
    }

    println("--- Lists loop ---")

    val daysOfWeek = mutableListOf("Sun", "Mon", "Tues", "Wed", "Thur", "Fri", "Sat")

    println("Весь список")

    for (day in daysOfWeek){
        println(day)
    }

    println("Весь список с индексами")

    for (index in daysOfWeek.indices){
        println("$index: ${daysOfWeek[index]}")
    }

    println("Весь список с индекса 1 по 5")

    for (index in 1..5) {
        println("$index: ${daysOfWeek[index]}")
    }

    println("Весь список с 1 индекса по предпоследний")

    for (index in 1 until daysOfWeek.lastIndex) {
        println("$index: ${daysOfWeek[index]}")
    }

    println("Список с конца с шагом 2")

    for (index in daysOfWeek.lastIndex downTo 0 step 2) {
        println("$index: ${daysOfWeek[index]}")
    }

    println("Чтение элементов списка из стандартного ввода")

    print("Введите размер списка: ")

    val size = readln().toInt()

    val mutList: MutableList<Int> = mutableListOf()

    for (i in 0 until size) {
        print("Введите число: ")

        mutList.add(readln().toInt())
    }

    for (i in mutList.lastIndex downTo 0) {
        print("${mutList[i]} ")
    }

    println()

    factorialOfANumber()
    multiplicationTableOfEvenNumbers()
    sumOfIntegersFromAtoB()
    rootsOfEquation(START_RANGE, END_RANGE)
    individualTaxes()
    numberInASet()
    fizzBuzz()
}

/**
 * Вычисляет факториал заданного целого числа.
 *
 * Факториал числа n — это произведение целых чисел от 1 до n включительно.
 *
 * Предположим, что факториал 0 равен 1. Факториал 1 также равен 1.
 */
fun factorialOfANumber() {
    print("Введите количество чисел факториала: ")

    val number = readln().toInt()

    var result = 1 // начальное значение факториала

    for (i in 2..number) { // произведение от 2 до n
        result *= i
    }

    println(result)
}

/**
 * Печатает таблицу умножения чётных чисел от 2 до 10
 */
fun multiplicationTableOfEvenNumbers() {
    for (i in 2..10 step 2) {
        for (j in 2..10 step 2) {
            print("${i}x$j=${i * j}")
            print('\t')
        }

        println()
    }
}

/**
 * Читает два целых числа a и b.
 *
 * Выводит сумму всех целых чисел от a до b включительно.
 *
 * Гарантируется, что a < b.
 */
fun sumOfIntegersFromAtoB() {
    print("Введите начало диапазона: ")

    val startNumber = readln().toInt()

    print("Введите окончание диапазона: ")

    val endNumber = readln().toInt()

    var result = 0

    for (number in startNumber..endNumber) {
        result += number
    }

    println("Result: $result")
}


/**
 * Даны числа a, b, c, d.
 *
 * Выведите в порядке возрастания все целые числа от 0 до 1000 (включительно), являющиеся корнями уравнения a⋅x^3 + b⋅x^2 + c⋅x + d = 0.
 *
 * Корни кубического уравнения — это значения переменной, которые удовлетворяют уравнению.
 */
fun rootsOfEquation(start: Int, end: Int) {
    print("Введите число a: ")

    val a: Int = readln().toInt()

    print("Введите число b: ")

    val b: Int = readln().toInt()

    print("Введите число c: ")

    val c: Int = readln().toInt()

    print("Введите число d: ")

    val d: Int = readln().toInt()

    for (number in start..end) {
        val x: Float = number.toFloat()
        val result: Float = a * x.pow(3) + b * x.pow(2) + c * x + d

        if (result.toInt() == 0) {
            println(number)
        }
    }
}

/**
 * Первым числом считывает количество компаний.
 *
 * Следующими N числами считывает годовой доход каждой компании.
 *
 * Следующими N числами считывает налоговую ставку для каждой компании.
 *
 * Числа являются неотрицательными целыми числами.
 *
 * Налоговая стоимость представляет собой годовой доход компании, умноженный на налоговую ставку.
 *
 * Выводит номер первой компании, которая платит наибольший налог.
 */
fun individualTaxes() {
    print("Введите количество компаний в стране: ")

    val numberOfCompanies: Int = readln().toInt()

    val incomeList: MutableList<Int> = mutableListOf()
    val taxPercentagesList: MutableList<Int> = mutableListOf()

    for (number in 1..numberOfCompanies) {
        print("Введите годовой доход компании ${number}: ")

        incomeList.add(readln().toInt())
    }

    for (number in 1..numberOfCompanies) {
        print("Введите налоговую ставку компании ${number}: ")

        taxPercentagesList.add(readln().toInt())
    }

    val taxValues: MutableList<Int> = mutableListOf()

    for (index in 0 until numberOfCompanies) {
        val income: Int = incomeList[index]
        val taxPercentage: Int = taxPercentagesList[index]

        val tax: Int = income * taxPercentage

        taxValues.add(tax)
    }

    val maxTax: Int = taxValues.max()
    val maxTaxIndex: Int = taxValues.indexOf(maxTax)

    println("Номер компании с наибольшим налогом: ${maxTaxIndex + 1}")
}

/**
 * Проверяет, содержит ли набор из N чисел число M.
 *
 * Первая строка содержит число N.
 * Следующие N строк содержат набор чисел, по одному числу в строке.
 *
 * В последней строке записано одно целое число M.
 *
 *  Нужно вывести YES или NO
 */
fun numberInASet() {
    print("Введите количество чисел в списке: ")

    val inputNumbers: List<Int> = List(readln().toInt()) {
        print("Введите число: ")

        readln().toInt()
    }

    print("Введите искомое число: ")

    val searchNumber: Int = readln().toInt()

    println(if (inputNumbers.contains(searchNumber)) "YES" else "NO")
}

/**
 * Принимает на вход два целых числа: начало и конец интервала (оба числа принадлежат интервалу).
 *
 * Программа должна вывести числа из этого интервала, но если число делится на 3, вместо него следует вывести Fizz; если число делится на 5, выведите Buzz, а если оно делится и на 3, и на 5, выведите FizzBuzz.
 *
 * Выведите каждое число или слово на отдельной строке.
 */
fun fizzBuzz() {
    print("Введите начало диапазона: ")

    val startRange: Int = readln().toInt()

    print("Введите окончание диапазона: ")

    val endRange: Int = readln().toInt()

    for (number in startRange..endRange) {
        val moduloBy3: Int = number % 3
        val moduloBy5: Int = number % 5

        when {
            moduloBy3 == 0 && moduloBy5 == 0 -> println("FizzBuzz")
            moduloBy3 == 0 -> println("Fizz")
            moduloBy5 == 0 -> println("Buzz")
            else -> println(number)
        }
    }
}
