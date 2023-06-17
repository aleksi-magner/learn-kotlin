const val stringConstant = "This is a constant string"
const val numberConstant = 2
const val doubleConstant = 3.14

fun main() {
  val number1: Int
  val number2 = 5

  println("String constant: $stringConstant")
  println("Number constant: $numberConstant")
  println("Double constant: $doubleConstant")

  number1 = 35 + number2 + numberConstant

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
