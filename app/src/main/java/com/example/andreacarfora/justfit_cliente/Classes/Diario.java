package com.example.andreacarfora.justfit_cliente.Classes;

import java.io.Serializable;

/**
 * Created by Andrea Carfora on 22/12/2017.
 */

public class Diario implements Serializable {

    private String giorno;
    private Pasti pasti;
    private String urlArchivio;


    public Diario(String giorno, Pasti pasti, String urlArchivio) {
        this.giorno = giorno;
        this.pasti = pasti;
        this.urlArchivio = urlArchivio;
    }


    public Diario(){
        this.giorno = "0";
        this.pasti = new Pasti();
        this.urlArchivio ="";
    }


    public String getGiorno() {
        return giorno;
    }

    public void setGiorno(String giorno) {
        this.giorno = giorno;
    }

    public Pasti getPasti() {
        return pasti;
    }

    public void setPasti(Pasti pasti) {
        this.pasti = pasti;
    }

    public String getUrlArchivio() {
        return urlArchivio;
    }

    public void setUrlArchivio(String urlArchivio) {
        this.urlArchivio = urlArchivio;
    }
}
