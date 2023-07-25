fun main() {
    // Длина строки
    val language = "Kotlin"
    val length: Int = language.length

    println(length) // 6

    val empty = ""

    println(empty.length) // 0

    // Конкатенация
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

    // Повтор строки
    print("Hello".repeat(4)) // HelloHelloHelloHello
    println()

    println("Eat. Sleep. Code.\n".repeat(7))

    // Необработанная строка
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

    print(largeString)
    println()

    val unevenString = """
        123
         456
          789""".trimIndent()

    print(unevenString)
    println()

    val rawString = """123
         456
          789
""".trimIndent()

    print(rawString)
    println()

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

    print(json)
    println()

    // Шаблоны
    val city = "Paris"
    val temp = "24"

    println("Now, the temperature in $city is $temp degrees Celsius.")

    val firstName = "John"
    val lastName = "Smith"
    val fullName = "$firstName $lastName"

    println(fullName)

    println("$language has ${language.length} letters in the name")

    /**
     * Получение символов строки
     */
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

    println("String is empty: ${greeting.isEmpty()}") // false
    println("String is not empty: ${greeting.isNotEmpty()}") // true

    personInformation()
}

/**
 * Считывает из одной строки ввода имя, фамилию и возраст человека, а затем выводит информацию по шаблону
 */
fun personInformation() {
    print("Введите через пробел имя, фамилию и возраст: ")

    val (firstName, lastName, age) = readln().split(' ')

    println("${firstName.first()}. $lastName, $age years old")
}
