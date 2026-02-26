package org.example.classes.commands;

import org.example.enums.Colors;
import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.managerInputOutput;
import static org.example.classes.runner.Runner.managerParserCommand;

/**
 * Команда {@code help} выводит справку по всем доступным командам.
 * <p>
 * Получает список команд из {@link org.example.classes.managers.ManagerParserCommand}
 * и выводит их описания, полученные из метода {@link Command#toString()}.
 * </p>
 *
 * @author
 * @version 1.0
 * @see Command
 */
public class Help implements Command {

    /**
     * Выполняет команду вывода справки.
     *
     * @param args аргументы команды (ожидается отсутствие аргументов)
     */
    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            managerInputOutput.writeLineIO("Справка по командам:\n");
            managerInputOutput.writeLineIO("------------------------------------------------------\n");
            for (Command cmd : managerParserCommand.getCommands()) {
                managerInputOutput.writeLineIO(cmd + "\n");
            }
            managerInputOutput.writeLineIO("------------------------------------------------------\n");
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
        return "help - выводит справку по каждой из команд";
    }
}