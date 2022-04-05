package com.example.superviseur.classe;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class WebService {
    public static final String GET = "GET", POST = "POST", DELETE = "DELETE", PUT = "PUT";
    private final HttpClient client;
    private HttpResponse<String> httpResponse;
    private HttpRequest httpRequest;

    public WebService() {
        client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
    }

    public String setHttpRequest(String adresse, String url, String request_type, String data, int timemout_secondes) {
        System.out.println("Request : " + request_type + " " + adresse + url + "\ndata:" + data);
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        switch (request_type) {
            case GET:
                builder.GET();
                break;
            case POST:
                builder.POST(HttpRequest.BodyPublishers.ofString(data));
                break;
            case DELETE:
                builder.DELETE();
                break;
            case PUT:
                builder.PUT(HttpRequest.BodyPublishers.ofString(data));
                break;
            default:
                return adresse;
        }
        httpRequest = builder
                .uri(URI.create(adresse.concat(url)))
                .timeout(Duration.ofSeconds(timemout_secondes))
                .headers("Content-Type", "application/json")
                .build();

        //async
        /*CompletableFuture<Void> future = client.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    System.out.println(response.statusCode());
                    return response;
                })
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);
        System.out.println(future);*/

        //sync
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
