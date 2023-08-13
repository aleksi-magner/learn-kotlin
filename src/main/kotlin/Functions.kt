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
    advancedLambdaExpressions()
    testPredicates()
    lambdaWithReceiver()
    scopeFunctions()
    pairAndTriple()

    manipulatingWithNumbers()
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
            val (name, ammunition) = ship.split('-')

            if (ammunition.toInt() > ammunitionLimit) {
                validShips.add(name)
            }
        }

        return validShips.toList()
    }

    println(listOfShip battle 50) // [Bismarck, Titanic]

    /**
     * Из списка целых чисел и параметра code должен возвращать сумму элементов, которые делятся на code
     */
    val listOfInt = listOf(1, 2, 3, 8, 10, 10, 11, 13, 5)

    infix fun List<Int>.matrix(code: Int): Int = this.sumOf {
        if (it % code == 0) it else 0
    }

    println(listOfInt matrix 5) // 25

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
    val maxLambda: (Int, Int) -> Int = { a: Int, b: Int -> maxOf(a, b) }
    val lambdaWithoutArguments: () -> Int = { 40 + 2 }

    println(anonymous(2, 3)) // 6
    println(lambda(2, 3)) // 6
    println(maxLambda(2, 3)) // 3
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

fun advancedLambdaExpressions() {
    /**
     * Иногда код в лямбде недостаточно короток, чтобы уместиться в одну строку, поэтому вам нужно разбить код на строки.
     *
     * В таком случае последняя строка внутри лямбды рассматривается как возвращаемое значение лямбда.
     */
    val originalText = "I don't know... what to say...123456"

    val textWithoutSmallDigits1 = originalText.filter {
        val isNotDigit = !it.isDigit()
        val stringRepresentation = it.toString()

        isNotDigit || stringRepresentation.toInt() >= 5
    }

    println(textWithoutSmallDigits1) // I don't know... what to say...56

    /**
     * Кроме того, лямбда может содержать более ранние возвраты.
     *
     * В Kotlin «более ранние возвраты» относятся к возможности завершить выполнение лямбда-выражения или функции до достижения конца ее блока с помощью ключевого слова return.
     */
    val textWithoutSmallDigits2 = originalText.filter {
        if (!it.isDigit()) {
            return@filter true
        }

        it.toString().toInt() >= 5
    }

    println(textWithoutSmallDigits2) // I don't know... what to say...56

    /**
     * Захват переменных в замыкании, также известный как использование захваченной переменной или захваченного значения, относится к процессу включения переменной в лямбда-выражение или анонимную функцию, чтобы ее можно было использовать внутри тела функции
     */
    var count = 0

    val changeAndPrint = {
        count += 1

        println(count)
    }

    println(count) // 0
    changeAndPrint() // 1

    count += 10

    changeAndPrint() // 12
    println(count) // 12

    /**
     * placeArgument преобразует функцию f, принимающую два аргумента, в функцию, принимающую один аргумент. Мы достигаем этого, создавая лямбду, которая принимает только один аргумент и вызывает данную функцию с этим аргументом и заданным значением. Здесь лямбда фиксирует значение и f
     */
    fun placeArgument(value: Int, f: (Int, Int) -> Int): (Int) -> Int = {
        number -> f(value, number)
    }

    fun sum(a: Int, b: Int): Int = a + b

    val mul2 = { a: Int, b: Int -> a * b }

    val increment = placeArgument(1, ::sum)
    val triple = placeArgument(3, mul2)

    println(increment(4)) // 5 = 1 + 4
    println(increment(40)) // 41 = 1 + 40

    println(triple(4)) // 12 = 3 * 4
    println(triple(40)) // 120 = 3 * 40
}

/**
 * Тестовые предикаты: проверка элементов в коллекции
 *
 * При работе с коллекциями часто требуется проверять, соответствуют ли элементы коллекции какому-либо условию.
 *
 * Фильтрация коллекции по определённым критериям или проверка наличия в коллекции элемента с определёнными свойствами — одни из самых распространенных задач, и важно сделать это наиболее эффективным способом.
 *
 * Предикат — это функция, которая возвращает истину или ложь в зависимости от входных данных. Мы получим true, если условие соответствует предикату, и false в противном случае.
 *
 * Один предикат всегда проверяет одно и то же условие; разные условия проверяются разными предикатами.
 */
