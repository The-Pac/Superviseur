package com.example.superviseur.classe;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Delivery {
    private final StringProperty statut, date;
    private Package aPackage;
    private Robot robot;


    /**
     * Statut = "à livrer" || "en cours de livraison" || "livré"
     *
     * @param statut
     * @param aPackage
     * @param robot
     * @param date
     */
    public Delivery(String statut, Package aPackage, Robot robot, String date) {
        this.statut = new SimpleStringProperty(this, "statut", statut);
        this.aPackage = aPackage;
        this.robot = robot;
        this.date = new SimpleStringProperty(this, "date", date);
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

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public StringProperty dateProperty() {
        return date;
    }

    public Package getaPackage() {
        return aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }
}
