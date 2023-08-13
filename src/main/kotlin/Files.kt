import java.io.File

/**
 * Данные хранятся на дисках по определенным адресам. Для компьютера такими адресами являются числа. Он использует их для поиска соответствующей информации. Однако люди не так хорошо запоминают наборы чисел. Нам гораздо проще назвать наши данные, например, семейное фото, отчет за ноябрь и так далее. Поэтому было придумано понятие файла: в файле можно хранить некоторую информацию под удобным для пользователя именем.
 *
 * Файл связан с частью данных. Однако существуют некоторые ограничения на присвоение имени файлу. Например, имена файлов должны содержать только те символы, которые поддерживаются конкретной файловой системой. Существуют различные типы содержимого файлов: текст, фото, музыка, видео и т. д. Тип информации, хранящейся в файле, определяет формат файла. Для того чтобы компьютер мог различать, какой формат у того или иного файла, были придуманы расширения файлов.
 */
fun main() {
    readingFiles()
}

/**
 * В Kotlin существует множество различных способов чтения файлов, но давайте пока сосредоточимся только на некоторых из них. Основным классом для обработки файлов является File из пакета java.io. Kotlin предоставляет дополнительную функциональность по сравнению с реализацией Java с помощью функций расширения. Это означает, что, просто импортировав java.io.File в свой проект, вы можете использовать функциональные возможности как Java, так и Kotlin File. Давайте подробнее рассмотрим эти функции расширения.
 */
fun readingFiles() {
    println("--- Reading files ---")

    readText()
    readLines()
    readBytes()
    forEachLine()
    fileSeparator()
}

/**
 * Функция readText(), которая помогает вам прочитать весь файл всего в одной строковой переменной.
 *
 * Если файла в файловой системе нет, то выбрасывается исключение FileNotFoundException
 *
 * Метод exists() вернёт false в случае отсутствия файла и true, если Kotlin его найдёт.
 *
 * На самом деле вы можете использовать любой метод из File, например, length() или delete().
 */
fun readText() {
    println("- readText -")

    val fileName = "src/main/resources/reading.txt"

    val lines = File(fileName).readText()

    print(lines)

    /**
     * Вместо того чтобы сразу читать наш файл в переменную String, мы можем сначала создать ссылку, а затем прочитать текст:
     */
    val file = File(fileName)

    print(file.readText())

    println(file.name) // reading.txt
    println(file.freeSpace) // 169697669120
    println(file.usableSpace) // 154891464704
    println(file.totalSpace) // 290005221376
    println(file.isFile) // true
    println(file.isDirectory) // false
    println(file.isHidden) // false
    println(file.absolutePath) // /home/magner/Projects/learn-kotlin/src/main/resources/reading.txt
    println(file.canonicalPath) // /home/magner/Projects/learn-kotlin/src/main/resources/reading.txt
    println(file.canRead()) // true
    println(file.canWrite()) // true
    println(file.length()) // 29
    println(file.lastModified()) // 1691946504613
    println(file.extension) // txt

    if (file.exists()) {
        print(file.readText())
    } else {
        print("No such file") // Не будет вызван, файл есть
    }

    val unknownFile = File("src/new.txt")

    if (unknownFile.exists()) {
        print(unknownFile.readText()) // Не будет вызван, файла нет
    } else {
        println("No such file")
    }

    /**
     * Мы также можем предоставить конкретную кодировку для чтения файла.
     *
     * По умолчанию readText() имеет кодировку UTF-8, но вы можете изменить ее, когда захотите.
     *
     * Также Kotlin не рекомендует использовать эту функцию с файлами размером более 2 Гб, так как это может вызвать OutOfMemoryError.
     */
    val line = File(fileName).readText(Charsets.ISO_8859_1)

    print(line)
}

/**
 * Функция readLines() обеспечивает функциональность чтения всех строк из файла и сохранения их в списке
 *
 * Этот метод имеет те же ограничения по размеру и спецификации кодировки, что и readText().
 */
fun readLines() {
    println("- readLines -")

    val fileName = "src/main/resources/reading.txt"

    val lines = File(fileName).readLines()

    for (line in lines){
        println(line)
    }
}

/**
 * Функция readBytes() может быть полезна, если вам нужно сохранить содержимое файла в виде массива байтов.
 *
 * Эта функция возвращает ByteArray. Структура Array похожа на MutableList, вы не можете изменять ее размер, но можете изменять элементы. Вы можете легко преобразовать MutableList в ByteArray и наоборот с помощью функций toByteArray() и toMutableList().
 *
 * По-прежнему не рекомендуется использовать эту опцию с большими файлами (2 Гб и более).
 *
 * Этот метод используется как реализация функции readText() с преобразованием в String в исходных файлах Kotlin.
 */
fun readBytes() {
    println("- readBytes -")

    val fileName = "src/main/resources/reading.txt"

    val lines: ByteArray = File(fileName).readBytes()

    // 75, 111, 116, 108, 105, 110, 32, 111, 114, 32, 74, 97, 118, 97, 44, 10, 74, 97, 118, 97, 32, 111, 114, 32, 67, 43, 43, 46, 10
    println(lines.joinToString())
}

/**
 * forEachLine() — рекомендуемый способ чтения больших файлов.
 *
 * Этот подход к чтению лямбда обеспечивает действие (в нашем случае println()) для каждой строки.
 *
 * Всегда есть вероятность, что файл, который вы собираетесь читать, уже был открыт в другом процессе или у него могут быть ограничения доступа. В таких случаях генерируется исключение AccessDeniedException.
 */
fun forEachLine() {
    println("- forEachLine -")

    val fileName = "src/main/resources/reading.txt"

    File(fileName).forEachLine { println(it) }
}

/**
 * В наших примерах мы использовали переменную fileName со значением пути «src/reading.txt».
 *
 * Обратите внимание, что разделитель пути к файлу является символом, зависящим от платформы.
 *
 * Например, для Windows это «\», а для UNIX и MAC — «/».
 *
 * Чтобы избежать ошибок при доступе к файлу, мы можем использовать File.separator из библиотеки java.io.File. File.separator возвращает значение системного разделителя в зависимости от используемой системы.
 */
fun fileSeparator() {
    println("- File separator -")

    val separator = File.separator
    val fileName = listOf("src", "main", "resources", "reading.txt").joinToString(separator)

    println("File path: $fileName") // src/main/resources/reading.txt

    File(fileName).forEachLine { println(it) }
}
