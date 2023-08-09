import org.junit.jupiter.api.*

private const val MIN_PASSWORD_LENGTH = 8

/**
 * Класс User имеет два приватных поля String: username и password, конструктор, который принимает два аргумента String, и три общедоступных метода для проверки наличия у экземпляра класса User действительного имени пользователя, надёжного пароля (считается надёжным, если он не менее 8 символов) и представляет действительного пользователя, что означает, что он имеет действительное имя пользователя и надёжный пароль.
 */
class User(private val username: String?, private val password: String?) {
    fun hasStrongPassword(): Boolean =
        password != null && password.length >= MIN_PASSWORD_LENGTH

    fun hasValidUsername(): Boolean = !username.isNullOrBlank()

    val isValid: Boolean
        get() = hasValidUsername() && hasStrongPassword()

}

/**
 * Аннотация `@TestInstance` управляет жизненным циклом экземпляра тестового класса.
 *
 * По умолчанию установлено значение `TestInstance.Lifecycle.PER_METHOD`, но при необходимости его можно изменить. Если отсутствует аннотация TestInstance, будет неявно установлен PER_METHOD
 *
 * - PER_METHOD - инстанс тестового класса создаётся для каждого теста
 * - PER_CLASS - инстанс тестового класса создаётся 1 раз и проходит все тесты. Если методы тестирования полагаются на состояние, хранящееся в нестатических переменных, вам может потребоваться сбросить это состояние в методах @BeforeEach или @AfterEach.
 *
 * Кроме того, JUnit5 имеет специальные аннотации для обозначения любых методов как методов жизненного цикла, например `@BeforeAll`, `@AfterAll`, `@BeforeEach` или `@AfterEach`.
 *
 * Они предписывают платформе выполнять назначенные методы до или после выполнения реальных тестовых методов.
 *
 * Аннотации `@BeforeEach` и `@AfterEach` указывают, соответственно, что аннотированный метод будет выполняться до и после каждого метода тестового класса, аннотированного `@Test`, а методы `@BeforeAll` и `@AfterAll` будут выполняться до или после всех методов `@Test` в тестовом классе.
 */

/**
 * Набор тестов для проверки правильности реализации методов класса User.
 *
 * Каждый из них полностью независим от других. Мы не вызываем никаких тестов в другом тесте, и каждый тест использует новый экземпляр класса User. Это может не иметь большого смысла в данном конкретном случае, но если мы тестируем более сложные классы, нам нужно начинать каждый раз с чистого состояния тестируемого объекта.
 */
internal class UserTestBefore {
    @Test
    fun hasStrongPassword() {
        val username = "Alice"
        val password = "12345678"

        val user = User(username, password)

        Assertions.assertTrue(user.hasStrongPassword())
    }

    @Test
    fun hasValidUsername() {
        val username = "Alice"
        val password = "12345678"

        val user = User(username, password)

        Assertions.assertTrue(user.hasValidUsername())
    }

    @Test
    fun isValid() {
        val username = "Alice"
        val password = "12345678"

        val user = User(username, password)

        Assertions.assertTrue(user.isValid)
    }
}

/**
 * Новый тестовый класс, чтобы увидеть, как на самом деле работают аннотации жизненного цикла
 */
class LifeCycleTest1 {
    init {
        println("Test Class Constructor") // 1, 5
    }

    @BeforeEach
    fun beforeEach() {
        println("Before each test") // 2, 6
    }

    @AfterEach
    fun afterEach() {
        println("After each test") // 4, 8
    }

    @Test
    fun test1() {
        println("Test 1") // 3
    }

    @Test
    fun test2() {
        println("Test 2") // 7
    }
}

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LifeCycleTest2 {
    init {
        println("Test Class Constructor") // 1
    }

    @BeforeAll // Требует аннотации @TestInstance(Lifecycle.PER_CLASS)
    fun beforeAll() {
        println("Before all tests") // 2
    }

    @BeforeEach
    fun beforeEach() {
        println("Before each test") // 3, 6
    }

    @AfterEach
    fun afterEach() {
        println("After each test") // 5, 8
    }

    @AfterAll // Требует аннотации @TestInstance(Lifecycle.PER_CLASS)
    fun afterAll() {
        println("After all tests") // 9
    }

    @Test
    fun test1() {
        println("Test 1") // 4
    }

    @Test
    fun test2() {
        println("Test 2") // 7
    }
}

/**
 * Теперь мы можем переписать наш класс UserTest и избавиться от инициализации экземпляров User в каждом тестовом методе
 */
internal class UserTestAfter {
    private var user: User? = null

    @BeforeEach
    fun createUser() {
        val username = "Alice"
        val password = "12345678"

        user = User(username, password)
    }

    @Test
    fun hasStrongPassword() {
        Assertions.assertTrue(user!!.hasStrongPassword())
    }

    @Test
    fun hasValidUsername() {
        Assertions.assertTrue(user!!.hasValidUsername())
    }

    @Test
    fun isValid() {
        Assertions.assertTrue(user!!.isValid)
    }
}

/**
 * Собрав всё вместе, мы можем написать следующую реализацию нашего тестового класса с заранее определённым набором входных данных.
 *
 * Инициализируем инстанс класса 1 раз, повторяем выполнение теста 6 раз с помощью RepeatedTest, увеличиваем индекс с каждым тестом и берём и списков следующий набор данных для каждого теста.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class UserTest {
    private lateinit var names: Array<String?>
    private lateinit var passwords: Array<String?>
    private lateinit var expectedOutcomes: BooleanArray

    private var index = 0

    private var user: User? = null
    private var expected = false

    @BeforeAll
    fun setUp() {
        names = arrayOf("Alice", "Alice", "Alice", "", null, "    ")
        passwords = arrayOf("12345678", "123", null, "12345678", "12345678", "12345678")
        expectedOutcomes = booleanArrayOf(true, false, false, false, false, false)
    }

    @BeforeEach
    fun createUser() {
        user = User(names[index], passwords[index])

        expected = expectedOutcomes[index]
    }

    @AfterEach
    fun incrementIndex() {
        index += 1
    }

    /**
     * Здесь мы использовали @RepeatedTest для запуска аннотированного теста 6 раз (value = 6) и определили пользовательское имя для отображения результатов теста.
     *
     * {currentRepetition} и {totalRepetitions} — это заполнители для отображения текущего запуска и общего количества тестовых запусков. Вот результат
     */
    @RepeatedTest(value = 6, name = "user.isValid() test {currentRepetition}/{totalRepetitions}")
    fun isValid() {
        println("RepeatedTest") // Повторился 6 раз

        Assertions.assertEquals(expected, user!!.isValid)
    }
}
