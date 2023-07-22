import java.io.IOException

fun main() {
    /**
     * Переменные, объявленные в блоке try, будут доступны только внутри блока: с ними нельзя работать ни снаружи, ни в блоке catch.
     */

    /**
     * Технически исключение может быть обработано в методе, в котором оно возникло, или в вызывающем методе.
     * Наилучший подход к обработке исключения состоит в том, чтобы сделать это в методе, который имеет достаточно информации для принятия правильного решения на основе этого исключения.
     */

    handlerSuitable()
    gettingInfoAboutExceptions()
    multipleExceptions()
    handlerUnsuitable()
    finallyBlock()
}

fun handlerSuitable() {
    println("Handler suitable. Before the try-catch block") // это будет напечатано

    try {
        println("Inside the try block before an exception") // это будет напечатано
        println(2 / 0) // он выдает ArithmeticException
        println("Inside the try block after the exception") // это НЕ будет напечатано
    } catch (error: ArithmeticException) {
        println("Division by zero!") // это будет напечатано
    }

    println("Handler suitable. After the try-catch block") // это будет напечатано
}

fun gettingInfoAboutExceptions() {
    try {
        val d = (2 / 0).toDouble()
    } catch (error: Exception) {
        println("Exception message: ${error.message}") // / by zero
    }
}

fun multipleExceptions() {
    /**
     * Вы можете добавить столько блоков catch, сколько вам нужно. Когда в блоке try возникает исключение, система выполнения определяет первый подходящий блок catch в соответствии с типом исключения; сопоставление идёт сверху вниз.
     * Блок catch с базовым типом должен быть написан ниже всех блоков с подтипами. Другими словами, более специализированные обработчики (такие как IOException) должны быть написаны до более общих (таких как Exception). В противном случае блок с подтипом будет проигнорирован.
     */
    try {
        // code that throws exceptions
    } catch (error: IOException) {
        // handling the IOException and its subtypes
    } catch (error: Exception) {
        // handling the Exception and its subtypes
    }
}

fun handlerUnsuitable() {
    println("Handler unsuitable. Before the try-catch block") // это будет напечатано

    try {
        println("Inside the try block before an exception") // это будет напечатано
        println(2 / 0) // он выдает ArithmeticException
        println("Inside the try block after the exception") // это НЕ будет напечатано
    } catch (error: NumberFormatException) { // Не верный тип исключения приведёт к падению программы
        println("Division by zero!") // это будет напечатано
    }

    println("Handler unsuitable. After the try-catch block") // это будет напечатано
}

fun finallyBlock() {
    /**
     * Если убрать блок catch, код после finally не выполнится
     */
    try {
        println("Inside the try block")
        println(2 / 0) // throws ArithmeticException
    }
    catch (error: Exception) {
        println("Inside the catch block")
    }
    finally {
        println("Inside the finally block")
    }

    println("After the try-catch-finally block")

    val string = "abc"

    var number = 0 // try to avoid var if possible

    try {
        number = string.toInt()
    } catch (e: NumberFormatException) {
        number = -1
    } finally {
        println(number)
    }
}
