package com.example.superviseur.classe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Intersection {
    private final WebService webService;
    private String id;
    private int x, y;
    private ObservableList<House> houses_list;

    /**
     * @param id
     * @param x
     * @param y
     */
    public Intersection(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.houses_list = FXCollections.observableArrayList();
        webService = new WebService();
    }

    /**
     * Get all the houses & intersections
     */
    public void get_houses() {
        /*for (String s : webService.setHttpRequest(ADDRESS, "maisons/", WebService.GET, null, TIMEOUT)) {

        }

        this.houses_list.add();*/
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ObservableList<House> getHouses_list() {
        return houses_list;
    }

    public void setHouses_list(ObservableList<House> houses_list) {
        this.houses_list = houses_list;
    }
}
