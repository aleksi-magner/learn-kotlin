fun main() {
    creatingRegExp()
    match()
    quantifiers()
    shorthands()
    regexpsAndSplit()
    regexpsAndReplace()
    regexpsAndFind()
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

/**
 * В регулярном выражении есть группа символов, известная как квантификаторы, которые определяют, сколько раз определённый символ (или класс символов) встречается в шаблоне регулярного выражения.
 *
 * Квантор может следовать как за обычным символом, так и за специальным.
 *
 * В общем, кванторы — одна из наиболее существенных и важных особенностей языка регулярных выражений, поскольку они позволяют одному шаблону сопоставлять разные строки различной длины.
 *
 * - `+` соответствует одному или нескольким экземплярам предыдущего символа;
 * - `*` соответствует нулю или более экземплярам предыдущего символа;
 * - `{n}` соответствует ровно n экземплярам предыдущего символа;
 * - `{n,m}` соответствует как минимум n, но не более m экземплярам предыдущего символа;
 * - `{n,}` соответствует как минимум n экземплярам предыдущего символа;
 * - `{0,m}` соответствует не более чем m экземплярам предыдущего символа.
 * - `?` - делает предыдущий символ необязательным. Это сокращение от {0,1}
 *
 * Внутри фигурных скобок нельзя использовать пробелы. Может быть только одна или две цифры и, по желанию, запятая. Помещение пробелов внутри фигурных скобок приводит к «деактивации» квантора и, как следствие, к совершенно другому регулярному выражению.
 */
fun quantifiers() {
    println("-- Quantifiers --")

    for (value in listOf("cab", "caaaaab", "cb")) {
        println(Regex("ca+b").matches(value)) // true, true, false
    }

    println()

    for (value in listOf("A", "A0", "A000111222333", "A34")) {
        println(Regex("A[0-3]*").matches(value)) // true, true, true, false
    }

    println()

    for (value in listOf(
        "My friend John is a computer programmer",
        "John",
        "My friend is a computer programmer"
    )) {
        println(Regex(".*John.*").matches(value)) // true, true, false
    }

    println()

    for (value in listOf("6342", "9034", "182", "54312")) {
        println(Regex("[0-9]{4}").matches(value)) // true, true, false, false
    }

    println()

    for (value in listOf("1", "11", "111", "1111")) {
        println(Regex("1{2,3}").matches(value)) // false, true, true, false
    }

    println()

    for (value in listOf("abb", "abbbb", "abbbbbbb")) {
        println(Regex("ab{4,}").matches(value)) // false, true, true
    }

    println()
}

/**
 * - `\d` — любая цифра, сокращение от `[0-9]`;
 *
 * - `\s` — символ пробела (включая табуляцию и новую строку), сокращение от `[ \t\n\x0B\f\r]`;
 *
 * - `\w` — буквенно-цифровой символ (буква или цифра), сокращение от `[a-zA-Z_0-9]`;
 *
 * - `\b` — граница слова. Этот немного сложнее: он не соответствует какому-либо конкретному символу, а скорее соответствует границе между буквенно-цифровым символом или подчеркиванием и небуквенно-цифровым символом (например, символом пробела) или границей строки (её конец или начало).
 * Таким образом, «\ba» соответствует всем словам (последовательности буквенно-цифровых символов), начинающимся с «a», «a\b» соответствует всем словам, заканчивающимся на «a», а «\ba\b» соответствует всем отдельным символам «a», если перед ними и после них идут небуквенно-цифровые символы.
 *
 * - `\D` — это не цифра, сокращение от `[^0-9]`;
 *
 * - `\S` — символ без пробелов, сокращение от `[^ \t\n\x0B\f\r]`;
 *
 * - `\W` — небуквенно-цифровой символ, сокращение от `[^a-zA-Z_0-9]`.
 *
 * - `\B` — не граница слова. Он соответствует регистру, противоположному случаю `\b`: он находит совпадение каждый раз, когда между буквенно-цифровыми символами нет «пробела». Например, «a\B» соответствует всем словам, начинающимся с «a».
 */
fun shorthands() {
    println("-- Shorthands --")

    for (value in listOf(" A5 ", " 33 ", "\tA4\t", "q18q", " AB ", " -1 ")) {
        println(Regex("\\s\\w\\d\\s").matches(value)) // true, true, true, false, false, false
    }

    println()

    for (value in listOf(" 9o9 ", "\nA 1 ", "\tAl4\t", " \taa ", "_BBB ")) {
        println(Regex("\\W\\S\\D\\S\\W").matches(value)) // true, true, true, false, false
    }

    println()

    for (value in listOf("cat", "cat bb", "cat42", "catalog", "ducati")) {
        println(Regex("\\bcat.*").matches(value)) // true, true, true, true, false
    }

    println()

    for (value in listOf("cat", "bb cat", "mouse and cat", "catalog")) {
        println(Regex(".*cat\\b").matches(value)) // true, true, true, false
    }

    println()

    for (value in listOf("cat", "bb cat", "mouse and cat", "catalog", "cat42")) {
        println(Regex(".*\\bcat\\b.*").matches(value)) // true, true, true, false, false
    }

    println()
}

fun regexpsAndSplit() {
    println("-- Regexps and split() --")

    val number = "+1-213-345-6789"
    val brackets = "+1-(213)-345-6789"

    println(number.split("-")) // [+1, 213, 345, 6789]

    val regExp = Regex("(-\\(|\\)-|-)")

    println(brackets.split(regExp)) // [+1, 213, 345, 6789]
    println(number.split(regExp)) // [+1, 213, 345, 6789]
    println(number.split(regExp, 2)) // [+1, 213-345-6789]
}

fun regexpsAndReplace() {
    println("-- Regexps and replace() --")

    val withDigits = "The first test flight of Falcon 9 was on June 4, 2010, from Cape Canaveral, Florida, and the first resupply mission to the ISS was made on October 7, 2012."

    // The first test flight of Falcon [digits] was on June [digits], [digits], from Cape Canaveral, Florida, and the first resupply mission to the ISS was made on October [digits], [digits].
    println(withDigits.replace(Regex("\\d+"), "[digits]"))
}

fun regexpsAndFind() {
    println("-- Regexps and find() --")

    // phone number template
    val phoneTemplate: Regex = """[+]?[(]?[0-9]{1,4}[)]?[-0-9]*""".toRegex()

    val matchResult1: MatchResult? = phoneTemplate.find("Her phone number is +1-234-567-89-01. You can also call the second one: +1-111-568-01-01")

    println(matchResult1?.value ?: "Not found") // +1-234-567-89-01

    // date template in format YYYY-MM-DD
    val dateTemplate = """\d{4}-\d{2}-\d{2}""".toRegex()

    val matchResult2: Sequence<MatchResult> =
        dateTemplate.findAll("Harry was born 1980-07-31 in the Godric's Hollow.\nIn 1997-12-24, on Christmas Eve, he returned there for the legendary Gryffindor sword")

    // 1980-07-31
    // 1997-12-24
    for (matches: MatchResult in matchResult2) {
        println(matches.value)
    }
}
