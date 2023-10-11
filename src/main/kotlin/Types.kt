import java.awt.print.Book
import java.nio.file.Watchable

fun main() {
    /**
     * Беззнаковые числа создаются так же, как и любые другие. Для того, чтобы указать, что вы создаете беззнаковый номер, вам нужно добавить к нему суффикс «u» или «U».
     */
    // Integer numbers
    val byte: Byte = 127 // 1 байт, -128..127
    val uByte: UByte = 255U // 1 байт, 0..255
    val short: Short = 32767 // 2 байта, -32768..32767
    val uShort: UShort = 65535U // 2 байта, 0..65535
    val int: Int = 2_147_483_647 // 4 байта, -2147483648..2147483647
    val uInt: UInt = 4_294_967_295U // 4 байта, 0..4294967295
    val long: Long = 9_223_372_036_854_775_807 // 8 байт, -9223372036854775807..9223372036854775807
    val longPostfix = 2_000_000L
    val uLong: ULong = 18_446_744_073_709_551_615U // 8 байт, 0..18446744073709551615
    val uLongPostfix = 2_000_000UL

    // Floating-point types
    val float: Float = 2.1234567F // 4 байта, 6-7 цифр после запятой
    val floatPostfix = 1.51F
    val double: Double = 3.1415 // 8 байт, 14-16 цифр после запятой

    /**
     * Any? - супер тип Any, допускающий null
     * │
     * └───Any - супер тип всех типов
     * │   │
     * │   └───Number - супер тип чисел
     * │   │   │
     * │   │   └───Byte
     * │   │   └───UByte
     * │   │   └───Short
     * │   │   └───UShort
     * │   │   └───Int
     * │   │   └───UInt
     * │   │   └───Long
     * │   │   └───ULong
     * │   │   └───Float
     * │   │   └───Double
     * │   │
     * │   └───Unit - Ничего не возвращает. Аналог void или undefined
     * │
     * └───Nothing? - не имеет экземпляров. Любой код, следующий за выражением этого типа, недоступен.
     *     │
     *     └───Nothing
     */

    // Min and max values
    println("Byte: ${Byte.MIN_VALUE}..${Byte.MAX_VALUE}")
    println("U Byte: ${UByte.MIN_VALUE}..${UByte.MAX_VALUE}")
    println("Short: ${Short.MIN_VALUE}..${Short.MAX_VALUE}")
    println("U Short: ${UShort.MIN_VALUE}..${UShort.MAX_VALUE}")
    println("Int: ${Int.MIN_VALUE}..${Int.MAX_VALUE}")
    println("U Int: ${UInt.MIN_VALUE}..${UInt.MAX_VALUE}")
    println("Long: ${Long.MIN_VALUE}..${Long.MAX_VALUE}")
    println("U Long: ${ULong.MIN_VALUE}..${ULong.MAX_VALUE}")
    println("Float: ${Float.MIN_VALUE}..${Float.MAX_VALUE}")
    println("Double: ${Double.MIN_VALUE}..${Double.MAX_VALUE}")

    println("Variable type: ${floatPostfix::class.simpleName}")

    // Size of an integer type in bytes or bits
    println(Int.SIZE_BYTES) // 4
    println(Int.SIZE_BITS)  // 32

    // Characters, 2 байта
    val lowerCaseLetter: Char = 'ж'
    val upperCaseLetter = 'Q'
    val number = '1'
    val space = ' '
    val dollar = '$'

    // Booleans, 1 бит
    val enabled: Boolean = true
    val bugFound = false

    // Strings
    val text: String = "Hello, I am studying Kotlin now."
    val creditCardNumber = "1234 5678 9012 3456"

    typeConversion()
    typeCastAndSmartCast()
    generics()
    typeBounds()
    typeAliases()
    variance()

    convertingAnObjectList()
}

// Не возвращает управление, выполнение кода останавливается. Поэтому тип Nothing
fun fail(): Nothing {
    throw Exception("Fail!")
}

/**
 * Лучше не использовать преобразования типов.
 * По крайней мере из большего в меньшее и за пределами диапазонов.
 * Могут быть непредвиденные и не корректные значения
 */
