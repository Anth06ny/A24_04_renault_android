package com.amonteiro.a24_04_renault_android;

import com.amonteiro.a24_04_renault_android.exo.ExoKotlinKt;
import com.amonteiro.a24_04_renault_android.model.CarBean;

public class Temp {

    private String marque;
    private String model;



    public static void main(String[] args) {

        double res = ExoKotlinKt.boulangerie(0,5,0);

        System.out.println(res);

        CarBean car = new CarBean("","");
        CarBean car2 = new CarBean();

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
