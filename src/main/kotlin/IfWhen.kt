fun main() {
    expressionStyle()
    ifElseIfChainsAlternative()
    whenExpression(3)
    whenRangesAndConditions(15)
    healthySleep()
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
        4 -> "Four"
        else -> "Number is greater than four"
    }

    println("When expression: $message")
}

fun whenRangesAndConditions(number: Int) {
    when {
        number < 0 -> println("Negative number")
        number in 1..10 -> println("Number between 1 and 10")
        number % 2 == 0 -> println("Even number")
        else -> println("Odd number greater than 10")
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
