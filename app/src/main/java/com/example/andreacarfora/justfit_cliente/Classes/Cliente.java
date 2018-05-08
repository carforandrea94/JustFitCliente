package com.example.andreacarfora.justfit_cliente.Classes;

import java.io.Serializable;

/**
 * Created by Andrea Carfora on 22/12/2017.
 */

public class Cliente implements Serializable{

    private String nome;
    private String cognome;
    private String password;
    private String dataDiNascita;
    private String numeroTelefonico;
    private String email;
    private String sesso;
    private String uidPersonalTrainer;
    private String urlFotoProfilo;


    public Cliente( String nome, String cognome, String password, String dataDiNascita, String numeroTelefonico, String email, String sesso, String uidPersonalTrainer, String urlFotoProfilo) {
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
        this.dataDiNascita = dataDiNascita;
        this.numeroTelefonico = numeroTelefonico;
        this.email = email;
        this.sesso = sesso;
        this.uidPersonalTrainer = uidPersonalTrainer;
        this.urlFotoProfilo = urlFotoProfilo;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getUidPersonalTrainer() {
        return uidPersonalTrainer;
    }

    public void setUidPersonalTrainer(String uidPersonalTrainer) {
        this.uidPersonalTrainer = uidPersonalTrainer;
    }

    public void setUrlFotoProfilo(String urlFotoProfilo) {
        this.urlFotoProfilo = urlFotoProfilo;
    }
}
