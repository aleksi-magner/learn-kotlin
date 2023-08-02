fun main() {
    /**
     * Каждый символ — это просто символ, заключенный в одинарные кавычки. Тип Char представляет буквы (как прописные, так и строчные), цифры и другие символы:
     */
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

    /**
     * Конвертация числа в символы и наоборот.
     */
    val a = 'a'

    println(a.code) // 97

    val num = 97

    println(num.toChar()) // a

    /**
     * Если нужно прочитать один Char в целой строке, используйте такую конструкцию
     */
    val firstCharInString: Char = "Any string".first()

    println(firstCharInString) // A
    println(firstCharInString.category) // UPPERCASE_LETTER
    println(firstCharInString.directionality) // LEFT_TO_RIGHT
    println(firstCharInString.code) // 65
    println(firstCharInString.javaClass) // char

    /**
     * Получение последующих символов
     */
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

    /**
     * Escape-последовательности
     */
    val newline = '\n' // Новая строка
    val tab = '\t' // Табуляция
    val carriageReturn = '\r' // Перевод каретки
    val backslash = '\\'
    val singleQuote = '\''
    val doubleQuote = '\"'

    /**
     * Реляционные операции с символами
     *
     * Вы можете сравнивать символы с помощью реляционных операций (==, <, >, <=, >= и !=) в соответствии с их положением в таблице Unicode.
     */
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

    /**
     * Обработка символов
     */
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
    println("uppercaseChar: ${'я'.uppercaseChar()}") // 'Я'
    println("uppercase: ${'я'.uppercase()}") // "Я"
    println("lowercaseChar: ${'Я'.lowercaseChar()}") // 'я'
    println("lowercase: ${'Я'.lowercase()}") // "я"

    comparesIgnoringCases()
    comparingNumbersAndCharacters()
    digitsOrNot()
    vowelOrNo()
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
