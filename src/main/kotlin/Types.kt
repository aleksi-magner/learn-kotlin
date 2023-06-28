fun main() {
    /**
     * Беззнаковые числа создаются так же, как и любые другие. Для того, чтобы указать, что вы создаете беззнаковый номер, вам нужно добавить к нему суффикс «u» или «U».
     */
    // Integer numbers
    val byte: Byte = 127 // 1 байт, -128..127
    val uByte: UByte = 255U // 1 байт, 0..255
    val short: Short = 32767 // 2 байта, -32768..32767
    val uShort: UShort = 32767U // 2 байта, 0..65535
    val int: Int = 2_147_483_647 // 4 байта, -2147483648..2147483647
    val uInt: UInt = 2_147_483_647U // 4 байта, 0..4294967295
    val long: Long = 9_223_372_036_854_775_807 // 8 байт, -9223372036854775807..9223372036854775807
    val longPostfix = 2_000_000L
    val uLong: ULong = 9_223_372_036_854_775_807U // 8 байт, 0..18446744073709551615
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
}

// Не возвращает управление, выполнение кода останавливается. Поэтому тип Nothing
fun fail(): Nothing {
    throw Exception("Fail!")
}
