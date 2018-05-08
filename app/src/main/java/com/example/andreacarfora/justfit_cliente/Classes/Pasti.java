package com.example.andreacarfora.justfit_cliente.Classes;

import java.util.ArrayList;

/**
 * Created by Andrea Carfora on 22/12/2017.
 */

public class Pasti {
    private ArrayList<Alimento> pranzo;
    private ArrayList<Alimento> cena;
    private ArrayList<Alimento> spuntino;
    private ArrayList<Alimento> colazione;

    public Pasti(){
        pranzo = new ArrayList<>();
        cena = new ArrayList<>();
        spuntino = new ArrayList<>();
        colazione = new ArrayList<>();
    }

    public Pasti(ArrayList<Alimento> pranzo, ArrayList<Alimento> cena, ArrayList<Alimento> spuntino, ArrayList<Alimento> colazione) {
        this.pranzo = pranzo;
        this.cena = cena;
        this.spuntino = spuntino;
        this.colazione = colazione;
    }

    public ArrayList<Alimento> getPranzo() {
        return pranzo;
    }

    public void setPranzo(ArrayList<Alimento> pranzo) {
        this.pranzo = pranzo;
    }

    public ArrayList<Alimento> getCena() {
        return cena;
    }

    public void setCena(ArrayList<Alimento> cena) {
        this.cena = cena;
    }

    public ArrayList<Alimento> getSpuntino() {
        return spuntino;
    }

    public void setSpuntino(ArrayList<Alimento> spuntino) {
        this.spuntino = spuntino;
    }

    public ArrayList<Alimento> getColazione() {
        return colazione;
    }

    public void setColazione(ArrayList<Alimento> colazione) {
        this.colazione = colazione;
    }
}
