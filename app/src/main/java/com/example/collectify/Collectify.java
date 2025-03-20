package com.example.collectify;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.HashMap;


public class Collectify extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);




        setContentView(R.layout.activity_collectify);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        Accounts accounts = new ViewModelProvider(this).get(Accounts.class);
        Account account = new Account("login1", "polpol1U#", false);
        accounts.setAccounts(account);


        // Użyj getAccounts() do uzyskania listy kont
        for (int i = 0; i < accounts.getAccounts().size(); i++)
            if (accounts.getAccounts().get(i).getDoNotLogOut()) {
                // Utwórz instancję FragmentManager
                FragmentManager fragmentManager = getSupportFragmentManager();
                // Rozpocznij transakcję
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                // Zastąp kontener fragmentu nowym fragmentem
                fragmentTransaction.replace(R.id.fragment_container, BoxList.class, null);
                // Dodaj transakcję do stosu (opcjonalnie)
                fragmentTransaction.addToBackStack(null);
                // Zatwierdź transakcję
                fragmentTransaction.commit();
                break;
            } else {
                // Utwórz instancję FragmentManager
                FragmentManager fragmentManager = getSupportFragmentManager();
                // Rozpocznij transakcję
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                // Zastąp kontener fragmentu nowym fragmentem
                fragmentTransaction.replace(R.id.fragment_container, LoginFragment.class, null);
                // Dodaj transakcję do stosu (opcjonalnie)
                // Zatwierdź transakcję
                fragmentTransaction.commit();
                break;
            }
    }

}
