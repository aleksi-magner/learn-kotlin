import java.util.*
import kotlin.random.Random

/**
 * Doc: https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/
 *
 * Коллекции — это контейнеры, которые поддерживают различные способы хранения и организации различных объектов и делают их легко доступными.
 *
 * Коллекция обычно содержит некоторое количество объектов (это число может быть равно нулю) одного и того же типа.
 *
 * Объекты в коллекции называются элементами.
 *
 * Коллекции — это реализация абстрактных структур данных, которые могут поддерживать следующие операции:
 *
 * - получение элемента;
 *
 * - удаление элемента;
 *
 * - изменение или замена элемента;
 *
 * - добавление нового элемента.
 *
 * Коллекции могут быть изменяемыми или неизменяемыми.
 *
 * Такие операции, как добавление, удаление и изменение элементов, применяются только к изменяемым коллекциям.
 *
 * Стандартная библиотека Kotlin обеспечивает реализацию основных типов коллекций: list, set и map. Все три существуют в изменяемых и неизменяемых вариациях.
 *
 * - List хранит элементы в указанном порядке и обеспечивает индексированный доступ к ним.
 *
 * - Set хранит уникальные элементы, порядок которых обычно не определен.
 *
 * - Map хранит пары ключ-значение (записи); ключи уникальны, но разные ключи могут сочетаться с одинаковыми значениями.
 *
 * Общие свойства и методы для коллекций:
 *
 * - size возвращает размер вашей коллекции.
 *
 * - contains(element) проверяет, находится ли элемент в вашей коллекции. Рекомендуется использовать ключевое слово in вместо contains. Например, element in collection
 *
 * - containsAll(elements) проверяет, все ли элементы коллекции elements находятся в вашей коллекции.
 *
 * - isEmpty() и isNotEmpty() показывает, пуста коллекция или нет.
 *
 * - joinToString() преобразует коллекцию в строку.
 *
 * - indexOf(element) возвращает индекс первой записи элемента или -1, если элемента нет в коллекции.
 *
 * Все изменяемые коллекции имеют некоторые общие методы:
 *
 * - clear() удаляет все элементы из коллекции.
 *
 * - remove(element) удаляет первое вхождение элемента из вашей коллекции.
 *
 * - removeAll(elements) удаляет из вашей коллекции все элементы, содержащиеся в элементах коллекции.
 */
fun main() {
    immutableLists()
    mutableLists()

    immutableSets()
    mutableSets()

    immutableMaps()
    mutableMaps()

    arrays()
    multiDimensionalArray()
    arrayExceptions()
    stack()
    arrayDeque()

    orderingElementsInCollection()
    retrieveSingleElement()
    collectionsAndNullable()
    filteringElements()
    mappingTransformation()
    aggregateOperations()
    foldAndReduce()
    groupingCollections()
    sequences()
    comparableAndComparator()
    slice()
    take()
    drop()
    chunked()
    windowed()
    distinct()
}

/**
 * List — это неизменяемая коллекция. Его размер не может быть изменён после инициализации. Этот тип позволяет дублировать и хранить элементы в определённом порядке.
 */
fun immutableLists() {
    val cars: List<String> = listOf("BMW", "Honda", "Mercedes")

    println("Cars list: $cars") // [BMW, Honda, Mercedes]
    println("Second car: ${cars[1]}") // Honda
    println("Last car: ${cars[cars.size - 1]}") // Mercedes
    println("Last car: ${cars.last()}") // Mercedes

    /**
     * Пустой список
     */
    val staff = emptyList<String>()

    println("Empty list: $staff") // []
    println("Empty list is empty: ${staff.isEmpty()}") // true

    /**
     * Другой способ создания списка — вызов функции — buildList()
     */
    val names = listOf<String>("Emma", "Kim")

    val list = buildList {
        add("Marta")
        addAll(names)
        add("Kira")
    }

    println("Build list: $list") // [Marta, Emma, Kim, Kira]
    println("Build list is not empty: ${list.isNotEmpty()}") // true
    println("Build list size: ${list.size}") // 4
    println("Has Emma in list: ${"Emma" in list}") // true
    println("Emma position in list: ${list.indexOf("Emma")}") // 1

    println("Sum of numbers: ${sumOfNumbers(listOf(3, 2, 15))}") // 20
}

fun sumOfNumbers(numbers: List<Int>): Int = numbers.sum()

/**
 * MutableList — это упорядоченный изменяемый список переменных одного типа. Вы можете получить доступ к элементам списка по их индексам.
 *
 * Это удобно в тех случаях, когда вы точно не знаете, сколько элементов обработает программа за время своего выполнения.
 */
fun mutableLists() {
    val numbers: MutableList<Double> = mutableListOf(10.8, 14.3, 13.5, 12.1, 9.7)

    println(numbers) // [10.8, 14.3, 13.5, 12.1, 9.7]

    val emptyMutableList = mutableListOf<Boolean>()

    println("Empty list $emptyMutableList") // Empty list []

    /**
     * Создание изменяемого списка заданного размера n
     */
    val list: MutableList<String> = MutableList(5) { "any" }

    println(list) // ["any", "any", "any", "any", "any"]

    println("List size: ${numbers.size}") // 5
    println("List first element (before): ${numbers[0]}") // 10.8

    numbers[0] = 3.14

    println("List first element (after): ${numbers[0]}") // 3.14
    println("List last element: ${numbers[numbers.size - 1]}") // 9.7

    /**
     * Kotlin предоставляет несколько удобных способов получить первый и последний элементы списка, а также последний индекс в списке:
     */
    println("List first element (first): ${numbers.first()}") // '3.14
    println("List last element (last()): ${numbers.last()}") // 9.7
    println("Last list index: ${numbers.lastIndex}") // 4

    /**
     * Создание списка заданного размера и стандартного ввода
     */
    val inputNumbers = MutableList(3) {
        print("Введите число: ")

        readln().toInt()
    }

    println(inputNumbers)

    /**
     * Чтение строки из стандартного ввода и преобразование в список
     */
    val regex = "\\s+".toRegex()  // 1 или более пробельных символов (пробел, табуляция и т. д.)

    print("Введите числа через пробел: ")

    val numbersFromSingleLine = readln()
        .split(regex)
        .map { it.toInt() }
        .toMutableList()

    println(numbersFromSingleLine)

    /**
     * Сравнение списков
     *
     * Возвращается true только в том случае, если элементы двух списков полностью совпадают и расположены в одном порядке.
     */
    val listA: MutableList<Int> = mutableListOf(1, 2, 3, 4)
    val listB: MutableList<Int> = mutableListOf(1, 2, 3, 4)
    val listC: MutableList<Int> = mutableListOf(4, 3, 2, 1)

    println(listA == listA) // true
    println(listA == listB) // true сравнение по значению
    println(listA === listB) // false сравнение по ссылке
    println(listA == listC) // false
    println(listA === listC) // false

    /**
     * Помогает вывести список, используя атрибут разделителя. По умолчанию запятая
     */
    println(numbers.joinToString()) // 3.14, 14.3, 13.5, 12.1, 9.7
    println(numbers.joinToString(" -> ")) // 3.14 -> 14.3 -> 13.5 -> 12.1 -> 9.7

    /**
     * Объединение списков
     */
    val southernCross = mutableListOf("Acrux", "Gacrux", "Imai", "Mimosa")
    val stars = mutableListOf("Ginan", "Mu Crucis")

    val joinedList = southernCross + stars

    println(joinedList) // [Acrux, Gacrux, Imai, Mimosa, Ginan, Mu Crucis]
    println(joinedList.joinToString()) // Acrux, Gacrux, Imai, Mimosa, Ginan, Mu Crucis

    /**
     * Функции добавления, удаления и очистки для изменения списков
     *
     * add(element) и add(index, element) вставляют новые элементы в любую позицию в списке. Если вы не укажете индекс, элемент будет добавлен в конец списка.
     *
     * list1.addAll(list2) добавляет все элементы из list2 в конец list1.
     *
     * remove(element) и removeAt(index) удаляют элемент из списка. Первая функция удаляет один экземпляр указанного элемента из списка (возвращает true, если элемент был успешно удален, иначе возвращает false). Последняя функция удаляет элемент в указанной позиции и возвращает удаленный элемент.
     *
     * clear() удаляет все элементы из списка
     */
    val sourceList = mutableListOf("Any", "Value", "Text", "Any", "Some")

    // Source list: [Any, Value, Text, Any, Some]
    println("Source list: $sourceList")

    sourceList.add("text 2")

    // Add element 'text 2' into last index: [Any, Value, Text, Any, Some, text 2]
    println("Add element 'text 2' into last index: $sourceList")

    sourceList.add(4, "text 7")

    // Add element 'text 7' into 4 index: [Any, Value, Text, Any, text 7, Some, text 2]
    println("Add element 'text 7' into 4 index: $sourceList")

    sourceList.removeAt(2)

    // Remove element by index 2: [Any, Value, Any, text 7, Some, text 2]
    println("Remove element by index 2: $sourceList")

    sourceList.remove("Any")

    // Remove first 'Any': [Value, Any, text 7, Some, text 2]
    println("Remove first 'Any': $sourceList")

    sourceList.clear()

    // Clear list: []
    println("Clear list: $sourceList")

    sourceList.add("text")
    sourceList.addAll(listOf("new text 1", "new text 2", "new text 3"))

    // Add new list via addAl: [text, new text 1, new text 2, new text 3]
    println("Add new list via addAll: $sourceList")

    val intList = mutableListOf(1, 2, 3)

    for (number in 4..7) {
        intList += number
    }

    // Add new list via +=: [1, 2, 3, 4, 5, 6, 7]
    println("Add new list via +=: $intList")

    /**
     * В Kotlin нет функций для копирования существующих списков. Однако вы можете сделать это с помощью функции toMutableList():
     */
    val listOfNumbers = listOf(1, 2, 3, 4, 5)
    val copyList = listOfNumbers.toMutableList()

    // Copy list: [1, 2, 3, 4, 5]
    println("Copy list: $copyList")

    println("Compare by value: ${listOfNumbers == copyList}") // true
    println("Compare by link: ${listOfNumbers === copyList}") // false

    /**
     * Проверка на пустой список
     *
     * list.isEmpty() и list.isNotEmpty() — проверяют, не пуст ли список.
     *
     * list.subList(from, to) – создаёт меньший список (подсписок), в который входят элементы исходного списка со следующими индексами: from, from + 1, ..., to - 2, to - 1.
     *
     * Элемент с индексом to не включен.
     */
    val numbersList = mutableListOf(1, 2, 3, 4, 5)
    var sublist = mutableListOf<Int>()

    println("List of numbers: $numbersList")
    println("List of numbers is empty: ${numbersList.isEmpty()}")
    println("List of numbers is not empty: ${numbersList.isNotEmpty()}")

    println("Sub list (before): $sublist")

    if (numbersList.isNotEmpty() && numbersList.size >= 4) {
        sublist = numbersList.subList(1, 4)
    }

    println("Sub list (after): $sublist") // [2, 3, 4]

    /**
     * list.indexOf(element) – ищет индекс элемента в списке. Результат этой функции равен -1, если в списке нет такого элемента. В противном случае, когда мы обращаемся к списку по вычисляемому индексу, мы получаем элемент.
     */
    val numbersForIndexOf = mutableListOf(1, 3, 5, 4, 2)

    if (5 in numbersForIndexOf) {
        println("Has element 5 in list: index ${numbersForIndexOf.indexOf(5)}") // 2
    }

    println("No such element 7 in list: index ${numbersForIndexOf.indexOf(7)}") // -1

    /**
     * list.minOrNull() и list.maxOrNull() — поиск минимального и максимального элементов в списке.
     */
    val min = numbersForIndexOf.minOrNull()
    val max = numbersForIndexOf.maxOrNull()
    val resultInEmptyList = listOf<Int>().minOrNull()

    println("Min: $min | Max: $max: Null: $resultInEmptyList")

    /**
     * list.sum() — возвращает сумму элементов в списке
     */
    val sum = numbersForIndexOf.sum()

    println("Sum: $sum")

    /**
     * list.sorted() и list.sortedDescending() — построить отсортированный список (по возрастанию или по убыванию) из доступного списка.
     *
     * Возвращает новый список с val и меняет текущий с var
     */
    val sortAsc = numbersForIndexOf.sorted()
    val sortDesc = numbersForIndexOf.sortedDescending()

    println("Source: $numbersForIndexOf")
    println("Sort asc: $sortAsc")
    println("Sort desc: $sortDesc")

    var variableList = mutableListOf(1, 4, 7)
    val sortVariableList = variableList.sorted()

    println("Source variable list: $variableList")
    println("Sort variable list: $sortVariableList")

    shapeShifter()
}

/**
 * Представьте, что у вас есть двумерный список inputList. Гарантируется, что он имеет не менее двух вложенных списков.
 *
 * Создайте новый двумерный список с двумя вложенными списками — первым и последним списками из inputList — в обратном порядке. Другими словами, последний должен стать первым, и наоборот.
 */
fun shapeShifter() {
    val inputList1 = mutableListOf(
        mutableListOf('P', 'R', 'O', 'G', 'R', 'A', 'M', 'M', 'I', 'N', 'G'),
        mutableListOf('I', 'S'),
        mutableListOf('M', 'A', 'G', 'I', 'C')
    )

    // [[M, A, G, I, C], [P, R, O, G, R, A, M, M, I, N, G]]
    println(listOf(inputList1.last().toList(), inputList1.first().toList()))

    val inputList2 = mutableListOf(
        mutableListOf(1, 2),
        mutableListOf(3, 4),
        mutableListOf(5, 6),
        mutableListOf(7, 8)
    )

    // [[7, 8], [1, 2]]
    println(listOf(inputList2.last().toList(), inputList2.first().toList()))
}

/**
 * Set — это неупорядоченная коллекция элементов, которая не допускает дублирования. Это неизменяемая коллекция, что означает, что её размер и отдельные элементы не могут быть изменены после инициализации набора.
 */
fun immutableSets() {
    val visitors: Set<String> = setOf("Vlad", "Vanya", "Liza", "Liza")

    println(visitors) // [Vlad, Vanya, Liza]
    println("Has Vlad in Set: ${"Vlad" in visitors}") // true
    println("Has Vanya in Set: ${visitors.contains("Vanya")}") // true
    println("And what is Liza index? ${visitors.indexOf("Liza")}" ) // 2
    println("First visitor: ${visitors.first()}") // Vlad
    println("Visitor by index 1: ${visitors.elementAt(1)}") // Vanya
    println("Visitor by index 7: ${visitors.elementAtOrNull(7)}") // null
    println("Last visitor: ${visitors.last()}") // Liza

    val joinToString = visitors.joinToString()

    println(joinToString) // Vlad, Vanya, Liza

    val empty: Set<Int> = emptySet()

    println(empty) // []
    println("Empty Set is empty: ${empty.isEmpty()}") // true

    val letters: Set<Char> = setOf('b', 'c')

    val set: Set<Char> = buildSet {
        add('a')
        addAll(letters)
        add('d')
    }

    println(set) // output: [a, b, c, d]
    println("Build Set is not empty: ${set.isNotEmpty()}") // true
    println("Build Set size: ${set.size}") // 4

    val studentsOfAGroup = setOf("Bob", "Larry", "Vlad")
    val studentsInClass = setOf("Vlad")

    println("Are all the students in the group in class today? It's ${studentsInClass.containsAll(studentsOfAGroup)}") // false

    val productsList1 = setOf("Banana", "Lime", "Strawberry")
    val productsList2 = setOf("Strawberry")

    val finalProductsList1 = productsList1 + productsList2

    println(finalProductsList1) // [Banana, Lime, Strawberry]

    val finalProductsList2 = productsList1 - productsList2

    println(finalProductsList2) // [Banana, Lime]

    val visitorsList: List<String> = listOf("Vlad", "Vanya", "Liza", "Liza")
    val visitorsSet: Set<String> = visitorsList.toSet()

    println("Visitors list: $visitorsList") // [Vlad, Vanya, Liza, Liza]
    println("Visitors set: $visitorsSet") // [Vlad, Vanya, Liza]

    for (visitor in visitorsSet) {
        println("Hello $visitor!")
    }
}

