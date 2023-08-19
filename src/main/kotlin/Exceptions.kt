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
    order()
    stackTrace()

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

/**
 * При обработке исключений порядок выстраивается сверху вниз от более конкретного (производного) класса к более общему.
 *
 * Всегда считается лучшей практикой перехватывать исключение супертипа при обработке нескольких исключений. Это помогает нам изящно обрабатывать неизвестные или неожиданные исключения.
 *
 * Иногда наш код может столкнуться с исключениями, которых мы не ожидали.
 *
 * Перехватывая супертип, мы можем обрабатывать такие исключения, не вызывая сбоя или непредсказуемого поведения нашей программы.
 */
fun order() {
    /**
     * Переставляя блоки catch и размещая более конкретные исключения первыми, мы обеспечиваем правильное выполнение соответствующего блока catch.
     */
    try {
        val input = "ome"

        println(100 / input.toInt())
    } catch (error: NumberFormatException) {
        println("You didn't type an INT number!")
    } catch (error: ArithmeticException) {
        println("You typed 0!")
    } catch (error: Exception) {
        println("What else can go wrong!")
    }

    /**
     * Оператор when может помочь нам написать более лаконичный код для перехвата нескольких исключений в одном блоке catch. Но вам всё равно нужно правильно упорядочивать блоки catch, чтобы более конкретные всегда обрабатывались первыми.
     */
    try {
        val input = "ome"

        println(100 / input.toInt())
    } catch (error: Exception) {
        when (error) {
            is NumberFormatException -> println("You didn't type an INT number!")
            is ArithmeticException -> println("You typed 0!")
            else -> println("What else can go wrong!")
        }
    }
}

/**
 * Трассировка стека - важная функция, которая поможет вам в отладке ваших приложений.
 *
 * Показывает стек вызовов в приложении до момента создания сообщения трассировки. Оно появляется в виде сообщения в вашей среде IDE, когда приложение выдаёт ошибку.
 */
