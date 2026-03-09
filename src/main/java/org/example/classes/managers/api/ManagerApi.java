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
    private static volatile ManagerApi managerApi;
    private final HttpClient client = HttpClient.newHttpClient();

    private ManagerApi() {
    }

    public static ManagerApi getInstance() {
        if (managerApi == null) {
            synchronized (ManagerApi.class) {
                if (managerApi == null) {
                    managerApi = new ManagerApi();
                }
            }
        }
        return managerApi;
    }

    public BigDecimal getPriceDollarForRublesRedis() {
        try {
            Jedis jedis = new Jedis("localhost", 6379);

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

            jedis.setex("usd_rate", 600, usdRate.toPlainString());

            return usdRate;

        } catch (IOException | InterruptedException e) {
            managerInputOutput.writeLineIO("Что-то пошло не так, клиент не связался с сервером\n", Colors.RED);
            return null;
        } catch (Exception e) {
            managerInputOutput.writeLineIO("Redis недоступен, используем прямой запрос\n", Colors.YELLOW);
            return getPriceDollarForRublesDontRedis();
        }
    }

    public BigDecimal getPriceDollarForRublesDontRedis() {
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
            managerInputOutput.writeLineIO("Что-то пошло не так, клиент не связался с сервером\n", Colors.RED);
            return null;
        }
    }
}