/**
 * MutableSet — это неупорядоченная коллекция элементов, не допускающая дублирования. Её можно изменять или модифицировать: добавлять и удалять элементы.
 */
fun mutableSets() {
    val points = mutableSetOf<Int>()

    println(points) // []

    val students = setOf("Joe", "Elena", "Bob").toMutableSet()

    students.add("Bob")

    println(students) // [Joe, Elena, Bob]

    /**
     * MutableSet имеет те же свойства и методы, что и Set: size, isEmpty(), indexOf(element), contains(element), first(), last() и так далее.
     *
     * Также MutableSet предлагает дополнительный функционал для изменения содержимого:
     *
     * - add(element) — добавляет указанный элемент в набор;
     *
     * - addAll(elements) добавляет в набор все элементы указанной коллекции.
     */
    val words = mutableSetOf("Apple", "Coke")
    val friendsWords = mutableSetOf("Banana", "Coke")

    words.add("Phone")
    words.add("Controller")

    friendsWords.add("Phone")
    friendsWords.add("Pasta")
    friendsWords.add("Pizza")

    words.addAll(friendsWords)

    println(words) // [Apple, Coke, Phone, Controller, Banana, Pasta, Pizza]

    /**
     * Вам также может понадобиться удалить некоторые или все элементы из вашего набора
     *
     * - remove(element) удаляет указанный элемент;
     *
     * - clear() удаляет все элементы из текущей коллекции;
     *
     * - removeAll(elements) удаляет все элементы, которые также содержатся в указанной коллекции.
     */
    val groceries = mutableSetOf("Apple", "Water", "Banana", "Pen")

    groceries.remove("Apple")

    println(groceries) // [Water, Banana, Pen]

    val uselessGroceries = setOf("Banana", "Pen")

    groceries.removeAll(uselessGroceries)

    println(groceries) // [Water]

    groceries.clear()

    println(groceries) // []

    for (word in words) {
        println(word)
    }

    val set1 = setOf(10, 11, 14, 16, 2, 1)
    val set2 = setOf(2, 1)

    println("Division problem: ${divisionProblem(set1, set2)}") // [10 14 16 2]

    println("To clear or not to clear ${clearOrNotClear(set1.toMutableSet(), 2)}")
    println("To clear or not to clear ${clearOrNotClear(set1.toMutableSet(), 22)}")

    val newSet: MutableSet<String> = mutableSetOf()
    val oldSet: Set<String> = setOf("parrot", "green", "blue", "ram", "rat", "pig", "cat", "frog", "wolf", "dog", "monkey", "fox", "apple", "Apricot")

    println(solution(newSet, oldSet)) // ["apple", "Apricot"]
}

/**
 * Дан пустой набор и набор строк в качестве входных данных. Переберите непустой набор, добавьте все элементы, начинающиеся с A или a, в первый набор и верните его в качестве результата.
 */
fun solution(newSet: MutableSet<String>, oldSet: Set<String>): MutableSet<String> {
    val filtered: List<String> = oldSet.filter { it.lowercase().first() == 'a' }

    newSet.addAll(filtered)

    return newSet
}

/**
 * Получает два набора Int. Возвращает новый набор элементов в первом наборе, кратный размеру второго набора.
 */
fun divisionProblem(first: Set<Int>, second: Set<Int>): Set<Int> {
    val result: List<Int> = first.filter { it % second.size == 0 }

    return result.toSet()
}

/**
 * Дан MutableSet целых чисел и целое число в качестве входных данных. Проверьте, присутствует ли данное целое число в MutableSet. Если это так, верните пустой набор. Если нет, просто верните тот же набор.
 */
fun clearOrNotClear(elements: MutableSet<Int>, element: Int): MutableSet<Int> {
    return if (element in elements) mutableSetOf() else elements
}

/**
 * Map является неизменяемой коллекцией с парами ключ-значение.
 */
fun immutableMaps() {
    val students: Map<String, Int> = mapOf(
        Pair("Zhenya", 5),
        "Vlad" to 4, // infix function. Инфиксная функция вместо Pair(a, b)
        Pair("Nina", 5),
    )

    println(students) // {Zhenya=5, Vlad=4, Nina=5}

    val gradeNina = students["Nina"]

    println("Nina's grade is: $gradeNina") // Nina's grade is: 5

    /**
     * Запись в Map представлена специальным типом Pair, предназначенным для общих пар двух значений.
     */
    // Простой способ получить первое и второе значения
    val (name, gradeZhenya) = Pair("Zhenya", 12)

    // Student name is: Zhenya And their grade is: 12
    println("Student name is: $name And their grade is: $gradeZhenya")

    val p = Pair(2, 3)

    println("${p.first} ${p.second}") // 2 3

    val (first, second) = p

    println("$first $second") // 2 3

    /**
     * Мы используем конструкцию to для создания записи на карте. Здесь to — это упрощенная конструкция для создания пары (инфиксная функция):
     */
    val (nameVlad, gradeVlad) = "Vlad" to 4

    // output: Student name is: Vlad And their grade is: 4
    println("Student name is: $nameVlad And their grade is: $gradeVlad")

    // Если нужно инициализировать пустую карту, emptyMap<K, V>
    val emptyStringToDoubleMap = emptyMap<String, Double>()

    // Создание через buildMap()
    val values = mapOf<String, Int>(Pair("Second", 2), Pair("Third", 3))

    val map = buildMap<String, Int> {
        put("First", 1)
        putAll(values)
        put("Fourth", 4)
    }

    println(map) // {First=1, Second=2, Third=3, Fourth=4}

    if (map.isNotEmpty()) {
        println("Number of map: ${map.size}")
        println("Third: ${map["Third"]}")
    }

    // Проверка наличия ключа в карте
    println("Vlad is student: ${students.containsKey("Vlad")}")
    println("Jim is student: ${students.containsKey("Jim")}")

    // Проверка наличия значения в карте
    println("Has value 4 in students: ${students.containsValue(4)}")
    println("Has value 7 in students: ${students.containsValue(7)}")

    // Итерация по карте
    for (student in students) {
        println("${student.key} ${student.value}")
    }

    for ((k, v) in students) {
        println("$k $v")
    }

    val intMap: Map<Int, Int> = mapOf(
        Pair(1, 2),
        Pair(2, 3),
        Pair(3, 4),
        Pair(4, 5),
        Pair(5, 6)
    )

    println("Map summator: ${summator1(intMap)}") // 8
    println("Map summator: ${summator2(intMap)}") // 8

    val priceList1: Map<String, Int> = mapOf(
        Pair("Cola", 500),
        Pair("Apple", 1500),
        Pair("Banana", 300)
    )

    val shoppingList1: MutableList<String> = mutableListOf("Cola", "Apple")

    println("bill: ${bill1(priceList1, shoppingList1)}") // 2000
    println("bill: ${bill2(priceList1, shoppingList1)}") // 2000

    val priceList2: Map<String, Int> = mapOf(
        Pair("Pen", 1),
        Pair("Ananas", 2),
        Pair("Sheet", 0)
    )

    val shoppingList2: MutableList<String> = mutableListOf()

    println("bill: ${bill1(priceList2, shoppingList2)}") // 0
    println("bill: ${bill2(priceList2, shoppingList2)}") // 0

    val priceList3: Map<String, Int> = mapOf(
        Pair("Sprite", 150),
        Pair("Lays", 200),
        Pair("Milk", 600),
        Pair("Snickers", 100)
    )

    val shoppingList3: MutableList<String> = mutableListOf("Sprite", "Lays", "Coffee")

    println("bill: ${bill1(priceList3, shoppingList3)}") // 350
    println("bill: ${bill2(priceList3, shoppingList3)}") // 350

    fizzBuzzMap()
    filterAndMatchLists()
}

/**
 * На входе карта число-число.
 *
 * Вернуть сумму значений с чётными ключами
 */
fun summator1(map: Map<Int, Int>): Int {
    var sum = 0

    for ((key, value) in map) {
        if (key % 2 == 0) {
            sum += value
        }
    }

    return sum
}

fun summator2(map: Map<Int, Int>): Int = map.filter { it.key % 2 == 0 }.values.sum()

/**
 * В магазине все товары хранятся в карте Map<String, Int>, которая содержит пары имя-цена.
 * Клиент приходит со списком покупок и хочет знать, какова будет общая стоимость продуктов в списке.
 */
fun bill1(priceList: Map<String, Int>, shoppingList: MutableList<String>): Int {
    var totalPrice = 0

    for (product in shoppingList) {
        if (priceList.containsKey(product)) {
            totalPrice += priceList[product]!!
        }
    }

    return totalPrice
}

fun bill2(priceList: Map<String, Int>, shoppingList: MutableList<String>): Int {
    return shoppingList.sumOf { priceList[it] ?: 0 }
}

/**
 * Есть список URL-адресов веб-сайтов с некоторыми ошибками — заглавными буквами.
 *
 * Выведите количество элементов списка, исправьте ошибки в каждом элементе этого списка, сделайте карту с доменами в качестве ключа и длиной соответствующих URL-адресов в качестве значения.
 */
fun filterAndMatchLists() {
    val input = "htTpS://hypeRskIll.org HTTPS://www.jetbrains.com https://github.com"

    val domains = input.split(' ')

    println(domains.size) // 3

    val domainsMap: Map<String, String> = buildMap {
        for (domain in domains) {
            put(domain.lowercase(), "[${domain.length}]")
        }
    }

    // {https://hyperskill.org=[22], https://www.jetbrains.com=[25], https://github.com=[18]}
    println(domainsMap)
}

/**
 * MutableMap является изменяемой или модифицируемой коллекцией: вы можете свободно добавлять и удалять пары объектов.
 */
fun mutableMaps() {
    val staff: MutableMap<String, Int> = mutableMapOf(
        Pair("John", 500),
        Pair("Mike", 1000),
        Pair("Lara", 1300)
    )

    staff["Nika"] = 999

    println(staff) // {John=500, Mike=1000, Lara=1300, Nika=999}

    // Преобразование обычной карты в мутабельную
    val mapCarsPerYear: Map<Int, Int> = mapOf(Pair(1999, 30000), Pair(2021, 202111))
    val carsPerYear: MutableMap<Int, Int> = mapCarsPerYear.toMutableMap()

    carsPerYear[2020] = 2020

    println(carsPerYear) // {1999=30000, 2021=202111, 2020=2020}}

    /**
     * MutableMap имеет те же свойства и методы, что и Map: size, isEmpty(), containsKey(key), containsValue(element) и так далее.
     *
     * Также MutableMap предлагает дополнительный функционал для изменения содержимого:
     *
     * - put(key, value) связывает указанное значение с указанным ключом в карте;
     * короткая форма mutableMap[key] = value;
     *
     * - putAll(Map) обновляет карту парами ключ/значение из указанной карты;
     *
     * - putIfAbsent(key, value) поместить значение, если ключа нет в карте, в противном случае он оставит карту без изменений.
     */
    val groupOfStudents = mutableMapOf<String, Int>() // empty mutable map

    groupOfStudents.put("John", 4)
    groupOfStudents["Mike"] = 5
    groupOfStudents += mapOf(Pair("Anastasia", 10))
    groupOfStudents += Pair("Alexa", 3)

    val studentsFromOregon = mapOf(Pair("Alexa", 7))

    groupOfStudents.putAll(studentsFromOregon)

    groupOfStudents["John"] = 7 // Перезапишет значение ключа
    groupOfStudents.putIfAbsent("Mike", 9) // Оставит 5, т.к. такой ключ уже есть

    println(groupOfStudents) // {John=7, Mike=5, Anastasia=10, Alexa=7}

    /**
     * Удаление элементов
     *
     * - remove(key) удаляет указанный ключ и соответствующее ему значение с карты;
     *
     * - clear() удаляет все элементы с карты.
     */
    groupOfStudents -= "John"

    println(groupOfStudents) // {Mike=5, Anastasia=10, Alexa=7}

    groupOfStudents.remove("Alexa")

    println(groupOfStudents) // {Mike=5, Anastasia=10}

    groupOfStudents.clear()

    println(groupOfStudents) // {}

    examMarks()

    val userMap: Map<String, String> = mapOf(
        Pair("user1@mail.com", "qwerty123"),
        Pair("hi_john@mail.com", "abcdef00"),
        Pair("dr_mike@mail.com", "000000")
    )

    // {user1@mail.com=qwerty123, hi_john@mail.com=abcdef00, dr_mike@mail.com=000000, new_login=new_password}
    println(addUser(userMap, "new_login", "new_password"))

    // User with this login is already registered!
    // {user1@mail.com=qwerty123, hi_john@mail.com=abcdef00, dr_mike@mail.com=000000}
    println(addUser(userMap, "user1@mail.com", "qwerty123"))

    buySweets()
}

/**
 * Считывает имена студентов и их экзаменационные оценки, добавляет их в MutableMap и печатает результат. В этой MutableMap имена являются ключами, а оценки — значениями. Гарантируется, что ключи являются строками, а значения имеют тип Int.
 *
 * Ключи и значения должны считываться с новой строки каждое. Ввод окончен, когда программа вместо следующей клавиши получает слово «stop». Если один и тот же ключ вводится более одного раза, MutableMap должен сохранить значение, которое было введено первым.
 */
fun examMarks() {
    val studentsMarks: MutableMap<String, Int> = mutableMapOf<String, Int>()

    do {
        print("Введите имя студента (stop для остановки): ")

        val key = readln()

        if (key != "stop") {
            print("Введите оценку студента: ")

            val value: Int = readln().toInt()

            studentsMarks.putIfAbsent(key, value)
        }
    } while (key != "stop")

    println(studentsMarks)
}

fun countSum(sweets: Map<String, Int>): Int = sweets.values.sum()

/**
 * У вас есть корзина со сладостями — она должна быть представлена в виде Map<String, Integer>, где ключ — название сладости, а значение — её цена. Вы должны заполнить Map<String, Integer> из консоли, а затем подсчитать общую стоимость корзины.
 *
 * Во-первых, вы получаете количество сладостей в корзине; затем вы получаете их имена и цены.
 */
fun buySweets() {
    val cart = mutableMapOf<String, Int>()

    print("Введите количество сладостей: ")

    val amountOfSweets: Int = readln().toInt()

    repeat(amountOfSweets) {
        print("Введите через пробел сладость и цену: ")

        val (name, price) = readln().split(' ')

        cart[name] = price.toInt()
    }

    println(cart)

    println(countSum(cart))
}

/**
 * Начинающий программист является администратором сайта. У него есть карта, где для каждого пользователя есть ключ (логин) и значение (пароль). Теперь ему нужно добавить данные нового пользователя.
 *
 * Напишите функцию addUser, которая берёт карту с пользовательскими данными userMap, логином и паролем и добавляет последние два в userMap.
 *
 * Если userMap уже содержит пользователя с таким логином, сначала выведите следующую строку: «Пользователь с таким логином уже зарегистрирован!». После этого выведите userMap без изменений.
 */
fun addUser(userMap: Map<String, String>, login: String, password: String): MutableMap<String, String> {
    val users: MutableMap<String, String> = userMap.toMutableMap()

    if (users.containsKey(login)) {
        println("User with this login is already registered!")
    }

    users.putIfAbsent(login, password)

    return users
}

/**
 * Даётся Map<String, Int> в качестве входных данных. Перебрать карту и вывести Fizz, если значение делится на три без остатка; вывести Buzz, если значение делится на пять без остатка; во всех остальных случаях выведите FizzBuzz.
 */
fun fizzBuzz(map: Map<String, Int>) {
    for (value in map.values) {
        val moduloBy3: Int = value % 3
        val moduloBy5: Int = value % 5

        when {
            moduloBy3 == 0 -> println("Fizz")
            moduloBy5 == 0 -> println("Buzz")
            else -> println("FizzBuzz")
        }
    }
}

fun fizzBuzzMap() {
    val map: Map<String, Int> = mapOf(Pair("a", 3), Pair("b", 5), Pair("c", 7))

    fizzBuzz(map)
}

