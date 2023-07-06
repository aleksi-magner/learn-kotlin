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

    /**
     * Копирование по ссылке
     *
     * Значения переменных ссылаются на значения по ссылкам.
     *
     * Например:
     * val msg1 = "Hi"
     * val msg2 = msg1
     *
     * И msg1, и msg2 ссылаются на одно и то же место в памяти, где хранится значение "Hi".
     * Таким образом, оператор присвоения копирует не сам объект, а ссылку на него.
     *
     * Мутабельность
     *
     * Что произойдёт при изменении объекта у одной переменной, изменится ли она для другой?
     * Примитивные типы данных, такие как Int, String, Float и Double являются не изменяемыми объектами.
     * Мы не меняем её объект — переменной присваивается новая ссылка на объект с новым значением.
     */

    /**
     * Создание нового класса
     */
    class Emptiness {
        // empty body
    }

    /**
     * В Kotlin, когда класс имеет пустое тело, фигурные скобки можно опустить.
     * Таким образом, один и тот же класс можно определить следующим образом:
     *
     * class Emptiness
     *
     * Обычно лучше объявить их на верхнем уровне файла, но вы можете объявить их и в других местах, даже внутри функции.
     */

    /**
     * Создание объекта
     */
    // Создаём экземпляр класса
    val empty = Emptiness()

    /**
     * Запись свойств класса
     *
     * Свойства очень похожи на переменные и значения.
     * Если вы хотите присвоить значение свойству во время выполнения, вы объявляете его как var, в противном случае ваш выбор — val.
     */
    class Patient {
        var name: String = "Unknown"
        var age: Int = 0
        var height: Double = 0.0
    }

    /**
     * Каждый объект класса имеет одинаковый набор полей, но значения полей могут отличаться от объекта к объекту.
     */

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

    println("${john.name}: ${john.age} yrs, ${john.height} cm")
    println("${alice.name}: ${alice.age} yrs, ${alice.height} cm")

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

    println("Main OS: ${mainOS.name}")
    println("Secondary OS: ${secondaryOS.name}")

    /**
     * Сравнение. Структурное равенство
     */
    println("--- Comparison ---")

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

    println("Blue box (before): ${blueBox.amount} balls")
    println("Azure box (before): ${azureBox.amount} balls")

    println(blueBox == azureBox) // true, это копия

    blueBox.addBall() // добавить мяч в первую коробку

    println(blueBox == azureBox) // true, во второй коробке тоже 4 мяча

    println("Blue box (after): ${blueBox.amount} balls")
    println("Azure box (after): ${azureBox.amount} balls")

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

    println("redBox (${redBox.amount}) == cyanBox (${cyanBox.amount}): ${redBox == cyanBox}")   // true
    println("redBox (${redBox.amount}) === cyanBox (${cyanBox.amount}): ${redBox === cyanBox}")  // false, cyanBox указывает на другой объект

    /**
     * Итак, blueBox и cyanBox имеют одинаковое состояние, но указывают на разные объекты.
     * В этом случае, если вы измените состояние blueBox, cyanBox останется прежним
     */
    redBox.addBall()

    println("redBox (${redBox.amount}) == cyanBox (${cyanBox.amount}): ${redBox == cyanBox}")   // true
    println("redBox (${redBox.amount}) === cyanBox (${cyanBox.amount}): ${redBox === cyanBox}")  // false, cyanBox указывает на другой объект

    println("--- MutableList ---")

    val list1: MutableList<Int> = mutableListOf()
    val list2: MutableList<Int> = list1

    list1.add(1)

    println("list1 $list1") // list1 [1]
    println("list2 $list2") // list2 [1]

    list2.add(5)

    println("list1 $list1") // list1 [1, 5]
    println("list2 $list2") // list2 [1, 5]
}
