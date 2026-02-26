package org.example.classes;

import org.example.classes.managers.ManagerGenerateId;

import java.time.ZonedDateTime;

/**
 * Класс, представляющий основной объект коллекции — маршрут.
 * <p>
 * Содержит все поля, соответствующие техническому заданию. Реализует {@link Comparable}
 * для определения естественного порядка сортировки в {@link java.util.PriorityQueue}.
 * </p>
 *
 * @author
 * @version 1.0
 * @see Comparable
 */
public class Route implements Comparable<Route> {
    /** Уникальный идентификатор. Значение поля должно быть больше 0, уникально, генерируется автоматически. */
    private long id;
    /** Название маршрута. Поле не может быть null, строка не может быть пустой. */
    private String name;
    /** Координаты. Поле не может быть null. */
    private Coordinates coordinates;
    /** Дата создания. Поле не может быть null, генерируется автоматически. */
    private java.time.ZonedDateTime creationDate;
    /** Локация отправления. Поле может быть null. */
    private Location from;
    /** Локация назначения. Поле не может быть null. */
    private Location to;
    /** Расстояние. Поле может быть null, значение должно быть больше 1. */
    private Integer distance;

    /**
     * Сравнивает текущий маршрут с другим для определения порядка.
     * <p>
     * Порядок сравнения: ID -> Name -> Coordinates -> CreationDate -> From (null считается меньшим) -> To -> Distance (null считается меньшим).
     * </p>
     *
     * @param o объект для сравнения
     * @return отрицательное число, 0 или положительное число, если текущий объект меньше, равен или больше указанного
     */
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
    }

    /**
     * Конструктор для создания нового маршрута (ID и дата генерируются автоматически).
     *
     * @param name        название маршрута
     * @param coordinates координаты
     * @param from        локация отправления
     * @param to          локация назначения
     * @param distance    расстояние
     */
    public Route(String name, Coordinates coordinates, Location from, Location to, Integer distance) {
        this.id = ManagerGenerateId.generateId();
        this.creationDate = ZonedDateTime.now();
        this.name = name;
        this.coordinates = coordinates;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    /**
     * Конструктор для создания маршрута с заданным ID (дата генерируется автоматически).
     * Используется при обновлении элемента.
     *
     * @param id          идентификатор
     * @param name        название
     * @param coordinates координаты
     * @param from        локация отправления
     * @param to          локация назначения
     * @param distance    расстояние
     */
    public Route(long id, String name, Coordinates coordinates, Location from, Location to, Integer distance) {
        this.id = id;
        this.creationDate = ZonedDateTime.now();
        this.name = name;
        this.coordinates = coordinates;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    /**
     * Конструктор для создания маршрута с заданными ID и датой создания.
     * Используется при импорте из файла.
     *
     * @param id           идентификатор
     * @param name         название
     * @param coordinates  координаты
     * @param creationDate дата создания
     * @param from         локация отправления
     * @param to           локация назначения
     * @param distance     расстояние
     */
    public Route(long id, String name, Coordinates coordinates, ZonedDateTime creationDate, Location from, Location to, Integer distance) {
        this.id = id;
        this.creationDate = creationDate;
        this.name = name;
        this.coordinates = coordinates;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public long getId() { return this.id; }
    public ZonedDateTime getCreationDate() { return this.creationDate; }
    public String getName() { return this.name; }
    public Coordinates getCoordinates() { return this.coordinates; }
    public Location getFrom() { return this.from; }
    public Location getTo() { return this.to; }
    public Integer getDistance() { return this.distance; }
}