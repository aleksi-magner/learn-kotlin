/**
 * Kotlin изначально был разработан со встроенной поддержкой многопоточности. Потоки поддерживаются на уровне JVM, уровне языка (специальные ключевые слова) и уровне стандартной библиотеки.
 *
 * В каждой программе Kotlin есть хотя бы один поток, который называется основным; он создаётся автоматически процессом JVM для выполнения операторов внутри функции main().
 *
 * В любой программе на Kotlin есть и другие потоки по умолчанию (например, отдельный поток для сборщика мусора).
 *
 * Поток — это последовательность инструкций, которые программа может выполнить во время своего запуска. Каждый поток представлен объектом — экземпляром класса java.lang.Thread (или его подкласса).
 *
 * Класс Thread имеет множество конструкторов. Полный список вы можете найти здесь: https://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html#constructor_summary
 *
 * Разница между режимом демона и режимом без демона заключается в том, что JVM не будет завершать работающую программу, пока ещё остаются потоки, не являющиеся демонами, в то время как потоки демона не препятствуют завершению работы JVM. Потоки-демоны обычно выполняют некоторую фоновую работу.
 */
fun main() {
    /**
     * Класс Thread имеет статический метод с именем currentThread для получения ссылки на выполняющийся в данный момент объект потока:
     */
    val thread: Thread = Thread.currentThread()

    /**
     * Класс Thread хранит базовую информацию о потоке: его имя, идентификатор (длинный), приоритет и некоторые другие характеристики, которые можно получить с помощью его функций.
     */
    // Возвращает имя потока
    println("Name: ${thread.name}") // main

    // Возвращает уникальный идентификатор потока
    println("ID: ${thread.threadId()}") // 1

    // Сообщает нам, был ли поток запущен и ещё не умер
    println("Alive: ${thread.isAlive}") // true

    // Возвращает приоритет данного потока.
    // Каждый поток имеет приоритет, определяющий порядок выполнения: потоки с более высоким приоритетом выполняются раньше потоков с более низким приоритетом
    println("Priority: ${thread.priority}") // 5

    // Проверяет, является ли поток демоном.
    // Поток демона (в терминологии UNIX) — это поток с низким приоритетом, который работает в фоновом режиме для выполнения таких задач, как сбор мусора и т.д.
    // JVM не ждёт остановки потоков демона перед выходом, в то время как она делает это для потоков, не являющихся демонами.
    println("Daemon: ${thread.isDaemon}") // false

    // Каждую характеристику можно изменить, задав новое значение.
    thread.name = "my-thread"

    println("New name: ${thread.name}") // New name: my-thread

    customThreads()

    /**
     * Иногда необходимо управлять жизненным циклом потока, пока он работает, а не просто запустить его и оставить в покое.
     *
     * Два часто используемых метода в многопоточном программировании: sleep() и join().
     *
     * Оба метода могут вызывать исключение InterruptedException.
     */
    sleeping()
    joining()
    exceptions()

    simpleMultiThreadedProgram()
    calcDistinctCharacters()
}

/**
 * Основной поток — это отправная точка, из которой вы можете создавать новые потоки для выполнения своих задач.
 *
 * Для этого вам нужно создать свои собственные потоки, написать код, который будет выполняться в отдельном потоке, а затем запустить его.
 *
 * Два основных способа создания нового потока, выполняющего нужную вам задачу, следующие:
 *
 * - расширение класса Thread и переопределение его метода запуска;
 *
 * - реализация интерфейса Runnable и передача реализации конструктору класса Thread.
 *
 * В обоих случаях вам следует переопределить метод run, который является обычным методом и содержит код для выполнения задачи.
 *
 * Класс Thread имеет метод start(), который используется для запуска потока. В какой-то момент после вызова этого метода метод run будет вызван автоматически, но это произойдёт не сразу.
 *
 * Не путайте методы run и start. Вы должны вызвать start, если хотите выполнить свой код внутри другого потока. Если вы вызываете run напрямую, код будет выполнен в том же потоке.
 *
 * Если вы попытаетесь запустить поток более одного раза, start выдаст исключение IllegalThreadStateException.
 *
 * Не полагайтесь на порядок выполнения операторов между разными потоками, если вы не приняли специальные меры.
 */
