# YLab_University
Репозиторий создан для проверки ДЗ на проекте YLab_University

**Задание №1 ==> Ввести с клавиатуры в консоль порядковый номер числа Фибоначчи и вывести его на экран.**

Реализовал решение задачи на нахождение числа Фибоначчи несколькими способами.

_Fibonacci_v1.java_ - решение через обычную рекурсию.

_Fibonacci_v2.java_ - решение с использованием цикла.

_Fibonacci_v3.java_ - решение через формулу Бине.

_Fibonacci_v4.java_- решение с использованием мемоизации.

_Fibonacci_v5.java_ - решение с предварительной инициализацией массива (тип "int").

_Fibonacci_v6.java_ - решение с предварительной инициализацией массива (класс BigInteger).


[Решение первого задания.](https://github.com/Ruvino/YLab_University/tree/master/src/com/Ruvino/YLabUniversity/Week1)

**Задание №2 ==> Реализовать игру крестики-нолики друг с другом.**

_StartGame.java_ - класс для запуска игры.

[Решение второго задания.](https://github.com/Ruvino/YLab_University/tree/master/src/com/Ruvino/YLabUniversity/Week2)

**Задание №3 ==> Реализовать запись игры в формате XML. Реализовать воспроизведение игры из файла XML**

_MyXMLReader_ - класс для воспроизведения игры.

[Решение третьего задания.](https://github.com/Ruvino/YLab_University/tree/master/src/com/Ruvino/YLabUniversity/Week3)

**Задание №4 ==> Реализовать запись игры в формате JSON. Реализовать воспроизведение игры из файла JSON**

_MyJSONReader_ - класс для воспроизведения игры.

[Решение четвертого задания.](https://github.com/Ruvino/YLab_University/tree/master/src/com/Ruvino/YLabUniversity/Week4)

**Задание №4.1 ==> Загрузить файл c результатами игры на сервер. Воспроизвести игру на сервере. Выдать ответ клиенту кто победил**

Правила проверки:

1) Для запуска игры перейдите в пакет com.Ruvino.YLabUniversity.Week2 и запустите main(String[] args) из файла StartGame.java

2) Сыграйте хотя бы одну игру.

3) Файл с игрой сохранится в корне проекта.

4) Запустить сервер.

5) Для запуска сервера необходимо запустить psvm класса "MyReaderLocalHost", который находится в пакете com.Ruvino.YLabUniversity.Week4_1

6) После запуска сервера перейти в браузер и в адресной строке ввести: http://localhost:4567

7) Загрузить файл сыгранной игры (находится в корне проекта), нажать кнопку "Отправить файл".

8) Дождаться ответа от сервера.

[Решение четвертого задания со звёздочкой.](https://github.com/Ruvino/YLab_University/tree/master/src/com/Ruvino/YLabUniversity/Week4_1)
