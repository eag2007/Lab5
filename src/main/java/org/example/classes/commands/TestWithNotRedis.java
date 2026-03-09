package org.example.classes.commands;

import org.example.classes.Route;
import org.example.enums.Colors;
import org.example.interfaces.Command;

import java.math.BigDecimal;

import static org.example.classes.runner.Runner.*;

public class TestWithNotRedis implements Command {

    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            long start = System.currentTimeMillis();

            BigDecimal valute = managerApi.getPriceDollarForRublesDontRedis();
            if (valute == null) {
                managerInputOutput.writeLineIO("У вас проблемы с интернетом\n", Colors.YELLOW);
                return;
            }

            if (managerCollections.getCollectionsRoute().isEmpty()) {
                managerInputOutput.writeLineIO("Коллекция пуста\n", Colors.YELLOW);
            }

            String header = String.format("%-3s | %-15s | %-3s | %-3s | %-6s | %-6s | %-4s | %-6s | %-6s | %-4s | %-5s | %-10s | %-10s | %-10s",
                    "ID", "Name", "X", "Y", "Date", "FromX", "FromY", "FromZ", "ToX", "ToY", "ToZ", "Distance", "Price", "Price RUB");
            managerInputOutput.writeLineIO(header + "\n");
            managerInputOutput.writeLineIO("-".repeat(header.length()) + "\n");

            for (Route route : managerCollections.getSortedCollections()) {

                String line = String.format("%-3s | %-15s | %-3s | %-3s | %-6s | %-6s | %-4s | %-6s | %-6s | %-4s | %-5s | %-10s | %-10s | %-10s",
                        route.getId(), route.getName(), route.getCoordinates().getX(), route.getCoordinates().getY(),
                        route.getCreationDate(), route.getFrom().getX(), route.getFrom().getY(), route.getFrom().getZ(),
                        route.getTo().getX(), route.getTo().getY(), route.getTo().getZ(), route.getDistance(),
                        route.getPrice(), route.getPrice().multiply(valute));
                managerInputOutput.writeLineIO(line + "\n");
            }

            long end = System.currentTimeMillis();
            managerInputOutput.writeLineIO("Время выполнения: " + (end - start) + " мс\n", Colors.GREEN);

        } else {
            managerInputOutput.writeLineIO("Неверное количество аргументов\n", Colors.RED);
        }
    }

    public boolean checkArg(String[] args) {
        return args.length == 0;
    }

    @Override
    public String toString() {
        return "show - выводит в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}