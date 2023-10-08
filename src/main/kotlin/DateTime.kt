import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.FixedOffsetTimeZone
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.periodUntil
import kotlinx.datetime.plus
import kotlinx.datetime.minus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlinx.datetime.until

import kotlin.time.Duration

/**
 * Kotlinx — это набор проектов, которые не являются частью стандартной библиотеки, но являются полезными расширениями. Одним из наиболее полезных проектов является библиотека kotlinx-datetime, которая представляет собой многоплатформенную библиотеку Kotlin для обработки данных о дате и времени.
 *
 * `dependencies {
 *     implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.1")
 * }`
 *
 * Instant всегда представляет время в формате UTC. Поэтому, если вы хотите отображать время в определенном часовом поясе, вам необходимо преобразовать Instant в другой тип даты и времени, например LocalDateTime или ZonedDateTime. Также помните, что Instant не содержит информации о часовом поясе.
 *
 * Класс TimeZone используется для хранения информации о часовом поясе.
 *
 * Функция-член currentSystemDefault() предоставляет часовой пояс компьютерной системы, а UTC устанавливает часовой пояс в формате UTC (обозначается как Z в ISO 8601).
 *
 * Любой другой часовой пояс устанавливается с помощью функции-члена `of()`, которая принимает строку в качестве параметра. Это может быть либо смещение UTC (например, «UTC-3» или «UTC-03:00»), либо название часового пояса (например, «Europe/Rome»). Действительные названия часовых поясов можно найти в базе данных tz (https://en.wikipedia.org/wiki/List_of_tz_database_time_zones).
 *
 * Если предоставленный параметр функции of() недействителен, выдаётся исключение IllegalTimeZoneException.
 *
 * Класс DateTimePeriod используется для хранения разницы между двумя объектами Instant, разделёнными на компоненты даты и времени.
 *
 * Доступ к ним можно получить из соответствующих свойств с именами years, months, days, hours, minutes, seconds, и nanoseconds. Печать объекта DateTimePeriod даёт разницу в представлении продолжительности ISO 8601.
 *
 * Разницу между двумя объектами Instant можно получить с помощью функции-члена:
 * periodUntil(other: Instant, timeZone: TimeZone),
 * где other — это другой Instant, а timezone — часовой пояс.
 */
