import kotlin.math.hypot

fun main() {
    /**
     * Объявление функций
     */
    sayHello()

    printStaticNumber()

    val result1 = sum(2, 5)

    println(result1) // 7

    val result2 = sum(result1, 4)

    println(result2) // 11

    println("Is positive number: ${isPositive(42)}")

    println(getLastDigit(-512))

    /**
     * Декомпозиция функций
     *
     * Подход к разделению сложной программы на ряд функций называется функциональной декомпозицией.
     *
     * Каждая функция выполняет определённую задачу, которую мы можем выполнять последовательно, чтобы получить нужные нам результаты.
     *
     * Рассматривая проблему, нам необходимо определить действия, которые будут многократно повторяться или, наоборот, выполняться по отдельности.
     *
     * Так мы получаем желаемые функции, которые легче читать, понимать, повторно использовать, тестировать и отлаживать.
     */
    calculatorWithFourFunctions()

    /**
     * Idiom with when
     */
    val colorNumber1: Int = transform1("Blue")
    val colorNumber2: Int = transform2("Yellow")

    println(colorNumber1) // 2
    println(colorNumber2) // -1

    /**
     * Аргументы по умолчанию и именованные аргументы
     */
    printLine() // пустая строка, как println()
    printLine("Kotlin") // "Kotlin" с завершающим символом новой строки
    printLine("Hello, Kotlin", "!!!\n") // "Hello, Kotlin!!!"
    printLine(end = "!!!\n", line = "Hello, Kotlin") // "Hello, Kotlin!!!"

    println(perimeter()) // 0.0
    println(perimeter(0.0, 0.0, 12.0, 0.0, 0.0, 5.0)) // 30.0

    extensionFunctions()
    infixFunctions()
    functionReferences()
    lambdaExpressions()
}

/**
 * Idiom.
 * The function just returns 3
 */
fun get3(): Int = 3

/**
 * The function returns its argument
 */
fun identity(a: Int): Int {
    return a
}

/**
 * Idiom.
 * The function returns the sum of two Ints
 */
fun sum(a: Int, b: Int): Int = a + b

/**
 * Print static numbers
 */
fun printStaticNumber() {
    println(get3()) // 3
    println(identity(1000)) // 1000
}

// Idiom
fun sayHello(): Unit = println("Hello")

// Idiom
fun isPositive(number: Int): Boolean = number > 0

/**
 * Возвращает последнее число
 */
fun getLastDigit(number: Int): Char = number.toString().last()

fun transform1(color: String): Int {
    return when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> 2
        else -> -1
    }
}

fun transform2(color: String): Int = when (color) {
    "Red" -> 0
    "Green" -> 1
    "Blue" -> 2
    else -> -1
}

fun calculatorWithFourFunctions() {
    print("Calc: ")

    val (_, value1, operator, value2) = readln().split("")

    val number1: Long = value1.toLong()
    val number2: Long = value2.toLong()

    when (operator) {
        "+" -> sumTwoNumbers(number1, number2)
        "-" -> subtractTwoNumbers(number1, number2)
        "*" -> multiplyTwoNumbers(number1, number2)
        "/" -> divideTwoNumbers(number1, number2)
    }
}

/**
 * Сложение
 */
fun sumTwoNumbers(a: Long, b: Long): Unit = println(a + b)

/**
 * Вычитание
 */
fun subtractTwoNumbers(a: Long, b: Long): Unit = println(a - b)

/**
 * Умножение
 */
fun multiplyTwoNumbers(a: Long, b: Long): Unit = println(a * b)

/**
 * Целочисленное деление
 */
fun divideTwoNumbers(a: Long, b: Long): Unit = println(if (b == 0L) "Division by 0!" else a / b)

fun printLine(line: String = "", end: String = "\n"): Unit = print("$line$end")

/**
 * Функция для вычисления периметра многоугольника с 3 или 4 вершинами, представленного координатами X и Y. Вершины проходятся последовательно.
 *
 * Функция Math.hypot(x, y) для вычисления длины отрезка по теореме Пифагора.
 */
fun perimeter(
    x1: Double = 0.0,
    y1: Double = 0.0,
    x2: Double = 0.0,
    y2: Double = 0.0,
    x3: Double = 0.0,
    y3: Double = 0.0,
    x4: Double = x1,
    y4: Double = y1
): Double {
    val segment1: Double = hypot(x2 - x1, y2 - y1)
    val segment2: Double = hypot(x3 - x2, y3 - y2)
    val segment3: Double = hypot(x4 - x3, y4 - y3)
    val segment4: Double = hypot(x1 - x4, y1 - y4)

    return segment1 + segment2 + segment3 + segment4
}

