@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson7.task1
import kotlin.math.*
import java.io.File

/**
 * Пример
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Вывести его в выходной файл с именем outputName, выровняв по левому краю,
 * чтобы длина каждой строки не превосходила lineLength.
 * Слова в слишком длинных строках следует переносить на следующую строку.
 * Слишком короткие строки следует дополнять словами из следующей строки.
 * Пустые строки во входном файле обозначают конец абзаца,
 * их следует сохранить и в выходном файле
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val outputStream = File(outputName).bufferedWriter()
    var currentLineLength = 0
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            outputStream.newLine()
            if (currentLineLength > 0) {
                outputStream.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(" ")) {
            if (currentLineLength > 0) {
                if (word.length + currentLineLength >= lineLength) {
                    outputStream.newLine()
                    currentLineLength = 0
                }
                else {
                    outputStream.write(" ")
                    currentLineLength++
                }
            }
            outputStream.write(word)
            currentLineLength += word.length
        }
    }
    outputStream.close()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 */
fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> {
    val a = mutableMapOf<String, Int>()
    val b = File(inputName)
            .readText()
            .toLowerCase()
    for (i in 0 until substrings.size) {
        a[substrings[i]] = Regex(substrings[i].toLowerCase())
                .findAll(b)
                .count()
    }
    return a
}


/**
 * Средняя
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */
fun sibilants(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по центру
 * относительно самой длинной строки.
 *
 * Выравнивание следует производить путём добавления пробелов в начало строки.
 *
 *
 * Следующие правила должны быть выполнены:
 * 1) Пробелы в начале и в конце всех строк не следует сохранять.
 * 2) В случае невозможности выравнивания строго по центру, строка должна быть сдвинута в ЛЕВУЮ сторону
 * 3) Пустые строки не являются особым случаем, их тоже следует выравнивать
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых)
 *
 */
fun centerFile(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно
 * самой длинной строки.
 * Выравнивание производить, вставляя дополнительные пробелы между словами: равномерно по всей строке
 *
 * Слова внутри строки отделяются друг от друга одним или более пробелом.
 *
 * Следующие правила должны быть выполнены:
 * 1) Каждая строка входного и выходного файла не должна начинаться или заканчиваться пробелом.
 * 2) Пустые строки или строки из пробелов трансформируются в пустые строки без пробелов.
 * 3) Строки из одного слова выводятся без пробелов.
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых).
 *
 * Равномерность определяется следующими формальными правилами:
 * 5) Число пробелов между каждыми двумя парами соседних слов не должно отличаться более, чем на 1.
 * 6) Число пробелов между более левой парой соседних слов должно быть больше или равно числу пробелов
 *    между более правой парой соседних слов.
 *
 * Следует учесть, что входной файл может содержать последовательности из нескольких пробелов  между слвоами. Такие
 * последовательности следует учитывать при выравнивании и при необходимости избавляться от лишних пробелов.
 * Из этого следуют следующие правила:
 * 7) В самой длинной строке каждая пара соседних слов должна быть отделена В ТОЧНОСТИ одним пробелом
 * 8) Если входной файл удовлетворяет требованиям 1-7, то он должен быть в точности идентичен выходному файлу
 */
fun alignFileByWidth(inputName: String, outputName: String) {
    val f = File(inputName).readLines()
    val a = File(outputName).bufferedWriter()
    var b = -1
    for (i in f) {
        if (i.trim().length > b) {
            b = i.trim().length
        }
    }
    for (j in f) {
        val w = j.split(" ").filter { it != "" }.toMutableList()
        if (w.size > 1) {
            while (b > w.joinToString("").length)
                for (i in 0 until w.size - 1)
                    if (b > w.joinToString("").length)
                        w[i] += " "
                    else
                        break
            a.write(w.joinToString(""))
        } else
            a.write(j.trim())
        a.newLine()
    }
    a.close()
}


