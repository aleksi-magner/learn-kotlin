import kotlin.math.pow

fun main() {
    /**
     * 128  64  32  16   8   4   2   1
     *  0    1   0   0   1   1   0   1
     *  |    |   |   |   |   |   |   |
     *  0   64   0   0   8   4   0   1
     *
     *  64 + 8 + 4 + 1 = 77
     */

    println(convertingBinaryNumbersToDecimal("")) // 0
    println(convertingBinaryNumbersToDecimal("100010")) // 34
    println(convertingBinaryNumbersToDecimal("100110")) // 38
    println(convertingBinaryNumbersToDecimal("100101")) // 37
    println(convertingBinaryNumbersToDecimal("101101")) // 45
    println(convertingBinaryNumbersToDecimal("01001101")) // 77
}

fun convertingBinaryNumbersToDecimal(number: String): Int {
    val reverseNumber = number.reversed()

    var result = 0

    repeat(number.length) {
        if (reverseNumber[it].digitToInt() > 0) {
            result += 2.toDouble().pow(it).toInt()
        }
    }

    return result
}
