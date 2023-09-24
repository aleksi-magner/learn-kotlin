/**
 * Отладка — это процесс поиска и исправления ошибок в программе.
 *
 * Некоторые ошибки, например те, которые мешают компиляции программы, можно легко исправить, поскольку компилятор или IDE могут сказать вам, что не так.
 *
 * Другие ошибки сложнее и могут потребовать от вас больших усилий для их обнаружения.
 *
 * Логирование (Logging), отладка через «println»
 *
 * Самый простой метод — вставка операторов println для отслеживания значений и порядка выполнения вашей программы. При выполнении они сообщат вам о том, что происходит «под капотом» во время выполнения.
 *
 * Не следует использовать этот метод в реальных проектах, поскольку современные отладчики могут делать то же самое гораздо более удобным способом и потому что вы не сможете делать это везде. Например, если вы хотите получить информацию из кода какой-либо библиотеки, это будет проблемой, поскольку вы не сможете изменять скомпилированный код.
 *
 * Утверждения (Assertions)
 *
 * Утверждение — это механизм, который отслеживает состояние программы, но, в отличие от дополнительных операторов печати, он завершает программу безотказным способом (fail-fast), если что-то идёт не так.
 *
 * Fail-fast — это подход, при котором ошибки, которые в противном случае могли бы быть нефатальными, вызывают немедленный сбой, что делает их видимыми.
 *
 * Вы можете задаться вопросом, почему может потребоваться сбой производственного кода. Это потому, что потерпеть неудачу немедленно гораздо безопаснее, чем совершить неправильный поступок.
 *
 * Вам следует использовать require при проверке аргумента, переданного функции, и check при проверке состояния объекта
 *
 * Инварианты — это ограничения, которые должны соблюдаться для правильной работы программы.
 *
 * В приведенном ниже коде положительный возраст является примером инварианта. Использование отрицательного возраста порождает проблему.
 *
 * Именно для обеспечения соблюдения инвариантов нам нужны утверждения.
 *
 * Мы также можем использовать утверждения для проверки предусловий и постусловий метода, то есть условий, которые должны быть выполнены до или после вызова метода.
 *
 * Подключение отладчика (Attaching a debugger)
 *
 * Отладчик — это инструмент, который мешает нормальному выполнению программы, позволяя вам получать информацию о времени выполнения и тестировать различные сценарии для диагностики ошибок.
 *
 * Это наиболее популярное использование отладчиков.
 *
 * Однако, когда вы приобретете больше опыта работы с ними, вы увидите, что они могут быть полезны в различных ситуациях, не обязательно связанных с ошибками.
 *
 * Современные отладчики предоставляют широкий спектр инструментов, которые можно использовать для диагностики самых сложных сбоев.
 */
fun main() {
    /**
     * Этот код создаёт объект Cat. Все было бы хорошо, если бы не бессмысленное отрицательное значение возраста.
     *
     * Естественно, в более сложной программе это может привести к различным ошибкам. Такой объект можно долго обходить стороной, прежде чем мы увидим проблему, а когда возникает проблема, не всегда очевидно, в чем её причина.
     *
     * Чтобы этого не произошло, мы можем проверить, чтобы параметр «возраст» был положительным, а «имя» не было пустым:
     *
     * Первый параметр — это логическое выражение, которое следует проверить, и когда оно принимает значение false, выдаётся исключение.
     *
     * Второй параметр — это функция, которая генерирует сообщение, описывающее эту ошибку.
     *
     * Вам следует использовать require при проверке аргумента, переданного функции, и check при проверке состояния объекта, как в этом случае.
     */
    class Cat(val name: String, val age: Int) {
        val enoughCat: Boolean = name == "Many"

        init {
            check(!enoughCat) { "You cannot add a new cat" } // IllegalStateException
            require(age >= 0) { "Invalid age: $age" } // IllegalArgumentException
        }
    }

    val cat1 = Cat("Tom", 3)

    println("Cat ${cat1.name}: ${cat1.age}") // Cat Tom: 3

    // Exception in thread "main" java.lang.IllegalArgumentException: Invalid age: -1
    // 	at DebuggingKt$main$Cat.<init>(Debugging.kt:49)
    // 	at DebuggingKt.main(Debugging.kt:61)
    // 	at DebuggingKt.main(Debugging.kt)
    // val cat2 = Cat("Casper", -1)

    // Exception in thread "main" java.lang.IllegalStateException: You cannot add a new cat
    // 	at DebuggingKt$main$Cat.<init>(Debugging.kt:48)
    // 	at DebuggingKt.main(Debugging.kt:63)
    // 	at DebuggingKt.main(Debugging.kt)
    // val cat3 = Cat("Many", 0)
}