fun testPredicates() {
    // Lambdas as predicates (T) -> Boolean
    val isEven: (Int) -> Boolean = { x -> x % 2 == 0 }
    val isPalindrome: (String) -> Boolean = { x -> x.reversed() == x }

    println("2 is even: ${isEven(2)}") // true
    println("3 is even: ${isEven(3)}") // false

    println("racecar is palindrome: ${isPalindrome("racecar")}") // true
    println("potatoes is palindrome: ${isPalindrome("potatoes")}") // false

    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val emptyList = emptyList<Int>()

    println("---Any---")

    // Вернёт true, если хотя бы один элемент коллекции соответствует предикату
    // any number is even?
    println(numbers.any { x -> x % 2 == 0 }) // true
    println(numbers.any { isEven(it) }) // true
    println(numbers.any(isEven)) // true
    println(emptyList.any(isEven)) // false

    println("---None---")

    // Вернёт true, если ни один из элементов коллекции не соответствует предикату
    // none of the numbers is even?
    println(numbers.none(isEven)) // false
    println(emptyList.none(isEven)) // true

    // none of the numbers is greater than 100
    println(numbers.none { x -> x > 100 }) // true

    println("---All---")

    // Вернёт true, если все элементы коллекции удовлетворяют заданному предикату.
    // Особенностью этой функции является то, что она всегда возвращает true для пустой коллекции; эта черта также называется пустой правдой в математике.
    println(numbers.all(isEven)) // false
    println(emptyList.all(isEven)) // true

    println("Has the spell been invoked?")

    print("Введите через пробел список слов: ")

    val list: List<String> = readln().split(' ')

    val hasSpell: Boolean = checkSpell(list)

    println("Заклинание произнесено: $hasSpell")
}

/**
 * Волшебник произнес список слов, но мы знаем, что только «abracadabra» произносит заклинание.
 *
 * Это было сказано?
 *
 * Верните true, если список содержит слово «abracadabra» в верхнем, нижнем или смешанном регистре.
 */
fun checkSpell(words: List<String>): Boolean {
    val spell = "abracadabra"

    return words.any { word -> word.lowercase() == spell }
}

/**
 * Расширение лямбда-функций (Lambda with receiver)
 *
 * Чтобы преобразовать лямбду в лямбду с получателем, можно присвоить одному из параметров лямбды специальный статус получателя, что позволяет обращаться к ее членам напрямую без каких-либо квалификаторов.
 *
 * Типы функций могут опционально иметь дополнительный тип приемника, который указывается перед точкой в нотации: A.(B) -> C { body } представляет функции, которые могут быть вызваны на объекте-приемнике A с параметром B, возвращают значение C, и выполнять любые действия в body.
 *
 * Внутри body литерала функции вы можете получить доступ к членам объекта-приемника, используя выражение this.
 */
fun lambdaWithReceiver() {
    // Обычная лямбда
    val lambdaSum: (Int, Int) -> Int = { a, b -> a + b }

    println(lambdaSum(1, 2)) // 3

    // lambda with a receiver
    val lambdaReceiverSum: Int.(Int) -> Int = { a ->
        println("this: $this") // 1

        this + a
    }

    println(lambdaReceiverSum(1, 2)) // 3
    println(1.lambdaReceiverSum(2)) // 3

    fun Int.opp(f: Int.() -> Int): Int = f()

    var res = 10.opp { this.times(2) }

    println(res) // 20

    // We can omit this
    res = 10.opp { plus(10) }

    println(res) // 20

    res = 10.opp { this * 2 }

    println(res) // 20

    /**
     * В следующем коде показано, как использовать типобезопасные построители с классом StringBuilder, которые можно применять для эффективного выполнения нескольких операций над строками. Например, используя метод append, мы можем добавить указанную последовательность символов; в итоге после всех манипуляций возвращаем конечную строку.
     */
    // Safe Builder String with Lambda with receiver
    fun myString(init: StringBuilder.() -> Unit): String {
        return StringBuilder().apply(init).toString()
    }

    val str = myString {
        append("Hello, ".uppercase())
        append("World!")
    }

    println(str) // HELLO, World!

    fun <T> T.apply(block: T.() -> Unit): T {
        block()
        return this
    }

    data class Student(var name: String, var age: Int)

    val student = Student("John", 20)

    student.apply {
        name = this.name.uppercase()
        age += 1
    }

    println(student) // Student(name=JOHN, age=21)

    val greeting = "hello"

    val magicString: String = greeting.magic {
        uppercase().reversed().dropLast(1)
    }

    println(magicString) // OLLE
}

/**
 * Напишите функцию magic, которая работает с лямбдой с приёмником и выполняет любую операцию со строкой
 */
