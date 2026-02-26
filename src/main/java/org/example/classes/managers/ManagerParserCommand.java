package org.example.classes.managers;

import org.example.classes.commands.*;
import org.example.interfaces.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Менеджер для парсинга и выполнения пользовательских команд.
 * <p>
 * Содержит реестр доступных команд ({@link HashMap}), хранит историю выполненных команд
 * и предоставляет методы для их выполнения и получения информации о них.
 * </p>
 *
 * @author
 * @version 1.0
 */
public class ManagerParserCommand {
    /** Реестр команд, где ключ — имя команды, значение — объект команды. */
    private final HashMap<String, Command> commands;
    /** История последних выполненных команд. */
    private final List<String> historyCommands;
    /** Максимальный размер истории команд. */
    private static final int MAX_SIZE_LEN_HISTORY = 14;

    /**
     * Конструктор, инициализирующий реестр команд и список истории.
     * <p>
     * Регистрирует все доступные команды.
     * </p>
     */
    public ManagerParserCommand() {
        this.commands = new HashMap<String, Command>();
        this.historyCommands = new ArrayList<>(MAX_SIZE_LEN_HISTORY);

        this.commands.put("add", new Add());
        this.commands.put("add_if_max", new AddIfMax());
        this.commands.put("average_of_distance", new AverageOfDistance());
        this.commands.put("clear", new Clear());
        this.commands.put("execute_script", new ExecuteScript());
        this.commands.put("exit", new Exit());
        this.commands.put("filter_less_than_distance", new FilterLessThanDistance());
        this.commands.put("help", new Help());
        this.commands.put("history", new History());
        this.commands.put("info", new Info());
        this.commands.put("remove_all_by_distance", new RemoveAllByDistance());
        this.commands.put("remove_by_id", new RemoveById());
        this.commands.put("remove_first", new RemoveFirst());
        this.commands.put("save", new Save());
        this.commands.put("show", new Show());
        this.commands.put("update", new Update());
        this.commands.put("ls", new Show()); // Алиас для show
    }

    /**
     * Парсит и выполняет команду, введенную пользователем.
     * <p>
     * Разделяет ввод на имя команды и аргументы. Если команда найдена в реестре,
     * вызывает её метод {@code executeCommand} и добавляет команду в историю.
     * </p>
     *
     * @param s строка, введенная пользователем
     * @return {@code true}, если команда распознана и выполнена, иначе {@code false}
     */
    public boolean parserCommand(String s) {
        String[] command = s.trim().replaceAll("\\s+", " ").split(" ");

        if (this.commands.containsKey(command[0])) {
            Command cmd = this.commands.get(command[0]);
            cmd.executeCommand(Arrays.copyOfRange(command, 1, command.length));

            if (this.historyCommands.size() >= MAX_SIZE_LEN_HISTORY) {
                this.historyCommands.remove(0);
            }
            this.historyCommands.add(s);
            return true;
        }
        return false;
    }

    /**
     * Возвращает список всех доступных команд.
     *
     * @return список объектов {@link Command}
     */
    public List<Command> getCommands() {
        List<Command> commandsList = new ArrayList<>();
        commandsList.addAll(this.commands.values());
        return commandsList;
    }

    /**
     * Возвращает историю выполненных команд.
     *
     * @return список последних команд
     */
    public List<String> getHistoryCommands() {
        return historyCommands;
    }
}