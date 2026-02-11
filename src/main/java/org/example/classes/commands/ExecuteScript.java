package org.example.classes.commands;

import org.example.interfaces.Command;

public class ExecuteScript implements Command {
    @Override
    public String toString() {
        return "execute_script file_name - считывает и исполняет скрипт из указанного файла";
    }
}
