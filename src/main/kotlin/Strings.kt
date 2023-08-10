fun main() {
    stringLength()
    concatenation()
    stringRepeat()
    rawStrings()
    stringTemplates()
    getStringChars()
    checkEmpty()
    substrings()
    splittingString()
    iteratingOverString()
    stringBuilder()

    personInformation()
    luckyNumber()
    createEuphoniousWord()
    numberOfTens()
}

/**
 * Длина строки
 */
fun stringLength() {
    val language = "Kotlin"
    val length: Int = language.length

    println(length) // 6

    val empty = ""

    println(empty.length) // 0
}

/**
 * Конкатенация строк
 */
fun concatenation() {
    val one = "1"
    val two = "2"
    val twelve = one + two

    println(one) // 1, no changes
    println(two) // 2, no changes
    println(twelve) // 12

    // Добавление значений
    val stringPlusBoolean = "abc" + 10 + true

    println(stringPlusBoolean) // abc10true

    val code = "123" + 456 + 789

    println(code) // 123456789

    val charPlusString = 'a' + "bc"

    println(charPlusString) // abc

    val stringPlusChar = "de" + 'f'

    println(stringPlusChar) // def
}

/**
 * Повтор строки
 */
fun stringRepeat() {
    print("Hello".repeat(4)) // HelloHelloHelloHello
    println()

    println("Eat. Sleep. Code.\n".repeat(7))
}

/**
 * Необработанная строка
 */
fun rawStrings() {
    // 'H' is the first letter of "Hello world!" string.
    println("\'H\' is the first letter of \"Hello world!\" string.")

    val largeString = """
        This is the house that Jack built.

        This is the malt that lay in the house that Jack built.

        This is the rat that ate the malt
        That lay in the house that Jack built.

        This is the cat
        That killed the rat that ate the malt
        That lay in the house that Jack built.
    """.trimIndent() // removes the first and the last lines and trims indents

    println(largeString)

    val unevenString = """
        123
         456
          789""".trimIndent()

    println(unevenString)

    val rawString = """123
         456
          789
""".trimIndent()

    println(rawString)

    val json = """
        {
            "firstName": "John",
            "lastName": "Smith",
            "age": 35,
            "phoneNumbers": [
                {
                    "type": "mobile",
                    "number": "123 567-7890"
                }
            ]
        }
    """.trimIndent()

    println(json)
}

/**
 * Шаблоны
 */
fun stringTemplates() {
    val city = "Paris"
    val temp = "24"

    println("Now, the temperature in $city is $temp degrees Celsius.")

    val firstName = "John"
    val lastName = "Smith"
    val fullName = "$firstName $lastName"

    println(fullName)

    val language = "Kotlin"

    println("$language has ${language.length} letters in the name")
}

/**
 * Получение символов строки
 */
fun getStringChars() {
    val greeting = "Hello"

    val first: Char = greeting[0] // 'H'
    val second: Char = greeting[1] // 'e'
    val five: Char = greeting[4] // 'o'
    val last: Char = greeting[greeting.length - 1] // 'o'
    val preLast: Char = greeting[greeting.length - 2] // 'l'

    println("First char: ${greeting.first()}") // 'H'
    println("First char: $first") // 'H'
    println("Second char: $second") // 'e'
    println("Five char: $five") // 'o'
    println("Last char: $last") // 'o'
    println("Last char: ${greeting.last()}") // 'o'
    println("Pre-last char: $preLast") // 'l'
    println("Last index: ${greeting.lastIndex}") // 4
}

/**
 * Проверка на пустоту
 */
fun checkEmpty() {
    val greeting = "Hello"

    println("String is empty: ${greeting.isEmpty()}") // false
    println("String is not empty: ${greeting.isNotEmpty()}") // true
}

/**
 * Получение новой строки из текущей.
 *
 * Функция подстроки принимает startIndex (включительно) и lastIndex (исключительно) в качестве аргументов и возвращает строку, которая начинается с startIndex и заканчивается непосредственно перед lastIndex.
 */
