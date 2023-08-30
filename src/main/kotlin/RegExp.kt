fun main() {
    creatingRegExp()
    match()
}

fun creatingRegExp() {
    /**
     * Первый способ — создать экземпляр String и затем вызвать метод toRegex(), который создаст регулярное выражение из этой строки
     */
    val string = "cat" // Создание строки "cat"
    val stringToRegexp: Regex = string.toRegex() // Создание регулярного выражения "cat"

    /**
     * Другой способ — вызвать конструктор Regex
     */
    val regexp = Regex("cat") // Создание регулярного выражения "cat"
}

/**
 * Используется для поиска полного соответствия, то есть вся строка должна соответствовать шаблону
 */
fun match() {
    println("-- Match --")

    for (value in listOf("cat", "dog", "cats")) {
        println(Regex("cat").matches(value)) // true, false, false
    }

    println()

    for (value in listOf("cat.", "cat!", "cat ", "cat\n")) {
        println(Regex("cat.").matches(value)) // true, true, true, false
    }

    println()

    for (value in listOf("cat", "cat!", "cats", "cat\n")) {
        println(Regex("cats?").matches(value)) // true, false, true, false
    }

    println()

    for (value in listOf("cat", "cats", "cat!", "cot")) {
        println(Regex("cat.?").matches(value)) // true, true, true, false
    }

    println()

    for (value in listOf("cat", "cats", "cats?")) {
        println(Regex("cats\\?").matches(value)) // false, false, true
    }

    println()

    for (value in listOf("color", "colour", "colors", "colours", "colooors")) {
        println(Regex("colou?rs?").matches(value)) // true, true, true, false
    }

    println()

    /**
     * Набор «[abc]» означает, что ему может соответствовать один символ «a», «b» или «c»
     *
     * Порядок наборов в регулярных выражениях важен. Порядок символов в наборе не имеет значения
     */
    for (value in listOf("rat", "fat")) {
        println(Regex("[bcr]at").matches(value)) // true, false
    }

    println()

    for (value in listOf("ax1", "ax2", "bx1", "bx2", "xa1", "aax1", "bx")) {
        println(Regex("[ab]x[12]").matches(value)) // true, true, true, true, false, false, false
    }

    println()

    /**
     * Иногда нам хочется сделать наборы символов достаточно большими. В этом случае нам не обязательно записывать их все, мы можем указать диапазон, обозначенный символом дефиса.
     *
     * Символ, стоящий перед дефисом, обозначает начальную точку диапазона; символ после дефиса — это последний символ, попадающий в диапазон.
     *
     * Мы можем поместить символы в набор как диапазон, если они следуют друг за другом в таблице кодировки ASCII/Unicode.
     */
    for (value in listOf("0", "1", "2", "9", "10", "a", "_")) {
        println(Regex("[0-9]").matches(value)) // true, true, true, true, false, false, false
    }

    println()

    for (value in listOf("a", "z", "A", "Z", "0")) {
        println(Regex("[a-zA-Z]").matches(value)) // true, true, true, true, false
    }

    println()

    /**
     * В некоторых случаях может потребоваться определить, какие символы нежелательны. Для этого мы пишем символ шляпы ^ первым в наборе.
     */
    for (value in listOf("a", "b", "c", "d", "A")) {
        println(Regex("[^abc]").matches(value)) // false, false, false, true, true
    }

    println()

    for (value in listOf("1", "2", "0", "9")) {
        println(Regex("[^1-6]").matches(value)) // false, false, true, true
    }

    println()

    /**
     * Вертикальная полоса | используется для сопоставления последовательностей символов до или после символа
     *
     * Наборы ([]) могут соответствовать только одному символу в строке, чередования (|) используются для определения многосимвольных альтернатив.
     */
    for (value in listOf("yes", "no", "maybe", "y", "e")) {
        println(Regex("yes|no|maybe").matches(value)) // true, true, true, false, false
    }

    println()

    /**
     * Вертикальную черту можно использовать вместе с круглыми скобками, обозначающими границы чередующихся подстрок: всё, что находится внутри круглых скобок, является необязательной подстрокой, которая может соответствовать блоку чередования
     */
    for (value in listOf("bat", "rat", "goat", "at", "hat")) {
        println(Regex("(b|r|go)at").matches(value)) // true, true, true, false, false
    }

    println()
}
