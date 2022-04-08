package com.example.superviseur.classe;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class House {

    private final IntegerProperty id_house, number, id_croisement;

    /**
     * @param id_house
     * @param number
     */
    public House(int id_house, int number, int id_croisement) {
        this.id_house = new SimpleIntegerProperty(this, "id_house", id_house);
        this.number = new SimpleIntegerProperty(this, "number", number);
        this.id_croisement = new SimpleIntegerProperty(this, "id_croisement", id_croisement);
    }

    public int getId_croisement() {
        return id_croisement.get();
    }

    public void setId_croisement(int id_croisement) {
        this.id_croisement.set(id_croisement);
    }

    public IntegerProperty id_croisementProperty() {
        return id_croisement;
    }

    public int getId_house() {
        return id_house.get();
    }

    public void setId_house(int id_house) {
        this.id_house.set(id_house);
    }

    public IntegerProperty id_houseProperty() {
        return id_house;
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
}
