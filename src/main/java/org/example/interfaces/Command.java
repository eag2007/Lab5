package org.example.interfaces;

public interface Command {
    public String toString();
    default void executeCommand() {}
}
