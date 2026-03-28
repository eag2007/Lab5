package org.example.classes.managers.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.enums.Colors;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import static org.example.classes.runner.Runner.managerInputOutput;

public class ManagerApi {
    private static ManagerApi managerApi;
    private final HttpClient client = HttpClient.newHttpClient();
    private int ttlSeconds = 60;

    private ManagerApi() {
    }

    public static ManagerApi getInstance() {
        if (managerApi == null) {
            managerApi = new ManagerApi();
        }
        return managerApi;
    }

    public void setTtl(int ttlSeconds) {
        this.ttlSeconds = ttlSeconds;
        managerInputOutput.writeLineIO("Установлена частота обновления: " + ttlSeconds + " сек\n");
    }

    public BigDecimal getCourse() {
        Jedis jedis = null;
        try {
            jedis = new Jedis("localhost", 6379);

            String cached = jedis.get("usd_rate");
            if (cached != null) {
                return new BigDecimal(cached);
            }

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.cbr-xml-daily.ru/daily_json.js"))
                    .build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.body());
            BigDecimal usdRate = new BigDecimal(
                    root.path("Valute").path("USD").path("Value").asText()
            );
            jedis.setex("usd_rate", ttlSeconds, usdRate.toPlainString());

            return usdRate;

        } catch (IOException | InterruptedException e) {
            managerInputOutput.writeLineIO("Ошибка подключения к API ЦБ\n", Colors.RED);
            return null;
        } catch (Exception e) {
            managerInputOutput.writeLineIO("Redis недоступен\n", Colors.YELLOW);
            return getCourseFromApi();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    private BigDecimal getCourseFromApi() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.cbr-xml-daily.ru/daily_json.js"))
                    .build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.body());
            BigDecimal usdRate = new BigDecimal(
                    root.path("Valute").path("USD").path("Value").asText()
            );
            return usdRate;

        } catch (IOException | InterruptedException e) {
            managerInputOutput.writeLineIO("Ошибка подключения к API ЦБ\n", Colors.RED);
            return null;
        }
    }

    public void clearCache() {
        try {
            Jedis jedis = new Jedis("localhost", 6379);
            jedis.del("usd_rate");
            jedis.close();
            managerInputOutput.writeLineIO("Кэш Redis очищен\n");
        } catch (Exception e) {
            managerInputOutput.writeLineIO("Не удалось очистить кэш Redis\n");
        }
    }
}