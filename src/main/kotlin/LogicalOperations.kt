fun main() {
    // Boolean
    val valid = true
    val invalid = false

    println(valid) // true
    println(invalid) // false

    println("--- Read input ---")

    print("Enter any: ")

    val readBoolean: Boolean = readln().toBoolean()

    println("You entered: $readBoolean")

    println("--- Boolean cases ---")

    val boolCases = listOf("true", "True", "TRuE", "T", "false")

    for (value in boolCases) {
        println(value.toBoolean())
    }

    println("--- Strict boolean ---")

    println("true".toBooleanStrict())     // true
    // println("True".toBooleanStrict())  // program crashes
    println("false".toBooleanStrict())    // false
    // println("faLse".toBooleanStrict()) // program crashes

    println("--- Strict or null boolean ---")

    println("true".toBooleanStrictOrNull())  // true
    println("false".toBooleanStrictOrNull()) // false
    println("faLse".toBooleanStrictOrNull()) // null

    // NOT
    /**
     * NOT — унарный оператор, обращающий логическое значение. Его можно обозначить знаком !.
     */
    println("Inverse valid variable: ${!valid}")

    // AND
    /**
     * И — это бинарный оператор, который возвращает значение true, если оба операнда true. В противном случае возвращается false. Его можно обозначить с помощью &&.
     */
    println("--- AND cases ---")

    val andCases = listOf(false && false, false && true, true && false, true && true)

    for (value in andCases) {
        println(value)
    }

    // OR
    /**
     * ИЛИ — это бинарный оператор, который возвращает true, если хотя бы один операнд true. В противном случае возвращается false. Его можно обозначить с помощью ||.
     */
    println("--- OR cases ---")

    val orCases = listOf(false || false, false || true, true || false, true || true)

    for (value in orCases) {
        println(value)
    }

    // XOR
    /**
     * XOR (исключающее ИЛИ) — это бинарный оператор, который возвращает true, если логические операнды имеют разные значения. В противном случае оно false.
     * Оператор XOR не так популярен, как другие логические операторы. Но вы все равно можете использовать его, на всякий случай.
     */
    println("--- XOR cases ---")

    val xorCases = listOf(false xor false, false xor true, true xor false, true xor true)

    for (value in xorCases) {
        println(value)
    }

    // Приоритеты
    /**
     * Приоритет определяет, как группируются другие переменные при отсутствии круглых скобок.
     * 1. ! for NOT
     * 2. xor for XOR
     * 3. && for AND
     * 4. || for OR
     */
    val cold = false
    val dry = true
    val summer = false // suppose it is autumn now

    val hiking = dry && (!cold || summer)

    println("Let's go hiking: $hiking") // true
}
