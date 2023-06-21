/**
Лучше не использовать преобразования типов.
По крайней мере из большего в меньшее и за пределами диапазонов.
Могут быть непредвиденные и не корректные значения
 */
fun main() {
    // Conversion between numeric types
    val byte: Byte = 127
    val short: Short = byte.toShort()
    val int: Int = byte.toInt() - 2
    val truncatedInt: Byte = (int + 6000).toByte() // -19 WTF?
    val long: Long = byte.toLong()
    val float: Float = byte.toFloat()
    val double: Double = byte.toDouble() + 0.8
    val doubleToInt: Int = double.toInt()
    val char: Char = int.toChar() // '}'
    val charToNumber: Int = char.code // 125

    println(byte)
    println(short)
    println(int)
    println(truncatedInt)
    println(long)
    println(float)
    println(double)
    println(doubleToInt)
    println(char)
    println(charToNumber)

    // Conversion to Short and Byte types
    val floatNumber = 10f
    val doubleNumber = 1.0

    // Correct way
    val shortNumber = floatNumber.toInt().toShort()
    val byteNumber = doubleNumber.toInt().toByte()

    println("-------")
    println(shortNumber)
    println(byteNumber)

    // String conversion
    val n: Int = 8
    val d: Double = 10.09
    val c: Char = '@'
    val b: Boolean = true

    val intToString: String = n.toString()
    val stringToInt: Int = intToString.toInt()
    val doubleToString: String = d.toString()
    val stringToDouble: Double = doubleToString.toDouble()
    val charToString: String = c.toString()
    val booleanToString: String = b.toString()
    val stringToBoolean: Boolean = booleanToString.toBoolean()

    println("-------")
    println(intToString) // "8"
    println(stringToInt) // 8
    println(doubleToString) // "10.09"
    println(stringToDouble) // 10.09
    println(charToString) // "@"
    println(booleanToString) // "true"
    println(stringToBoolean) // true
    println("false".toBoolean()) // false
    println("False".toBoolean()) // false
    println("FALSE".toBoolean()) // false
    println("tru".toBoolean()) // false
    println("true".toBoolean()) // "true"
    println("True".toBoolean()) // "true"
    println("TRUE".toBoolean()) // "true"

    demonstration()
}

fun demonstration() {
    println("\nВведите число, чтобы увидеть преобразования. Например, 1000.0123456789")

    val something = readln() // Например, 1000.0123456789

    val d = something.toDouble()
    val f = d.toFloat()
    val i = f.toInt()
    val b = i.toByte()

    println("To double: $d") // 1000.0123456789 - ok
    println("To float: $f") // 1000.0123 - потеря точности
    println("To int: $i") // 1000 - отбрасывание дробной части
    println("To byte: $b") // -24 - переполнение типа, непредвиденное значение
    println("To boolean: ${something.toBoolean()}") // false - значение не равно "true" (регистр не важен)
}
