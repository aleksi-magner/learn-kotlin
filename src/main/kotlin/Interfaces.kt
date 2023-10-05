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
 *
 * Принцип разделения интерфейсов (ISP) основан на двух ключевых моментах:
 * - объекты не следует заставлять реализовывать части интерфейсов, которые они не используют;
 * - многие конкретные интерфейсы лучше, чем один интерфейс общего назначения.
 *
 * Другими словами, чтобы сделать наши интерфейсы точными, мы должны сделать их конкретными, чтобы нам не пришлось реализовывать ненужные методы в будущем.
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

/**
 * Наследование интерфейсов
 *
 * Основное правило реализации производных интерфейсов заключается в том, что класс должен реализовывать методы и свойства как из базового, так и из производного интерфейса
 *
 * В примере интерфейс Bird является производным от интерфейса Animal, но с добавлением некоторых собственных методов и свойств.
 */
interface Bird : Animal {
    val canFly: Boolean
    val flyingSpeed: Int

    fun buildNest()
}

class Cockatoo : Bird {
    override val numberOfLimbs: Int = 2
    override val canFly: Boolean = true
    override val flyingSpeed: Int = 20

    override fun move() {
        println("Fly")
    }

    override fun communicate(): String = "Speak"

    override fun buildNest() {
        println("Collect materials")
        println("Find good place")
        println("Build small nest")
    }
}

/**
 * Мы также можем использовать множественное наследование: класс, реализующий несколько разных интерфейсов.
 */
interface SimpleBird : Animal {
    fun buildNest()
}

interface Flying {
    val flyingSpeed: Int
    val flyingManeuverability: Int
}

class Owl : SimpleBird, Flying {
    // Flying interface
    override val flyingSpeed: Int = 100
    override val flyingManeuverability: Int = 95

    // Bird interface
    override fun buildNest() {
        println("Build small nest")
    }

    // Animal Interface
    override val numberOfLimbs: Int = 2

    override fun move() {
        println("Fly")
    }

    override fun communicate(): String = "Coo"
}

/**
 * Точно так же, как классы могут реализовывать несколько интерфейсов, один интерфейс может быть производным от нескольких других.
 */
interface FlyingBird : Animal, Flying {
    fun buildNest()
}

class Owl2 : FlyingBird {
    // Flying interface
    override val flyingSpeed: Int = 100
    override val flyingManeuverability: Int = 95

    // Bird interface
    override fun buildNest() {
        println("Build small nest")
    }

    // Animal Interface
    override val numberOfLimbs: Int = 2

    override fun move() {
        println("Fly")
    }

    override fun communicate(): String = "Coo"
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

    val cockatoo = Cockatoo()

    println("Cockatoo age: ${cockatoo.age}") // Cockatoo age: 10
    println("Cockatoo number of limbs: ${cockatoo.numberOfLimbs}") // Cockatoo number of limbs: 2
    println("Cockatoo can fly: ${cockatoo.canFly}") // Cockatoo can fly: true
    println("Cockatoo flying speed: ${cockatoo.flyingSpeed}") // Cockatoo flying speed: 20
    println("Cockatoo communicate: ${cockatoo.communicate()}") // Cockatoo communicate: Speak

    cockatoo.move() // Fly
    cockatoo.printNumberOfLimbs() // 2
    cockatoo.buildNest()

    val owl = Owl()

    println("Owl age: ${owl.age}") // Owl age: 10
    println("Owl number of limbs: ${owl.numberOfLimbs}") // Owl number of limbs: 2
    println("Owl flying speed: ${owl.flyingSpeed}") // Owl flying speed: 100
    println("Owl flying maneuverability: ${owl.flyingManeuverability}") // Owl flying maneuverability: 95
    println("Owl communicate: ${owl.communicate()}") // Owl communicate: Coo

    owl.move() // Fly
    owl.printNumberOfLimbs() // 2
    owl.buildNest() // Build small nest

    val owl2 = Owl2()

    println("Owl2 age: ${owl2.age}") // Owl2 age: 10
    println("Owl2 number of limbs: ${owl2.numberOfLimbs}") // Owl2 number of limbs: 2
    println("Owl2 flying speed: ${owl2.flyingSpeed}") // Owl2 flying speed: 100
    println("Owl2 flying maneuverability: ${owl2.flyingManeuverability}") // Owl flying maneuverability: 95
    println("Owl2 communicate: ${owl2.communicate()}") // Owl2 communicate: Coo

    owl2.move() // Fly
    owl2.printNumberOfLimbs() // 2
    owl2.buildNest() // Build small nest

    collections()
    interfaceComposition()
}

