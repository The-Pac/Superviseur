package com.example.superviseur.classe;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Delivery {
    private final StringProperty statut, date_delivered, time_between;
    private Package aPackage;
    private Robot robot;


    /**
     * Statut = "a livrer" || "en cours de livraison" || "livre"
     *
     * @param statut
     * @param aPackage
     * @param robot
     * @param date_delivered
     */
    public Delivery(String statut, Package aPackage, Robot robot, String date_delivered, String time_between) {
        this.statut = new SimpleStringProperty(this, "statut", statut);
        this.aPackage = aPackage;
        this.robot = robot ==  null? new Robot() : robot;
        this.date_delivered = new SimpleStringProperty(this, "date_delivered", date_delivered);
        this.time_between = new SimpleStringProperty(this, "time_between", time_between);
    }

    public String getTime_between() {
        return time_between.get();
    }

    public void setTime_between(String time_between) {
        this.time_between.set(time_between);
    }

    public StringProperty time_betweenProperty() {
        return time_between;
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

    public String getDate_delivered() {
        return date_delivered.get();
    }

    public void setDate_delivered(String date_delivered) {
        this.date_delivered.set(date_delivered);
    }

    public StringProperty date_deliveredProperty() {
        return date_delivered;
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
