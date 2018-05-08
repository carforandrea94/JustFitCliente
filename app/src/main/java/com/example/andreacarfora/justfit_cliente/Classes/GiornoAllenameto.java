package com.example.andreacarfora.justfit_cliente.Classes;

import java.util.ArrayList;

/**
 * Created by Andrea Carfora on 22/12/2017.
 */

public class GiornoAllenameto {


    private String uid;
    private int giorno;
    private String descrizione; //esempio: Gambe - Petto
    private ArrayList<Esercizio> esercizi;


    public GiornoAllenameto(String uid, int giorno, String descrizione, ArrayList<Esercizio> esercizi) {
        this.uid = uid;
        this.giorno = giorno;
        this.descrizione = descrizione;
        this.esercizi = esercizi;
    }

    public ArrayList<Esercizio> getEsercizi() {
        return esercizi;
    }

    public void setEsercizi(ArrayList<Esercizio> esercizi) {
        this.esercizi = esercizi;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getGiorno() {
        return giorno;
    }

    public void setGiorno(int giorno) {
        this.giorno = giorno;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
