package com.amonteiro.a24_04_renault_android;

import com.amonteiro.a24_04_renault_android.exo.ExoKotlinKt;
import com.amonteiro.a24_04_renault_android.model.CarBean;

public class Temp {

    public static void main(String[] args) {
        CarBean c = new CarBean();
    }

    private String marque;
    private String model;

    public Temp(String marque, String model) {
        this.marque = marque;
        this.model = model;
    }


    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
