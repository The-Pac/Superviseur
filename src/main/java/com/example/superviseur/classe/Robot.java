package com.example.superviseur.classe;

public class Robot {
    private String identifiant, statut;

    public Robot() {
    }

    public Robot(String identifiant, String statut) {
        this.identifiant = identifiant;
        this.statut = statut;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

}
