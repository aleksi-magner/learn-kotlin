fun main() {
    declaration()
    characterConversion()
    characterDetail()
    characterOffset()
    escapeSequence()
    relationalOperations()
    checks()
    transform()
    charSequence()

    comparesIgnoringCases()
    comparingNumbersAndCharacters()
    digitsOrNot()
    vowelOrNo()

    println(countUniqueChars("abacaba")) // 3
    println(containsOnlyAlphabets("Hello_world")) // false
    println(containsOnlyAlphabets("HelloWorld")) // true
}

/**
 * Объявление символов
 *
 * Каждый символ — это просто символ, заключенный в одинарные кавычки. Тип Char представляет буквы (как прописные, так и строчные), цифры и другие символы
 */
fun declaration() {
    val lowerCaseLetter: Char = 'a'
    val upperCaseLetter: Char = 'Q'
    val number: Char = '1'
    val space: Char = ' '
    val dollar: Char = '$'

    /**
     * Символ также может быть представлен его шестнадцатеричным кодом в таблице Unicode.
     * Код начинается с \u:
     */
    val at = '\u0040' // it represents '@'

    println(at) // @
}

/**
 * Конвертация числа в символы и наоборот
 */
fun characterConversion() {
    val a = 'a'

    println(a.code) // 97

    val num = 97

    println(num.toChar()) // a
}

fun characterDetail() {
    /**
     * Если нужно прочитать первый Char в целой строке, используйте такую конструкцию
     */
    val firstCharInString: Char = "Any string".first()

    println(firstCharInString) // A
    println(firstCharInString.category) // UPPERCASE_LETTER
    println(firstCharInString.directionality) // LEFT_TO_RIGHT
    println(firstCharInString.code) // 65
    println(firstCharInString.javaClass) // char
}

/**
 * Получение последующих символов
 */
fun characterOffset() {
    val x = 'x'
    val y = x + 1 // 'y'
    val w = y - 2 // 'w'

    println(x) // x
    println(y) // y
    println(w) // w

    /**
     * Нельзя добавлять символ к числу, складывать символы друг с другом, умножать или делить символы на числа.
     * Это вызовет ошибку
     */
    // val error1 = 1 + x // Error
    // val error2 = x + y // Error
    // val error3 = x * 2 // Error
    // val error4 = x / 2 // Error
}

/**
 * Escape-последовательности
 */
fun escapeSequence() {
    val newline = '\n' // Новая строка
    val tab = '\t' // Табуляция
    val carriageReturn = '\r' // Перевод каретки
    val backslash = '\\'
    val singleQuote = '\''
    val doubleQuote = '\"'
}

/**
 * Реляционные операции с символами
 *
 * Вы можете сравнивать символы с помощью реляционных операций (==, <, >, <=, >= и !=) в соответствии с их положением в таблице Unicode.
 */
fun relationalOperations() {
    println('a' < 'c')  // true
    println('x' >= 'z') // false

    println('D' == 'D') // true
    println('Q' != 'q') // true потому что заглавные и строчные буквы не одно и то же

    println('A' < 'a')  // true потому что заглавные латинские буквы ставятся перед маленькими

    /**
     * Используя реляционные операции и коды, мы можем проверить, является ли Char цифрой: все десять цифр имеют коды от '\u0030' до '\u0039'.
     */
    val input: Char = readln().first()
    val isDigit: Boolean = input in '\u0030'..'\u0039'

    println(isDigit)
}

/**
 * Проверка принадлежности
 */
fun checks() {
    println("isDigit: ${'7'.isDigit()}") // true
    println("isDigit: ${'Я'.isDigit()}") // false
    println("isLetter: ${'7'.isLetter()}") // false
    println("isLetter: ${'Я'.isLetter()}") // true
    println("isLetterOrDigit: ${'7'.isLetterOrDigit()}") // true
    println("isLetterOrDigit: ${'Я'.isLetterOrDigit()}") // true
    println("isLetterOrDigit: ${'@'.isLetterOrDigit()}") // false
    println("isWhitespace: ${' '.isWhitespace()}") // true
    println("isWhitespace: ${'\n'.isWhitespace()}") // true
    println("isWhitespace: ${'\t'.isWhitespace()}") // true
    println("isWhitespace: ${'_'.isWhitespace()}") // false
    println("isUpperCase: ${'Я'.isUpperCase()}") // true
    println("isUpperCase: ${'я'.isUpperCase()}") // false
    println("isLowerCase: ${'я'.isLowerCase()}") // true
    println("isLowerCase: ${'Я'.isLowerCase()}") // false
}

/**
 * Изменение регистра
 */
fun transform() {
    println("uppercaseChar: ${'я'.uppercaseChar()}") // 'Я'
    println("uppercase: ${'я'.uppercase()}") // "Я"
    println("lowercaseChar: ${'Я'.lowercaseChar()}") // 'я'
    println("lowercase: ${'Я'.lowercase()}") // "я"
}