fun arrays() {
    /**
     * Kotlin может обрабатывать множество типов массивов: IntArray, LongArray, DoubleArray, FloatArray, CharArray, ShortArray, ByteArray, BooleanArray. Обратите внимание, что нет типа StringArray по умолчанию.
     *
     * Чтобы создать массив указанного типа, нам нужно вызвать специальную функцию *ArrayOf()
     *
     * Вы не можете изменить размер массива после его создания.
     *
     * Основные отличия от списка:
     * - Имеет фиксированный размер
     * - Нельзя добавлять и удалять элементы
     * - Оптимизирован под примитивы
     * - Нет прямого сравнения значений через ==
     */
    val numbers1: IntArray = intArrayOf(1, 2, 3, 4, 5)

    println("Numbers: $numbers1") // [I@452b3a41 - только ссылка н массив
    println("Numbers join to string: ${numbers1.joinToString()}") // 1, 2, 3, 4, 5
    println("Numbers size: ${numbers1.size}") // 5

    /**
     * Независимо от того, используете ли вы val или var, вы можете редактировать значения существующих элементов через их индекс
     */
    numbers1[2] = 42
    numbers1[3] = numbers1[0] + numbers1[numbers1.size - 1]

    println("Numbers after replace items: ${numbers1.joinToString()}") // 1, 2, 42, 6, 5

    // println(numbers1[42]) // ArrayIndexOutOfBoundsException

    println("Numbers first: ${numbers1.first()}") // 1
    println("Numbers last: ${numbers1.last()}") // 5
    println("Numbers last index: ${numbers1.lastIndex}") // 4

    val stringArray: Array<String> = arrayOf("array", "of", "strings")

    println("Array of strings: ${stringArray.joinToString()}")

    /**
     * Однако существует большая разница между val и var, когда дело доходит до переназначения. Когда у вас есть массив var, вы можете изменить его, добавив в него новые элементы.
     *
     *  В Kotlin массивы в каком-то смысле неизменяемы. Даже если массив объявлен с помощью var, его нельзя редактировать. В обоих примерах массив был фактически воссоздан. По сути, мы буквально удалили массив и вместо него создали другой.
     */
    var newEmptyArray: Array<String> = emptyArray<String>()

    println("Empty array (before): ${newEmptyArray.joinToString()}")
    println("Empty array size (before): ${newEmptyArray.size}")

    newEmptyArray += "Acrux"
    newEmptyArray += "Gacrux"
    newEmptyArray += "Imai"

    println("Empty array (after): ${newEmptyArray.joinToString()}")
    println("Empty array size (after): ${newEmptyArray.size}")

    val characters: CharArray = charArrayOf('K', 't', 'l')

    println(characters.joinToString()) // K, t, l

    val doubles1: DoubleArray = doubleArrayOf(1.25, 0.17, 0.4)

    println(doubles1.joinToString()) // 1.25, 0.17, 0.4

    /**
     * Создание массива заданного размера
     */
    val numbers2 = IntArray(5) // an array of 5 integer numbers

    println(numbers2.joinToString()) // 0, 0, 0, 0, 0

    val doubles2 = DoubleArray(7) // an array of 7 doubles

    println(doubles2.joinToString()) // 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0

    val numbers3 = IntArray(3) { i -> i + 1 }

    println("Numbers: ${numbers3.joinToString()}") // 1, 2, 3

    val onlyOne = IntArray(3) { 1 }

    println("Only 1: ${onlyOne.joinToString()}") // 1, 1, 1

    val onlyMinusOne = IntArray(3).apply { fill(-1) }

    println("Only -1: ${onlyMinusOne.joinToString()}") // -1, -1, -1

    /**
     * Чтение массива из ввода
     */
    val numbers4 = IntArray(5) {
        print("Введите число - элемент массива: ")

        readln().toInt()
    }

    println(numbers4.joinToString()) // 1, 2, 3, 4, 5

    print("Введите числа - элементы массива через пробел: ")

    val numbers5: Array<Int> = readln().split(' ').map { it.toInt() }.toTypedArray()

    println(numbers5.joinToString()) // 1, 2, 3, 4, 5

    /**
     * С помощью этого регулярного выражения вы можете игнорировать пробелы и табуляции во входной строке
     */
    val regex: Regex = "\\s+".toRegex()
    val str = "1 2\t\t3  4\t5    6"

    val nums: Array<Int> = str.split(regex).map { it.toInt() }.toTypedArray()

    println(nums.joinToString()) // 1, 2, 3, 4, 5, 6

    /**
     * Сравнение массивов
     *
     * Возвращает true только в том случае, если элементы двух массивов полностью совпадают и расположены в одном порядке
     */
    val nums1: IntArray = intArrayOf(1, 2, 3, 4)
    val nums2: IntArray = intArrayOf(1, 2, 3, 4)
    val nums3: IntArray = intArrayOf(1, 2, 3)

    println(nums1.contentEquals(nums2)) // true
    println(nums1.contentEquals(nums3)) // false

    /**
     * Операторы == и != не сравнивают содержимое массивов, они сравнивают только те переменные, которые указывают на один и тот же объект
     */
    val simpleArray = intArrayOf(1, 2, 3, 4)
    val similarArray = intArrayOf(1, 2, 3, 4)

    println(simpleArray == simpleArray)  // true, указывает на тот же объект
    println(simpleArray === simpleArray)  // true, указывает на тот же объект

    println(simpleArray == similarArray) // false, указывает на другой объект
    println(simpleArray === similarArray) // false, указывает на другой объект

    /**
     * Работа с несколькими массивами
     */
    val southernCross: Array<String> = arrayOf("Acrux", "Gacrux", "Imai", "Mimosa")
    val stars: Array<String> = arrayOf("Ginan", "Mu Crucis")

    val newArray: Array<String> = southernCross + stars

    // Acrux, Gacrux, Imai, Mimosa, Ginan, Mu Crucis
    println("Concatenate: ${newArray.joinToString()}")

    /**
     * Итерация по массиву
     */
    for (star in southernCross){
        println("Star: $star")
    }

    /**
     * Итерация по индексам
     */
    for (index in southernCross.indices){
        println("Star (indexes): $index: ${southernCross[index]}")
    }

    /**
     * Итерация по индексам диапазона
     */
    for (index in 2..3) {
        println("Star (range indexes): $index: ${southernCross[index]}")
    }

    for (index in 2 until southernCross.lastIndex) {
        println("Star (until lastIndex): $index: ${southernCross[index]}")
    }

    for (index in southernCross.lastIndex downTo 0 step 2) {
        println("Star (lastIndex downTo step 2): $index: ${southernCross[index]}")
    }

    /**
     * Чтение элементов массива
     */
    print("Укажите длину массива: ")

    val size = readln().toInt()
    val array = IntArray(size)

    for (index in 0..array.lastIndex) {
        print("Введите число - элемент массива: ")

        array[index] = readln().toInt()
    }

    print("Результат в обратном порядке: ")

    for (index in array.lastIndex downTo 0) {
        print("${array[index]} ")
    }

    println()

    countingNumbers()
    rightRotation()
    isItHere()
}

/**
 * Считает, сколько раз число M встречается в N числах.
 *
 * Первая строка содержит число N.
 * Следующие N строк содержат числа.
 * Последняя строка содержит число M.
 *
 * Выведите только одно неотрицательное целое число.
 */
fun countingNumbers() {
    print("Укажите длину массива: ")

    val size: Int = readln().toInt()
    val array = IntArray(size)

    for (index in 0..array.lastIndex) {
        print("Введите число - элемент массива: ")

        array[index] = readln().toInt()
    }

    print("Введите искомое число: ")

    val query: Int = readln().toInt()
    val count: Int = array.count { it == query }

    println("Количество искомых чисел в массиве: $count")
}

/**
 * Правое вращение — это операция, которая сдвигает каждый элемент массива вправо.
 *
 * Если правое вращение равно 1 с массивом {1,2,3,4,5}, новый массив будет {5,1,2,3,4}.
 *
 * Другой пример, если поворот равен 2, массив равен {1,2,3,4,5}, новый массив будет {4,5,1,2,3}, потому что
 * {1,2,3,4,5} -> {5,1,2,3,4} -> {4,5,1,2,3}.
 *
 * Первая строка входных данных содержит количество элементов в массиве N.
 * Следующие N строк содержат элементы массива.
 * Последнее — это значение вращения.
 *
 * Выходные данные содержат сдвинутые элементы массива. Разделяйте элементы пробелом.
 */
fun rightRotation() {
    print("Укажите длину массива: ")

    val size: Int = readln().toInt()
    val array = IntArray(size)

    for (index in 0..array.lastIndex) {
        print("Введите число - элемент массива: ")

        array[index] = readln().toInt()
    }

    print("Введите количество смещений массива: ")

    val rotationValue: Int = readln().toInt() % size

    val start: IntArray = array.sliceArray(size - rotationValue..array.lastIndex)
    val end: IntArray = array.sliceArray(0 until size - rotationValue)

    println("Массив со смещением: ${(start + end).joinToString(" ")}")
}

/**
 * Проверяет, содержит ли набор из N чисел число M.
 *
 * Первая строка содержит число N.
 * Следующие N строк содержат числа.
 * В последней строке записано одно целое число M.
 *
 * Вам нужно вывести YES или NO.
 */
fun isItHere() {
    print("Укажите длину массива: ")

    val size: Int = readln().toInt()
    val array = IntArray(size)

    for (index in 0..array.lastIndex) {
        print("Введите число - элемент массива: ")

        array[index] = readln().toInt()
    }

    print("Введите искомое число: ")

    val query: Int = readln().toInt()

    println(if (array.contains(query)) "YES" else "NO")
}

/**
 * Многомерные массивы
 */
fun multiDimensionalArray() {
    println("--- Creating 2-dimensional arrays ---")

    // Двумерный массив Int с 3 строками и 4 столбцами, где все элементы равны 0
    val arrayOfStrings2D: Array<Array<String>> = arrayOf(
        arrayOf("1-1", "1-2", "1-3", "1-4"),
        arrayOf("2-1", "2-2", "2-3", "2-4"),
        arrayOf("3-1", "3-2", "3-3", "3-4")
    )

    println(arrayOfStrings2D.contentDeepToString())

    for (array in arrayOfStrings2D) {
        println(array.joinToString())
    }

    val arrayOfNumbers2D: Array<Array<Int>> = arrayOf(
        arrayOf(0),
        arrayOf(1, 2),
        arrayOf(3, 4, 5)
    )

    for (array in arrayOfNumbers2D) {
        println(array.joinToString())
    }

    // Пустой двумерный массив
    val empty2DInt: Array<Array<Int>> = arrayOf()

    println("--- Accessing elements ---")

    println("Get 3 cell in 2 row: ${arrayOfStrings2D[1][2]}") // 2-3

    println("--- Creating 2D arrays of different types ---")

    val arrayOfChar2D: Array<CharArray> = arrayOf(
        charArrayOf('A', 'R', 'R'),
        charArrayOf('A', 'Y', 'S'),
    )

    println(arrayOfChar2D.contentDeepToString())

    val multiDimensionalArray2D = arrayOf(
        arrayOf("Practice", "makes", "perfect"),
        intArrayOf(1, 2)
    )

    println(multiDimensionalArray2D.contentDeepToString())

    println("--- Multi-dimensional arrays (>2) ---")

    val array3D: Array<Array<Array<Int>>> = arrayOf(
        arrayOf(arrayOf(0,1), arrayOf(2,3)),
        arrayOf(arrayOf(4,5), arrayOf(6,7))
    )

    println(array3D.contentDeepToString())


    /**
     * Создание трёхмерного массива 3x3x3, содержащего элементы типа Int и заполненных нулями
     */
    val array3x3: Array<Array<IntArray>> = Array(3) {
        Array(3) {
            IntArray(3) { 0 }
        }
    }

    println(array3x3.contentDeepToString())
}

/**
 * Когда ваша программа обрабатывает массив, могут возникать различные типы исключений. Чтобы избежать их, вы должны знать о рискованных ситуациях и придерживаться набора часто используемых практик.
 */
fun arrayExceptions() {
    println("--- NullPointerException ---")

    val numbers1: IntArray? = null

    // val size = numbers!!.size // It throws NPE
    val size = numbers1?.size ?: 0

    println("Size: $size") // 0

    println("--- NegativeArraySizeException ---")

    /**
     * Если вы попытаетесь создать массив с отрицательным размером, ваш код будет успешно скомпилирован, но соответствующая строка вызовет NegativeArraySizeException во время выполнения.
     *
     * Чтобы этого избежать, просто не используйте переменные, которые могут иметь отрицательный размер, при задании размера массива.
     */
    val negSize = -1

    // val numbers2 = IntArray(negSize) // It throws NegativeArraySizeException

    println("--- ArrayIndexOutOfBoundsException ---")

    /**
     * Это вызвано попыткой доступа к несуществующему элементу массива.
     *
     * Код выдаст такое же исключение, если мы попытаемся получить доступ к элементу с отрицательным индексом
     *
     * Чтобы избежать исключения, мы можем проверить, принадлежит ли данный индекс интервалу [0, size - 1].
     */
    val array = intArrayOf(1, 2, 3)

    val n1 = array[2] // 3
    // val n2 = array[3] // It throws ArrayIndexOutOfBoundsException
    // val n3 = array[-1]; // It throws ArrayIndexOutOfBoundsException

    val indexes = listOf(-1, 0, 1, 3)

    for (index in indexes) {
        if (index in array.indices) {
            println(array[index]) // for 0, 1
        } else {
            println("The index is out of bounds.") // for -1, 3
        }
    }

    println("--- StringIndexOutOfBoundsException ---")

    /**
     * Поскольку строку можно рассматривать как последовательность символов, аналогичное исключение может возникнуть при доступе к несуществующему элементу строки.
     */
    val string = "string"

    // val ch: Char = string[6] // It throws StringIndexOutOfBoundsException

    val stringIndexes = listOf(-1, 0, 1, 3, 6)

    for (index in stringIndexes) {
        if (index in string.indices) {
            println(string[index]) // for 0, 1, 3
        } else {
            println("The check works, there is no exception.") // for -1, 6
        }
    }
}

/**
 * В JVM класс Stack моделирует и реализует структуру данных Stack, используя стратегию LIFO (Last-In-First-Out).
 *
 * Это класс Java; он не принадлежит к чистым коллекциям Kotlin, поэтому вы должны импортировать его, чтобы иметь возможность его использовать: import java.util.stack
 *
 * Лучше использоваться реализацию Kotlin - ArrayDeque, она больше оптимизирована и более производительна.
 *
 * Когда вы добавляете элемент в стек, вы кладете его на вершину стека. Когда вы удаляете элемент из стека, вы всегда удаляете самый верхний элемент. Он расширяет класс Vector пятью операциями, которые позволяют рассматривать вектор как стек. Другие существующие методы унаследованы от Vector.
 *
 * Операции со стеком:
 * - push() помещает элемент на вершину стека
 *
 * - pop() удаляет объект из вершины стека и возвращает этот объект. Он выдаст EmptyStackException, если этот стек пуст.
 *
 * - peek() извлекает первый/верхний элемент стека, не удаляя его из стека. Он выдаст EmptyStackException, если этот стек пуст.
 *
 * - empty() возвращает true, если на вершине стека ничего нет; иначе он вернёт false
 *
 * - search() возвращает позицию элемента с вершины стека; иначе он вернёт -1.
 */
fun stack() {
    val stack = Stack<Int>()

    // push at top
    stack.push(1)
    stack.push(2)
    stack.push(3)

    println(stack) // [1, 2, 3]

    // Удаляем верхний элемент
    stack.pop()

    println(stack) // [1, 2]

    // Получаем верхний элемент без удаления
    println(stack.peek()) // 2

    println(stack) // [1, 2]

    // Ищем позицию элемента в стеке
    println(stack.search(1)) // 2
    println(stack.search(2)) // 1
    println(stack.search(9)) // -1

    // is empty?
    println(stack.empty()) // false

    stack.pop()
    stack.pop()

    println(stack) // []

    println(stack.empty()) // true

    /**
     * Кроме того, мы можем преобразовать список в стек и работать с ним, используя функцию pop()
     */
    val listOfNames: List<String> = listOf("John", "Jane", "Mary", "Peter", "Paul", "George")
    val stackOfNames = Stack<String>()

    stackOfNames.addAll(listOfNames)

    // George Paul Peter Mary Jane John
    while (stackOfNames.isNotEmpty()) {
        print(stackOfNames.pop())
        print(" ")
    }
}