fun main() {
    println("--- Instant ---")

    // Метод now() для получения текущей даты и времени
    val currentInstant: Instant = Clock.System.now()

    println(currentInstant) // 2023-10-08T12:45:05.012479612Z

    // Время текущей даты в миллисекундах
    val currentInstantInMilliseconds: Long = currentInstant.toEpochMilliseconds()

    println(currentInstantInMilliseconds) // 1696769105012

    // Конкретный момент в миллисекундах
    val specificInstant: Instant = Instant.fromEpochMilliseconds(1696751343563)

    println(specificInstant) // 2023-10-08T07:49:03.563Z

    // Смещение времени
    val futureInstant: Instant = currentInstant.plus(Duration.parse("6h"))
    val pastInstant: Instant = currentInstant.minus(Duration.parse("24h"))

    println(futureInstant) // 2023-10-08T18:45:05.012479612Z
    println(pastInstant) // 2023-10-07T12:45:05.012479612Z

    val zonedDateTime: LocalDateTime = currentInstant.toLocalDateTime(TimeZone.currentSystemDefault())
    val backToInstant: Instant = zonedDateTime.toInstant(TimeZone.currentSystemDefault())

    println(zonedDateTime) // 2023-10-08T15:45:05.012479612
    println(backToInstant) // 2023-10-08T12:45:05.012479612Z

    // Часовой пояс компьютерной системы
    val tz1: TimeZone = TimeZone.currentSystemDefault()

    println(tz1) // Europe/Moscow

    // UTC тайм зона
    val tz2: FixedOffsetTimeZone = TimeZone.UTC

    println(tz2) // Z

    // Paris, France time zone
    val tz3: TimeZone = TimeZone.of("Europe/Paris")

    println(tz3) // Europe/Paris

    // UTC + 2 hours time zone
    val tz4: TimeZone = TimeZone.of("UTC+2")

    println(tz4) // UTC+02:00

    val instant1: Instant = Instant.parse("2000-01-01T20:00:00Z")
    val instant2: Instant = Instant.parse("2000-10-14T00:00:00Z")

    val period: DateTimePeriod = instant1.periodUntil(instant2, TimeZone.UTC)

    println(period) // P9M12DT4H

    // Months: 9, Days: 12, Hours: 4
    println("Months: ${period.months}, Days: ${period.days}, Hours: ${period.hours}")

    val instant = Instant.parse("2000-01-01T00:00:00Z")

    println(instant) // 2000-01-01T00:00:00Z

    val dateTimePeriod: DateTimePeriod = DateTimePeriod(
        years = 1,
        months = 1,
        days = 1,
        hours = 1,
        minutes = 1,
        seconds = 1,
        nanoseconds = 123456789L
    )

    println(dateTimePeriod) // P1Y1M1DT1H1M1.123456789S

    val after: Instant = instant.plus(dateTimePeriod, TimeZone.UTC)

    println(after) // 2001-02-02T01:01:01.123456789Z

    val before: Instant = instant.minus(dateTimePeriod, TimeZone.UTC)

    println(before) // 1998-11-29T22:58:58.876543211Z

    // Ниже приведены несколько примеров, иллюстрирующих различия между классами Duration (пакет kotlin.time) и DateTimePeriod (kotlinx):
    val instant3: Instant = Instant.parse("2100-01-01T00:00:00Z")
    val instant4: Instant = Instant.parse("2105-07-09T15:23:40Z")

    val duration: Duration = instant4 - instant3

    println(duration) // 2015d 15h 23m 40s
    println(duration.inWholeDays) // 2015
    println(duration.inWholeHours) // 48375

    println(instant3.periodUntil(instant4, TimeZone.UTC)) // P5Y6M8DT15H23M40S
    println(instant3.periodUntil(instant4, TimeZone.UTC).days) // 8

    localDateTime()
    localDate()

    // Найдите разницу в единицах DateTimeUnit
    val date1 = LocalDate(2022, 1, 1)
    val date2 = LocalDate(2022, 3, 1)

    // В этом случае функция until() принимает только те единицы DateTimeUnit, которые кратны дню.
    println(date1.until(date2, DateTimeUnit.DAY)) // 59

    // Сравнить объекты LocalDate
    println(if (date1 > date2) date1 else date2) // 2022-03-01

    // periodUntil() function
    val period2: DatePeriod = date1.periodUntil(date2)

    // Years: 0, Months: 2, Days: 0
    println("Years: ${period2.years}, Months: ${period2.months}, Days: ${period2.days}")

    // plus() and minus() functions
    val date = LocalDate(2000, 1, 1)

    println(date) // 2000-01-01

    val manyDays: DatePeriod = DatePeriod(days = 101)

    val after2: LocalDate = date.plus(manyDays) // or val after = date + manyDays

    println(after2) // 2000-04-11

    val before2: LocalDate = date.minus(manyDays) // or val before = date - manyDays

    println(before2) // 1999-09-22

    val dateTime: LocalDateTime = LocalDateTime(2022, 1, 1, 0, 0,0)

    println(dateTime) // 2022-01-01T00:00

    val tz: TimeZone = TimeZone.of("Asia/Seoul")

    val instant5: Instant = dateTime.toInstant(tz)

    println(instant5) // 2021-12-31T15:00:00Z

    println("--- nextMonth ---")

    println(nextMonth("2000-12-01T00:00:00Z")) // 2001-01-01T00:00:00Z
}