fun customThreads() {
    /**
     * В приведённом ниже коде приведён пример расширения класса Thread и переопределения его метода запуска.
     *
     * Если вы расширите класс Thread, вы сможете принимать поля и методы базового класса, но не сможете расширять другими классами, поскольку в Котлине нет множественного наследования классов.
     */
    class HelloThread : Thread() {
        override fun run() {
            val helloMsg = "Hello, i'm $name"

            println(helloMsg)
        }
    }

    val thread1 = HelloThread() // a subclass of Thread

    // Через время запустится run и println()
    // Hello, i'm Thread-0
    thread1.start()

    /**
     * Следующий код показывает, как реализовать интерфейс Runnable и передать реализацию конструктору класса Thread:
     */
    class HelloRunnable : Runnable {
        override fun run() {
            val threadName = Thread.currentThread().name
            val helloMsg = "Hello, i'm $threadName"

            println(helloMsg)
        }
    }

    val thread2 = Thread(HelloRunnable()) // passing runnable

    // Через время запустится run и println()
    // Hello, i'm Thread-1
    thread2.start()

    /**
     * А вот еще один способ указать имя вашего потока, передав его конструктору:
     */
    val thread3 = Thread(HelloRunnable(), "my-thread")

    // Через время запустится run и println()
    // Hello, i'm my-thread
    thread3.start()

    // Порядок отображения сообщений может быть разным
    println("Finished")
}

/**
 * Простая многопоточная программа с двумя потоками. Первый поток считывает числа из стандартного ввода и выводит их квадраты. В то же время основной поток время от времени выводит сообщения на стандартный вывод. Оба потока работают одновременно.
 */
fun simpleMultiThreadedProgram() {
    /**
     * Поток, который циклически считывает числа и возводит их в квадрат. У него есть оператор прерывания, позволяющий остановить цикл, если заданное число равно 0.
     */
    class SquareWorkerThread(name: String) : Thread(name) {
        override fun run() {
            while (true) {
                val number: Int = readln().toInt()

                if (number == 0) {
                    break
                }

                println(number * number)
            }

            println("$name's finished")
        }
    }

    /**
     * Запускает объект класса SquareWorkerThread, который записывает сообщения в стандартный вывод из основного потока.
     */
    val workerThread = SquareWorkerThread("square-worker")

    workerThread.start() // start a worker (not run!)

    // Hello from the main!
    // Hello from the main!
    // Hello from the main!
    // Hello from the main!
    // Hello from the main!
    // Hello from the main!
    // 2 // input
    // 4 // result
    // 3 // input
    // 9 // result
    // 5 // input
    // 25 // result
    // 0 // input
    // square-worker's finished
    for (i: Long in 0 ..< 5_555_555_543L) {
        if (i % 1_000_000_000 == 0L) {
            println("Hello from the main!")
        }
    }
}

/**
 * Метод Thread.sleep() заставляет текущий выполняющийся поток приостановить выполнение на указанное количество миллисекунд.
 *
 * Это эффективный способ предоставления процессорного времени другим потокам приложения или другим приложениям, которые могут работать на компьютере.
 */
fun sleeping() {
    println("Started. Before sleeping")

    // приостановить текущий поток на 2000 миллисекунд или 2 секунды
    Thread.sleep(2000)

    println("Finished. After sleeping")
}

/**
 * Метод join заставляет текущий поток ждать завершения другого потока, в котором был вызван этот метод.
 */
fun joining() {
    // Строка «The end» не будет напечатана до тех пор, пока поток не завершится.
    class HelloThread(name: String) : Thread(name) {
        override fun run() {
            println("Run $name thread")
        }
    }

    val thread: Thread = HelloThread("join-thread")

    println("The start")

    thread.start()

    println("Do something useful")

    // В качестве параметра передаётся время в миллисекундах, которое используется, чтобы избежать слишком долгого или даже бесконечного ожидания в случае зависания потока.
    thread.join(20000) // Ждёт пока поток умрёт или продолжает выполнение через заданное время

    println("The end")

    /**
     * Класс Worker разработан для решения «сложной задачи», моделируемой методом sleep
     *
     * Основной поток ожидает worker и не может напечатать сообщение "The program stopped" до тех пор, пока worker не завершится или не будет превышено время ожидания.
     *
     * Мы точно знаем лишь то, что «Starting a task» предшествует «The task is finished», и «Do something useful» предшествует «The program stopped».
     */
    class Worker : Thread() {
        override fun run() {
            println("Starting a task")

            sleep(2000) // Имитация решения сложной задачи

            println("The task is finished")
        }
    }

    val worker = Worker()

    worker.start()

    Thread.sleep(100)

    println("Do something useful")

    worker.join(3000) // waiting for the worker

    println("The program stopped")

    /**
     * Есть несколько возможных выводов.
     *
     * Первый возможный вывод (задача завершается до превышения таймаута):
     *
     * Starting a task
     * Do something useful
     * The task is finished
     * The program stopped
     *
     * Второй возможный вывод (задача завершается до превышения таймаута):
     *
     * Do something useful
     * Starting a task
     * The task is finished
     * The program stopped
     *
     * Третий возможный вывод:
     *
     * Do something useful
     * Starting a task
     * The program stopped
     * The task is finished
     *
     * Четвёртый возможный вывод:
     *
     * Starting a task
     * Do something useful
     * The program stopped
     * The task is finished
     */
}

