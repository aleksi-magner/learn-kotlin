import kotlin.math.roundToInt

fun main() {
    /**
     * Объект — это часть памяти, которая что-то хранит.
     * Переменные указывают только на объекты.
     *
     * Простым примером объекта является строка, в которой хранится сообщение.
     *
     * Строка имеет состояние: она содержит не только последовательность символов, но и размер последовательности, то есть длину сообщения.
     *
     * В Kotlin то, что позволяет вам получить доступ к состоянию объекта, называется свойством.
     *
     * Структурное равенство переменных подразумевает равенство внутренних состояний.
     *
     * Вы можете использовать операторы == и != для проверки структурного равенства.
     *
     * Ссылочное равенство переменных означает, что эти переменные указывают на один и тот же объект.
     *
     * Вы можете использовать операторы === и !== для проверки ссылочного равенства.
     *
     * Ключевое слово val означает, что вы не можете переназначить переменную, а не неизменность.
     */
     val msg = "Hi"

     println(msg.length) // 2

    /**
     * В Kotlin некоторые функции привязаны к определённому типу. Эти функции также называются методами.
     */
     println(msg.repeat(3)) // "HiHiHi"

    copingByReference()

    /**
     * Создание нового класса
     *
     * Когда класс имеет пустое тело, фигурные скобки можно опустить.
     *
     * Обычно лучше объявить их на верхнем уровне файла, но вы можете объявить их и в других местах, даже внутри функции.
     */
    class Emptiness

    /**
     * Создание объекта
     */
    // Создаём экземпляр класса
    val empty = Emptiness()

    writingClassProperties()
    comparisonObjects()
    defaultConstructor()
    primaryConstructor()
    secondaryConstructor()
    overridingConstructors()
    initBlock()
    memberFunctions()
    finalMembers()
    visibilityModifiersForMembers()
    nestedClasses()
    inheritance()
    polymorphism()
    overriding()
    delegation()
    dataClass()
    destructuring()
    hashCode()
    equals()
    sealed()
    abstractClasses()
    gettersAndSetters()
    objectDeclarations()
    companionObject()

    weatherComparison()
    createTable()
    animalSounds()
    shapeArea()

    typeSafeBuilders()
}

/**
 * Копирование по ссылке
 *
 * Значения переменных ссылаются на значения по ссылкам.
 *
 * Мутабельность
 *
 * Что произойдёт при изменении объекта у одной переменной, изменится ли она для другой?
 * Примитивные типы данных, такие как Int, String, Float и Double являются не изменяемыми объектами.
 * Мы не меняем её объект — переменной присваивается новая ссылка на объект с новым значением.
 */
fun copingByReference() {
    val msg1 = "Hi"
    val msg2: String = msg1

    /**
     * И msg1, и msg2 ссылаются на одно и то же место в памяти, где хранится значение "Hi".
     * Таким образом, оператор присвоения копирует не сам объект, а ссылку на него.
     */
    println(msg1 === msg2) // true
 }

/**
 * Запись свойств класса
 *
 * Свойства очень похожи на переменные и значения.
 * Если вы хотите присвоить значение свойству во время выполнения, вы объявляете его как var, в противном случае ваш выбор — val.
 */
fun writingClassProperties() {
    /**
     * Каждый объект класса имеет одинаковый набор полей, но значения полей могут отличаться от объекта к объекту.
     */
    class Patient {
        var name: String = "Unknown"
        var age: Int = 0
        var height: Double = 0.0
    }

    /**
     * Доступ к свойствам
     */
    val patient = Patient()

    println(patient.name) // "Unknown"
    println(patient.age)  // "0"

    /**
     * Изменение свойств
     */
    val john = Patient()

    john.name = "John"
    john.age = 30
    john.height = 180.0

    val alice = Patient()

    alice.name = "Alice"
    alice.age = 22
    alice.height = 165.0

    println("${john.name}: ${john.age} yrs, ${john.height} cm") // John: 30 yrs, 180.0 cm
    println("${alice.name}: ${alice.age} yrs, ${alice.height} cm") // Alice: 22 yrs, 165.0 cm

    class OperatingSystem {
        var name: String = ""
    }

    class DualBoot {
        private val os1 = OperatingSystem()
        private val os2 = OperatingSystem()

        var primaryOs: OperatingSystem = os1
        var secondaryOs: OperatingSystem = os2
    }

    val boot = DualBoot()
    val mainOS = OperatingSystem()
    val secondaryOS = OperatingSystem()

    mainOS.name = "Linux"
    secondaryOS.name = "FreeBSD"

    boot.primaryOs = mainOS
    boot.secondaryOs = secondaryOS

    println("Main OS: ${mainOS.name}") // Main OS: Linux
    println("Secondary OS: ${secondaryOS.name}") // Secondary OS: FreeBSD
}

/**
 * Сравнение объектов
 */
fun comparisonObjects() {
    println("--- Comparison ---")

    /**
     * Структурное равенство
     */
    val msg1 = "Hi"
    val msg2 = "Hi"

    println(msg1 == msg2) // true
    println(msg1 == "Hi") // true
    println(msg2 == "Hi") // true
    println(msg1 == "Hello") // false
    println(msg1 != "Hello") // true

    println("--- Box ---")

    data class Box(var amount: Int) {
        fun addBall() {
            amount += 1
        }
    }

    val blueBox = Box(3) // коробка с 3 мячами
    val azureBox: Box = blueBox

    println("Blue box (before): ${blueBox.amount} balls") // Blue box (before): 3 balls
    println("Azure box (before): ${azureBox.amount} balls") // Azure box (before): 3 balls

    println(blueBox == azureBox) // true, это копия

    blueBox.addBall() // добавить мяч в первую коробку

    println(blueBox == azureBox) // true, во второй коробке тоже 4 мяча

    println("Blue box (after): ${blueBox.amount} balls") // Blue box (after): 4 balls
    println("Azure box (after): ${azureBox.amount} balls") // Azure box (after): 4 balls

    /**
     * Ссылочное равенство
     *
     * Kotlin предоставляет специальный оператор `===` для проверки, указывают ли переменные на один и тот же объект.
     */
    val redBox = Box(3)
    val yellowBox: Box = redBox
    val cyanBox = Box(3)

    cyanBox.addBall()

    println("redBox (${redBox.amount}) == yellowBox (${yellowBox.amount}): ${redBox == yellowBox}")  // true
    println("redBox (${redBox.amount}) === yellowBox (${yellowBox.amount}): ${redBox === yellowBox}") // true, azureBox указывает на тот же объект

    redBox.addBall()

    println("redBox (${redBox.amount}) == cyanBox (${cyanBox.amount}): ${redBox == cyanBox}") // true
    println("redBox (${redBox.amount}) === cyanBox (${cyanBox.amount}): ${redBox === cyanBox}") // false, cyanBox указывает на другой объект

    /**
     * Итак, blueBox и cyanBox имеют одинаковое состояние, но указывают на разные объекты.
     * В этом случае, если вы измените состояние blueBox, cyanBox останется прежним
     */
    redBox.addBall()

    println("redBox (${redBox.amount}) == cyanBox (${cyanBox.amount}): ${redBox == cyanBox}") // false
    println("redBox (${redBox.amount}) === cyanBox (${cyanBox.amount}): ${redBox === cyanBox}") // false, cyanBox указывает на другой объект

    println("--- MutableList ---")

    val list1: MutableList<Int> = mutableListOf()
    val list2: MutableList<Int> = list1

    list1.add(1)

    println("list1 $list1") // list1 [1]
    println("list2 $list2") // list2 [1]
    println("list1 == list2: ${list1 == list2}") // true

    list2.add(5)

    println("list1 $list1") // list1 [1, 5]
    println("list2 $list2") // list2 [1, 5]
    println("list1 == list2: ${list1 == list2}") // true
}

/**
 * Конструктор по умолчанию
 *
 * Конструкторы — это члены класса, которые инициализируют новый объект класса. Другими словами, конструкторы устанавливают новое состояние объекта, определяя его свойства. Итак, когда вы создаёте объект, вы вызываете конструктор.
 */
fun defaultConstructor() {
    /**
     * На самом деле это вызов конструктора, и это похоже на вызов функции без аргументов.
     *
     * Каждый класс должен иметь конструктор, поэтому если он не определён явно, компилятор автоматически генерирует конструктор по умолчанию, который только создаёт объект и не имеет внутри никакой логики.
     */
    class SizeWithDefaultConstructor {
        var width: Int = 1
        var height: Int = 1
    }

    val size1 = SizeWithDefaultConstructor()
}

