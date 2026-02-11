package org.example.classes.managers;

public class ManagerGenerateId {
    private static long id = 0;

    public static synchronized long generateId() {
        return ++id;
    }
}
