import com.squareup.moshi.Moshi
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.reflect.ParameterizedType

/**
 * Конвертировать классы в JSON вручную не очень удобно, но, к счастью, существует множество библиотек, которые упрощают этот процесс. Один из самых популярных и часто используемых в Android называется Moshi.
 *
 * API Moshi намного меньше, чем другие известные библиотеки, такие как Jackson и Gson. У Gson более тысячи методов, а у Moshi — лишь половина этого числа.
 *
 * Moshi тоже весит в 2-3 раза меньше (чуть более 100кБ по сравнению с 300кБ у Gson).
 *
 * Чтобы использовать библиотеку Moshi, нам нужно добавить следующую зависимость в наш файл build.gradle.kts в разделе dependencies:
 * implementation("com.squareup.moshi:moshi-kotlin:1.15.0")
 */
fun main() {
    serializingKotlinObjectsIntoJSON()
    deserializingJSONIntoKotlinObjects()
    workingWithJSON()
}

class Human(
    var name: String,
    var age: Int,
    var friends: Array<String>,
    var grades: Map<String, String> = emptyMap()
)

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val jsonAdapter: JsonAdapter<Human> = moshi.adapter(Human::class.java)

fun serializingKotlinObjectsIntoJSON() {
    println("--- Serializing ---")

    val human = Human("Mike", 20, arrayOf("Alex", "Valery", "Ann"))

    val json: String = jsonAdapter.toJson(human)

    // {"name":"Mike","age":20,"friends":["Alex","Valery","Ann"],"grades":{}}
    println(json)
}

fun deserializingJSONIntoKotlinObjects() {
    println("--- Deserializing ---")

    val newHumanString = """{
        "name":"John",
        "age":25, 
        "friends":["Mike","Helen"]
    }""".trimIndent()

    val newHuman: Human? = jsonAdapter.fromJson(newHumanString)

    val human = Human("Mike", 20, arrayOf("Alex", "Valery", "Ann"))

    val humanList: List<Human?> = listOf(human, newHuman)

    val type: ParameterizedType = Types.newParameterizedType(List::class.java, Human::class.java)

    val humanListAdapter: JsonAdapter<List<Human?>> = moshi.adapter(type)
    val json: String = humanListAdapter.toJson(humanList)

    // [{"name":"Mike","age":20,"friends":["Alex","Valery","Ann"],"grades":{}},{"name":"John","age":25,"friends":["Mike","Helen"],"grades":{}}]
    println(json)
}

fun workingWithJSON() {
    println("--- Working ---")

    val json: String = """{
        "name":"John",
        "age":25, 
        "friends":["Mike","Helen"]
    }""".trimIndent()

    val newHuman: Human? = jsonAdapter.fromJson(json)

    println(newHuman?.name) // John
    println(newHuman?.age) // 25
    println(newHuman?.friends.contentToString()) // [Mike, Helen]
    println(newHuman?.grades) /// {}

    val type = Types.newParameterizedType(List::class.java, Human::class.java, Map::class.java)
    val humanListAdapter = moshi.adapter<List<Human?>>(type)
}
