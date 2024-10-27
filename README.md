# Java Algorithms Project

## SonarCloud
[Ссылка на результаты анализа кода SonarCloud](https://sonarcloud.io/project/overview?id=kz306438_TaskSolutions)

## Навигация по разделам

### Раздел 1.3: Контейнеры, очереди, стеки.

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
| Task 2     | 2.5.18. Обеспечение устойчивости. Напишите метод-оболочку, который делает устойчи- вой любую сортировку. Для этого он создает новый тип ключа, составленный из старого ключа и его индекса в массиве, вызывает метод sort(), а затем восста- навливает исходные ключи. | [Task 2](https://github.com/kz306438/TaskSolutions/tree/main/Task2_2) | 4           | 5           |
| Task 3     | 2.5.24. Устойчивая очередь с приоритетами. Разработайте устойчивую реализацию оче- реди с приоритетами (которая возвращает одинаковые ключи в порядке их вставки). | [Task 3](https://github.com/kz306438/TaskSolutions/tree/main/Task2_3) | 3           | 2           |
| Task 4     | 2.5.29. Сортировка файлов по размеру и дате последнего изменения. Напишите компара- торы для типа File, которые позволяют сортировать файлы по возрастанию убыванию их размера, по возрастанию/убыванию их имен и по возрастанию/ убыванию их даты последнего изменения. Используйте эти компараторы в про- грамме LЅ, которая принимает из командной строки имя каталога и выводит имена всех находящихся там файлов, упорядоченные по заданному критерию - например, по отметке времени при наличии параметра "-t". Программа должна поддерживать несколько параметров для более точного упорядочения и исполь- зовать устойчивую сортировку. | [Task 4](https://github.com/kz306438/TaskSolutions/tree/main/Task2_4) | 4           | 3           |