/**
 * При работе с коллекциями важно адаптировать их функциональность к решаемой задаче. Многие коллекции имеют реализованное поведение, упрощающее работу с ними.
 *
 * Существует два типа коллекций с особым поведением, которые вы будете очень часто использовать в своих задачах: очереди (Queue) и стеки (Stack).
 *
 * - Queue: использует поведение FIFO — принцип «первым пришёл — первым обслужен». В таких коллекциях данные всегда добавляются в конец и удаляются всегда с начала. Его основные методы — enqueue (добавить в конец) и dequeue (удалить с начала).
 *
 * - Stack: он использует поведение LIFO — принцип «последним пришёл — первым вышел». В соответствии с ним последний добавленный элемент будет первым удалён, то есть элементы уложены друг на друга. Его основными методами являются push (добавить сверху), pop (удалить сверху) и peek, который возвращает верхний элемент.
 */
fun arrayDeque() {
    println("--- Queue ---")

    /**
     * Мы можем получить поведение очереди благодаря MutableList, и он работает следующим образом: используя add() для постановки в очередь, то есть добавить в конец и removeFirst() для удаления первого элемента списка. Хитрость в том, что каждая из операций действует на разные стороны (голову и хвост) коллекции.
     */
    val queue = mutableListOf<Int>()

    queue.add(1)
    queue.add(2)
    queue.add(3)
    queue.add(4)

    println(queue) // [1, 2, 3, 4]

    queue.removeFirst()
    queue.removeFirst()

    println(queue) // [3, 4]

    println("--- Stack ---")

    /**
     * Мы можем имитировать поведение стека с помощью MutableList, используя add() для добавления элемента в конец и removeLast() для удаления последнего элемента. Хитрость в том, что обе операции действуют на одну и ту же сторону коллекции: в данном случае хвост или конец.
     */
    val stack = mutableListOf<Int>()

    stack.add(1)
    stack.add(2)
    stack.add(3)
    stack.add(4)

    println(stack) // [1, 2, 3, 4]

    stack.removeLast()
    stack.removeLast()

    println(stack) // [1, 2]

    println("--- ArrayDeque ---")

    /**
     * ArrayDeque — это коллекция, реализующая как принцип Queue (первым пришёл — первым обслужен), так и принцип Deque (двухсторонняя очередь, «первым пришёл — первым обслужен», «последним пришёл — первым обслужен»).
     *
     * Он также известен как двусторонняя очередь массива или колода массива. Он позволяет добавлять и удалять элементы с обеих сторон или концов коллекции: коллекция предоставляет методы для удобного доступа к обеим сторонам.
     *
     * Он также реализует интерфейс MutableList и поддерживает эффективные операции получения/установки по индексу.
     *
     * Таким образом, с ним вы также можете использовать все привычные методы MutableList.
     *
     * Итак, зачем нам ArrayDeque, если мы можем выполнять аналогичные задачи, используя MutableList?
     *
     * Вы должны использовать ArrayDeque всякий раз, когда вам нужна функциональность «двусторонней очереди», Queue или Stack: его методы были семантически адаптированы для функционального соответствия таким задачам.
     */
    val deque = ArrayDeque<Int>()

    // Как Queue, FIFO в обе стороны
    deque.addLast(1)
    deque.addLast(2)
    deque.addLast(3)

    println(deque) // [1, 2, 3]

    deque.removeFirst()
    deque.removeFirst()

    println(deque) // [3]

    // Как Stack, LIFO с одной стороны, т.е. конец
    deque.addLast(1)
    deque.addLast(2)

    println(deque) // [3, 1, 2]

    deque.removeLast()
    deque.removeLast()

    println(deque) // [3]

    // Или LIFO с другой стороны, т.е. начало
    deque.addFirst(1)
    deque.addFirst(2)

    println(deque) // [2, 1, 3]

    deque.removeFirst()
    deque.removeFirst()

    println(deque) // [3]

    println("--- Adding elements ---")

    /**
     * Помните, если вам нужна очередь, вы должны добавить элементы в конец (очередь в супермаркете); если вы хотите стек, вы должны добавить их в начало (стопка грязной посуды).
     *
     * Вот несколько методов для выполнения этих задач:
     *
     * - add(): добавляет указанный элемент в конец списка и возвращает true. Возвращает true, потому что список всегда изменяется в результате этой операции.
     *
     * - addAll(): добавляет все элементы (коллекцию) в конец. Возвращает true, потому что список всегда изменяется в результате этой операции.
     *
     * - addFirst(): добавляет указанный элемент в начало коллекции. Возвращает единицу.
     *
     * - addLast(): добавляет указанный элемент в конец коллекции. Возвращает единицу.
     */
    val deque2 = ArrayDeque<Int>()

    deque2.add(1)
    deque2.add(2)
    deque2.add(3)

    println(deque2) // [1, 2, 3]

    deque2.addAll(listOf(5, 6, 7))

    println(deque2) // [1, 2, 3, 5, 6, 7]

    deque2.addFirst(8)

    println(deque2) // [8, 1, 2, 3, 5, 6, 7]

    deque2.addLast(9)

    println(deque2) // [8, 1, 2, 3, 5, 6, 7, 9]

    println("--- Removing elements ---")

    /**
     * Следующий шаг — удаление элементов коллекции: вы можете либо «обслужить человека в очереди супермаркета» — обработать первый предмет — либо «сначала убрать верхнее блюдо» в очереди грязной посуды.
     *
     * Вот несколько методов для выполнения этих задач:
     *
     * - remove(): удаляет один экземпляр указанного элемента из коллекции, если он присутствует. Он возвращает true, если элемент был успешно удалён, и false, если его нет в коллекции.
     *
     * - removeAll(): удаляет все элементы коллекции, которые также содержатся в указанной коллекции. Он вернет true, если какой-либо из указанных элементов был удалён из коллекции, и false, если коллекция не была изменена.
     *
     * - removeFirst(): удаляет первый элемент из двухсторонней очереди и возвращает этот удалённый элемент или создаёт исключение NoSuchElementException, если двухсторонняя очередь пуста.
     *
     * - removeLast(): удаляет последний элемент из очереди и возвращает этот удалённый элемент или генерирует исключение NoSuchElementException, если очередь пуста.
     */
    val deque3 = ArrayDeque<Int>()

    deque3.addAll(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9))

    println(deque3) // [1, 2, 3, 4, 5, 6, 7, 8, 9]

    deque3.remove(5)

    println(deque3) // [1, 2, 3, 4, 6, 7, 8, 9]

    deque3.removeAll(listOf(1, 2))

    println(deque3) // [3, 4, 6, 7, 8, 9]

    deque3.removeFirst()

    println(deque3) // [4, 7, 8, 9]

    deque3.removeLast()

    println(deque3) // [4, 7, 8]

    deque3.clear()

    println(deque3) // []

    println("--- Getting elements ---")

    /**
     * Представьте, что вы хотите узнать, кто стоит в очереди в супермаркете или какая первая/последняя грязная тарелка в стопке, не обрабатывая её.
     *
     * Вы можете получить элементы коллекции с помощью:
     *
     * - get(): возвращает элемент по указанному индексу в списке; вы можете использовать [].
     *
     * - first(): возвращает первый элемент или генерирует исключение NoSuchElementException, если очередь пуста.
     *
     * - firstOrNull(): возвращает первый элемент или null, если очередь пуста.
     *
     * - last(): возвращает последний элемент или генерирует исключение NoSuchElementException, если очередь пуста.
     *
     * - lastOrNull(): возвращает последний элемент или null, если очередь пуста.
     */
    val deque4 = ArrayDeque<Int>()

    deque4.addAll(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9))

    println(deque4) // [1, 2, 3, 4, 5, 6, 7, 8, 9]

    println(deque4.first()) // 1
    println(deque4.last()) // 9

    deque4.clear()

    println(deque4.firstOrNull()) // null
    println(deque4.lastOrNull()) // null

    hackingMatrix()
}

/**
 * Нео пытается взломать Матрицу. У него есть список кодов, но для решения этой задачи он должен сделать следующее: если индекс чётный, взять первый элемент, иначе взять последний.
 *
 * Пожалуйста, распечатайте результат, чтобы помочь Нео взломать Матрицу.
 */
fun hackingMatrix() {
    val input = "1 2 3 8 10 10"
    val list = input.split(' ').map { it.toInt() }

    val deque = ArrayDeque<Int>()

    deque.addAll(list)

    val hackCode = mutableListOf<Int>()

    for (index in 0..<deque.size) {
        val isEven = index % 2 == 0

        if (isEven) {
            hackCode.add(deque.first())

            deque.removeFirst()
        } else {
            hackCode.add(deque.last())

            deque.removeLast()
        }
    }

    println(hackCode.joinToString(" ")) // 1 10 2 10 3 8
}

/**
 * Kotlin предлагает вам возможность определять порядок элементов коллекции различными способами: в естественном, обратном, случайном или пользовательском порядке.
 */
fun orderingElementsInCollection() {
    /**
     * Sorting objects
     *
     * Есть две функции: sorted() и sortedDescending() для сортировки элементов коллекции в естественном порядке, определённом в интерфейсе Comparable.
     * - sorted() даёт нам коллекцию, в которой элементы отсортированы в порядке возрастания.
     * - sortedDescending() используется для применения порядка убывания.
     */
    val numbers = mutableListOf(3, 5, 6, 4, 1, 8, 2, 7)

    println("--- Sorting ---")

    println(numbers.sorted()) // [1, 2, 3, 4, 5, 6, 7, 8]
    println(numbers.sortedDescending()) // [8, 7, 6, 5, 4, 3, 2, 1]

    val words = listOf("racecar", "mom", "dad", "abracadabra", "MANDRAKE")

    println(words.sorted()) // [MANDRAKE, abracadabra, dad, mom, racecar]
    println(words.sortedDescending()) // [racecar, mom, dad, abracadabra, MANDRAKE]

    /**
     * Reverse order
     *
     * Мы можем получить коллекцию в обратном порядке (на основе позиций индекса) с помощью двух функций: reversed() и asReversed().
     *
     * - reversed() возвращает копию исходной коллекции в порядке, обратном индексу. Если вы измените исходную коллекцию, изменения не повлияют на копию.
     * - asReversed() возвращает ссылку на исходную коллекцию в порядке, обратном индексу. Он легче, потому что не создаёт новую копию, но если исходная коллекция изменяется, изменения отражаются в перевёрнутом списке. Мы должны быть осторожны, если работаем с изменяемыми коллекциями.
     */
    val reversedNumbers = numbers.reversed()

    println("--- Reverse ---")

    println(numbers) // [3, 5, 6, 4, 1, 8, 2, 7]
    println(reversedNumbers) // [7, 2, 8, 1, 4, 6, 5, 3]

    numbers.add(9)

    println(numbers) // [3, 5, 6, 4, 1, 8, 2, 7, 9]
    println(reversedNumbers) // [7, 2, 8, 1, 4, 6, 5, 3]

    val wordsReversed = words.reversed()

    println(words) // [racecar, mom, dad, abracadabra, MANDRAKE]
    println(wordsReversed) // [MANDRAKE, abracadabra, dad, mom, racecar]

    val numbersAsReversed = numbers.asReversed()

    println(numbers) // [3, 5, 6, 4, 1, 8, 2, 7, 9]
    println(numbersAsReversed) // [9, 7, 2, 8, 1, 4, 6, 5, 3]

    numbers.add(19)

    println(numbers) // [3, 5, 6, 4, 1, 8, 2, 7, 9, 19]
    println(numbersAsReversed) // [19, 9, 7, 2, 8, 1, 4, 6, 5, 3]

    val wordsAsReversed = words.asReversed()

    println(words) // [racecar, mom, dad, abracadabra, MANDRAKE]
    println(wordsAsReversed) // [MANDRAKE, abracadabra, dad, mom, racecar]

    /**
     * Random order
     *
     * - shuffled() даёт новую коллекцию, в которой исходные элементы перемешиваются случайным образом. Вы можете использовать его без аргументов или с экземпляром Random в качестве источника случайности. Использование целого числа в качестве начального числа в генераторе случайных чисел перетасует элементы случайным образом, что можно воспроизвести, если вы продолжите использовать одно и то же начальное число.
     */
    val shuffledNumbers = numbers.shuffled()

    println("--- Random ---")

    println(numbers) // [3, 5, 6, 4, 1, 8, 2, 7, 9, 19]
    println(shuffledNumbers) // [6, 2, 8, 7, 9, 19, 5, 1, 4, 3]

    val shuffledWords = words.shuffled(Random(1))

    println(words) // [racecar, mom, dad, abracadabra, MANDRAKE]
    println(shuffledWords) // [MANDRAKE, abracadabra, mom, dad, racecar]

    palindromeList()
}

fun palindromeList() {
    val list = listOf("anne", "peter", "anne")

    println(list == list.reversed())
}

