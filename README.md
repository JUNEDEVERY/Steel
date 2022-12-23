# Обучающая программа натему "Построение канонической задачи минимизации и результатов первой симплекс-таблицы".
Данная программа предназначена обучать студента на сгенерированных примерах, а так же проверять полученные знания с предварительным отображением результата тестирования. Решение программы и вся логика взаимодействия содержимого кода предназначена для решения функций симплекс метода. [Подробнее тут.](https://habr.com/ru/post/474286/)
## Начало работы
Для использования данной программы, необходимо проделать следующий алгоритм действий:
* Открыть репозиторий по [ссылке](https://github.com/JUNEDEVERY/ApplicationProjectSimple).
* Нажать на кнопку "Code".
* Затем, после открывшегося выпадающего списка необходимо выбрать любой из последних пунктов ( Open with Visual Studio/Download ZIP"). В первом случае у нас клонируется репозиторий непосредственно в проект Visual Studio, а во втором, у нас скачается проект в виде .zip файла, который необходимо разархивировать любым архиватором в любое место на жестком диске, после запустить
      
![logo](https://github.com/JUNEDEVERY/ApplicationProjectSimple/blob/master/EditCode/Resources/imgDownloads.png)
     
### Необходимые условия
Для работы с программой необходимо наличие программной среды Visual Studio, а также следующих аппаратных требований, описанных в таблице, приведенной ниже.
| Компонент | Описание |
| --- | --- |
| Процессор | Процессор с тактовой частотой не ниже 1,8 ГГц. Рекомендуется использовать как минимум двухъядерный процессор. |
| ОЗУ | 2 ГБ ОЗУ; рекомендуется 8 ГБ ОЗУ (минимум 2,5 ГБ при выполнении на виртуальной машине) |
| Жесткий диск | Место на жестком диске: до 210 ГБ (минимум 800 МБ) свободного места в зависимости от установленных компонентов; обычно для установки требуется от 20 до 50 ГБ свободного места. Скорость жесткого диска: для повышения производительности установите Windows и Visual Studio на твердотельный накопитель (SSD) |
| Видеоадаптер | Видеоадаптер с минимальным разрешением 720p (1280 на 720 пикселей); для оптимальной работы Visual Studio рекомендуется разрешение WXGA (1366 на 768 пикселей) или более высокое. |

**Visual Studio 2022** (последняя версия) поддерживается в следующих операционных системах x64-разрядных систем:
+ ***Windows 11*** версии 21H2 или более поздней: Домашняя, Pro, Pro для образовательных учреждений, Pro для рабочих станций, Корпоративная и для образовательных учреждений;
+ ***Windows 10*** версии 1909 или более поздних версий: Домашняя, Профессиональная, для образовательных учреждений и Корпоративная;
+ ***Windows Server 2022***: Standard и Datacenter;
+ ***Windows Server 2019***: Standard и Datacenter;
+ ***Windows Server 2016***: Standard и Datacenter.
### Установка
Для установки и последующих манипуляций с программой необходимо проделать следующие шаги, указанные ниже.
1. Открыть [ссылку с проектом](https://github.com/JUNEDEVERY/ApplicationProjectSimple).
2. Выбрать "Open with Visual Studio или Download ZIP"
2.1. В случае, если выбирается вариант Open with Visual Studio, необходимо разрешить браузеру открывать подобные ссылки (в случае, если в Вашем браузере будет подобное предупреждение)

![logo](https://github.com/JUNEDEVERY/ApplicationProjectSimple/blob/master/EditCode/Resources/first.png)

2.1.1 Затем, выбрать путь на диске, куда будет расположен проект и нажать на кнопку клонировать. 

![logo](https://github.com/JUNEDEVERY/ApplicationProjectSimple/blob/master/EditCode/Resources/second.png)

2.1.2 Откроется код программы, после чего необходимо нажать на кнопку запуска проекта и следовать инструкции в консольном окне. 

2.2. В случае, если выбирается вариант Download ZIP, скачается проект программы.

![logo](https://github.com/JUNEDEVERY/ApplicationProjectSimple/blob/master/EditCode/Resources/third.png)

2.2.1 Необходимо распаковать архив любым способом:
   * нажать ПКМ на архив;
   * выбрать нужный архиватор;
   * нажать распаковать в папку;
   
![logo](https://github.com/JUNEDEVERY/ApplicationProjectSimple/blob/master/EditCode/Resources/four.png)

2.2.2. Запустить проект с помощью программы **Microsoft Visual Studio**.

2.3. Так же, после распаковки архива, можно запускать .exe файл из папки решения. [Более подробно тут.](https://www.youtube.com/watch?v=iIer4g23Bqk)

## Основные механизмы

</b></details>
<details>
<summary>Часть процесс определения канонической задачи минимизации и построения первой симплекс таблицы </summary><br><b>

````C#
   public static void GenerateModelwithStable(int[,] numberOfResources, int[] targetFunction, int[] reserveResource)
        {
            Console.WriteLine("Для вас представлена математическая модель\n");
            Console.Write("F = "); //Формирование целевой функции из введенных данных
            for (int i = 0; i < targetFunction.Length; i++)
            {
                Console.Write($"{targetFunction[i]}x{i + 1} ");
                if (i != targetFunction.Length - 1) // i - индекса x.
                                                    // если индекса икса не равен длине введенных коэффициентов функции, в которой вычли -1
                                                    // условно говоря, осуществляется проверка на последний х
                                                    // если икс последний ставим ему +макс
                {
                    Console.Write("+ ");
                }
                else
                {
                    Console.Write("-> max");
                }

            }
            Console.WriteLine();
            for (int i = 0; i < numberOfResources.GetLength(0); i++) // 0 строки
            {
                for (int j = 0; j < numberOfResources.GetLength(1); j++) // 1 - столбцы
                {

                    Console.Write($"{numberOfResources[i, j]}x{j + 1} "); // т.к индекс массива с нуля. мы ставим +1 для того чтобы в уравнении начинать не с х0, а с х1
                    /// добавлены фигурные скобки (требование использовать конструкию if с фигурными скобками)
                    if (j != numberOfResources.GetLength(1) - 1) // j - индекс икса помощь от андрея с обьясниненим в painte                                              
                    {
                        Console.Write("+ ");
                    }
                    /// добавлены фигурные скобки (требование использовать конструкию if с фигурными скобками)
                    else
                    {
                        Console.WriteLine($"<= {reserveResource[i]}");
                    }
                }

            }
            for (int i = 0; i < targetFunction.Length; i++) // цикл который идет до длины целевой функции, от которой вычли -1.
                                                            // т.к если мы не вычтем -1, то он продолжит ставить индексы к иксу. т.к у нас всего х1 и х2, нам необходимо поставить -1, дабы он не начал ставить х3.
            {
                Console.Write($"x{i + 1}"); // вывод строки с граничными условиями
                /// добавлены фигурные скобки (требование использовать конструкию if с фигурными скобками)
                if (i != targetFunction.Length - 1)
                {
                    Console.Write(", ");
                }
                /// добавлены фигурные скобки (требование использовать конструкию if с фигурными скобками)
                else
                {
                    Console.Write(" >=0; ");
                }

            }

            Console.Write("\n\nF` = -(");
            for (int i = 0; i < targetFunction.Length; i++)
            {
                Console.Write($"{targetFunction[i]}x{i + 1}");
                /// добавлены фигурные скобки (требование использовать конструкию if с фигурными скобками)
                if (i != targetFunction.Length - 1) // i - индекса x
                                                    // аналогичная проверка что и выше
                {
                    Console.Write(" + ");
                }
                /// добавлены фигурные скобки (требование использовать конструкию if с фигурными скобками)
                else
                {
                    Console.Write(") -> min");
                }

            }
            Console.WriteLine();
            /// изменено название перменной
            int dummyVariable = numberOfResources.GetLength(1) + 1; // индекс фиктивной переменной с количеством столбцов
            for (int i = 0; i < numberOfResources.GetLength(0); i++)
            {
                for (int j = 0; j < numberOfResources.GetLength(1); j++)
                {
                    Console.Write($"{numberOfResources[i, j]}x{j + 1}");
                    /// добавлены фигурные скобки (требование использовать конструкию if с фигурными скобками)
                    if (j != numberOfResources.GetLength(1) - 1) // j - индекс икса
                    {
                        Console.Write(" + ");
                    }
                    /// добавлены фигурные скобки (требование использовать конструкию if с фигурными скобками)
                    else
                    {
                        Console.WriteLine($" + x{dummyVariable} = {reserveResource[i]}");
                        // для того, чтобы в каждой строке прибавлялся индекс фиктивной переменной +1
                        dummyVariable = 1 + dummyVariable; // тоже попросить андрея в paint
                    }
                }
            }
            for (int i = 0; i <= targetFunction.Length - 1; i++) // аналогичный цикл с условием выше
            {
                Console.Write($"x{i + 1}"); // вывод строки с граничными условиями
                /// добавлены фигурные скобки (требование использовать конструкию if с фигурными скобками)
                if (i != targetFunction.Length - 1)
                {
                    Console.Write(", ");
                }
                else
                {
                    Console.Write(" >=0; ");
                }
            }
            for (int i = numberOfResources.GetLength(1) + 1; i < reserveResource.Length + numberOfResources.GetLength(1) + 1; i++)
            // т.к у нас всего х1 и х2, нам необходимо начать цикл со следующего - т.е х3
            //цикл продолжаем до последнего х

            {
                Console.Write($"x{i}"); // вывод строки с граничными условиями
                /// добавлены фигурные скобки (требование использовать конструкию if с фигурными скобками)
                if (i != reserveResource.Length + numberOfResources.GetLength(1))
                {
                    Console.Write(", ");
                }
                else
                {
                    Console.Write(" - любое");
                }
            }
            // Этап формирования таблицы
            Console.WriteLine();
            double[,] table1 = new double[reserveResource.Length + 1, numberOfResources.GetLength(1) + numberOfResources.GetLength(0) + 1];
            // количество строк зависит от запаса ( видов ресурсов) +1 для получения строки оценок
            // kolresnaedproduc.GetLength(1) - первый 2 столбца зависят от количества видов продукции (p1 и p2)
            // kolresnaedproduc.GetLength(0) - следующие три столбца для фиктивных переменных. они формируются от количества видов ресурсов
            // сколько строк столько и столбцов с фиктивными переменными
            // +1 добавление столбца запаса ресурсов

            for (int i = 0; i < numberOfResources.GetLength(0); i++)
            {
                for (int j = 0; j < numberOfResources.GetLength(1); j++)
                {
                    table1[i, j] = numberOfResources[i, j];
                    // приравниваем значения первых двух столбцов
                }
            }
            for (int i = 0; i < targetFunction.Length; i++) // заполнение строки оценок
            {
                table1[table1.GetLength(0) - 1, i] = targetFunction[i];
            }

            for (int i = 0; i < reserveResource.Length; i++)
            {
                table1[i, table1.GetLength(1) - 1] = reserveResource[i];
            }
            for (int i = 0; i < table1.GetLength(0) - 1; i++)
            {
                // цикл по i берет все кроме последней строки оценок
                for (int j = numberOfResources.GetLength(1); j < numberOfResources.GetLength(0) * 2 - 1; j++)
                {
                    if (i == j - numberOfResources.GetLength(1)) table1[i, j] = 1;
                }
            }
            Console.WriteLine();
            for (int i = 0; i < table1.GetLength(0); i++)
            {
                for (int j = 0; j < table1.GetLength(1); j++)
                {
                    Console.Write(table1[i, j] + " ");
                }
                Console.WriteLine();
            }
            // Вывод промежуточных результатов
            Console.WriteLine("\nПромежуточные результаты");
            Console.WriteLine($"F`={table1[table1.GetLength(0) - 1, table1.GetLength(1) - 1]}");
            Console.WriteLine($"F={Math.Abs(table1[table1.GetLength(0) - 1, table1.GetLength(1) - 1])}");
            for (int d = 0; d < targetFunction.Length; d++)
            {
                Console.WriteLine($"x{d + 1} = 0");
            }
            for (int i = 0; i < numberOfResources.GetLength(0); i++)
            {
                Console.WriteLine($"x{numberOfResources.GetLength(1) + 1 + i} = {reserveResource[i]} ");
            }

        }
````
                                                               
</b></details>
Каноническая задача минимизации (КЗМ) – это задача, целевая функция которой всегда стремится к минимуму, а в ограничениях вместо неравенств всегда стоят равенства. Этого добиваются путём специальных преобразований.
      
Далее выбирают любой опорный план с некоторым набором базисных переменных. Потом определяют свободную переменную, которую нужно включить в базис, чтобы при такой замене произошло наибольшее увеличение целевой функции. Определяют переменную, выходящую из базиса и с помощью линейных преобразований, совершают переход к новым базисным переменным. В результате получают новый план, значение целевой функции которого ближе к экстремальному. Процесс повторяют до тех пор, пока целевая функция не достигнет экстремального значения.
      
В геометрической интерпретации это означает следующее.
1. Вначале мы выбираем любую вершину многогранника ОДР.
2. Добавляя в базис новую переменную, выбираем направление до смежной вершины вдоль ребра многогранника, двигаясь по которому целевая функция наиболее быстро возрастает.
3. Переходим на новую вершину по выбранному в пункте 2 направлению, исключая из базиса одну из переменных.
4. Повторяем пункты 2 и 3, пока не достигнем экстремума

</b></details>
<details>
      
<summary> Часть кода, запускающий функцию канонической задачи минимизации первой симплекс таблицы </summary><br><b>
      
````C#
switch (changeVariable)
                {
                    case 1:
                        int[,] numberOfResources = new int[,] { { 6, 6 }, { 4, 2 }, { 4, 8 } }; 
                        int[] targetFunction = new int[] { 12, 15 }; 
                        int[] stockOfProducts = new int[] { 36, 20, 40 };
                        Console.Write("F = "); // Формирование целевой функции из введенных данных
                        for (int i = 0; i < targetFunction.Length; i++)
                        {
                            Console.Write($"{targetFunction[i]}x{i + 1} ");
                            if (i != targetFunction.Length - 1) 
                                Console.Write("+ ");
                            }
                            else
                            {
                                Console.Write("-> max");
                            }
                        }
                        Console.WriteLine();
                        for (int i = 0; i < numberOfResources.GetLength(0); i++) // 0 строки
                        {
                            for (int j = 0; j < numberOfResources.GetLength(1); j++) // 1 - столбцы
                            {
                                Console.Write($"{numberOfResources[i, j]}x{j + 1} "); 

                                if (j != numberOfResources.GetLength(1) - 1) 
                                {
                                    Console.Write("+ ");
                                }
                                else
                                {
                                    Console.WriteLine($"<= {stockOfProducts[i]}");

                                }
                            }
                        }
                        for (int i = 0; i < targetFunction.Length; i++) 
                        {
                            Console.Write($"x{i + 1}"); // вывод строки с граничными условиями
                            if (i != targetFunction.Length - 1)
                            {
                                Console.Write(", ");
                            }
                            else Console.Write(" >=0; ");         
                            //Console.Write($"x{i + 1} >= 0 "); // вывод строки с граничными условиями
                        }
                        // Построение канонической задачи минимизации
                        Console.Write("\n\nF` = -(");
                        for (int i = 0; i < targetFunction.Length; i++)
                        {
                            Console.Write($"{targetFunction[i]}x{i + 1}");
                            if (i != targetFunction.Length - 1) // i - индекса x
                                                                // аналогичная проверка что и выше
                            {
                                Console.Write(" + ");
                            }
                            else
                            {
                                Console.Write(") -> min");
                            }

                        }
                        Console.WriteLine();
                        /// измненено название переменной
                        int dummyVariable = numberOfResources.GetLength(1) + 1;
                        for (int i = 0; i < numberOfResources.GetLength(0); i++)
                        {
                            for (int j = 0; j < numberOfResources.GetLength(1); j++)
                            {
                                Console.Write($"{numberOfResources[i, j]}x{j + 1}");
                                /// добавлены фигурные скобки (требование использовать конструкию if с фигурными скобками)
                                if (j != numberOfResources.GetLength(1) - 1) // j - индекс икса
                                {
                                    Console.Write(" + ");
                                }
                                /// добавлены фигурные скобки (требование использовать конструкию if с фигурными скобками)
                                else
                                {
                                    Console.WriteLine($" + x{dummyVariable} = {stockOfProducts[i]}");
                                    // для того, чтобы в каждой строке прибавлялся индекс фиктивной переменной +1
                                    dummyVariable = 1 + dummyVariable;
                                }
                            }

                        }
                        for (int i = 0; i <= targetFunction.Length - 1; i++) // аналогичный цикл с условием выше
                        {
                            /// добавлены фигурные скобки (требование использовать конструкию if с фигурными скобками)
                            Console.Write($"x{i + 1}"); // вывод строки с граничными условиями
                            if (i != targetFunction.Length - 1)
                            {
                                Console.Write(", ");
                            }
                            else
                            {
                                Console.Write(" >=0; ");
                            }
                        }

                        for (int i = numberOfResources.GetLength(1) + 1; i < stockOfProducts.Length+ numberOfResources.GetLength(1) + 1; i++)
                        // т.к у нас всего х1 и х2, нам необходимо начать цикл со следующего - т.е х3
                        //цикл продолжаем до последнего х
                        {
                            Console.Write($"x{i}"); // вывод строки с граничными условиями
                            /// добавлены фигурные скобки (требование использовать конструкию if с фигурными скобками)
                            if (i != stockOfProducts.Length + numberOfResources.GetLength(1))
                            {
                                Console.Write(", ");
                            }
                            else
                            {
                                Console.Write(" - любое");
                            }

                        }
                        // Этап формирования таблицы
                        Console.WriteLine();
                        double[,] table = new double[stockOfProducts.Length + 1, numberOfResources.GetLength(1) + numberOfResources.GetLength(0) + 1];
                        // количество строк зависит от запаса ( видов ресурсов) +1 для получения строки оценок
                        for (int i = 0; i < numberOfResources.GetLength(0); i++)
                        {

                            for (int j = 0; j < numberOfResources.GetLength(1); j++)
                            {
                                table[i, j] = numberOfResources[i, j];
                                // приравниваем значения первых двух столбцов
                            }

                        }
                        for (int i = 0; i < targetFunction.Length; i++) // заполнение строки оценок
                        {
                            table[table.GetLength(0) - 1, i] = targetFunction[i];
                        }

                        for (int i = 0; i < stockOfProducts.Length; i++)
                        {

                            table[i, table.GetLength(1) - 1] = stockOfProducts[i];

                        }
                        for (int i = 0; i < table.GetLength(0) - 1; i++)
                        {
                            // цикл по i берет все кроме последней строки оценок
                            for (int j = numberOfResources.GetLength(1); j < numberOfResources.GetLength(0) * 2 - 1; j++)
                            {
                                /// добавлены фигурные скобки (требование использовать конструкию if с фигурными скобками)
                                if (i == j - numberOfResources.GetLength(1))
                                {
                                    table[i, j] = 1;
                                }

                            }
                            // цикл по j

                        }
                        Console.WriteLine();
                        for (int i = 0; i < table.GetLength(0); i++)
                        {

                            for (int j = 0; j < table.GetLength(1); j++)
                            {
                                Console.Write(table[i, j] + " ");

                            }
                            Console.WriteLine();
                        }
                        // Вывод промежуточных результатов
                        Console.WriteLine("\nПромежуточные результаты");
                        Console.WriteLine($"F`={table[table.GetLength(0) - 1, table.GetLength(1) - 1]}");
                        Console.WriteLine($"F={Math.Abs(table[table.GetLength(0) - 1, table.GetLength(1) - 1])}");
                        for (int d = 0; d < targetFunction.Length; d++)
                        {
                            Console.WriteLine($"x{d + 1} = 0");
                        }
                        for (int i = 0; i < numberOfResources.GetLength(0); i++)
                        {
                            Console.WriteLine($"x{numberOfResources.GetLength(1) + 1 + i} = {stockOfProducts[i]} ");
                        }
                        break;
````
</b></details>      
## Авторы
* **Герасимов** - [JUNEDEVERY](https://github.com/JUNEDEVERY)

