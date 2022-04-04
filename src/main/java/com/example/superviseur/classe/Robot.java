package com.example.superviseur.classe;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Robot {
    private StringProperty id, statut;
    private int x, y;

    public Robot() {
    }

    /**
     * Statut = "en course" || "pret"
     */

    public Robot(String id, String statut, int x, int y) {
        this.id = new SimpleStringProperty(this, "id", id);
        this.statut = new SimpleStringProperty(this, "statut", statut);
        this.x = x;
        this.y = y;
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getStatut() {
        return statut.get();
    }

    public void setStatut(String statut) {
        this.statut.set(statut);
    }

    public StringProperty statutProperty() {
        return statut;
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