/**
 * Основной конструктор
 *
 * Вы можете поместить простые объявления свойств внутрь основного конструктора.
 *
 * Чтобы объявить свойство только для чтения, поместите ключевое слово val в круглые скобки перед именем аргумента.
 *
 * Для изменяемого свойства используйте ключевое слово var.
 */
fun primaryConstructor() {
    class SizeWithPrimaryConstructor(val width: Int = 1, val height: Int = 1) {
        val area: Int = width * height
    }

    val size2 = SizeWithPrimaryConstructor(4, 6)
    val size3 = SizeWithPrimaryConstructor()
    val size4 = SizeWithPrimaryConstructor(height = 6, width = 4)

    println(size2.area) // 24
    println(size3.area) // 1
    println(size4.area) // 24

    /**
     * Однострочные классы
     *
     * Если не осталось других членов класса, кроме тех, что в первичном конструкторе, мы можем опустить пустые фигурные скобки.
     *
     * Представьте, что в нашем примере отсутствует свойство area:
     */
    class SizeSingleLine(val width: Int = 4, val height: Int = 6)

    val size5 = SizeSingleLine()

    println(size5.width) // 24
    println(size5.height) // 24
}

/**
 * Первичные конструкторы имеют свои ограничения. Вам может понадобиться создать несколько разных конструкторов для одного и того же класса, но вы не сможете сделать это с помощью одного основного конструктора. Вот где вторичные или пользовательские конструкторы пригодятся.
 *
 * Создать несколько конструкторов для класса почти так же просто, как создать только один, но необходимо помнить об одном конкретном ограничении. Каждый вторичный конструктор должен иметь уникальную подпись. Вы не можете использовать одну и ту же подпись для основного или любого другого конструктора.
 *
 * Сигнатура конструктора состоит из числа, типов и порядка параметров. Чтобы создать допустимый конструктор, необходимо убедиться, что он имеет уникальный список параметров.
 */
fun secondaryConstructor() {
    /**
     * Помните, что сигнатуры определяются типами параметров, а не их именами. Например, компилятор не может отличить эти два конструктора, даже если для человека они выглядят по-разному:
     *
     * constructor(width: Int, height: Int) {}
     * constructor(x: Int, y: Int) {}
     */
    class Size {
        val width: Int
        val height: Int

        constructor(height: Int) {
            this.width = 1
            this.height = height
        }

        constructor(width: Int, height: Int) {
            this.width = width
            this.height = height
        }

        constructor(width: Int, height: Double) {
            this.width = width
            this.height = height.toInt()
        }

        constructor(height: Double, width: Int) {
            this.width = width
            this.height = height.toInt()
        }
    }

    val size1 = Size(7) // uses 1st constructor
    val size2 = Size(2, 7) // uses 2nd constructor
    val size3 = Size(3,  7.0) // uses 3rd constructor
    val size4 = Size(7.0,  4) // uses 4th constructor

    println("Size 1: ${size1.width}x${size1.height}")
    println("Size 2: ${size2.width}x${size2.height}")
    println("Size 3: ${size3.width}x${size3.height}")
    println("Size 4: ${size4.width}x${size4.height}")

    /**
     * Если у класса есть первичный конструктор, каждый вторичный конструктор должен вызывать первичный прямо или косвенно через другой вторичный конструктор(ы). Это называется делегированием.
     *
     * Делегирование другому конструктору того же класса выполняется с помощью ключевого слова this, помещаемого после аргументов конструктора и перед телом конструктора.
     *
     * Делегирование первичному конструктору становится первым оператором вторичного конструктора, поэтому свойства инициализируются до выполнения кода вторичного конструктора. Блоки инициализатора, если они есть, также выполняются перед вторичным конструктором. Если класс не имеет первичного конструктора, делегирование происходит неявно.
     */
    class Size2(val width: Int, val height: Int) {
        var area: Int = width * height

        init {
            println("Object with area equal to $area is created")
        }

        constructor(width: Int, height: Int, outerSize: Size2): this(width, height) {
            outerSize.area -= this.area

            println("Updated outer object's area is equal to ${outerSize.area}")
        }
    }

    val outerObject = Size2(5, 8)
    val innerObject = Size2(2, 3, outerObject)

    // outerObject: 5x8, area 34
    println("outerObject: ${outerObject.width}x${outerObject.height}, area ${outerObject.area}")

    // innerObject: 2x3, area 6
    println("innerObject: ${innerObject.width}x${innerObject.height}, area ${innerObject.area}")
}

/**
 * Kotlin не позволяет напрямую переопределять конструкторы. Однако, используя наследование и полиморфизм, мы можем расширять конструкторы суперклассов в подклассах.
 *
 * В контексте объектно-ориентированного программирования (ООП) переопределение — это механизм, позволяющий подклассу реализовать метод, уже определённый в его суперклассе.
 */
fun overridingConstructors() {
    open class Person1(val name: String)

    // В этом случае используем конструктор суперкласса, но не переопределяем его
    class Employee1(name: String, val id: Int) : Person1(name)

    /**
     * Конструкторы в Kotlin не могут быть переопределены. Однако вы можете определить конструкторы в подклассах, которые используют или «расширяют» конструкторы суперкласса.
     *
     * При объявлении подкласса мы можем использовать ключевые слова open, final и override для управления наследованием и полиморфизмом.
     *
     * - open позволяет подклассам наследовать или переопределять функции и свойства
     * - final предотвращает переопределение функций или свойств подклассами
     * - override используется подклассом для переопределения функций или свойств суперкласса
     *
     * Есть несколько распространенных ошибок и проблем, которые могут возникнуть при работе с конструкторами в контексте наследования и полиморфизма.
     *
     * Одна из самых распространенных ошибок — не вызывать конструктор суперкласса в подклассе.
     */
    open class Person2(open val name: String)

    // Переопределяем свойство name
    class Employee2(override val name: String, val id: Int) : Person2(name)

    // class Employee(val id: Int) : Person1 // Error: требуется вызов конструктора суперкласса

    open class Person3(val name: String) {
        fun talk() {
            println("$name is talking")
        }
    }

    // Класс Employee расширяет класс Person, используя его конструктор для установки свойства name
    // Это пример наследования, когда подкласс (Employee) использует конструктор суперкласса (Person)
    class Employee3(name: String, val id: Int) : Person3(name) {
        fun work() {
            println("$name is working with id $id")
        }
    }

    val person = Person3("John")

    person.talk() // John is talking

    val employee = Employee3("Jane", 123)

    employee.talk() // Jane is talking
    employee.work() // Jane is working with id 123
}

/**
 * Инициализация
 *
 * Первичные конструкторы не могут содержать никакого кода: они только устанавливают значения свойств класса на основе переданных аргументов.
 *
 * Иногда мы хотим установить некоторые из наших свойств на основе значений других свойств или других источников информации.
 *
 * В таких случаях мы будем использовать блоки инициализации, перед которыми стоит ключевое слово init
 *
 * Ключевое слово init обозначает блок кода, который служит расширением основного конструктора и вызывается после того, как свойства объекта были установлены в основном конструкторе
 */
fun initBlock() {
    class SizeWithInit(width: Int = 1, height: Int = 1) {
        var area: Int = 0

        init {
            area = width * height
        }
    }

    val size6 = SizeWithInit(6, 4)

    println(size6.area) // 24

    val timer1 = ByteTimer(-300)
    val timer2 = ByteTimer(300)
    val timer3 = ByteTimer(42)

    println(timer1.time) // -128
    println(timer2.time) // 127
    println(timer3.time) // 42
}

/**
 * Если число меньше -128, то время свойства класса ByteTimer должно быть -128. Если оно больше 127, то оно должно быть 127, в противном случае это должно быть его необработанное значение.
 */
class ByteTimer(var time: Int) {
    init {
        time = when {
            time < -128 -> -128
            time > 127 -> 127
            else -> time
        }
    }
}

/**
 * Функции-члены
 *
 * Иногда вам нужно хранить в объектах не только сами данные, но и поведение. Функции-члены реализуют общее поведение для всего набора объектов, принадлежащих одному классу.
 *
 * Обратите внимание, что функция-член — это официальное название Kotlin для функций, которые помещаются внутри класса. Такие функции часто называют методами.
 */
fun memberFunctions() {
    class MyClass(var property: Int) {
        fun print(): Unit = println("Hello from print")

        fun printProperty(): Unit = println(this.property)
    }

    val myClass1 = MyClass(42)
    val myClass2 = MyClass(13)

    myClass1.print()
    myClass1.printProperty()
    myClass2.printProperty()
}

/**
 * В Kotlin все классы и методы по умолчанию окончательные (final).
 *
 * Это означает, что по умолчанию нельзя наследовать классы. Что открыть возможность наследования, нужно явно сделать класс открытым (open).
 *
 * Точно так же методы и свойства по умолчанию являются окончательными (final), и их нельзя переопределить в подклассах, пока они не будут объявлены открытыми (open).
 */
