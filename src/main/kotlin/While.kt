import java.util.*

fun main() {
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
