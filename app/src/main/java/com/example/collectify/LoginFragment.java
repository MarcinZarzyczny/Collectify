package com.example.collectify;

import static android.content.ContentValues.TAG;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.util.Log;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
        //Button burtonButton = getActivity().findViewById(R.id.button2);
    }

    private ItemFragmentName viewModel;
    private Account account;
    Accounts accounts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ItemFragmentName.class);
        accounts = new ViewModelProvider(requireActivity()).get(Accounts.class);

        //Create a new Account
       //Account account = new Account("Jan", "gfdgg");


        View view = inflater.inflate(R.layout.fragment_login, container, false);
        //sendData("Mój tekst");
        Log.d(TAG, "LF- Panel logowania wczytany");


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        viewModel.getSelectedData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String name) {
                Log.d(TAG, " LF- Dane otrzymane: " + name);
            }
        });

        //stylowanie pul tekstowych
        GradientDrawable border = new GradientDrawable();
        border.setColor(Color.parseColor("#F1E4FF")); // Kolor tła
        border.setStroke(4, Color.parseColor("#36115D")); // Grubość i kolor obramowania (czerwony)
        border.setCornerRadius(25f); // Ustawia promień zaokrąglenia (można pominąć dla kształtu OVAL)

        TextView login = requireActivity().findViewById(R.id.login);
        login.setBackground(border);
        login.setPadding(15, login.getPaddingTop(), login.getPaddingRight(), login.getPaddingBottom());
        TextView password = requireActivity().findViewById(R.id.editTextPassword);
        password.setBackground(border);
        password.setPadding(15, login.getPaddingTop(), login.getPaddingRight(), login.getPaddingBottom());
        int color = ContextCompat.getColor(requireActivity(), R.color.collectifyCoolor);


        ImageButton openAddNewAccount = requireActivity().findViewById(R.id.openAddnewAccont);
        openAddNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d( TAG,"Click działa poprawnie");
                sendData("Nowe dane");
                Log.d(TAG, "LF- Dane wysłane: " + v);


                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, AddNewAccount.class, null);
                ; // R.id.fragment_container to ID kontenera w głównym layout
                transaction.addToBackStack(null); // Dodaj do stosu, jeśli chcesz umożliwić powrót
                transaction.commit();


            }
        });

        Button openBoxList = requireActivity().findViewById(R.id.openBoxItems);
        openBoxList.setBackgroundColor(color);

        openBoxList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log.d( TAG,"Click działa poprawnie");
                sendData("Nowe dane");
                Log.d(TAG, "LF- Dane wysłane: " + v);
                // Sprawdzanie czy logi ichasło jest w bazie kont.
                // jeśli tak zaloguj do programu
                if(accounts.setCheck(cleanHtml(login.getText().toString()), cleanHtml(password.getText().toString()))) {
                    login.setText("");
                    password.setText("");
                    FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, BoxList.class, null);
                    ; // R.id.fragment_container to ID kontenera w głównym layout
                    transaction.addToBackStack(null); // Dodaj do stosu, jeśli chcesz umożliwić powrót
                    transaction.commit();
                //W przeciwnym wypadku wyswietl komunikat o błędzie
                }else{
                    Log.d(TAG, "Login lub hasło niepoprawne.");

                }

            }
        });
    }

    public void sendData(String data) {
        viewModel.select(data); // Ustawienie danych w ViewModel
    }
    public static String cleanHtml(String html) {
        // Parsowanie HTML i oczyszczanie
        Document doc = Jsoup.parse(html);
        return doc.body().text(); // Zwraca tylko tekst bez znaczników HTML
    }


}