fun finalMembers() {
    /**
     * В этом примере мы не можем наследовать MyFinalClass или переопределить myFinalMethod, потому что оба они являются окончательными (final) по умолчанию.
     */
    class MyFinalClass {
        fun myFinalMethod() {
            println("This method cannot be overridden!")
        }
    }

    /// Error
    // class MyChildClass: MyFinalClass() {
    //     override fun myFinalMethod() {
    //         println("I'm trying to override your method!")
    //     }
    // }

    /**
     * Если вы хотите, чтобы ваш класс можно было наследовать или метод можно было переопределить, вам нужно использовать ключевое слово open.
     */
    open class MyBaseClass {
        open fun myMethod() {
            println("Basic implementation")
        }
    }

    class MyDerivedClass : MyBaseClass() {
        override fun myMethod() {
            println("Overridden implementation")
        }
    }

    val myDerived = MyDerivedClass()

    myDerived.myMethod() // Overridden implementation

    /**
     * Вы можете использовать final для переопределённых методов или свойств, чтобы предотвратить их дальнейшее переопределение
     */
    open class MyIntermediateClass : MyBaseClass() {
        final override fun myMethod() {
            println("An overridden implementation that cannot be redefined further")
        }
    }

    val myIntermediate = MyIntermediateClass()

    myIntermediate.myMethod() // An overridden implementation that cannot be redefined further

    /// Error! Cannot override myMethod
    // class MyDerivedClass : MyIntermediateClass() {
    //     override fun myMethod() {
    //         println("I'm trying to override your method!")
    //     }
    // }
}

/**
 * Все члены класса — поля, методы и свойства — имеют модификаторы видимости.
 *
 * Модификаторы видимости позволяют установить допустимую область действия для членов класса. То есть они определяют контекст, в котором может использоваться данная переменная или метод.
 *
 * Модификаторы видимости — это специальные ключевые слова, которые определяют, какую часть вашего кода разрешено использовать.
 *
 * В Kotlin есть четыре модификатора доступа: private, protected, internal и public.
 *
 * - private — данные доступны только внутри класса;
 * - protected — то же, что и private, за исключением того, что данные можно увидеть в подклассах;
 * - internal -- тот, кто видит объявляющий класс, видит его внутренние члены;
 * - public — данные доступны везде.
 *
 * Имейте в виду, что для локальных переменных и функций нельзя установить модификатор видимости. Локальные переменные и функции доступны только внутри функции, в которой они определены.
 */
fun visibilityModifiersForMembers() {
    println("--- Public modifier ---")

    /**
     * Ключевое слово public используется, чтобы сообщить компилятору, что что-то должно быть доступно всем. Объявив экземпляр класса с модификатором public, вы можете ссылаться на любое из его полей в любом месте программы, где доступен сам объект.
     */
    // public class Student {
    //     public var name: String // Свойство общедоступно и видно везде
    //
    // }

    println("--- Private modifier ---")

    /**
     * Для защиты полей от подделки используется ключевое слово private — оно делает члены класса доступными только внутри самого класса. Теперь эти поля нельзя изменить нигде, кроме как в методах этого класса. Однако получить их значение извне также не получится, а попытка вывода приведёт к ошибке.
     *
     * Давайте усложним предыдущий пример, добавив модификатор private и новую переменную id. Если свойства задаются через конструктор, то модификатор видимости также можно указать в конструкторе для свойств:
     */
    class Student(val name: String, private val id: Int)

    val mark = Student("Mark", 1)

    println("Name: ${mark.name}") // Name: Mark
    // println("ID: ${mark.id}") // Cannot access 'id': it is private in 'Student'

    println("--- Protected modifier ---")

    /**
     * `protected` — это то же самое, что и private, за исключением того, что его можно увидеть в подклассах.
     */
    open class Person {
        protected open val name: String = ""
        private val age: Int = 0
    }

    class Student2 : Person() {
        // override val age = 18 // возраст является частным, и это НЕ будет работать
        override val name = "Eyad" // это сработает
    }

    class Teacher {
        private val person = Person()

        // fun printPerson(): String {
        //     return person.name // Cannot access 'name': it is protected in 'Person'
        // }
    }

    println("--- Internal modifier ---")

    /**
     * `internal` модификатор означает, что тот, кто видит объявляющий класс, видит его члены с модификатором internal:
     */
    class Bank {
        internal val accountNumber: Long = 5L
        internal fun getBranch(): String = "Branch is Alex"
    }

    class BankController {
        private val bank = Bank()

        fun getUserAccountNumber(): Long {
            return bank.accountNumber // same module
        }
    }

    println("--- Visibility of constructors in a class ---")

    /**
     * Вы также можете указать модификатор для конструкторов: например, сделать первичный конструктор класса закрытым. Не забудьте добавить явное ключевое слово constructor.
     *
     * В этом случае первичный конструктор является закрытым, поэтому он доступен только из того же класса (например, при вызове вторичного конструктора). Соответственно извне для создания объекта этого класса можно использовать только вторичный конструктор
     */
    class Student3 private constructor(val name: String) {
        var age: Int = 0

        constructor(name: String, _age: Int) : this(name) {
            age = _age
        }
    }

    // val anna = Student3("Anna") //Cannot access '<init>': it is private in 'Student'
    val mark2 = Student3("Mark", 23)

    println("Name: ${mark2.name}  Age: ${mark2.age}")

    println("--- Public and private functions ---")

    /**
     * Частные (private) функции используются для того, чтобы скрыть реализацию внутренней низкоуровневой логики от остального кода и сделать публичные функции более краткими и читабельными.
     *
     * Вот пример с функциями printInfo() и getAge(). Мы установили функцию getAge() как приватную, и эта функция недоступна извне. Тем временем функция printInfo() открыта, и мы можем получить информацию о студенте.
     */
    class Student4(
        private val name: String,
        private val id: Int,
        private val age: Int
    ) {

        fun printInfo() {
            println("Id: $id Name: $name")
        }

        private fun getAge() {
            print("Age: $age ")
        }
    }

    val anna = Student4("Anna", 9, 19)

    anna.printInfo() // Id: 9 Name: Anna
    // anna.getAge() // Cannot access 'getAge': it is private in 'Student'
}

/**
 * Вложенные классы помогают логически группировать классы и повышают инкапсуляцию нашего кода.
 *
 * Вы можете создать класс внутри другого класса, и такие классы называются вложенными.
 *
 * Родительский класс часто называют внешним классом, тогда как вложенный класс вместе со своими свойствами, функциями и конструкторами является членом внешнего класса.
 *
 * Обычный вложенный класс не может получить доступ к членам своего внешнего класса. Но вложенный класс, помеченный как inner класс, может.
 *
 * Внутренний класс может получить доступ ко всем членам своего внешнего класса.
 *
 * Чтобы получить доступ к внутреннему классу, вам нужно сначала создать экземпляр внешнего класса, так как внутренние классы связаны с экземпляром их внешнего класса.
 */
fun nestedClasses() {
    /**
     * Представьте, что вы пишете класс Cat, представляющий кошек. Кошки могут иметь множество свойств и функций, но мы также можем использовать внутренние структуры классов.
     *
     * Скажем, вы хотите, чтобы у кошки был бант из ленты; затем вам нужно создать новый класс Bow. Класс Bow должен быть довольно маленьким и специфическим, и вы знаете, что вам не понадобится Bow без Cat.
     *
     * Решение состоит в том, чтобы создать класс Bow внутри класса Cat.
     *
     * Вы можете столкнуться с внутренними классами, члены которых имеют те же имена, что и их внешние классы.
     *
     * Например, и Cat, и Bow могут иметь свойство color. Как вы можете получить доступ к членам внешнего класса из внутреннего класса в таких случаях?
     *
     * Квалифицированное выражение this поможет! Напишите this@Cat.color во внутреннем коде класса, и вы получите цвет внешнего класса, а использование color или this.color всегда даст вам свойство цвета текущего класса.
     */
    class Cat(val name: String, val color: String) {
        fun sayMeow() {
            println("$name says: \"Meow\".")
        }

        inner class Bow(val color: String) {
            fun printColor() {
                println("The cat named $name is ${this@Cat.color} and has a $color bow.")
            }

            fun putOnABow() {
                sayMeow()
            }
        }

        val catBow = Bow("Green")
    }

    val cat = Cat("Bob", "grey")
    val bow: Cat.Bow = cat.Bow("red")

    bow.printColor() // The cat named Bob is grey and has a red bow.
    cat.catBow.printColor() // The cat named Bob is grey and has a Green bow.

    bow.putOnABow() // Bob says: "Meow".
    cat.catBow.putOnABow() // Bob says: "Meow".
}