/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * Вернуть ассоциативный массив, содержащий 20 наиболее часто встречающихся слов с их количеством.
 * Если в тексте менее 20 различных слов, вернуть все слова.
 *
 * Словом считается непрерывная последовательность из букв (кириллических,
 * либо латинских, без знаков препинания и цифр).
 * Цифры, пробелы, знаки препинания считаются разделителями слов:
 * Привет, привет42, привет!!! -привет?!
 * ^ В этой строчке слово привет встречается 4 раза.
 *
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 * Ключи в ассоциативном массиве должны быть в нижнем регистре.
 *
 */
fun top20Words(inputName: String): Map<String, Int> = TODO()

/**
 * Средняя
 *
 * Реализовать транслитерацию текста из входного файла в выходной файл посредством динамически задаваемых правил.

 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * В ассоциативном массиве dictionary содержится словарь, в котором некоторым символам
 * ставится в соответствие строчка из символов, например
 * mapOf('з' to "zz", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "yy", '!' to "!!!")
 *
 * Необходимо вывести в итоговый файл с именем outputName
 * содержимое текста с заменой всех символов из словаря на соответствующие им строки.
 *
 * При этом регистр символов в словаре должен игнорироваться,
 * но при выводе символ в верхнем регистре отображается в строку, начинающуюся с символа в верхнем регистре.
 *
 * Пример.
 * Входной текст: Здравствуй, мир!
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Пример 2.
 *
 * Входной текст: Здравствуй, мир!
 * Словарь: mapOf('з' to "zZ", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "YY", '!' to "!!!")
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.
 * Выбрать из данного словаря наиболее длинное слово,
 * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.
 * Вывести его в выходной файл с именем outputName.
 * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,
 * в выходной файл следует вывести их все через запятую.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 * Пример входного файла:
 * Карминовый
 * Боязливый
 * Некрасивый
 * Остроумный
 * БелогЛазый
 * ФиолетОвый

 * Соответствующий выходной файл:
 * Карминовый, Некрасивый
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе элементы текстовой разметки следующих типов:
 * - *текст в курсивном начертании* -- курсив
 * - **текст в полужирном начертании** -- полужирный
 * - ~~зачёркнутый текст~~ -- зачёркивание
 *
 * Следует вывести в выходной файл этот же текст в формате HTML:
 * - <i>текст в курсивном начертании</i>
 * - <b>текст в полужирном начертании</b>
 * - <s>зачёркнутый текст</s>
 *
 * Кроме того, все абзацы исходного текста, отделённые друг от друга пустыми строками, следует обернуть в теги <p>...</p>,
 * а весь текст целиком в теги <html><body>...</body></html>.
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 * Отдельно следует заметить, что открывающая последовательность из трёх звёздочек (***) должна трактоваться как "<b><i>"
 * и никак иначе.
 *
 * Пример входного файла:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * Соответствующий выходной файл:
<html>
    <body>
        <p>
            Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
            Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
        </p>
        <p>
            Suspendisse <s>et elit in enim tempus iaculis</s>.
        </p>
    </body>
</html>
 *
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlSimple(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе набор вложенных друг в друга списков.
 * Списки бывают двух типов: нумерованные и ненумерованные.
 *
 * Каждый элемент ненумерованного списка начинается с новой строки и символа '*', каждый элемент нумерованного списка --
 * с новой строки, числа и точки. Каждый элемент вложенного списка начинается с отступа из пробелов, на 4 пробела большего,
 * чем список-родитель. Максимально глубина вложенности списков может достигать 6. "Верхние" списки файла начинются
 * прямо с начала строки.
 *
 * Следует вывести этот же текст в выходной файл в формате HTML:
 * Нумерованный список:
 * <ol>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ol>
 *
 * Ненумерованный список:
 * <ul>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ul>
 *
 * Кроме того, весь текст целиком следует обернуть в теги <html><body>...</body></html>
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 *
 * Пример входного файла:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
* Утка по-пекински
    * Утка
    * Соус
* Салат Оливье
    1. Мясо
        * Или колбаса
    2. Майонез
    3. Картофель
    4. Что-то там ещё
* Помидоры
* Фрукты
    1. Бананы
    23. Яблоки
        1. Красные
        2. Зелёные
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 *
 *
 * Соответствующий выходной файл:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
<html>
  <body>
    <ul>
      <li>
        Утка по-пекински
        <ul>
          <li>Утка</li>
          <li>Соус</li>
        </ul>
      </li>
      <li>
        Салат Оливье
        <ol>
          <li>Мясо
            <ul>
              <li>
                  Или колбаса
              </li>
            </ul>
          </li>
          <li>Майонез</li>
          <li>Картофель</li>
          <li>Что-то там ещё</li>
        </ol>
      </li>
      <li>Помидоры</li>
      <li>
        Яблоки
        <ol>
          <li>Красные</li>
          <li>Зелёные</li>
        </ol>
      </li>
    </ul>
  </body>
</html>
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlLists(inputName: String, outputName: String) {
    TODO()
}

/**
 * Очень сложная
 *
 * Реализовать преобразования из двух предыдущих задач одновременно над одним и тем же файлом.
 * Следует помнить, что:
 * - Списки, отделённые друг от друга пустой строкой, являются разными и должны оказаться в разных параграфах выходного файла.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Вывести в выходной файл процесс умножения столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 111):
   19935
*    111
--------
   19935
+ 19935
+19935
--------
 2212785
 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 * Нули в множителе обрабатывать так же, как и остальные цифры:
  235
*  10
-----
    0
+235
-----
 2350
 *
 */
fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    val build = StringBuilder()
    fun loopAdd(char: Char, loop: Int): String {
        val builder = StringBuilder()
        var a = loop
        if (loop == 0) return ""
        while (a > 0) {
            builder.append(char)
            a--
        }
        return builder.toString()
    }

    val spisok = mutableListOf<Int>()
    var count = 10
    spisok.add(rhv % count)
    while (rhv / count > 0) {
        spisok.add(rhv / count % 10)
        count *= 10
    }
    spisok.reverse()
    val kolvo = (spisok[0] * 10.0.pow((rhv.toString().length - 1).toDouble()).toInt() * lhv).toString().length + 1
    build.append(loopAdd(' ', kolvo - lhv.toString().length) + lhv.toString() + "\n")
    build.append("*" + loopAdd(' ', kolvo - rhv.toString().length - 1) + rhv.toString() + "\n")
    build.append(loopAdd('-', kolvo) + "\n")
    var b = rhv.toString().length
    var counter = 0
    var spisokSize = spisok.size
    var x = false
    while (b > 0) {
        if (b != rhv.toString().length) {
            build.append('+')
            x = true
        }
        if (x) build.append(loopAdd(' ', kolvo - (spisok[spisokSize - 1] * lhv).toString().length - counter - 1) + (spisok[spisokSize - 1] * lhv).toString() + "\n")
        else build.append(loopAdd(' ', kolvo - (spisok[spisokSize - 1] * lhv).toString().length - counter) + (spisok[spisokSize - 1] * lhv).toString() + "\n")
        b--
        counter++
        spisokSize--
    }
    build.append(loopAdd('-', kolvo) + "\n")
    build.append(loopAdd(' ', kolvo - (lhv * rhv).toString().length) + (lhv * rhv).toString())
    writer.write(build.toString())
    writer.close()
}


/**
 * Сложная
 *
 * Вывести в выходной файл процесс деления столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 22):
  19935 | 22
 -198     906
 ----
    13
    -0
    --
    135
   -132
   ----
      3

 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 *
 */
fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {
    val outputStream = File(outputName).bufferedWriter()
    val res = lhv / rhv                     // результат от деления
    val ost = lhv % rhv                     // остаток от деления
    val lhvs = lhv.toString()               // lnh в виде строки

    /** Рассматриваем отдельно вариант, res - однозначное число **/
    if (res < 10) {
        outputStream.write(" $lhv | $rhv")                  // Просто сразу выписываем ответ в нужном формате
        outputStream.newLine()
        val lenght = 1 + lhvs.length
        var str = "-" + (rhv * res).toString()
        str = str.padEnd(lenght + 3) + "$res"
        outputStream.write(str)
        outputStream.newLine()
        str = "".padStart(max(lhvs.length, (rhv * res).toString().length + 1), '-')
        outputStream.write(str)
        outputStream.newLine()
        outputStream.write(" $ost")
        outputStream.close()
    } else {

        /** ЧАСТЬ 1 - вычисления **/
        var num = 0
        val ress = res.toString()                                       // ответ в виде строки
        val list1 = mutableListOf<Int>()                                // список из промежуточных результатов
        while (num != ress.length) {                                    // num - разряд ответа, номер промежуточного результата
            num++
            list1.add(ress[num - 1].toString().toInt() * rhv)           // разряд ответа * делитель
        }

        val listprob = mutableListOf<Int>()                                 // список с кол-вом пробелов, которые надо добавить после строки
        val list2 = mutableListOf<String>()                                 // список из вычислений
        num = 0
        val chislo = lhv / 10.0.pow(ress.length - 1).toInt()                // ищем первое число, которое поделилось
        var podstroka = lhvs.substring(lhvs.length - ress.length + 1)
        val value = if (chislo - list1[num] == 0)                           // сохраняем 0 в начале, если он есть
            0.toString() + podstroka[0].toString()                          // (именно поэтому list2 типа String)
        else
            ((chislo - list1[num]) * 10 + podstroka[0].toString().toInt()).toString()
        list2.add(value)
        listprob.add(podstroka.length)
        num++
        while (num != ress.length - 1) {                                      // вычисляем со 2-го до предпоследнего
            podstroka = lhvs.substring(lhvs.length - ress.length + 1 + num)
            val a = if ((list2[num - 1])[0].toString().toInt() == 0)          // убираем ноль в начале, если он есть,
                list2[num - 1].substring(1).toInt()                           // чтобы можно было перевести в Int
            else
                list2[num - 1].toInt()
            val value = if (a - list1[num] == 0)                              // сохраняем 0, если он получился при вычитании
                0.toString() + podstroka[0].toString()
            else
                ((a - list1[num]) * 10 + podstroka[0].toString().toInt()).toString()
            list2.add(value)
            listprob.add(podstroka.length)
            num++
        }
        list2.add(ost.toString())                         // последнее число - остаток
        listprob.add(0)


        /** ЧАСТЬ 2 - вывод в нужном формате **/
        val lenght = 1 + lhvs.length                                // длина строк, начиная с 3-ей
        outputStream.write(" $lhv | $rhv")                          // добавляем первую строку
        outputStream.newLine()
        var str = "-${list1[0]}"
        str = str.padEnd(lenght + 3) + "$res"                       // добавляем вторую строку отдельно,
        outputStream.write(str)                                     // потому что на этой же строке ещё и ответ
        outputStream.newLine()
        str = "".padEnd(list1[0].toString().length + 1, '-')        // добавляем третью строку отдельно,
        outputStream.write(str)                                     // потому что её не загнать в цикл
        outputStream.newLine()
        for (i in 1 until ress.length) {                            // через цикл записываем строки тройками
            str = list2[i - 1]
            str = str.padStart(lenght - listprob[i])                // строка с остатком от вычитания и добавленный разрядом
            outputStream.write(str)
            outputStream.newLine()
            str = "-${list1[i]}"                                    // строка с вычитаемым
            str = str.padStart(lenght - listprob[i])
            outputStream.write(str)
            outputStream.newLine()
            str = "".padStart(max(list1[i].toString().length + 1, list2[i - 1].length), '-')
            str = str.padStart(lenght - listprob[i])                // строка с чёрточками
            outputStream.write(str)
            outputStream.newLine()
        }

        str = "$ost".padStart(lenght)                               // отдельно записываем остаток
        outputStream.write(str)
        outputStream.close()
    }
}

