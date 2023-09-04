fun main() {
    repeat(-1) {
        println("Hello -1")
    }

    repeat(0) {
        println("Hello 0")
    }

    repeat(1) {
        println("Hello 1")
    }

    repeat(3) {
        println("Hello $it")
    }

    sumTask()
    sizeOfParts()
}

fun sumTask() {
    print("Сколько чисел нужно сложить? ")

    val amount = readln().toInt()

    var sum = 0

    repeat(amount) {
        print("Введите число: ")

        val next = readln().toInt()

        sum += next

        if (it + 1 < amount) {
            println("Текущая сумма чисел равна $sum")
        }
    }

    println("Общая сумма чисел равна $sum")
}

fun sizeOfParts() {
    print("Количество сканируемых запчастей: ")

    val amountParts = readlnOrNull()?.toInt() ?: error("Detection canceled")

    var perfect = 0
    var larger = 0
    var rejections = 0

    repeat(amountParts) {
        print("Итог проверки запчасти (-1/0/1): ")

        val sizeOfComponent = readlnOrNull()?.toInt() ?: error("Detector error")

        when {
            sizeOfComponent > 0 -> larger += 1
            sizeOfComponent < 0 -> rejections += 1
            else -> perfect += 1
        }
    }

    println("Запчастей на доработку: $larger")
    println("Бракованных запчастей: $rejections")
    println("Идеальных запчастей: $perfect")
}