fun typeConversion() {
    println("--- Conversion between numeric types ---")

    val byte: Byte = 127
    val short: Short = byte.toShort()
    val int: Int = byte.toInt() - 2
    val truncatedInt: Byte = (int + 6000).toByte() // -19 WTF?
    val long: Long = byte.toLong()
    val float: Float = byte.toFloat()
    val double: Double = byte.toDouble() + 0.8
    val doubleToInt: Int = double.toInt()
    val char: Char = int.toChar() // '}'
    val charToNumber: Int = char.code // 125

    println(byte)
    println(short)
    println(int)
    println(truncatedInt)
    println(long)
    println(float)
    println(double)
    println(doubleToInt)
    println(char)
    println(charToNumber)

    println("--- Conversion to Short and Byte types ---")

    val floatNumber = 10f
    val doubleNumber = 1.0

    // Correct way
    val shortNumber = floatNumber.toInt().toShort()
    val byteNumber = doubleNumber.toInt().toByte()

    println(shortNumber)
    println(byteNumber)

    println("--- String conversion ---")

    val n: Int = 8
    val d: Double = 10.09
    val c: Char = '@'
    val b: Boolean = true

    val intToString: String = n.toString()
    val stringToInt: Int = intToString.toInt()
    val doubleToString: String = d.toString()
    val stringToDouble: Double = doubleToString.toDouble()
    val charToString: String = c.toString()
    val booleanToString: String = b.toString()
    val stringToBoolean: Boolean = booleanToString.toBoolean()

    println(intToString) // "8"
    println(stringToInt) // 8
    println(doubleToString) // "10.09"
    println(stringToDouble) // 10.09
    println(charToString) // "@"
    println(booleanToString) // "true"
    println(stringToBoolean) // true
    println("false".toBoolean()) // false
    println("False".toBoolean()) // false
    println("FALSE".toBoolean()) // false
    println("tru".toBoolean()) // false
    println("true".toBoolean()) // "true"
    println("True".toBoolean()) // "true"
    println("TRUE".toBoolean()) // "true"

    demonstration()
}

fun demonstration() {
    val something = "1000.0123456789"

    val d = something.toDouble()
    val f = d.toFloat()
    val i = f.toInt()
    val b = i.toByte()

    println("To double: $d") // 1000.0123456789 - ok
    println("To float: $f") // 1000.0123 - потеря точности
    println("To int: $i") // 1000 - отбрасывание дробной части
    println("To byte: $b") // -24 - переполнение типа, непредвиденное значение
    println("To boolean: ${something.toBoolean()}") // false - значение не равно "true" (регистр не важен)
}

/**
 * Функция принимает аргумент типа Any, что означает, что она может принимать любой тип объекта.
 *
 * Внутри функции мы используем when с is для проверки типа входного объекта
 */
fun processInput(input: Any) {
    when (input) {
        is Int -> println("Input is an integer")
        is String -> println("Input is a string")
        is Double -> println("Input is a double")
        else -> println("Unknown input")
    }
}

/**
 * Чтобы проверить, является ли объект экземпляром определённого параметра типа, мы можем использовать оператор is с параметром типа в угловых скобках.
 */
inline fun <reified T> exampleFunction1(obj: Any) {
    if (obj is T) {
        println("obj is an instance of type parameter T")
    } else {
        println("obj is not an instance of type parameter T")
    }
}

// Фильтрация списка по типам
inline fun <reified T> filterByType(list: List<Any>): List<T> = list.filterIsInstance<T>()

/**
 * Мы можем привести объект к параметру типа, используя оператор as с параметром типа в угловых скобках. Однако если объект не является экземпляром параметра типа, будет выброшено исключение ClassCastException.
 *
 * Чтобы избежать этого, мы можем использовать оператор безопасного приведения as?, который возвращает null, если приведение невозможно.
 */
fun <T> exampleFunction2(obj: Any) {
    val tObj: T? = obj as? T

    if (tObj != null) {
        println("obj can be safely cast to type parameter T")
    } else {
        println("obj cannot be cast to type parameter T")
    }
}

/**
 * Проверка типов и приведение типов необходимы для любого языка программирования. Проверки типов позволяют разработчикам проверять, принадлежит ли объект к определённому типу данных, а приведения типов позволяют программистам преобразовывать объект из одного типа в другой.
 *
 * Kotlin, будучи языком со статической типизацией, имеет несколько функций, делающих проверку и приведение типов простыми и безопасными в использовании.
 */
