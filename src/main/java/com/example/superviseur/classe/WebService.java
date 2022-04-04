package com.example.superviseur.classe;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class WebService {
    private final HttpClient client;
    private HttpRequest httpRequest;

    public WebService() {
        client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
    }

    public void setHttpRequest(String adresse, String url, String request_type, HttpRequest.BodyPublisher data, int timemout_secondes) {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        switch (request_type.toUpperCase()) {
            case "GET":
                builder.GET();
                break;
            case "POST":
                builder.POST(data);
                break;
            case "DELETE":
                builder.DELETE();
                break;
            case "PUT":
                builder.PUT(data);
                break;
            default:
                return;
        }
        httpRequest = builder
                .uri(URI.create(adresse.concat(url)))
                .timeout(Duration.ofSeconds(timemout_secondes))
                .headers("Content-Type", "application/json")
                .build();

        client.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }
}
