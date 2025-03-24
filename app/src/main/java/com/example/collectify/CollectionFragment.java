package com.example.collectify;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CollectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CollectionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ActiveElements activeElements;

    public CollectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CollectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CollectionFragment newInstance(String param1, String param2) {
        CollectionFragment fragment = new CollectionFragment();
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
        return inflater.inflate(R.layout.fragment_collection, container, false);
    } @SuppressLint("DefaultLocale")
    @Override
    public void onResume() {
        super.onResume();

        activeElements = new ViewModelProvider(requireActivity()).get(ActiveElements.class);
        //Wstawianie zazwy
        TextView name =  requireActivity().findViewById(R.id.name);
        name.setText(activeElements.getActiveBox().getContainerName());
        name.setTextColor(activeElements.getActiveBox().getTextColor());
        name.setBackgroundColor(activeElements.getActiveBox().getBoxBackgroundColor());


        //Wstawianie zawartośći
        TextView contents =  requireActivity().findViewById(R.id.contents);
        contents.setText(String.format("%d szt.", activeElements.getActiveBox().getElements())); //activeElements.getActiveBox().getElements()));

        //Wstawianie daty
        TextView creationData =  requireActivity().findViewById(R.id.creationData);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        creationData.setText(String.format("Utworzono: %s ",sdf.format(activeElements.getActiveBox().getBoxCreateDate())));
        TextView description = requireActivity().findViewById(R.id.description);
        description.setText(activeElements.getActiveBox().getDescription());
        //Watawianie zdięcia
        ImageView collectionPhoto = requireActivity().findViewById(R.id.collectionPhoto);
        if(activeElements.getActiveBox().getPhoto() == null){
            collectionPhoto.setImageResource(R.drawable.boxicon); // Ustaw obrazek

        }else{
            collectionPhoto.setImageBitmap(activeElements.getActiveBox().getPhoto());
        } // Ustaw obrazek



    }
}