package com.example.collectify;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModel;

import java.net.CookieHandler;
import java.util.ArrayList;

public class Account extends ViewModel {
    private String login;
    private String password;
    private boolean doNotLogOut = false;
    public ArrayList<Object> boxList = new ArrayList<>();


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

    public int boxListSize(){
        return this.boxList.size();
    }
    public void addNewBox(Object item){
        this.boxList.add(item);
    }
    public ArrayList<Object>getBoxList() {
        return this.boxList;
    }
}