fun substrings() {
    val greeting = "Hello"

    println(greeting.substring(0, 3)) // "Hel"
    println(greeting.substring(1, 3)) // "el"

    // lastIndex можно не указывать, будет браться до конца строки
    println(greeting.substring(2)) // "llo"

    println(greeting.substring(4, 5)) // "o"
    println(greeting.substring(4, greeting.lastIndex + 1)) // "o"
    println(greeting.substring(4, greeting.length)) // "o"

    /**
     * Метод substring — не единственный способ получить часть строки. Вы также можете использовать функции substringAfter и substringBefore
     *
     * Эти функции принимают разделитель в качестве аргумента и возвращают строку до/после первого появления указанного разделителя. Если строка не содержит вхождений аргумента-разделителя, функция возвращает всю строку.
     */
    println(greeting.substringAfter('l'))  // "lo"
    println(greeting.substringBefore('o')) // "Hell"
    println(greeting.substringBefore('Я')) // "Hello"
    println(greeting.substringBefore('Я', "can't find a character")) // "can't find a character"

    /**
     * Функции substringBeforeLast и substringAfterLast имеют логику, аналогичную substringBefore и substringAfter, но возвращают строку до или после последнего вхождения разделителя.
     */
    println(greeting.substringAfterLast('l'))  // "o"
    println(greeting.substringBeforeLast('l')) // "Hel"

    /**
     * Замена частей строки
     *
     * Функция replace заменяет все вхождения первого аргумента в строке вторым аргументом.
     */
    val example = "Good morning..."

    println(example.replace("o", "O")) // "GOOd mOrning..."
    println(example.replace("morning", "bye")) // "Good bye..."
    println(example.replace('.', '!')) // "Good morning!!!"

    /**
     * Если вам нужно заменить только первое вхождение аргумента, используйте replaceFirst.
     */
    val example2 = "one one two three"

    println(example2.replaceFirst("one", "two")) // "two one two three"
    println(example2.replaceFirst("ONE", "two", true)) // "two one two three"

    println(example) // "Good morning..."
    println(greeting) // "Hello"

    /**
     * Изменение регистра
     */
    val uppercaseString = "UPPERCASE String"

    println(uppercaseString.lowercase()) // uppercase string

    val lowercaseString = "Lowercase String"

    println(lowercaseString.uppercase()) // LOWERCASE STRING
}

/**
 * Строка может быть разделена разделителями на список строк.
 */
fun splittingString() {
    val sentence = "a long text"

    val wordsList: List<String> = sentence.split(' ')

    println(wordsList) // ["a", "long", "text"]

    val number = "+1-213-345-6789"

    val parts = number.split('-')

    println(parts) // ["+1", "213", "345", "6789"]

    val text1 = "That's one small step for a man, one giant leap for mankind."

    // ["That's one small step for a man", " one giant leap for mankind."]
    println(text1.split(','))

    val text2 = "I'm gonna be a programmer"

    println(text2.split(" gonna be ")) // ["I'm", "a programmer"]
}

/**
 * Можно перебирать символы строки с помощью цикла
 */
fun iteratingOverString() {
    val scientistName = "Isaac Newton"

    // I s a a c   N e w t o n
    for (char in scientistName) {
        print("$char ") // print the current character
    }

    println()

    // Подсчитывает и печатает количество пробелов в строке str.
    val str = "strings are not primitive types!"

    var count = 0

    for (ch in str) {
        if (Character.isWhitespace(ch)) {
            count += 1
        }
    }

    println(count) // 4

    val rainbow = "ROYGCBV"

    /**
     * 1: R
     * 2: O
     * 3: Y
     * 4: G
     * 5: C
     * 6: B
     * 7: V
     */
    for (index in rainbow.indices){
        println("${index + 1}: ${rainbow[index]}")
    }
}