fun typeCastAndSmartCast() {
    println("--- is and !is operators ---")

    /**
     * Оператор is возвращает значение true, если объект принадлежит к указанному типу, и значение false, если нет. И наоборот, оператор !is возвращает true, если объект не принадлежит к указанному типу, и false, если принадлежит.
     */
    val obj: Any = "Hello, Kotlin"

    if (obj is String) {
        println(obj.uppercase()) // HELLO, KOTLIN
    } else {
        println("obj is not a String") // Не будет вызван, т.к. obj строка и проходит условие
    }

    processInput(42) // Input is an integer
    processInput(obj) // Input is a string
    processInput(3.14) // Input is a double
    processInput(3.14f) // Unknown input

    println("--- Smart casts ---")

    /**
     * В Kotlin также есть функция, известная как умные приведения (smart casts).
     *
     * Смарт-приведения используются для упрощения кода при работе с типами, допускающими значение null.
     *
     * Когда тип, допускающий значение null, проверяется с помощью оператора is, Kotlin автоматически приводит объект к типу, не допускающему значение null.
     */
    val any: Any? = null

    if (any is String) {
        println(any.length) // Не будет вызван
    } else {
        println("any is not a String") // Будет вызван
    }

    println("--- 'Unsafe' cast operator ---")

    /**
     * В Kotlin есть небезопасный оператор приведения, который представлен ключевым словом as. Ключевое слово as используется для приведения объекта к ненулевому типу. Если объект не может быть приведён к указанному типу, оператор as создаёт исключение ClassCastException
     */
    val obj2: Any = "Hello, Kotlin"

    // Используем оператор as для приведения переменной obj2 к строке
    val str2: String = obj2 as String // Unsafe cast operator

    println(str2.uppercase()) // HELLO, KOTLIN

    // NullPointerException: null cannot be cast to non-null type kotlin.String
    // println(any as String)

    println("--- 'Safe' (nullable) cast operator ---")

    /**
     * `as?` оператор используется для приведения объекта к типу, допускающему значение null. Если объект не может быть приведён к указанному типу, оператор `as?` возвращает null.
     */
    val obj3: Any = 123
    val str3: String? = obj3 as? String // Safe (nullable) cast operator

    if (str3 != null) {
        println(str3.uppercase()) // Не будет вызван
    }

    println(any as? String) // null

    println("--- Generics type checks and casts ---")

    /**
     * В Kotlin мы также можем использовать проверки типов и приведения типов с дженериками. При работе с дженериками нам может понадобиться проверить, является ли объект экземпляром определённого параметра типа или привести его к параметру типа.
     *
     * Чтобы проверить, является ли объект экземпляром определённого параметра типа, мы можем использовать оператор is с параметром типа в угловых скобках.
     *
     * Важно отметить, что в Kotlin с дженериками происходит стирание типа, а это означает, что фактический тип универсального объекта неизвестен во время выполнения. Поэтому некоторые операции, такие как создание нового экземпляра параметра типа или проверка того, является ли параметр типа подтипом другого класса, невозможны.
     */

    println("--- Tasks ---")

    val list = listOf("abc", 42, '$', 3.14f, "string")

    println(countStrings(list)) // 2
    println(getStringsOnly(list)) // [abc, string]
}

/**
 * Принимает список объектов и возвращает количество строк в списке. Если элемент списка не является строкой, его необходимо пропустить.
 *
 * Функция должна использовать операторы is или !is для проверки типа элементов списка.
 */
fun countStrings(list: List<Any>): Int = list.count { it is String }

/**
 * Принимает список объектов и возвращает только те элементы списка, которые являются строками.
 */
fun <T> getStringsOnly(list: List<T>): List<String> {
    val result = mutableListOf<String>()

    for (item in list) {
        val obj = item as? String

        if (obj != null) {
            result.add(obj)
        }
    }

    return result.toList()
}

/**
 * Параметр типа именуется одной буквой в соответствии с соглашением, чтобы отличить его от обычного имени класса.
 *
 * - T – Type;
 * - S, U, V, etc. – 2nd, 3rd, 4th types;
 * - E – Element (often used by different collections);
 * - K – Key;
 * - V – Value;
 * - N – Number.
 */
