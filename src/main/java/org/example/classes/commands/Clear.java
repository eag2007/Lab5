package org.example.classes.commands;

import org.example.interfaces.Command;

import static org.example.classes.runner.Runner.managerCollections;

public class Clear implements Command {
    public void executeCommand() {
        managerCollections.clearCollections();
    }

    @Override
    public String toString() {
        return "clear - очищает коллекцию";
    }
}