fun String.magic(init: String.() -> String): String = this.init()

data class Musician(var name: String, var instrument: String = "", var band: String = "")

/**
 * В Kotlin есть пять функций области видимости: let, run, with, apply и also. Они не выполняют никаких конкретных действий, а просто организуют ваш код и выполняют определенные операции в контексте объекта. Эти функции создают временную область для объектов и вызывают код из лямбда-выражений. Внутри лямбды мы можем общаться с объектами, используя ключевые слова it или this
 *
 * Кратко:
 *
 * `apply` используется для настройки объекта.
 * - Объект контекста доступен как this.
 * - Функция возвращает объект контекста.
 *
 * `also` аналогично `apply`, но рекомендуется выбирать, когда вы работаете со всем объектом и не заботитесь о его параметрах или методах.
 * - Объект контекста доступен как it.
 * - Функция возвращает объект контекста.
 * - Возвращает контекст до выполнения операций.
 *
 * `with`
 * - Объект контекста доступен как this.
 * - Он возвращает результат лямбда.
 * - Не является функцией расширения.
 *
 * `let` когда хотим:
 * - Что-то сделать с оператором вызова безопасности `?` и ненулевыми объектами. `it` не может быть null внутри `?.let { }`
 * - Ввести локальные переменные с ограниченной областью действия
 *
 * Особенности:
 * - Контекстный объект доступен как it.
 * - Он возвращает результат лямбда (последняя строка в лямбде).
 *
 * `run` это функция расширения. Делает то же самое, что и `with`
 *
 * Используется когда мы хотим:
 * - Инициализировать новый объект и передать ему результат лямбды.
 * - Использовать функцию без расширения и выполнить блок из нескольких операторов.
 *
 * Особенности:
 * - Объект контекста доступен как this.
 * - Он возвращает результат лямбда.
 */
