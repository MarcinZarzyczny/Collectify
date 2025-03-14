package com.example.collectify;

import static android.content.ContentValues.TAG;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import org.jsoup.Jsoup;
import java.util.Objects;


import androidx.lifecycle.ViewModelProvider;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddNewAccount#newInstance} factory method to
 * create an instance of this fragment.
 * @noinspection ALL
 */
public class AddNewAccount extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddNewAccount() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add_new_account.
     */
    // TODO: Rename and change types and number of parameters
    public static AddNewAccount newInstance(String param1, String param2) {
        AddNewAccount fragment = new AddNewAccount();
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
        super.onCreate(savedInstanceState); // Upewnij się, że to jest poprawne
        setRetainInstance(true);
    }

    private ItemFragmentName viewModel;
    private static Accounts accounts;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider(requireActivity()).get(ItemFragmentName.class);
        accounts = new ViewModelProvider(requireActivity()).get(Accounts.class);

        return inflater.inflate(R.layout.fragment_add_new_account, container, false);
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

        //Utworzenie dodatkowych stylów dla pul tekstowych.
        GradientDrawable border = new GradientDrawable();
        border.setColor(Color.parseColor("#F1E4FF")); // Kolor tła
        border.setStroke(4, Color.parseColor("#36115D")); // Grubość i kolor obramowania
        border.setCornerRadius(25f); // Ustawia promień zaokrąglenia (można pominąć dla kształtu OVAL)
        // Uchwyt pola login
        TextView creatingLogin = requireActivity().findViewById(R.id.creatingLogin);
        //Ustawienie dodatkowych stylów dla elementu ceratingLogin.
        creatingLogin.setBackground(border);
        creatingLogin.setPadding(15, creatingLogin.getPaddingTop(), creatingLogin.getPaddingRight(), creatingLogin.getPaddingBottom());
        //Ustawianie początkowej wartości dla elementu creatingLogin.
        creatingLogin.setText(R.string.login);
        //Ustawianie początkowej wartości dla elementu loginCorrectMessage.
        TextView loginCorrectMessage = requireActivity().findViewById(R.id.loginCorrectMessage);
        loginCorrectMessage.setText(R.string.info_login);


        //Ustawienie FocusChangeListenera dla pola creatoingLogin.
        creatingLogin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            //Jeśli element uzyska fokus sprawdź wartość elementu
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //Jeśli wartość pola wynosi Login wyczyść pole
                    String log = "Login";
                    if (creatingLogin.getText().toString().equals(log)) {
                        creatingLogin.setText("");
                    }
                    //Jeśli element straci fokus sprawdź zawartość elementu
                } else {
                    //Jeśli element jest pusty lub wykryto biały znak ustaw dla niego wartość początkowa i ustaw komunikat poczatkowy.
                    if (creatingLogin.getText().toString().isEmpty()) {
                        creatingLogin.setText(R.string.login);
                        creatingLogin.setTextColor(Color.parseColor("#36115D"));
                        loginCorrectMessage.setText(R.string.info_login);
                        loginCorrectMessage.setTextColor(Color.parseColor("#36115D"));
                    }
                }
            }
        });

        // Ustawienie ChangedListener na pole loginu
        creatingLogin.addTextChangedListener(new TextWatcher() {
            String creatingLogi2;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Możesz użyć tej metody, jeśli potrzebujesz
            }
            // Sprawdzanie poprawności loginu
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Jesli login nie spełnia warunkó walidacji
                if (!isValid(creatingLogin.getText().toString())) {
                    // Wysweitl komunikat o błędzie
                    loginCorrectMessage.setTextColor(Color.parseColor("#830808"));
                    loginCorrectMessage.setText(R.string.info_login_six);
                    // Jeśli ogin większy niż 5 znaków i krótszy niż 15 znaków i spełnia warunki validacji
                } else if (creatingLogin.getText().toString().length() >= 5 && creatingLogin.getText().toString().length() <= 15) {
                    // Jesli loginu nie ma w bazie kont
                    if (!loginUnavailable(creatingLogin.getText().toString())) {
                        // Wyśweitl komunikat o poprawności loginu
                        loginCorrectMessage.setTextColor(Color.parseColor("#358A3B"));
                        loginCorrectMessage.setText(R.string.info_login_two);
                    } else {
                        // Jesli login znajduje się w bazie kont wyświetl komunikat że login niedostępny
                        loginCorrectMessage.setText(R.string.login_busy);
                        loginCorrectMessage.setTextColor(Color.parseColor("#830808"));
                    }
                    // Login dłuższy niż 15 znaków
                } else if (creatingLogin.getText().toString().length() > 15) {
                    // Wyświetl komunikat żę login za długi
                    loginCorrectMessage.setText(R.string.info_login_four);
                    loginCorrectMessage.setTextColor(Color.parseColor("#830808"));
                    // Jesli login za krótki

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // creatingLogin.setText(creatingLogi2);
            }
        });

        //Uchwyt przycisku imformaci
        MaterialButton informationPanelButton = requireActivity().findViewById(R.id.informationPanelButton);
        //Utworzenie dodatkowych stylów dla przyucisku.
        GradientDrawable borderTwo = new GradientDrawable();
        borderTwo.setCornerRadius(100f); // Ustawia promień zaokrąglenia (można pominąć dla kształtu OVAL)
        informationPanelButton.setBackground(borderTwo);
        // Dodanie onClick listenera dla przycisku informacji
        informationPanelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Przejdz do panel informacji
                FragmentTransaction transaction = Objects.requireNonNull(requireActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, InformationPanel.class, null);
                transaction.addToBackStack(null); // Dodaj do stosu, jeśli chcesz umożliwić powrót
                transaction.commit();
            }
        });

        // Uchwyt i głownego pola hasła
        TextView passwordOne = requireActivity().findViewById(R.id.creatingPassword);
        // Ustawienie wyglądu
        passwordOne.setBackground(border);
        passwordOne.setPadding(15, creatingLogin.getPaddingTop(), creatingLogin.getPaddingRight(), creatingLogin.getPaddingBottom());
        // uchwyt pola powturzenia hasła
        TextView passwordTwo = requireActivity().findViewById(R.id.repeatThePassword);
        // Ustawienie wyglądu
        passwordTwo.setBackground(border);
        passwordTwo.setPadding(15, creatingLogin.getPaddingTop(), creatingLogin.getPaddingRight(), creatingLogin.getPaddingBottom());
        // Uchwyt pola komuikatau pola chasłą
        TextView passwordCorrectnessMessage = requireActivity().findViewById(R.id.passwordCorrectnessMessage);

        //Ustawienie ChangedListener na pole hasła
        passwordOne.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Możesz użyć tej metody, jeśli potrzebujesz
            }
            // Sprawdzanie poprawności hasła
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Zresetowanie pola powtużonego hasła
                passwordTwo.setText("");

                // Jesli login nie spełnia warunkó walidacji
                if (!isValidPsssword(passwordOne.getText().toString()) && passwordOne.getText().toString().length() >= 5 && passwordOne.getText().toString().length() <= 15) {
                    if(!passwordOne.getText().toString().matches(".*[A-Z].*")){// co najmniej jedna durza litera
                        // Wyświetl komunikat o błędzie
                        passwordCorrectnessMessage.setTextColor(Color.parseColor("#830808"));
                        passwordCorrectnessMessage.setText(R.string.info_password_five);
                    }else if (!passwordOne.getText().toString().matches(".*[0-9].*")) { // co najmniej jedna cyfra
                        // Wyświetl komunikat o błędzie
                        passwordCorrectnessMessage.setTextColor(Color.parseColor("#830808"));
                        passwordCorrectnessMessage.setText(R.string.info_password_six);
                    }else if (!passwordOne.getText().toString().matches(".*[_.!@#$%^&*()+,?:;].*")) { // co najmniej jedna z wymienionych znaków
                        // Wyświetl komunikat o błędzie
                        passwordCorrectnessMessage.setTextColor(Color.parseColor("#830808"));
                        passwordCorrectnessMessage.setText(R.string.info_password_seven);
                    }else{
                        // Wyświetl komunikat o błędzie
                        passwordCorrectnessMessage.setTextColor(Color.parseColor("#830808"));
                        passwordCorrectnessMessage.setText(R.string.info_password_four);
                    }
                    // Jeśli hasło jest większy niż 5 znaków i krótsze niż 15 znaków i spełnia warunki validacji
                } else if (passwordOne.getText().toString().length() >= 5 && passwordOne.getText().toString().length() <= 15 && isValidPsssword(passwordOne.getText().toString())) {
                   // Wyświetl komunikat o poprawności chasła
                    passwordCorrectnessMessage.setTextColor(Color.parseColor("#358A3B"));
                    passwordCorrectnessMessage.setText(R.string.info_password_two);
                    // Login dłuższy niż 15 znaków
                } else if (passwordOne.getText().toString().length() > 15) {
                    // Wyświetl komunikat żę hasło jest za długi
                    passwordCorrectnessMessage.setText(R.string.info_password_three);
                    passwordCorrectnessMessage.setTextColor(Color.parseColor("#830808"));
                    // Jesli hasło za krótki
                } else if (passwordOne.getText().toString().length() <= 5) {
                    // Wyświetl komunikat żę hasło za krutkie
                    passwordCorrectnessMessage.setText(R.string.info_password_four);
                    passwordCorrectnessMessage.setTextColor(Color.parseColor("#830808"));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                // creatingLogin.setText(creatingLogi2);
            }
        });



        // Uchwyt elementu komunikatu dla pola powturzonego hasła
        TextView passwordMatchMessage = requireActivity().findViewById(R.id.passwordMatchMessage);

        passwordTwo.addTextChangedListener(new TextWatcher() {
            String creatingLogi2;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Możesz użyć tej metody, jeśli potrzebujesz
            }
            // Sprawdzanie poprawności hasła
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Jesli login nie spełnia warunkó walidacji
                if (passwordTwo.getText().toString().equals(passwordOne.getText().toString()) && !passwordTwo.getText().toString().isEmpty()) {
                    passwordMatchMessage.setTextColor(Color.parseColor("#358A3B"));
                    passwordMatchMessage.setText(R.string.info_passwordTwo_one);
                } else{
                    passwordMatchMessage.setText(R.string.info_passwordTwo_two);
                    passwordMatchMessage.setTextColor(Color.parseColor("#830808"));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                // creatingLogin.setText(creatingLogi2);
            }
        });



        // Ustawienie nasłuchu zdarzenia oneClick na przycisku dodawania nowego konta
        MaterialButton addAnAccount = requireActivity().findViewById(R.id.addAnAccount);
        addAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Jesli hsło spełnia wymogi validacji
                if (isValidPsssword(passwordOne.getText().toString())) {
                    //Oczyść hasło z nieporzadanych zanków
                    String password = Jsoup.parse(passwordOne.getText().toString()).text();
                    // Jesli powturzone hasło spełnie wymogi validacji i jest take samoi jak pierwsze
                    if (isValidPsssword(passwordTwo.getText().toString()) && passwordTwo.getText().toString().equals(password)) {
                        // Oczyść powturzone hsło z nieporzadanych znaków
                        String passwordThree = Jsoup.parse(passwordTwo.getText().toString()).text();
                        // Jeśli login spełnia wymogi walidacji i jest nie ma takiego samego w bazie kont
                        if (isValid(creatingLogin.getText().toString()) && !loginUnavailable(creatingLogin.getText().toString())) {
                            // Oczyść login z niepożadanych zanków
                            String creatingLoginTwoo = Jsoup.parse(creatingLogin.getText().toString()).text();
                            // Utwurz nowe konto
                            Account account = new Account(creatingLoginTwoo, password);
                            accounts.setAccounts(account); // Dodaj konto do listy
                            // Przejdź do panelu logowania
                            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container, LoginFragment.class, null);
                            ; // R.id.fragment_container to ID kontenera w głównym layout
                            transaction.addToBackStack(null); // Dodaj do stosu, jeśli chcesz umożliwić powrót
                            transaction.commit();
                        }
                    } else {
                        // Wyswietl komunikat że hasło nie spełnia wymogów walidacji.
                        passwordMatchMessage.setText(R.string.info_error_in_the_second_password);
                        passwordMatchMessage.setTextColor(Color.parseColor("#830808"));

                    }
                }
            }
        });

    }

    //Validacja loginu oras hasłą.
    public static boolean isValid(String text) {
        // Jesli login/hasło spełniakją kryterium długości oraz dowolonych znaków, zwruć true.
        return text.length() >= 5 && text.length() <= 15 && text.matches("[a-zA-Z0-9_.!@#$%^*()+,?:;fd]+");
    }
    public static boolean isValidPsssword(String text) {
        // Jesli hsło spełnia kryterium vlidacji zwruć true, jeśli nie zwruć false
        return text.length() >= 5 && text.length() <= 15 &&
                text.matches(".*[A-Z].*") && // co najmniej jedna duża litera
                text.matches(".*[0-9].*") && // co najmniej jedna cyfra
                text.matches(".*[_.!@#$%^&*()+,?:;].*") && // co najmniej jeden znak specjalny
                text.matches("[a-zA-Z0-9_.!@#$%^&*()+,?:;]+"); // dozwolone znaki

    }
    public static boolean loginUnavailable(String login) {
        // Sprawdzenie czy login jest w bazie kont.
        return  accounts.loginBusy(login);
    }
}