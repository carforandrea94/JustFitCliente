package com.example.andreacarfora.justfit_cliente.Classes;

import java.util.ArrayList;

/**
 * Created by Andrea Carfora on 22/12/2017.
 */

public class Scheda {

    private String uidScheda;
    private String dataInizio;
    private String dataFine;
    private String uidPersonalTrainer;
    private String uidCliente;
    private int numSettimane;
    private ArrayList<GiornoAllenameto> giorniAllenamento;


    public Scheda(String dataInizio, String dataFine, String uidPersonalTrainer, String uidCliente, ArrayList<GiornoAllenameto> giorniAllenamento, int numSettimane) {
        this.uidScheda = "";
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.uidCliente=uidCliente;
        this.uidPersonalTrainer = uidPersonalTrainer;
        this.giorniAllenamento = giorniAllenamento;
        this.numSettimane = numSettimane;

    }

    public Scheda(){
        this.uidScheda = "";
        this.dataInizio = "";
        this.dataFine = "";
        this.uidCliente="";
        this.uidPersonalTrainer ="";
        this.numSettimane = 0;
        this.giorniAllenamento = new ArrayList<GiornoAllenameto>();
    }

    public String getUidScheda() {
        return uidScheda;
    }

    public void setUidScheda(String uidScheda) {
        this.uidScheda = uidScheda;
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }

    public String getDataFine() {
        return dataFine;
    }

    public void setDataFine(String dataFine) {
        this.dataFine = dataFine;
    }

    public String getUidPersonalTrainer() {
        return uidPersonalTrainer;
    }

    public void setUidPersonalTrainer(String uidPersonalTrainer) {
        this.uidPersonalTrainer = uidPersonalTrainer;
    }

    public String getUidCliente() {
        return uidCliente;
    }

    public void setUidCliente(String uidCliente) {
        this.uidCliente = uidCliente;
    }

    public ArrayList<GiornoAllenameto> getGiorniAllenamento() {
        return giorniAllenamento;
    }

    public void setGiorniAllenamento(ArrayList<GiornoAllenameto> giorniAllenamento) {
        this.giorniAllenamento = giorniAllenamento;
    }
    public int getNumSettimane() {
        return numSettimane;
    }

    public void setNumSettimane(int numSettimane) {
        this.numSettimane = numSettimane;
    }

}
