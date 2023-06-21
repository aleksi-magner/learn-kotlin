import java.util.*

fun main() {
    val inputScanner = Scanner(System.`in`)

    // Вызовы методов сканера ждут ввода для всех вызовов
    if (inputScanner.hasNextLine()) {
        val line = inputScanner.nextLine() // Читает всю строку. Например, "Hello, Kotlin"

        println("Line: $line")
    }

    if (inputScanner.hasNextInt()) {
        val num = inputScanner.nextInt() // Читает число. Например, 123

        println("Number: $num")
    }

    if (inputScanner.hasNext()) {
        val string = inputScanner.next() // Читает одно слово. Например, "Hello"

        println("Word: $string")
    }

    inputScanner.close()

    val handleScanner = Scanner("Hello, Kotlin")

    println("Language: ${handleScanner.findInLine("Kotlin")}") // Kotlin

    handleScanner.close()

    val customDelimiterScanner = Scanner("123_456")

    customDelimiterScanner.useDelimiter("_")

    println("First number: ${customDelimiterScanner.nextInt()}") // 123
    println("Second number: ${customDelimiterScanner.nextInt()}") // 456

    customDelimiterScanner.close()
}
