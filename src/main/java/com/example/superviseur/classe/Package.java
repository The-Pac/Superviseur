package com.example.superviseur.classe;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Package {
    private final StringProperty date;
    private final StringProperty identifiant;
    private final IntegerProperty id_paquet;
    private House house;

    /**
     * @param id_paquet
     * @param house
     * @param date
     */
    public Package(int id_paquet, House house, String date, String identifiant) {
        this.id_paquet = new SimpleIntegerProperty(this, "id_paquet", id_paquet);
        this.identifiant = new SimpleStringProperty(this, "identifiant", identifiant);
        this.house = house;
        this.date = new SimpleStringProperty(this, "date", date);
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

    public int getId_paquet() {
        return id_paquet.get();
    }

    public void setId_paquet(int id_paquet) {
        this.id_paquet.set(id_paquet);
    }

    public IntegerProperty id_paquetProperty() {
        return id_paquet;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public StringProperty dateProperty() {
        return date;
    }
}
