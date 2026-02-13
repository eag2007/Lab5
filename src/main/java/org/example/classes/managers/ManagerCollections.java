package org.example.classes.managers;

import org.example.classes.Coordinates;
import org.example.classes.Location;
import org.example.classes.Route;
import org.example.interfaces.Command;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import static org.example.classes.runner.Runner.managerInputOutput;

public class ManagerCollections {
    private PriorityQueue<Route> collectionsRoute;
    private ZonedDateTime timeInit;

    public ManagerCollections() {
        this.collectionsRoute = new PriorityQueue<>(Route.COMPARATOR_COLLECTIONS);
        this.timeInit = ZonedDateTime.now();
    }

    public void addCollections(Route element) {
        this.collectionsRoute.add(element);
    }

    public void addIfMaxCollections() {
    }

    public void clearCollections() {
        this.collectionsRoute.clear();
    }

    public void removeAllByDistanceCollections() {
    }

    public void removeByIdCollections() {
    }

    public void removeFirstCollections() {
    }

    public PriorityQueue<Route> getCollectionsRoute() {
        return this.collectionsRoute;
    }

    public void updateCollections() {
    }

    public int getSizeCollections() {
        return this.collectionsRoute.size();
    }

    public ZonedDateTime getTimeInit() {
        return this.timeInit;
    }

    public void addAllCollection(List<String[]> collectionImportCSV) {
        for (String[] row : collectionImportCSV) {
            try {
                if (row[0].equals("id")) continue;

                long id = Long.parseLong(row[0]);
                String name = row[1];

                Coordinates coordinates = new Coordinates(
                        Long.parseLong(row[2]),
                        Long.parseLong(row[3])
                );

                Location from = new Location(
                        Float.parseFloat(row[4].replace(',', '.')),
                        Double.parseDouble(row[5].replace(',', '.')),
                        Integer.parseInt(row[6])
                );

                Location to = new Location(
                        Float.parseFloat(row[7].replace(',', '.')),
                        Double.parseDouble(row[8].replace(',', '.')),
                        Integer.parseInt(row[9])
                );

                Integer distance = Integer.parseInt(row[10]);
                ZonedDateTime creationDate = ZonedDateTime.parse(row[11]);

                Route route = new Route(id, name, coordinates, creationDate, from, to, distance);
                collectionsRoute.add(route);

            } catch (Exception e) {
                managerInputOutput.writeLineIO("Ошибка: " + e.getMessage() + "\n");
            }
        }
    }
}