fun retrieveSingleElement() {
    /**
     * Получение элемента по позиции
     *
     * Вызывают исключение IndexOutOfBoundsException, если индекс не находится в указанных пределах (0..size-1).
     */
    val charList: List<Char> = listOf('а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и')
    val charSet: Set<Char> = setOf('а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и')

    println("--- elementAt ---")

    println(charList.elementAt(6)) // 'ё'
    println(charSet.elementAt(6)) // 'ё'

    println("--- get ---")

    println(charList.get(7)) // 'ж'
    // println(charSet.get(7)) // error: 'get' is not a member of 'Set'

    println("--- [index] ---")

    println(charList[3]) // 'г'
    // println(charSet[3]) // error: 'get' is not a member of 'Set'

    // Выдаст исключение, если индекс выходит за пределы
    // println(charList[30]) // error: Index 30 out of bounds for length 10
    // println(charSet.elementAt(30)) // error: Index 30 out of bounds for length 10

    println("--- first ---")

    println(charList.first()) // 'а'
    println(charSet.first()) // 'а'

    println("--- last ---")

    println(charList.last()) // 'и'
    println(charSet.last()) // 'и'

    /**
     * Чтобы не было проблем с исключениями при попытке получить недопустимый элемент индекса, мы можем использовать следующие безопасные вызовы
     * - elementAtOrNull(): возвращает значение null, когда позиция индекса выходит за границы коллекции.
     * - elementAtOrElse(): принимает лямбда-функцию, которая возвращает результат лямбда-выражения, если позиция индекса выходит за границы коллекции.
     *
     * - getOrNull(): эквивалентно elementAtOrNull() для списка.
     * - getOrElse(): эквивалентно elementAtOrElse() для списка.
     *
     * - firstOrNull(): используется для получения первого элемента или null, если коллекция пуста.
     * - lastOrNull(): используется для получения последнего элемента или null, если коллекция пуста.
     */
    val emptyList = listOf<Int>()
    val emptySet = setOf<Int>()

    println("--- firstOrNull ---")

    println(charList.firstOrNull()) // 'а'
    println(emptyList.firstOrNull()) // null
    println(charSet.firstOrNull()) // 'а'
    println(emptySet.firstOrNull()) // null

    println("--- lastOrNull ---")

    println(charList.lastOrNull()) // 'и'
    println(emptyList.lastOrNull()) // null
    println(charSet.lastOrNull()) // 'и'
    println(emptySet.lastOrNull()) // null

    println("--- elementAtOrNull ---")

    println(charList.elementAtOrNull(6)) // 'ё'
    println(charList.elementAtOrNull(30)) // null
    println(charSet.elementAtOrNull(6)) // 'ё'
    println(charSet.elementAtOrNull(30)) // null

    println("--- getOrNull ---")

    println(charList.getOrNull(7)) // 'ж'
    println(charList.getOrNull(30)) // null
    // println(charSet.getOrNull(30)) // error: 'getOrNull' is not a member of 'Set'

    println("--- elementAtOrElse ---")

    println(charList.elementAtOrElse(6) { "element not found" }) // 'ё'
    println(charList.elementAtOrElse(30) { "element not found" }) // element not found
    println(charSet.elementAtOrElse(6) { "element not found" }) // 'ё'
    println(charSet.elementAtOrElse(30) { "element not found" }) // element not found

    println("--- getOrElse ---")

    println(charList.getOrElse(6) { "element not found" }) // 'ё'
    println(charList.getOrElse(30) { "element not found" }) // element not found
    // println(charSet.getOrElse(30) { "element not found" }) // error: 'getOrElse' is not a member of 'Set'

    /**
     * Получение элемента по условию
     *
     * - find() возвращает первый элемент коллекции в соответствии с предикатом или null, если он не существует.
     *
     * - findLast() возвращает последний элемент коллекции в соответствии с предикатом или null, если он не существует.
     */
    val numberList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val numberSet = setOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    println("--- first() with a lambda predicate ---")

    println(numberList.first { it > 5 }) // 6
    println(numberSet.first { it > 5 }) // 6

    println("--- last() with a lambda predicate ---")

    println(numberList.last { it > 5 }) // 10
    println(numberSet.last { it > 5 }) // 10

    // println(numberList.first { it > 50 }) // error: NoSuchElementException
    // println(numberSet.first { it > 50 }) // error: NoSuchElementException

    // println(numberList.last { it > 50 }) // error: NoSuchElementException
    // println(numberSet.last { it > 50 }) // error: NoSuchElementException

    println("--- firstOrNull() with a lambda predicate ---")

    println(charList.firstOrNull { it > 'г' }) // 'д'
    println(emptyList.firstOrNull { it > 50 }) // null
    println(charSet.firstOrNull { it > 'г' }) // 'д'
    println(emptySet.firstOrNull { it > 50 }) // null

    println("--- lastOrNull() with a lambda predicate ---")

    println(charList.lastOrNull { it > 'г' }) // 'и'
    println(emptyList.lastOrNull { it > 50 }) // null
    println(charSet.lastOrNull { it > 'г' }) // 'и'
    println(emptySet.lastOrNull { it > 50 }) // null

    println("--- find() with a lambda predicate ---")

    println(numberList.find { it > 5 }) // 6
    println(emptyList.find { it > 50 }) // null
    println(numberSet.find { it > 5 }) // 6
    println(emptySet.find { it > 50 }) // null

    println("--- findLast() with a lambda predicate ---")

    println(numberList.findLast { it > 5 }) // 10
    println(emptyList.findLast { it > 50 }) // null
    println(numberSet.findLast { it > 5 }) // 10
    println(emptySet.findLast { it > 50 }) // null

    /**
     * Получение элемента с помощью селектора
     *
     * - firstNotNullOf() сопоставляет коллекцию с функцией выбора и возвращает первое ненулевое значение в результате. Генерирует исключение NoSuchElementException, если нет ненулевого элемента.
     * - firstNotNullOfOrNull() - аналогичен firstNotNullOf(), но возвращает null вместо исключения
     */
    val listOfNames = listOf("John", "Jane", "Mary", "Peter")

    println("--- firstNotNullOf() ---")

    fun getFirstNotNullName(length: Int): String {
        return listOfNames.firstNotNullOf { item ->
            item.uppercase().takeIf { it.length >= length }
        }
    }

    println(getFirstNotNullName(4)) // JOHN
    // println(getFirstNotNullName(10)) // Exception

    println("--- firstNotNullOfOrNull() ---")

    fun getFirstNotNullNameOrNull(length: Int): String? {
        return listOfNames.firstNotNullOfOrNull { item ->
            item.uppercase().takeIf { it.length >= length }
        }
    }

    println(getFirstNotNullNameOrNull(4)) // JOHN
    println(getFirstNotNullNameOrNull(10)) // null

    /**
     * Случайный элемент
     *
     * - random() - возвращает случайный элемент. Генерирует исключение NoSuchElementException, если список пуст
     * - randomOrNull() - аналогичен random(), но возвращает null вместо исключения
     */
    val emptyListNames = listOf<String>()

    println("--- random() ---")

    println(listOfNames.random()) // Peter
    // println(emptyListNames.random()) // Exception

    println("--- randomOrNull() ---")

    println(listOfNames.randomOrNull()) // Jane
    println(emptyListNames.randomOrNull()) // null

    /**
     * Проверить, существует ли элемент или набор элементов
     *
     * - contains() возвращает true, если элемент найден в коллекции.
     * - оператор in - аналогичен contains()
     * - containsAll() возвращает true, если коллекция содержит несколько элементов
     * - isEmpty - коллекция пуста
     * - isNotEmpty - коллекция не пуста
     */
    println("--- contains() ---")

    println(listOfNames.contains("John")) // true
    println(listOfNames.contains("john")) // false

    println("--- in operator ---")

    println("John" in listOfNames) // true
    println("john" in listOfNames) // false

    println("--- containsAll() ---")

    println(listOfNames.containsAll(listOf("John", "Jane"))) // true
    println(listOfNames.containsAll(listOf("John", "Jane", "john"))) // false
    println(listOfNames.containsAll(emptyListNames)) // true

    println("--- isEmpty() ---")

    println(listOfNames.isEmpty()) // false
    println(emptyListNames.isEmpty()) // true

    println("--- isNotEmpty() ---")

    println(listOfNames.isNotEmpty()) // true
    println(emptyListNames.isNotEmpty()) // false

    harryAndSpellPower()
    secretCodeToSaveCity()
    studentsGrades()
}

data class Spell(val name: String, val power: Int)

/**
 * Гарри Поттер изучает различные заклинания. У него есть список заклинаний и значений их силы. Он должен найти первое заклинание с силой больше 50. Пожалуйста, помогите Гарри найти заклинание или напечатайте «No spell found», если оно не существует.
 */
fun harryAndSpellPower() {
    val list = listOf("Expetum-98", "Patronus-83", "Axio-29", "Lithigum-23")

    val spells: List<Spell> = list.map {
        Spell(it.split('-')[0], it.split('-')[1].toInt())
    }

    val spell: Any = spells.find { it.power > 50 } ?: "No spell found"

    println(spell) // Spell(name=Expetum, power=98)


}

/**
 * Итан Хант готов к очередной «Миссия невыполнима». Чтобы предотвратить побег вируса из нелегальной лаборатории, он должен ввести правильный код.
 *
 * Он знает только, что код получается следующим образом: дан список слов, он должен взять первое перевёрнутое слово, начинающееся с «m», и ввести его.
 */
fun secretCodeToSaveCity() {
    val list: List<String> = listOf("exilium", "gold", "silver", "bronze", "platinum")

    val code: String = list.firstNotNullOfOrNull { item ->
        item.reversed().takeIf { it.first() == 'm' }
    } ?: "No code found"

    println(code) // muilixe
}

/**
 * У вас есть список оценок учеников (в порядке возрастания). Учащиеся должны получать уведомления об оценке, превышающей или равную 5.0 (если она существует, или null в противном случае) и их наивысшей оценке, разделённой двоеточием «:».
 */
fun studentsGrades() {
    val list: List<Double> = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)

    val gradeLimit = 5.0
    val minGrade: Double? = list.find { it >= gradeLimit }
    val maxGrade: Double? = list.findLast { it >= gradeLimit }

    val notify: String? = if (minGrade != null) "$minGrade:$maxGrade" else minGrade

    println(notify) // 5.0:10.0
}

fun collectionsAndNullable() {
    /**
     * Пустая не обнуляемая коллекция
     *
     * Простой пустой список. Мы можем справиться с этим, как с обычным списком, и не нужно беспокоиться об исключении NullPointerException. Этот список реален и не нулевой, он просто пуст.
     */
    val list: List<String> = listOf()

    println("Empty list: $list")

    /**
     * Обнуляемая коллекция. Либо коллекция, либо null
     *
     * Элементы в таком списке не обнуляемые, они должны быть целыми числами. Но переменная nullableList может быть null.
     */
    var nullableList: List<Int>? = listOf(1, 2, 4, 6)

    /**
     * А когда мы работаем со списком, допускающим значение null, нам приходится использовать оператор безопасного вызова, проверки, оператор Элвиса и т.д.
     */
    val nullableListWithCheck1: List<Int> = nullableList ?: listOf()

    println("Nullable list: $nullableList") // [1, 2, 4, 6]
    println("Nullable list with check: $nullableListWithCheck1") // [1, 2, 4, 6]

    nullableList = null

    val nullableListWithCheck2: List<Int> = nullableList ?: listOf()

    println("Nullable list: $nullableList") // null
    println("Nullable list with check: $nullableListWithCheck2") // []

    /**
     * Не обнуляемая коллекция, допускающая элементы null
     *
     * В этом случае у нас есть список с элементами null. Он имеет не обнуляемый тип, но элементы внутри обнуляемые.
     */
    val listWithNullableElements: List<Int?> = listOf(1, 2, 4, null, null)

    val num1: Int = listWithNullableElements[1] ?: 42
    val num2: Int = listWithNullableElements[3] ?: 42

    println("Item in list with nullable elements: $num1") // 2
    println("Item in list with nullable elements: $num2") // 42

    /**
     * Обнуляемая коллекция, допускающая элементы null
     */
    var absolutelyNullableList: List<Int?>? = listOf(1, 2, 4, null, null)

    val num3: Int = absolutelyNullableList?.get(1) ?: 42
    val num4: Int = absolutelyNullableList?.get(3) ?: 42

    println("Item in nullable list with nullable elements: $num3") // 2
    println("Item in nullable list with nullable elements: $num4") // 42

    absolutelyNullableList = null

    val num5: Int = absolutelyNullableList?.get(1) ?: 42
    val num6: Int = absolutelyNullableList?.get(3) ?: 42

    println("Item in nullable list with nullable elements: $num5") // 42
    println("Item in nullable list with nullable elements: $num6") // 42

    /**
     * Иногда у вас есть последовательности элементов с нулевым значением, и вам нужно использовать их для создания коллекции без нулевого значения. В этом случае вы можете использовать специальные функции listOfNotNull() и setOfNotNull(), которые помогают нам удалить все нулевые элементы и вернуть коллекции только для чтения с ненулевым типом по умолчанию.
     */
    val emptyList: List<Int> = listOfNotNull(null, null, null)

    // [1, 50, 404, 42, 42, 404]
    val listWithoutNull: List<Int> = listOfNotNull(1, null, 50, 404, 42, null, 42, 404)

    // [1, 50, 404, 42]
    val setWithoutNull: Set<Int> = setOfNotNull(1, null, 50, 404, 42, null, 42, 404)

    println("Empty list without null: $emptyList") // []
    println("List without null: $listWithoutNull") // [1, 50, 404, 42, 42, 404]
    println("Set without null: $setWithoutNull") // [1, 50, 404, 42]

    /**
     * Функция isNullOrEmpty() возвращает значение true, если коллекция пуста или равна нулю. Во всех остальных случаях возвращает false.
     */
    val emptySet: Set<Int>? = setOf()
    val nullSet: Set<Int>? = null
    val set = setOf<Int?>(null, null)

    println("--- isNullOrEmpty ---")

    println(emptySet.isNullOrEmpty()) // true потому что коллекция пуста
    println(nullSet.isNullOrEmpty()) // true потому что коллекция равна null
    println(set.isNullOrEmpty()) // false потому что в коллекции есть два элемента со значением null

    /**
     * Функция getOrNull() возвращает один элемент списка или массива, но если этого элемента не существует, возвращает null (не работает с Set).
     *
     * Вы можете сказать, что можно использовать просто list[3], но в этом случае мы получим исключение, а getOrNull() действительно возвращает значение в каждом случае.
     */
    val list1 = listOf(0, 1, 2)

    println("--- getOrNull ---")

    println(list1.getOrNull(2)) // 2
    println(list1.getOrNull(3)) // null, нет 4 элемента

    /**
     * Функция randomOrNull() работает так же, как и предыдущая: возвращает null, если коллекция пуста, и случайный элемент во всех остальных случаях.
     */
    val list2 = listOf<Int>()

    println("--- randomOrNull ---")

    println(list1.randomOrNull()) // вернёт какой-то элемент
    println(list2.randomOrNull()) // null, коллекция пуста

    /**
     * Функции firstOrNull() и lastOrNull() позволяют нам устанавливать определенные условия. И если есть хотя бы один элемент, удовлетворяющий условию, они его возвращают.
     */
    val list3 = listOf(0, 1, 1, 2, 5, 7, 6)

    println("--- firstOrNull & lastOrNull ---")

    val num7 = list3.firstOrNull()
    val num8 = list3.firstOrNull { it > 3 }
    val num9 = list3.firstOrNull { it > 13 }
    val num10 = list3.lastOrNull()
    val num11 = list3.lastOrNull { it == 1 }
    val num12 = list3.lastOrNull { it == 10 }

    println(num7) // 0
    println(num8) // 5
    println(num9) // null
    println(num10) // 6
    println(num11) // 1
    println(num12) // null

    /**
     * Минимальные и максимальные элементы с нулевым значением
     *
     * - minOrNull()/maxOrNull() - вернуть максимальный или минимальный элемент коллекции или null, если коллекция пуста.
     *
     * - minByOrNull()/maxByOrNull() - вернуть первый наибольший или наименьший элемент коллекции, который удовлетворяет условию или null
     *
     * - minOfOrNull()/maxOfOrNull() - вернуть значение характеристики элемента, отмеченной в условии, или null
     *
     * - minWithOrNull()/maxWithOrNull() - вернуть первый элемент, удовлетворяющий условию, указанному в блоке compareBy {}, или null
     *
     * - minOfWithOrNull()/maxOfWithOrNull() - вернуть значение характеристики элемента, помеченного в условии, указанном в блоке compareBy {}, или null
     */
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    println("--- min/max or null ---")

    println("minOrNull: ${numbers.minOrNull()}") // 1
    println("maxOrNull: ${numbers.maxOrNull()}") // 10

    println("minByOrNull: ${numbers.minByOrNull { it > 3 }}") // 1
    println("maxByOrNull: ${numbers.maxByOrNull { it > 3 }}") // 4

    println("minOfOrNull: ${numbers.minOfOrNull { it > 3 }}") // false
    println("maxOfOrNull: ${numbers.maxOfOrNull { it > 3 }}") // true

    println("minWithOrNull: ${numbers.minWithOrNull(compareBy { it > 3 })}") // 1
    println("maxWithOrNull: ${numbers.maxWithOrNull(compareBy { it > 3 })}") // 4

    println("minOfWithOrNull: ${numbers.minOfWithOrNull(naturalOrder()) { it > 3 }}") // false
    println("maxOfWithOrNull: ${numbers.maxOfWithOrNull(naturalOrder()) { it > 3 }}") // true

    firstShortestWord()
}

/**
 * Верните первое кратчайшее слово, первая буква которого от «a» до «l», или null, если такое слово не существует
 */
fun firstShortestWord() {
    val list: List<String> = listOf("parrot", "green", "blue", "ram", "rat", "pig", "cat", "frog", "wolf", "dog", "monkey", "fox")

    val filtered = list.filter { it.first() in 'a'..'l' }

    println(filtered.minByOrNull { it.length }) // cat
}

val isEven: (Int) -> Boolean = { x -> x % 2 == 0 }

/**
 * `filter()` принимает предикат и возвращает новую коллекцию с элементами, удовлетворяющими предикату (или условию).
 *
 * `filterIndexed()` принимает индекс элемента и сам элемент и возвращает коллекцию, которая соответствует предикату.
 *
 * `filterNot()` возвращает коллекцию с элементами, не соответствующими предикату.
 *
 * `filterIsInstance()` возвращает коллекцию с элементами типа, указанного в предикате.
 *
 * `filterNotNull()` возвращает все элементы, которые не равны null.
 *
 * `partition()` разбивает исходную коллекцию на две части: одну с элементами, удовлетворяющими предикату, и другую с остальными элементами исходной коллекции (теми, которые не соответствуют предикату).
 *
 * Эти методы доступны во всех базовых коллекциях Kotlin (списки, наборы и карты).
 */
