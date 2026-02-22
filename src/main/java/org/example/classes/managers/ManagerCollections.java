package org.example.classes.managers;

import org.example.classes.Coordinates;
import org.example.classes.Location;
import org.example.classes.Route;
import org.example.interfaces.Command;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import static org.example.classes.runner.Runner.managerInputOutput;
import static org.example.classes.runner.Runner.managerValidationData;

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

    public void removeAllByDistanceCollections(PriorityQueue<Route> routes) {
        this.collectionsRoute = routes;
    }

    public void removeByIdCollections(Long id) {
        this.collectionsRoute.removeIf(route -> ((Long) route.getId()).equals(id));
    }

    public void removeFirstCollections() {
        this.collectionsRoute.poll();
    }

    public List<Route> getSortedCollections() {
        List<Route> sorted = new ArrayList<>(collectionsRoute);
        sorted.sort(Route.COMPARATOR_COLLECTIONS);
        return sorted;
    }

    public PriorityQueue<Route> getCollectionsRoute() {
        return this.collectionsRoute;
    }

    public void updateCollections(Long id) {
        removeByIdCollections(id);
        this.collectionsRoute.add(managerValidationData.validateFromInput(id));
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

                Route route = new Route(id, name, coordinates, creationDate, from, to, distance);
                collectionsRoute.add(route);

                if (id > maxId) {
                    maxId = id;
                }

                ManagerGenerateId.setId(maxId);

            } catch (Exception e) {
                managerInputOutput.writeLineIO("Ошибка: " + e.getMessage() + "\n");
            }
        }
    }
}