fun generics() {
    println("--- Generics class ---")

    /**
     * Конструктор принимает переменную «некоторого типа» и устанавливает её в поле.
     */
    class Box<T>(t: T) {
        var value: T = t
            get() = field
            set(value) {
                field = value;
            }

    }

    val box1: Box<String> = Box("String")

    println(box1.value)

    box1.value = "Some"

    println(box1.value)

    val box2: Box<Int> = Box(42)

    println(box2.value)

    box2.value = 13

    println(box2.value)

    val box3: Box<List<Char>> = Box(listOf('a', 'b', 'c'))

    println(box3.value)

    box3.value = listOf('d', 'e', 'f')

    println(box3.value)

    // Дженерики для нескольких параметров
    class Three<T, U, V>(var first: T, var second: U, var third: V)

    val obj = Three<String, Int, Int>("abc", 1, 2)

    println(obj.first) // abc
    println(obj.second) // 1
    println(obj.third) // 2

    class Pair<T, P>(var first: T, var second: P) {
        fun changeFirst(newValue: T) {
            first = newValue
        }

        fun changeSecond(newValue: P) {
            second = newValue
        }

        fun printData() {
            println("Values: first = $first, second = $second")
        }
    }

    val nameLastname: Pair<String, String> = Pair("John", "Smith")
    val nameAge: Pair<String, Int> = Pair("Kite", 18)

    nameLastname.changeFirst("John")
    nameLastname.changeSecond("Smith")

    nameAge.changeFirst("Kate")
    nameAge.changeSecond(19)

    nameLastname.printData() // Values: first = John, second = Smith
    nameAge.printData() // Values: first = Kate, second = 19

    class RandomCollection<T>(val items: List<T>) {
        fun get(index: Int): T = items[index]
        fun length(): Int = items.size
    }

    val nums: RandomCollection<Int> = RandomCollection(listOf(1, 2, 3, 4))

    // "1 2 3 4 "
    for (i in 0 ..< nums.length()) {
        print("${nums.get(i)} ")
    }

    println()

    class GenericClass<T> {
        fun <U> someGenericMethod(t: T, u: U): T = t
    }

    println("--- Generics function ---")

    fun <T> doSomething(t: T): T {
        return t
    }

    fun <T, U> multipleDoSomething(t: T, u: U) {
        // do something
    }

    fun <T> calculateLength(list: List<T>): Int = list.size

    val list = listOf("hello", "from", "hyperskill")

    println(calculateLength(list)) // 3

    println("--- Generics extension function ---")

    class BiggerBox<T>(var value1: T, var value2: T)

    fun <T> BiggerBox<T>.changeBoxes() {
        val tmp = this.value1

        this.value1 = this.value2
        this.value2 = tmp
    }

    val box = BiggerBox("hyperskill", "kotlin")

    println("${box.value1} and ${box.value2}") // hyperskill and kotlin

    box.changeBoxes()

    println("${box.value1} and ${box.value2}") // kotlin and hyperskill
}

class StorageGeneric<T : Book>
open class Book
class Magazine : Book()
class Stone
interface Watchable

/**
 * Ограничение типов в дженериках
 *
 * Переменные типа могут иметь несколько границ, но внутри угловых скобок можно указать только одну верхнюю границу.
 *
 * Когда вы используете несколько границ, первым типом должен быть класс или интерфейс. Следующие типы должны быть интерфейсами
 */
fun typeBounds() {
    val storage1 = StorageGeneric<Book>()
    val storage2 = StorageGeneric<Magazine>()
    // val storage3 = StorageGeneric<Stone>(Stone) // compile-time error

    fun <T : Book> sortByDate(list: List<T>) {
        // ...
    }

    var listOne: List<Magazine> = listOf();
    var listTwo: List<String> = listOf();

    sortByDate(listOne) // OK, because Magazine is a subtype of Book
    // sortByDate(listTwo) // Error: String is not a subtype of Book

    fun <T> sortByDateWithAny(list: List<T>)
        where T : Book, T : Watchable {
            // ...
        }
}

open class ClassWithVeryLongName
typealias SomeClass = ClassWithVeryLongName

typealias Password = String

class Pet {
    class Kitten(name: String) {
        private var kittenName: String = name
        fun getName(): String = kittenName
    }
}

