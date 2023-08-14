import java.io.File

/**
 * Данные хранятся на дисках по определенным адресам. Для компьютера такими адресами являются числа. Он использует их для поиска соответствующей информации. Однако люди не так хорошо запоминают наборы чисел. Нам гораздо проще назвать наши данные, например, семейное фото, отчет за ноябрь и так далее. Поэтому было придумано понятие файла: в файле можно хранить некоторую информацию под удобным для пользователя именем.
 *
 * Файл связан с частью данных. Однако существуют некоторые ограничения на присвоение имени файлу. Например, имена файлов должны содержать только те символы, которые поддерживаются конкретной файловой системой. Существуют различные типы содержимого файлов: текст, фото, музыка, видео и т. д. Тип информации, хранящейся в файле, определяет формат файла. Для того чтобы компьютер мог различать, какой формат у того или иного файла, были придуманы расширения файлов.
 */
fun main() {
    // Определение текущего каталога
    println("Current directory: ${System.getProperty("user.dir")}") // /home/magner/Projects/learn-kotlin

    readingFiles()
    writingFiles()
    filesAndDirectories()
    methodsForIterating()
    fileCopying()
}

fun getFileURL(filename: String): String = listOf("src", "main", "resources", filename).joinToString(File.separator)

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

    countLinesWithNumbers()
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

/**
 * Есть файл words_with_numbers.txt, который содержит последовательность слов и цифр.
 *
 * Напишите программу, которая считает только строки, содержащие числа в этом файле.
 *
 * Введите результат.
 */
fun countLinesWithNumbers() {
    val fileName = "src/main/resources/words_with_numbers.txt"
    val numberRegex = Regex("[0-9]+")

    var count = 0

    File(fileName).forEachLine {
        if (it.contains(numberRegex)) {
            count += 1
        }
    }

    println(count)
}

/**
 * В Kotlin есть разные способы записи в файл. Все их мы здесь рассматривать не будем: начнем с избранных основ.
 */
fun writingFiles() {
    println("--- Writing files ---")

    writeText()
    formattingAndProcessing()
    appendText()
    writingByteArrays()
}

/**
 * writeText() - запись файла на диск с каким-то содержимым и перезапись содержимого файла
 *
 * Если указанный файл не существовал по этому пути, он будет создан.
 *
 * Если файл имеет ограничения доступа или уже был открыт в другом процессе, создается исключение AccessDeniedException.
 */
fun writeText() {
    println("- writeText -")

    val fileName: String = getFileURL("MyFile.txt")
    val textToFile = "If we learn to process our code carefully, we’ll grow as programmers."

    val myFile = File(fileName) // Объект файла

    myFile.writeText(textToFile) // Запись в файл и сохранение на диск

    // Коротко
    // File(fileName).writeText(textToFile)
}

/**
 * Иногда нам нужно применить форматирование, чтобы улучшить расположение текста.
 */
fun formattingAndProcessing() {
    println("- Formatting and processing -")

    /**
     * Например, нам может понадобиться переместить текст на новую строку. В этом случае \n пригодится:
     */
    val fileName: String = getFileURL("MyFile.txt")
    val textToFile1 = "Just \nlook\n at\n that!"

    /**
     * Если вам нужно сделать абзац с отступом, используйте \t:
     */
    val textToFile2 = "\tThere’s a tab"

    File(fileName).writeText(textToFile1)
    File(fileName).writeText(textToFile2)
}

/**
 * appendText(text: String) добавляет содержимое к имеющемуся файлу
 */
fun appendText() {
    println("- appendText -")

    val fileName: String = getFileURL("MyFile.txt")

    val startContent = "Beware of bugs in the above code; I have only proved it correct, not tried it"
    val appendContent = ", Donald E. Knuth said."

    File(fileName).writeText(startContent)
    File(fileName).appendText(appendContent)
}

/**
 * Иногда вам нужно писать байты, а не строки.
 *
 * writeBytes(array: ByteArray) — запись в файл массива байтов.
 *
 * appendBytes(array: ByteArray) – аналогична методу appendText(), но применяется только к массиву байтов
 *
 * Обратите внимание, что эти функции работают с ByteArray.
 *
 * Структура Array похожа на MutableList, вы не можете изменять её размер, но можете изменять элементы.
 *
 * Вы можете легко преобразовать MutableList в ByteArray и наоборот с помощью функций toByteArray() и toMutableList().
 */
fun writingByteArrays() {
    println("- Writing byte arrays -")

    val fileName: String = getFileURL("textFromByteArray.txt")

    // "If we learn to process our code carefully"
    val startArrayOfBytes: ByteArray = byteArrayOf(73, 102, 32, 119, 101, 32, 108, 101, 97, 114, 110, 32, 116, 111, 32, 112, 114, 111, 99, 101, 115, 115, 32, 111, 117, 114, 32, 99, 111, 100, 101, 32, 99, 97, 114, 101, 102, 117, 108, 108, 121)

    // Другой способ
    // val arrayOfBytes = mutableListOf<Byte>(1, 2, 3).toByteArray()

    File(fileName).writeBytes(startArrayOfBytes)

    // ", we’ll grow as programmers."
    val appendArrayOfBytes: ByteArray = byteArrayOf(44, 32, 119, 101, -30, -128, -103, 108, 108, 32, 103, 114, 111, 119, 32, 97, 115, 32, 112, 114, 111, 103, 114, 97, 109, 109, 101, 114, 115, 46)

    File(fileName).appendBytes(appendArrayOfBytes)
}

