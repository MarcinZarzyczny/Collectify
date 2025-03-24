package com.example.collectify;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BoxList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BoxList extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public Box selectedBox = null;
    ActiveElements activeElements;



    public BoxList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BoxList.
     */
    // TODO: Rename and change types and number of parameters
    public static BoxList newInstance(String param1, String param2) {
        BoxList fragment = new BoxList();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_box_list, container, false);
    }
    @Override
    public void onResume() {
        super.onResume();

         activeElements = new ViewModelProvider(requireActivity()).get(ActiveElements.class);


        // Wstawianie nazwy zalogowanego urzytkownika do pola textView.
        TextView loginUser = requireActivity().findViewById(R.id.loginUser);
        loginUser.setText(Accounts.loginAccount.getLogin());

        // Wstawianie listy zbioró do leyautu.
        LinearLayout myLayout = requireActivity().findViewById(R.id.box2);
        // Upewnij się, że myLayout nie jest null
        if (myLayout != null) {
            Accounts.loginAccount.createBox(myLayout, requireContext(), this);
        }
        // Obsługa przycisko dodawania nowego zbioru.
        ImageButton addNewBox = requireActivity().findViewById(R.id.addNewBox);
        addNewBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = Objects.requireNonNull(requireActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, CreateCollection.class, null);
                transaction.addToBackStack(null); // Dodaj do stosu, jeśli chcesz umożliwić powrót
                transaction.commit();

            }
        });

        // Obsługa przycisku ustawień.
        ImageButton openSetings = requireActivity().findViewById(R.id.openSettings);
        openSetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = Objects.requireNonNull(requireActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, Setting.class, null);
                transaction.addToBackStack(null); // Dodaj do stosu, jeśli chcesz umożliwić powrót
                transaction.commit();
            }
        });



    }
    public void onBoxClicked(Box box) {
        selectedBox = box;
        activeElements.setActiveBox(selectedBox); // Przypisanie wskaźnika do zmiennej
        Log.d("TAG", "Wybrano pudełko: " + selectedBox.getContainerName());
        FragmentTransaction transaction = Objects.requireNonNull(requireActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, CollectionFragment.class, null);
        transaction.addToBackStack(null); // Dodaj do stosu, jeśli chcesz umożliwić powrót
        transaction.commit();
    }

}