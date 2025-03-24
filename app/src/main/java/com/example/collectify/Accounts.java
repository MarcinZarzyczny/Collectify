package com.example.collectify;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModel;
import java.util.ArrayList;

public class Accounts extends ViewModel {
    ArrayList<Account> accounts = new ArrayList<>();
    static Account loginAccount = null;
    public void setAccounts(Account account) {
        this.accounts.add(account);
    }

    public boolean setCheck(String login, String password, Boolean doNotLogOut) {
        boolean accountFind = false;

        for (int i = 0; i < this.accounts.size(); i++) {
            if (this.accounts.get(i).getLogin().equals(login)) {
                if (this.accounts.get(i).getPassword().equals(password)) {
                    accounts.get(i).setDoNotLogOut(doNotLogOut);
                    loginAccount = accounts.get(i);
                    accountFind = true;
                }
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
    public void createBox(ViewGroup myLayout, Context context){
        for (int i = 0; i < this.accounts.size(); i++) {
            ArrayList<Object> boxList = this.accounts.get(i).getBoxList();
            for (int index = 0; index < boxList.size(); index++) {
                if (boxList.get(index) instanceof Box) {
                    ((Box) boxList.get(index)).createBox(myLayout, context, null);
                }
            }
        }
    }
    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}