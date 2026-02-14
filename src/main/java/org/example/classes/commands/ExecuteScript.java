package org.example.classes.commands;

import org.example.interfaces.Command;

public class ExecuteScript implements Command {
    public boolean checkArg(String[] args) {
        if (args.length == 1) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "execute_script file_name - считывает и исполняет скрипт из указанного файла";
    }
}
