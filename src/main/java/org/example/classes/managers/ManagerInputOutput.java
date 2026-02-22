package org.example.classes.managers;

import org.example.enums.Colors;
import org.example.interfaces.InputOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class ManagerInputOutput implements InputOutput {
    private static ManagerInputOutput managerInputOutput;
    private Scanner in;
    private BufferedReader inRead;
    private boolean executeScript = false;

    private ManagerInputOutput() {
        this.in = new Scanner(System.in);
    }

    public static ManagerInputOutput getInstance() {
        if (managerInputOutput == null) {
            managerInputOutput = new ManagerInputOutput();
        }
        return managerInputOutput;
    }

    public void setFileExecute(BufferedReader reader) {
        this.inRead = reader;
        this.executeScript = true;
    }

    public void setConsoleExecute() {
        this.executeScript = false;
        this.inRead = null;
    }

    public boolean isScriptMode() {
        return this.executeScript;
    }

    public String readLineIO() {
        if (this.executeScript && this.inRead != null) {
            try {
                String line = this.inRead.readLine();
                if (line != null) {
                    System.out.println("[Значение из скрипта] " + line);
                    return line;
                } else {
                    setConsoleExecute();
                }
            } catch (IOException e) {
                setConsoleExecute();
            }
        }
        return this.in.nextLine();
    }

    public void writeLineIO(String message) {
        System.out.print(message);
    }

    public void writeLineIO(String message, Colors color) {
        System.out.print(color + message + "\u001B[0m");
    }

    public boolean hasNextIntIO() {
        if (this.executeScript) return true;
        return this.in.hasNextInt();
    }

    public int nextIntIO() {
        if (this.executeScript) {
            try {
                String line = this.inRead.readLine();
                if (line != null) {
                    System.out.println("[Значение из скрипта] " + line);
                    return Integer.parseInt(line.trim());
                } else {
                    setConsoleExecute();
                }
            } catch (Exception e) {
                setConsoleExecute();
            }
        }
        return this.in.nextInt();
    }

    public void closeIO() {
        this.in.close();
        try {
            if (this.inRead != null) {
                this.inRead.close();
            }
        } catch (IOException e) {
            System.out.println("Ошибка: " + e);
        }
        writeLineIO("IO закрыт\n");
    }
}