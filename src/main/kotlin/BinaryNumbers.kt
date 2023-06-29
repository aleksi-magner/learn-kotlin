import kotlin.math.pow

fun main() {
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
