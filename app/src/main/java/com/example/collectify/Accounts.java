package com.example.collectify;

import static android.content.ContentValues.TAG;

import android.util.Log;

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
            if(this.accounts.get(i).getLogin().equals(login)){
                //Log.d(TAG, this.accounts.get(i).getLogin()  + " = "  + login);

                if(this.accounts.get(i).getPassword().equals(password)){
                   // Log.d(TAG, this.accounts.get(i).getPassword() + " = " + password);

                    accountFind = true;
                }else{
                    System.out.println(this.accounts.get(i).getLogin()  + " = "  + login);
                    System.out.println(this.accounts.get(i).getPassword() + " != " + password);

                }
            }else{
                System.out.println(this.accounts.get(i).getLogin()  + " != "  + login);

            }



        }
        return accountFind;
    }
    public boolean loginBusy(String login) {
        boolean loginBusy = false;

        for (int i = 0; i < this.accounts.size(); i++) {
            if(this.accounts.get(i).getLogin().equals(login)){
                //Log.d(TAG, this.accounts.get(i).getLogin()  + " = "  + login);
                loginBusy = true;

            }/*else{
               // Log.d(TAG,  this.accounts.get(i).getLogin()  + " != "  + login);
            }*/
        }
        return loginBusy;
    }

}