/**
 * Объекты String являются неизменяемыми, то есть после создания их нельзя изменить. Несмотря на то, что неизменяемость имеет свои преимущества с точки зрения стабильности, она также может создавать определённые проблемы, когда речь идёт о манипулировании строками и конкатенации.
 *
 * Каждый раз, когда мы выполняем такие операции, как объединение с помощью оператора + или функции plus(), создаётся новый строковый объект, что может привести к ненужному выделению памяти и снижению производительности.
 *
 * Для решения этих проблем есть специальный класс StringBuilder, представляющий изменяемую последовательность символов. Его можно использовать для эффективного выполнения нескольких операций со строками, что позволяет изменять его содержимое без создания нового объекта. Это может помочь уменьшить нагрузку на память и повысить производительность.
 *
 * Существует два основных способа создания объекта StringBuilder в Kotlin: использование конструктора StringBuilder и использование функции buildString.
 *
 * У класса StringBuilder есть два важных члена: length и capacity(). Свойство length представляет количество символов, содержащихся в объекте StringBuilder, тогда как метод capacity() возвращает количество символов, которое он может содержать, прежде чем потребуется изменить его размер.
 */
fun stringBuilder() {
    println("--- StringBuilder ---")

    /**
     * Пустой StringBuilder с начальной ёмкостью
     */
    val sb1 = StringBuilder()

    println(sb1) // ""

    /**
     * Указание начальной ёмкости StringBuilder, передав целочисленное значение конструктору
     */
    val sb2 = StringBuilder(100)

    println(sb2) // ""

    /**
     * Кроме того, вы можете создать StringBuilder, который содержит те же символы, что и данный CharSequence или String
     */
    val sb3 = StringBuilder("Kotlin")
    val sb4 = StringBuilder(sb3)

    println(sb3) // Kotlin
    println(sb4) // Kotlin

    println("--- buildString ---")

    /**
     * Одной из особенностей, выделяющих Kotlin, является поддержка функционального программирования. Вот почему более удобным способом создания StringBuilder является использование функции buildString.
     *
     * Эта функция принимает лямбда-выражение в качестве аргумента и позволяет вам построить строку с помощью StringBuilder без необходимости её явного создания
     */
    val str1: String = buildString {
        append("Hello")
        append(' ')
        append("Kotlin")
    }

    println(str1) // Hello Kotlin

    /**
     * Мы также можем передать начальную ёмкость лямбда-выражению в качестве аргумента
     */
    val str2: String = buildString(100) {
        append("Hello")
        append(' ')
        append("Kotlin")
    }

    println(str2) // Hello Kotlin

    /**
     * Хотя buildString можно использовать в большинстве случаев для достижения одних и тех же результатов, что и при непосредственном использовании StringBuilder, в некоторых случаях непосредственное использование StringBuilder обеспечивает большую гибкость или контроль над процессом манипулирования строками.
     */

    println("--- Methods of StringBuilder ---")

    /**
     * StringBuilder предоставляет несколько методов для работы со строками, которые позволяют легко изменять содержимое объекта StringBuilder.
     *
     * - append позволяет добавлять символы или строки в конец StringBuilder. Этот метод перегружен, чтобы принимать различные типы аргументов.
     *
     * - insert позволяет вставлять символы или строки в определённую позицию в StringBuilder. Этот метод также перегружен, чтобы принимать различные типы аргументов.
     *
     * - delete позволяет удалять символы или строки из определённого диапазона в StringBuilder. Этот метод принимает два аргумента: начальный индекс (включенный) и конечный индекс (исключенный) диапазона для удаления.
     *
     * - replace позволяет заменять символы или строки в пределах определённого диапазона в StringBuilder. Этот метод принимает три аргумента: начальный индекс (включенный), конечный индекс заменяемого диапазона (исключенный) и заменяемую строку.
     *
     * - reverse позволяет инвертировать символы в StringBuilder. Этот метод не принимает аргументов и переворачивает всю последовательность символов.
     *
     * Больше методов в документации: https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/
     */
    val str3 = buildString {
        append("Kotlin v") // Appending a String
        append(1.8) // Appending a Double
    }

    println(str3) // Kotlin v1.8

    val str4 = buildString {
        append("Hello World")
        insert(5, ",")
    }

    println(str4) // Hello, World

    val str5 = buildString {
        append("That's impossible!")
        delete(7, 9) // Удаляем "im"
    }

    println(str5) // That's possible!

    val str6 = buildString {
        append("one, two, two, ...")
        replace(10, 13, "three")
    }

    println(str6) // one, two, three, ...

    val str7 = buildString {
        append("Hello, Kotlin!")
        reverse()
    }

    println(str7) // !niltoK ,olleH

    println("--- Length vs. capacity ---")

    val sb5 = StringBuilder()

    println("Length: ${sb5.length}, Capacity: ${sb5.capacity()}") // Length: 0, Capacity: 16

    sb5.append("Hello!")

    println("Length: ${sb5.length}, Capacity: ${sb5.capacity()}") // Length: 6, Capacity: 16

    /**
     * Емкость StringBuilder — это количество символов, которое он может содержать до того, как потребуется изменить его размер, и он всегда больше или равен его длине.
     *
     * newCapacity = max(oldCapacity * 2 + 2, newLength)
     */
    val sb6 = StringBuilder(1)

    println("Length: ${sb6.length}, Capacity: ${sb6.capacity()}") // Length: 0, Capacity: 1

    sb6.append("Hello!")

    println("Length: ${sb6.length}, Capacity: ${sb6.capacity()}") // Length: 6, Capacity: 6

    val sb7 = StringBuilder(4)

    println("Length: ${sb7.length}, Capacity: ${sb7.capacity()}") // Length: 0, Capacity: 4

    sb7.append("Hello!")

    println("Length: ${sb7.length}, Capacity: ${sb7.capacity()}") // Length: 6, Capacity: 10

    println("--- Best practices ---")

    /**
     * StringBuilder
     *
     * #. Используйте StringBuilder для сложных задач обработки строк.
     *
     * StringBuilder хорошо подходит для сложных задач обработки строк, таких как постепенное построение или изменение строки. Для простых задач конкатенации строк может быть более эффективным использование оператора + или интерполяции строк.
     *
     * #. Предварительно выделите ёмкость StringBuilder.
     *
     * Если вы знаете приблизительный размер конечной строки, вы можете предварительно выделить ёмкость StringBuilder, чтобы уменьшить количество раз, когда необходимо изменить её размер. Это может помочь повысить производительность за счёт уменьшения количества выделений памяти.
     *
     * #. Повторно используйте один и тот же объект StringBuilder.
     *
     * Если вам нужно выполнить несколько операций со строками, попробуйте повторно использовать один и тот же объект StringBuilder, а не создавать каждый раз новый. Это может помочь уменьшить нагрузку на память и повысить производительность. Для этого вы можете сбросить StringBuilder в пустое состояние, вызвав его метод setLength с аргументом 0. Это очистит содержимое StringBuilder без изменения его ёмкости, что позволит вам повторно использовать его для дальнейших операций со строками.
     *
     * #. Преобразуйте StringBuilder в строку, когда закончите.
     *
     * После того как вы закончили манипулировать StringBuilder, вы можете преобразовать его в String. Это можно легко сделать, вызвав метод toString для объекта StringBuilder. Имейте в виду, что вызов этого метода создаёт новый объект String, поэтому, если вам нужно выполнить дальнейшие операции со строками, может быть более эффективно продолжать использовать StringBuilder.
     */
    val sb8 = StringBuilder()

    sb8.append("Java")

    println(sb8.toString()) // Java

    sb8.setLength(0)

    sb8.append("Kotlin")

    println(sb8.toString()) // Kotlin

    /**
     * buildString
     *
     * #. Используйте buildString для создания простой строки.
     *
     * Функция buildString хорошо подходит для простых задач построения строк, таких как объединение небольшого количества строк или построение строки в цикле. Для более сложных задач манипулирования строками может быть эффективнее напрямую использовать StringBuilder.
     *
     * #. Избегайте создания ненужных объектов.
     *
     * При использовании функции buildString избегайте создания ненужных объектов, таких как временные строки или массивы символов. Вместо этого используйте методы, доступные в StringBuilder, для прямого управления строкой.
     *
     * #. Используйте область лямбда.
     *
     * Функция buildString принимает лямбда-выражение в качестве аргумента, что обеспечивает удобную область действия для построения строки. Используйте эту область в своих интересах, объявляя локальные переменные и функции, которые могут помочь упростить ваш код.
     */
    val numbers = listOf(1, 2, 3, 4, 5)

    val str = buildString {
        for (number in numbers) {
            append(number)
            append(' ')
        }
    }

    println(str) // 1 2 3 4 5

    hackMatrix()
}

