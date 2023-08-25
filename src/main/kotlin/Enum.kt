/**
 * Если вы когда-либо использовали константы в программировании, то могли задать себе вопрос: «Как я могу хранить множество констант в одном месте и обрабатывать их все одновременно?» Что ж, на этот вопрос у Kotlin есть ответ, и мы называем этот ответ Enums. По сути, перечисления представляют собой логический набор констант, и они делают наш код более понятным и читабельным.
 *
 * enum — это ключевое слово, позволяющее нам создать собственное перечисление просто из обычного класса
 */
enum class Rainbow(val color: String, val rgb: String) {
    RED_COLOR("Red", "#FF0000"),
    ORANGE_COLOR("Orange", "#FF7F00"),
    YELLOW_COLOR("Yellow", "#FFFF00"),
    GREEN_COLOR("Green", "#00FF00"),
    BLUE_COLOR("Blue", "#0000FF"),
    INDIGO_COLOR("Indigo", "#4B0082"),
    VIOLET_COLOR("Violet", "#8B00FF");

    fun printFullInfo() {
        println("Color - $color, rgb - $rgb")
    }
}

enum class DaysOfTheWeek {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;
}

fun main() {
    val redColor = Rainbow.RED_COLOR
    val color: String = redColor.color
    val rgb: String = redColor.rgb

    println("Color name: $color") // Red
    println("RGB: $rgb") // #FF0000

    redColor.printFullInfo() // Color - Red, rgb - #FF0000

    /**
     * name позволяет получить имя экземпляра Enum
     */
    println("Имя экземпляра Enum (name): ${redColor.name}") // RED_COLOR

    /**
     * ordinal содержит позицию экземпляра Enum
     */
    println("Позиция экземпляра Enum (ordinal): ${Rainbow.GREEN_COLOR.ordinal}") // 3

    /**
     * values() возвращает массив всех экземпляров Enum. Это может быть полезно, если вы хотите перебирать экземпляры Enum
     */
    for (enum in Rainbow.values()) {
        println("Значение Enum (values): ${enum.ordinal} | $enum | ${enum.color} (${enum.rgb})")
    }

    /**
     * valueOf() возвращает экземпляр Enum по его имени с типом String и чувствительностью к регистру.
     *
     * Если подходящего экземпляра Enum нет, будет выдано исключение IllegalArgumentException
     */
    val indigoColor: Rainbow = Rainbow.valueOf("INDIGO_COLOR")

    println("Экземпляр по имени Enum (valueOf): $indigoColor") // INDIGO_COLOR
    println("Экземпляр по имени Enum (valueOf): ${indigoColor.color}") // Indigo
    println("Экземпляр по имени Enum (valueOf): ${indigoColor.rgb}") // #4B0082

    /**
     * Печатает имя каждого экземпляра перечисления на новой строке, начиная с SUNDAY.
     */
    for (enum in DaysOfTheWeek.entries) {
        println(enum)
    }
}
