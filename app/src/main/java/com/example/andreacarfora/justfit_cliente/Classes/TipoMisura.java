package com.example.andreacarfora.justfit_cliente.Classes;

import java.util.ArrayList;

public class TipoMisura {

    private String nomeMisura;
    private ArrayList<Misura> misure;

    public TipoMisura(String nomeMisura, ArrayList<Misura> misure) {
        this.nomeMisura = nomeMisura;
        this.misure = misure;
    }

    public String getNomeMisura() {
        return nomeMisura;
    }

    public void setNomeMisura(String nomeMisura) {
        this.nomeMisura = nomeMisura;
    }

    public ArrayList<Misura> getMisure() {
        return misure;
    }

    public void setMisure(ArrayList<Misura> misure) {
        this.misure = misure;
    }
}
