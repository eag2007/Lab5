package org.example.classes.managers;

/**
 * Утилитарный класс для генерации уникальных идентификаторов.
 * <p>
 * Используется для автоматической генерации ID для новых объектов {@link org.example.classes.Route}.
 * Счетчик является общим для всей программы и потокобезопасным.
 * </p>
 *
 * @author
 * @version 1.0
 */
public class ManagerGenerateId {
    /** Текущее значение счетчика ID. */
    private static long id = 0;

    /**
     * Генерирует и возвращает следующий уникальный ID.
     *
     * @return новый уникальный идентификатор
     */
    public static synchronized long generateId() {
        return ++id;
    }

    /**
     * Устанавливает значение счетчика ID.
     * <p>
     * Используется при импорте данных, чтобы установить счетчик на максимальное значение из загруженных ID.
     * Значение устанавливается как {@code idGet + 1}.
     * </p>
     *
     * @param idGet значение, на основе которого будет установлен новый счетчик
     */
    public static synchronized void setId(long idGet) {
        id = idGet++;
    }
}