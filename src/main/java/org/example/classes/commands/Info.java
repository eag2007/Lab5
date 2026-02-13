package org.example.classes.commands;

import org.example.classes.Route;
import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.managerCollections;
import static org.example.classes.runner.Runner.managerInputOutput;

public class Info implements Command {
    public void executeCommand() {
        managerInputOutput.writeLineIO("Количество элементов: " +
                managerCollections.getSizeCollections() + "\n");
        managerInputOutput.writeLineIO("Время инициализации: " + managerCollections.getTimeInit() + "\n");
        managerInputOutput.writeLineIO("Тип данных: Route\n");
    }

    @Override
    public String toString() {
        return "info - выводит в стандартный поток вывода информацию о коллекции";
    }
}