/**
 * В рамках обучения Нео нужно расшифровать серию закодированных сообщений. Вас попросили помочь ему и написать программу, которая использует StringBuilder или buildString для декодирования сообщений. Программа должна пройтись по закодированному сообщению и выполнить следующее:
 *
 * - Проверить, является ли символ гласным. Если это так, добавьте его к декодированному сообщению.
 * - Если символ не является гласным, проверьте, является ли он строчной буквой. Если это так, добавьте его версию в верхнем регистре к декодированному сообщению.
 * - Если символ не является ни гласной, ни строчной буквой, откажитесь от него.
 */
fun hackMatrix() {
    val message = "THe mAtRIX HAs YOu"

    val decoded: String = buildString {
        for (char: Char in message) {
            when {
                "aeiou".contains(char, ignoreCase = true) -> append(char)
                char.isLowerCase() -> append(char.uppercase())
            }
        }
    }

    println(decoded) // eMATIASOu
}

/**
 * Считывает из одной строки ввода имя, фамилию и возраст человека, а затем выводит информацию по шаблону
 */
fun personInformation() {
    print("Введите через пробел имя, фамилию и возраст: ")

    val (firstName, lastName, age) = readln().split(' ')

    println("${firstName.first()}. $lastName, $age years old")
}

/**
 * Дано число N с четным количеством цифр. Если сумма первой половины цифр равна сумме второй половины цифр, то это число считается счастливым. Для заданного числа выведите «YES», если это число удачное, иначе выведите «NO».
 */