/**
 * Наследование классов
 */
fun inheritance() {
    /**
     * Если ваш код выглядит так, вы создаете окончательный класс. Это означает, что этот класс не будет доступен для наследования в будущем
     */
    class BookFinalClass(val pages: Int, val author: String)

    /**
     * Если вы действительно уверены, что вам нужно расширить класс Book (родительский класс), добавьте ключевое слово open
     */
    open class BookOpenClass(val pages: Int, val author: String)

    // Родительский класс, открытый для наследования
    open class Book(val pages: Int, val author: String, var cost: Float = 0F) {
        fun getFullInfo(): String = "$pages pages, $author author, $$cost cost"
    }

    // Наследование класса Book, создание дочернего класса без дополнительного функционала
    class Comics(pages: Int, author: String, cost: Float) : Book(pages, author, cost)

    val spidermanBook = Comics(113, "The Universe", 8.99F)

    println(spidermanBook.getFullInfo()) // 113 pages, The Universe author, $8.99 cost

    /**
     * Мы также можем добавить дополнительные функции в дочерние классы
     */
    class Booklet(pages: Int, cost: Float) : Book(pages, "", cost) {
        fun getUSDCost(): String = "$$cost cost"
        fun getEuroCost(): String = "€$cost cost"
    }

    val centralBooklet = Booklet(5, 0.14F)

    println(centralBooklet.getFullInfo()) // 5 pages,  author, $0.14 cost
    println(centralBooklet.getUSDCost()) // $0.14 cost
    println(centralBooklet.getEuroCost()) // €0.14 cost

    /**
     * Использование классов в функциях
     */
    fun isBigBook(book: Book): Boolean = book.pages >= 100

    println(isBigBook(spidermanBook)) // true
    println(isBigBook(centralBooklet)) // false

    /**
     * Производный класс может использовать преимущества нескольких конструкторов базового класса для создания собственной схемы с несколькими конструкторами.
     *
     * Базовый класс не имеет параметров конструктора, скобки необходимы для инициализации основного класса:
     */
    open class PrimaryConstructor

    class Fiction : PrimaryConstructor()

    /**
     * Когда базовый класс имеет параметры конструктора, производный класс должен позаботиться о них.
     *
     * Kotlin не позволит скомпилировать программу, если базовый класс не инициирован должным образом.
     */
    open class Journal(
        val title: String,
        val author: String = "Unknown",
        val genre: String = "Unknown",
        val isbn: Long = 0
    )

    /**
     * В классе ExtJournal есть новое свойство publisher, которое должно быть объявлено с помощью var или val. Все остальные параметры не являются новыми свойствами и используются для инициализации соответствующих свойств базового класса Journal.
     */
    class ExtJournal(
        val publisher: String = "Unknown",
        title: String,
        genre: String = "Unknown",
        author: String = "Unknown",
        isbn: Long = 0
    ) : Journal(title, author, genre, isbn)

    /**
     * Класс NoInfoJournal имеет только 2 параметра, которые используются для инициализации базового класса. Все остальные параметры базового класса принимают значения по умолчанию.
     */
    class NoInfoJournal(title: String, author: String = "Unknown") : Journal(title, author)

    /**
     * Класс FictionJournal имеет только 3 параметра, которые используются для инициализации 3 параметров базового класса. Четвёртый параметр genre явно задается в скобках Book.
     */
    class FictionJournal(
        title: String,
        author: String = "Unknown",
        isbn: Long = 0
    ) : Journal(title, author, genre = "fiction", isbn)

    /**
     * Базовый класс может иметь несколько конструкторов, которые могут включать первичный конструктор и множество вторичных. Производный класс может использовать один или несколько из них, чтобы инициировать базовый класс путем реализации нескольких конструкторов.
     */
    open class Base(val beta: Int, val gamma: Int, var message: String = "") {
        constructor(beta: Int, message: String = "") : this(beta, 0, message)
    }

    class Derived(val alpha: Int, beta: Int, gamma: Int, message: String = "") : Base(beta, gamma, message) {
        constructor(alpha: Int, beta: Int, message: String = "") : this(alpha, beta, 0, message)
    }

    Base(10) // beta is set
    Base(10, 20) // beta and gamma are set
    Base(10, 20, "My message") // beta, gamma, and a message are set
    Base(10, "My message") // beta and a message are set

    /**
     * Класс Derived объявляет тот же список параметров, что и класс Base, аналогичный вторичный конструктор, а также новое свойство с именем alpha. Таким образом, класс может быть инициирован теми же способами, что и базовый класс.
     */
    Derived(0, 10)
    Derived(0, 10, 20)
    Derived(0, 10, 20, "My message")
    Derived(0, 10, "My message")

    /**
     * Если мы хотим ограничить способы инициации нашего производного класса, нам следует в дальнейшем использовать вторичные конструкторы.
     *
     * В следующем примере мы явно определяем каждый возможный конструктор для класса Derived2, используя вторичные конструкторы. Класс Derived2 добавляет новое свойство с именем alpha, как и в предыдущем примере.
     *
     * Каждый вторичный конструктор вызывает конструктор базового класса с помощью ключевого слова super. Здесь у нас не может быть основного конструктора. Также обратите внимание на отсутствие круглых скобок после имен классов.
     *
     * Здесь мы определили 4 конструктора для соответствия различным конструкторам класса Base, но мы могли ограничить их только необходимыми. В случае ограничения конструкторов, нужно задать значение по умолчанию для alpha
     */
    class Derived2 : Base {
        val alpha: Int

        constructor(alphaConstr: Int, beta: Int) : super(beta) {
            alpha = alphaConstr
        }

        constructor(alphaConstr: Int, beta: Int, gamma: Int) : super(beta, gamma) {
            alpha = alphaConstr
        }

        constructor(alphaConstr: Int, beta: Int, gamma: Int, message: String) : super(beta, gamma, message) {
            alpha = alphaConstr
        }

        constructor(alphaConstr: Int, beta: Int, message: String) : super(beta, message = message) {
            alpha = alphaConstr
        }
    }

    Derived2(0, 10)
    Derived2(0, 10, 20)
    Derived2(0, 10, 20, "My message")
    Derived2(0, 10, "My message")

    /**
     * Если у класса есть первичный конструктор, несколько блоков инициализации и несколько вторичных конструкторов, то порядок выполнения следующий:
     *
     * - Первичный конструктор, даже если вызывается вторичный, вызывается первым через ключевое слово this;
     *
     * - Все блоки init последовательно в том порядке, в котором они появляются;
     *
     * - Вторичный конструктор, если этот конструктор был вызван.
     *
     * В случае наследования сначала инициируется базовый класс: либо путем вызова его первичного, либо вторичного конструктора через производный класс.
     *
     * Итак, порядок следования следующий:
     *
     * - Первичный конструктор базового класса, даже если вторичный конструктор базового класса вызывается через производный класс;
     *
     * - Блоки init базового класса последовательно в том порядке, в котором они появляются;
     *
     * - Блок вторичного конструктора базового класса, если этот конструктор был вызван;
     *
     * - Первичный конструктор производного класса, даже если вызывается вторичный конструктор производного класса;
     *
     * - Блоки инициализации производного класса, последовательно в порядке их появления;
     *
     * - Блок вторичного конструктора производного класса, если этот конструктор был вызван.
     */
    open class BaseFull(val message: String, val email: String) {
        init {
            println("Base class init")
        }

        constructor(email: String) : this("No message", email) {
            println("Base class secondary")
        }
    }

    class DerivedFull(email: String) : BaseFull(email) {
        init {
            println("Derived class init")
        }

        constructor() : this("example.com") {
            println("Derived class secondary")
        }
    }

    /**
     * Инициируется через вторичный конструктор, блоки выполняются в следующем порядке:
     * - BaseFull class init block;
     * - BaseFull class secondary block;
     * - DerivedFull class init block;
     * - DerivedFull class secondary block.
     */
    val myDerived = DerivedFull()

    // Base class init
    // Base class secondary
    // Derived class init
    // Derived class secondary
}

/**
 * Полиморфизм — это способность объекта или его методов принимать множество форм в зависимости от его типа и параметров того или иного метода.
 *
 * Механизм определения нескольких методов с одинаковым именем, но с разными параметрами называется перегрузкой (overloading).
 */
fun polymorphism() {}

/**
 * Переопределение функций-членов в наследуемых классах
 *
 * По умолчанию любая переопределённая функция открыта. Это означает, что вы также можете переопределять функции в дочерних классах.
 *
 * Кроме того, если вы хотите вызвать родительскую функцию, вы можете использовать super
 */
