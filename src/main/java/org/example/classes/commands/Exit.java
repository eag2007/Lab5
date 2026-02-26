package org.example.classes.commands;


import org.example.enums.Colors;
import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.managerInputOutput;


public class Exit implements Command {
    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            managerInputOutput.closeIO();
            managerInputOutput.writeLineIO("Завершение работы\n", Colors.GREEN);
            System.exit(0);
        } else {
            managerInputOutput.writeLineIO("Неверное количество аргументов\n", Colors.RED);
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
