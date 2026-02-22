package org.example.classes;

import org.example.classes.managers.ManagerGenerateId;

import java.time.ZonedDateTime;
import java.util.Comparator;
//import java.util.Comparable;

import static org.example.classes.runner.Runner.managerInputOutput;

public class Route {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Location from; //Поле может быть null
    private Location to; //Поле не может быть null
    private Integer distance; //Поле может быть null, Значение поля должно быть больше 1

    /*
    @Override
    public int compareTo(Route o) {
        int idCompare = Long.compare(this.id, o.id);
        if (idCompare != 0) {
            return idCompare;
        }

        int nameCompare = this.name.compareTo(o.name);
        if (nameCompare != 0) {
            return nameCompare;
        }

        int coordCompare = this.coordinates.compareTo(o.coordinates);
        if (coordCompare != 0) {
            return coordCompare;
        }

        int dateCompare = this.creationDate.compareTo(o.creationDate);
        if (dateCompare != 0) {
            return dateCompare;
        }

        if (this.from == null) {
            return -1;
        }

        if (o.from == null) {
            return 1;
        }

        int locFromCompare = this.from.compareTo(o.from);
        if (locFromCompare != 0) {
            return locFromCompare;
        }

        int locToCompare = this.to.compareTo(o.to);
        if (locToCompare != 0) {
            return locToCompare;
        }

        if (this.distance == null) {
            return -1;
        }

        if (o.distance == null) {
            return 1;
        }

        return Integer.compare(this.distance, o.distance);
    }*/
    public static Comparator<Route> COMPARATOR_COLLECTIONS = Comparator
            .comparingLong(Route::getId)
            .thenComparing(Route::getDistance)
            .thenComparing(Route::getName)
            .thenComparing(Route::getFrom, Comparator.nullsLast(Comparator.comparing(Location::getX).thenComparing(Location::getY).thenComparing(Location::getZ)))
            .thenComparing(Route::getTo, Comparator.comparing(Location::getX).thenComparing(Location::getY).thenComparing(Location::getZ))
            .thenComparing(Route::getCreationDate);

    public Route(String name, Coordinates coordinates, Location from, Location to, Integer distance) {
        this.id = ManagerGenerateId.generateId();
        this.creationDate = ZonedDateTime.now();
        this.name = name;
        this.coordinates = coordinates;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public Route(long id, String name, Coordinates coordinates, Location from, Location to, Integer distance) {
        this.id = id;
        this.creationDate = ZonedDateTime.now();
        this.name = name;
        this.coordinates = coordinates;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public Route(long id, String name, Coordinates coordinates, ZonedDateTime creationDate, Location from, Location to, Integer distance) {
        this.id = id;
        this.creationDate = ZonedDateTime.now();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public long getId() {
        return this.id;
    }

    public ZonedDateTime getCreationDate() {
        return this.creationDate;
    }

    public String getName() {
        return this.name;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public Location getFrom() {
        return this.from;
    }

    public Location getTo() {
        return this.to;
    }

    public Integer getDistance() {
        return this.distance;
    }
}
