package org.example.classes.commands;

import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.managerCollections;

public class RemoveFirst implements Command {
    public void executeCommand() {
        managerCollections.removeFirstCollections();
    }

    @Override
    public String toString() {
        return "remove_first  - удаляет первый элемент из коллекции";
    }
}
