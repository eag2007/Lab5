package org.example.classes.commands;

import org.example.enums.Colors;
import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.managerCollections;
import static org.example.classes.runner.Runner.managerInputOutput;

public class Info implements Command {

    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            managerInputOutput.writeLineIO("Количество элементов: " +
                    managerCollections.getSizeCollections() + "\n");
            managerInputOutput.writeLineIO("Время инициализации: " + managerCollections.getTimeInit() + "\n");
            managerInputOutput.writeLineIO("Тип данных: Route\n");
        } else {
            managerInputOutput.writeLineIO("Неверное количество аргументов\n", Colors.RED);
        }
    }

    public boolean checkArg(String[] args) {
        return args.length == 0;
    }

    @Override
    public String toString() {
        return "info - выводит в стандартный поток вывода информацию о коллекции";
    }
}