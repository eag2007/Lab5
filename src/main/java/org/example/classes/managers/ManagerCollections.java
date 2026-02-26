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

/**
 * Менеджер для управления коллекцией маршрутов.
 * <p>
 * Хранит основную коллекцию {@link PriorityQueue} объектов {@link Route},
 * предоставляет методы для её модификации, получения информации и импорта данных.
 * </p>
 *
 * @author
 * @version 1.0
 */
public class ManagerCollections {
    /** Основная коллекция маршрутов. */
    private PriorityQueue<Route> collectionsRoute;
    /** Время инициализации менеджера/коллекции. */
    private ZonedDateTime timeInit;

    /**
     * Конструктор, инициализирующий пустую коллекцию и фиксирующий время создания.
     */
    public ManagerCollections() {
        this.collectionsRoute = new PriorityQueue<>();
        this.timeInit = ZonedDateTime.now();
    }

    /**
     * Добавляет элемент в коллекцию.
     *
     * @param element элемент для добавления
     */
    public void addCollections(Route element) {
        this.collectionsRoute.add(element);
    }

    /**
     * Очищает коллекцию, удаляя все элементы.
     */
    public void clearCollections() {
        this.collectionsRoute.clear();
    }

    /**
     * Заменяет текущую коллекцию новой, используется для удаления группы элементов.
     *
     * @param routes новая коллекция
     */
    public void removeAllByDistanceCollections(PriorityQueue<Route> routes) {
        this.collectionsRoute = routes;
    }

    /**
     * Удаляет элемент с указанным ID из коллекции.
     *
     * @param id идентификатор удаляемого элемента
     */
    public void removeByIdCollections(Long id) {
        this.collectionsRoute.removeIf(route -> ((Long) route.getId()).equals(id));
    }

    /**
     * Удаляет первый элемент коллекции (с наивысшим приоритетом).
     */
    public void removeFirstCollections() {
        this.collectionsRoute.poll();
    }

    /**
     * Возвращает список элементов коллекции, отсортированных в естественном порядке.
     *
     * @return отсортированный список маршрутов
     * @see Route#compareTo(Route)
     */
    public List<Route> getSortedCollections() {
        List<Route> sorted = new ArrayList<>(collectionsRoute);
        sorted.sort(Comparator.naturalOrder());
        return sorted;
    }

    /**
     * Возвращает исходную коллекцию (без копирования).
     *
     * @return очередь с приоритетом, содержащую все маршруты
     */
    public PriorityQueue<Route> getCollectionsRoute() {
        return this.collectionsRoute;
    }

    /**
     * Обновляет элемент по ID: удаляет старый и добавляет новый, созданный на основе ввода пользователя.
     *
     * @param id идентификатор обновляемого элемента
     */
    public void updateCollections(Long id) {
        removeByIdCollections(id);
        this.collectionsRoute.add(managerValidationData.validateFromInput(id));
    }

    /**
     * Возвращает размер коллекции.
     *
     * @return количество элементов в коллекции
     */
    public int getSizeCollections() {
        return this.collectionsRoute.size();
    }

    /**
     * Возвращает время инициализации менеджера.
     *
     * @return временная метка создания менеджера
     */
    public ZonedDateTime getTimeInit() {
        return this.timeInit;
    }

    /**
     * Добавляет все элементы из импортированного CSV-файла в коллекцию.
     * <p>
     * Парсит строки CSV, создает объекты {@link Route} и добавляет их в коллекцию.
     * Также обновляет глобальный счетчик ID в {@link ManagerGenerateId},
     * чтобы избежать конфликтов при последующем создании элементов.
     * </p>
     *
     * @param collectionImportCSV список строк CSV, где каждая строка — массив полей маршрута
     */
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