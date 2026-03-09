package org.example.classes.managers;

import org.example.classes.Coordinates;
import org.example.classes.Location;
import org.example.classes.Route;
import org.example.enums.Colors;
import org.example.interfaces.Command;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import static org.example.classes.runner.Runner.*;


public class ManagerCollections {
    private PriorityQueue<Route> collectionsRoute;
    private ZonedDateTime timeInit;

    public ManagerCollections() {
        this.collectionsRoute = new PriorityQueue<>();
        this.timeInit = ZonedDateTime.now();
    }

    public void addCollections(Route element) {
        this.collectionsRoute.add(element);
    }

    public void clearCollections() {
        this.collectionsRoute.clear();
    }

    public void removeAllByDistanceCollections(PriorityQueue<Route> routes) {
        this.collectionsRoute = routes;
    }

    public void removeByIdCollections(Long id) {
        boolean flag = this.collectionsRoute.removeIf(route -> ((Long) route.getId()).equals(id));

        if (flag) {
            managerInputOutput.writeLineIO("Объект удалён\n", Colors.GREEN);
        } else {
            managerInputOutput.writeLineIO("Объект не найден\n", Colors.RED);
        }
    }

    public void removeFirstCollections() {
        this.collectionsRoute.poll();
    }

    public List<Route> getSortedCollections() {
        List<Route> sorted = new ArrayList<>(collectionsRoute);
        sorted.sort(Comparator.naturalOrder());
        return sorted;
    }

    public PriorityQueue<Route> getCollectionsRoute() {
        return this.collectionsRoute;
    }

    public void updateCollections(Long id) {
        boolean flag = false;
        ZonedDateTime time = null;
        Route now = null;
        for (Route route : this.collectionsRoute) {
            if (route.getId() == id) {
                time = route.getCreationDate();
                flag = true;
                now = route;
            }
        }
        if (!flag) {
            managerInputOutput.writeLineIO("Элемент не найден\n", Colors.RED);
        } else {
            removeByIdCollections(id);
            managerInputOutput.writeLineIO("Прошлый элемент\n", Colors.YELLOW);
            managerInputOutput.writeLineIO("Прошлое поле name: " + now.getName() + "\n");
            managerInputOutput.writeLineIO("Прошлое поле Coordinates X: " + now.getCoordinates().getX() + "\n");
            managerInputOutput.writeLineIO("Прошлое поле Coordinates Y: " + now.getCoordinates().getY() + "\n");
            managerInputOutput.writeLineIO("Прошлое поле LocationFrom X: " + now.getFrom().getX() + "\n");
            managerInputOutput.writeLineIO("Прошлое поле LocationFrom Y: " + now.getFrom().getY() + "\n");
            managerInputOutput.writeLineIO("Прошлое поле LocationFrom Z: " + now.getTo().getZ() + "\n");
            managerInputOutput.writeLineIO("Прошлое поле LocationTo X: " + now.getTo().getX() + "\n");
            managerInputOutput.writeLineIO("Прошлое поле LocationTo Y: " + now.getTo().getY() + "\n");
            managerInputOutput.writeLineIO("Прошлое поле LocationTo Z: " + now.getTo().getZ() + "\n");
            managerInputOutput.writeLineIO("Прошлое поле distance: " + now.getDistance() + "\n");
            managerInputOutput.writeLineIO("Прошлое поле price: " + now.getPrice() + "\n");

            this.collectionsRoute.add(managerValidationData.validateFromInput(id, time));
            managerInputOutput.writeLineIO("Элемент обновлён\n", Colors.GREEN);
        }
    }

    public int getSizeCollections() {
        return this.collectionsRoute.size();
    }

    public ZonedDateTime getTimeInit() {
        return this.timeInit;
    }

    public void addAllCollection(List<String[]> collectionImportCSV) {
        long maxId = 0;
        for (String[] row : collectionImportCSV) {
            try {
                if (row[0].equals("id")) continue;

                long id = Long.parseLong(row[0]);
                String name = row[1];

                Coordinates coordinates = new Coordinates(
                        Long.parseLong(row[2]),
                        Long.parseLong(row[3])
                );

                ZonedDateTime creationDate = ZonedDateTime.parse(row[4]);

                Location from = new Location(
                        Float.parseFloat(row[5].replace(',', '.')),
                        Double.parseDouble(row[6].replace(',', '.')),
                        Integer.parseInt(row[7])
                );

                Location to = new Location(
                        Float.parseFloat(row[8].replace(',', '.')),
                        Double.parseDouble(row[9].replace(',', '.')),
                        Integer.parseInt(row[10])
                );

                Integer distance = Integer.parseInt(row[11]);

                BigDecimal price = new BigDecimal(row[12].trim().replace(',', '.'));

                Route route = new Route(id, name, coordinates, creationDate, from, to, distance, price);
                collectionsRoute.add(route);

                if (id > maxId) {
                    maxId = id;
                }

                ManagerGenerateId.setId(maxId);
            } catch (NumberFormatException e) {
                managerInputOutput.writeLineIO("Ошибка: неправильный формат\n");
            } catch (Exception e) {
                managerInputOutput.writeLineIO("Ошибка: " + e.getMessage() + "\n");
            }
        }
    }
}