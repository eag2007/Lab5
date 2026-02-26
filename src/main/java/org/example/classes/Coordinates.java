package org.example.classes;

/**
 * Класс, представляющий координаты маршрута.
 * <p>
 * Содержит координаты X и Y, накладывает ограничения на их значения.
 * Реализует {@link Comparable} для сравнения объектов.
 * </p>
 *
 * @author
 * @version 1.0
 * @see Comparable
 */
public class Coordinates implements Comparable<Coordinates> {
    /** Координата X, максимальное значение поля: 108. */
    private long x;
    /** Координата Y, максимальное значение поля: 20. */
    private long y;

    /**
     * Создает новый объект координат.
     *
     * @param x координата X (должна быть в диапазоне (0, 108])
     * @param y координата Y (должна быть в диапазоне (0, 20])
     */
    public Coordinates(long x, long y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Сравнивает текущие координаты с другими.
     * <p>
     * Сначала сравнивается по X, затем по Y.
     * </p>
     *
     * @param o объект для сравнения
     * @return отрицательное число, 0 или положительное число, если текущий объект меньше, равен или больше указанного
     */
    @Override
    public int compareTo(Coordinates o) {
        int xCompare = Long.compare(this.x, o.x);
        if (xCompare != 0) {
            return xCompare;
        }
        return Long.compare(this.y, o.y);
    }

    /**
     * Возвращает координату X.
     *
     * @return значение X
     */
    public long getX() {
        return this.x;
    }

    /**
     * Возвращает координату Y.
     *
     * @return значение Y
     */
    public long getY() {
        return this.y;
    }
}