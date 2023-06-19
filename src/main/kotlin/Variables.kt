const val STRING_CONSTANT = "This is a constant string"
const val NUMBER_CONSTANT = 2
const val DOUBLE_CONSTANT = 3.14

fun main() {
    val number1: Int
    val number2 = 5

    println("String constant: $STRING_CONSTANT")
    println("Number constant: $NUMBER_CONSTANT")
    println("Double constant: $DOUBLE_CONSTANT")

    number1 = 35 + number2 + NUMBER_CONSTANT

    println("Number value: $number1")

    // List creation
    val myMutableList = mutableListOf(1, 2, 3, 4, 5)

    // Adding a new element
    myMutableList.add(6)

    println(myMutableList)

    var variable = "Any variable"

    variable += " add string"

    println("Variable: $variable")
}