typealias Kitten = Pet.Kitten

class BoxForSomeDessert<T>(var dessert: T) {
    fun getDessertFromBox(): T = dessert
}

typealias DessertBox<T> = BoxForSomeDessert<T>

/**
 * Псевдонимы типов предоставляют альтернативные имена для существующих типов — как стандартных, так и пользовательских. Если имя типа слишком длинное, рекомендуется ввести другое, более короткое имя и использовать вместо него новое.
 *
 * Псевдонимы типов должны быть верхнего уровня! Вы не можете разместить их внутри классов или функций.
 *
 * Псевдонимы можно также использовать в импортах:
 * `import Pet.Kitten as Kitten`
 *
 * Следует помнить, что псевдонимы типов не вводят новые типы. Они эквивалентны соответствующим исходным типам.
 */
fun typeAliases() {
    class Some : SomeClass()

    val myPassword: Password = "hyperskill"

    val kitten: Kitten = Kitten("Fluffy")

    println(kitten.getName()) // Fluffy
    println(kitten.getName().length) // 6

    /// compile-time error: The integer literal does not conform to the expected type String
    // val kitten2: Kitten = Kitten(6)

    class Tart(var name: String)

    var tartBox1: DessertBox<Tart> = DessertBox(Tart("tastytart"))
    var tartBox2: BoxForSomeDessert<Tart> = BoxForSomeDessert(Tart("tastytart"))
    var tartBox3: DessertBox<Tart> = BoxForSomeDessert(Tart("tastytart"))

    println(tartBox1.getDessertFromBox().name) // tastytart
    println(tartBox2.getDessertFromBox().name) // tastytart
    println(tartBox3.getDessertFromBox().name) // tastytart
}

interface Comparator<in T> {
    fun compare(e1: T, e2: T): Int
}

/**
 * В контексте универсальных типов (дженериков) в программировании вариативность (дисперсия) означает возможность использовать более производный (или менее производный) тип, чем указано изначально.
 *
 * Разница применяется, когда у вас есть связь типов между двумя типами, и вы хотите поддерживать эту связь при использовании этих классов в качестве универсальных параметров.
 *
 * Различают три вида дисперсии:
 *
 * - Инвариант (Invariant): универсальный класс называется инвариантным по параметру типа, если для двух разных типов A и B Class<A> не является ни подтипом, ни супертипом Class<B>. Другими словами - не сохраняют отношения подтипов между типами.
 *
 * - Ковариантность (Covariance): универсальный класс называется ковариантным по параметру типа, если выполняется следующее: Class<A> является подтипом Class<B>, если A является подтипом B (сохраняемое отношение подтипирования).
 *
 * - Контравариантность (Contravariance): универсальный класс называется контравариантным по параметру типа, если выполняется следующее: Class<A> является подтипом Class<B>, если B является подтипом A (обратные отношения подтипирования).
 */
