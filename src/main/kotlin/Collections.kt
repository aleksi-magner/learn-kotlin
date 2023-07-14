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
 *
 */
fun mutableSets() {}

/**
 * Map является неизменяемой коллекцией с парами ключ-значение.
 */
fun immutableMaps() {
    val students: Map<String, Int> = mapOf(
        "Zhenya" to 5,
        "Vlad" to 4,
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
     * Мы используем конструкцию to для создания записи на карте. Здесь to — это упрощенная конструкция для создания пары:
     */
    val (nameVlad, gradeVlad) = "Vlad" to 4

    // output: Student name is: Vlad And their grade is: 4
    println("Student name is: $nameVlad And their grade is: $gradeVlad")

    // Если нужно инициализировать пустую карту, emptyMap<K, V>
    val emptyStringToDoubleMap = emptyMap<String, Double>()

    // Создание через buildMap()
    val values = mapOf<String, Int>("Second" to 2, "Third" to 3)

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

    val intMap: Map<Int, Int> = mapOf(1 to 2, 2 to 3, 3 to 4, 4 to 5, 5 to 6)

    println("Map summator: ${summator1(intMap)}") // 8
    println("Map summator: ${summator2(intMap)}") // 8

    val priceList1: Map<String, Int> = mapOf("Cola" to 500, "Apple" to 1500, "Banana" to 300)
    val shoppingList1: MutableList<String> = mutableListOf("Cola", "Apple")

    println("bill: ${bill1(priceList1, shoppingList1)}") // 2000
    println("bill: ${bill2(priceList1, shoppingList1)}") // 2000

    val priceList2: Map<String, Int> = mapOf("Pen" to 1, "Ananas" to 2, "Sheet" to 0)
    val shoppingList2: MutableList<String> = mutableListOf()

    println("bill: ${bill1(priceList2, shoppingList2)}") // 0
    println("bill: ${bill2(priceList2, shoppingList2)}") // 0

    val priceList3: Map<String, Int> = mapOf("Sprite" to 150, "Lays" to 200, "Milk" to 600, "Snickers" to 100)
    val shoppingList3: MutableList<String> = mutableListOf("Sprite", "Lays", "Coffee")

    println("bill: ${bill1(priceList3, shoppingList3)}") // 350
    println("bill: ${bill2(priceList3, shoppingList3)}") // 350
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
        "John" to 500,
        "Mike" to 1000,
        "Lara" to 1300
    )

    staff["Nika"] = 999

    println(staff) // {John=500, Mike=1000, Lara=1300, Nika=999}

    // Преобразование обычной карты в мутабельную
    val mapCarsPerYear: Map<Int, Int> = mapOf(1999 to 30000, 2021 to 202111)
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
    groupOfStudents += mapOf("Anastasia" to 10)
    groupOfStudents += "Alexa" to 3

    val studentsFromOregon = mapOf("Alexa" to 7)

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
