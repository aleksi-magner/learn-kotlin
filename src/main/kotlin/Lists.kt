fun main() {
    mutableLists()
}

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
    val listOfNumbers = mutableListOf(1, 2, 3, 4, 5)
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
