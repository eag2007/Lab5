package org.example.classes.commands;

import org.example.interfaces.Command;

public class RemoveAllByDistance implements Command {
    @Override
    public String toString() {
        return "remove_all_by_distance distance - удаляет все " +
                "элементы из коллекции, значения  поля distance которого эквивалентно заданному";
    }
}
