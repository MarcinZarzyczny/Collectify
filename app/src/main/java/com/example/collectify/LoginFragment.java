package com.example.collectify;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
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
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.util.Log;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Objects;

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
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    private ItemFragmentName viewModel;
    Accounts accounts;

    private boolean rememberMy = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ItemFragmentName.class);
        accounts = new ViewModelProvider(requireActivity()).get(Accounts.class);

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
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch rememberMySwich = requireActivity().findViewById(R.id.rememberMySwich);
        rememberMySwich.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(requireActivity().getApplicationContext(), "Włączono", Toast.LENGTH_SHORT).show();
                    rememberMy = true;
                } else {
                    Toast.makeText(requireActivity().getApplicationContext(), "Wyłączono", Toast.LENGTH_SHORT).show();
                    rememberMy = false;
                }
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

        // Otwieranie fragmentu z formularzem dodawania nowego konta.
        ImageButton openAddNewAccount = requireActivity().findViewById(R.id.openAddnewAccont);
        openAddNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d( TAG,"Click działa poprawnie");
                sendData("Nowe dane");
                Log.d(TAG, "LF- Dane wysłane: " + v);
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, AddNewAccount.class, null);
                transaction.addToBackStack(null); // Dodaj do stosu, jeśli chcesz umożliwić powrót
                transaction.commit();


            }
        });

        // Logowanie do aplikacji
        TextView liginInformation = requireActivity().findViewById(R.id.loginIformation);
        Button openBoxList = requireActivity().findViewById(R.id.loginButoon);
        openBoxList.setBackgroundColor(color);
        openBoxList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sprawdzanie czy logi i chasło jest w bazie kont.
                // jeśli tak zaloguj do programu
                if(accounts.setCheck(cleanHtml(login.getText().toString()), cleanHtml(password.getText().toString()), rememberMy)) {
                    login.setText("");
                    password.setText("");
                    rememberMy = false;
                    FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, BoxList.class, null);
                    transaction.addToBackStack(null); // Dodaj do stosu, jeśli chcesz umożliwić powrót
                    transaction.commit();
                    liginInformation.setText(R.string.wpisz_login_oraz_haslo_aby_sie_zalogowac);
                    liginInformation.setTextColor(Color.parseColor("#830808"));
                //W przeciwnym wypadku wyswietl komunikat o błędzie
                }else{
                    liginInformation.setText(R.string.login_lub_haslo);
                    liginInformation.setTextColor(Color.parseColor("#830808"));

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