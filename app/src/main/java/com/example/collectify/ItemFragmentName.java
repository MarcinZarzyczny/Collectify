package com.example.collectify;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ItemFragmentName extends ViewModel {
    private final MutableLiveData<String> daneStartowa = new MutableLiveData<>("Dane startowe");
    private String activFragment = "AddNewAccount";


    public LiveData<String> getSelectedData() {
        return daneStartowa;
    }

    public void select(String value) {
        daneStartowa.setValue(value);
        Log.d(TAG, "IFN- Otrzymane dane: " + daneStartowa);
    }
    public String getActivFragment(){
        return this.activFragment;
    }
    public void setActivFragment (String fragment){
        this.activFragment = fragment;
    }
}