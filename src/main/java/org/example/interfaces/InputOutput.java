package org.example.interfaces;

import org.example.enums.Colors;
import java.io.BufferedReader;

public interface InputOutput {
    String readLineIO();
    void writeLineIO(String message);
    void writeLineIO(String message, Colors color);
    boolean hasNextIntIO();
    int nextIntIO();
    void closeIO();
    boolean isScriptMode();
    boolean isCurrentReader(BufferedReader reader);
    void pushFileExecute(BufferedReader reader);
    void popFileExecute();
}