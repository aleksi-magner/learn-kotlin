fun main() {
    stringLength()
    concatenation()
    stringRepeat()
    rawStrings()
    stringTemplates()
    getStringChars()
    checkEmpty()
    substrings()
    splittingString()
    iteratingOverString()

    personInformation()
    luckyNumber()
    createEuphoniousWord()
}

/**
 * Длина строки
 */
fun stringLength() {
    val language = "Kotlin"
    val length: Int = language.length

    println(length) // 6

    val empty = ""

    println(empty.length) // 0
}

/**
 * Конкатенация строк
 */
fun concatenation() {
    val one = "1"
    val two = "2"
    val twelve = one + two

    println(one) // 1, no changes
    println(two) // 2, no changes
    println(twelve) // 12

    // Добавление значений
    val stringPlusBoolean = "abc" + 10 + true

    println(stringPlusBoolean) // abc10true

    val code = "123" + 456 + 789

    println(code) // 123456789

    val charPlusString = 'a' + "bc"

    println(charPlusString) // abc

    val stringPlusChar = "de" + 'f'

    println(stringPlusChar) // def
}

/**
 * Повтор строки
 */
fun stringRepeat() {
    print("Hello".repeat(4)) // HelloHelloHelloHello
    println()

    println("Eat. Sleep. Code.\n".repeat(7))
}

/**
 * Необработанная строка
 */
fun rawStrings() {
    // 'H' is the first letter of "Hello world!" string.
    println("\'H\' is the first letter of \"Hello world!\" string.")

    val largeString = """
        This is the house that Jack built.

        This is the malt that lay in the house that Jack built.

        This is the rat that ate the malt
        That lay in the house that Jack built.

        This is the cat
        That killed the rat that ate the malt
        That lay in the house that Jack built.
    """.trimIndent() // removes the first and the last lines and trims indents

    println(largeString)

    val unevenString = """
        123
         456
          789""".trimIndent()

    println(unevenString)

    val rawString = """123
         456
          789
""".trimIndent()

    println(rawString)

    val json = """
        {
            "firstName": "John",
            "lastName": "Smith",
            "age": 35,
            "phoneNumbers": [
                {
                    "type": "mobile",
                    "number": "123 567-7890"
                }
            ]
        }
    """.trimIndent()

    println(json)
}

/**
 * Шаблоны
 */
fun stringTemplates() {
    val city = "Paris"
    val temp = "24"

    println("Now, the temperature in $city is $temp degrees Celsius.")

    val firstName = "John"
    val lastName = "Smith"
    val fullName = "$firstName $lastName"

    println(fullName)

    val language = "Kotlin"

    println("$language has ${language.length} letters in the name")
}

/**
 * Получение символов строки
 */
fun getStringChars() {
    val greeting = "Hello"

    val first: Char = greeting[0] // 'H'
    val second: Char = greeting[1] // 'e'
    val five: Char = greeting[4] // 'o'
    val last: Char = greeting[greeting.length - 1] // 'o'
    val preLast: Char = greeting[greeting.length - 2] // 'l'

    println("First char: ${greeting.first()}") // 'H'
    println("First char: $first") // 'H'
    println("Second char: $second") // 'e'
    println("Five char: $five") // 'o'
    println("Last char: $last") // 'o'
    println("Last char: ${greeting.last()}") // 'o'
    println("Pre-last char: $preLast") // 'l'
    println("Last index: ${greeting.lastIndex}") // 4
}

/**
 * Проверка на пустоту
 */
fun checkEmpty() {
    val greeting = "Hello"

    println("String is empty: ${greeting.isEmpty()}") // false
    println("String is not empty: ${greeting.isNotEmpty()}") // true
}

/**
 * Получение новой строки из текущей.
 *
 * Функция подстроки принимает startIndex (включительно) и lastIndex (исключительно) в качестве аргументов и возвращает строку, которая начинается с startIndex и заканчивается непосредственно перед lastIndex.
 */
