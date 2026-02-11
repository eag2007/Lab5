package org.example.classes.commands;

import org.example.interfaces.Command;

public class Show implements Command {
    @Override
    public String toString() {
        return "show - выводит в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
