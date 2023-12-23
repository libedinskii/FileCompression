import java.io.BufferedReader; // Импортируем класс BufferedReader для чтения текстовых данных из потока ввода
import java.io.BufferedWriter; // Импортируем класс BufferedWriter для записи текстовых данных в поток вывода
import java.io.FileReader; // Импортируем класс FileReader для чтения данных из файла
import java.io.FileWriter; // Импортируем класс FileWriter для записи данных в файл
import java.io.IOException; // Импортируем класс IOException для обработки исключений ввода-вывода
import java.util.HashSet; // Импортируем класс HashSet для хранения уникальных строк
import java.util.Set; // Импортируем класс Set для работы с множествами

public class FileCompression { // Объявляем класс FileCompression
    public static void compressFile(String sourceFilePath, String destinationFilePath) { // Объявляем метод compressFile, который принимает пути к исходному файлу и файлу назначения
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath)); // Создаем объект BufferedReader для чтения данных из исходного файла
             BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFilePath))) { // Создаем объект BufferedWriter для записи данных в файл назначения
            Set<String> uniqueLines = new HashSet<>(); // Создаем множество uniqueLines для хранения уникальных строк
            String line; // Объявляем переменную line для хранения текущей строки из файла
            int duplicateCount = 0; // Объявляем переменную duplicateCount для подсчета количества удаленных дублирующихся строк

            while ((line = reader.readLine()) != null) { // Читаем строки из исходного файла, пока не достигнем конца файла
                if (uniqueLines.contains(line)) { // Проверяем, содержится ли текущая строка в множестве uniqueLines
                    duplicateCount++; // Если строка уже присутствует в множестве, увеличиваем счетчик duplicateCount
                } else {
                    uniqueLines.add(line); // Если строка уникальна, добавляем ее в множество uniqueLines
                    writer.write(line); // Записываем строку в файл назначения
                    writer.newLine(); // Переходим на новую строку в файле назначения
                }
            }

            writer.write("Number of duplicate lines removed: " + duplicateCount); // Записываем информацию о количестве удаленных дублирующихся строк в файл назначения
        } catch (IOException e) { // Обрабатываем исключение IOException, если возникла ошибка ввода-вывода
            e.printStackTrace(); // Выводим информацию об ошибке в консоль
        }
    }

    public static void decompressFile(String compressedFilePath, String destinationFilePath) { // Объявляем метод decompressFile, который принимает пути к сжатому файлу и файлу назначения
        try (BufferedReader reader = new BufferedReader(new FileReader(compressedFilePath)); // Создаем объект BufferedReader для чтения данных из сжатого файла
             BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFilePath))) { // Создаем объект BufferedWriter для записи данных в файл назначения
            String line; // Объявляем переменную line для хранения текущей строки из файла

            while ((line = reader.readLine()) != null) { // Читаем строки из сжатого файла, пока не достигнем конца файла
                writer.write(line); // Записываем строку в файл назначения
                writer.newLine(); // Переходим на новую строку в файле назначения
            }
        } catch (IOException e) { // Обрабатываем исключение IOException, если возникла ошибка ввода-вывода
            e.printStackTrace(); // Выводим информацию об ошибке в консоль
        }
    }

    public static void main(String[] args) { // Объявляем метод main
        String sourceFilePath = "input.txt"; // Задаем путь к исходному файлу
        String compressedFilePath = "compressed.txt"; // Задаем путь к сжатому файлу
        String decompressedFilePath = "decompressed.txt"; // Задаем путь к файлу для восстановления

        // Сжатие файла
        compressFile(sourceFilePath, compressedFilePath); // Вызываем метод compressFile для сжатия файла

        // Восстановление сжатого файла
        decompressFile(compressedFilePath, decompressedFilePath); // Вызываем метод decompressFile для восстановления сжатого файла
    }
}
