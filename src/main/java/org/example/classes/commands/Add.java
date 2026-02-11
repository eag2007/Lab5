package org.example.classes.commands;

import org.example.interfaces.Command;

public class Add implements Command {
    @Override
    public String toString() {
        return "add - добавляет новый элемент в коллекцию";
    }
}
