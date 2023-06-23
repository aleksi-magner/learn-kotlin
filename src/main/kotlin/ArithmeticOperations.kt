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
}
