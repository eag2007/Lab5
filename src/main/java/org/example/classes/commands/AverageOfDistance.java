package org.example.classes.commands;

import org.example.interfaces.Command;

public class AverageOfDistance implements Command {
    @Override
    public String toString() {
        return "average_of_distance - выводит среднее значение поля distance для всех элементов коллекции";
    }
}
