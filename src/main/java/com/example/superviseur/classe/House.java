package com.example.superviseur.classe;

public class House {

    private String id;
    private int number;
    private double[] position;

    public House(String id, int number, double[] position) {
        this.id = id;
        this.number = number;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = position;
    }
}
