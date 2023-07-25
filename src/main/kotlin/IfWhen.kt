fun main() {
    expressionStyle()

    /**
     * when
     */
    ifElseIfChainsAlternative()
    whenExpression(3)
    whenExpression(4)
    whenRangesAndConditions(15)
    healthySleep()

    /**
     * Если нужно обработать несколько кейсов, их можно объединить и разделить запятой
     */
    print("Введите операцию с двумя числами: ")

    val (var1, op, var2) = readln().split(' ')

    val a = var1.toInt()
    val b = var2.toInt()

    /**
     * Этот код работает для 5 + 8, для 5 плюс 8, 5 plus 8.
     */
    when (op) {
        "+", "plus", "плюс" -> println(a + b)
        "-", "minus", "минус" -> {
            val diff: Int = a - b

            println(diff)
        }
        "*", "times", "умножить" -> println(a * b)
        else -> println("Unknown operator")
    }
}

fun expressionStyle() {
    print("Введите первое число: ")

    val a = readln().toInt()

    print("Введите второе число: ")

    val b = readln().toInt()

    val max1 = if (a > b) {
        println("Choose a")
        a
    } else {
        println("Choose b")
        b
    }

    println("Max: $max1")

    // Idiom
    val max2 = if (a > b) a else b

    println("Max: $max2")

    println(if (a == b) {
        "a equal b"
    } else if (a > b) {
        "a is greater than b"
    } else {
        "a is less than b"
    })
}

fun ifElseIfChainsAlternative() {
    print("Введите число: ")

    when (readln().toInt()) {
        1 -> println("One")
        2 -> println("Two")
        3 -> println("Three")
        4 -> println("Four")
        else -> println("Number is greater than four")
    }
}

fun whenExpression(number: Int) {
    val message = when (number) {
        1 -> "One"
        2 -> "Two"
        3 -> "Three"
        4 -> {
            val start = "Fo"
            val end = "ur"

            start + end
        }
        else -> "Number is greater than four"
    }

    println("When expression: $message")
}

fun whenRangesAndConditions(number: Int) {
    /**
     * Каждое условие ветвления представляет собой логическое выражение, которое может включать в себя любые операции, производящие логические значения.
     */
    when {
        number < 0 -> println("Negative number")
        number in 1..10 -> println("Number between 1 and 10")
        number % 2 == 0 -> println("Even number")
        else -> println("Odd number greater than 10")
    }

    when (number) {
        0 -> println("number is zero")
        in 1..10 -> println("number is between 1 and 10 (inclusive)")
        in 25..27, in 28..30 -> println("number is between 25 and 30 (inclusive)")
        else -> println("number is outside a range")
    }
}

fun healthySleep() {
    print("Введите минимальное количество часов сна: ")

    val minHoursPerDay = readln().toInt()

    print("Введите максимальное количество часов сна: ")

    val maxHoursPerDay = readln().toInt()

    print("Сколько поспали часов за сегодня? ")

    val sleepsHoursPerDay = readln().toInt()

    when {
        sleepsHoursPerDay < minHoursPerDay -> println("Deficiency")
        sleepsHoursPerDay > maxHoursPerDay -> println("Excess")
        else -> println("Normal")
    }
}