/**
 * Функции расширения (Extension functions)
 *
 * Часто разработчик не пишет всё с нуля, а использует уже написанный код. Это экономит им некоторое время. Однако есть некоторые недостатки, такие как невозможность редактирования используемого кода, особенно когда код исходит от другого разработчика.
 *
 * Функции расширения используются для расширения возможностей класса, а не для изменения существующего поведения.
 */
fun extensionFunctions() {
    /**
     * Проблема с существующими классами
     *
     * Для работы с классами, которые вы не можете изменить, вы можете написать функцию, принимающую объект этого класса в качестве аргумента.
     *
     * Обратите внимание: если разработчик скрывает какую-то информацию и ваш код не может её получить, функция расширения также не может иметь доступа.
     *
     * Также нужно отметить, что функции расширения используются даже в стандартной библиотеке Kotlin.
     *
     * Например, если вы посмотрите на определение класса String, вы увидите только необходимые функции-члены.
     *
     * Другие функции, такие как .first() и .toUpperCase(), на самом деле являются функциями расширения, необходимыми только для упрощения кода класса.
     */
    class AnyClass(val string: String) {
        fun repeated(): String = string + string
    }

    val remoteClass = AnyClass("ha")

    fun AnyClass.tripleRepeated(): String {
        var result = ""

        repeat(3) {
            result += this.string
        }

        return result
    }

    println(remoteClass.repeated())  // returns "haha"
    println(remoteClass.tripleRepeated())  // returns "hahaha"

    /**
     * Если попытаться расширить класс ещё раз ту же функцию, что уже расширена (например, tripleRepeated()), код не скомпилируется.
     *
     * Если попытаться расширить класс функцией с именем функции-члена (например, repeated), код скомпилируется, но при вызове сработает функция-член класса. Чтобы защитить объект от случайного или намеренного изменения поведения класса.
     *
     * Если вы хотите дать функции расширения имя, которое уже существует, вы должны изменить сигнатуру функции, например, изменить её аргументы. Это не сломает уже существующий код.
     */
}

/**
 * Инфиксные функции
 *
 * add(2, 4) -> 2 add 4
 */
fun infixFunctions() {
    infix fun Int.add(x: Int): Int = this + x

    println(1 add 2)  // 3

    val listOfShip: List<String> = listOf("Ford-11", "Bismarck-200", "Titanic-340", "HMS-44")

    infix fun List<String>.battle(ammunitionLimit: Int): List<String> {
        val validShips: MutableSet<String> = mutableSetOf()

        for (ship in this) {
            val (name, ammunition) = ship.split("-")

            if (ammunition.toInt() > ammunitionLimit) {
                validShips.add(name)
            }
        }

        return validShips.toList()
    }

    println(listOfShip battle 50) // [Bismarck, Titanic]
}

fun getRealGrade(x: Double): Double = x
fun getGradeWithPenalty(x: Double): Double = x - 1

fun getScoringFunction(isCheater: Boolean): (Double) -> Double = if (isCheater) ::getGradeWithPenalty else ::getRealGrade

fun same(x: Int): Int = x
fun square(x: Int): Int = x * x
fun triple(x: Int): Int = 3 * x

fun applyAndSum(a: Int, b: Int, transform: (Int) -> Int): Int = transform(a) + transform(b)

fun isNotDot(c: Char): Boolean = c != '.'

/**
 * Ссылки на функции.
 *
 * Kotlin позволяет получать ссылки на функции. Чтобы получить ссылку на функцию верхнего уровня, нам просто нужно написать двойное двоеточие (::) перед её именем, и мы не пишем круглые скобки и аргументы после имени.
 *
 * Существует четыре вида ссылок на функции:
 * - ссылка на функцию (::functionName);
 * - ссылка по классу (ClassName::functionName);
 * - ссылка по объекту (objectName::functionName);
 * - ссылка на конструктор (::ClassName).
 */
