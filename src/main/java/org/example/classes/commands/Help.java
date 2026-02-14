package org.example.classes.commands;

import org.example.interfaces.Command;
import org.example.classes.runner.Runner;

import static org.example.classes.runner.Runner.managerInputOutput;
import static org.example.classes.runner.Runner.managerParserCommand;

public class Help implements Command{
    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            managerInputOutput.writeLineIO("Справка по командам:\n");
            managerInputOutput.writeLineIO("------------------------------------------------------\n");
            for (Command cmd : managerParserCommand.getCommands()) {
                managerInputOutput.writeLineIO(cmd + "\n");
            }
            managerInputOutput.writeLineIO("------------------------------------------------------\n");
        } else {
            managerInputOutput.writeLineIO("Неверное количество аргументов\n");
        }
    }

    public boolean checkArg(String[] args) {
        if (args.length == 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "help - выводит справку по каждой из команд";
    }
}
