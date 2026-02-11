package org.example.classes.commands;

import org.example.interfaces.Command;

public class RemoveById implements Command {
    @Override
    public String toString() {
        return "remove_by_id id - удалить элемент из коллекции по его id";
    }
}
