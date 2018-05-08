package com.example.andreacarfora.justfit_cliente.Classes;

/**
 * Created by Andrea Carfora on 22/12/2017.
 */

public class PersonalTrainer {

    private String id;
    private String nomeEcognome;
    private String dataDiNascita;
    private String email;
    private String numeroTelefonico;
    private String sesso;
    private String urlFotoProfilo;

    public PersonalTrainer(String nomeEcognome, String dataDiNascita, String email, String numeroTelefonico, String sesso, String urlFotoProfilo) {
        this.nomeEcognome = nomeEcognome;
        this.dataDiNascita = dataDiNascita;
        this.email = email;
        this.numeroTelefonico = numeroTelefonico;
        this.sesso = sesso;
        this.urlFotoProfilo = urlFotoProfilo;
    }

    public PersonalTrainer() {
        this.id ="";
        this.nomeEcognome = "";
        this.dataDiNascita = "";
        this.email = email;
        this.numeroTelefonico = "";
        this.sesso = sesso;
        this.urlFotoProfilo ="";
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeEcognome() {
        return nomeEcognome;
    }

    public void setNomeEcognome(String nomeEcognome) {
        this.nomeEcognome = nomeEcognome;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getUrlFotoProfilo() {
        return urlFotoProfilo;
    }

    public void setUrlFotoProfilo(String urlFotoProfilo) {
        this.urlFotoProfilo = urlFotoProfilo;
    }
}