fun addListToCollection(list: MutableCollection<String>, addedList: Collection<String>): MutableCollection<String> {
    list.addAll(addedList)

    return list
}

// Интерфейсы коллекций
fun collections() {
    val oldList: MutableList<String> = "8 12 25 56 192 32 76 21".split(" ").toMutableList()
    val oldSet: MutableSet<String> = oldList.toMutableSet()

    val addedList: List<String> = "12 67986 12 889 9898 12 3232".split(" ").toList()

    addListToCollection(oldList, addedList)
    addListToCollection(oldSet, addedList)

    // 8 12 25 56 192 32 76 21 12 67986 12 889 9898 12 3232
    println(oldList.joinToString(" "))

    // 8 12 25 56 192 32 76 21 67986 889 9898 3232
    println(oldSet.joinToString(" "))
}

interface FirstInterface {
    fun f() { println("First") }
    fun g() { println("First g") }
}

interface SecondInterface {
    fun f() { println("Second") }
    fun g() { println("Second g") }
}

// Класс наследует два разных интерфейса с одинаковыми методами.
// Пример того, как указывать из какого интерфейса брать нужный метод, в случае конфликтов
class ThirdClass : FirstInterface, SecondInterface {
    override fun f() {
        super<FirstInterface>.f()
        super<SecondInterface>.f()
    }

    override fun g() {
        super<SecondInterface>.g()
    }
}

/**
 * Предположим, мы хотим создать игру. Например, персонажи в нашей игре имеют 3 параметра:
 *
 * - Level - насколько они сильны
 *
 * - Enemy - является ли персонаж врагом или союзником
 *
 * - Class - что персонаж может делать, например, сражаться мечом или произносить заклинания
 *
 * Следуя принципам наследования, мы могли бы реализовать это следующим образом:
 */
interface Level {
    fun getLevel(): Int
}

interface Enemy {
    fun isEnemy(): Boolean
}

interface Class {
    fun getClass(): String
}

/**
 * И для каждого символа нам пришлось бы написать довольно много кода
 *
 * Мы создали персонажа 10-го уровня, который является Врагом для игрока и принадлежит к классу Воин.
 *
 * Для каждого нового персонажа нам нужно будет вручную установить все эти параметры. Как насчёт того, чтобы попытаться повторно использовать одни и те же параметры для оптимизации процесса?
  */
class DangerousEnemyWarrior : Level, Enemy, Class {
    override fun getLevel(): Int = 10
    override fun isEnemy(): Boolean = true
    override fun getClass(): String = "Warrior"
}

// Нам нужно создать все эти объекты только один раз
object Dangerous : Level {
    override fun getLevel(): Int = 10
}

object NotDangerous : Level {
    override fun getLevel(): Int = 1
}

object Foe : Enemy {
    override fun isEnemy(): Boolean = true
}

object Friend : Enemy {
    override fun isEnemy(): Boolean = false
}

object Warrior : Class {
    override fun getClass(): String = "Warrior"
}

object Wizard : Class {
    override fun getClass(): String = "Wizard"
}

// И тогда мы сможем использовать их столько раз, сколько захотим!
class DangerousKotlinEnemyWarrior : Level by Dangerous, Enemy by Foe, Class by Warrior

class NotDangerousFriendlyWizard : Level by NotDangerous, Enemy by Friend, Class by Wizard


/**
 * Наследование как шаблон проектирования имеет свои недостатки. Есть альтернатива этому, которая предполагает повторное использование того же кода и называется композицией.
 *
 * Парадигма для более эффективной реализации известна как композиция.
 *
 * Вместо того, чтобы реализовывать множество интерфейсов и переопределять все их методы и поля, мы можем заранее создать их реализации и хранить их как отдельные сущности. Затем мы можем просто составить из этих «кирпичиков» нужный объект.
 *
 * И именно здесь в игру вступает делегирование, поскольку в Котлине это очень удобный способ ссылаться на существующую реализацию вместо того, чтобы предоставлять ее там, где это необходимо.
 */
fun interfaceComposition() {
    val myClass = ThirdClass()

    // First
    // Second
    myClass.f()

    // Second g
    myClass.g()

    // Наследование
    val inheritedClass = DangerousEnemyWarrior()

    println(inheritedClass.getClass()) // Warrior
    println(inheritedClass.isEnemy()) // true
    println(inheritedClass.getLevel()) // 10

    // Композиция
    val character1 = DangerousKotlinEnemyWarrior()

    println(character1.getClass()) // Warrior
    println(character1.isEnemy()) // true
    println(character1.getLevel()) // 10

    val character2 = NotDangerousFriendlyWizard()

    println(character2.getClass()) // Wizard
    println(character2.isEnemy()) // false
    println(character2.getLevel()) // 1
}