/**
 * Файл — это именованная область данных на носителе информации, используемая в качестве основного объекта взаимодействия с данными в операционных системах.
 *
 * Каталог — это элемент файловой системы, упрощающий организацию файлов. Типичная файловая система содержит большое количество файлов, и каталоги помогают организовать её, группируя их вместе. Вы также можете работать с ними как с файловыми объектами.
 *
 * - File.isDirectory проверяет, является ли файл каталогом
 *
 * - File.isFile проверяет, является ли файл файлом.
 *
 * - File.exists() проверяет, существует ли файл.
 *
 * - File.resolve(String) возвращает файл с именем String в каталоге.
 *
 * - File.resolve(File) возвращает файл File в каталоге.
 *
 * - File.createNewFile() создаёт новый файл.
 *
 * - File.mkdir() создаёт каталог.
 */
fun filesAndDirectories() {
    println("--- Files and Directories ---")

    val outDir = File("outDir")

    println(outDir.exists()) // false

    val created: Boolean = outDir.mkdir()

    println(outDir.exists()) // true
    println(outDir.isDirectory) // true

    val file: File = outDir.resolve("newFile.txt") // outDir/newFile.txt

    println(file.exists()) // false

    file.createNewFile()

    println(file.exists()) // true
    println(file.isFile) // true

    outDir.deleteRecursively()
}

/**
 * Вы можете перебирать файловую иерархию с помощью методов java.io.File:
 *
 * - File.getParentFile() возвращает экземпляр java.io.File, представляющий родительский каталог файла, или null, если у этого файла нет родителя (что означает, что это корень).
 *
 * - File.getParent() возвращает строковое представление родительского каталога файла или null, если у этого файла нет родителя.
 *
 * - File.listFiles() возвращает массив файлов, расположенных в заданном каталоге, или null, если этот экземпляр не является каталогом.
 *
 * - File.list() возвращает массив файлов и каталогов в каталоге, определённом этим абстрактным путём.
 *
 * kotlin.io предоставляет специальные методы, которые позволяют перебирать всю файловую иерархию. Рассмотрим три основных метода:
 *
 * - File.walk(direction): FileTreeWalk предоставляет каталоги и файлы, которые мы можем посетить в этом каталоге; нам нужно указать, как именно мы будем выполнять итерацию (вверх или вниз по иерархической структуре);
 *
 * - File.walkBottomUp(): FileTreeWalk предоставляет каталоги и файлы, которые мы можем посетить в этом каталоге. Он использует поиск в глубину, а каталоги посещаются после всех их файлов;
 *
 * - File.walkTopDown(): FileTreeWalk предоставляет каталоги и файлы, которые мы можем посетить в этом каталоге. Мы используем поиск в глубину, и каталоги посещаются перед всеми их файлами.
 *
 * Класс FileTreeWalk используется для итерации по заданной файловой системе. Это позволяет вам перебирать все файлы в данном каталоге. Метод iterator возвращает итератор, просматривающий файлы. Вы можете перебрать эту структуру или преобразовать ее в список с помощью функции toList().
 */
