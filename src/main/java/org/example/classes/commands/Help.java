package org.example.classes.commands;

import org.example.enums.Colors;
import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.managerInputOutput;
import static org.example.classes.runner.Runner.managerParserCommand;

public class Help implements Command {

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

    public boolean checkArg(String[] args) {
        return args.length == 0;
    }

    @Override
    public String toString() {
        return "help - выводит справку по каждой из команд";
    }
}