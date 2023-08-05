fun main() {
    /**
     * Java-архив (JAR) — это независимый от платформы формат файла для упаковки нескольких файлов вместе и распространения их как единого блока.
     * - может объединять несколько файлов разных типов;
     * - сжатый архив (с алгоритмом ZIP);
     * - можно подписать цифровой подписью.
     *
     * JRE может запустить приложение, упакованное в JAR, но для создания JAR вам нужно использовать JDK.
     *
     * Все файлы, кроме файлов байт-кода, обычно называются ресурсами.
     *
     * Рекомендуется содержать специальный файл с именем MANIFEST.MF в специальной папке с именем META-INF.
     * Этот файл должен описывать сам JAR-файл (манифест — это своего рода метаданные): его версию, автора и так далее.
     */

    /**
     * Пример структуры файла JAR:
     * example.jar
     * ├── META-INF
     * │   └── MANIFEST.MF
     * ├── second
     * │   ├── Main.class
     * │   └── MyIcon.png
     * └── third
     *     └── another
     *         └── OneMore.class
     */

    /**
     * Пример манифеста:
     * Manifest-Version: 1.0
     * Created-By: 9.0.1 (Oracle Corporation)
     * Main-Class: second.Main
     *
     * Main-Class определяет относительный путь класса с основным методом для запуска приложения
     */

    /**
     * Запуск без Main-Class в манифесте:
     * java -cp app-without-main-class-header.jar path.to.Main
     *
     * Последний параметр здесь — это полное имя класса (с пакетами).
     * Параметр -cp означает путь к классам, т. е. пути ко всем JAR-файлам, которые JRE должна сканировать на наличие байт-кода и ресурсов. При желании вы можете повторить несколько пар параметров -cp path-to-Nth.jar, чтобы предоставить JRE несколько разных файлов JAR.
     *
     * Запустить в терминале
     * java -cp src/main/resources/app1.jar myapp.Main
     *
     * Вывод: Hello, Java
     */

    /**
     * Запуск с Main-Class в манифесте:
     * java -jar app-with-main-class-header.jar
     *
     * Запустить в терминале
     * java -jar src/main/resources/app2.jar
     *
     * Вывод: Hello, Java
     */

    /**
     * Скомпилированную программу можно также вызывать с параметрами:
     * `java -jar filename.jar args`
     * Аргументы `args` - это массив строк, которые принимает точка входа - функция main
     * При вызове просто перечисляются через пробел.
     *
     * Для запуска программы файл программы должен содержать либо функцию с сигнатурой main(), либо функцию main(args: Array<String>).
     */

    /**
     * fun main(args: Array<String>) {
     *     println(args[0]) // Hello,
     *     println(args[1]) // Kotlin!
     * }
     *
     * $ java -jar print_args.jar Hello, Kotlin!
     */
}
