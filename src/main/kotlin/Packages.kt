/**
 * В Kotlin наши проекты обычно структурированы в пакеты. Пакеты группируют классы, функции и/или переменные в соответствии с конкретным вариантом использования или функциональностью.
 *
 * Чтобы определить пакет, мы используем заголовок package.
 *
 * Файл может либо принадлежать пакету, либо нет, и он может не использовать ни один, один или несколько пакетов.
 *
 * Пакет содержит один или несколько файлов Kotlin, причем файлы связаны с пакетом с использованием одного и того же заголовка пакета.
 *
 * Один файл или класс может использовать ноль или несколько пакетов. Если пакет не указан, содержимое такого файла принадлежит пакету по умолчанию без имени.
 *
 * Пакеты Kotlin не требуют, чтобы файлы находились в каком-либо определенном месте, связь между файлом и его пакетом устанавливается только через заголовок package.
 *
 * Если мы хотим использовать код, сгруппированный или инкапсулированный внутри пакета, мы должны его импортировать.
 *
 * Мы можем импортировать либо весь пакет, либо отдельные элементы из этого пакета.
 *
 * Чтобы импортировать пакет, нам нужно использовать директиву import.
 *
 * Ряд пакетов, которые по умолчанию импортируются в каждый файл Kotlin:
 * - kotlin.*
 * - kotlin.annotation.*
 * - kotlin.collections.*
 * - kotlin.comparisons.*
 * - kotlin.io.*
 * - kotlin.ranges.*
 * - kotlin.sequences.*
 * - kotlin.text.*
 *
 * Некоторые пакеты импортируются в зависимости от целевой платформы:
 *
 * JVM:
 * - java.lang.*
 * - kotlin.jvm.*
 *
 * JS:
 * - kotlin.js.*
 *
 * Иногда нам может понадобиться импортировать два элемента с одинаковыми именами, но принадлежащими к разным пакетам, или импортировать один и тот же класс и использовать его с двумя разными именами.
 *
 * Первый вариант решения такой задачи — использовать в качестве идентификатора полное имя пакета и имя элемента. Однако этот вариант может быть длительным и иногда непродуктивным.
 *
 * Мы можем решить эту ситуацию, используя псевдоним для наших импортированных классов или функций. Мы делаем это с помощью модификатора as.
 *
 * Таким образом, мы можем создать псевдоним для любых коллизий, которые у нас есть, и работать с нашим кодом намного проще.
 */

/**
 * В следующем примере вы можете увидеть, как мы определяем пакет. Все элементы в этом пакете, независимо от того, находятся ли они в одном файле или нет (классы и функции), инкапсулируются в этом пакете.
 *
 * Чтобы иметь возможность их использовать, мы должны указать полное имя элемента, которое включает в себя имя пакета.
 */
package testPackage

data class Person1(val name: String, val age: Int)

fun sayHello() {
    println("Hello")
}


/**
 * В следующем примере мы импортируем весь пакет allpackage, используя модификатор *.
 * С другой стороны, мы импортируем только класс Person из пакета oneclass.
 */
// import allpackage.*
// import oneclass.Person

/**
 * Псевдонимы для импорта с одним и тем же именем
 */
// import packageone.Person
// import packagetwo.Person as PersonAdvanced

fun main() {
    val person1 = testPackage.Person1("John", 30)

    println(person1) // Person(name=John, age=30)

    testPackage.sayHello() // Hello
}
