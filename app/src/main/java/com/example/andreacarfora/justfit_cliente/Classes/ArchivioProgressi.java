package com.example.andreacarfora.justfit_cliente.Classes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Andrea Carfora on 18/02/2018.
 */

public class ArchivioProgressi implements Serializable{

    private ArrayList<String> urlPhoto;


    public ArchivioProgressi() {
        this.urlPhoto = new ArrayList<>();
    }

    public ArrayList<String> getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(ArrayList<String> urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public int getCountUrls(){
        return urlPhoto.size();
    }
}