fun stackTrace() {
    /**
     * Пример получения трассировки стека после того, как приложение выдаёт ошибку
     *
     * Если мы вводим слово вместо числа, приложение выдаёт ошибку и показывает следующее сообщение трассировки стека:
     *
     * Exception in thread "main" java.lang.NumberFormatException: For input string: "Kotlin"
     * 	at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)
     * 	at java.base/java.lang.Integer.parseInt(Integer.java:668)
     * 	at java.base/java.lang.Integer.parseInt(Integer.java:784)
     * 	at MainKt.main(Main.kt:6)
     * 	at MainKt.main(Main.kt)
     *
     * 	Во-первых, нам нужно прочитать верхнюю строку, где у нас есть три важные подсказки:
     *
     * 	1. Поток, в котором возникло исключение. Если вы помните, когда приложение запускается, оно создаёт основной поток.
     *
     * 	2. Класс, отвечающий за тип ошибки. В нашем случае это класс NumberFormatException из пакета java.lang.
     *
     * 	3. Сообщение, указывающее, почему было выбрано исключение (здесь ввод строки «Kotlin»). Далее вы увидите, как было сгенерировано это сообщение.
     *
     * 	Теперь давайте двигаться дальше и исследовать оставшиеся четыре строки. Вторая строка снизу указывает на строку 6, которая находится в методе main. Это строка программы, выполнение которой привело к исключению. Следующим вызываемым методом был toInt().
     *
     * 	Внутри этого метода в строке 784 был вызван другой перегруженный метод parseInt(s: String, radix: Int) из класса Integer.
     *
     * 	public actual inline fun String.toInt(): Int = java.lang.Integer.parseInt(this)
     *
     * 	public static int parseInt(String s) throws NumberFormatException {
     *    return parseInt(s,10);
     * }
     *
     * Во втором методе parseInt(String s, int radix) в строке 784 приложение выдает исключение, вызывающее метод NumberFormatException.forInputString(String s, int radix).
     *
     * if (digit < 0 || result < multmin) {
     *     throw NumberFormatException.forInputString(s, radix);
     * }
     *
     * Наконец, в четвёртой строке снизу мы видим вызов статического метода forInputString(s, radix) из класса NumberFormatException. Ниже, в строке 64, вы можете увидеть сообщение из приведенного выше примера трассировки стека. Так было сгенерировано сообщение из самой первой строки.
     *
     * static NumberFormatException forInputString(String s, int radix) {
     *     return new NumberFormatException("For input string: \"" + s + "\"" +
     *                                      (radix == 10 ?
     *                                       "" :
     *                                       " under radix " + radix));
     * }
     *
     * Следующая диаграмма представляет стек вызовов из приведённого выше примера. Поскольку стек вызовов представляет собой структуру данных LIFO, метод main(), который был вызван при запуске приложения, находится внизу, и он будет последним напечатанным элементом трассировки стека.
     *
     *          Stack Trace Elements
     * | forInputString(String s, int radix) | NumberFormatException
     * | parseInt(String s, int radix)       | Integer
     * | parseInt(String s)                  | Integer
     * | main()                              |
     */
    val input = "Kotlin"
    val number: Int = input.toInt() // Здесь возможно исключение!

    println(number + 1)

    /**
     * Что делать, если нужно получить трассировку стека в какой-то конкретный момент?
     *
     * Его можно получить без выдачи ошибки, вызвав метод Thread.currentThread().stackTrace.
     *
     * Таким образом, он возвращает массив StackTraceElement, и вы можете распечатать трассировку стека, используя цикл.
     *
     * Согласно официальной документации Java, класс StackTraceElement описывается как элемент в трассировке стека, представляющий один фрейм стека. То есть каждый элемент, возвращаемый функцией Thread.currentThread().getStackTrace(), представляет собой кадр стека, где элемент, напечатанный вверху, представляет точку выполнения, в которой была сгенерирована трассировка стека.
     *
     * Существуют и другие способы получения трассировки стека, такие как вызов методов Throwable().stackTrace или Throwable().printStackTrace().
     *
     * Вы можете найти их в документации и изучить самостоятельно.
     */
    for (element: StackTraceElement in Thread.currentThread().stackTrace) {
        println(element)
    }

    val validInput = "4"

    println("Valid parameter")

    /**
     * Если мы введём число и приложение не выдаст исключение, сообщение трассировки стека напечатает следующие строки:
     *
     * java.base/java.lang.Thread.getStackTrace(Thread.java:2550)
     * ExceptionsKt.demoStackTrace(Exceptions.kt:387)
     * ExceptionsKt.stackTrace(Exceptions.kt:374)
     * ExceptionsKt.main(Exceptions.kt:25)
     * ExceptionsKt.main(Exceptions.kt)
     *
     * Затем будет напечатан результат функции: 5
     */
    demoStackTrace(validInput)

    val invalidInput = "Kotlin"

    println("\nInvalid parameter")

    /**
     * Если мы введём строку и приложение выдаст исключение, сообщение трассировки стека напечатает следующие строки:
     *
     * java.base/java.lang.Thread.getStackTrace(Thread.java:2550)
     * ExceptionsKt.demoStackTrace(Exceptions.kt:387)
     * ExceptionsKt.stackTrace(Exceptions.kt:380)
     * ExceptionsKt.main(Exceptions.kt:25)
     * ExceptionsKt.main(Exceptions.kt)
     *
     * Затем будет напечатано исключение:
     *
     * Exception in thread "main" java.lang.NumberFormatException: For input string: "Kotlin"
     * 	at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)
     * 	at java.base/java.lang.Integer.parseInt(Integer.java:665)
     * 	at java.base/java.lang.Integer.parseInt(Integer.java:781)
     * 	at ExceptionsKt.demoStackTrace(Exceptions.kt:406)
     * 	at ExceptionsKt.stackTrace(Exceptions.kt:380)
     * 	at ExceptionsKt.main(Exceptions.kt:25)
     * 	at ExceptionsKt.main(Exceptions.kt)
     */
    demoStackTrace(invalidInput)
}

fun demoStackTrace(input: String) {
    /**
     * Самая полезная функция класса StackTraceElement заключается в том, что он предоставляет методы для упрощения этих строк и получения только необходимой информации. Если вы напечатаете println(element.className) внутри упомянутого цикла, вы получите сообщение трассировки стека в следующем виде:
     */
    for (element: StackTraceElement in Thread.currentThread().stackTrace) {
        println(element)

        println("---")

        println("Details:" +
            "\nCModule version: ${element.moduleVersion}" +
            "\nModule name: ${element.moduleName}" +
            "\nFile name: ${element.fileName}" +
            "\nClass loader name: ${element.classLoaderName}" +
            "\nClass name: ${element.className}" +
            "\nLine number: ${element.lineNumber}" +
            "\nMethod name: ${element.methodName}" +
            "\nIs native method: ${element.isNativeMethod}"
        )

        println("---")
    }

    val number: Int = input.toInt() // Здесь возможно исключение!

    println(number + 1)
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