fun overriding() {
    open class Transport(val cost: Int) {
        open fun getFullInfo(): String = "$$cost cost"

        fun getTax(): String = "$${(cost * 0.25).roundToInt()} tax"
    }

    /**
     * Расширяем класс Transport собственной функцией getFullInfo().
     *
     * Если вы забудете ключевое слово override, компилятор предупредит вас, потому что не может быть двух функций getFullInfo() с одинаковыми параметрами.
     *
     * Вы также не можете переопределить функцию getTax(), потому что она не открыта.
     */
    open class Ship(cost: Int, val color: String) : Transport(cost) {
        override fun getFullInfo(): String = super.getFullInfo() + ", $color color"
    }

    val transport = Transport(1000)
    val ship = Ship(2000, "marine")

    println(transport.getFullInfo()) // $1000 cost
    println(ship.getFullInfo()) // $2000 cost, marine color

    /**
     * Есть еще одна полезная особенность, касающаяся открытых функций. Мы можем передавать открытые классы и любые потомки таких классов в функцию как параметры
     */
    fun getTransportInfo(transport: Transport): String = "transport info: " + transport.getFullInfo()

    println(getTransportInfo(transport)) // transport info: $1000 cost
    println(getTransportInfo(ship)) // transport info: $2000 cost, marine color
}

interface MyInterface {
    val msg: String

    fun print()
}

class MyImplementation : MyInterface {
    override val msg: String = "MyImplementation sends regards!"

    override fun print() {
        println(msg)
    }
}

interface ICallbackReceiver {
    fun onBeforeAction()
    fun onAfterAction()
    fun action(function: () -> Unit) {
        onBeforeAction()
        function()
        onAfterAction()
    }
}

interface ILogger {
    fun getStubDateTime() = "05.11.2022-14:31:04"

    val format: String
        get() = "[${getStubDateTime()}]: "

    fun print(message: String)
}

// Простая реализация интерфейса ILogger
class BasicLogger : ILogger {
    override fun print(message: String): Unit = println(format + message)
}

class ConsoleNotifier(logger: ILogger) : ICallbackReceiver, ILogger by logger {
    private val onBeforeStr = "OnBefore!"
    private val onAfterStr = "OnAfter!"

    override fun onBeforeAction() = print(onBeforeStr)
    override fun onAfterAction() = print(onAfterStr)
}

/**
 * Делегирование — это процесс использования определенного объекта вместо реализации
 */
fun delegation() {
    /**
     * Предположим, что нам нужно создать новый класс, который:
     * - будет иметь некоторую собственную функциональность
     * - одновременно будет реализовывать интерфейс MyInterface.
     *
     * Мы бы наткнулись на копирование кода: у нас уже есть реализация этого интерфейса, но нам нужен другой класс, который, однако, всё равно должен будет реализовывать этот интерфейс.
     *
     * Мы можем закодировать наш новый класс, а когда нам нужно использовать реализацию интерфейса, мы просто ссылаемся на уже существующую реализацию, а Котлин делает всё остальное.
     *
     * В примере ниже мы ожидаем реализацию MyInterface в качестве параметра (с именем «base»).
     * И при наследовании мы заявляем, что MyInterface реализуется ранее полученным параметром с именем «base».
     *
     * По сути, в конструкторе этого класса нам требуется что-то, что реализует интерфейс MyInterface, отмеченный двоеточием.
     * А затем, используя ключевое слово `by`, мы сообщаем производному классу, что всякий раз, когда его просят выполнить что-либо «обещанное» интерфейсом MyInterface, он будет использовать предоставленный объект.
     */
    class MyNewClass(base: MyInterface) : MyInterface by base {
        override val msg = "Delegate sends regards."
    }

    // Создаём экземпляр класса, реализующего MyInterface
    val implementation = MyImplementation()

    // Затем передаём этот экземпляр реализации в качестве параметра
    val delegate = MyNewClass(implementation)

    println(delegate.msg) // Delegate sends regards.

    // Сообщение и первичной реализации интерфейса, несмотря на переопределение при делегировании
    delegate.print() // MyImplementation sends regards!

    // Использование нескольких делегатов
    class ExampleParser(notifier: ICallbackReceiver, logger: BasicLogger) : ICallbackReceiver by notifier, ILogger by logger {
        fun start() = action {
            parseFiles()
        }

        fun parseFiles() {
            print("Parsing...")
        }
    }

    val loggerInstance = BasicLogger()
    val dateTimeNotifier = ConsoleNotifier(loggerInstance)

    val simpleParser = ExampleParser(dateTimeNotifier, loggerInstance)

    // [05.11.2022-14:31:04]: OnBefore!
    // [05.11.2022-14:31:04]: Parsing...
    // [05.11.2022-14:31:04]: OnAfter!
    simpleParser.start()
}

/**
 * Как сделать простой класс для хранения данных? Помимо хранения информации, он должен иметь возможность сравнивать и копировать объекты. Также было бы очень удобно сразу выводить данные. Обычно для этой функциональности класс должен иметь несколько методов: equals() и hashCode() для сравнения, copy() для копирования, toString() для строкового представления объекта и функции componentN(), соответствующие свойствам в порядке их объявления.
 *
 * Но в Котлине вам не нужно реализовывать все эти функции, вы можете просто использовать data class.
 */
fun dataClass() {
    class ClientStandard(val name: String, val age: Int, val gender: String)
    data class ClientData(val name: String, val age: Int, val gender: String)

    val standard = ClientStandard("Name", 42, "male")
    val data = ClientData("Name", 42, "male")
    val john = data.copy(name = "John")

    println(standard) // ObjectsKt$dataClass$ClientStandard@3941a79c

    println(data) /// ClientData(name=Name, age=42, gender=male)
    println(data.component1()) /// Name
    println(data.component2()) /// 42
    println(data.component3()) /// male

    println(john) /// ClientData(name=John, age=42, gender=male)

    // destructuring
    val (name, age, gender) = john

    println(name) // John
    println(age)  // 42
    println(gender) // male

    data class Some(var age: Int) {
        val name = "Name"

        fun printName() {
            println(this.name)
        }
    }

    val some = Some(13)

    println(some.age)

    some.age = 30

    println(some.age)
    println(some.name)

    some.printName()
}

/**
 * Геттеры и сеттеры.
 *
 * Позволяют сделать поле объекта только для чтения, реализовать проверку перед назначением значения, реализовать более сложную логику при получении и изменении данных
 *
 * Ключевое слово field позволяет брать и устанавливать значение у текущего поля
 */
fun gettersAndSetters() {
    class Client {
        var name: String = "Unknown"
            get() {
                println("Somebody wants to know $field name")

                return field
            }

            set(value) {
                println("The name is changing. Old value is $field. New value is $value.")

                field = value
            }
    }

    val client = Client()

    // Somebody wants to know Unknown name
    // Unknown
    println(client.name)

    // The name is changing. Old value is Unknown. New value is Ann.
    client.name = "Ann"

    // Somebody wants to know Ann name
    // Ann
    println(client.name)

    class Client2(age: Int) {
        private val defaultAge = 18

        var age: Int = age
            set(value) {
                println("Call setter")

                field = if (value in 0..120) value else defaultAge
            }
    }

    val client2 = Client2(-23)

    // Сеттер не вызвался при инициализации конструктора
    println(client2.age) // -23

    // Call setter
    client2.age = 23

    println(client2.age) // 23

    // Call setter
    client2.age = -12

    println(client2.age) // 18

    /**
     * Задействование проверки при инициализации конструктора
     */
    class Client3(age: Int) {
        private val defaultAge = 18

        var age: Int = validateAge(age)
            set(value) {
                println("Call setter")

                field = validateAge(value)
            }

        private fun validateAge(age: Int): Int {
            return if (age in 0..120) age else defaultAge
        }
    }

    val client3 = Client3(-23)

    // Сеттер не вызвался при инициализации конструктора
    println(client3.age) // 18

    // Call setter
    client3.age = 23

    println(client3.age) // 23

    // Call setter
    client3.age = -12

    println(client3.age) // 18

    /**
     * Если поле только для чтения
     */
    class Client4(age: Int) {
        private val defaultAge = 18

        val age: Int

        init {
            this.age = if (age in 0..120) age else defaultAge
        }
    }

    val clientA = Client4(-23)

    println(clientA.age) // 18

    val clientB = Client4(23)

    println(clientB.age) // 23
}

/**
 * В Kotlin класс описывает общую структуру, экземпляр которой можно создавать несколько раз и разными способами. Иногда нам нужен только один экземпляр, ни больше, ни меньше. Это может помочь вам организовать вашу кодовую базу и собрать вместе похожие методы.
 *
 * Котлин предоставляет специальную структуру для объявления синглтона: объявление объекта (object declaration). Это специальный класс с ключевым словом object, создающим синглтон.
 */
