package com.example.andreacarfora.justfit_cliente.Classes;

import java.util.ArrayList;

public class Progressi {


    private String uidCliente;
    private int altezza;
    private TipoMisura peso;
    private TipoMisura bodyFat;
    private ArrayList<TipoMisura> circonferenze;
    private ArrayList<TipoMisura> plicometrie;


    public Progressi(String uidCliente, int altezza, TipoMisura peso, TipoMisura bodyFat, ArrayList<TipoMisura> circonferenze, ArrayList<TipoMisura> plicometrie) {
        this.uidCliente = uidCliente;
        this.altezza = altezza;
        this.peso = peso;
        this.bodyFat = bodyFat;
        this.circonferenze = circonferenze;
        this.plicometrie = plicometrie;
    }


    public String getUidCliente() {
        return uidCliente;
    }

    public void setUidCliente(String uidCliente) {
        this.uidCliente = uidCliente;
    }

    public int getAltezza() {
        return altezza;
    }

    public void setAltezza(int altezza) {
        this.altezza = altezza;
    }

    public Misura getLastPeso() {
        int size = peso.getMisure().size();
        return peso.getMisure().get(size-1);
    }


    public void setPeso(TipoMisura peso) {
        this.peso = peso;
    }

    public TipoMisura getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(TipoMisura bodyFat) {
        this.bodyFat = bodyFat;
    }

    public ArrayList<TipoMisura> getCirconferenze() {
        return circonferenze;
    }

    public void setCirconferenze(ArrayList<TipoMisura> circonferenze) {
        this.circonferenze = circonferenze;
    }

    public ArrayList<TipoMisura> getPlicometrie() {
        return plicometrie;
    }

    public void setPlicometrie(ArrayList<TipoMisura> plicometrie) {
        this.plicometrie = plicometrie;
    }
}
