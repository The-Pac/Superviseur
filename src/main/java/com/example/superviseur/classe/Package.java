package com.example.superviseur.classe;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class Package {
    private StringProperty id;
    private House house;
    private Date date;

    public Package() {
    }

    public Package(String id, House house, Date date) {
        this.id = new SimpleStringProperty(this, "id", id);
        this.house = house;
        this.date = date;
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

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
