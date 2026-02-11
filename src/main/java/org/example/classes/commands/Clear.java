package org.example.classes.commands;

import org.example.interfaces.Command;

public class Clear implements Command {
    @Override
    public String toString() {
        return "clear - очищает коллекцию";
    }
}
