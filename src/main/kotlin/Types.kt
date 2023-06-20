fun main() {
    // Integer numbers
    val byte: Byte = 127 // 1 байт, -128..127
    val short: Short = 32767 // 2 байта, -32768..32767
    val int: Int = 2_147_483_647 // 4 байта, -2147483648..2147483647
    val long: Long = 9_223_372_036_854_775_807 // 8 байт, -9223372036854775807..9223372036854775807
    val longPostfix = 2_000_000L

    // Floating-point types
    val float: Float = 2.1234567f // 4 байта, 6-7 цифр после запятой
    val floatPostfix = 1.51f
    val double: Double = 3.1415 // 8 байт, 14-16 цифр после запятой

    // Min and max values
    println("Byte: ${Byte.MIN_VALUE}..${Byte.MAX_VALUE}")
    println("Short: ${Short.MIN_VALUE}..${Short.MAX_VALUE}")
    println("Int: ${Int.MIN_VALUE}..${Int.MAX_VALUE}")
    println("Long: ${Long.MIN_VALUE}..${Long.MAX_VALUE}")
    println("Float: ${Float.MIN_VALUE}..${Float.MAX_VALUE}")
    println("Double: ${Double.MIN_VALUE}..${Double.MAX_VALUE}")

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
