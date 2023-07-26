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

    orderingElementsInCollection()
    retrieveSingleElement()
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

    counting()
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
fun counting() {
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