class Player

object PlayingField {
    val list = arrayOf(Player(), Player())

    fun getAllPlayers(): Array<Player> = list
    fun isPlayerInGame(player: Player): Boolean = false
}

class SomePlayer(val id: Int) {
    object Properties {
        const val DEFAULT_SPEED = 7

        fun calcMovePenalty(cell: Int): Int = 42
    }
}

class AnyPlayer(val id: Int) {
    object Properties {
        val defaultSpeed = 7

        fun calcMovePenalty(cell: Int): Int = cell * 2 * defaultSpeed
    }

    object Factory {
        fun create(playerId: Int): AnyPlayer = AnyPlayer(playerId)
    }
}

fun objectDeclarations() {
    val players = PlayingField.getAllPlayers()

    if (players.size < 2) {
        return println("The game cannot be continued without players")
    }

    for (player in players) {
        if (!PlayingField.isPlayerInGame(player)) {
            println("Current player lost. Next...")
        }
    }

    println(SomePlayer.Properties.DEFAULT_SPEED) // 7
    println(SomePlayer.Properties.calcMovePenalty(13)) // 42

    println(AnyPlayer.Properties.defaultSpeed) // 7
    println(AnyPlayer.Factory.create(13).id) // 13
}

/**
 * Объект-компаньон (companion object) — это синглтон, прикрепленный к внешнему классу, и, следовательно, к нему невозможно получить доступ без доступа к внешнему классу.
 *
 * Это позволяет нам понять, что текущий объект каким-то образом связан с внешним классом.
 *
 * Например, мы можем сохранить скорость по умолчанию для всех игроков в объекте-компаньоне Player. Это также означает, что каждый экземпляр Player содержит ссылку на сопутствующий объект и каждый раз будет возвращать его экземпляр.
 */
class CompanionPlayer(val id: Int) {
    companion object {
        val defaultSpeed = 7

        fun calcMovePenalty(cell: Int): Int = cell * 2 * defaultSpeed
    }
}

/**
 * Companion действительно тесно связан с внешним классом. Вы можете свободно использовать свойства и функции объекта companion во внешнем классе.
 */
class Deck1 {
    companion object {
        val size = 10
        val height = 2

        fun volume(bottom: Int, height: Int) = bottom * height
    }

    val square = size * size // 100
    val volume = volume(square, height) // 200
}

/**
 * Но что произойдет, если у внешнего класса есть свойство с тем же именем, что и у сопутствующего объекта? Что ж, в этом случае свойства класса будут затмевать свойства компаньона.
 */
class Deck2 {
    companion object {
        val size = 10
    }

    val size = 2
    val square = size * size // 4
}

/**
 * В этом случае, если вы хотите использовать свойство компаньона, вы должны использовать имя компаньона или, если оно не было названо, имя по умолчанию Companion:
 */
class Deck3 {
    companion object {
        val size = 10
    }

    val size = 2
    val square = Companion.size * Companion.size // 100
}

class CompanionPlayer2(val id: Int) {
    companion object Properties {
        val defaultSpeed = 7
        fun calcMovePenalty(cell: Int): Int = cell * defaultSpeed
    }

    object Factory {
        fun create(playerId: Int): CompanionPlayer2 = CompanionPlayer2(playerId)
    }
}


/**
 * Для каждого класса доступен только один объект-компаньон. Это означает, что вы не можете создать несколько сопутствующих объектов для класса, поскольку Kotlin не поддерживает такое поведение, даже если у них разные имена.
 *
 * Есть еще одно ограничение: мы не можем создать объект-компаньон внутри другого синглтона (или объекта-компаньона), поскольку это нарушает принцип глобального доступа.
 *
 * Как видите, когда вы используете собъект-компаньон, вам также не нужно создавать экземпляр класса, чтобы получить эту функцию или поле!
 *
 * В Котлине это один вложенный объект, который содержит все методы и поля, общие для всего класса.
 */
fun companionObject() {
    /**
     * Как видите, если мы опустим имя сопутствующего объекта, мы все равно сможем получить к нему доступ через объявление внешнего класса. Если мы хотим его как-то использовать, мы можем определить его вручную.
     */
    println(CompanionPlayer.defaultSpeed) // 7

    /**
     * Если у объекта-компаньона нет имени, мы также можем использовать имя по умолчанию Companion:
     */
    println(CompanionPlayer.Companion.defaultSpeed) // 7

    println(CompanionPlayer2.Properties.defaultSpeed) // 7
    println(CompanionPlayer2.defaultSpeed) // 7
    println(CompanionPlayer2.Factory.create(13).id) // 13
}

/**
 * Считывает три числа, представляющие температуру в Дубае, Москве и Ханое.
 *
 * Печатает название самого холодного города.
 *
 * Если введенное число меньше -92 или больше 57, для температуры будет установлено значение по умолчанию, которое представляет собой среднюю температуру в городе:
 * +5 для Москвы,
 * +20 для Ханоя
 * +30 для Дубая.
 *
 * Если самая низкая температура наблюдается более чем в одном городе, результат будет «neither».
 */
fun weatherComparison() {
    class City(val name: String) {
        var degrees: Int = 0
            set(value) {
                field = validateDegrees(value)
            }

        private fun validateDegrees(degrees: Int): Int {
            if (degrees in -92..57) {
                return degrees
            }

            return when (name) {
                "Moscow" -> 5
                "Dubai" -> 30
                "Hanoi" -> 20
                else -> 0
            }
        }
    }

    val dubai = City("Dubai")

    dubai.degrees = 20

    val moscow = City("Moscow")

    moscow.degrees = 100

    val hanoi = City("Hanoi")

    hanoi.degrees = 35

    val cities: List<City> = listOf(dubai, moscow, hanoi).sortedBy { it.degrees }

    // Moscow
    if (cities[0].degrees == cities[1].degrees) {
        println("neither")
    } else {
        println(cities.first().name)
    }
}

class Table(rows: Int, columns: Int) {
    val html: String

    init {
        val columnTags: String = createTag("td", columns)
        val rowTags: String = createTag("tr", rows, columnTags)
        val tableTags: String = createTag("table", 1, rowTags)

        html = tableTags
    }

    private fun createTag(name: String, count: Int, children: String = ""): String {
        return "<$name>$children</$name>".repeat(count)
    }
}

fun createTable() {
    val table = Table(1, 2)

    println(table.html) // <table><tr><td></td><td></td></tr></table>
}

/**
 * Создайте класс с именем Animal с открытым методом makeSound(), который отображает сообщение «The animal makes a sound». Затем создайте подкласс Dog, который переопределяет этот метод, отображая сообщение «The dog barks».
 */
fun animalSounds() {
    open class Animal {
        open fun makeSound() {
            println("The animal makes a sound")
        }
    }

    class Dog : Animal() {
        override fun makeSound() {
            println("The dog barks")
        }
    }

    val anyAnimal = Animal()
    val myDog = Dog()

    anyAnimal.makeSound() // The animal makes a sound
    myDog.makeSound() // The dog barks
}

/**
 * Деструктуризация
 *
 * В объявлении деструктуризации используется оператор componentN(), который возвращает n-й элемент класса.
 *
 * В коллекциях и data классах уже реализованы операторы componentN(), в своих классах необходимо их реализовывать самостоятельно.
 */
fun destructuring() {
    // Деструктуризация data класса
    data class UserData(val name: String, val age: Int, val isAdmin: Boolean)

    val anonym = UserData("Anonym", 999, false)

    /// val userName = anonym.component1()
    /// val userAge = anonym.component2()
    /// val isAdmin = anonym.component3()
    val (userName, userAge, admin) = anonym

    println(userName) // Anonym
    println(userAge) // 999
    println(admin) // false

    // Деструктуризация не data класса.
    // Нужно добавить операторы componentN() для деструктурируемых полей
    class UserBase(val name: String, val age: Int, val isAdmin: Boolean){
        operator fun component1(): String = name
        operator fun component2(): Int = age
        operator fun component3(): Boolean = isAdmin
    }

    val (name, age, isAdmin) = UserBase("Any user", 42, false)

    println(name) // Any user
    println(age) // 42
    println(isAdmin) // false
}

/**
 * Хэш-функция — это специальная функция, которую можно использовать для сопоставления данных любого размера со значениями фиксированного размера. Значения, возвращаемые хэш-функцией, называются хеш-значениями, хеш-кодами, дайджестами или просто хэшами.
 *
 * Одним из фундаментальных свойств хеш-функций является то, что они всегда возвращают одно и то же значение для одного и того же контента или входной информации.
 *
 * В Kotlin hashCode — это 32-битный идентификатор, который хранится в хеш-таблице экземпляра класса.
 *
 * Каждый класс должен предоставлять метод hashCode(), который позволяет получить хэш-код, назначенный по умолчанию классом Any.
 *
 * Все классы наследуют класс Any для хеширования, хотя обычно его переопределяют, чтобы получить хеш-функцию, которая более конкретно обрабатывает содержащиеся данные.
 */
