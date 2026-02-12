package org.example.classes;

import org.example.classes.managers.ManagerGenerateId;

import java.time.ZonedDateTime;
import java.util.Comparator;

import static org.example.classes.runner.Runner.managerInputOutput;

public class Route {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Location from; //Поле может быть null
    private Location to; //Поле не может быть null
    private Integer distance; //Поле может быть null, Значение поля должно быть больше 1

    public static Comparator<Route> COMPARATOR_COLLECTIONS = Comparator
            .comparing(Route::getDistance)
            .thenComparing(Route::getName)
            .thenComparingLong(Route::getId);

    public Route() {
        this.id = ManagerGenerateId.generateId();
        this.creationDate = ZonedDateTime.now();
        setName();
        setCoordinates();
        setLocationFrom();
        setLocationTo();
        setDistance();
    }

    public Route(long id) {
        this.id = id;
        this.creationDate = ZonedDateTime.now();
        setName();
        setCoordinates();
        setLocationFrom();
        setLocationTo();
        setDistance();
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



    private void setName() {
        managerInputOutput.writeLineIO("Введите name : ");
        String name = managerInputOutput.readLineIO();

        while (name.isEmpty()) {
            managerInputOutput.writeLineIO("NAME не может быть null\n");
            managerInputOutput.writeLineIO("Введите name : ");
            name = managerInputOutput.readLineIO();
        }

        this.name = name;
    }

    private void setCoordinates() {
        this.coordinates = new Coordinates();
    }

    private void setLocationFrom() {
        this.from = new Location();
    }

    private void setLocationTo() {
        this.to = new Location();
    }

    private void setDistance() {
        managerInputOutput.writeLineIO("Введите distance : ");

        int distance;
        while (true) {
            if (managerInputOutput.hasNextIntIO()) {
                distance = managerInputOutput.nextIntIO();
                managerInputOutput.readLineIO();
                if (distance > 1) {
                    this.distance = distance;
                    break;
                } else {
                    managerInputOutput.writeLineIO("DISTANCE должно быть больше\n");
                }
            } else {
                managerInputOutput.readLineIO();
                managerInputOutput.writeLineIO("DISTANCE должно быть типа Integer\n");
            }

            managerInputOutput.writeLineIO("Введите distance : ");
        }
    }
}
