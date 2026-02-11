package org.example.classes.commands;

import org.example.interfaces.Command;

import java.util.List;

import static org.example.classes.runner.Runner.managerInputOutput;
import static org.example.classes.runner.Runner.managerParserCommand;

public class History implements Command {
    public void executeCommand() {
        List<String> historyCommands = managerParserCommand.getHistoryCommands();
        managerInputOutput.writeLineIO("Список последних 14 команд:\n");
        managerInputOutput.writeLineIO("-----------------------\n");
        for (String cmd : historyCommands) {
            managerInputOutput.writeLineIO(cmd + "\n");
        }
        managerInputOutput.writeLineIO("-----------------------\n");
    }

    @Override
    public String toString() {
        return "history - выводит 14 последних команд";
    }
}
