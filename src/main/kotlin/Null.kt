fun main() {
    // Safe Calls
    var name: String? = null

    println(name?.length)

    name = "Kotlin"

    // Elvis Operator
    val length: Int? = name?.length

    // if (length != null) length else 0
    println(length ?: 0)

    val exceptionLength: Int = name?.length ?: throw Exception("The name is null")

    println(exceptionLength)

    // The !! Operator
    /**
     * Заменяет
     * val length: Int = name?.length ?: throw Exception("The name is null")
     */
    println(name!!.length)

    print("What is your nickname? ")

    // Null -> Ctrl + D
    val nickname: String? = readlnOrNull()

    println("Hello, $nickname!")

    // Idioms examples
    val nullString: String? = null
    val emptyString: String? = ""

    // if-not-null
    println(nullString?.length) // null
    println(emptyString?.length) // 0

    // if-not-null-else
    println(nullString?.length ?: -1) // -1
    println(emptyString?.length ?: -1) // 0

    // throw exceptions, Ctrl + D
    readlnOrNull() ?: error("No lines read")

    // nullable Boolean
    print("Введите булево значение или Ctrl+D: ")

    val bool: Boolean? = readlnOrNull()?.toBoolean()

    if (bool == true) {
        println("Boolean true")
    } else {
        println("Boolean $bool")
    }

    println(nullString?.isNotEmpty() ?: false) // false
    println(nullString?.isNotEmpty() == true) // false
    println(emptyString?.isNotEmpty() ?: false) // false
    println(emptyString?.isNotEmpty() == true) // false

    var x: String? = null

    x = x ?: "Hello"

    println(x) // "Hello"

    print("Что сказал Элвис? (или Ctrl+D): ")

    val input = readlnOrNull() ?: throw IllegalStateException()

    println("Elvis says: $input")

    println(reverse(654)) // 456
    println(reverse(null)) // -1
}

/**
 * Обращает заданное значение Int. Если данный Int равен нулю, возвращает -1.
 */
fun reverse(input: Int?): Int {
    val number: Int = input ?: 0

    if (number == 0) {
        return -1
    }

    return number.toString().reversed().toInt()
}
