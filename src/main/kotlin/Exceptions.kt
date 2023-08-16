import kotlin.math.pow

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
    creatingCustomExceptions()

    // Handle exceptions
    println(calculateBrakingDistance("6", "-1")) // 18
    println(calculateBrakingDistance("10", "0")) // -1, The car does not slow down!
    println(calculateBrakingDistance("nine", "one")) // -1, For input string: "nine"

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

class LessThanZero : Exception("Parameter less than zero")

class GreaterThanTen : Exception("Parameter greater than ten")

/**
 * Создания кастомных исключений
 *
 * Самый ценный механизм обработки ошибок в Kotlin — это исключения. Однако во многих случаях нам необходимо реализовать более точную технику обработки ошибок, чем обеспечивают встроенные классы исключений. Здесь в игру вступают пользовательские исключения.
 *
 * Исключение можно вызвать с помощью ключевого слова throw, за которым следует объект исключения, то есть либо сам объект Exception, либо любой из его подтипов.
 */
fun creatingCustomExceptions() {
    /**
     * Генерация исключения без каких-либо параметров.
     *
     * Это не очень полезный случай, потому что здесь нет информации об ошибке:
     * throw Exception()
     *
     * Генерация исключения с сообщением:
     * throw Exception("My error message")
     *
     * Генерация исключения с сообщением и причиной, которая обычно является другим исключением"
     * throw Exception("My error message", cause)
     *
     * Генерация исключения только с параметром причины:
     * throw Exception(error) // где error - ещё одно исключение
     *
     * Генерация подтипа объекта исключения.
     * В следующем примере мы выбрасываем исключение NullPointerException с нашим пользовательским сообщением:
     * throw NullPointerException("NPE at Alpha point")
     */

    /**
     * В примере ArithmeticException выбрасывается с сообщением «/ by zero».
     *
     * Мы фиксируем это исключение и выбрасываем новое с другим сообщением и с экземпляром ArithmeticException в качестве параметра причины.
     */
    fun testFunction(a: Int, b: Int): Int = a / b

    /**
     * Exception in thread "main" java.lang.Exception: testFunction() failed
     * 	at ErrorsKt.creatingCustomExceptions(Errors.kt:118)
     * 	at ErrorsKt.main(Errors.kt:24)
     * 	at ErrorsKt.main(Errors.kt)
     * Caused by: java.lang.ArithmeticException: / by zero
     * 	at ErrorsKt.creatingCustomExceptions$testFunction(Errors.kt:113)
     * 	at ErrorsKt.creatingCustomExceptions(Errors.kt:116)
     * 	... 2 more
     */
    try {
        testFunction(1, 0)
    } catch (error: Exception) {
        throw Exception("testFunction() failed", error)
    }

    /**
     * Мы можем создавать свои собственные классы исключений как простые подклассы класса Exception.
     *
     * В следующем примере мы создаем 2 пользовательских исключения с именами LessThanZero и GreaterThanTen.
     */
    fun myFunction(par: Int) {
        if (par < 0) {
            throw LessThanZero()
        } else if (par > 10) {
            throw GreaterThanTen()
        } else {
            println("OK")
        }
    }

    /**
     * Exception in thread "main" LessThanZero: Parameter less than zero
     * 	at ErrorsKt.creatingCustomExceptions$myFunction(Errors.kt:149)
     * 	at ErrorsKt.creatingCustomExceptions(Errors.kt:164)
     * 	at ErrorsKt.main(Errors.kt:24)
     * 	at ErrorsKt.main(Errors.kt)
     */
    myFunction(-3)

    /**
     * Обратите внимание, что пользовательское исключение может быть подклассом любого ранее существовавшего подкласса исключения.
     */
    open class MyException : ArithmeticException("My message")

    class MySubclassException : MyException()

    /**
     * Пользовательские исключения обрабатываются точно так же, как и обычные исключения.
     */
    class BetweenOneAndFive: Exception("Value between 1 and 5")
    class BetweenSixAndTen: Exception("Value between 6 and 10")

    fun myFunction2() {
        /**
         * Получить случайное целое число от 1 до 10
         *
         * Это простой способ получить случайное целое число. Целые числа в диапазоне 1..10 перемешиваются, а затем берётся первое.
         */
        val randomInteger = (1..10).shuffled().first()

        if (randomInteger <= 5) {
            throw BetweenOneAndFive()
        } else {
            throw BetweenSixAndTen()
        }
    }

    try {
        myFunction2()
    } catch (error: BetweenOneAndFive) {
        println("BetweenOneAndFive was thrown")
    } catch (error: BetweenSixAndTen) {
        println("BetweenSixAndTen was thrown")
    }

    /**
     * Класс Exception имеет 4 разных конструктора, в то время как другие подклассы исключений могут иметь как минимум два.
     *
     * Если мы хотим создать подклассы исключений для библиотеки, мы можем захотеть, чтобы у них было несколько конструкторов.
     *
     * В следующем примере мы реализуем несколько конструкторов, используя ключевое слово `super`. Это вызывает конструктор базового типа.
     *
     * Чтобы использовать этот метод, производное исключение не должно иметь первичного конструктора. Для каждого конструктора инициализируется соответствующий базовый конструктор исключений (или делегируется новому конструктору).
     *
     * Синтаксис каждого вторичного конструктора показан в следующем примере кода. За новым конструктором следует `:`, а затем ключевое слово `super` с соответствующими параметрами.
     *
     * Обратите внимание, что Throwable является базовым классом для всех ошибок и исключений.
     */
    class MyCustomException: Exception {
        constructor() : super() // Без параметров
        constructor(message: String?) : super(message) // Только строковый параметр
        constructor(message: String?, cause: Throwable?) : super(message, cause) // Оба параметра
        constructor(cause: Throwable?) : super(cause) // Только параметр исключения
    }

    // throw MyCustomException()
    // throw MyCustomException("My exception message")
    // throw MyCustomException("My exception message", otherException)
    // throw MyCustomException(otherException)

    /**
     * В следующем примере мы создаем пользовательское исключение, производное от ArithmeticException. У него всего 2 конструктора:
     */
    class MyArithmeticException: ArithmeticException {
        constructor() : super()
        constructor(message: String?) : super(message)
    }
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

fun calculateBrakingDistance(initialSpeed: String, acceleration: String): Int {
    try {
        return -initialSpeed.toDouble().pow(2).toInt() / (2 * acceleration.toInt())
    } catch (error: ArithmeticException) {
        println("The car does not slow down!")
    } catch (error: Exception) {
        println(error.message)
    }

    return -1
}