fun scopeFunctions() {
    println("--- apply ---")

    // С функцией области видимости apply
    Musician("Dave Grohl", "Drums", "Nirvana").apply {
        println(this) // Musician(name=Dave Grohl, instrument=Drums, band=Nirvana)

        instrument = "Guitar"
        band = "Foo Fighters"

        println(this) // Musician(name=Dave Grohl, instrument=Guitar, band=Foo Fighters)
    }

    // Та же реализация без функции области видимости
    val dave = Musician("Dave Grohl", "Drums", "Nirvana")

    println(dave) // Musician(name=Dave Grohl, instrument=Drums, band=Nirvana)

    dave.instrument = "Guitar"
    dave.band = "Foo Fighters"

    println(dave) // Musician(name=Dave Grohl, instrument=Guitar, band=Foo Fighters)

    /**
     * Мы видим, что без apply наш код стал тяжелее и получил новую переменную. При этом в коде с apply у нас операции удобно сгруппированы, а без применения все операции расположены на одном уровне. А если добавить ещё операций, код может стать нечитаемым.
     */

    /**
     * `apply` обычно используется для настройки объекта — например, если вы хотите присвоить новые значения методам или параметрам класса.
     *
     * Две основные особенности функции apply:
     * - Объект контекста доступен как this.
     * - Функция возвращает объект контекста.
     */
    val jonny = Musician("Jonny Greenwood").apply {
        instrument = "Harmonica" // здесь мы также можем использовать this.instrument
        band = "Pavement"
    }

    println(jonny) // Musician(name=Jonny Greenwood, instrument=Harmonica, band=Pavement)

    val thom = jonny.copy(name = "Thom York")

    println(jonny) // Musician(name=Jonny Greenwood, instrument=Harmonica, band=Pavement)
    println(thom) // Musician(name=Thom York, instrument=Harmonica, band=Pavement)

    println("--- also ---")

    /**
     * Использование also аналогично apply, но рекомендуется выбирать also, когда вы работаете со всем объектом и не заботитесь о его параметрах или методах.
     *
     * Две основные характеристики функции also:
     * - Объект контекста доступен как it.
     * - Функция возвращает объект контекста.
     */
    val instruments = mutableListOf("Guitar", "Harmonica", "Bass guitar")

    instruments
        .also {
            println("Right now I can play these instruments: $it") // [Guitar, Harmonica, Bass guitar]
        }
        .add("Theremin")

    println(instruments) // [Guitar, Harmonica, Bass guitar, Theremin]

    /**
     * `also` имеет интересную особенность — вроде бы выполняет операции сразу (на самом деле возвращает контекст до выполнения операций).
     */
    var a = 10
    var b = 5

    a = b.also { b = a }

    println("a = $a, b = $b") // a = 5, b = 10

    println("--- with ---")

    /**
     * Основные характеристики функции with:
     * - Объект контекста доступен как this.
     * - Он возвращает результат лямбда.
     * - Это не функция расширения.
     *
     * Что мы имеем в виду, когда говорим, что with не является функцией расширения? Это означает, что в качестве аргумента передается объект контекста — он заключен в круглые скобки. Однако внутри лямбды наш объект доступен как получатель (this).
     */
    val musicians = mutableListOf("Thom York", "Jonny Greenwood", "Colin Greenwood")

    // 'with' is called with the argument [Thom York, Jonny Greenwood, Colin Greenwood]
    // List contains 3 elements
    with(musicians) {
        println("'with' is called with the argument $this")
        println("List contains $size elements")
    }

    val firstAndLast = with(musicians) {
        "First list element - ${first()}," + " last list element - ${last()}"
    }

    println(firstAndLast) // First list element - Thom York, last list element - Colin Greenwood

    println("--- let ---")

    /**
     * Основные особенности функции let:
     * - Контекстный объект доступен как it.
     * - Он возвращает результат лямбда (последняя строка в лямбде).
     *
     * `let` используется в двух общих случаях:
     * - когда хотим что-то сделать с оператором вызова безопасности `?` и ненулевыми объектами. `it` не может быть null внутри `?.let { }`
     * - когда хотим ввести локальные переменные с ограниченной областью действия
     */
    val str: String? = "Jonny Greenwood"

    fun processNonNullString(string: String) {
        println(string)
    }

    // processNonNullString(str) // compilation error: str can be null

    val length = str?.let {
        println("let() is called on $it") // let() is called on Jonny Greenwood

        processNonNullString(it) // Jonny Greenwood

        it.length // Возвращаемое значение
    }

    println(length) // 15

    val modifiedFirstItem = musicians.first().let { firstItem ->
        // The first item of the list is 'Thom York'
        println("The first item of the list is '$firstItem'")

        if (firstItem.length >= 5) firstItem else "!$firstItem!" // Возвращаемое значение
    }.uppercase()

    // First item after modifications: 'THOM YORK'
    println("First item after modifications: '$modifiedFirstItem'")

    println(musicians) // [Thom York, Jonny Greenwood, Colin Greenwood]

    println("--- run ---")

    /**
     * `run` похож на `with`, но это функция расширения. Таким образом, `run` делает то же самое, что и `with`, но вызывается как `let`.
     *
     * Основные особенности функции run:
     * - Объект контекста доступен как this.
     * - Он возвращает результат лямбда.
     *
     * `run` используется в двух общих случаях:
     * - когда мы хотим инициализировать новый объект и передать ему результат лямбды.
     * - когда мы хотим использовать функцию без расширения и выполнить блок из нескольких операторов.
     */

    /**
     * Это важно — наш новый объект независим и ценен, в отличие от функции `with`.
     *
     * Например, в приведённом ниже коде мы создаём новый объект `result`, передаём новое значение `service` элемента `port` и передаём результат `result` функции `query()` с функцией `prepareRequest()`, объединённой строкой в качестве параметра. Примечание! Значение `service.port` изменено.
     */
    class MultiportService(var url: String, var port: Int) {
        fun prepareRequest(): String = "Default request"

        fun query(request: String): String = "Result for query '$request'"
    }

    val service = MultiportService("https://example.kotlinlang.org", 80)

    val result = service.run {
        port = 8080

        query(prepareRequest() + " to port $port")
    }

    println(result) // Result for query 'Default request to port 8080'

    /**
     * В этом случае мы не используем объект контекста, а просто организуем некоторый фрагмент кода, связанный с переменной hexNumberRegex.
     */
    val hexNumberRegex = run {
        val digits = "0-9"
        val hexDigits = "A-Fa-f"
        val sign = "+-"

        Regex("[$sign]?[$digits$hexDigits]+")
    }

    // +1234
    // -FFFF
    // -a
    // be
    for (match in hexNumberRegex.findAll("+1234 -FFFF not-a-number")) {
        println(match.value)
    }
}

fun multiplyByTwo(number: Int): Int = number * 2
fun addTen(number: Int): Int = number + 10

/**
 * Считывает числа и выполняет определённые действия в зависимости от чётности числа.
 *
 * Вам дана сама последовательность чисел, разделенная пробелом.
 *
 * Умножить нечётное число на 2 (multiplyByTwo) и прибавить 10 к чётному числу (addTen).
 */
