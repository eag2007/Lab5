package org.example.classes.commands;

import org.example.enums.Colors;
import org.example.interfaces.Command;

import java.util.List;

import static org.example.classes.runner.Runner.managerInputOutput;
import static org.example.classes.runner.Runner.managerParserCommand;

/**
 * Команда {@code history} выводит последние 14 выполненных команд.
 * <p>
 * История команд хранится в {@link org.example.classes.managers.ManagerParserCommand}.
 * </p>
 *
 * @author
 * @version 1.0
 * @see Command
 */
public class History implements Command {

    /**
     * Выполняет команду вывода истории.
     *
     * @param args аргументы команды (ожидается отсутствие аргументов)
     */
    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            List<String> historyCommands = managerParserCommand.getHistoryCommands();
            managerInputOutput.writeLineIO("Список последних 14 команд:\n");
            managerInputOutput.writeLineIO("-----------------------\n");
            for (String cmd : historyCommands) {
                managerInputOutput.writeLineIO(cmd + "\n");
            }
            managerInputOutput.writeLineIO("-----------------------\n");
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
        return "history - выводит 14 последних команд";
    }
}