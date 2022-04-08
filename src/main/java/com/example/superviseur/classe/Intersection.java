package com.example.superviseur.classe;

import javafx.collections.ObservableList;

public class Intersection {
    private final WebService webService;
    private int id_croisement;
    private int x, y;
    private ObservableList<House> Houses_list;

    /**
     * @param id_croisement
     * @param x
     * @param y
     * @param Houses_list
     */
    public Intersection(int id_croisement, int x, int y, ObservableList<House> Houses_list) {
        this.id_croisement = id_croisement;
        this.x = x;
        this.y = y;
        this.webService = new WebService();
        this.Houses_list = Houses_list;
    }

    public void add_houses(House house) {
        Houses_list.add(house);
    }

    public ObservableList<House> getHouses_list() {
        return Houses_list;
    }

    public void setHouses_list(ObservableList<House> houses_list) {
        Houses_list = houses_list;
    }

    public int getId_croisement() {
        return id_croisement;
    }

    public void setId_croisement(int id_croisement) {
        this.id_croisement = id_croisement;
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
}