fun functionReferences() {
    /**
     * Ссылки на функции как объекты.
     */
    val sumObject: (Int, Int) -> Int = ::sum

    println(sum(10, 20)) // 30
    println(sumObject(10, 20)) // 30

    /**
     * Функции, возвращающие другие функции
     */
    val wantedFunction: (Double) -> Double = getScoringFunction(false)

    println(wantedFunction(9.0)) // 9.0

    /**
     * Ссылки на функции как параметры функций
     */
    println(applyAndSum(1, 2, ::same)) // 3 = 1 + 2
    println(applyAndSum(1, 2, ::square)) // 5 = 1 * 1 + 2 * 2
    println(applyAndSum(1, 2, ::triple)) // 9 = 3 * 1 + 3 * 2

    val originalText = "I don't know... what to say..."
    val textWithoutDots: String = originalText.filter(::isNotDot)

    println(textWithoutDots) // I don't know what to say

    /**
     * Вы можете ссылаться на функции, принадлежащие классу. Базовый синтаксис в таких случаях выглядит так:
     * - objectOrClass::functionName
     *
     * objectOrClass может быть именем класса или конкретным экземпляром класса.
     */
    class Person(val name: String, val lastname: String) {
        fun printFullName(): String = "full name: $name $lastname"
    }

    val person = Person("Sara", "Rogers")
    val personFun: () -> String = person::printFullName

    println(personFun())

    /**
     * Ссылки на функции также работают с функциями из стандартных классов Kotlin
     *
     * Создаем ссылку на стандартную функцию dec класса Int. Функция dec уменьшает число на единицу (декремент).
     */
    val dec: (Int) -> Int = Int::dec

    println(dec(4)) // 3

    /**
     * Альтернативный способ создать тот же объект с помощью лямбда-выражения
     */
    val dec2: (Int) -> Int = { x: Int -> x.dec() }

    println(dec2(4)) // 3

    /**
     * Ссылка по объекту
     */
    val whatsGoingOnText = "What's going on here?"

    /**
     * Функция находит индекс первого вхождения элемента в текст. Эта функция принимает строку для поиска, индекс, с которого мы начинаем поиск, и логическое значение, определяющее, будет ли игнорироваться регистр при сопоставлении с символом (по умолчанию оно равно false)
     */
    val indexWithinWhatsGoingOnText: (String, Int, Boolean) -> Int = whatsGoingOnText::indexOf

    println(indexWithinWhatsGoingOnText("going", 0, true)) // 7
    println(indexWithinWhatsGoingOnText("Hi", 0, true))  // -1
    println(indexWithinWhatsGoingOnText("what's", 0, false))  // -1
    println(indexWithinWhatsGoingOnText("what's", 0, true))  // 0

    /**
     * Ссылка на конструктор
     */
    class User (val name: String)

    val personGenerator: (String) -> User = ::User
    val johnFoster: User = personGenerator("John Foster")

    println(johnFoster.name) // John Foster
}

/**
 * Лямбда-выражения
 *
 * Функция создаваемая во время выполнения и без предопределенного имени. Lambda — одна из наиболее важных функций, широко используемая в современном программировании.
 *
 * Чтобы создать функцию Kotlin, которая не привязана к своему имени, вы можете использовать либо анонимную функцию, либо лямбда-выражение:
 * - fun(arguments): ReturnType { body } – это обычно называют «анонимной функцией».
 * - { arguments -> body } - это обычно называют «лямбда-выражением»
 * - { body } - «лямбда-выражение» без аргументов
 *
 * Иногда ссылка на функцию более читабельна, чем лямбда, и нет правильного ответа, какой из них предпочтительнее. Однако, если код довольно сложный, вместо копирования и вставки некоторых лямбда-выражений может быть лучше использовать ссылку на функцию для упрощения обслуживания и повторного использования.
 */
fun lambdaExpressions() {
    val anonymous: (Int, Int) -> Int = fun(a: Int, b: Int): Int = a * b
    val lambda: (Int, Int) -> Int = { a: Int, b: Int -> a * b }
    val lambdaWithoutArguments: () -> Int = { 40 + 2 }

    println(anonymous(2, 3)) // 6
    println(lambda(2, 3)) // 6
    println(lambdaWithoutArguments()) // 42

    val originalText = "I don't know... what to say..."

    /**
     * Бывают ситуации, когда лямбда передается последним аргументом. В таком случае Kotlin предоставляет способ исключить скобочные последовательности ({ }) и записать лямбду вне круглых скобок:
     * - originalText.someFunction(a, b) { c -> c != '.' }
     */
    val textWithoutDots: String = originalText.filter { c: Char -> c != '.' }

    println(textWithoutDots) // I don't know what to say

    /**
     * Когда в лямбде есть один параметр, есть возможность его пропустить. Параметр доступен под именем it. Его тип выводится из типа аргумента, передаваемого в лямбду.
     */
    println(originalText.filter { it != '.' }) // I don't know what to say
}
