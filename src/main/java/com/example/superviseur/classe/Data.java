package com.example.superviseur.classe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.superviseur.MainController.ADDRESS;
import static com.example.superviseur.MainController.TIMEOUT;

public class Data {
    private final WebService webService;
    private ObservableList<House> houses;
    private ObservableList<Intersection> intersections;
    private ObservableList<Robot> robots;
    private ObservableList<Package> packages;
    private ObservableList<Delivery> deliveries;


    /**
     * init webservice & ObservableList
     */
    public Data() {
        webService = new WebService();
        houses = FXCollections.observableArrayList();
        intersections = FXCollections.observableArrayList();
        packages = FXCollections.observableArrayList();
        deliveries = FXCollections.observableArrayList();
        robots = FXCollections.observableArrayList();
    }

    /**
     * Create TimerTask with 10 sec between refresh that refill the ObservableList
     */
    public void refresh_thread() {
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                JsonArray jsonArray;
                JsonReader jsonReader;
                String data;
                //clear data
                if (!robots.isEmpty()) {
                    robots.clear();
                }
                if (!deliveries.isEmpty()) {
                    deliveries.clear();
                }
                if (!houses.isEmpty()) {
                    houses.clear();
                }
                if (!packages.isEmpty()) {
                    packages.clear();
                }
                if (!intersections.isEmpty()) {
                    intersections.clear();
                }


                //deliveries
                data = webService.setHttpRequest(ADDRESS, "livraisons/", WebService.GET, null, TIMEOUT);
                jsonReader = Json.createReader(new StringReader(data));
                jsonArray = jsonReader.readArray();
                //deliveries.add(new Delivery(jsonObject.getString("statut_livraison"), jsonObject.get("paquet"), "", jsonObject.getString("dateheure")));

                //robots
                data = webService.setHttpRequest(ADDRESS, "robots/", WebService.GET, null, TIMEOUT);
                jsonReader = Json.createReader(new StringReader(data));
                jsonArray = jsonReader.readArray();
                //robots.add(new Robot("", "", "", ""));

                //intersections
                data = webService.setHttpRequest(ADDRESS, "croisements/", WebService.GET, null, TIMEOUT);
                jsonReader = Json.createReader(new StringReader(data));
                jsonArray = jsonReader.readArray();
                //intersections.add(new Robot("", "", "", ""));

                //houses
                data = webService.setHttpRequest(ADDRESS, "maisons/", WebService.GET, null, TIMEOUT);
                jsonReader = Json.createReader(new StringReader(data));
                jsonArray = jsonReader.readArray();
                //houses.add(new Robot("", "", "", ""));

                //packages
                data = webService.setHttpRequest(ADDRESS, "paquets/", WebService.GET, null, TIMEOUT);
                jsonReader = Json.createReader(new StringReader(data));
                jsonArray = jsonReader.readArray();
                //packages.add(new Robot("", "", "", ""));

            }
        }, 0, 10 * 1000);
    }

    public ObservableList<House> getHouses() {
        return houses;
    }

    public void setHouses(ObservableList<House> houses) {
        this.houses = houses;
    }

    public ObservableList<Intersection> getIntersections() {
        return intersections;
    }

    public void setIntersections(ObservableList<Intersection> intersections) {
        this.intersections = intersections;
    }

    public ObservableList<Robot> getRobots() {
        return robots;
    }

    public void setRobots(ObservableList<Robot> robots) {
        this.robots = robots;
    }

    public ObservableList<Package> getPackages() {
        return packages;
    }

    public void setPackages(ObservableList<Package> packages) {
        this.packages = packages;
    }

    public ObservableList<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(ObservableList<Delivery> deliveries) {
        this.deliveries = deliveries;
    }
}
