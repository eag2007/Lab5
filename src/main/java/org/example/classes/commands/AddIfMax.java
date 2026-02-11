package org.example.classes.commands;

import org.example.interfaces.Command;

public class AddIfMax implements Command {
    @Override
    public String toString() {
        return "add_if_max {element} - добавляет новый элемент в коллекцию, " +
                "если его значение превышает значение наибольшего элемента в коллекции";
    }
}