fun filteringElements() {
    val numbers = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    println("--- List of even numbers ---")

    // [0, 2, 4, 6, 8, 10]
    println(numbers.filter { x -> x % 2 == 0 })
    println(numbers.filter { isEven(it) })
    println(numbers.filter(isEven))

    println("--- List of odd numbers ---")

    // [1, 3, 5, 7, 9]
    println(numbers.filter { x -> x % 2 != 0 })
    println(numbers.filterNot { x -> x % 2 == 0 })
    println(numbers.filterNot { isEven(it) })
    println(numbers.filterNot(isEven))

    println("--- List even number with index greater than 3 ---")

    // [4, 6, 8, 10]
    println(numbers.filterIndexed { index, number ->
        index > 3 && number % 2 == 0
    })

    println("--- List of words with odd index and starting with \"k\" ---")

    val words = listOf("peter", "kyle", "robert", "kate", "kevin", "anne", "jeremy")

    // [kyle, kate]
    println(words.filterIndexed { i, word ->
        i % 2 != 0 && word.startsWith("k")
    })

    println("--- Filtering and types ---")

    val elements = listOf(null, 0, "string", 3.14, null, 'c', "Luke")

    println("--- Only string elements ---")

    // [string, Luke]
    println(elements.filter { x -> x is String })
    println(elements.filterIsInstance<String>())

    println("--- Only integer elements ---")

    println(elements.filterIsInstance<Int>()) // [0]

    println("--- Not null elements ---")

    // [0, string, 3.14, c, Luke]
    println(elements.filterNot { x -> x == null })
    println(elements.filterNotNull())

    println("--- Partitioning ---")

    val (even1, odd1) = numbers.partition { x -> x % 2 == 0 }
    val (even2, odd2) = numbers.partition(isEven)

    // [0, 2, 4, 6, 8, 10]
    println(even1)
    println(even2)

    // [1, 3, 5, 7, 9]
    println(odd1)
    println(odd2)

    redPillAndBluePill()
    filteredPalindromeList()
}

/**
 * У вас есть список таблеток разного цвета, и вы должны отфильтровать только красные и синие и помочь Нео решить его дилемму.
 */
fun redPillAndBluePill() {
    val input = "blue red orange black"
    val validPills = listOf("blue", "red")

    val pills = input.split(' ').filter { validPills.contains(it) }

    println(pills) // [blue, red]
}

/**
 * У нас есть список слов, и мы должны получить список палиндромов (регистр букв не важен).
 *
 * Используя функцию фильтрации, получите список палиндромов.
 */
fun filteredPalindromeList() {
    val input = "kayak deified rotator test repaper"

    val list: List<String> = input.split(' ').filter {
        val word: String = it.lowercase()

        word.reversed() == word
    }

    println(list) // [kayak, deified, rotator, repaper]
}

/**
 * С помощью функций map() и mapIndexed() мы можем создать новую коллекцию, применив лямбда-функцию преобразования к нашей исходной коллекции.
 *
 * Если вы примените функцию преобразования к элементам и преобразование не может быть выполнено для некоторых из них, результат для этих элементов будет null.
 *
 * Вы можете избежать этих результатов, используя mapNotNull() или mapIndexedNotNull().
 *
 * Эти функции представляют собой комбинации map/mapIndexed с filterNotNull.
 *
 * Есть функции преобразования, которые помогут нам обработать несколько коллекций.
 *
 * Функция flatten() возвращает единый список всех элементов из всех коллекций в данной коллекции.
 *
 * Это очень полезно, если у нас есть несколько коллекций и мы хотим получить одну коллекцию, которая их объединит.
 *
 * Функция flatMap() принимает функцию, которая сопоставляет элементы одной коллекции с другой и возвращает список всех значений в этих элементах. Это эквивалентно сопоставлению каждого элемента вместе со сведением для получения окончательного результата.
 *
 * Это очень полезно, если перед нами стоит задача отображения связей «один ко многим».
 *
 * Функция zip() — позволяет получить пару на основе двух элементов, занимающих одну и ту же позицию в двух разных коллекциях.
 *
 * Если исходные коллекции не имеют одинакового размера, результатом будет список пар, ограниченный размером меньшей коллекции, игнорируя остальные элементы большей коллекции.
 *
 * Функция unzip() - позволяет получить обратное преобразование - разбиение пар на разные списки. В первом будут первые элементы пары, во втором — вторые элементы.
 *
 * Метод associateWith() позволяет нам получить карту, элементы которой являются ключами. Значения карты получаются с помощью функции преобразования. Если два объекта одинаковы, в карте будет только один из них.
 *
 * Метод associateBy() предлагает нам способ создания карты, где элементами коллекции являются значения. Ключ получается с помощью функции преобразования. Если два предмета имеют один и тот же ключ, в карте будет только один из них.
 *
 * Функция associate() принимает лямбду, возвращает Pair, соответствующую записи карты. Его следует использовать с осторожностью – когда производительность не критична и когда вы не можете использовать другие варианты.
 *
 * Одна из проблем, с которыми вы сталкиваетесь при работе с коллекциями, заключается в том, что их нелегко прочитать. Благодаря этим методам вы можете представлять коллекции в читаемом формате.
 *
 * Метод joinToString() создаёт строковое представление при вызове toString() на основе элементов коллекции, используя следующие аргументы:
 *
 * - separator - разделитель между элементами, например, ";"
 * - prefix - представляет начало коллекции, например, "{"
 * - postfix - представляет конец коллекции, например "}"
 * - limit - количество элементов, включаемых в строковое представление, например, 3
 * - truncated - когда вы используете limit, остальные элементы будут представлены этим значением, например, "..."
 *
 * Метод joinTo() выполняет ту же задачу — добавляет результат к заданному объекту Appendable (объекту, к которому можно добавлять последовательности символов и значения, например, StringBuilder).
 */
fun mappingTransformation() {
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val words = listOf("anne", "michael", "caroline", "dimitry", "emilio")

    // Mapping
    println(numbers.map { it * 2 }) // [2, 4, 6, 8, 10, 12, 14, 16, 18, 20]
    println(words.map { it.uppercase() }) // [ANNE, MICHAEL, CAROLINE, DIMITRY, EMILIO]

    // Mapping with index
    // [0, 2, 6, 12, 20, 30, 42, 56, 72, 90]
    println(numbers.mapIndexed { index, value -> value * index })

    // [ANNE, michael, CAROLINE, dimitry, EMILIO]
    println(words.mapIndexed { index, value ->
        if (index % 2 == 0) value.uppercase() else value
    })

    // List of word lengths
    println(words.map { it.length }) // [4, 7, 8, 7, 6]

    // List of strings of numbers
    val numbersString = listOf("1", "2", "3", "4", "5a", "6", "7", "8", "9", "10")

    // List of Ints of numbers.
    // It will return a list of Int? (Int or null)
    val myNumbersWithNulls = numbersString.map { it.toIntOrNull() }
    val myNumbersWithoutNulls = numbersString.mapNotNull { it.toIntOrNull() }

    println(myNumbersWithNulls) // [1, 2, 3, 4, null, 6, 7, 8, 9, 10]
    println(myNumbersWithoutNulls) // [1, 2, 3, 4, 6, 7, 8, 9, 10]

    // List of words
    val wordsString = listOf("anne", "michael", "caroline", "dimitry", "alicia")

    // map and filter it.
    // It will return a list of Strings (String or null)
    val myWordsWithNulls = wordsString.map {
        if (it.startsWith("a")) {
            it.uppercase()
        } else {
            null
        }
    }

    val myWordsWithoutNulls = wordsString.mapNotNull {
        if (it.startsWith("a")) {
            it.uppercase()
        } else {
            null
        }
    }

    println(myWordsWithNulls) // [ANNE, null, null, null, ALICIA]
    println(myWordsWithoutNulls) // [ANNE, ALICIA]

    // Map
    val map = mapOf(
        Pair(1, "one"),
        Pair(2, "two"),
        Pair(3, "three"),
        Pair(4, "four"),
        Pair(5, "five"),
    )

    println(map.map { it.key }) // [1, 2, 3, 4, 5]
    println(map.map { it.value }) // [one, two, three, four, five]

    println(map.mapKeys { it.key * 2 }) // {2=one, 4=two, 6=three, 8=four, 10=five}
    println(map.mapValues { it.value.uppercase() }) // {1=ONE, 2=TWO, 3=THREE, 4=FOUR, 5=FIVE}

    // Map numbers to words.
    // key and length of value
    println(map.mapValues { it.value.length }) // {1=3, 2=3, 3=5, 4=4, 5=4}

    // reversed value where the key is even
    // {1=one, 2=owt, 3=three, 4=ruof, 5=five}
    println(map.mapValues {
        if (it.key % 2 == 0) {
            it.value.reversed()
        } else {
            it.value
        }
    })

    // Flatten (flatten/flatMap)
    val nestedNumbers = listOf(
        listOf(1, 2, 3),
        listOf(4, 5, 6),
        listOf(7, 8, 9)
    )

    val nestedWords = listOf(
        listOf("anne", "michael"),
        listOf("caroline", "dmitry"),
        listOf("emilio", "francois")
    )

    println(nestedNumbers.flatten()) // [1, 2, 3, 4, 5, 6, 7, 8, 9]
    println(nestedWords.flatten()) // [anne, michael, caroline, dmitry, emilio, francois]

    // [2, 4, 6, 8, 10, 12, 14, 16, 18]
    println(nestedNumbers.flatMap { list -> list.map { it * 2 } })

    // [ANNE, MICHAEL, CAROLINE, DMITRY, EMILIO, FRANCOIS]
    println(nestedWords.flatMap { list -> list.map { it.uppercase() } })

    val listOfMaps = listOf(
        mapOf(Pair(1, "one"), Pair(2, "two")),
        mapOf(Pair(3, "three"), Pair(4, "four")),
    )

    val resMapFlatten = listOfMaps
        .flatMap { it.entries }
        .associate { it.toPair() } // it.toPair() | Pair(it.key, it.value) | it.key to it.value

    println(resMapFlatten) // {1=one, 2=two, 3=three, 4=four}

    val listOfListOfList = listOf(
        listOf(listOf(1, 2, 3), listOf(4, 5, 6)),
        listOf(listOf(7, 8, 9), listOf(10, 11, 12))
    )

    /// or .flatten().flatten()
    val resListFlatten = listOfListOfList.flatMap { it.flatten() }

    println(resListFlatten) // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]

    // Zipping
    val zipped = numbers.zip(words)

    println(zipped) // [(1, anne), (2, michael), (3, caroline), (4, dimitry), (5, emilio)]

    // Unzipping
    val unzipped = zipped.unzip()

    println(unzipped) // ([1, 2, 3, 4, 5], [anne, michael, caroline, dimitry, emilio])

    // Zipping with transform
    val zippedWithTransform = numbers.zip(words) { number, string ->
        "$number: $string"
    }

    println(zippedWithTransform) // [1: anne, 2: michael, 3: caroline, 4: dimitry, 5: emilio]

    // Associate With
    val associatedWith = words.associateWith { it.length }

    println(associatedWith) // {anne=4, michael=7, caroline=8, dimitry=7, emilio=6}

    // Associate By
    val associatedBy = words.associateBy { it.first().uppercase() }

    println(associatedBy) // {A=anne, M=michael, C=caroline, D=dimitry, E=emilio}

    // Associate with a lambda
    val associatedWithLambda = words.associate {
        Pair(it.first().uppercase(), it.length)
    }

    println(associatedWithLambda) // {A=4, M=7, C=8, D=7, E=6}

    // joinToString
    val joinToString = numbers.joinToString(
        separator = " ; ",
        prefix = "{ ",
        postfix = " }",
        limit = 3,
        truncated = "..."
    )

    println(joinToString) // { 1 ; 2 ; 3 ; ... }

    // joinTo appendable
    val joinTo = numbers.joinTo(
        StringBuilder(),
        separator = ", ",
        prefix = "[",
        postfix = "]",
        limit = 3,
        truncated = "..."
    )

    println(joinTo) // [1, 2, 3, ...]
}

/**
 * Получение значения на основе содержимого коллекции, например, максимальное значение, среднее значение, объект с максимальным значением свойства и т.д.
 *
 * Эти методы известны как «методы агрегирования»:
 *
 * - minOrNull() возвращает наименьший элемент коллекции или значение null в пустой коллекции.
 *
 * - maxOrNull() возвращает самый большой элемент коллекции или значение null в пустой коллекции.
 *
 * - average() возвращает среднее значение элементов в коллекции с числовыми значениями.
 *
 * - sum() возвращает сумму всех элементов коллекции с числовыми значениями.
 *
 * - count() возвращает количество элементов в коллекции.
 *
 * - minByOrNull() с функцией селектора возвращает элемент с наименьшим значением. Он используется вместо minBy(), чтобы избежать исключения.
 *
 * - maxByOrNull() с функцией селектора возвращает элемент с наибольшим значением. Он используется вместо maxBy(), которое выдаёт исключение NoSuchElementException, чтобы избежать исключения.
 *
 * - minOfOrNull() с функцией селектора возвращает наименьшее возвращаемое значение самого селектора. Это развитие minOf(), которое выдаёт исключение NoSuchElementException, если коллекция пуста.
 *
 * - maxOfOrNull() с функцией селектора возвращает наибольшее возвращаемое значение самого селектора. Это развитие метода maxOf(), который выдаёт исключение, если коллекция пуста.
 *
 * - sumOf() с функцией селектора возвращает сумму своего применения ко всем элементам коллекции.
 *
 * - minWithOrNull() возвращает наименьший элемент согласно компаратору.
 *
 * - maxWithOrNull() возвращает наибольший элемент по данным компаратора.
 *
 * - minOfWithOrNull() возвращает наименьшее возвращаемое значение селектора согласно компаратору.
 *
 * - maxOfWithOrNull() возвращает наибольшее возвращаемое значение селектора согласно компаратору.
 */
fun aggregateOperations() {
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val words = listOf("anne", "michael", "caroline", "dimitry", "emilio")
    val emptyWordsList = emptyList<String>()

    println("--- Min and Max ---")

    println(numbers.minOrNull()) // 1
    println(numbers.maxOrNull()) // 10

    println("--- Average ---")

    println(numbers.average()) // 5.5
    println(numbers.filter { it % 2 == 0 }.average()) // 6.0
    println(words.map { it.length }.average()) // 6.4
    println(words.filter { it.startsWith("d") }.map { it.length }.average()) // 7.0

    println("--- Sum ---")

    println(numbers.sum()) // 55
    println(numbers.sumOf { it }) // 55
    println(words.sumOf { it.length }) // 32
    println(numbers.sumOf { it * 2 }) // 110
    println(numbers.sumOf { if (it % 2 == 0) it else 0 }) // 30

    println(words.sumOf { it.length }) // 32
    println(words.sumOf { if (it.startsWith("d")) it.length else 0 }) // 7

    println("--- Count ---")

    println(numbers.size) // 10
    println(words.size) // 5

    println(numbers.count()) // 10
    println(numbers.count { it % 2 == 0 }) // 5

    println(words.count()) // 5
    println(words.count { it.first() == 'a' }) // 1
    println(words.count { it.length > 5 }) // 4
    println(words.count { it.startsWith("d") }) // 1

    println(emptyWordsList.count()) // 0

    println("--- maxByOrNull / minByOrNull ---")

    println(numbers.minByOrNull { it }) // 1
    println(numbers.maxByOrNull { it }) // 10

    println(words.minByOrNull { it.length }) // anne
    println(words.maxByOrNull { it.length }) // caroline

    println(words.minByOrNull { it.length > 5 }) // anne
    println(words.maxByOrNull { it.length > 5 }) // michael

    println(emptyWordsList.minByOrNull { it.length }) // null
    println(emptyWordsList.maxByOrNull { it.length }) // null

    // println(emptyWordsList.minBy { it.length })  // NoSuchElementException
    // println(emptyWordsList.maxBy { it.length })  // NoSuchElementException

    println("--- maxOfOrNull / minOfOrNull ---")

    println(numbers.minOfOrNull { it }) // 1
    println(numbers.maxOfOrNull { it }) // 10
    println(words.minOfOrNull { it.length }) // 4
    println(words.maxOfOrNull { it.length }) // 8

    println(emptyWordsList.minOfOrNull { it.length }) // null
    println(emptyWordsList.maxOfOrNull { it.length }) // null

    // println(emptyWordsList.minOf { it.length })  // NoSuchElementException
    // println(emptyWordsList.maxOf { it.length })  // NoSuchElementException

    println("--- maxWithOrNull / minWithOrNull ---")

    println(words.minWithOrNull(compareBy { it.length })) // anne
    println(words.maxWithOrNull(compareBy { it.length })) // caroline
    println(words.minWithOrNull(compareBy { it.length > 5 })) // anne
    println(words.maxWithOrNull(compareBy { it.length > 5 })) // michael

    println("--- maxOfWithOrNull / minOfWithOrNull ---")

    println(words.minOfWithOrNull(naturalOrder()) { it.length }) // 4
    println(words.maxOfWithOrNull(naturalOrder()) { it.length }) // 8
    println(words.minOfWithOrNull(naturalOrder()) { it.length > 5 }) // false
    println(words.maxOfWithOrNull(naturalOrder()) { it.length > 5 }) // true
}

