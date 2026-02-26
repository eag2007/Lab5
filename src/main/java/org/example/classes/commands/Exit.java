package org.example.classes.commands;

import org.example.enums.Colors;
import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.managerInputOutput;

/**
 * Команда {@code exit} завершает работу приложения.
 * <p>
 * Закрывает все потоки ввода-вывода и останавливает выполнение программы.
 * </p>
 *
 * @author
 * @version 1.0
 * @see Command
 */
public class Exit implements Command {

    /**
     * Выполняет команду выхода из программы.
     *
     * @param args аргументы команды (ожидается отсутствие аргументов)
     */
    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            managerInputOutput.closeIO();
            managerInputOutput.writeLineIO("Завершение работы\n", Colors.GREEN);
            System.exit(0);
        } else {
            managerInputOutput.writeLineIO("Неверное количество аргументов\n", Colors.RED);
        }
    }

    /**
     * Проверяет, что команда вызвана без аргументов.
     *
     * @param args массив аргументов для проверки
     * @return {@code true}, если аргументов нет, иначе {@code false}
     */
    public boolean checkArg(String[] args) {
        return args.length == 0;
    }

    /**
     * Возвращает строковое представление команды для справки.
     *
     * @return описание команды
     */
    @Override
    public String toString() {
        return "Команда exit - завершает работу SppoManager";
    }
}