fun manipulatingWithNumbers() {
    val input = "1 2"

    val numbers = input.split(' ').map { it.toInt() }

    // 2 12
    for (number in numbers) {
        val changedNumber = if (number % 2 == 0) addTen(number) else multiplyByTwo(number)

        print("$changedNumber ")
    }
}

/**
 * Что делать, если мы хотим вернуть несколько значений разных типов? Kotlin предлагает нам два специальных класса, которые помогут нам справиться с этой ситуацией: Pair и Triple.
 */
fun pairAndTriple() {
    println("--- Pair ---")

    val pair = Pair(1, "one")

    println(pair) // (1, one)

    // Properties
    println(pair.first) // 1
    println(pair.second) // one

    // Methods
    println(pair.component1()) // 1
    println(pair.component2()) // one

    val pair2 = Pair("marks", listOf(8.0, 9.0, 10.0))

    println(pair2) // (marks, [8.0, 9.0, 10.0]) toString() является неявным
    println(pair2.toString()) // (marks, [8.0, 9.0, 10.0])
    println(pair2.toList()) // [marks, [8.0, 9.0, 10.0]]

    /**
     * Кроме того, мы можем использовать метод copy() для копирования объектов Pair и изменения их свойств, используя имя параметра (first и second, например: myCopy = pair.copy(first = "new Value", second = 3)
     *
     * С помощью метода copy() вы также можете создать новую пару на основе другой пары или изменить некоторые свойства.
     *
     * Вы должны сохранить порядок свойств. Помните: свойства пары неизменяемы и доступны только для чтения. Вы должны создать новую пару, чтобы изменить значения, или использовать функцию копирования, чтобы создать новую пару и присвоить ей любые значения, которые вы хотите, на основе предыдущего объекта.
     */
    val other = pair2.copy()

    println(pair2) // (marks, [8.0, 9.0, 10.0])
    println(other) // (marks, [8.0, 9.0, 10.0])

    val other2 = pair2.copy("other")
    val grades = pair2.copy(second = listOf(9.0, 7.0, 8.5))
    val myCopy = pair2.copy(first = "other", second = listOf(1.0, 2.0, 3.0))

    println(pair2) // (marks, [8.0, 9.0, 10.0])
    println(other2) // (other, [8.0, 9.0, 10.0])
    println(grades) // (marks, [9.0, 7.0, 8.5])
    println(myCopy) // (other, [1.0, 2.0, 3.0])

    println("--- Triple ---")

    val triple = Triple(1, "A", true)

    println(triple) // (1, A, true)

    // Properties
    println(triple.first) // 1
    println(triple.second) // A
    println(triple.third) // true

    // Methods
    println(triple.component1()) // 1
    println(triple.component2()) // A
    println(triple.component3()) // true

    val triple2 = Triple("marks", "Kotlin", listOf(8.0, 9.0, 10.0))

    println(triple2) // (marks, Kotlin, [8.0, 9.0, 10.0])
    println(triple2.toList()) //[marks, Kotlin, [8.0, 9.0, 10.0]]

    val other3 = triple2.copy()

    println(triple2) // (marks, Kotlin, [8.0, 9.0, 10.0])
    println(other3) // (marks, Kotlin, [8.0, 9.0, 10.0])

    val other4 = triple2.copy("other", third=listOf(7.0, 9.0, 8.5))
    val course = triple2.copy(second = "Kotlin Triple")

    println(triple2) // (marks, Kotlin, [8.0, 9.0, 10.0])
    println(other4) // (other, Kotlin, [7.0, 9.0, 8.5])
    println(course) // (marks, Kotlin Triple, [8.0, 9.0, 10.0])

    val marks = Triple("Anne", 2, listOf(8.0, 7.0, 9.0))

    println(resume(marks)) // (Anne, 8.0)

    println(revert(Pair("home", "car"))) // (car, home)
}

/**
 * Вам дается Triple, который представляет имя студента, номер курса и полученные оценки.
 *
 * Верните пару в виде резюме с именем учащегося и средним значением оценок.
 */
fun resume(marks: Triple<String, Int, List<Double>>): Pair<String, Double> =
    Pair(marks.first, marks.third.average())

/**
 * У вас есть пара со строковыми значениями.
 *
 * Возвращает новую пару, обратную исходной.
 */
fun revert(pair: Pair<String, String>): Pair<String, String> =
    Pair(pair.second, pair.first)
