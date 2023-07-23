const val STRING_CONSTANT = "This is a constant string"
const val NUMBER_CONSTANT = 2
const val DOUBLE_CONSTANT = 3.14

fun main() {
    val number1: Int
    val number2 = 5

    println("String constant: $STRING_CONSTANT")
    println("Number constant: $NUMBER_CONSTANT")
    println("Double constant: $DOUBLE_CONSTANT")

    number1 = 35 + number2 + NUMBER_CONSTANT

    println("Number value: $number1")

    // List creation
    val myMutableList = mutableListOf(1, 2, 3, 4, 5)

    // Adding a new element
    myMutableList.add(6)

    println(myMutableList)

    var variable = "Any variable"

    variable += " add string"

    println("Variable: $variable")

    lazyInitialization()
}

/**
 * Ленивая инициализация позволяет нам создать объект именно в тот момент, когда происходит первая ссылка на него.
 *
 * Это означает, что нам не нужно тратить время на немедленное использование ресурсов нашей программы для выделения памяти объектам, которые не требуются с самого начала.
 *
 * Если мы позволим объектам инициализироваться, когда они нужны, мы сможем сэкономить много времени, перераспределив выделение ресурсов в течение жизненного цикла нашей программы.
 *
 * В Kotlin есть специальная функция lazy(), которая принимает лямбду. Первый вызов выполняет эту лямбду и запоминает результат. Последующие вызовы просто возвращают это значение.
 */
fun lazyInitialization() {
    /**
     * При использовании ленивой инициализации в Kotlin вы должны объявлять переменные с помощью ключевого слова val, потому что значение может быть инициализировано только один раз.
     */
    val a: String by lazy {
        print("Variable a is initialized. ")

        "I love Hyperskill!"
    }

    println("Initializing a! ") // Initializing a!

    println(a) // Variable a is initialized. I love Hyperskill!
    println(a) // I love Hyperskill!

    val c: String by lazy {
        print("a")
        "c"
    }

    val b: String by lazy {
        print("b")
        c
    }

    println(b) // bac

    /**
     * Проблемы с синхронизацией
     *
     * Если ваши программы используют преимущества многопоточности, вам нужно знать о параметре режима функции lazy().
     *
     * - LazyThreadSafetyMode.SYNCHRONIZED означает, что значение вычисляется только в одном потоке, и все потоки получат одно и то же значение. Это опция по умолчанию, поэтому вы можете опустить ее, если хотите.
     *
     * val a: String by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {}
     *
     * - LazyThreadSafetyMode.PUBLICATION указывает, что лямбда-выражение может вызываться несколько раз с неинициализированным ленивым значением объекта, но будет использоваться значение, возвращенное первым.
     *
     * val a: String by lazy(LazyThreadSafetyMode.PUBLICATION) {}
     *
     * - LazyThreadSafetyMode.NONE означает, что синхронизации вообще нет, поэтому, если мы вызываем переменную из разных потоков, ее значение не может быть однозначно определено. Использование этого параметра не рекомендуется, если ваша программа позволяет в первый раз вызывать ленивый объект из более чем одного потока.
     *
     * val a: String by lazy(LazyThreadSafetyMode.None) {}
     */

    /**
     * Ещё одна функция отложенной инициализации в Kotlin, о которой стоит упомянуть, — lateinit. Это ключевое слово, а не функция, такая как lazy().
     *
     * Обычно, если значение поля класса не равно null, мы должны либо немедленно инициализировать свойство, либо сделать это в конструкторе. Но часто бывают ситуации, когда во время создания экземпляра класса мы не можем инициализировать свойство и не хотим делать его обнуляемым.
     *
     * Переменные lateinit должны быть объявлены с помощью var, в отличие от объявленных с помощью lazy().
     */
    class LateInitExample {
        lateinit var a: String

        fun initA(a: String) {
            this.a = a
        }

        fun doSmth() {
            if (::a.isInitialized) {
                println("a is Initialized")
            } else {
                println("a isn't Initialized")
            }
        }
    }

   val lateExample = LateInitExample()

    // println(lateExample.a) // UninitializedPropertyAccessException

    lateExample.doSmth() // a isn't Initialized

    lateExample.initA("Any A")

    println(lateExample.a) // Any A

    lateExample.doSmth() // a is Initialized

    /**
     * При первом использовании свойства name должна быть напечатана строка «I prefer to ignore it», и должна быть вызвана функция callName() для установки свойства имени.
     *
     * Функция callName() должна вывести строку «Input the cat name», прочитать ответ пользователя и вернуть его.
     */
    class Cat {
        val name: String by lazy {
            println("I prefer to ignore it")

            this.callName()
        }

        fun callName(): String {
            println("Input the cat name")

            return readln()
        }
    }

    val myCat = Cat()

    /**
     * I prefer to ignore it
     * Input the cat name
     * kitty
     */
    println(myCat.name)
}
