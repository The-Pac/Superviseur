package com.example.superviseur.classe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.json.JsonValue;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                clear_data();


                //deliveries
                data = webService.setHttpRequest(ADDRESS, "livraisons/", WebService.GET, null, TIMEOUT);
                if (data != null) {
                    jsonReader = Json.createReader(new StringReader(data));
                    jsonArray = jsonReader.readArray();
                    Date date_arriver, date_livrer = null;
                    long difference_In_Time, difference_In_Days = 0;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    JsonValue jsonValue_delivery = null,
                            jsonValue_package_array = null,
                            jsonValue_robot = null, jsonValue_package = null, jsonValue_house = null;

                    for (JsonValue jsonValue : jsonArray) {
                        for (JsonValue jsonValue_1 : jsonValue.asJsonArray()) {
                            switch (jsonValue_1.asJsonArray().get(0).toString().replace("\"", "")) {
                                case "livraison":
                                    jsonValue_delivery = jsonValue_1.asJsonArray().get(1);
                                    break;
                                case "paquet":
                                    jsonValue_package_array = jsonValue_1.asJsonArray().get(1);
                                    for (JsonValue jsonValue_package_value : jsonValue_package_array.asJsonArray()) {
                                        switch (jsonValue_package_value.asJsonArray().get(0).toString().replace("\"", "")) {
                                            case "paquet":
                                                jsonValue_package = jsonValue_package_array.asJsonArray().get(0).asJsonArray().get(1);
                                                break;
                                            case "maison":
                                                jsonValue_house = jsonValue_package_array.asJsonArray().get(1).asJsonArray().get(1);
                                                break;
                                        }
                                    }

                                    break;
                                case "robot":
                                    jsonValue_robot = jsonValue_1.asJsonArray().get(1);
                                    break;
                            }
                        }

                        if (jsonValue_delivery != null &&
                                jsonValue_package_array != null &&
                                jsonValue_package != null &&
                                jsonValue_house != null) {
                            try {
                                if (!jsonValue_delivery.asJsonObject().get("date_livrer").toString().replace("\"", "").equals("null")) {

                                    date_arriver = simpleDateFormat.parse(jsonValue_package.asJsonObject().get("date_arriver").toString().replace("\"", ""));
                                    date_livrer = simpleDateFormat.parse(jsonValue_delivery.asJsonObject().get("date_livrer").toString().replace("\"", ""));
                                    difference_In_Time = date_livrer.getTime() - date_arriver.getTime();
                                    difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;

                                }
                                if (jsonValue_robot != null && !jsonValue_delivery.asJsonObject().get("id_robot").toString().replace("\"", "").equals("null")) {
                                    deliveries.add(new Delivery(jsonValue_delivery.asJsonObject().get("statut").toString().replace("\"", "")
                                            , new Package(jsonValue_package.asJsonObject().getInt("id_paquet"),
                                            new House(jsonValue_house.asJsonObject().getInt("id_maison"),
                                                    jsonValue_house.asJsonObject().getInt("numero"),
                                                    jsonValue_house.asJsonObject().getInt("id_croisement")),
                                            simpleDateFormat.parse(jsonValue_package.asJsonObject().get("date_arriver").toString().replace("\"", "")).toString(),
                                            jsonValue_package.asJsonObject().get("identifiant").toString().replace("\"", "")),
                                            new Robot(jsonValue_robot.asJsonObject().get("identifiant").toString().replace("\"", ""),
                                                    jsonValue_robot.asJsonObject().get("statut").toString().replace("\"", ""),
                                                    jsonValue_robot.asJsonObject().getInt("x"),
                                                    jsonValue_robot.asJsonObject().getInt("y"),
                                                    jsonValue_robot.asJsonObject().getInt("number_delivery")),
                                            jsonValue_delivery.asJsonObject().get("date_livrer").toString().replace("\"", "").equals("null") ? "" : jsonValue_delivery.asJsonObject().get("date_livrer").toString().replace("\"", ""),
                                            difference_In_Days == 0 ? "" : difference_In_Days + "jours"));
                                } else {
                                    deliveries.add(new Delivery(jsonValue_delivery.asJsonObject().get("statut").toString().replace("\"", "")
                                            , new Package(jsonValue_package.asJsonObject().getInt("id_paquet"),
                                            new House(jsonValue_house.asJsonObject().getInt("id_maison"),
                                                    jsonValue_house.asJsonObject().getInt("numero"),
                                                    jsonValue_house.asJsonObject().getInt("id_croisement")),
                                            simpleDateFormat.parse(jsonValue_package.asJsonObject().get("date_arriver").toString().replace("\"", "")).toString(),
                                            jsonValue_package.asJsonObject().get("identifiant").toString().replace("\"", "")),
                                            null,
                                            jsonValue_delivery.asJsonObject().get("date_livrer").toString().replace("\"", "").equals("null") ? "" : jsonValue_delivery.asJsonObject().get("date_livrer").toString().replace("\"", ""),
                                            difference_In_Days == 0 ? "" : difference_In_Days + "jours"));
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }

                //robots
                data = webService.setHttpRequest(ADDRESS, "robots/", WebService.GET, null, TIMEOUT);
                if (data != null) {
                    jsonReader = Json.createReader(new StringReader(data));
                    jsonArray = jsonReader.readArray();
                    for (JsonValue jsonValue : jsonArray) {
                        robots.add(new Robot(jsonValue.asJsonObject().get("identifiant").toString().replace("\"", ""),
                                jsonValue.asJsonObject().get("statut").toString().replace("\"", ""),
                                jsonValue.asJsonObject().getInt("x"),
                                jsonValue.asJsonObject().getInt("y"),
                                jsonValue.asJsonObject().getInt("number_delivery")));
                    }
                }

                //intersections
                data = webService.setHttpRequest(ADDRESS, "croisements/", WebService.GET, null, TIMEOUT);
                if (data != null) {
                    jsonReader = Json.createReader(new StringReader(data));
                    jsonArray = jsonReader.readArray();
                    ObservableList<House> houses;
                    JsonValue jsonValue_intersection = null, jsonValue_house_array = null;

                    for (JsonValue jsonValue : jsonArray) {
                        houses = FXCollections.observableArrayList();
                        for (JsonValue jsonValue_1 : jsonValue.asJsonArray()) {
                            switch (jsonValue_1.asJsonArray().get(0).toString().replace("\"", "")) {
                                case "croisement":
                                    jsonValue_intersection = jsonValue_1.asJsonArray().get(1);
                                    break;
                                case "maison":
                                    jsonValue_house_array = jsonValue_1.asJsonArray().get(1);
                                    break;
                            }
                        }
                        if (jsonValue_intersection != null && jsonValue_house_array != null) {
                            for (JsonValue jsonValue_house : jsonValue_house_array.asJsonArray()) {
                                houses.add(new House(jsonValue_house.asJsonObject().getInt("id_maison"),
                                        jsonValue_house.asJsonObject().getInt("numero"),
                                        jsonValue_house.asJsonObject().getInt("id_croisement")));
                            }
                            intersections.add(new Intersection(jsonValue_intersection.asJsonObject().getInt("id_croisement"),
                                    jsonValue_intersection.asJsonObject().getInt("x"),
                                    jsonValue_intersection.asJsonObject().getInt("y"),
                                    houses
                            ));
                        }
                    }
                }

                //houses
                data = webService.setHttpRequest(ADDRESS, "maisons/", WebService.GET, null, TIMEOUT);
                if (data != null) {
                    jsonReader = Json.createReader(new StringReader(data));
                    jsonArray = jsonReader.readArray();

                    for (JsonValue jsonValue : jsonArray) {
                        houses.add(new House(jsonValue.asJsonObject().getInt("id_maison"), jsonValue.asJsonObject().getInt("numero"), jsonValue.asJsonObject().getInt("id_croisement")));
                    }
                }

                //packages
                data = webService.setHttpRequest(ADDRESS, "paquets/", WebService.GET, null, TIMEOUT);
                if (data != null) {
                    jsonReader = Json.createReader(new StringReader(data));
                    jsonArray = jsonReader.readArray();
                    House house = null;
                    JsonValue jsonValue_2 = null;
                    for (JsonValue jsonValue : jsonArray) {
                        for (JsonValue jsonValue_1 : jsonValue.asJsonArray()) {
                            switch (jsonValue_1.asJsonArray().get(0).toString().replace("\"", "")) {
                                case "paquet":
                                    jsonValue_2 = jsonValue_1.asJsonArray().get(1);
                                    break;
                                case "maison":
                                    house = new House(jsonValue_1.asJsonArray().get(1).asJsonObject().getInt("id_maison"),
                                            jsonValue_1.asJsonArray().get(1).asJsonObject().getInt("numero"),
                                            jsonValue_1.asJsonArray().get(1).asJsonObject().getInt("id_croisement"));
                                    break;
                            }
                        }
                        if (jsonValue_2 != null) {
                            packages.add(new Package(jsonValue_2.asJsonObject().getInt("id_paquet"),
                                    house,
                                    jsonValue_2.asJsonObject().get("date_arriver").toString().replace("\"", ""),
                                    jsonValue_2.asJsonObject().get("identifiant").toString().replace("\"", "")));
                        }


                    }
                }

            }
        }, 0, 10 * 1000);
    }

    private void clear_data() {
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