val mainThreadId: Long = Thread.currentThread().threadId()

class SlowStringProcessor(val input: String) : Thread() {
    @Volatile
    var numberOfUniqueCharacters: Int = 0
        private set

    override fun run() {
        val currentId: Long = currentThread().threadId()

        if (currentId == mainThreadId) {
            throw RuntimeException("You must start a new thread!")
        }

        try {
            sleep(2000)
        } catch (error: Exception) {
            throw RuntimeException("Do not interrupt the processor", error)
        }

        numberOfUniqueCharacters = input
            .split("")
            .filter { it != "" }
            .toTypedArray()
            .distinct()
            .size
    }
}

fun calcDistinctCharacters() {
    val processor = SlowStringProcessor("multithreading")

    processor.start()
    processor.join(3000)

    println(processor.numberOfUniqueCharacters) // 12
}

/**
 * Если один из потоков вашей программы выдаёт исключение, которое не перехватывается ни одним методом в стеке вызовов, поток будет завершен. Если такое исключение возникает в однопоточной программе, вся программа остановится, поскольку JVM завершит работающую программу, как только не останется потоков, не являющихся демонами.
 *
 * `fun main() {
 *     print(2 / 0)
 * }`
 *
 * Эта программа выводит:
 *
 * Exception in thread "main" java.lang.ArithmeticException: / by zero
 *     at ExampleKt.main(example.kt:6)
 *     at ExampleKt.main(example.kt)
 *
 * Process finished with exit code 1
 *
 * Код 1 означает, что процесс завершился с ошибкой.
 *
 * Если ошибка произойдёт внутри нового потока, который мы создали, весь процесс не будет остановлен:
 *
 * `fun main() {
 *     val thread = CustomThread()
 *     thread.start()
 *     thread.join() // wait for the thread with an exception to terminate
 *     println("I am printed!") // this line will be printed
 * }
 *
 *
 * class CustomThread : Thread() {
 *     override fun run() {
 *         println(2 / 0)
 *     }
 * }`
 *
 * Несмотря на неперехваченное исключение, программа будет успешно завершена.
 *
 * Exception in thread "Thread-2" java.lang.ArithmeticException: / by zero
 *     at CustomThread.run(example.kt:14)
 * I am printed!
 *
 * Process finished with exit code 0
 *
 * Код 0 означает, что процесс успешно завершён.
 *
 * Что произойдёт, если в основном потоке произойдёт ошибка, а код пользовательского потока правильный? Давайте посмотрим:
 *
 * `fun main() {
 *     thread(block = {
 *         println("Hello from the custom thread!")
 *     })
 *     print(2 / 0)
 *     println("Hello from the main thread!")
 * }`
 *
 * Вывод программы будет следующим:
 *
 * Exception in thread "main" java.lang.ArithmeticException: / by zero
 * 	at Thread_excKt.main(thread_exc.kt:7)
 * 	at Thread_excKt.main(thread_exc.kt)
 * Hello from custom thread!
 *
 * Process finished with exit code 1
 *
 * В результате процесс завершился с ошибкой (код выхода 1). Код после print(2/0) не выполнился, но блок кода в пользовательском потоке выполнился.
 *
 * Таким образом, несмотря на исключение в основном потоке, пользовательские потоки всегда работают корректно, даже если программа завершается с ошибкой.
 */
fun exceptions() {}
