package org.example.classes.commands;

import org.example.interfaces.Command;

public class Info implements Command {
    @Override
    public String toString() {
        return "info - выводит в стандартный поток вывода информацию о коллекции";
    }
}
