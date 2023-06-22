import java.util.*

fun main() {
    readStandardInput()
    readHandleString()
    readCustomDelimiter()
}

fun readStandardInput() {
    val inputScanner = Scanner(System.`in`)

    val result = mutableListOf<String>()

    // Вызовы методов сканера ждут ввода для всех вызовов
    if (inputScanner.hasNextLine()) {
        val parsedLine = inputScanner.nextLine() // Читает всю строку. Например, "Hello, Kotlin"

        result.add("Line: $parsedLine")
    }

    if (inputScanner.hasNextInt()) {
        val parsedNumber = inputScanner.nextInt() // Читает число. Например, 123

        result.add("Number: $parsedNumber")
    }

    if (inputScanner.hasNext()) {
        val parsedWord = inputScanner.next() // Читает одно слово. Например, "Hello"

        result.add("Word: $parsedWord")
    }

    inputScanner.close()

    println(result.joinToString(separator = "\n"))
}

fun readHandleString() {
    val handleScanner = Scanner("Hello, Kotlin")

    println("Language: ${handleScanner.findInLine("Kotlin")}") // Kotlin

    handleScanner.close()
}

fun readCustomDelimiter() {
    val customDelimiterScanner = Scanner("123_456")

    customDelimiterScanner.useDelimiter("_")

    println("First number: ${customDelimiterScanner.nextInt()}") // 123
    println("Second number: ${customDelimiterScanner.nextInt()}") // 456

    customDelimiterScanner.close()
}
