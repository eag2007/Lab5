package org.example.classes.managers;

import org.example.enums.Colors;
import org.example.interfaces.InputOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class ManagerInputOutput implements InputOutput {
    private static ManagerInputOutput managerInputOutput;
    private Scanner in;
    private Stack<BufferedReader> readerStack;
    private boolean executeScript = false;

    private ManagerInputOutput() {
        this.in = new Scanner(System.in);
        this.readerStack = new Stack<>();
    }

    public static ManagerInputOutput getInstance() {
        if (managerInputOutput == null) {
            managerInputOutput = new ManagerInputOutput();
        }
        return managerInputOutput;
    }

    public void pushFileExecute(BufferedReader reader) {
        this.readerStack.push(reader);
        this.executeScript = true;
    }

    public void popFileExecute() {
        if (!readerStack.isEmpty()) {
            try {
                BufferedReader currentReader = readerStack.peek();
                if (currentReader != null) {
                    currentReader.close();
                }
            } catch (IOException e) {
                System.out.println("Ошибка при закрытии ридера: " + e.getMessage());
            } finally {
                readerStack.pop();
                if (readerStack.isEmpty()) {
                    this.executeScript = false;
                }
            }
        }
    }

    public boolean isScriptMode() {
        return this.executeScript || !readerStack.isEmpty();
    }

    public boolean isCurrentReader(BufferedReader reader) {
        return !readerStack.isEmpty() && readerStack.peek() == reader;
    }

    public String readLineIO() {
        while (!readerStack.isEmpty()) {
            BufferedReader currentReader = readerStack.peek();
            try {
                String line = currentReader.readLine();
                if (line != null) {
                    System.out.println("[Значение из скрипта] " + line);
                    return line;
                } else {
                    popFileExecute();
                    return null;
                }
            } catch (IOException e) {
                popFileExecute();
                return null;
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
        if (isScriptMode()) return true;
        return this.in.hasNextInt();
    }

    public int nextIntIO() {
        if (isScriptMode()) {
            String line = readLineIO();
            if (line != null) {
                return Integer.parseInt(line.trim());
            }
        }
        return this.in.nextInt();
    }

    public void closeIO() {
        this.in.close();

        while (!readerStack.isEmpty()) {
            try {
                BufferedReader reader = readerStack.pop();
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("Ошибка при закрытии ридера: " + e.getMessage());
            }
        }

        writeLineIO("IO закрыт\n");
    }
}