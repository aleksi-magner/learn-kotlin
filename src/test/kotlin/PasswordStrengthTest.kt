import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

enum class PasswordStrength {
    STRONG, MEDIUM, WEAK
}

/**
 * Существует служебный метод, который оценивает надёжность пароля. Пароль считается надёжным, если его длина составляет не менее 8 символов и он содержит хотя бы один символ верхнего регистра, один символ нижнего регистра, одну цифру и один специальный символ. Пароль средней сложности также состоит из не менее 8 символов и содержит буквенные символы и хотя бы одну цифру. Любой другой пароль считается слабым:
 */
class PasswordUtils {
    fun getStrength(password: String): PasswordStrength {
        val length = ".{8,}" // не менее 8 символов в длину
        val lowercase = "(?=.*[a-z])" // хотя бы один символ нижнего регистра
        val uppercase = "(?=.*[A-Z])" // хотя бы один символ в верхнем регистре
        val digit = "(?=.*\\d)" // хотя бы одна цифра
        val special = "(?=.*[ !@#$%^&*])" // хотя бы один из этих специальных символов

        return if (password.matches((lowercase + uppercase + digit + special + length).toRegex())) {
            PasswordStrength.STRONG
        } else if (password.matches((lowercase + digit + length).toRegex())) {
            PasswordStrength.MEDIUM
        } else if (password.matches((uppercase + digit + length).toRegex())) {
            PasswordStrength.MEDIUM
        } else {
            PasswordStrength.WEAK
        }
    }
}

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SnippetTests {
    private val passwordUtils = PasswordUtils()

    private fun provideStrongPasswords(): List<String> {
        return listOf("aN we2aM", "*****Jj0", "Ux134!&а")
    }

    @ParameterizedTest
    @MethodSource("provideStrongPasswords")
    fun testStrongPasswords(password: String) {
        assertEquals(PasswordStrength.STRONG, passwordUtils.getStrength(password))
    }

    private fun provideWeakPasswords(): List<String> {
        return listOf("paSSword1", "yf8HdAjk", "Y20220101")
    }

    @ParameterizedTest
    @MethodSource("provideWeakPasswords")
    fun testMediumPasswords(password: String) {
        assertEquals(PasswordStrength.MEDIUM, passwordUtils.getStrength(password))
    }

    private fun provideMediumPasswords(): List<String> {
        return listOf("QWERTY2", "Admin01", "8^ax00")
    }

    @ParameterizedTest
    @MethodSource("provideMediumPasswords")
    fun testWeakPasswords(password: String) {
        assertEquals(PasswordStrength.WEAK, passwordUtils.getStrength(password))
    }
}
