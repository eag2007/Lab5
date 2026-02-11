package org.example.classes.commands;

import org.example.interfaces.Command;

public class Update implements Command {
    @Override
    public String toString() {
        return "update id {element} - обновляет значение элемента коллекции, id которого равен заданному";
    }
}
