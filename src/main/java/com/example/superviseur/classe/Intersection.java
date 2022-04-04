package com.example.superviseur.classe;

public class Intersection {
    private String id;
    private int x, y;
    private House[] houses;

    public Intersection(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public void get_intersection() {


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

    public House[] getHouses() {
        return houses;
    }

    public void setHouses(House[] houses) {
        this.houses = houses;
    }
}
