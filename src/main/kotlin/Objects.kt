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

    createTable()
    animalSounds()

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
