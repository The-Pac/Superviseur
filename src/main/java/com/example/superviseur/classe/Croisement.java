package com.example.superviseur.classe;

public class Croisement {
    private String identifiant;
    private Double[] position;
    private Maison[] maison;

    public Croisement(String identifiant, Double[] position) {
        this.identifiant = identifiant;
        this.position = position;
    }

    public void get_croisement() {


    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public Double[] getPosition() {
        return position;
    }

    public void setPosition(Double[] position) {
        this.position = position;
    }

    public Maison[] getMaison() {
        return maison;
    }

    public void setMaison(Maison[] maison) {
        this.maison = maison;
    }
}