fun methodsForIterating() {
    println("--- Methods for Iterating through file hierarchies ---")

    // A hierarchy example

    /**
     * Files
     * │
     * └───CompletedProjects
     * │   │
     * │   └───HelloWorld
     * │   │   │
     * │   │   └───Doc.pdf
     * │   │   └───Reviews.txt
     * │   │
     * │   └───JCalculator
     * │       │
     * │       └───Doc.pdf
     * │
     * └───Music
     *     │
     *     └───Soundtracks
     *         │
     *         └───<empty>
     */

    println("- listFiles -")

    /**
     * Получение содержимого каталога и родителя каталога/файла
     *
     * listFiles() печатает содержимое (файлы и каталоги) выбранного каталога.
     */
    val helloWorld = File("/Files/CompletedProjects/HelloWorld")

    if (helloWorld.exists()) {
        // [Doc.pdf, Reviews.txt]
        val helloWorldFilesNames: List<String> = helloWorld.listFiles()?.map { it.name } ?: emptyList()
    }

    val reviews = File("/Files/CompletedProjects/HelloWorld/Reviews.txt")

    if (reviews.exists()) {
        // Не является каталогом, null
        val reviewsFiles = reviews.listFiles() // null
    }

    val soundtracks = File("/Files/Music/SoundTracks")

    if (soundtracks.exists()) {
        // Пустой каталог
        val soundtracksFiles = soundtracks.listFiles() // []
    }

    println("- parent -")

    /**
     * Свойство File.parent возвращает имя родителя файла или каталога.
     *
     * Свойство File.parentFile возвращает родительский файл или каталог как файл.
     *
     * Если каталог является корнем файловой иерархии, вы получите null. Будьте осторожны, чтобы не получить исключения!
     */
    val files = File("/Files")

    if (files.exists()) {
        println(files.parent) // "/"
        println(files.parentFile.name) // ""
    }

    if (reviews.exists()) {
        println(reviews.parent) // "/Files/CompletedProjects/HelloWorld"
        println(reviews.parentFile.name) // "HelloWorld"
    }

    val root = File("/")

    if (root.exists()) {
        println(root.parent) // null
        // println(root.parentFile.name) // throws java.lang.NullPointerException
    }

    /**
     * Итерация в обоих направлениях
     *
     * Метод File.walk(direction) для итерации файловой иерархии. Направление атрибута описывает способ, которым мы можем перемещаться по нашей файловой иерархии; он может принимать два значения: FileWalkDirection.BOTTOM_UP и FileWalkDirection.TOP_DOWN.
     *
     * Тот же результат можно получить следующими методами:
     *
     * - File.walkBottomUp() (аналогично File.walk(FileWalkDirection.BOTTOM_UP));
     * - File.walkTopDown() (аналогично File.walk(FileWalkDirection.TOP_DOWN))
     */
    if (files.exists()) {
        // TOP_DOWN:
        // /Files
        // /Files/Music
        // /Files/Music/SoundTracks
        // /Files/CompletedProjects
        // /Files/CompletedProjects/HelloWorld
        // /Files/CompletedProjects/HelloWorld/Doc.pdf
        // /Files/CompletedProjects/HelloWorld/Reviews.txt
        // /Files/CompletedProjects/JCalculator
        // /Files/CompletedProjects/JCalculator/Doc.pdf
        println("TOP_DOWN:")

        files.walk(FileWalkDirection.TOP_DOWN).forEach { println(it) }

        // BOTTOM_UP:
        // /Files/Music/SoundTracks
        // /Files/Music
        // /Files/CompletedProjects/HelloWorld/Doc.pdf
        // /Files/CompletedProjects/HelloWorld/Reviews.txt
        // /Files/CompletedProjects/HelloWorld
        // /Files/CompletedProjects/JCalculator/Doc.pdf
        // /Files/CompletedProjects/JCalculator
        // /Files/CompletedProjects
        // /Files
        println("BOTTOM_UP:")

        files.walk(FileWalkDirection.BOTTOM_UP).forEach { println(it) }
    }

    /**
     * Работа с иерархиями
     *
     * Предположим, у нас есть экземпляр java.io.File с именем CompletedProjects, который соответствует каталогу CompletedProjects. Давайте теперь получим оба его подкаталога, содержащие данные проекта.
     *
     * Определённый порядок файлов в массиве не гарантируется.
     */
    val completedProjects = File("/Files/CompletedProjects")

    if (completedProjects.exists()) {
        val projects: FileTreeWalk = completedProjects.walk()

        projects.maxDepth(1) // HelloWorld and JCalculator

        // Чтобы найти проект HelloWorld, мы пройдёмся по дереву файлов
        val helloWorldProject1: File = projects.first { it.name == "HelloWorld" }

        // Другой способ получить каталог HelloWorld — использовать метод File.listFiles():
        val helloWorldProject2: File = completedProjects.listFiles()!!.first { it.name == "HelloWorld" }
    }

    /**
     * Теперь попробуем перейти в директорию Soundtracks из файла Reviews.txt:
     */
    var parent = reviews.parentFile

    if (reviews.exists()) {
        while (parent.name != "Files"){
            parent = parent.parentFile
        }

        val soundTracks: File = parent.walkTopDown().first { it.name == "SoundTracks" }
    }
}

/**
 * Если вам нужно скопировать файл, вы можете использовать функцию copyTo()
 */
fun fileCopying() {
    println("--- File copying ---")

    val fileName: String = getFileURL("MyFile.txt")

    val fileIn = File(fileName)
    val fileOut = File(getFileURL("MyFileCopy"))

    // fileIn.copyTo(fileOut)

    /**
     * Обратите внимание, что если вам нужно перезаписать файл, вам нужно добавить параметр overwrite
     *
     * Без параметра, если файл уже существует, будет исключение FileAlreadyExistsException
     */
    fileIn.copyTo(fileOut, overwrite = true)

    /**
     * Если вам нужно рекурсивно скопировать весь каталог, вы можете использовать функцию copyRecursively()
     */
    val dirIn = File("outDir")
    val dirOut = File("newDir")

    // dirIn.copyRecursively(dirOut)

    /**
     * Обратите внимание, что если вам нужно перезаписать папки и файлы, вам также необходимо добавить параметр overwrite
     */
    // dirIn.copyRecursively(dirOut, overwrite = true)
}
