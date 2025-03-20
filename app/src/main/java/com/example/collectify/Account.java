package com.example.collectify;
import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.lifecycle.ViewModel;
import java.util.ArrayList;

public class Account extends ViewModel {
    private String login;
    private String password;
    private boolean doNotLogOut = false;
    public ArrayList<Object> boxList = new ArrayList<>();


    // Konstruktor
    public Account(String login, String pasword, Boolean doNotLogOut) {
        this.login = login;
        this.password = pasword;
        this.doNotLogOut = doNotLogOut;

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
        if (doNotLogOut){
            this.doNotLogOut = doNotLogOut;
            Log.d("TAG", "nie wylogować ustawione na true");

        }else{
            this.doNotLogOut = false;
            Log.d("TAG", "Nie wylogować ustawione na false");
        }
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


    public void createBox(LinearLayout myLayout, Context context) {
        for (int index = 0; index < this.boxList.size(); index++) {
            if (this.boxList.get(index) instanceof Box) {
                ((Box) this.boxList.get(index)).createBox(myLayout, context);
            }
        }
    }
}
