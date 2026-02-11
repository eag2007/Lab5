package org.example.classes.commands;

import org.example.interfaces.Command;
import org.example.classes.runner.Runner;

import static org.example.classes.runner.Runner.managerInputOutput;
import static org.example.classes.runner.Runner.managerParserCommand;

public class Help implements Command{
    public void executeCommand() {
        managerInputOutput.writeLineIO("Справка по командам:\n");
        managerInputOutput.writeLineIO("------------------------------------------------------\n");
        for (Command cmd : managerParserCommand.getCommands()) {
            managerInputOutput.writeLineIO(cmd + "\n");
        }
        managerInputOutput.writeLineIO("------------------------------------------------------\n");
    }

    @Override
    public String toString() {
        return "help - выводит справку по каждой из команд";
    }
}
