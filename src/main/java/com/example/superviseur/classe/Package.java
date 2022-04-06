package com.example.superviseur.classe;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Package {
    private StringProperty id, date;
    private House house;

    public Package() {
    }

    /**
     * @param id
     * @param house
     * @param date
     */
    public Package(String id, House house, String date) {
        this.id = new SimpleStringProperty(this, "id", id);
        this.house = house;
        this.date = new SimpleStringProperty(this, "date", date);
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