fun luckyNumber() {
    print("Введите число с чётным количеством цифр: ")

    val input = readln()

    val first: Int = input.substring(0, input.length / 2).sumOf { it.digitToInt() }
    val last: Int = input.substring(input.length / 2).sumOf { it.digitToInt() }

    println(if (first == last) "YES" else "NO")
}

/**
 * Все буквы английского алфавита делятся на гласные и согласные.
 * Гласные: a, e, i, o, u, y.
 * Остальные буквы согласные.
 *
 * Слово считается благозвучным, если в нём нет трёх и более гласных или согласных подряд. В противном случае он считается неблагозвучным.
 *
 * Ваша задача состоит в том, чтобы из неблагозвучных слов составить благозвучные. В слово можно вставлять любые буквы. Выведите минимальное количество символов, необходимое для составления благозвучного слова из заданного.
 *
 * Например, слово «schedule» считается неблагозвучным, потому что в нём три согласных подряд: «sch». Чтобы создать благозвучное слово, вам нужно добавить любую гласную между «s» и «c» или между «c» и «h».
 */
fun createEuphoniousWord() {
    val list = listOf(
        "wwwwwwwwwwwwwwwwwwwwwwwwwwwww",
        "schedule",
        "garage",
        "player",
        "biiiiig"
    )

    val pattern = "([aeiouy]{3,}|[^aeiouy]{3,})"

    for (word in list) {
        val count: Int = Regex(pattern).findAll(word).sumOf { (it.value.length - 1) / 2 }

        println(count) // 14, 1, 0, 1, 2
    }
}

/**
 * Читает число и печатает десятки в этом числе.
 *
 * Например, число 174 раскладывается как:
 * 1 * 100 + 7 * 10 + 4 * 1
 *
 * Нам нужны десятки числа, т.е. число 7
 *
 * Используйте индексы, чтобы решить эту проблему!
 */
fun numberOfTens() {
    val number = "235"

    println(number.getOrElse(number.lastIndex - 1) { '0' })
}
