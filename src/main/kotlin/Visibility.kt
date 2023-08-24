/**
 * Видимость определяет, какие компоненты кода доступны из других частей кода.
 */

/**
 * Классы
 *
 * Видимость класса определяется 4 модификаторами видимости, которые указывают, какой код может получить доступ к классу:
 * - public - Класс доступен из любого кода.
 * - internal - Класс доступен только внутри модуля.
 * - protected - Класс доступен только внутри класса и его подклассов.
 * - private - Класс доступен только внутри файла, в котором он объявлен.
 */
// public class PublicClass
// internal class InternalClass
// protected class ProtectedClass // Ошибка компиляции, так как защищенные классы не могут быть верхнего уровня
// private class PrivateClass

/**
 * Пакеты
 *
 * Пакеты в Kotlin служат для группировки связанных классов, объектов и функций. Видимость внутри пакетов определяется модификаторами видимости, подобными тем, которые используются для классов.
 */
// In the file mypackage/MyClass.kt
// package mypackage

// public class MyClass
// internal class MyInternalClass

/**
 * Модуль
 *
 * Модуль представляет собой набор исходных файлов, скомпилированных вместе.
 *
 * В Kotlin модуль может быть проектом IntelliJ IDEA, проектом Gradle или Maven или другой единицей компиляции. Видимость модуля регулируется модификатором internal.
 *
 * Модификатор internal используется для указания того, что определенный элемент кода доступен только в пределах одного модуля. Это полезно, когда вы хотите ограничить доступ к частям кода, которые должны быть доступны только внутри модуля и не должны быть видны за его пределами.
 */
// In the file mymodule/MyInternalClass.kt
// package mymodule

// internal class MyInternalClass {
//     val value = 42
// }

// В другом файле в том же модуле
// import mymodule.MyInternalClass

// fun main() {
//     val instance = MyInternalClass() // Разрешено, так как MyInternalClass является частью того же модуля
//
//     println(instance.value)
// }
