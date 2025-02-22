# Java Algorithms Project

## SonarCloud
[Ссылка на результаты анализа кода SonarCloud](https://sonarcloud.io/project/overview?id=kz306438_TaskSolutions)

## Навигация по разделам

### 1.3 Контейнеры, очереди, стеки.

| Задача     | Описание        | Директория с кодом  | План (часы) | Факт (часы) |
|------------|-----------------|---------------------|-------------|-------------|
| Task 1     | 1.3.14. Разработайте класс ResizingArrayQueueOfStrings, который реализует абстракцию очереди с массивом фиксированного размера, а потом добавьте в полученную реализацию изменение размера массива, чтобы снять ограничение на размер. | [Task 1](https://github.com/kz306438/TaskSolutions/tree/main/Task1_1) | 3           | 3           |
| Task 2     | 1.3.33. Дек. Очередь с двумя концами, или дек (double-ended queue - deque), похожа на стек или очередь, но поддерживает добавление и удаление элементов с обо- их концов. Дек хранит коллекцию элементов и поддерживает следующий АРІ- интерфейс: создание пустого дека, пуст ли дек? количество элементов в деке, добавление элемента с левого конца, добавление элемента с правого конца, удаление элемента с левого конца, удаление элемента с правого конца. Напишите класс Deque, который реализует этот API-интерфейс с помощью двухсвязного списка, и класс ResizingArrayDeque, который использует массив с переменным размером. | [Task 2](https://github.com/kz306438/TaskSolutions/tree/main/Task1_2) | 4           | 5           |
| Task 3     | 1.3.32. Стеко-очередь. Очередь с элементами поведения стека, или стеко-очередь тип данных, поддерживающий операции втолкнуть, вытолкнуть и занести. Сформулируйте API-интерфейс для такого АТД. Разработайте реализацию на основе связного списка. | [Task 3](https://github.com/kz306438/TaskSolutions/tree/main/Task1_3) | 3           | 2           |
| Task 4     | 1.3.49. Очередь на основе стеков. Реализуйте очередь с помощью фиксированного количества стеков, чтобы каждая операция с очередью требовала выполнения по- стоянного (в худшем случае) количества операций со стеками. Внимание: задача очень сложная! | [Task 4](https://github.com/kz306438/TaskSolutions/tree/main/Task1_4) | 4           | 3           |

### 2.5 Применение сортировок.

| Задача     | Описание        | Директория с кодом  | План (часы) | Факт (часы) |
|------------|-----------------|---------------------|-------------|-------------|
| Task 1     | 2.5.16. Выборы без предпочтений. Чтобы не ущемлять кандидатов, имена которых нахо- дятся в конце алфавита, на выборах губернатора Калифорнии в 2003 г. их упо- рядочили с помощью следующего набора символов: R W Q O J M V A H B S G Z X N T C I E K U P D Y F L. Создайте тип данных, где этот порядок является естественным, и напишите клиент California с единственным статическим методом main(), который упорядочивает строки в таком порядке. Считайте, что все строки содержат только прописные буквы. | [Task 1](https://github.com/kz306438/TaskSolutions/tree/main/Task2_1) | 3           | 3           |
| Task 2     | 2.5.18. Обеспечение устойчивости. Напишите метод-оболочку, который делает устойчи- вой любую сортировку. Для этого он создает новый тип ключа, составленный из старого ключа и его индекса в массиве, вызывает метод sort(), а затем восста- навливает исходные ключи. | [Task 2](https://github.com/kz306438/TaskSolutions/tree/main/Task2_2) | 2           | 2           |
| Task 3     | 2.5.24. Устойчивая очередь с приоритетами. Разработайте устойчивую реализацию очереди с приоритетами (которая возвращает одинаковые ключи в порядке их вставки). | [Task 3](https://github.com/kz306438/TaskSolutions/tree/main/Task2_3) | 5           | 5           |
| Task 4     | 2.5.29. Сортировка файлов по размеру и дате последнего изменения. Напишите компара- торы для типа File, которые позволяют сортировать файлы по возрастанию убыванию их размера, по возрастанию/убыванию их имен и по возрастанию/ убыванию их даты последнего изменения. Используйте эти компараторы в про- грамме LЅ, которая принимает из командной строки имя каталога и выводит имена всех находящихся там файлов, упорядоченные по заданному критерию - например, по отметке времени при наличии параметра "-t". Программа должна поддерживать несколько параметров для более точного упорядочения и исполь- зовать устойчивую сортировку. | [Task 4](https://github.com/kz306438/TaskSolutions/tree/main/Task2_4) | 4           | 4           |

### 3.2 Деревья бинарного поиска

| Задача     | Описание        | Директория с кодом  | План (часы) | Факт (часы) |
|------------|-----------------|---------------------|-------------|-------------|
| Task 1     | 3.2.14. Приведите нерекурсивные реализации методов min(), max(), floor(), ceiling(), rank() и select(). | [Task 1](https://github.com/kz306438/TaskSolutions/tree/main/Task3_1) | 6           | 6           |
| Task 2     | 3.2.30. Проверка упорядоченности. Напишите рекурсивный метод isordered(), который принимает в качестве аргумента узел Node и два ключа min и max и возвращает true, если значения всех ключей в дереве находятся между значениями min и max (т.е. это наименьший и наибольший ключи в дереве) и свойство упорядоченности ДБП верно для всех поддеревьев указанного дерева; иначе метод должен возвратить false. | [Task 2](https://github.com/kz306438/TaskSolutions/tree/main/Task3_2) | 3           | 2           |
| Task 3     | 3.2.34. Связывание. Реализуйте расширенный API ThreadedST, который поддерживает две дополнительные операции, выполняемые за константное время: Key next(Key key) ключ, следующий за key (null, если key наибольший); Key prev(Key key) ключ, предшествующий key (null, если key наименьший) Для этого добавьте в структуру Node поля pred и succ, содержащие ссылки на предшествующий и последующий узлы, и добавьте в методы put(), deleteMin(), deleteMax() и delete() поддержку этих полей. | [Task 3](https://github.com/kz306438/TaskSolutions/tree/main/Task3_3) | 4           | 6           |
| Task 4     | 3.2.37. Поуровневый обход. Напишите метод printLevel(), который принимает в качест- ве аргумента узел Node и выводит ключи из поддерева с корнем в указанном узле по уровням т.е. в порядке их расстояния до корня, а для узлов с одинаковым расстоянием в порядке слева направо. Совет: воспользуйтесь классом Queue. | [Task 4](https://github.com/kz306438/TaskSolutions/tree/main/Task3_4) | 2           | 2           |

### 3.3 Сбалансированные деревья поиска.

| Задача     | Описание        | Директория с кодом  | План (часы) | Факт (часы) |
|------------|-----------------|---------------------|-------------|-------------|
| Task 1     | 3.3.35. 2-3-деревья. Напишите программу TwoThreeST.java, которая использует два типа узлов для непосредственной реализации 2-3-деревьев поиска. | [Task 1](https://github.com/kz306438/TaskSolutions/tree/main/Task4_1) | 10           | 15           |
| Task 2     | 3.3.40. Удаление наибольшего. Реализуйте операцию deleteMax() для красно-черных ДБП. | [Task 2](https://github.com/kz306438/TaskSolutions/tree/main/Task4_2) | 4           | 6           |
| Task 3     | 3.3.32. АВЛ-деревья. АВЛ-дерево представляет собой ДБП, в котором высота каждого узла отличается от высоты его родственного узла не более чем на 1. (Самые старые алгоритмы работы со сбалансированными деревьями основаны на разворотах, поддерживающих баланс высот в АВЛ- деревьях.) Разработайте реализацию API таблицы имен, основанной на этой структуре данных. | [Task 3](https://github.com/kz306438/TaskSolutions/tree/main/Task4_3) | 4           | 4           |
| Task 4     | 3.3.30. Программное кеширование. Добавьте в класс RedBlackBST хранение последнего узла, к которому было обращение, в переменной экземпляров, чтобы иметь доступ к этому узлу за постоянное время, если следующая операция put() или get() использует тот же ключ. | [Task 4](https://github.com/kz306438/TaskSolutions/tree/main/Task4_4) | 3           | 4           |

### 3.4 Хеш-таблицы

| Задача     | Описание        | Директория с кодом  | План (часы) | Факт (часы) |
|------------|-----------------|---------------------|-------------|-------------|
| Task 1     | 3.4.19. Реализуйте метод keys() для классов SeparateChainingHashST и Linear ProbingHashST. | [Task 1](https://github.com/kz306438/TaskSolutions/tree/main/Task5_1) | 8          | 8           |
| Task 2     | 3.4.26. Ленивое удаление для линейного опробования. Добавьте в класс LinearProbingHashЅT метод delete(), удаляющий пару ключ-значение с помощью занесения значения null (но без удаления ключа). Такая пара удаляется из таблицы при изменении ее размера методом resize(). Главная проблема решить, когда вызвать resize(). | [Task 2](https://github.com/kz306438/TaskSolutions/tree/main/Task5_2) | 4           | 3           |
| Task 3     | 3.4.28. Двойное хеширование. Добавьте в класс LinearProbingHashST использование второй хеш-функции, которая задает последовательность проб. А именно, замените оба выражения (1 + 1) % М на (і + k) % М, где к ненулевое число, зависящее от ключа и взаимно простое с М. Примечание: последнее условие удовлетворяется автоматически, если м простое. Приведите трассировку процесса вставки ключей E A Ѕ Y QU T I O N в указанном порядке в первоначально пустую таблицу размером М = 11 и с хеш-функциями из предыдущего упражнения. | [Task 3](https://github.com/kz306438/TaskSolutions/tree/main/Task5_3) | 4           | 3           |
| Task 4     | 3.4.31. Кукушкино хеширование. Разработайте реализацию таблицы имен, в которой используются две хеш-таблицы и две хеш-функции. Любой заданный ключ может находиться в одной из таблиц, но не в обеих. При вставке нового ключа хешируйте его в одну из таблиц. Если позиция в этой таблице занята, замените ключ в ней новым ключом, а старый ключ хешируйте в другую таблицу (где опять возможно вышвыривание ключа, который находится там). Если процесс зациклился, начните его снова. Поддерживайте заполнение таблиц менее чем наполовину. Этот метод использует константное количество проверок на равенство в худшем случае для поиска (в обычном смысле) и амортизированно константное время для вставок. | [Task 4](https://github.com/kz306438/TaskSolutions/tree/main/Task5_4) | 4           | 3           |

### 5.1 Сортировка строк

| Задача     | Описание        | Директория с кодом  | План (часы) | Факт (часы) |
|------------|-----------------|---------------------|-------------|-------------|
| Task 1     | 5.1.9. Разработайте реализацию LSD-сортировки строк, которая способна работать со строками переменной длины.  | [Task 1](https://github.com/kz306438/TaskSolutions/tree/main/Task6_1) | 2          | 2           |
| Task 2     | 5.1.11. Сортировка с помощью очередей. Реализуйте MSD-сортировку строк с помощью очередей. Для каждого кармана используйте отдельную очередь. На первом проходе по сортируемым элементам вставляйте каждый элемент в очередь, соответствующую значению ее старшего символа. Потом отсортируйте подсписки и объедините содержимое очередей в единый результат. Обратите внимание, что в этом методе не требуется хранить массивы count[] внутри рекурсивного метода. | [Task 2](https://github.com/kz306438/TaskSolutions/tree/main/Task6_2) | 2           | 3           |
| Task 3     | 5.1.12. Алфавит. Разработайте реализацию API Alphabet, приведенного на рис. 5.0.2, и воспользуйтесь ей для разработки LSD- и MSD-сортировки строк из произвольных алфавитов.  | [Task 3](https://github.com/kz306438/TaskSolutions/tree/main/Task6_3) | 4           | 4           |
| Task 4     | 5.1.15. Сублинейная сортировка. Разработайте реализацию сортировки для целочисленных значений, которая выполняет два прохода по сортируемому массиву: LSD- сортировка по старшим 16 битам ключей, а затем сортировка вставками. | [Task 4](https://github.com/kz306438/TaskSolutions/tree/main/Task6_4) | 3           | 3           |

### 5.2 Trie-деревья

| Задача     | Описание        | Директория с кодом  | План (часы) | Факт (часы) |
|------------|-----------------|---------------------|-------------|-------------|
| Task 1     | 5.2.10. Размер. Реализуйте "очень энергичный" метод size() (который хранит в каж- дом узле количество ключей в поддереве этого узла) для классов TrieST и TST. | [Task 1](https://github.com/kz306438/TaskSolutions/tree/main/Task7_1) | 8          | 6           |
| Task 2     | 5.2.9. Расширенные операции для ТТП. Реализуйте метод keys() и расширенные операции, описанные в данном разделе - longestPrefixOf(), keysWithPrefix() и keysThatMatch() Для класса TST. | [Task 2](https://github.com/kz306438/TaskSolutions/tree/main/Task7_2) | 4           | 4           |
| Task 3     | 5.2.14. Уникальные подстроки длиной L. Напишите клиент TST, который вводит текст из стандартного ввода и подсчитывает в нем количество уникальных подстрок длиной . Например, входной текст cggggсgcg содержит пять уникальных подстрок длиной 3 - cgc, cgg, gcg, ggc и ggg. Совет: выберите 1-ю подстроку с помощью вызова substring(i, i + L) и вставьте ее в таблицу имен. | [Task 3](https://github.com/kz306438/TaskSolutions/tree/main/Task7_3) | 2           | 2           |
| Task 4     | 5.2.13. Гибридное ТТП с R2-частным ветвлением в корне. Добавьте в реализацию TST код для выполнения многочастного ветвления на первых двух уровнях, как описано в тексте. | [Task 4](https://github.com/kz306438/TaskSolutions/tree/main/Task7_4) | 6           | 5           |

### 5.3 Поиск подстрок

| Задача     | Описание        | Директория с кодом  | План (часы) | Факт (часы) |
|------------|-----------------|---------------------|-------------|-------------|
| Task 1     | 5.3.24. Поиск всех вхождений. Добавьте во все четыре алгоритма поиска подстроки, приведенные в тексте, метод findAll(), который возвращает объект Iterable<Integer>, позволяющий клиентам перебрать все позиции, где образец встречается в тексте. | [Task 1](https://github.com/kz306438/TaskSolutions/tree/main/Task8_1) | 7           | 6           |
| Task 2     | 5.3.32. Уникальные подстроки. Выполните упражнение 5.2.14 с помощью принципа, на котором основан метод Рабина-Карпа. | [Task 2](https://github.com/kz306438/TaskSolutions/tree/main/Task8_2) | 3           | 3           |
| Task 3     | 5.3.35. Поиск Бойера-Мура в двоичных строках. Эвристика несовпадающего символа не особенно годится для двоичных строк, т.к. есть лишь два возможных символа, которые могут привести к несоответствию, и они присутствуют в образце с примерно одинаковой вероятностью. Разработайте класс поиска подстроки для двоичных строк и групп битов, в котором используются "символы" из нескольких битов, в точности как в алгоритме 5.7. | [Task 3](https://github.com/kz306438/TaskSolutions/tree/main/Task8_3) | 3           | 4           |
| Task 4     | 5.3.26. Проверка наличия циклических перестановок. Напишите программу, которая для заданных двух строк определяет, является ли одна из них циклической перестановкой другой например, пальто и топаль. | [Task 4](https://github.com/kz306438/TaskSolutions/tree/main/Task8_4) | 2           | 2           |

### 5.4 Регулярные выражения

| Задача     | Описание        | Директория с кодом  | План (часы) | Факт (часы) |
|------------|-----------------|---------------------|-------------|-------------|
| Task 1     | 5.4.17. Обобщенные символы. Добавьте в класс NFA обработку обобщенных символов. | [Task 1](https://github.com/kz306438/TaskSolutions/tree/main/Task9_1) | 6           | 6           |
| Task 2     | 5.4.16. Многочастное или. Добавьте в НКА многочастные операции или.| [Task 2](https://github.com/kz306438/TaskSolutions/tree/main/Task9_2) | 3           | 2           |
| Task 3     | 5.4.20. Диапазон. Добавьте в класс NFA обработку описателей диапазонов. | [Task 3](https://github.com/kz306438/TaskSolutions/tree/main/Task9_3) | 3           | 4           |
| Task 4     | 5.4.18. Один или несколько. Добавьте в класс NFA обработку операции замыкания +. | [Task 4](https://github.com/kz306438/TaskSolutions/tree/main/Task9_4) | 2           | 2           |


