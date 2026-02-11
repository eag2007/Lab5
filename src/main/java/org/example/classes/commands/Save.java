package org.example.classes.commands;

import org.example.interfaces.Command;

public class Save implements Command {
    @Override
    public String toString() {
        return "save file_name - сохраняет коллекцию в файл";
    }
}