/**
 * С помощью fold() и reduce() мы можем работать с элементами коллекции как с последовательностью и возвращать накопленный результат.
 *
 * - reduce() преобразует данную коллекцию в один результат. Для объединения пары элементов в накопленное значение требуется оператор лямбда-функции. Затем он проходит по коллекции и шаг за шагом выполняет операцию, объединяя накопленное значение со следующим элементом коллекции.
 *
 * Выполняет на один шаг меньше, поскольку аккумулятор является первым элементом.
 * Выдаст исключение UnsupportedOperationException с пустой коллекцией.
 * Результат операции всегда будет того же типа (или супертипа), что и исходные данные — мы не можем изменить его на другой тип.
 *
 * - fold() принимает начальное значение, использует его как накопленное значение на первом шаге и применяет операцию слева направо, объединяя текущее накопленное значение с каждым элементом.
 *
 * Выполняет столько операций, сколько элементов в коллекции.
 * Значение по умолчанию будет итоговым значением при использовании с пустой коллекцией.
 * Можно изменить тип возвращаемого значения.
 *
 * Иногда нам нужно выполнить действия в обратном порядке. Мы можем использовать foldRight() и reduceRight() для применения операций справа налево.
 *
 * Однако помните, что аргументы операции меняют свой порядок: сначала идет элемент, а затем накопленное значение.
 *
 * Если нужно использовать индексы, можно пользоваться методами reduceIndexed(), foldIndexed(), reduceRightIndexed(), foldRightIndexed()
 *
 * Все операции reduce, которые мы представили, вызывают исключение для пустых коллекций. Если мы хотим получить значение null, мы должны использовать те же методы, но заканчивающиеся на *OrNull(): reduceOrNull(), reduceRightOrNull(), reduceIndexedOrNull() и reduceRightIndexedOrNull()
 *
 * В некоторых случаях интересно сохранить промежуточное значение аккумулятора. Для этого у нас есть runningFold(), runningReduce(), runningFoldIndexed() и runningReduceIndexed(), которые возвращают список с промежуточными результатами.
 */
fun foldAndReduce() {
    val list = listOf(1, 2, 3, 4, 5)

    println("-- fold sum --")

    // fold использует параметр в качестве начального значения аккумулятора
    var sum = list.fold(0) { accumulator, element ->
        println("acc: $accumulator, i: $element, acc + i: ${accumulator + element}")

        accumulator + element
    }

    println(sum) // 15

    println("-- reduce sum --")

    // reduce использует первый элемент в качестве начального значения аккумулятора
    sum = list.reduce { accumulator, element ->
        println("acc: $accumulator, i: $element, acc + i: ${accumulator + element}")

        accumulator + element
    }

    println(sum) // 15

    println("-- fold multiplication --")

    var product = list.fold(1) { accumulator, element ->
        println("acc: $accumulator, i: $element, acc * i: ${accumulator * element}")

        accumulator * element
    }

    println(product) // 120

    println("-- reduce multiplication --")

    product = list.reduce { accumulator, element ->
        println("acc: $accumulator, i: $element, acc * i: ${accumulator * element}")

        accumulator * element
    }

    println(product) // 120

    println("-- fold / reduce with empty list --")

    val emptyList = listOf<Int>()

    sum = emptyList.fold(0) { accumulator, element ->
        println("acc: $accumulator, i: $element, acc + i: ${accumulator + element}")

        accumulator + element
    }

    println(sum) // 0

    // Выдаст исключение UnsupportedOperationException
    // sum = emptyList.reduce { accumulator, element ->
    //     println("acc: $accumulator, i: $element, acc + i: ${accumulator + element}")
    //
    //     accumulator + element
    // }

    println("-- fold / reduce change type --")

    val list2 = listOf("a", "b", "c", "d", "e")

    val string = list2.fold(StringBuilder()) { accumulator, string ->
        accumulator.append(string)
    }

    println(string) // abcde

    // Ошибка компиляции, нельзя изменить тип
    // string = list2.reduce(StringBuilder()) { accumulator, string ->
    //     accumulator.append(string)
    // }

    println("-- fold / reduce reverse --")

    sum = list.foldRight(0) { element, accumulator -> accumulator + element }

    println(sum) // 15

    product = list.foldRight(1) { element, accumulator -> accumulator * element }

    println(product) // 120

    println("-- fold / reduce indexed --")

    sum = list.foldIndexed(0) { index, accumulator, element ->
        if (index % 2 == 0) accumulator + element else accumulator
    }

    println(sum) // 9

    sum = list.foldRightIndexed(0) { index, element, accumulator ->
        if (index % 2 == 0) accumulator + element else accumulator
    }

    println(sum) // 9

    sum = list.reduceIndexed { index, accumulator, element ->
        if (index % 2 == 0) accumulator + element else accumulator
    }

    println(sum) // 9

    sum = list.reduceRightIndexed { index, element, accumulator ->
        if (index % 2 == 0) accumulator + element else accumulator
    }

    println(sum) // 9

    println("-- runningFold and runningReduce --")

    val runningSum = list.runningFold(0) { accumulator, element ->
        accumulator + element
    }

    println(runningSum) // [0, 1, 3, 6, 10, 15]

    val runningProduct = list.runningReduce { accumulator, element ->
        accumulator * element
    }

    println(runningProduct) // [1, 2, 6, 24, 120]

    // with index
    val runningSumWithIndex = list.runningFoldIndexed(0) { index, accumulator, element ->
        if (index % 2 == 0) accumulator + element else accumulator
    }

    println(runningSumWithIndex) // [0, 1, 1, 4, 4, 9]

    val runningProductWithIndex = list.runningReduceIndexed { index, accumulator, element ->
        if (index % 2 == 1) accumulator * element else accumulator
    }

    println(runningProductWithIndex) // [1, 2, 2, 8, 8]
}

/**
 * Функция расширения groupBy() используется для группировки элементов коллекции.
 *
 * Принимает лямбда-функцию и возвращает карту, сгруппированную по ключам, с полем значения, содержащим все соответствующие элементы коллекции.
 *
 * Вы можете использовать groupBy() со вторым лямбда-аргументом в качестве функции преобразования.
 *
 * Результатом является карта, на которой ключи, созданные функцией keySelector, сопоставляются со значениями, а каждое значение является результатом применения функции преобразования valueTransform к каждому элементу группировки.
 *
 * Иногда, когда мы работаем с коллекциями, нам хочется применить операцию ко всем группам одновременно.
 *
 * Мы можем выполнить эту задачу, используя groupingBy(). Он возвращает экземпляр группировки, который позволяет применять операции ко всем группам ленивым способом: то есть группы создаются до выполнения операции.
 *
 * С помощью функции aggregate() мы применяем операцию ко всем элементам в каждой группе и возвращаем результат. Это общий способ выполнения всех операций группировки, если методов fold и reduce недостаточно.
 *
 * Мы можем группировать элементы из источника Grouping по ключу и применять операцию к элементам каждой группы последовательно, передавая ранее накопленное значение и текущий элемент в качестве аргументов, и сохранять результаты в новой карте.
 */
fun groupingCollections() {
    val names = listOf("John", "Jane", "Mary", "Peter", "John", "Jane", "Mary", "Peter")

    // Группировка по первому символу имени
    val groupedNames = names.groupBy { it.first() }

    // {
    //   J=[John, Jane, John, Jane],
    //   M=[Mary, Mary],
    //   P=[Peter, Peter]
    // }
    println(groupedNames)

    // Группировка по длине имени и значениями, преобразованными в верхний регистр.
    val groupedNames2 = names.groupBy(
        keySelector = { it.length },
        valueTransform = { it.uppercase() }
    )

    // {
    //   4=[JOHN, JANE, MARY, JOHN, JANE, MARY],
    //   5=[PETER, PETER]
    // }
    println(groupedNames2)

    // Группировка по первой букве и подсчёт всех значений
    val groupedNames3 = names.groupingBy { it.first() }.eachCount()

    println(groupedNames3) // {J=4, M=2, P=2}

    // Группировка по первой букве и складывание (накапливание) длины имен.
    val groupedNames4 = names
        .groupingBy { it.first() }
        .fold(0) { acc, name -> acc + name.length }

    println(groupedNames4) // {J=16, M=8, P=10}

    // Группировка по длине и сокращение до самого длинного имени
    val groupedNames5 = names
        .groupingBy { it.length }
        .reduce { _, acc, name ->
            if (name.length > acc.length) name else acc
        }

    println(groupedNames5) // {4=John, 5=Peter}

    // Группировка и использование агрегата для получения размера группы
    val groupedNames6 = names
        .groupingBy { it.first() }
        .aggregate { _, accumulator: Int?, _, first ->
            if (first) 1 else accumulator!! + 1
        }

    println(groupedNames6) // {J=4, M=2, P=2}

    // Группировка и использование агрегатов, возврат четного или нечетного размера в зависимости от размера группы.
    val groupedNames7 = names
        .groupingBy { it.first() }
        .aggregate { _, accumulator: Boolean?, element, first ->
            if (first) {
                element.length % 2 == 0
            } else {
                accumulator!! && element.length % 2 == 0
            }
        }

    println(groupedNames7) // {J=true, M=true, P=false}
}

/**
 * Особый тип хранения элементов - последовательности.
 *
 * Последовательность — это контейнер, в котором объекты не содержатся, а создаются во время итерации.
 *
 * Что это значит? Эти объекты не обрабатываются до тех пор, пока не придёт время их использовать: то есть последовательности выполняются лениво. Мы не получим промежуточный результат в конце каждого шага.
 *
 * Последовательности предлагают те же функции, что и Iterable: мы можем фильтровать, сопоставлять или упорядочивать элементы, но внутри они работают по-другому и имеют большой потенциал для коллекций с большим количеством элементов или тех, которые требуют большого количества объединенных операций.
 *
 * При использовании Iterable каждый шаг обработки завершается и возвращает свой результат (промежуточную коллекцию), и каждый шаг завершается для всей коллекции перед переходом к следующему шагу. При использовании последовательности результат вычисляется только тогда, когда запрашивается результат всей цепочки обработки, и все этапы обработки выполняются один за другим для каждого отдельного элемента.
 *
 * Это дает нам два преимущества:
 *
 * - Последовательности могут быть бесконечными: мы можем определить последовательность по начальному значению и операции (например), и эта последовательность будет иметь бесконечные значения.
 *
 * - Они позволяют избежать промежуточных шагов: в отличие от других коллекций, когда последовательность выполняет несколько операций (фильтрация, трансформация и т.д.), они применяются цепочкой к объектам один за другим, без необходимости создания новой коллекции на каждом этапе
 *
 * Операции с последовательностями можно разделить на две категории:
 *
 * - Без сохранения состояния (Stateless): эти операции не требуют состояния и обрабатывают каждый элемент независимо; им может потребоваться небольшое постоянное количество состояний для обработки элемента.
 * Некоторые методы без сохранения состояния — это map(), filter(), take() или drop().
 *
 * - С сохранением состояния (Stateful): эти операции требуют большого количества состояний, обычно пропорционального количеству элементов в последовательности.
 * Некоторые методы с сохранением состояния — это sorted(), distinct() или chunked().
 *
 * Также у нас есть два типа операций, в зависимости от того, генерируют ли они новую последовательность или уже нужно вычислить результат:
 *
 * - Промежуточный (Intermediate): когда мы применяем операцию, возвращаемый результат представляет собой другую последовательность, поэтому вам пока не нужно вычислять результат на основе значений последовательности, которая создаётся лениво.
 * Например, map() или filter().
 *
 * - Терминал (Terminal): ему нужны значения потока, поэтому он будет обрабатывать весь поток, чтобы получить результат.
 * Например, toList() (возвращает определённый список) или sum() (вычисляет сумму значений в последовательности).
 */
fun sequences() {
    println("--- Creating sequences ---")

    /**
     * Мы можем использовать функцию sequenceOf() для получения последовательности из элементов, переданных в качестве аргументов:
     */
    val sequenceOfStrings1 = sequenceOf("one", "two", "three", "four")
    val sequenceOfInts1 = sequenceOf(1, 2, 3, 4)

    println(sequenceOfStrings1.toList()) // [one, two, three, four]
    println(sequenceOfInts1.toList()) // [1, 2, 3, 4]

    /**
     * Используя функцию asSequence(), мы можем преобразовать любую коллекцию в последовательность:
     */
    val listOfStrings = listOf("one", "two", "three", "four")
    val listOfInts = listOf(1, 2, 3, 4)

    val sequenceOfStrings2 = listOfStrings.asSequence()
    val sequenceOfInts2 = listOfInts.asSequence()

    println(sequenceOfStrings2.toList()) // [one, two, three, four]
    println(sequenceOfInts2.toList()) // [1, 2, 3, 4]

    /**
     * Мы можем использовать функцию generateSequence(), которая получает первое значение (начальное значение) и функцию, применяемую к этому значению, и создаёт бесконечную последовательность.
     */
    val sequenceOfEvenNumbers = generateSequence(1) { it + 1 }
        .filter { it % 2 == 0 }
        .take(5)

    println(sequenceOfEvenNumbers.toList()) // [2, 4, 6, 8, 10]

    /**
     * Используя метод sequence(), мы можем создавать элементы последовательности один за другим. Благодаря методам yield() и yieldAll() мы можем вернуть один или несколько элементов (Iterable) и приостановить выполнение (подождать, пока эти элементы будут израсходованы).
     */
    val evenNumbersSequence = sequence {
        yield(2) // Возвращает 2 и приостанавливает функцию
        yieldAll(listOf(4, 6)) // Возвращает 4 и 6 и приостанавливает функцию
        yieldAll(generateSequence(8) { it + 2 }) // Генерирует бесконечную последовательность чётных чисел, начиная с 8
    }

    println(evenNumbersSequence.take(0).toList()) // []
    println(evenNumbersSequence.take(1).toList()) // [2]
    println(evenNumbersSequence.take(2).toList()) // [2, 4]
    println(evenNumbersSequence.take(3).toList()) // [2, 4, 6]
    println(evenNumbersSequence.take(4).toList()) // [2, 4, 6, 8]
    println(evenNumbersSequence.take(5).toList()) // [2, 4, 6, 8, 10]
    println(evenNumbersSequence.take(13).toList()) // [2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26]

    println("--- Sequence operations ---")

    /**
     * Когда мы работаем с итерируемыми объектами или последовательностями, важно определить, работаем ли мы лениво (lazy) или усердно (eager).
     *
     * Усердно (eager)
     *
     * Когда мы работаем с Iterables, мы выполняем все операции над всеми элементами. То есть мы работаем активно (усердно). Это означает, что каждая операция выполняется над каждым из элементов, даже если в дальнейшем они не понадобятся.
     *
     * На каждом этапе создаётся новая коллекция. Мы используем это при работе с небольшими списками или когда у нас не так много связанных операций.
     *
     * В примере ниже мы отфильтровали все элементы, чтобы получить только четные числа, и получили новую коллекцию с отфильтрованными элементами. Затем мы отображаем новую коллекцию, умножая каждый элемент на 2. Наконец, мы берем первые три числа.
     *
     * Таким образом, всего мы выполнили 10 операций filter, 5 операций map и 3 take. То есть всего 18 операций.
     */
    val withIterator = (1..10)
        // Отфильтровать нечётные числа
        .filter {
            print("Filter: $it, ")

            it % 2 == 0
        }
        // Умножить оставшиеся числа на 2
        .map {
            print("Mapping: $it, ")

            it * 2
        }
        .take(3) // Взять первые 3 цифры

    println()

    // Filter: 1, Filter: 2, Filter: 3, Filter: 4, Filter: 5, Filter: 6, Filter: 7, Filter: 8, Filter: 9, Filter: 10,
    // Mapping: 2, Mapping: 4, Mapping: 6, Mapping: 8, Mapping: 10,
    // Take: 4, Take: 8, Take: 12,
    println(withIterator) // [4, 8, 12]

    /**
     * Лениво (lazy)
     *
     * Когда мы работаем с последовательностями, методы вызываются только при построении списка результатов. То есть мы работаем лениво. Каждую операцию мы выполняем после вызова терминальной операции.
     *
     * Обрабатывается вся цепочка операций над каждым элементом. Никаких промежуточных коллекций не требуется. Это подходит для коллекций со многими элементами или для случаев, когда мы выполняем множество связанных операций.
     *
     * В примере ниже мы берём элементы поэлементно и выполняем все операции (filter, map, и take) над каждым из них одну за другой.
     *
     * Таким образом, мы выполнили всего 6 операций filter, 3 операции map и 3 операции take. То есть всего 12 операций.
     */
    val withSequence = (1..10)
        .asSequence()
        // Отфильтровать нечётные числа
        .filter {
            print("Filter: $it, ")

            it % 2 == 0
        }
        // Умножить оставшиеся числа на 2
        .map {
            print("Mapping: $it, ")

            it * 2
        }
        .take(3) // Взять первые 3 цифры
        .toList() // Конвертировать последовательность в список

    println()

    // Filter: 1,
    // Filter: 2, Mapping: 2, Take: 4
    // Filter: 3,
    // Filter: 4, Mapping: 4, Take: 8
    // Filter: 5,
    // Filter: 6, Mapping: 6, Take: 12
    println(withSequence) // [4, 8, 12]
}

