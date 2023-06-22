fun main() {
    println("Enter any string:")

    val string = readln()

    print("You entered the string: ")
    print(string)
    println()

    println("Enter any number:")

    val number = readln().toInt()

    print("You entered the number: ")
    print(number)
    println()

    println("Enter any big number:")

    val bigNumber = readln().toLong()

    print("You entered the big number: ")
    print(bigNumber)
    println()

    println("Enter any double type number:")

    val doubleNumber = readln().toDouble()

    print("You entered the double type number: ")
    print(doubleNumber)
    println()

    println("Enter true or false:")

    val boolean = readln().toBoolean()

    print("You entered: ")
    print(boolean)
    println()

    // Reading multiple values in one line
    println("Enter message:")

    val (a, b) = readln().split(" ")

    println("First word: $a")
    println("Second word: $b")
}
