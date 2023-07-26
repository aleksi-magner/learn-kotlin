fun main() {
    /**
     * Беззнаковые числа создаются так же, как и любые другие. Для того, чтобы указать, что вы создаете беззнаковый номер, вам нужно добавить к нему суффикс «u» или «U».
     */
    // Integer numbers
    val byte: Byte = 127 // 1 байт, -128..127
    val uByte: UByte = 255U // 1 байт, 0..255
    val short: Short = 32767 // 2 байта, -32768..32767
    val uShort: UShort = 65535U // 2 байта, 0..65535
    val int: Int = 2_147_483_647 // 4 байта, -2147483648..2147483647
    val uInt: UInt = 4_294_967_295U // 4 байта, 0..4294967295
    val long: Long = 9_223_372_036_854_775_807 // 8 байт, -9223372036854775807..9223372036854775807
    val longPostfix = 2_000_000L
    val uLong: ULong = 18_446_744_073_709_551_615U // 8 байт, 0..18446744073709551615
    val uLongPostfix = 2_000_000UL

    // Floating-point types
    val float: Float = 2.1234567F // 4 байта, 6-7 цифр после запятой
    val floatPostfix = 1.51F
    val double: Double = 3.1415 // 8 байт, 14-16 цифр после запятой

    /**
     * Any? - супер тип Any, допускающий null
     * │
     * └───Any - супер тип всех типов
     * │   │
     * │   └───Number - супер тип чисел
     * │   │   │
     * │   │   └───Byte
     * │   │   └───UByte
     * │   │   └───Short
     * │   │   └───UShort
     * │   │   └───Int
     * │   │   └───UInt
     * │   │   └───Long
     * │   │   └───ULong
     * │   │   └───Float
     * │   │   └───Double
     * │   │
     * │   └───Unit - Ничего не возвращает. Аналог void или undefined
     * │
     * └───Nothing? - не имеет экземпляров. Любой код, следующий за выражением этого типа, недоступен.
     *     │
     *     └───Nothing
     */

    // Min and max values
    println("Byte: ${Byte.MIN_VALUE}..${Byte.MAX_VALUE}")
    println("U Byte: ${UByte.MIN_VALUE}..${UByte.MAX_VALUE}")
    println("Short: ${Short.MIN_VALUE}..${Short.MAX_VALUE}")
    println("U Short: ${UShort.MIN_VALUE}..${UShort.MAX_VALUE}")
    println("Int: ${Int.MIN_VALUE}..${Int.MAX_VALUE}")
    println("U Int: ${UInt.MIN_VALUE}..${UInt.MAX_VALUE}")
    println("Long: ${Long.MIN_VALUE}..${Long.MAX_VALUE}")
    println("U Long: ${ULong.MIN_VALUE}..${ULong.MAX_VALUE}")
    println("Float: ${Float.MIN_VALUE}..${Float.MAX_VALUE}")
    println("Double: ${Double.MIN_VALUE}..${Double.MAX_VALUE}")

    println("Variable type: ${floatPostfix::class.simpleName}")

    // Size of an integer type in bytes or bits
    println(Int.SIZE_BYTES) // 4
    println(Int.SIZE_BITS)  // 32

    // Characters, 2 байта
    val lowerCaseLetter: Char = 'ж'
    val upperCaseLetter = 'Q'
    val number = '1'
    val space = ' '
    val dollar = '$'

    // Booleans, 1 бит
    val enabled: Boolean = true
    val bugFound = false

    // Strings
    val text: String = "Hello, I am studying Kotlin now."
    val creditCardNumber = "1234 5678 9012 3456"

    typeCastAndSmartCast()
}

// Не возвращает управление, выполнение кода останавливается. Поэтому тип Nothing
fun fail(): Nothing {
    throw Exception("Fail!")
}

/**
 * Функция принимает аргумент типа Any, что означает, что она может принимать любой тип объекта.
 *
 * Внутри функции мы используем when с is для проверки типа входного объекта
 */
fun processInput(input: Any) {
    when (input) {
        is Int -> println("Input is an integer")
        is String -> println("Input is a string")
        is Double -> println("Input is a double")
        else -> println("Unknown input")
    }
}

/**
 * Чтобы проверить, является ли объект экземпляром определённого параметра типа, мы можем использовать оператор is с параметром типа в угловых скобках.
 */
inline fun <reified T> exampleFunction1(obj: Any) {
    if (obj is T) {
        println("obj is an instance of type parameter T")
    } else {
        println("obj is not an instance of type parameter T")
    }
}

