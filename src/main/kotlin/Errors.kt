fun main() {
    /**
     * Compile-time errors.
     * Синтаксические ошибки: неверное ключевое слово, опечатка, отсутствие скобки или фигурной скобки.
     * Неправильное имя импортируемого пакета.
     * Вызов несуществующего метода.
     */

    /**
     * Run-time errors.
     * Логические ошибки — когда программа выдает неверный результат из-за неправильного кода (например, вместо «Hello!» ваша программа выводит «Hi!»);
     * Необработанные исключения, такие как деление на ноль, не найденные файлы и другие непредвиденные случаи.
     */

    /**
     * Exception.
     * Ошибка может произойти во время работы программы, даже если она синтаксически верна и скомпилирована без каких-либо проблем.
     */
    exception()

    hierarchyOfExceptions()

    // Handle exceptions
    calculateSpentMoney(-10, 4)
    makeAnException()
}

// Нет гарантий, что пользователь введёт именно число
// NumberFormatException: For input string: "> Hi :)"
fun exception(): Int {
    print("> ")

    return readln().toInt()
}

// Иерархия типов исключений
fun hierarchyOfExceptions() {
    /**
     * Throwable
     * │
     * └───Exception
     * │   │
     * │   └───RuntimeException - могут создаваться во время выполнения программы. Эти исключения обычно вызваны недостаточными проверками
     * │   │    │
     * │   │    └───ArithmeticException - недопустимые арифметические операции
     * │   │    │
     * │   │    └───NumberFormatException - ожидается число, получаем что-то другое
     * │   │    │
     * │   │    └───IndexOutOfBoundsException - доступ к несуществующему индексу
     * │   │        │
     * │   │        └───StringIndexOutOfBoundsException
     * │   │
     * │   └───IOException
     * │
     * └───Error - указывает на серьёзные проблемы, которые разумное приложение не должно пытаться обработать
     */

    /**
     * Основой иерархии является тип Throwable.
     * Это корень иерархии, и все исключения так или иначе относятся к этому типу.
     * Он предоставляет набор полезных методов для обработки исключений.
     * И, поскольку все исключения в Kotlin являются потомками Throwable,
     * все эти методы доступны для каждого исключения.
     */

    // throws ArithmeticException
    // val example = 2 / 0

    val string = "It's not a number"

    // throws NumberFormatException
    // val number = string.toInt()

    val sequence = "string"

    // throws StringIndexOutOfBoundsException
    // println(sequence[10]) // Нет такого индекса в строке
}

// Ручное создание исключений
fun calculateSpentMoney(total: Int, itemPrice: Int): Int {
    if (total < 0) {
        val error = Exception("Total can't be negative")

        println("Any code here...")

        throw error
    }

    if (itemPrice < 0) {
        throw Exception("Item price can't be negative")
    }

    if (itemPrice == 0) {
        return 0
    }

    val amountToBuy = total / itemPrice

    return amountToBuy * itemPrice
}

fun makeAnException(): Nothing {
    throw Exception("I'm an exception!")
}

fun returnValue(): Int {
    val value = readln().toInt()

    if (value > 0) {
        throw Exception("It's too big")
    }

    return value
}