fun variance() {
    open class Animal
    class Dog : Animal()
    class Cat : Animal()

    println("--- Invariance ---")

    /**
     * Универсальные типы по умолчанию инвариантны, что означает, что они не сохраняют отношения подтипов между типами.
     *
     * Это не позволяет нам совершать ошибки, например, помещать Cat в Box<Dog> только потому, что Cat и Dog являются подтипами Animal.
     *
     * Это режим по умолчанию, в котором вы можете как создавать, так и потреблять значения.
     *
     * Например, MutableList<Dog> не является подтипом MutableList<Animal> или наоборот, поскольку вы можете создавать (get()) и потреблять (add()) значения, и он инвариантен.
     */
    class Box<T>

    val d: Animal = Dog() // Dog подтип Animal, ОК

    /**
     * Несмотря на то, что Dog является подтипом Animal, Box<Dog> не является подтипом Box<Animal>, и наоборот
     */
    // val bd: Box<Animal> = Box<Dog>() // Error: Type mismatch
    // val bp: Box<Dog> = Box<Animal>() // Error: Type mismatch

    /**
     * Аналогично, хотя Int является подтипом Number, Box<Int> не является подтипом Box<Number>, и наоборот
     */
    // val bn: Box<Number> = Box<Int>() // Error: Type mismatch
    // val bi: Box<Int> = Box<Number>() // Error: Type mismatch

    println("--- Covariance (out) ---")

    /**
     * Отношения сохраняются в том же направлении, когда классы используются в качестве общих параметров.
     *
     * Если Dog является подтипом Animal, Box<Dog> является подтипом Box<Animal>.
     *
     * Обычно это допускается, когда универсальный параметр используется только в «выходных» позициях (например, возвращаемых значениях), но не во «входных» позициях (например, в параметрах метода).
     *
     * Например, интерфейс List в Kotlin представляет собой коллекцию, доступную только для чтения. Это означает, что если Dog является подтипом Animal, то List<Dog> является подтипом List<Animal>. Такие классы или интерфейсы называются ковариантными.
     *
     * Вы можете прочитать элемент только в определенной позиции в списке (используя метод get()) и использовать его значение.
     *
     * Список определяется модификатором out (List<out T>, List<Animal>), который используется для объявления класса как ковариантного по определённому параметру типа.
     */
    val animals: List<Animal> = listOf(Dog(), Cat())
    val dogs: List<Dog> = listOf(Dog(), Dog())
    val cats: List<Cat> = listOf(Cat(), Cat())
    val animalsFromDogs: List<Animal> = dogs

    /**
     * Класс Box определён как ковариантный
     *
     * Обратите внимание: когда вы определяете класс с ковариацией с помощью ключевого слова out, это ограничивает использование типа T внутри класса.
     *
     * Вы можете использовать T только как тип возвращаемого значения, а не как тип параметра. Это связано с тем, что это может привести к нарушению инвариантов класса.
     *
     * Например, если бы вы могли добавить Cat в Box<Dog>, это уже не был бы Box<Dog>. Ключевое слово out сообщает компилятору, что универсальный класс (или интерфейс) создаёт значения типа T и никогда не использует их, обеспечивая безопасность типов.
      */
    class Box2<out T>

    val dog: Dog = Dog()
    val dogBoxOut: Box2<Dog> = Box2<Dog>()
    val animalBoxOut: Box2<Animal> = dogBoxOut

    println("--- Contravariance (in) ---")

    /**
     * Отношения сохраняются в противоположном направлении, когда классы используются в качестве общих параметров. Если Dog является подтипом Animal, Box<Animal> является подтипом Box<Dog>. Обычно это допускается, когда общий параметр используется только во «входных» позициях, но не в «выходных», например, в параметрах методов.
     *
     * Обратите внимание: когда вы определяете класс с контравариантностью с помощью ключевого слова in, это ограничивает использование типа T внутри класса.
     *
     * Мы объявляем класс Box с модификатором in для параметра типа T. Это позволяет нам присваивать Box<Animal> переменным типа Box<Dog> и Box<Cat>, поскольку Animal является суперклассом как Dog, так и Cat. Это возможно, поскольку мы используем контравариантность, которая позволяет нам назначать более общий тип (Box<Animal>) более конкретному типу (Box<Dog> или Box<Cat>).
     *
     * Ключевое слово in сообщает компилятору, что универсальный класс (или интерфейс) потребляет значения типа T и никогда не создаёт их, обеспечивая безопасность типов.
     *
     * Обратите внимание, что при контравариантности вы можете использовать параметр типа T только в качестве входных данных (в параметрах функции), а не в качестве выходных данных (в возвращаемых типах).
     */
    class Box3<in T>

    val dogBoxIn: Box3<Dog> = Box3<Animal>()
    val catBoxIn: Box3<Cat> = Box3<Animal>()

    /**
     * Давайте рассмотрим пример интерфейса Comparator.
     *
     * Компаратор — хороший пример контравариантности, поскольку он потребляет объекты (для их сравнения), но не производит их.
     *
     * Вот как вы можете определить контравариантный компаратор в Котлине:
     */
    open class AnimalIn {
        open fun feed() = println("Feeding an animal")
    }

    class DogIn : AnimalIn() {
        override fun feed() = println("Feeding a dog")
    }

    val animalComparator: Comparator<AnimalIn> = object : Comparator<AnimalIn> {
        override fun compare(e1: AnimalIn, e2: AnimalIn): Int {
            // Comparison logic goes here
            return 0
        }
    }

    val dogComparator: Comparator<DogIn> = animalComparator

    println("--- Type projections: use-site ---")

    /**
     * Use-site даёт возможность указывать модификаторы отклонения в точке использования, а не в определении класса или интерфейса.
     *
     * Представьте, что вам нужно скопировать MutableList с классами Animals, Dogs и Cats. Помните, что MutableList инвариантен. Вы можете использовать use-site для объявления ограничений.
     *
     * В этом примере у нас есть классы Animal, Dog и Cat. У нас также есть функция copyAnimals, которая принимает два параметра: source с типом MutableList<out Animal> (ковариантный) и destination с типом MutableList<in Animal> (контравариантный).
     *
     * Функция copyAnimals копирует элементы из списка source в список destination с помощью функции addAll. Поскольку source объявлен с отклонением от места использования (out Animal), он позволяет читать элементы типа Animal из списка. А поскольку destination объявлено с вариацией места использования (in Animal), оно позволяет записывать в список элементы типа Animal.
     *
     * Затем мы дважды вызываем функцию copyAnimals: сначала с Dog в качестве источника и Animal в качестве места назначения, а затем с Cat в качестве источника и Animal в качестве места назначения. Это позволяет нам копировать объекты Dog и Cat в список Animal.
     */
    fun copyAnimals(source: MutableList<out Animal>, destination: MutableList<in Animal>) {
        destination.addAll(source)
    }

    val dogsUse: MutableList<Dog> = mutableListOf(Dog(), Dog())
    val catsUse: MutableList<Cat> = mutableListOf(Cat(), Cat())

    val animalsUse: MutableList<Animal> = mutableListOf()

    copyAnimals(dogsUse, animalsUse)
    copyAnimals(catsUse, animalsUse)

    println(animalsUse)

    println("--- Type projections: star projection ---")

    /**
     * Звездная проекция — это функция Kotlin, которая позволяет работать с универсальными типами, когда точный аргумент типа неизвестен или не имеет значения. Он обозначается символом * и в определенных сценариях может использоваться вместо аргумента определенного типа. Звездчатая проекция полезна, когда вы хотите работать с универсальным типом таким образом, чтобы он учитывал любой аргумент типа, удовлетворяющий определенным ограничениям. Это обеспечивает большую гибкость и универсальность кода, который не зависит от аргумента конкретного типа.
     */
    class Box4<T>(val item: T)

    /**
     * Функция printItems принимает в качестве параметра список Box<*> (звездообразной проекции). Внутри функции мы перебираем поля и печатаем элемент, содержащийся в каждом поле. Поскольку точный аргумент типа неизвестен, мы можем выполнять только операции чтения над элементами. Поскольку аргумент типа неизвестен или не имеет значения в этом контексте, мы используем звездчатую проекцию (Box<*>) для размещения экземпляров Box<Dog> и Box<Cat>. Код способен обрабатывать различные подтипы Animal, не полагаясь на конкретный аргумент типа. Звездчатую проекцию можно использовать с классом Box<T>, что обеспечивает гибкость и универсальность при работе с универсальными типами, где точный аргумент типа неизвестен или не имеет значения.
     */
    fun printItems(boxes: List<Box4<*>>) {
        for (box: Box4<*> in boxes) {
            println(box.item)
        }
    }

    val dogBox = Box4(Dog())
    val catBox = Box4(Cat())

    val boxes: List<Box4<*>> = listOf(dogBox, catBox)

    printItems(boxes)
}

inline fun <reified T, R> convertList(list: List<T>, crossinline transform: (T) -> R): List<R> {
    return list.map(transform)
}

/**
 * Преобразование списка объектов одного типа в список объектов другого типа встречается довольно часто.
 *
 * Напишите универсальную функцию convertList, которая может конвертировать список объектов из одного типа в другой, используя функцию, переданную в качестве параметра.
 *
 * В вашей функции используйте два параметра типа reified — T и R. T представляет тип элементов во входном списке, а R представляет тип элементов в выходном списке.
 *
 * Функция преобразования принимает элемент типа T и преобразует его в элемент типа R.
 */
fun convertingAnObjectList() {
    val list: List<Char> = listOf('1', '2', '3')

    val transform = { char: Char -> char.digitToInt() }

    val result: List<Int> = convertList(list, transform).map { it + 2 }

    println(result)
}
