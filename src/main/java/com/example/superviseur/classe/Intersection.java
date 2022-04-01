package com.example.superviseur.classe;

public class Intersection {
    private String id;
    private Double[] position;
    private House[] houses;

    public Intersection(String id, Double[] position) {
        this.id = id;
        this.position = position;
    }

    public void get_intersection() {


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double[] getPosition() {
        return position;
    }

    public void setPosition(Double[] position) {
        this.position = position;
    }

    public House[] getHouses() {
        return houses;
    }

    public void setHouses(House[] houses) {
        this.houses = houses;
    }
}
