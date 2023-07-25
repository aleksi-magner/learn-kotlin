fun main() {
    print("Enter any string: ")

    val string = readln()

    println("You entered the string: $string")

    print("Enter any number: ")

    val number = readln().toInt()

    println("You entered the number: $number")

    print("Enter any big number: ")

    val bigNumber = readln().toLong()

    println("You entered the big number: $bigNumber")

    print("Enter any double type number: ")

    val doubleNumber = readln().toDouble()

    println("You entered the double type number: $doubleNumber")

    print("Enter true or false: ")

    val boolean = readln().toBoolean()

    println("You entered: $boolean")

    // Reading multiple values in one line
    print("Enter message: ")

    val (a, b) = readln().split(' ')

    println("First word: $a")
    println("Second word: $b")
}