fun hashCode() {
    println("-- Numbers hash code --")

    val intValue = 32
    val intValue2 = 64
    val intValue3 = 32

    println(intValue.hashCode()) // 32
    println(intValue2.hashCode()) // 64
    println(intValue3.hashCode()) // 32

    println(intValue.hashCode() == intValue2.hashCode()) // false
    println(intValue.hashCode() == intValue3.hashCode()) // true

    println("-- Strings hash code --")

    val stringValue = "Hello"
    val stringValue2 = "Hello"
    val stringValue3 = "Hello World"

    println(stringValue.hashCode()) // 69609650
    println(stringValue2.hashCode()) // 69609650
    println(stringValue3.hashCode()) // -862545276

    println(stringValue.hashCode() == stringValue2.hashCode()) // true
    println(stringValue.hashCode() == stringValue3.hashCode()) // false

    println("-- Classes hash code --")

    class PersonDefault(val name: String, val age: Int)

    data class PersonData(val name: String, val age: Int)

    class Person(val name: String, val age: Int) {
        override fun hashCode(): Int = 31 * name.hashCode() + age.hashCode()
    }

    val personDefault = PersonDefault("John", 32)
    val personData = PersonData("John", 32)
    val person1 = Person("John", 32)
    val person2 = Person("John", 32)
    val person3 = Person("John", 64)

    println(personDefault.hashCode()) // 1349393271
    println(personData.hashCode()) // 71750741
    println(person1.hashCode()) // 71750741
    println(person2.hashCode()) // 71750741
    println(person3.hashCode()) // 71750773

    println(person1.hashCode() == person2.hashCode()) // true
    println(person1.hashCode() == person3.hashCode()) // false
}

/**
 * Специальный метод equals() сравнивает два объекта и возвращает true, если они равны.
 *
 * Этот метод существует по умолчанию для всех объектов и определен в Any, поэтому мы должны переопределить его, чтобы адаптировать к природе и определению наших классов.
 */
fun equals() {
    println("-- Numbers equals --")

    val intValue = 32
    val intValue2 = 64
    val intValue3 = 32

    println(intValue.equals(intValue2)) // false
    println(intValue.equals(intValue3)) // true
    println(intValue == intValue2) // false
    println(intValue == intValue3) // true

    println("-- Strings equals --")

    val stringValue = "Hello"
    val stringValue2 = "Hello"
    val stringValue3 = "Hello World"

    println(stringValue.equals(stringValue2)) // true
    println(stringValue.equals(stringValue3)) // false
    println(stringValue == stringValue2) // true
    println(stringValue == stringValue3) // false

    println("-- Classes equals --")

    class PersonDefault(val name: String, val age: Int)

    data class PersonData(val name: String, val age: Int)

    class Person(val name: String, val age: Int) {
        override fun equals(other: Any?): Boolean = when {
            this === other -> true
            other !is Person -> false
            name != other.name -> false
            else -> age == other.age
        }
    }

    val personDefault = PersonDefault("John", 32)
    val personData = PersonData("John", 32)
    val person1 = Person("John", 32)
    val person2 = Person("John", 32)
    val person3 = Person("John", 64)

    println(personDefault.equals(personData)) // false
    println(personDefault.equals(person1)) // false
    println(personData.equals(person1)) // false
    println(person1.equals(person2)) // true
    println(person1.equals(person3)) // false
    println(person1 == person2) // true
    println(person1 == person3) // false
}

open class Person {
    fun whoAmI(name: String): String = "I am $name"
}

sealed class Staff1 : Person() {
    class Teacher(val numberOfLessons: Int) : Staff1()
    class Manager(val responsibility: String) : Staff1()
    data object Worker : Staff1()
}

sealed class Staff2 {
    class Teacher(val numberOfLessons: Int) : Staff2()
    class Manager(val responsibility: String) : Staff2()
    data object Worker : Staff2()
}

/**
 * Контейнер для фиксированных подклассов: sealed class или sealed interface.
 *
 * - `sealed class CustomError`
 * - `sealed interface CustomErrors`
 *
 * Класс или интерфейс sealed является абстрактным, поэтому его экземпляр не может быть создан.
 *
 * Следующий код выдаст ошибку:
 * - val customError = CustomError()
 *
 * Как и в случае с обычными классами, вы можете объявлять конструкторы, но конструкторы в sealed классе должны быть private или protected
 *
 * `sealed class CustomError {
 *     constructor(type: String) {} // protected (default)
 *     private constructor(type: String, code: Int) {} // private
 *     public constructor() {} // Public gives error
 * }`
 *
 * Вы также можете использовать первичный конструктор, как и в любом обычном классе.
 *
 * `sealed class CustomError(type: String)`
 */
fun sealed() {
    val worker1 = Staff1.Worker

    println(worker1.whoAmI("Worker")) // I am Worker

    fun listTheTasks(staff: Staff2) = when (staff) {
        is Staff2.Teacher -> println("The teacher has ${staff.numberOfLessons} lessons today")
        is Staff2.Manager -> println("The manager is doing ${staff.responsibility} today")
        Staff2.Worker -> println("Worker is fixing the projector for profs in CS, all respect to him.")
    }

    val teacher = Staff2.Teacher(3)
    val worker = Staff2.Worker

    listTheTasks(teacher) // The teacher has 3 lessons today
    listTheTasks(worker) // Worker is fixing the projector for profs in CS, all respect to him.
}

interface ShapeInterface {
    fun calculateArea(): Double
    fun calculatePerimeter(): Double
}

/**
 * Абстрактный класс — это класс, экземпляр которого не может быть создан напрямую, но служит основой для других классов. Он действует как частично реализованный класс, обеспечивая общую структуру и поведение, которые подклассы могут наследовать и развивать.
 *
 * - abstract class Animal
 * - abstract class Animal(val id: Int)
 *
 * Абстрактный класс может иметь как абстрактные, так и неабстрактные члены (свойства и методы). Чтобы объявить член абстрактным, вы должны явно использовать ключевое слово abstract.
 *
 * Обратите внимание, что абстрактный член не имеет тела (реализации) в своем классе.
 *
 * `abstract class Animal(val id: Int) {
 *     abstract val name: String
 *
 *     val age: Int = 7
 *
 *     abstract fun makeSound()
 *
 *     fun isSleeping(): Boolean = false
 * }`
 *
 * По умолчанию абстрактные классы в Kotlin открыты для расширения, а их абстрактные методы и свойства открыты для переопределения.
 *
 * Различия между абстрактным классом и интерфейсом:
 *
 * - Создание экземпляра
 *
 * Abstract. Не может быть создан напрямую. Предназначен служить основой для наследования подклассов.
 *
 * Interface. Не может быть создан напрямую. Определяет контракт методов и свойств, которому должны соответствовать реализующие классы.
 *
 * - Конструкторы
 *
 * Abstract. Может иметь конструкторы, включая как первичные, так и вторичные. Подклассы отвечают за вызов соответствующего конструктора суперкласса.
 *
 * Interface. Не может быть конструкторов. Они только объявляют методы и свойства без какой-либо реализации.
 *
 * - Состояние
 *
 * Abstract. Может иметь переменные-члены и неабстрактные методы с реализациями по умолчанию. Может хранить состояние и поддерживать внутренние данные.
 *
 * Interface. Не может хранить состояние или определять переменные-члены. Сосредоточен исключительно на объявлении поведения.
 *
 * - Наследование
 *
 * Abstract. Подклассы могут расширять только один абстрактный класс. В Kotlin наследование классов ограничено одним классом, а абстрактные классы позволяют установить иерархию наследования.
 *
 * Interface. Реализация классов может реализовывать несколько интерфейсов. Kotlin поддерживает множественное наследование через интерфейсы, позволяя классам реализовывать несколько интерфейсов одновременно.
 *
 * - Абстрактные и неабстрактные члены
 *
 * Abstract. Может иметь как абстрактные, так и неабстрактные методы и свойства. Подклассы должны предоставлять реализации абстрактных членов, наследуя при этом неабстрактные члены.
 *
 * Interface. Может объявлять абстрактные методы или методы, имеющие реализации по умолчанию. Оба типа методов могут быть переопределены путём реализации классов.
 *
 * При выборе между абстрактными классами и интерфейсами учитывайте следующие рекомендации:
 *
 * - Используйте абстрактные классы, когда вы хотите предоставить реализацию по умолчанию или когда вам нужно поддерживать внутреннее состояние внутри базового класса.
 *
 * - Используйте интерфейсы, когда вы хотите определить контракт поведения, который могут реализовать несколько несвязанных классов, или когда вам нужно добиться множественного наследования.
 */