/**
 * Мы можем привести объект к параметру типа, используя оператор as с параметром типа в угловых скобках. Однако если объект не является экземпляром параметра типа, будет выброшено исключение ClassCastException.
 *
 * Чтобы избежать этого, мы можем использовать оператор безопасного приведения as?, который возвращает null, если приведение невозможно.
 */
fun <T> exampleFunction2(obj: Any) {
    val tObj: T? = obj as? T

    if (tObj != null) {
        println("obj can be safely cast to type parameter T")
    } else {
        println("obj cannot be cast to type parameter T")
    }
}

/**
 * Проверка типов и приведение типов необходимы для любого языка программирования. Проверки типов позволяют разработчикам проверять, принадлежит ли объект к определённому типу данных, а приведения типов позволяют программистам преобразовывать объект из одного типа в другой.
 *
 * Kotlin, будучи языком со статической типизацией, имеет несколько функций, делающих проверку и приведение типов простыми и безопасными в использовании.
 */
fun typeCastAndSmartCast() {
    println("--- is and !is operators ---")

    /**
     * Оператор is возвращает значение true, если объект принадлежит к указанному типу, и значение false, если нет. И наоборот, оператор !is возвращает true, если объект не принадлежит к указанному типу, и false, если принадлежит.
     */
    val obj: Any = "Hello, Kotlin"

    if (obj is String) {
        println(obj.uppercase()) // HELLO, KOTLIN
    } else {
        println("obj is not a String") // Не будет вызван, т.к. obj строка и проходит условие
    }

    processInput(42) // Input is an integer
    processInput(obj) // Input is a string
    processInput(3.14) // Input is a double
    processInput(3.14f) // Unknown input

    println("--- Smart casts ---")

    /**
     * В Kotlin также есть функция, известная как умные приведения (smart casts).
     *
     * Смарт-приведения используются для упрощения кода при работе с типами, допускающими значение null.
     *
     * Когда тип, допускающий значение null, проверяется с помощью оператора is, Kotlin автоматически приводит объект к типу, не допускающему значение null.
     */
    val any: Any? = null

    if (any is String) {
        println(any.length) // Не будет вызван
    } else {
        println("any is not a String") // Будет вызван
    }

    println("--- 'Unsafe' cast operator ---")

    /**
     * В Kotlin есть небезопасный оператор приведения, который представлен ключевым словом as. Ключевое слово as используется для приведения объекта к ненулевому типу. Если объект не может быть приведён к указанному типу, оператор as создаёт исключение ClassCastException
     */
    val obj2: Any = "Hello, Kotlin"

    // Используем оператор as для приведения переменной obj2 к строке
    val str2: String = obj2 as String // Unsafe cast operator

    println(str2.uppercase()) // HELLO, KOTLIN

    // NullPointerException: null cannot be cast to non-null type kotlin.String
    // println(any as String)

    println("--- 'Safe' (nullable) cast operator ---")

    /**
     * `as?` оператор используется для приведения объекта к типу, допускающему значение null. Если объект не может быть приведён к указанному типу, оператор `as?` возвращает null.
     */
    val obj3: Any = 123
    val str3: String? = obj3 as? String // Safe (nullable) cast operator

    if (str3 != null) {
        println(str3.uppercase()) // Не будет вызван
    }

    println(any as? String) // null

    println("--- Generics type checks and casts ---")

    /**
     * В Kotlin мы также можем использовать проверки типов и приведения типов с дженериками. При работе с дженериками нам может понадобиться проверить, является ли объект экземпляром определённого параметра типа или привести его к параметру типа.
     *
     * Чтобы проверить, является ли объект экземпляром определённого параметра типа, мы можем использовать оператор is с параметром типа в угловых скобках.
     *
     * Важно отметить, что в Kotlin с дженериками происходит стирание типа, а это означает, что фактический тип универсального объекта неизвестен во время выполнения. Поэтому некоторые операции, такие как создание нового экземпляра параметра типа или проверка того, является ли параметр типа подтипом другого класса, невозможны.
     */

    println("--- Tasks ---")

    val list = listOf("abc", 42, '$', 3.14f, "string")

    println(countStrings(list)) // 2
}

/**
 * Принимает список объектов и возвращает количество строк в списке. Если элемент списка не является строкой, его необходимо пропустить.
 *
 * Функция должна использовать операторы is или !is для проверки типа элементов списка.
 */
fun countStrings(list: List<Any>): Int = list.count { it is String }
