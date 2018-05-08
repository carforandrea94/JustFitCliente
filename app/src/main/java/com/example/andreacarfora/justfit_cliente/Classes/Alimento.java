package com.example.andreacarfora.justfit_cliente.Classes;

/**
 * Created by Andrea Carfora on 22/12/2017.
 */

public class Alimento {
    private String id;
    private String nome;
    private double grammi;
    private double carbidrati;
    private double proteine;
    private double grassi;

    public Alimento(String id, String nome, double grammi, double carbidrati, double proteine, double grassi) {
        this.id = id;
        this.nome = nome;
        this.grammi = grammi;
        this.carbidrati = carbidrati;
        this.proteine = proteine;
        this.grassi = grassi;
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

    public double getGrammi() {
        return grammi;
    }

    public void setGrammi(double grammi) {
        this.grammi = grammi;
    }

    public double getCarbidrati() {
        return carbidrati;
    }

    public void setCarbidrati(double carbidrati) {
        this.carbidrati = carbidrati;
    }

    public double getProteine() {
        return proteine;
    }

    public void setProteine(double proteine) {
        this.proteine = proteine;
    }

    public double getGrassi() {
        return grassi;
    }

    public void setGrassi(double grassi) {
        this.grassi = grassi;
    }
}
