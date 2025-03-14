package com.example.collectify;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.ViewModel;

public class Account extends ViewModel {
    private String login;
    private String password;
    private boolean doNotLogOut = false;

    // Konstruktor
    public Account(String login, String pasword) {
        this.login = login;
        this.password = pasword;
        //Log.d( TAG,"LF- konto utworzone login: " + this.login + ", haslo: " + this.password);

    }

    // Gettery i Settery
    public String getLogin() {
        return login;
    }

    public void setLogin(String name) {
        this.login = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public boolean getDoNotLogOut() {
        return doNotLogOut;
    }

    public void setDoNotLogOut(boolean doNotLogOut) {
        this.doNotLogOut = doNotLogOut;
    }


}
