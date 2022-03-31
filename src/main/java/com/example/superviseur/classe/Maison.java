package com.example.superviseur.classe;

public class Maison {

    private String identifiant;
    private int numero;
    private double[] position;

    public Maison(String identifiant, int numero, double[] position) {
        this.identifiant = identifiant;
        this.numero = numero;
        this.position = position;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = position;
    }
}
