import kotlin.random.Random

/**
 * Случайное число — это число, которое почти невозможно предсказать.
 *
 * Генератор случайных чисел может предоставить вам такое число, когда вам это нужно, и, вероятно, оно вам понадобится довольно часто.
 *
 * Например, это удобно, когда вы пытаетесь создать пароль, который никто не сможет угадать, сделать следующий непредсказуемый ход в игре или создать множество случайных данных.
 *
 * Вы можете установить некоторые ограничения для вашего случайного числа, выбрав определенный тип. Все необходимое для этого находится в пакете kotlin.random
 */
fun main() {
    // Генерирует int значение между Int.MIN_VALUE и Int.MAX_VALUE (включительно)
    println(Random.nextInt()) // 1836651950

    // Генерирует long значение между Long.MIN_VALUE и Long.MAX_VALUE (включительно)
    println(Random.nextLong()) // -8998212912835567980

    // Генерирует float значение от 0 (включительно) до 1.0 (исключительно)
    println(Random.nextFloat()) // 0.020747364

    // Генерирует double значение от 0 (включительно) до 1.0 (исключительно)
    println(Random.nextDouble()) // 0.24640775277168914

    /**
     * Но что, если нам нужны только целые числа от 0 до 100 или числа с плавающей точкой от 0.0 до 5.0?
     *
     * Мы можем указать их явно.
     *
     * Обратите внимание, что nextFloat — единственная функция, которая не позволяет указать пользовательский диапазон: nextDouble обеспечивает лучшую точность, поэтому вместо этого мы используем её.
     */
    // Генерирует неотрицательное int значение меньше 100
    println(Random.nextInt(100)) // 31

    // Генерирует int значение от 1 (включительно) до 100 (не включая)
    println(Random.nextInt(1, 100)) // 65

    // Генерирует неотрицательное long значение меньше 100
    println(Random.nextLong(100)) // 7

    // Генерирует long значение от 1 (включительно) до 100 (не включая)
    println(Random.nextLong(1, 100)) // 43

    // Генерирует неотрицательное double значение меньше 5.0
    println(Random.nextDouble(5.0)) // 4.028642356932432

    // Генерирует double значение от 0.0 (включительно) до 5.0 (не включая)
    println(Random.nextDouble(0.0, 5.0)) // 3.51227785926793

    /**
     * Так почему же эти функции вызываются next[......], а не get[.....]?
     *
     * Каждый раз, когда функция вызывается, она даёт нам следующее число в предопределённой последовательности. Эти числа называются псевдослучайными, и они не совсем непредсказуемы!
     *
     * Мы можем вычислить их все, если знаем начальное значение и алгоритм последовательности.
     *
     * Само начальное число никогда не возвращается функцией next[......], но оно определяет все последующие числа.
     *
     * Гарантируется, что одно и то же начальное число создаёт одинаковую последовательность, если используется одна и та же версия среды выполнения Kotlin, потому что алгоритм тот же.
     *
     * Это может быть полезно для надёжного тестирования кода, использующего генераторы случайных чисел.
     *
     * Сгенерируем пять псевдослучайных чисел из последовательности с начальным числом 42.
     */
    val randomGenerator42 = Random(42)

    for (i in 0..5) {
        println(randomGenerator42.nextInt(100)) // 33, 40, 41, 2, 41, 32 - static result
    }

    /**
     * Напротив, генератор по умолчанию каждый раз будет давать нам новую последовательность.
     */
    val defaultGenerator = Random.Default

    for (i in 0..5) {
        println(defaultGenerator.nextInt(100))
    }

    println(generatePredictablePassword(42)) // H=bAl_0%j\
    println(generatePredictablePassword(13)) // =XK_p?;']$
    println(verificationCode())
}

/**
 * Допустим, мы пытаемся установить зашифрованную связь с нашим другом.
 *
 * Для этого мы будем использовать секретное начальное число, о котором мы договорились, затем сгенерируем 10-символьный пароль и попросим нашего друга ввести тот же пароль.
 *
 * Если это действительно наш друг, слова совпадут.
 *
 * Напишите функцию, которая берёт известное начальное число и генерирует строку ровно из 10 случайных печатных символов (коды от 33 до 126 включительно), используя это начальное число.
 *
 * Используйте функцию .toChar() для преобразования случайного числа в символ и объединения всех символов в одну строку.
 *
 * Обязательно вызывайте конструктор Random только один раз и повторно используйте один и тот же экземпляр для генерации всех чисел.
 */
fun generatePredictablePassword(seed: Int): String {
    val randomGenerator = Random(seed)

    var randomPassword = ""

    for (i in 0..9) {
        randomPassword += randomGenerator.nextInt(33, 127).toChar()
    }

    return randomPassword
}

/**
 * Вы работаете над страницей регистрации и хотите отправить электронное письмо пользователю, чтобы проверить его электронную почту, и, наконец, решили проверить его с помощью кода подтверждения.
 *
 * Теперь реализуйте логику создания случайного кода. Длина проверочного кода должна быть 10.
 */
fun verificationCode(): String {
    val letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789"

    var randomCode = ""

    repeat(10) {
        randomCode += letters.random()
    }

    return randomCode
}
