package com.example.collectify;

import androidx.lifecycle.ViewModel;

import java.util.Date;

import kotlin.text.UStringsKt;

public class Box extends ViewModel {
    private String name;
    private String opis = null;
    private final Date bocCreateDate = new Date();


    // Konstruktor
    public Box(String name, String opis) {
        this.name = name;
        this.opis = opis;
        //Log.d( TAG,"LF- konto utworzone login: " + this.login + ", haslo: " + this.password);

    }

    // Gettery i Settery
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Object getBocCreateDate(){return this.bocCreateDate;}
    
}
