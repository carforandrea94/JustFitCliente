package com.example.andreacarfora.justfit_cliente.Classes;

import java.util.GregorianCalendar;

public class Misura {

    private GregorianCalendar dataMisura;
    private double misura;

    public Misura(double misura) {
        this.misura = misura;
    }


    public GregorianCalendar getDataMisura() {
        return dataMisura;
    }

    public void setDataMisura(GregorianCalendar dataMisura) {
        this.dataMisura = dataMisura;
    }

    public double getMisura() {
        return misura;
    }

    public void setMisura(float misura) {
        this.misura = misura;
    }
}
