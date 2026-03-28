package org.example.classes.commands;

import org.example.classes.Route;
import org.example.interfaces.Command;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.PriorityQueue;

import static org.example.classes.runner.Runner.*;

public class TestRedis implements Command {

    @Override
    public void executeCommand(String[] args) {
        if (!checkArg(args)) {
            managerInputOutput.writeLineIO("Ошибка: укажите время в секундах\n");
            return;
        }

        int ttl = Integer.parseInt(args[0]);

        managerInputOutput.writeLineIO("\n=== ТЕСТИРОВАНИЕ КЭША REDIS ===\n");

        managerApi.setTtl(ttl);

        managerInputOutput.writeLineIO("Элементов в коллекции: " + managerCollections.getCollectionsRoute().size() + "\n");

        managerApi.clearCache();

        managerInputOutput.writeLineIO("\n--- ПОЛУЧЕНИЕ КУРСА (первый раз, должен пойти в API) ---\n");
        BigDecimal course1 = managerApi.getCourse();
        if (course1 == null) {
            managerInputOutput.writeLineIO("Курс не получен\n");
            return;
        }
        managerInputOutput.writeLineIO("Курс: " + course1 + " RUB\n");

        managerInputOutput.writeLineIO("\n--- ПОЛУЧЕНИЕ КУРСА (второй раз, должен взять из Redis) ---\n");
        BigDecimal course2 = managerApi.getCourse();
        managerInputOutput.writeLineIO("Курс: " + course2 + " RUB\n");

        managerInputOutput.writeLineIO("\n--- ТЕОРЕТИЧЕСКАЯ ПОГРЕШНОСТЬ ---\n");
        managerInputOutput.writeLineIO("-------------------------------------\n");
        managerInputOutput.writeLineIO("│ Время │ Погрешность  │ Статус     │\n");
        managerInputOutput.writeLineIO("-------------------------------------\n");

        for (int t = 0; t <= ttl + 30; t += 15) {
            if (t > ttl + 30) break;

            double error;
            String status;

            if (t <= ttl) {
                error = 0.5 * t / ttl;
                status = "Актуален";
            } else {
                error = 0.75;
                status = "Просрочен";
            }

            managerInputOutput.writeLineIO(String.format("│ %-5d │ %-12.4f │ %-10s │\n", t, error, status));
        }

        managerInputOutput.writeLineIO("-------------------------------------\n");

        if (ttl > 60) {
            managerInputOutput.writeLineIO("Рекомендация: TTL слишком большой, лучше 30-60 сек\n");
        } else {
            managerInputOutput.writeLineIO("Рекомендация: TTL оптимальный\n");
        }

        managerInputOutput.writeLineIO("\n=== ПОДСЧЕТ ОБЩЕЙ СТОИМОСТИ ===\n");
        managerInputOutput.writeLineIO("Каждый запрос курса идет в Redis\n");

        PriorityQueue<Route> collection = managerCollections.getCollectionsRoute();
        if (collection.isEmpty()) {
            managerInputOutput.writeLineIO("Коллекция пуста\n");
            return;
        }

        BigDecimal totalUSD = BigDecimal.ZERO;
        BigDecimal totalRUB = BigDecimal.ZERO;
        int count = 0;

        long startMs = System.currentTimeMillis();
        managerInputOutput.writeLineIO("Начало: " + LocalDateTime.now() + "\n");

        for (Route route : collection) {
            if (route != null && route.getPrice() != null) {
                // Каждый раз запрашиваем курс из Redis
                BigDecimal currentRate = managerApi.getCourse();

                totalUSD = totalUSD.add(route.getPrice());
                totalRUB = totalRUB.add(route.getPrice().multiply(currentRate));
                count++;
            }
        }

        long endMs = System.currentTimeMillis();
        long delta = endMs - startMs;

        managerInputOutput.writeLineIO("\n--- РЕЗУЛЬТАТЫ ---\n");
        managerInputOutput.writeLineIO("Обработано: " + count + " элементов\n");
        managerInputOutput.writeLineIO("Общая цена USD: " + totalUSD + "\n");
        managerInputOutput.writeLineIO("Общая цена RUB: " + totalRUB + "\n");
        managerInputOutput.writeLineIO("Время выполнения: " + delta + " мс\n");
    }

    @Override
    public boolean checkArg(String[] args) {
        if (args == null || args.length == 0) return false;
        try {
            return Integer.parseInt(args[0]) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "test - тестирование Redis кэша";
    }
}