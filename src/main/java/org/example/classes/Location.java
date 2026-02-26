package org.example.classes;

/**
 * Класс, представляющий локацию (точку на карте).
 * <p>
 * Содержит координаты X (float), Y (Double, не null) и Z (int).
 * Реализует {@link Comparable} для сравнения объектов.
 * </p>
 *
 * @author
 * @version 1.0
 * @see Comparable
 */
public class Location implements Comparable<Location>{
    /** Координата X. */
    private float x;
    /** Координата Y. Поле не может быть null. */
    private Double y;
    /** Координата Z. */
    private int z;

    /**
     * Сравнивает текущую локацию с другой.
     * <p>
     * Сначала сравнивается по X, затем по Y, затем по Z.
     * </p>
     *
     * @param o объект для сравнения
     * @return отрицательное число, 0 или положительное число, если текущий объект меньше, равен или больше указанного
     */
    @Override
    public int compareTo(Location o) {
        int xCompare = Float.compare(this.x, o.x);
        if (xCompare != 0) {
            return xCompare;
        }

        int yCompare = Double.compare(this.y, o.y);
        if (yCompare != 0) {
            return yCompare;
        }

        return Integer.compare(this.z, o.z);
    }

    /**
     * Создает новый объект локации.
     *
     * @param x координата X
     * @param y координата Y (не может быть null)
     * @param z координата Z
     */
    public Location(float x, Double y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Возвращает координату X.
     *
     * @return значение X
     */
    public float getX() {
        return this.x;
    }

    /**
     * Возвращает координату Y.
     *
     * @return значение Y
     */
    public Double getY() {
        return this.y;
    }

    /**
     * Возвращает координату Z.
     *
     * @return значение Z
     */
    public int getZ() {
        return this.z;
    }
}