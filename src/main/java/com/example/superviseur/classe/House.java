package com.example.superviseur.classe;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class House {

    private final StringProperty id;
    private final IntegerProperty number, x, y;


    /**
     * @param id
     * @param number
     * @param x
     * @param y
     */
    public House(String id, int number, int x, int y) {
        this.id = new SimpleStringProperty(this, "id", id);
        this.number = new SimpleIntegerProperty(this, "number", number);
        this.x = new SimpleIntegerProperty(this, "x", x);
        this.y = new SimpleIntegerProperty(this, "y", y);
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

    public int getNumber() {
        return number.get();
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public IntegerProperty numberProperty() {
        return number;
    }

    public int getX() {
        return x.get();
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public int getY() {
        return y.get();
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public IntegerProperty yProperty() {
        return y;
    }
}
