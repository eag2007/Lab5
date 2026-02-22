package org.example.classes.managers;

import org.example.classes.commands.*;
import org.example.interfaces.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ManagerParserCommand {
    private final HashMap<String, Command> commands;
    private final List<String> historyCommands;
    private static final int MAX_SIZE_LEN_HISTORY = 14;

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
    }

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

    public List<Command> getCommands() {
        List<Command> commandsList = new ArrayList<>();

        commandsList.addAll(this.commands.values());
        return commandsList;
    }

    public List<String> getHistoryCommands() {
        return historyCommands;
    }
}
