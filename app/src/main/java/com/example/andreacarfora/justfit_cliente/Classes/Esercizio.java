package com.example.andreacarfora.justfit_cliente.Classes;

/**
 * Created by Andrea Carfora on 22/12/2017.
 */

public class Esercizio {

    private String id;
    private String nome;
    private String ripetizioni;
    private int serie;
    private int giornoAllenamento;
    private String attrezzi;
    private String descrizione;
    private String muscoli;
    private String muscoliSecondari;

    public Esercizio(String id, String nome, String ripetizioni, int serie, int giornoAllenamento, String attrezzi, String descrizione, String muscoli, String muscoliSecondari) {
        this.id = id;
        this.nome = nome;
        this.ripetizioni = ripetizioni;
        this.serie = serie;
        this.giornoAllenamento = giornoAllenamento;
        this.attrezzi = attrezzi;
        this.descrizione = descrizione;
        this.muscoli = muscoli;
        this.muscoliSecondari = muscoliSecondari;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRipetizioni() {
        return ripetizioni;
    }

    public void setRipetizioni(String ripetizioni) {
        this.ripetizioni = ripetizioni;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public int getGiornoAllenamento() {
        return giornoAllenamento;
    }

    public void setGiornoAllenamento(int giornoAllenamento) {
        this.giornoAllenamento = giornoAllenamento;
    }

    public String getAttrezzi() {
        return attrezzi;
    }

    public void setAttrezzi(String attrezzi) {
        this.attrezzi = attrezzi;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getMuscoli() {
        return muscoli;
    }

    public void setMuscoli(String muscoli) {
        this.muscoli = muscoli;
    }

    public String getMuscoliSecondari() {
        return muscoliSecondari;
    }

    public void setMuscoliSecondari(String muscoliSecondari) {
        this.muscoliSecondari = muscoliSecondari;
    }
}
