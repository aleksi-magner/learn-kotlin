/**
 * Интерфейс — это набор методов, описывающих поведение объекта. Для реализации интерфейса объект должен реализовать все методы из него. Поведение объекта жёсткое и не может измениться во время выполнения программы.
 *
 * Обычно мы используем глаголы для имён методов и существительные или прилагательные для имён интерфейсов.
 *
 * Один метод соответствует только одному навыку объекта, но во многих ситуациях этого достаточно, чтобы сделать интерфейс эффективным.
 *
 * Если не разбивать интерфейсы с большим количеством обязанностей на более мелкие, код запутается, и ни вы, ни другие разработчики его не поймёте.
 *
 * Иногда, работая с классами, мы имеем представление о том, что класс должен делать, но нам не нужно — или мы не можем по какой-то причине — кодировать весь класс сразу. Вот когда интерфейсы пригодятся.
 *
 * Интерфейсы позволяют создать что-то вроде скелета класса.
 *
 * Интерфейсы определяются аналогично классам, только без конструкторов — интерфейсы не могут хранить состояния.
 *
 * Это означает, что мы не можем создать экземпляр интерфейса, но мы можем создать экземпляр класса, реализующего этот интерфейс.
 *
 * Каждое поле или метод, объявленный в интерфейсе, нужно объявить в классе с ключевым словом override, так как это показывает, что мы «затираем» общий случай интерфейса спецификой его реализации.
 *
 * Если в классе нужны какие-то методы, которые не являются частью интерфейса, переопределение следует опустить.
 *
 * Также обратите внимание, что нам не нужно переопределять каждое отдельное свойство или метод: если у них есть реализация по умолчанию. Однако вы можете сделать это, если реализация по умолчанию не соответствует вашим целям.
 *
 * Поскольку интерфейс не может поддерживать состояния, так как это просто контракт для реализации другими классами, вместо него мы можем использовать геттеры для достижения того же результата (вы не можете использовать сеттеры, поскольку нет экземпляров, которым можно было бы что-либо присваивать)
 */
interface Animal {
    val numberOfLimbs: Int

    val age: Int
        get() = 10

    fun move()
    fun communicate(): String

    // Реализация метода по умолчанию
    fun printNumberOfLimbs() {
        println(numberOfLimbs)
    }
}

class Cat : Animal {
    override val numberOfLimbs: Int = 4

    override fun move() {
        println("Run")
    }

    override fun communicate(): String = "Say meow"
}

class Parrot : Animal {
    override val numberOfLimbs: Int = 2

    override fun move() {
        println("Fly")
    }

    override fun communicate(): String = "Talk"

    fun myAnimalMethod() {
        println("My method")
    }
}

fun main() {
    val cat = Cat()

    println("Cat age: ${cat.age}") // Cat age: 10
    println("Cat number of limbs: ${cat.numberOfLimbs}") // Cat number of limbs: 4
    println("Cat communicate: ${cat.communicate()}") // Cat communicate: Say meow

    cat.move() // Run
    cat.printNumberOfLimbs() // 4

    val parrot = Parrot()

    println("Parrot age: ${parrot.age}") // Parrot age: 10
    println("Parrot number of limbs: ${parrot.numberOfLimbs}") // Parrot number of limbs: 2
    println("Parrot communicate: ${parrot.communicate()}") // Parrot communicate: Talk

    parrot.move() // Fly
    parrot.printNumberOfLimbs() // 2
    parrot.myAnimalMethod() // My method

    // Интерфейсы коллекций
    val oldList: MutableList<String> = "8 12 25 56 192 32 76 21".split(" ").toMutableList()
    val oldSet: MutableSet<String> = oldList.toMutableSet()

    val addedList: List<String> = "12 67986 12 889 9898 12 3232".split(" ").toList()

    fun addListToCollection(list: MutableCollection<String>, addedList: Collection<String>): MutableCollection<String> {
        list.addAll(addedList)

        return list
    }

    addListToCollection(oldList, addedList)
    addListToCollection(oldSet, addedList)

    println(oldList.joinToString(" ")) // 8 12 25 56 192 32 76 21 12 67986 12 889 9898 12 3232
    println(oldSet.joinToString(" ")) // 8 12 25 56 192 32 76 21 67986 889 9898 3232
}
