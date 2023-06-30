import kotlin.math.pow

fun main() {
    /**
     * Binary | Decimal | Hexadecimal
     *     0  |    0    |     0
     *     1  |    1    |     1
     *    10  |    2    |     2
     *    11  |    3    |     3
     *   100  |    4    |     4
     *   101  |    5    |     5
     *   110  |    6    |     6
     *   111  |    7    |     7
     *  1000  |    8    |     8
     *  1001  |    9    |     9
     *  1010  |   10    |     A
     *  1011  |   11    |     B
     *  1100  |   12    |     C
     *  1101  |   13    |     D
     *  1110  |   14    |     E
     *  1111  |   15    |     F
     * 10000  |   16    |    10
     */

    /**
     * Decimal, десятичная
     * База 10
     * 4251
     * 4*10^3 + 2*10^2 + 5*10^1 + 1*10^0
     * 4*1000 + 2*100 + 5*10 + 1*1
     * 4000 + 200 + 50 + 1 = 4251
     */

    /**
     * Binary, двоичная
     * База 2
     * 1011
     * 1*2^3 + 0*2^2 + 1*2^1 + 1*2^0
     * 1*8 + 0*4 + 1*2 + 1*1
     * 8 + 0 + 2 + 1 = 11
     *
     * 8   4   2   1
     * 1   0   1   1
     * |   |   |   |
     * 8   0   2   1
     *
     *  8 + 0 + 2 + 1 = 11
     */

    /**
     * Hexadecimal (HEX), шестнадцатеричная
     * База 16
     * Запись числа, чтобы не путать с десятичной системой
     * 63h или 0x63
     *
     * 101100 -> 10 1100 -> 0010 1100 -> 0x2C, или 2C, или 2Ch
     * 1110011001110001 -> 1110 0110 0111 0001 -> 0xE671, или E671, или E671h
     *
     * 0xBE -> 1011 1110 -> 10111110
     * 44 -> 101100 -> 0x2C
     * 0xBE -> 10111110 -> 190
     */

    println("Binary to decimal")

    println(binaryToDecimal(0)) // 0
    println(binaryToDecimal(100010)) // 34
    println(binaryToDecimal(100110)) // 38
    println(binaryToDecimal(100101)) // 37
    println(binaryToDecimal(101101)) // 45
    println(binaryToDecimal(1001101)) // 77

    println("Decimal to binary")

    println(decimalToBinary(0)) // 0
    println(decimalToBinary(34)) // 100010
    println(decimalToBinary(38)) // 100110
    println(decimalToBinary(37)) // 100101
    println(decimalToBinary(45)) // 101101
    println(decimalToBinary(77)) // 1001101

    println("Binary to HEX")

    println(binaryToHex(101100)) // "0x2C"
    println(binaryToHex(10142100)) // ""
    println(binaryToHex(1110011001110001)) // "0xE671"

    println("HEX to binary")

    println(hexToBinary("0x2C")) // 101100
    println(hexToBinary("2C")) // 101100
    println(hexToBinary("2Ch")) // 101100
    println(hexToBinary("0xE671")) // 1110011001110001
    println(hexToBinary("E671")) // 1110011001110001
    println(hexToBinary("E671h")) // 1110011001110001
    println(hexToBinary("E6ZZZ71h")) // 0

    println("Decimal to HEX")

    println(decimalToHex(44)) // "0x2C"
    println(decimalToHex(174)) // "0xAE"

    println("HEX to decimal")

    println(0x0) // 0
    println(0xBE) // 190
    println(hexToDecimal("0x2C")) // 44
    println(hexToDecimal("0xAE")) // 174
}

/**
 * 128 64  32  16   8   4   2   1
 *  0   1   0   0   1   1   0   1
 *  |   |   |   |   |   |   |   |
 *  0  64   0   0   8   4   0   1
 *
 *  64 + 8 + 4 + 1 = 77
 */
fun binaryToDecimal(number: Long): Int {
    val base = 2.0
    val value: String = number.toString()

    var result = 0

    for ((index: Int, char: Char) in value.withIndex()) {
        if (char.digitToInt() > 0) {
            val power: Int = value.length - 1 - index

            result += base.pow(power).toInt()
        }
    }

    return result
}

/**
 * 11
 * 11 / 2 = 5, остаток 1, бит 0
 * 5 / 2 = 2, остаток 1, бит 1
 * 2 / 2 = 1, остаток 0, бит 2
 * 1 / 2 = 0, остаток 1, бит 3
 *
 * 1011
 */
fun decimalToBinary(number: Long): Long {
    var quotient: Long = number
    var result = ""

    do {
        val remainder: Long = quotient % 2

        quotient /= 2

        result += remainder
    } while (quotient != 0L)

    return result.reversed().toLong()
}

fun binaryToHex(number: Long): String {
    val binaryRegex = Regex("^[01]+$")

    var value: String = number.toString()

    val isValid: Boolean = binaryRegex.matches(value)

    if (!isValid) {
        return ""
    }

    var hex = ""

    val amountLeadingZeroes: Int = value.length % 4

    repeat(amountLeadingZeroes) {
        value = "0$value"
    }

    while (value.isNotEmpty()) {
        val sequence = value.substring(0, 4)

        value = value.substring(4)

        when (sequence) {
            "0000" -> hex += "0"
            "0001" -> hex += "1"
            "0010" -> hex += "2"
            "0011" -> hex += "3"
            "0100" -> hex += "4"
            "0101" -> hex += "5"
            "0110" -> hex += "6"
            "0111" -> hex += "7"
            "1000" -> hex += "8"
            "1001" -> hex += "9"
            "1010" -> hex += "A"
            "1011" -> hex += "B"
            "1100" -> hex += "C"
            "1101" -> hex += "D"
            "1110" -> hex += "E"
            "1111" -> hex += "F"
        }
    }

    return "0x$hex"
}

fun hexToBinary(hex: String): Long {
    val hexSymbols = Regex("(0x|h)")
    val hexRegex = Regex("^[0-9A-F]+$")

    val value = hexSymbols.replace(hex, "")

    val isValid: Boolean = hexRegex.matches(value)

    if (!isValid) {
        return 0
    }

    var binary = ""

    for (char: Char in value) {
        when (char) {
            '0' -> binary += "0000"
            '1' -> binary += "0001"
            '2' -> binary += "0010"
            '3' -> binary += "0011"
            '4' -> binary += "0100"
            '5' -> binary += "0101"
            '6' -> binary += "0110"
            '7' -> binary += "0111"
            '8' -> binary += "1000"
            '9' -> binary += "1001"
            'A' -> binary += "1010"
            'B' -> binary += "1011"
            'C' -> binary += "1100"
            'D' -> binary += "1101"
            'E' -> binary += "1110"
            'F' -> binary += "1111"
        }
    }

    return binary.toLong()
}

fun decimalToHex(number: Long): String {
    val binary: Long = decimalToBinary(number)

    return binaryToHex(binary)
}

fun hexToDecimal(number: String): Int {
    val binary: Long = hexToBinary(number)

    return binaryToDecimal(binary)
}
