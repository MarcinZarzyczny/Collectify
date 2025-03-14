package com.example.collectify;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.LiveData;


public class Collectify extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ItemFragmentName itemFragmentName = new ViewModelProvider(this).get(ItemFragmentName.class);
        Accounts accounts = new ViewModelProvider(this).get(Accounts.class);

        //Log.d("MainActivity", "Dane startowe: " +  itemFragmentName.getSelectedData());

        setContentView(R.layout.activity_collectify);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });


        itemFragmentName = new ViewModelProvider(this).get(ItemFragmentName.class);


        itemFragmentName.getSelectedData().observe(this, data -> {
            Log.d("MainActivity", "MA- Otrzymane dane: " + data);
        });
        //Tymczasowe konto
        Account account = new Account("login1", "polpol1U#");
        accounts.setAccounts(account);
        // Utwórz instancję FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Rozpocznij transakcję
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // Zastąp kontener fragmentu nowym fragmentem
        fragmentTransaction.replace(R.id.fragment_container, CreateNewBox.class, null);
        // Dodaj transakcję do stosu (opcjonalnie)
        fragmentTransaction.addToBackStack(null);
        // Zatwierdź transakcję
        fragmentTransaction.commit();


    }

}