/**
 * Способ представления последовательности символов, известный как CharSequence
 *
 * CharSequence в Kotlin — это интерфейс, представляющий последовательность символов.
 *
 * Он предназначен для неизменности, что означает, что его содержимое не может быть изменено после его создания.
 *
 * Интерфейс CharSequence определяет несколько методов, которые можно использовать для доступа к символам в последовательности и управления ими.
 *
 * - `[index]` возвращает символ по указанному индексу
 *
 * - subSequence() возвращает новый CharSequence, представляющий диапазон символов из исходного CharSequence
 *
 * - contains() проверяет, присутствует ли указанный символ или последовательность символов в CharSequence
 *
 * CharSequence можно использовать в ситуациях, когда нам нужно представить последовательность символов, но заранее неизвестна точная реализация последовательности.
 *
 * Например, если мы хотим прочитать большой файл в память, мы можем не знать точную длину файла, поэтому мы можем использовать CharSequence для представления содержимого файла.
 *
 * CharSequence также можно использовать для реализации пользовательских классов строк, которые должны представлять последовательность символов.
 *
 * Любой объект String также может использоваться как объект CharSequence, поскольку String реализует интерфейс CharSequence.
 */
fun charSequence() {
    val charSequence: CharSequence = "example"

    println("CharSequence: $charSequence") // CharSequence: example

    val charAt: Char = charSequence[0]

    println("Char at index 0: $charAt") // Char at index 0: e

    val subSequence: CharSequence = charSequence.subSequence(1, 4)

    println("Sub-sequence from index 1 to 4: $subSequence") // Sub-sequence from index 1 to 4: xam

    val contains: Boolean = charSequence.contains("amp", ignoreCase = true)

    println("CharSequence contains 'amp': $contains") // CharSequence contains 'amp': true

    /**
     * Наиболее существенное различие между CharSequence и String заключается в том, что CharSequence — это интерфейс, тогда как String — это конкретный класс в Kotlin.
     *
     * CharSequence можно рассматривать как более общий интерфейс, который реализует String. Любая строка является CharSequence, но не каждая CharSequence является строкой. Ещё одно различие между CharSequence и String — чувствительность к регистру. CharSequence чувствителен к регистру, а String не чувствителен к регистру.
     *
     * В приведённом ниже примере мы создаем String и CharSequence, ссылающиеся на одну и ту же строку.
     *
     * Мы можем вызвать функцию uppercase() для объекта String, чтобы преобразовать её в верхний регистр. Однако мы не можем вызвать uppercase() для объекта CharSequence, поскольку интерфейс CharSequence не определяет эту функцию.
     *
     * Таким образом, String — это конкретный класс, который реализует интерфейс CharSequence и предоставляет дополнительные функции.
     *
     * CharSequence — это интерфейс, определяющий последовательность символов, которая может быть реализована различными классами.
     */
    val string: String = "Hello, World!"
    val charSequence1: CharSequence = string

    println(string.uppercase()) // "HELLO, WORLD!"

    // Следующая строка не скомпилируется, потому что в CharSequence нет функции uppercase().
    // println(charSequence1.uppercase())

    // Преобразование CharSequence в String
    println(charSequence1.toString().uppercase()) // "HELLO, WORLD!"
}

/**
 * Читает две латинские буквы как символы и сравнивает их без учёта регистра.
 *
 * Если эти символы представляют одну и ту же букву, выводит true, иначе false.
 */
fun comparesIgnoringCases() {
    val first: Char = readln().first().lowercaseChar()
    val second: Char = readln().first().lowercaseChar()

    println(first == second)
}

/**
 * Считывает одно число и один символ в отдельных строках и проверяет, соответствует ли введённое число десятичному представлению символа в таблице Unicode.
 */
fun comparingNumbersAndCharacters() {
    val number: Int = readln().toInt()
    val char: Char = readln().first()

    println(number == char.code)
}

/**
 * Читает четыре символа и проверяет для каждого символа, является ли он цифрой.
 *
 * Программа должна вывести либо true, либо false для каждого символа в новой строке.
 */
fun digitsOrNot() {
    repeat(4) {
        val char: Char = readln().first()

        println(char.isDigit())
    }
}

fun isVowel(char: Char): Boolean = "aeiou".contains(char, ignoreCase = true)

/**
 * Проверяет, является ли буква основного латинского алфавита гласной. Вход - одна буква.
 *
 * Не считайте букву 'y' гласной.
 *
 * Ваша функция должна принимать значение типа Char и возвращать логическое значение.
 */
fun vowelOrNo() {
    println(isVowel('a')) // true
    println(isVowel('A')) // true
    println(isVowel('b')) // false
}

/**
 * Принимает CharSequence в качестве входных данных и возвращает количество уникальных символов в последовательности
 */
fun countUniqueChars(sequence: CharSequence): Int = sequence.toSet().size

/**
 * Принимает CharSequence в качестве входных данных и возвращает логическое значение, указывающее, содержат ли входные данные только буквенные символы.
 */
fun containsOnlyAlphabets(charSequence: CharSequence): Boolean = charSequence.all { it.isLetter() }
