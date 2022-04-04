package com.example.superviseur.classe;

import java.util.Date;

public class Delivery {
    private String statut;
    private Package aPackage;
    private Robot robot;
    private Date date;

    public Delivery() {
    }

    /**
     * Statut = "à livrer" || "en cours de livraison" || "livré"
     *
     * @param statut
     * @param aPackage
     * @param robot
     * @param date
     */
    public Delivery(String statut, Package aPackage, Robot robot, Date date) {
        this.statut = statut;
        this.aPackage = aPackage;
        this.robot = robot;
        this.date = date;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
