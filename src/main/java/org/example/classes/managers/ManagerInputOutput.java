package org.example.classes.managers;

import org.example.interfaces.InputOutput;

import java.util.Scanner;

public class ManagerInputOutput implements InputOutput {
    private static ManagerInputOutput managerInputOutput;
    private final Scanner in;

    private ManagerInputOutput() {
        this.in = new Scanner(System.in);
    }

    public static ManagerInputOutput getInstance() {
        if (managerInputOutput == null) {
            managerInputOutput = new ManagerInputOutput();
        }
        return managerInputOutput;
    }

    public String readLineIO() {
        return this.in.nextLine();
    }

    public void writeLineIO(String message) {
        System.out.print(message);
    }

    public boolean hasNextIntIO() {
        return in.hasNextInt();
    }

    public int nextIntIO() {
        return in.nextInt();
    }

    public void closeIO() {
        this.in.close();
        managerInputOutput.writeLineIO("IO закрыт\n");
    }
}