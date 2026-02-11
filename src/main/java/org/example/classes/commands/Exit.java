package org.example.classes.commands;


import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.managerInputOutput;


public class Exit implements Command {
    public void executeCommand() {
        managerInputOutput.closeIO();
        System.exit(0);
    }

    @Override
    public String toString() {
        return "Команда exit - завершает работу SppoManager";
    }
}