/**
 * Comparable и Comparator — это два интерфейса, которые позволяют разработчикам сравнивать объекты одного и того же или разных классов.
 *
 * Оба интерфейса обычно используются для сортировки и упорядочивания коллекций объектов на основе одного или нескольких атрибутов.
 *
 * Comparable
 *
 * Интерфейс Comparable используется для определения естественного порядка объектов.
 *
 * Когда класс реализует Comparable, он должен переопределить метод compareTo()
 *
 * Этот метод принимает объект того же типа, что и аргумент, и возвращает целочисленное значение. Это целочисленное значение указывает, является ли объект меньше (возвращает –1), равен (возвращает 0) или больше (возвращает 1) другого сравниваемого объекта.
 *
 * Класс, реализующий интерфейс Comparable, поддерживает полезные функции расширения, в том числе следующие:
 *
 * - coerceAtLeast() – эта функция проверяет, превышает ли вызывающий объект определённый минимальный объект. Он возвращает текущий объект, если он больше; в противном случае он возвращает минимальный объект
 *
 * - coerceAtMost() – эта функция проверяет, меньше ли вызывающий объект заданного максимального объекта. Он возвращает текущий объект, если он меньше; в противном случае он возвращает максимальный объект.
 *
 * - coerceIn() – эта функция проверяет, находится ли вызывающий объект в определённом диапазоне между минимальным и максимальным значениями. Он возвращает объект, если он находится в пределах диапазона, минимум, если объект меньше минимума, или максимум, если объект больше максимума.
 *
 * Comparator
 *
 * Интерфейс Comparator используется для определения пользовательского порядка объектов.
 *
 * Когда класс реализует Comparator, он должен переопределить метод compare(), который принимает в качестве аргументов два объекта одного типа и возвращает целочисленное значение. Он возвращает ноль, если аргументы равны, отрицательное число, если первый аргумент меньше второго, или положительное число, если первый аргумент больше второго.
 *
 * Интерфейс Comparator имеет интересные методы, среди них следующие:
 *
 * - reversed() – эта функция принимает компаратор в качестве аргумента и возвращает компаратор с обратным порядком переданного компаратора
 *
 * Основное различие между Comparable и Comparator заключается в том, что Comparable определяет естественный порядок объектов, а Comparator определяет пользовательский порядок объектов. При сравнении объектов одного и того же класса рекомендуется реализовать Comparable, чтобы обеспечить для них естественный порядок. Когда сравниваются объекты разных классов или требуется индивидуальный порядок, рекомендуется реализовать Comparator.
 */
fun comparableAndComparator() {
    println("-- Comparable interface --")

    // Переопределение метода compareTo()
    // Метод compareTo() сравнивает объекты Person по их возрасту, возвращая отрицательное целое число, если возраст текущего объекта меньше возраста объекта other, и положительное целое число, если возраст текущего объекта больше возраста объекта other и ноль, если они равны.
    data class Person(val name: String, val age: Int) : Comparable<Person> {
        override fun compareTo(other: Person): Int = this.age - other.age
    }

    // Теперь, если у нас есть список объектов Person, мы можем отсортировать его по возрасту
    val people = listOf(
        Person("Alice", 25),
        Person("Bob", 30),
        Person("Charlie", 20)
    )

    // В этом коде функция sorted() использует естественный порядок, определённый методом compareTo(), для сортировки списка объектов Person
    val sortedPeople = people.sorted()

    /// [Person(name=Charlie, age=20), Person(name=Alice, age=25), Person(name=Bob, age=30)]
    println(sortedPeople)

    // coerceAtLeast()
    val minimum = Person("Jack", 28)

    println(people[0].age.coerceAtLeast(minimum.age)) // 28
    println(people[1].age.coerceAtLeast(minimum.age)) // 30

    // coerceAtMost()
    val maximum = Person("Jack", 28)

    println(people[0].age.coerceAtMost(maximum.age)) // 25
    println(people[1].age.coerceAtMost(maximum.age)) // 28

    // coerceIn()
    println(25.coerceIn(18..28)) // 25
    println(15.coerceIn(18..28)) // 18
    println(30.coerceIn(18..28)) // 28

    println("-- Comparator interface --")

    /**
     * В этом примере у нас есть класс данных AnyPerson и класс PersonAgeComparator, который реализует интерфейс Comparator<AnyPerson>.
     *
     * Метод compare() принимает в качестве аргументов два объекта AnyPerson и сравнивает их по возрасту.
     *
     * Если возраст p1 меньше возраста p2, возвращается отрицательное число. Если возраст p1 больше возраста p2, возвращается положительное число. Если возрасты равны, возвращается ноль.
     */
    data class AnyPerson(val name: String, val age: Int)

    val people2 = listOf(
        AnyPerson("Alice", 25),
        AnyPerson("Bob", 30),
        AnyPerson("Charlie", 20)
    )

    // Экземпляр интерфейса Comparator, который сравнивает объекты AnyPerson по их возрасту.
    val ageComparator = Comparator<AnyPerson> { p1, p2 -> p2.age - p1.age }

    // Функция sortedWith() сортирует список объектов AnyPerson, используя пользовательский порядок, определённый ageComparator.
    val sortedPeopleWithComparator = people2.sortedWith(ageComparator)

    /// [AnyPerson(name=Bob, age=30), AnyPerson(name=Alice, age=25), AnyPerson(name=Charlie, age=20)]
    println(sortedPeopleWithComparator)

    // reversed()
    val sortedPeople2 = people2.sortedWith(ageComparator.reversed())

    /// [AnyPerson(name=Charlie, age=20), AnyPerson(name=Alice, age=25), AnyPerson(name=Bob, age=30)]
    println(sortedPeople2)

    val people3 = listOf(
        AnyPerson("Alice", 25),
        AnyPerson("Bob", 25),
        AnyPerson("Bob", 30),
        AnyPerson("Charlie", 30),
        AnyPerson("Charlie", 20),
        AnyPerson("Alice", 25)
    )

    val sortedList = people3.sortedWith(compareBy(AnyPerson::age, AnyPerson::name))

    /// [AnyPerson(name=Charlie, age=20), AnyPerson(name=Alice, age=25), AnyPerson(name=Alice, age=25), AnyPerson(name=Bob, age=25), AnyPerson(name=Bob, age=30), AnyPerson(name=Charlie, age=30)]
    println(sortedList)

    val sortByName = Comparator<AnyPerson> { p1, p2 -> p1.name.compareTo(p2.name) }

    val sortedPeople3 = people3.sortedWith(sortByName)

    /// [AnyPerson(name=Alice, age=25), AnyPerson(name=Alice, age=25), AnyPerson(name=Bob, age=25), AnyPerson(name=Bob, age=30), AnyPerson(name=Charlie, age=30), AnyPerson(name=Charlie, age=20)]
    println(sortedPeople3)
}

/**
 * `slice()` — возвращает новую коллекцию элементов по указанным индексам
 */
fun slice() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // Получить элементы от 1 до 5
    println(list.slice(0..4)) // [1, 2, 3, 4, 5]
    println(list.slice(0..5 step 2)) // [1, 3, 5]

    // Получить элементы от 5 до 1, используя диапазон и шаг по убыванию
    println(list.slice(4 downTo 0 step 2)) // [5, 3, 1]

    // получить элементы от 1 до 5, используя только индексы
    println(list.slice(listOf(0, 2, 4))) // [1, 3, 5]

    println(list) // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
}

/**
 * Часто вам нужно получить элементы коллекции, не используя их индексы.
 *
 * Вы можете указать, сколько элементов нужно вернуть из коллекции, используя семейство методов take:
 * - take() - возвращает коллекцию из n элементов из исходной коллекции, начиная с первого элемента. Если вызывается с числом, превышающим размер коллекции, возвращается вся коллекция.
 *
 * - takeLast() - возвращает коллекцию из n элементов из исходной коллекции, начиная с последнего элемента. Если вызывается с числом, превышающим размер коллекции, возвращается вся коллекция.
 *
 * - takeWhile() - возвращает коллекцию с элементами, удовлетворяющими предикату, начиная с первого элемента. Если ни один из элементов не соответствует предикату, результатом будет пустая коллекция.
 *
 * - takeWhileLast() - возвращает коллекцию с элементами, удовлетворяющими предикату, начиная с последнего элемента. Если ни один из элементов не соответствует предикату, результатом будет пустая коллекция.
 */
fun take() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    println(list.take(5)) // [1, 2, 3, 4, 5]
    println(list.takeLast(5)) // [6, 7, 8, 9, 10]
    println(list.takeWhile { it < 5 }) // [1, 2, 3, 4]
    println(list.takeWhile { it > 5 }) // []
    println(list.takeLastWhile { it > 5 }) // [6, 7, 8, 9, 10]
    println(list.takeLastWhile { it < 5 }) // []

    println(list) // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
}

/**
 * Иногда нам нужно взять из коллекции только определенное количество элементов. Для этой задачи пригодятся «drop» методы:
 *
 * - drop() - возвращает коллекцию, содержащую все элементы, кроме первых n элементов. Если вызывается с числом, превышающим размер коллекции, возвращается пустой список.
 *
 * - dropLast() - возвращает коллекцию, содержащую все элементы, кроме последних n элементов. Если вызывается с числом, превышающим размер коллекции, возвращается пустой список.
 *
 * - dropWhile() - возвращает коллекцию, содержащую все элементы, кроме первых элементов, удовлетворяющих заданному предикату. Если ни один элемент не соответствует предикату, результатом является исходная коллекция; если все элементы соответствуют предикату, возвращается пустой список.
 *
 * - dropLastWhile() - возвращает коллекцию, содержащую все элементы, кроме последних элементов, удовлетворяющих заданному предикату. Если ни один элемент не соответствует предикату, результатом является исходная коллекция; если все элементы соответствуют предикату, возвращается пустой список
 */
fun drop() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    println(list.drop(5)) // [6, 7, 8, 9, 10]
    println(list.dropLast(5)) // [1, 2, 3, 4, 5]
    println(list.dropWhile { it < 5 }) // [5, 6, 7, 8, 9, 10]
    println(list.dropWhile { it > 5 }) // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    println(list.dropLastWhile { it > 5 }) // [1, 2, 3, 4, 5]
    println(list.dropLastWhile { it < 5 }) // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

    println(list) // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
}

/**
 * Довольно часто нам приходится разбивать коллекцию на части фиксированного размера. Это то, что делает функция chunked().
 *
 * Эта функция принимает размер чанка в качестве аргумента. Он возвращает список списков элементов — частей исходной коллекции, и каждый список имеет размер, определённый аргументом. Если последний список имеет меньший размер, он возвращается как есть.
 *
 * Также вы можете применить преобразования к полученным чанкам, передав в качестве второго аргумента лямбду, определяющую такое преобразование.
 */
fun chunked() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    println(list.chunked(3)) // [[1, 2, 3], [4, 5, 6], [7, 8, 9], [10]]
    println(list.chunked(3) { it.sum() }) // [6, 15, 24, 10]
    println(list.chunked(3) { it.joinToString() }) // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

    println(list) // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
}

/**
 * Вы также можете получить все возможные диапазоны элементов коллекции определенного размера. Это расширенная версия предыдущей функции. Благодаря функции windowed() вы получаете гораздо большую гибкость, чем с chunked(), и можете работать с «окнами» или диапазонами в соответствии с вашими потребностями.
 *
 * Мы используем следующие параметры:
 *
 * - size - это количество элементов в каждом окне, то есть размер окна
 *
 * - step - это количество элементов, которые нам нужно прокручивать между окнами, по умолчанию оно равно 1. Оно представляет собой расстояние между первыми элементами последовательных окон.
 *
 * - partialWindows - он включает в конец коллекции окна меньших размеров, если вы не можете построить окно указанного размера; по умолчанию это ложь. Он указывает, хотите ли вы сохранить частичные окна в конце.
 *
 * С помощью windowed() вы также можете применять преобразования к полученным окнам, передавая в качестве второго аргумента лямбду, определяющую такое преобразование.
 */
fun windowed() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // [[1, 2, 3], [2, 3, 4], [3, 4, 5], [4, 5, 6], [5, 6, 7], [6, 7, 8], [7, 8, 9], [8, 9, 10]]
    println(list.windowed(3))

    // [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
    println(list.windowed(3, 3))

    // [[1, 2, 3], [5, 6, 7]]
    println(list.windowed(3, 4, false))

    // [[1, 2, 3], [5, 6, 7], [9, 10]]
    println(list.windowed(3, 4, true))

    // [6, 18, 19]
    println(list.windowed(3, 4, true) { it.sum() })

    println(list) // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
}

/**
 * Часто вы не знаете, содержит ли ваша коллекция элементы, которые появляются более одного раза. По этой причине очень полезно знать уникальные элементы коллекции.
 *
 * Метод distinct() возвращает список, содержащий только отдельные элементы данной коллекции.
 *
 * Метод distinctBy() возвращает коллекцию, содержащую только элементы с разными ключами, определяемыми в лямбда-функции.
 */
fun distinct() {
    val numberWithRepeated = listOf(1, 2, 3, 4, 5, 4, 4, 3, 1)
    val namesWithRepeated = listOf("Pablo", "John", "Jane", "John", "Jane")

    println(numberWithRepeated.distinct()) // [1, 2, 3, 4, 5]
    println(namesWithRepeated.distinct()) // [Pablo, John, Jane]

    // -> 1 % 2 = 1 -> 2 % 2 = 0 -> 3 % 2 = 1 -> 4 % 2 = 0 -> 5 % 2 = 1
    println(numberWithRepeated.distinctBy { it % 2 }) // [1, 2]

    // -> "Pablo".first() = 'P' -> "John".first() = 'J' -> "Jane".first() = 'J'
    println(namesWithRepeated.distinctBy { it.first() }) // [Pablo, John]
}
