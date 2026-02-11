package org.example.classes.commands;

import org.example.interfaces.Command;

public class FilterLessThanDistance implements Command {
    @Override
    public String toString() {
        return "filter_less_than_distance - выводит элементы значения поля distance которых меньше заданного";
    }
}
