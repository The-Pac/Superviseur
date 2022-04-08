package com.example.superviseur.classe;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Robot {
    private StringProperty statut, identifiant;
    private IntegerProperty number_delivery;
    private int x, y;


    /**
     * Statut = "en course" || "pret" || "retour"
     *
     * @param identifiant
     * @param statut
     * @param x
     * @param y
     */
    public Robot(String identifiant, String statut, int x, int y, int number_delivery) {
        this.identifiant = new SimpleStringProperty(this, "identifiant", identifiant);
        this.statut = new SimpleStringProperty(this, "statut", statut);
        this.x = x;
        this.y = y;
        this.number_delivery = new SimpleIntegerProperty(this, "number_delivery", number_delivery);
    }

    public Robot() {
    }

    public int getNumber_delivery() {
        return number_delivery.get();
    }

    public void setNumber_delivery(int number_delivery) {
        this.number_delivery.set(number_delivery);
    }

    public IntegerProperty number_deliveryProperty() {
        return number_delivery;
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

    public String getIdentifiant() {
        return identifiant.get();
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant.set(identifiant);
    }

    public StringProperty identifiantProperty() {
        return identifiant;
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
