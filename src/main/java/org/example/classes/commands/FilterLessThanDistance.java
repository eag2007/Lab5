package org.example.classes.commands;

import org.example.classes.Route;
import org.example.enums.Colors;
import org.example.interfaces.Command;

import java.math.BigDecimal;
import java.util.PriorityQueue;

import static org.example.classes.runner.Runner.*;

public class FilterLessThanDistance implements Command {

    public void executeCommand(String[] args) {
        if (checkArg(args)) {
            Integer distance = Integer.parseInt(args[0]);
            PriorityQueue<Route> routes = managerCollections.getCollectionsRoute();
            boolean flag = true;
            if (routes.isEmpty()) {
                managerInputOutput.writeLineIO("Коллекция пуста\n", Colors.YELLOW);
                return;
            }

            BigDecimal valute = managerApi.getPriceDollarForRublesRedis();
            if (valute == null) {
                managerInputOutput.writeLineIO("У вас проблемы с интернетом\n", Colors.YELLOW);
                return;
            }

            String header = String.format("%-3s | %-15s | %-3s | %-3s | %-6s | %-6s | %-4s | %-6s | %-6s | %-4s | %-5s | %-10s | %-10s | %-10s",
                    "ID", "Name", "X", "Y", "Date", "FromX", "FromY", "FromZ", "ToX", "ToY", "ToZ", "Distance", "Price", "Price RUB");
            managerInputOutput.writeLineIO(header + "\n");
            managerInputOutput.writeLineIO("-".repeat(header.length()) + "\n");

            for (Route route : routes) {
                if (route.getDistance() < distance) {
                    String line = String.format("%-3s | %-20s | %-3s | %-3s | %-6s | %-6s | %-4s | %-6s | %-6s | %-4s | %-5s | %-10s | %-10s | %-10s",
                            route.getId(), route.getName(), route.getCoordinates().getX(), route.getCoordinates().getY(),
                            route.getCreationDate(), route.getFrom().getX(), route.getFrom().getY(), route.getFrom().getZ(),
                            route.getTo().getX(), route.getTo().getY(), route.getTo().getZ(), route.getDistance(),
                            route.getPrice(), route.getPrice().multiply(valute));
                    managerInputOutput.writeLineIO(line + "\n");
                    flag = false;
                }
            }
            if (flag) {
                managerInputOutput.writeLineIO("Таких элементов нет\n", Colors.YELLOW);
            }
        } else {
            managerInputOutput.writeLineIO("Неправильное количество аргументов или их тип\n", Colors.RED);
        }
    }

    public boolean checkArg(String[] args) {
        if (args.length == 1) {
            try {
                Integer.parseInt(args[0]);
                return true;
            } catch (NumberFormatException e) {
                managerInputOutput.writeLineIO("Ошибка: аргумент должен быть целым числом в диапазоне от " +
                        Integer.MIN_VALUE + " до " + Integer.MAX_VALUE + "\n", Colors.RED);
                return false;
            }

        }
        return false;
    }

    @Override
    public String toString() {
        return "filter_less_than_distance distance - выводит элементы значения поля distance которых меньше заданного";
    }
}