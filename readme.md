Параметры программы задаются при запуске через аргументы командной строки, по порядку:
1. режим сортировки (-a или -d), необязательный, по умолчанию сортируем по возрастанию;
2. тип данных (-s или -i), обязательный;
3. имя выходного файла, обязательное;
4. остальные параметры – имена входных файлов, не менее одного.

Примеры запуска из командной строки для Windows:  
java -jar sortit.jar -i -a out.txt in.txt (для целых чисел по возрастанию)    
java -jar sortit.jar -s out.txt in1.txt in2.txt in3.txt (для строк по возрастанию)  
java -jar sortit.jar -d -s out.txt in1.txt in2.txt (для строк по убыванию)  

Особенности реализации:  
• среда разработки intellij idea  
• версия JDK 1.8  
• сборка gradle-6.8  
• использовалась стороняя библиотека JewelCli('com.lexicalscope.jewelcli:jewelcli:0.8.9')  