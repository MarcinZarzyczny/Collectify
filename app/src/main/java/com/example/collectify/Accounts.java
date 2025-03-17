package com.example.collectify;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.Objects;

public class Accounts extends ViewModel {
    ArrayList<Account> accounts = new ArrayList<>();

    public ArrayList<Account> getLogin() {
        return this.accounts;
    }

    public void setAccounts(Account account) {
        // Użyj konstruktora ArrayList do skopiowania
        this.accounts.add(account);

        //Log.d(TAG, "--------------------------");
        //Log.d(TAG, "Odczytanie tablicy, długosć araylist " + accounts.size());
        //for (int i = 0; i < this.accounts.size(); i++) {
        //Log.d(TAG, "elem login " + i + " - "  + this.accounts.get(i).getLogin());
        // Log.d(TAG, "elem password " + i + " - "  + this.accounts.get(i).getPassword());

        // System.out.println(this.accounts.get(i));
        // }
    }

    public boolean setCheck(String login, String password) {
        boolean accountFind = false;

        for (int i = 0; i < this.accounts.size(); i++) {
            if (this.accounts.get(i).getLogin().equals(login)) {
                //Log.d(TAG, this.accounts.get(i).getLogin()  + " = "  + login);

                if (this.accounts.get(i).getPassword().equals(password)) {
                    // Log.d(TAG, this.accounts.get(i).getPassword() + " = " + password);

                    accountFind = true;
                } else {
                    System.out.println(this.accounts.get(i).getLogin() + " = " + login);
                    System.out.println(this.accounts.get(i).getPassword() + " != " + password);

                }
            } else {
                System.out.println(this.accounts.get(i).getLogin() + " != " + login);

            }


        }
        return accountFind;
    }

    public boolean loginBusy(String login) {
        boolean loginBusy = false;

        for (int i = 0; i < this.accounts.size(); i++) {
            if (this.accounts.get(i).getLogin().equals(login)) {
                //Log.d(TAG, this.accounts.get(i).getLogin()  + " = "  + login);
                loginBusy = true;

            }/*else{
               // Log.d(TAG,  this.accounts.get(i).getLogin()  + " != "  + login);
            }*/
        }
        return loginBusy;
    }

    public boolean addNewBox(String login, Object item) {
        boolean boxAdded = false;
        for (int i = 0; i < this.accounts.size(); i++) {
            if (this.accounts.get(i).getLogin().equals(login)) {
                accounts.get(i).addNewBox(item);
                Log.d(TAG, "--------------------------- ");
                Log.d(TAG, "Element dodany: " + accounts.get(i).boxListSize());
                boxAdded = true;
            }
        }
        return boxAdded;
    }
    public void createBox(String login, ViewGroup myLayout, Context context){
        for (int i = 0; i < this.accounts.size(); i++) {
            if (this.accounts.get(i).getLogin().equals(login)) {
                ArrayList<Object> boxList = this.accounts.get(i).getBoxList();
                for (int index = 0; index < boxList.size(); index++) {
                    if (boxList.get(index) instanceof Box) {
                        ((Box) boxList.get(index)).createBox(myLayout, context);
                    }//if (boxList.get(index) instanceof ) {
                        //((Box) boxList.get(index)).createBox(myLayout, nazwa, ilosc, context);
                    //}
                }
            }
        }
    }

}