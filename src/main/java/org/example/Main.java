package org.example;

import org.example.classes.runner.Runner;

/**
 * Главный класс приложения.
 * <p>
 * Содержит точку входа {@code main}, которая создает объект {@link Runner} и запускает его.
 * </p>
 *
 * @author
 * @version 1.0
 */
public class Main {

    /**
     * Точка входа в программу.
     *
     * @param args аргументы командной строки (не используются)
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        Runner run = new Runner();
        run.run();
    }
}