/**
 * Класс LocalDateTime используется для представления данных о дате и времени для определенного часового пояса и может предоставлять удобочитаемые компоненты даты и времени. Он не хранит конкретный момент времени (как это делает класс Instant), а определяет время и дату в определенной стране или часовом поясе.
 */
fun localDateTime() {
    println("--- LocalDateTime ---")

    // Создание LocalDateTime из Instant
    val dateTime: Instant = Instant.parse("2022-02-17T19:00:00Z")

    val tz: TimeZone = TimeZone.of("UTC+3")

    val local: LocalDateTime = dateTime.toLocalDateTime(tz)

    println(local) // 2022-02-17T22:00

    // Создание LocalDateTime напрямую
    // Параметры: год, месяц, день, часы, минуты
    val dateTime1: LocalDateTime = LocalDateTime(2022, 2, 1, 17, 0)

    println(dateTime1) // 2022-02-01T17:00

    // Параметры: год, месяц, день, часы, минуты, секунды
    val dateTime2: LocalDateTime = LocalDateTime(2022, 2, 1, 17, 0, 16)

    println(dateTime2)  // 2022-02-01T17:00:16

    // Параметры: год, месяц, день, часы, минуты, секунды, наносекунды
    val dateTime3: LocalDateTime = LocalDateTime(2022, 2, 1, 17, 0, 16, 3456)

    println(dateTime3)  // 2022-02-01T17:00:16.000003456

    // Создание из строки даты в формате ISO 8601
    val dateTime4: LocalDateTime = "2022-02-22T22:10:00".toLocalDateTime()
    val dateTime5: LocalDateTime = LocalDateTime.parse("2022-02-22T22:10:00")

    println(dateTime4) // 2022-02-22T22:10
    println(dateTime5) // 2022-02-22T22:10

    val dateTime6: LocalDateTime = LocalDateTime(2023, 2, 1, 23, 22,21, 123456789)

    println(dateTime6.year) // 2023
    println(dateTime6.month) // FEBRUARY
    println(dateTime6.monthNumber) // 2
    println(dateTime6.dayOfYear) // 32
    println(dateTime6.dayOfWeek) // WEDNESDAY
    println(dateTime6.date) // 2023-02-01
    println(dateTime6.dayOfMonth) // 1
    println(dateTime6.hour) // 23
    println(dateTime6.minute) // 22
    println(dateTime6.second) // 21
    println(dateTime6.nanosecond) // 123456789
}

/**
 * Класс LocalDate аналогичен классу LocalDateTime, с той разницей, что он содержит только информацию о локальной дате. Его можно инициировать напрямую, из строки ISO 8601 или из LocalDateTime.
 */
fun localDate() {
    println("--- LocalDate ---")

    // Параметры: год, месяц, день
    val date1: LocalDate = LocalDate(2022, 2, 1)
    val date2: LocalDate = "2022-02-01".toLocalDate()
    val date3: LocalDate = LocalDate.parse("2022-02-01")

    val datetime: LocalDateTime = LocalDateTime(2022, 2, 1, 12, 0)
    val date4: LocalDate = datetime.date

    println(date1) // 2022-02-01
    println(date2) // 2000-02-01
    println(date3) // 2022-02-01
    println(date4) // 2022-02-01

    // Получите текущую дату в UTC+01:00.
    val today: LocalDate = Clock.System.todayIn(TimeZone.of("UTC+1"))

    println(today) // 2023-10-08

    val date: LocalDate = LocalDate(2023, 2, 1)

    println(date.year) // 2023
    println(date.month) // FEBRUARY
    println(date.monthNumber) // 2
    println(date.dayOfYear) // 32
    println(date.dayOfWeek) // WEDNESDAY
    println(date.dayOfMonth) // 1
}

fun nextMonth(date: String): String {
    val instant: Instant = Instant.parse(date)

    return instant.plus(1, DateTimeUnit.MONTH, TimeZone.UTC).toString()
}
