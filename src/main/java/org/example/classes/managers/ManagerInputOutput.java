package org.example.classes.managers;

import org.example.enums.Colors;
import org.example.interfaces.InputOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Менеджер ввода-вывода (Singleton).
 * <p>
 * Отвечает за чтение данных из консоли или из файла (в режиме выполнения скрипта)
 * и за вывод сообщений пользователю с поддержкой цветового форматирования.
 * </p>
 *
 * @author
 * @version 1.0
 * @see InputOutput
 */
public class ManagerInputOutput implements InputOutput {
    /** Единственный экземпляр класса (Singleton). */
    private static ManagerInputOutput managerInputOutput;
    /** Сканер для чтения из стандартного потока ввода. */
    private Scanner in;
    /** Буферизированный читатель для чтения из файла скрипта. */
    private BufferedReader inRead;
    /** Флаг, указывающий, находится ли программа в режиме выполнения скрипта. */
    private boolean executeScript = false;

    /** Приватный конструктор для Singleton. */
    private ManagerInputOutput() {
        this.in = new Scanner(System.in);
    }

    /**
     * Возвращает единственный экземпляр менеджера.
     *
     * @return экземпляр {@code ManagerInputOutput}
     */
    public static ManagerInputOutput getInstance() {
        if (managerInputOutput == null) {
            managerInputOutput = new ManagerInputOutput();
        }
        return managerInputOutput;
    }

    /**
     * Переключает менеджер в режим чтения из файла.
     *
     * @param reader читатель для файла скрипта
     */
    public void setFileExecute(BufferedReader reader) {
        this.inRead = reader;
        this.executeScript = true;
    }

    /**
     * Возвращает менеджер в режим чтения из консоли.
     */
    public void setConsoleExecute() {
        this.executeScript = false;
        this.inRead = null;
    }

    /**
     * Проверяет, активен ли режим выполнения скрипта.
     *
     * @return {@code true}, если в данный момент выполняется скрипт, иначе {@code false}
     */
    public boolean isScriptMode() {
        return this.executeScript;
    }

    /**
     * Читает строку из текущего источника ввода.
     * <p>
     * Если в режиме скрипта, читает из {@code BufferedReader} и выводит прочитанное в консоль.
     * В противном случае читает из {@code Scanner}.
     * </p>
     *
     * @return прочитанная строка
     */
    public String readLineIO() {
        if (this.executeScript && this.inRead != null) {
            try {
                String line = this.inRead.readLine();
                if (line != null) {
                    System.out.println("[Значение из скрипта] " + line);
                    return line;
                } else {
                    setConsoleExecute();
                    return null;
                }
            } catch (IOException e) {
                setConsoleExecute();
                return null;
            }
        }
        return this.in.nextLine();
    }

    /**
     * Выводит сообщение в стандартный поток вывода без цвета.
     *
     * @param message сообщение для вывода
     */
    public void writeLineIO(String message) {
        System.out.print(message);
    }

    /**
     * Выводит сообщение в стандартный поток вывода с цветовым форматированием.
     *
     * @param message сообщение для вывода
     * @param color   цвет из перечисления {@link Colors}
     */
    public void writeLineIO(String message, Colors color) {
        System.out.print(color + message + "\u001B[0m");
    }

    /**
     * Проверяет, доступно ли для чтения целое число.
     * <p>
     * В режиме скрипта всегда возвращает {@code true}.
     * </p>
     *
     * @return {@code true}, если доступно целое число, иначе {@code false}
     */
    public boolean hasNextIntIO() {
        if (this.executeScript) return true;
        return this.in.hasNextInt();
    }

    /**
     * Читает целое число из текущего источника ввода.
     *
     * @return прочитанное целое число
     * @throws NumberFormatException если в режиме скрипта прочитана строка, не являющаяся числом
     */
    public int nextIntIO() {
        if (this.executeScript) {
            try {
                String line = this.inRead.readLine();
                if (line != null) {
                    System.out.println("[Значение из скрипта] " + line);
                    return Integer.parseInt(line.trim());
                } else {
                    setConsoleExecute();
                }
            } catch (Exception e) {
                setConsoleExecute();
            }
        }
        return this.in.nextInt();
    }

    /**
     * Закрывает все открытые потоки ввода.
     */
    public void closeIO() {
        this.in.close();
        try {
            if (this.inRead != null) {
                this.inRead.close();
            }
        } catch (IOException e) {
            System.out.println("Ошибка: " + e);
        }
        writeLineIO("IO закрыт\n");
    }
}