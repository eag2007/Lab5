package org.example.classes.managers;

import org.example.classes.Route;
import org.example.interfaces.Command;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.PriorityQueue;

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
}
