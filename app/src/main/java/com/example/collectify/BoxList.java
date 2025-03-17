package com.example.collectify;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private LinearLayout myLayout;
    private ImageButton addNewBox;

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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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
        Accounts accounts = new ViewModelProvider(requireActivity()).get(Accounts.class);


        myLayout = requireActivity().findViewById(R.id.box2);

        // Upewnij się, że myLayout nie jest null
        if (myLayout != null) {
            Log.e(TAG, "--------------------------------------------------------------");
            accounts.createBox("login1", myLayout, requireContext());
        } else {
            Log.e(TAG, "-----------------------2------------------------------------");

            Log.e(TAG, "myLayout is null. Check if R.id.box2 exists in the current layout.");
        }



        addNewBox = requireActivity().findViewById(R.id.addNewBox);
        addNewBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewAccount AddNewAccount = new AddNewAccount();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, CreateCollection.class, null);
                ; // R.id.fragment_container to ID kontenera w głównym layout
                transaction.addToBackStack(null); // Dodaj do stosu, jeśli chcesz umożliwić powrót
                transaction.commit();

            }
        });


        ImageButton openAddNewAccount = requireActivity().findViewById(R.id.openSettings);
        openAddNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d( TAG,"Click działa poprawnie");

                Log.d(TAG, "LF- Dane wysłane: " + v);


                AddNewAccount AddNewAccount = new AddNewAccount();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, Setting.class, null);
                ; // R.id.fragment_container to ID kontenera w głównym layout
                transaction.addToBackStack(null); // Dodaj do stosu, jeśli chcesz umożliwić powrót
                transaction.commit();


            }
        });


    }
    /*private void addCardView(String nazwa, String ilosc) {
        {
            CardView cardView = new CardView(requireContext());
            LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            cardView.setLayoutParams(cardParams);
            cardView.setCardBackgroundColor(0xFFFBF8FB); // background
            cardView.setCardElevation(16);
            cardView.setRadius(100);
            cardView.setCardBackgroundColor(0xFFFBF8F8);
            cardView.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white));


            LinearLayout innerLayout = new LinearLayout(getContext());
            innerLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
            innerLayout.setOrientation(LinearLayout.HORIZONTAL);

            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(300, 300));
            imageView.setImageResource(R.mipmap.ic_launcher);
            imageView.setVisibility(View.VISIBLE);
            imageView.setPadding(0, 0, 10, 0);

            LinearLayout textLayout = new LinearLayout(getContext());
            textLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
            textLayout.setOrientation(LinearLayout.VERTICAL);
            textLayout.setGravity(Gravity.CENTER);

            TextView titleTextView = new TextView(getContext());
            titleTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            titleTextView.setText(nazwa);
            titleTextView.setTextColor(0xFF36115D);
            titleTextView.setTextSize(16);
            titleTextView.setTypeface(null, Typeface.BOLD);
            titleTextView.setShadowLayer(1, 0, 0, 0xFF050505);


            TextView quantityTextView = new TextView(getContext());
            quantityTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            quantityTextView.setText("Zawartość: " + ilosc);
            quantityTextView.setTextColor(0xFF36115D);
            quantityTextView.setTextSize(10);

            textLayout.addView(titleTextView);
            textLayout.addView(quantityTextView);
            innerLayout.addView(imageView);
            innerLayout.addView(textLayout);
            cardView.addView(innerLayout);

            // Załóżmy, że cardView jest już zainicjalizowane
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );

            // Ustaw marginesy (górny i dolny) na 10dp
            int marginInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
            params.setMargins(10, marginInDp, 10, marginInDp);

            // Zastosuj LayoutParams do cardView
            cardView.setLayoutParams(params);

            myLayout.addView(cardView);

        }
    }*/
}