fun substrings() {
    val greeting = "Hello"

    println(greeting.substring(0, 3)) // "Hel"
    println(greeting.substring(1, 3)) // "el"

    // lastIndex можно не указывать, будет браться до конца строки
    println(greeting.substring(2)) // "llo"

    println(greeting.substring(4, 5)) // "o"
    println(greeting.substring(4, greeting.lastIndex + 1)) // "o"
    println(greeting.substring(4, greeting.length)) // "o"

    /**
     * Метод substring — не единственный способ получить часть строки. Вы также можете использовать функции substringAfter и substringBefore
     *
     * Эти функции принимают разделитель в качестве аргумента и возвращают строку до/после первого появления указанного разделителя. Если строка не содержит вхождений аргумента-разделителя, функция возвращает всю строку.
     */
    println(greeting.substringAfter('l'))  // "lo"
    println(greeting.substringBefore('o')) // "Hell"
    println(greeting.substringBefore('Я')) // "Hello"
    println(greeting.substringBefore('Я', "can't find a character")) // "can't find a character"

    /**
     * Функции substringBeforeLast и substringAfterLast имеют логику, аналогичную substringBefore и substringAfter, но возвращают строку до или после последнего вхождения разделителя.
     */
    println(greeting.substringAfterLast('l'))  // "o"
    println(greeting.substringBeforeLast('l')) // "Hel"

    /**
     * Замена частей строки
     *
     * Функция replace заменяет все вхождения первого аргумента в строке вторым аргументом.
     */
    val example = "Good morning..."

    println(example.replace("o", "O")) // "GOOd mOrning..."
    println(example.replace("morning", "bye")) // "Good bye..."
    println(example.replace('.', '!')) // "Good morning!!!"

    /**
     * Если вам нужно заменить только первое вхождение аргумента, используйте replaceFirst.
     */
    val example2 = "one one two three"

    println(example2.replaceFirst("one", "two")) // "two one two three"
    println(example2.replaceFirst("ONE", "two", true)) // "two one two three"

    println(example) // "Good morning..."
    println(greeting) // "Hello"

    /**
     * Изменение регистра
     */
    val uppercaseString = "UPPERCASE String"

    println(uppercaseString.lowercase()) // uppercase string

    val lowercaseString = "Lowercase String"

    println(lowercaseString.uppercase()) // LOWERCASE STRING
}

/**
 * Строка может быть разделена разделителями на список строк.
 */
fun splittingString() {
    val sentence = "a long text"

    val wordsList: List<String> = sentence.split(' ')

    println(wordsList) // ["a", "long", "text"]

    val number = "+1-213-345-6789"

    val parts = number.split('-')

    println(parts) // ["+1", "213", "345", "6789"]

    val text1 = "That's one small step for a man, one giant leap for mankind."

    // ["That's one small step for a man", " one giant leap for mankind."]
    println(text1.split(','))

    val text2 = "I'm gonna be a programmer"

    println(text2.split(" gonna be ")) // ["I'm", "a programmer"]
}

/**
 * Можно перебирать символы строки с помощью цикла
 */
fun iteratingOverString() {
    val scientistName = "Isaac Newton"

    // I s a a c   N e w t o n
    for (char in scientistName) {
        print("$char ") // print the current character
    }

    println()

    // Подсчитывает и печатает количество пробелов в строке str.
    val str = "strings are not primitive types!"

    var count = 0

    for (ch in str) {
        if (Character.isWhitespace(ch)) {
            count += 1
        }
    }

    println(count) // 4

    val rainbow = "ROYGCBV"

    /**
     * 1: R
     * 2: O
     * 3: Y
     * 4: G
     * 5: C
     * 6: B
     * 7: V
     */
    for (index in rainbow.indices){
        println("${index + 1}: ${rainbow[index]}")
    }
}

/**
 * Считывает из одной строки ввода имя, фамилию и возраст человека, а затем выводит информацию по шаблону
 */
fun personInformation() {
    print("Введите через пробел имя, фамилию и возраст: ")

    val (firstName, lastName, age) = readln().split(' ')

    println("${firstName.first()}. $lastName, $age years old")
}

/**
 * Дано число N с четным количеством цифр. Если сумма первой половины цифр равна сумме второй половины цифр, то это число считается счастливым. Для заданного числа выведите «YES», если это число удачное, иначе выведите «NO».
 */
fun luckyNumber() {
    print("Введите число с чётным количеством цифр: ")

    val input = readln()

    val first: Int = input.substring(0, input.length / 2).sumOf { it.digitToInt() }
    val last: Int = input.substring(input.length / 2).sumOf { it.digitToInt() }

    println(if (first == last) "YES" else "NO")
}

/**
 * Все буквы английского алфавита делятся на гласные и согласные.
 * Гласные: a, e, i, o, u, y.
 * Остальные буквы согласные.
 *
 * Слово считается благозвучным, если в нём нет трёх и более гласных или согласных подряд. В противном случае он считается неблагозвучным.
 *
 * Ваша задача состоит в том, чтобы из неблагозвучных слов составить благозвучные. В слово можно вставлять любые буквы. Выведите минимальное количество символов, необходимое для составления благозвучного слова из заданного.
 *
 * Например, слово «schedule» считается неблагозвучным, потому что в нём три согласных подряд: «sch». Чтобы создать благозвучное слово, вам нужно добавить любую гласную между «s» и «c» или между «c» и «h».
 */
fun createEuphoniousWord() {
    val list = listOf(
        "wwwwwwwwwwwwwwwwwwwwwwwwwwwww",
        "schedule",
        "garage",
        "player",
        "biiiiig"
    )

    val pattern = "([aeiouy]{3,}|[^aeiouy]{3,})"

    for (word in list) {
        val count: Int = Regex(pattern).findAll(word).sumOf { (it.value.length - 1) / 2 }

        println(count) // 14, 1, 0, 1, 2
    }
}