fun abstractClasses() {
    abstract class Animal {
        abstract fun move()
        abstract fun makeSound()

        fun eat(): Boolean = false
        fun sleep(): Boolean = false
    }

    class Cat : Animal() {
        override fun move() {
           println("Walk")
        }

        override fun makeSound() {
            println("Meow")
        }
    }

    val cat: Animal = Cat()

    cat.move() // Walk
    cat.makeSound() // Meow

    println(cat.eat()) // false
    println(cat.sleep()) // false

    /**
     * В Kotlin также возможно наследовать абстрактный класс от класса open, переопределяя неабстрактный открытый член абстрактным, используя два ключевых слова: abstract override.
     */
    open class Polygon {
        open fun draw() {
            // Some default polygon drawing method
        }
    }

    abstract class WildShape : Polygon() {
        // Classes that inherit WildShape need to provide their own draw method instead of using the default on Polygon
        abstract override fun draw()
    }

    /**
     * В Kotlin можно использовать абстрактные классы и интерфейсы вместе
     */
    abstract class AbstractShape : ShapeInterface {
        // Common behavior or properties for shapes can be implemented here
    }

    class Rectangle(private val width: Double, private val height: Double) : AbstractShape() {
        override fun calculateArea(): Double = width * height
        override fun calculatePerimeter(): Double = 2 * (width + height)
    }

    class Circle(private val radius: Double) : AbstractShape() {
        override fun calculateArea(): Double = Math.PI * radius * radius
        override fun calculatePerimeter(): Double = 2 * Math.PI * radius
    }
}

/**
 * В Kotlin аннотация @DslMarker используется для определения интерфейса маркера DSL или аннотации. Он позволяет указать маркер, который указывает область действия DSL и помогает применять правила области действия в рамках DSL. При создании DSL часто желательно ограничить доступность определённых функций или компоновщиков в определённых областях. Аннотация @DslMarker позволяет определить интерфейс маркера или аннотацию, которая служит сигналом для компилятора и других разработчиков о том, что определённые функции или компоновщики предназначены для использования только в определённой области DSL.
 */
@DslMarker
annotation class TreeNodeDslMarker

@TreeNodeDslMarker
data class TreeNode(val value: String) {
    val children: MutableList<TreeNode> = mutableListOf()

    fun addChild(child: TreeNode) {
        children.add(child)
    }
}

class TreeNodeBuilder {
    private val root = TreeNode("")
    private var currentNode: TreeNode = root

    fun value(value: String) {
        currentNode = TreeNode(value)

        root.addChild(currentNode)
    }

    fun child(block: TreeNodeBuilder.() -> Unit) {
        val childBuilder = TreeNodeBuilder()

        childBuilder.block()

        currentNode.addChild(childBuilder.build())
    }

    fun build(): TreeNode = root
}

fun buildTree(block: TreeNodeBuilder.() -> Unit): TreeNode {
    val builder = TreeNodeBuilder()

    builder.block()

    return builder.build()
}

fun printTree(node: TreeNode, level: Int = 0) {
    val indentation = "  ".repeat(level)

    println("$indentation${node.value}")

    for (child in node.children) {
        printTree(child, level + 1)
    }
}

/**
 * Шаблоны построителя для реализации DSL (предметно-ориентированного языка) и создания типового построителя для нашего кода.
 *
 * Паттерн строителя — это творческий паттерн проектирования, который позволяет шаг за шагом создавать сложные объекты. Он предоставляет способ создания объектов путём отделения логики построения от представления, что приводит к более читабельному и удобному для сопровождения коду.
 *
 * Традиционный шаблон построителя обычно реализуется с использованием комбинации класса, представляющего объект, который нужно построить, и отдельного класса Builder. Класс Builder предоставляет методы для установки свойств создаваемого объекта, что позволяет осуществлять плавный и настраиваемый процесс построения. Паттерн построителя помогает повысить удобочитаемость и гибкость построения объектов, предоставляя чёткий и настраиваемый способ создания сложных объектов, избегая необходимости в нескольких конструкторах или больших списках параметров.
 */
fun typeSafeBuilders() {
    data class Person(
        val firstName: String,
        val lastName: String,
        val age: Int,
        val address: String
    )

    data class PersonBuilder(
        var firstName: String = "",
        var lastName: String = "",
        var age: Int = 0,
        var address: String = ""
    )

    fun personBuilder(init: PersonBuilder.() -> Unit): Person {
        val builder = PersonBuilder()

        builder.init()

        return Person(builder.firstName, builder.lastName, builder.age, builder.address)
    }

    val person: Person = personBuilder {
        firstName = "John"
        lastName = "Doe"
        age = 30
        address = "123 Main St"
    }

    println("Person builder: $person")
    println("Person first name: ${person.firstName}")
    println("Person last name: ${person.lastName}")
    println("Person age: ${person.age}")
    println("Person address: ${person.address}")

    fun buildString(action: (StringBuilder).() -> Unit): String {
        val stringBuilder = StringBuilder()

        action(stringBuilder)

        return stringBuilder.toString()
    }

    // I Love learning Kotlin with Hyperskill
    println(buildString {
        append("I Love ")
        append("learning Kotlin")
        append(" with Hyperskill")
    })

    /**
     * В качестве примера мы кодируем древовидную структуру и элементы в иерархической структуре.
     *
     * Типобезопасный построитель TreeNodeBuilder предоставляет такие методы, как value и child, которые можно связать вместе для построения древовидной структуры. Это позволяет построить дерево декларативным и удобочитаемым способом. В итоге вы получите объект дерева, представляющий структуру данных дерева.
     *
     * Функция buildTree вне класса TreeNode используется для инициации процесса построения путём получения лямбда-выражения с получателем (TreeNodeBuilder.() -> Unit). Внутри класса TreeNodeBuilder значения и дочерние функции определены для настройки и построения древовидной структуры. Функция значения в классе TreeNodeBuilder используется для установки значения текущего узла. Он создаёт новый TreeNode с указанным значением и добавляет его как дочерний к корневому узлу. Дочерняя функция в классе TreeNodeBuilder используется для добавления дочернего узла к текущему узлу. Он принимает лямбду с приёмником (TreeNodeBuilder.() -> Unit), который определяет конфигурацию дочернего узла. Внутри лямбды создаётся новый TreeNodeBuilder, и лямбда-блок выполняется в рамках дочернего построителя. Полученный дочерний узел затем добавляется в список дочерних элементов текущего узла.
     */
    val tree = buildTree {
        value("Root")
        child {
            value("Child 1")
            child {
                value("Grandchild 1.1")
            }
            child {
                value("Grandchild 1.2")
            }
        }
        child {
            value("Child 2")
            child {
                value("Grandchild 2.1")
            }
        }
    }

    printTree(tree)
}

sealed class Shape {
        data class Rectangle(val width: Double, val height: Double) : Shape()
        data class Square(val side: Double) : Shape()
        data class Circle(val radius: Double) : Shape()
        data class Triangle(val base: Double, val height: Double) : Shape()
        data class Pentagon(val side: Double) : Shape()
}

fun calculateArea(shape: Shape): Double = when (shape) {
    is Shape.Rectangle -> shape.width * shape.height
    is Shape.Square -> shape.side * shape.side
    is Shape.Circle -> Math.PI * shape.radius * shape.radius
    is Shape.Triangle -> (shape.base * shape.height) / 2
    is Shape.Pentagon -> 1 / 4.0 * Math.sqrt(5 * (5 + 2 * Math.sqrt(5.0))) * shape.side * shape.side
}

/**
 * Есть sealed класс Shape. Объявите функцию calculationArea(), которая принимает sealed класс в качестве параметра и возвращает площадь с типом Double.
 */
fun shapeArea() {
    val rectangle = Shape.Rectangle(4.0, 2.5)

    println("Rectangle area: ${calculateArea(rectangle)}") // 10.0

    val square = Shape.Square(2.5)

    println("Square area: ${calculateArea(square)}") // 6.25

    val circle = Shape.Circle(2.5)

    println("Circle area: ${calculateArea(circle)}") // 19.634954084936208

    val triangle = Shape.Triangle(4.0, 2.5)

    println("Triangle area: ${calculateArea(triangle)}") // 5.0

    val pentagon = Shape.Pentagon(2.5)

    println("Pentagon area: ${calculateArea(pentagon)}") // 10.752983753681043
}
