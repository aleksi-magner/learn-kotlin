import java.util.*
import java.io.Reader
import java.io.FileReader
import java.io.File
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

@Throws(IOException::class)
fun readFile() {
    var reader: Reader? = null

    try {
        reader = FileReader("file.txt")

        throw RuntimeException("Exception1")
    } finally {
        reader?.close() // throws new RuntimeException("Exception2")
    }
}

/**
 * Когда создаётся входной поток, JVM уведомляет ОС о своём намерении работать с файлом.
 *
 * Если у процесса JVM достаточно прав и всё в порядке, ОС возвращает файловый дескриптор — специальный индикатор, используемый процессом для доступа к файлу.
 *
 * Проблема в том, что количество файловых дескрипторов ограничено. По этой причине важно уведомить ОС о том, что задание выполнено и удерживаемый файловый дескриптор может быть освобождён для дальнейшего повторного использования.
 *
 * В предыдущих примерах для этой цели мы вызывали метод close. После вызова JVM освобождает все системные ресурсы, связанные с потоком.
 *
 * Лучше всего поместить любой код, относящийся к системным ресурсам, в метод use.
 */
fun tryWithResources() {
    readFile() // FileNotFoundException: file.txt (Нет такого файла или каталога)

    /**
     * Чтобы не пропускать исключения и более корректно обрабатывать чтение ресурсов, нужно использовать метод use
     */
    FileReader("../resources/file1.txt").use { reader1 ->
        println("Reader 1: $reader1")

        FileReader("../resources/file2.txt").use {
            println("Reader 2: $it")

            // some code
        }
    }

    /**
     * Однако освобождать следует не только ресурсы, основанные на файлах. Закрытие также имеет решающее значение для других внешних источников, таких как подключение к сети или базе данных. Классы, которые их обрабатывают, имеют метод close и, следовательно, могут быть обёрнуты оператором try-with-resources
     *
     * Предположим, что-то пошло не так, содержимое файла — "123 not_number", где второй аргумент — String.
     *
     * Это приводит к исключению java.util.InputMismatchException при анализе второго аргумента.
     *
     * Метод use гарантирует правильное освобождение ресурсов, связанных с файлами.
     */
    Scanner(File("file.txt")).use { scanner ->
        val first: Int = scanner.nextInt()
        val second: Int = scanner.nextInt()

        println("arguments: $first $second")
    }
}
