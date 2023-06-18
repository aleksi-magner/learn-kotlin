// **Documentation comments**
/**
 * The `main` function accepts string arguments from outside.
 *
 * @param args arguments from the command line.
 */
fun main(args: Array<String>) {
  // **End-of-line comments**
  // The line below will be ignored
  // println("Hello, World")

  // This prints the string "Hello, Kotlin"
  println("Hello, Kotlin")  // Here can be any comment
  /// Valid single-line comment

  // **Multi-line comments**
  /* This is a single-line comment */
  /*  This is an example of
      a multi-line comment */

  /*** Valid multiline comment
    println("Hello")
    println("World")
  **/

  /*
    System.out.println("Hello")  // print "Hello"
    System.out.println("Kotlin") /* print "Kotlin" */
  */
}
