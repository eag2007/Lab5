package org.example.classes.commands;


import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.managerInputOutput;


public class Exit implements Command {
    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            managerInputOutput.closeIO();
            System.exit(0);
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
        return "Команда exit - завершает работу SppoManager";
    }
}
