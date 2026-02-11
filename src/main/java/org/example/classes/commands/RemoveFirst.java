package org.example.classes.commands;

import org.example.interfaces.Command;

public class RemoveFirst implements Command {
    @Override
    public String toString() {
        return "remove_first  - удаляет элемент из коллекции";
    }
}
