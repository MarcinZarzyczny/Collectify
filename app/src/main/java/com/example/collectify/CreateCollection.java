package com.example.collectify;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateCollection#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateCollection extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateCollection() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateColectionBox.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateCollection newInstance(String param1, String param2) {
        CreateCollection fragment = new CreateCollection();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_colection_box, container, false);
    }@Override
    public void onResume() {
        super.onResume();

        // Przycisk utworzenia nowego boxa.
        ImageButton openCreateBox = requireActivity().findViewById(R.id.openCreateBox);
        openCreateBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewAccount AddNewAccount = new AddNewAccount();
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, CreateNewBox.class, null);
                ; // R.id.fragment_container to ID kontenera w głównym layout
                transaction.addToBackStack(null); // Dodaj do stosu, jeśli chcesz umożliwić powrót
                transaction.commit();

            }
        });
        // Przucisk utworzenia nowego albumy.
        ImageButton openCreateAlbum = requireActivity().findViewById(R.id.openCreateAlbum);
        openCreateAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewAccount AddNewAccount = new AddNewAccount();
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, CreateNewAlbum.class, null);
                ; // R.id.fragment_container to ID kontenera w głównym layout
                transaction.addToBackStack(null); // Dodaj do stosu, jeśli chcesz umożliwić powrót
                transaction.commit();

            }
        });
    }
}