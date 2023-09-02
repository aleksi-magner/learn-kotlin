fun main() {
    creatingRegExp()
    match()
    quantifiers()
    shorthands()
    regexpsAndSplit()
    regexpsAndReplace()
    regexpsAndFind()

    groups()
    dates()
    phoneNumbers()
    email()
    url()

    replacesAllA()
    snakeCaseToCamel()
    nicknameParser()
    splittingWithSpaces()
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

/**
 * Группы имеют то же значение, что и математические выражения: с их помощью мы можем устанавливать новые приоритеты операций.
 *
 * Часть регулярного выражения можно заключить в круглые скобки, чтобы создать группу.
 *
 * Также мы можем применять к каждой группе кванторы: если задать квантор после скобок, то он будет применяться ко всему содержимому скобки, а не к отдельному символу.
 */
fun groups() {
    val resultWithGroups = Regex("(ho)+").findAll("ho hoho hohoho")

    // ho
    // hoho
    // hohoho
    for (res in resultWithGroups) {
        println(res.value)
    }

    val input = "papa paparazzi pan power word apaa dpapapa papas"

    // papa
    // paparazzi
    // pan
    // apaa
    // dpapapa
    // papas
    val result = Regex("\\S*(pa)+\\S*").findAll(input)

    for (res in result) {
        println(res.value)
    }
}

/**
 * Начнём с довольно простой и распространённой задачи. Предположим, вам нужно найти все даты в двух разных форматах: yyyy-mm-dd и yyyy/mm/dd.
 *
 * Как мы можем сделать это? Нам нужно сопоставить фрагменты текста, которые выглядят следующим образом: 4 цифры, затем один из возможных разделителей (/ или -), затем 2 цифры, а затем тот же разделитель и 2 цифры: `\d{4}(-|\/)\d{2}\1\d{2}`
 *
 * - Мы ищем 4 цифры, а затем один из возможных разделителей: `\d{4}(-|\/)`
 *
 * - Далее ищем две цифры и тот же разделитель, который уже найден: `\d{2}\1`.
 * С помощью `\1` мы ссылаемся на первую группу, с которой мы столкнулись в регулярном выражении: `(-|\/)`. Так мы ищем уже идентифицированный разделитель.
 *
 * - Наконец, мы ищем две цифры: `\d{2}`
 */
fun dates() {
    val regex = Regex("""\d{4}(-|/)\d{2}\1\d{2}""")

    val dates = regex.findAll("Date 1: 2022-06-06 Date 2: 2021/01/01; date 3: 2020-02-02")

    // 2022-06-06
    // 2021/01/01
    // 2020-02-02
    for (date in dates) {
        println(date.value)
    }
}

/**
 * Для простоты предположим, что номера телефонов можно записать в одном из следующих форматов: XXX-XXX-XXXX, (XXX)-XXX-XXXX, (XXX)XXXXXXX и XXXXXXXXXX.
 *
 * `\(?[\d]{3}\)?-?[\d]{3}-?[\d]{4}`
 *
 * - `\(?[\d]{3}\)?-?` соответствует первым трём цифрам, возможным скобкам и разделителю.
 *
 * - `[\d]{3}-?` ищет следующие три цифры и возможный разделитель
 *
 * - `[\d]{4}` мы сопоставляем последние четыре цифры
 */
fun phoneNumbers() {
    val regex = Regex("""\(?\d{3}\)?-?\d{3}-?\d{4}""")

    val phones = regex.findAll("Ann's phone: 123-345-6789 Dave's phone: (111)-234-5678, and next phone is (101)-234-5000")

    // 123-345-6789
    // (111)-234-5678
    // (101)-234-5000
    for (phone in phones) {
        println(phone.value)
    }
}

/**
 * Давайте представим, что вам нужно найти все адреса электронной почты, которые встречаются в тексте.
 *
 * Правила составления электронных писем регламентированы RFC 5322.
 *
 * Стандартный адрес электронной почты выглядит как «login@subdomain.domain».
 *
 * Следующее регулярное выражение будет соответствовать большинству адресов электронной почты, составленных в соответствии с этими правилами:
 *
 * `([a-z0-9.-]+)@([a-z0-9_.-]+)\.([a-z.]{2,6})`
 *
 * - `([a-z0-9_.-]+)` мы сопоставляем одну или несколько строчных букв от a до z, цифры от 0 до 9, символы подчеркивания, точки и дефисы. Затем в адресе электронной почты идет знак @, который завершает эту группу.
 *
 * - `([a-z0-9_.-]+)` очень похож на предыдущий: имя поддомена может состоять из тех же символов. За ним следует точка `\.`
 *
 * - `([a-z.]{2,6})` соответствует домену верхнего уровня: любая группа букв или точек длиной от 2 до 6 символов.
 */
fun email() {
    val regex = Regex("""([\w.-]+)@([\w.-]+)\.([a-z.]{2,6})""")

    val matchResult = regex.findAll("We have the following emails: abc@mail.com, joe_blow@address.ing, joe.blow@address.org")

    // abc@mail.com
    // joe_blow@address.ing
    // joe.blow@address.org
    for (matches in matchResult) {
        println(matches.value)
    }
}

/**
 * Поиск и копирование ссылок из текста вручную может быть утомительным. Но нам не придется этого делать!
 *
 * Типичный URL-адрес может выглядеть так: https://www.somesite.com/index.html. Ниже приведено регулярное выражение, соответствующее шаблону:
 *
 * `(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w\.-]*)*\/?`
 *
 * - `(https?:\/\/)?` соответствует первой необязательной части URL-адреса. Он включает буквы «http», возможно «s», двоеточие и две косые черты.
 *
 * - `([\da-z\.-]+)\.([a-z\.]{2,6})` соответствует последовательности букв, цифр, дефисов, символов подчеркивания и точек (домены и домен нулевого уровня – от 2 до 6 символов и точек)
 *
 * - `([\/\w\.-]*)*` необходим для идентификации файла: набор слов, состоящий из букв, цифр, дефисов, подчеркиваний и точек с косой чертой в конце. Наконец, за ним может следовать косая черта
 */
fun url() {
    val regex = Regex("""(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w\.-]*)*\/?""")

    val matchResult = regex.findAll("Jet Brains Website: https://www.jetbrains.com/ And here is information about Hyperskill: https://hi.hyperskill.org/how-we-teach")

    // https://www.jetbrains.com/
    // https://hi.hyperskill.org/how-we-teach
    for (matches in matchResult) {
        println(matches.value)
    }
}

/**
 * Читает строку, затем заменяет в ней все неоднократно встречающиеся буквы «а» на одну «а» (например, «ааААааа» необходимо заменить на один символ «а») и печатает полученный текст.
 *
 * Ваш код должен быть нечувствителен к регистру.
 *
 * Обратите внимание: вам необходимо заменить одну букву «А» на «а»
 */
fun replacesAllA() {
    val cases = listOf(
        "And I will aalwaaaaaays love you. I will Aaalways love you.",
        "Mamaaa, life had just begun, But now I've gone and thrown it all awaaaaAAAay"
    )

    val answers = listOf(
        "and I will always love you. I will always love you.",
        "Mama, life had just begun, But now I've gone and thrown it all away"
    )

    for (index: Int in cases.indices) {
        val result: String = cases[index].replace(Regex("[aA]+"), "a")

        println(result == answers[index])
        println(result)
    }
}

/**
 * Преобразует текст в из snake_case в CamelCase
 */
fun snakeCaseToCamel() {
    val input = "a_modern_prograMming_language_that__makes_developers_happier"
    val answer = "AModernProgrammingLanguageThatMakesDevelopersHappier"

    var listOfWords = input.split(Regex("_+"))

    if (listOfWords.size > 1) {
        listOfWords = listOfWords.map {
            it.lowercase().replaceFirstChar(Char::uppercaseChar)
        }
    }

    val result = listOfWords.joinToString("")

    println(result == answer)
    println(result)
}

/**
 * Функция парсинга имени и фамилии из адресов электронной почты.
 *
 * Например, если пользователь вводит адрес электронной почты jon.kirbi@gmail.com или jon_kirbi@gmail.com, результатом функции должна быть строка следующего вида: Jon Kirbi.
 *
 * Помните, что разделителем имени и фамилии может быть "." или "_".
 */
fun nicknameParser() {
    val emailString = "jon.kirbi@gmail.com"

    val nicknameString: String = emailString.split("@").first()

    val nickname = nicknameString
        .split(Regex("[._]"))
        .joinToString(" ") {
            it.replaceFirstChar(Char::uppercaseChar)
        }

    println(nickname)
}

/**
 * Принимает одну строку и количество подстрок для её разделения. Программа должна разбить строку на подстроки, используя любое количество пробелов (1 или более) в качестве разделителя.
 */
fun splittingWithSpaces() {
    val cases = listOf(
        "This   is a      text with   many     spaces",
        "Lorem    ipsum dolor sit, amet,    consectetur"
    )

    val limits = listOf(0, 3)

    for (index in cases.indices) {
        val text = cases[index]
        val limit = limits[index]

        text.split(Regex("\\s+"), limit).forEach {
            println(it)
        }
    }
}
