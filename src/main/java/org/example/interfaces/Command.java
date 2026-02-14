package org.example.interfaces;

public interface Command {
    public String toString();
    default void executeCommand(String[] arg) {};
    public boolean checkArg(String[] arg);
}
