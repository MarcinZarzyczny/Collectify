package com.example.collectify;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mrudultora.colorpicker.ColorPickerPopUp;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateNewBox#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateNewBox extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView boxIconImage;
    private CardView boxVidget;
    private TextView titleTextView;
    private ActivityResultLauncher<Intent> takePictureLauncher;
    private int boxBackgroundColor = Color.parseColor("#FFFFFF");
    private int textColor = Color.parseColor("#36115D");
    public String boxName = "";
    private String opis = "";
    private Bitmap imageBitmap;
    private TextView boxInformation;
    private TextView boxNameValue;
    private TextView informationNameView;
    private TextView dateView;
    private TextView boxDescription;

    public CreateNewBox() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateNewBox.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateNewBox newInstance(String param1, String param2) {
        CreateNewBox fragment = new CreateNewBox();
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



        takePictureLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        assert data != null;
                        Bundle extras = data.getExtras();
                        assert extras != null;
                        imageBitmap = (Bitmap) extras.get("data");
                        boxIconImage.setImageBitmap(imageBitmap);
                    }
                }
        );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_new_box, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();
        //Utworzenie dodatkowych stylów dla pul tekstowych.
        GradientDrawable border = new GradientDrawable();
        //border.setColor(Color.parseColor("#F1E4FF")); // Kolor tła
        border.setStroke(4, Color.parseColor("#36115D")); // Grubość i kolor obramowania
        border.setCornerRadius(25f); // Ustawia promień zaokrąglenia (można pominąć dla kształtu OVAL)

        boxInformation = requireActivity().findViewById(R.id.boxInformation);
        informationNameView = requireActivity().findViewById(R.id.informationNameBox);



        //Utworzenie dodatkowych stylów dla pul tekstowych.
        GradientDrawable bordertwo = new GradientDrawable();

        boxIconImage = requireActivity().findViewById(R.id.boxPhoto);
        ImageButton captureButton = requireActivity().findViewById(R.id.addPhotoButton);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.CAMERA}, 100);
                } else {
                    dispatchTakePictureIntent();
                }
            }
        });

        // Zmiaka koloru tła w boxie.
        ImageButton changeBoxColor = requireActivity().findViewById(R.id.changeBoxColor);
        boxVidget = requireActivity().findViewById(R.id.boxVidget);
        String textFromResources = getString(R.string.wybierz_kolor);

        changeBoxColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerPopUp colorPickerPopUp = new ColorPickerPopUp(requireActivity());	// Pass the context.
                colorPickerPopUp.setShowAlpha(true)			// By default show alpha is true.
                // .setDefaultColor(defaultColor) // By default red color is set.
                .setDialogTitle(textFromResources)
                .setOnPickColorListener(new ColorPickerPopUp.OnPickColorListener() {
                    @Override
                    public void onColorPicked(int color) {
                        boxBackgroundColor = color;
                        boxVidget.setBackgroundColor(boxBackgroundColor);
                        GradientDrawable drawable = new GradientDrawable();
                        drawable.setShape(GradientDrawable.RECTANGLE);
                        drawable.setColor(boxBackgroundColor); // Ustaw kolor tła
                        drawable.setCornerRadius(100f); // Ustaw promień zaokrąglenia
                        boxVidget.setBackground(drawable);

                    }

                    @Override
                    public void onCancel() {
                        colorPickerPopUp.dismissDialog();	// Dismiss the dialog.
                    }
                })
                .show();
            }
        });

        //Zmiana koloru tekstu w boxie
        titleTextView = requireActivity().findViewById(R.id.boxTitle);
        boxDescription = requireActivity().findViewById(R.id.boxContents);
        dateView = requireActivity().findViewById(R.id.boxCreationDate);
        ImageButton changeTextColor = requireActivity().findViewById(R.id.changeTextColor);
        String textFromResourcesTwo = getString(R.string.wybierz_kolor_czcionki);

        changeTextColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerPopUp colorPickerPopUp = new ColorPickerPopUp(requireActivity());	// Pass the context.
                colorPickerPopUp.setShowAlpha(true)			// By default show alpha is true.
                // .setDefaultColor(defaultColor) // By default red color is set.
                .setDialogTitle(textFromResourcesTwo)
                .setOnPickColorListener(new ColorPickerPopUp.OnPickColorListener() {
                    @Override
                    public void onColorPicked(int color) {
                        textColor = color;
                        titleTextView.setTextColor(textColor);
                        boxDescription.setTextColor(textColor);
                        dateView.setTextColor(textColor);
                    }
                    @Override
                    public void onCancel() {
                        colorPickerPopUp.dismissDialog();	// Dismiss the dialog.
                    }
                })
                .show();
            }
        });

    // Dodanie tytułu do kontenera
    boxNameValue = requireActivity().findViewById(R.id.boxName);
    CardView draw = requireActivity().findViewById(R.id.dram);
        draw.setBackground(border);

        boxNameValue.setBackground(bordertwo);

        boxNameValue.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // Możesz użyć tej metody, jeśli potrzebujesz
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if(startsWithWhitespace(boxNameValue.getText().toString())){
                informationNameView.setText(R.string.na_poczatku_nazwy_nie_moze_byc_bialego_znaku);
            }else {
                boxName = boxNameValue.getText().toString();
                titleTextView.setText(boxName);
                informationNameView.setText("");
            }
        }
        @Override
        public void afterTextChanged(Editable s) {
            // creatingLogin.setText(creatingLogi2);
        }
    });

        boxInformation.setBackground(border);
        boxInformation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Możesz użyć tej metody, jeśli potrzebujesz
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                opis = boxInformation.getText().toString();

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        // Obsługa przycisku zapisz
        ImageButton save = requireActivity().findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log.d(TAG, "Click działa");
                if(!startsWithWhitespace(boxNameValue.getText().toString()) && !boxNameValue.getText().toString().trim().isEmpty()){
                    Box box = new Box(boxName, opis, boxBackgroundColor, textColor, imageBitmap);
                    //Accounts accounts = new ViewModelProvider(requireActivity()).get(Accounts.class);
                    Accounts.logindAccount.addNewBox(box);
                    resetForm();
                    informationNameView.setText(R.string.nowa_kolekcja_dodana);
                }
            }
        });

        // Obsługa przycisku reset
        ImageButton reset = requireActivity().findViewById(R.id.resetForm);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.d(TAG, "Click działa");
                resetForm ();
            }
        });




    }
    @SuppressLint("QueryPermissionsNeeded")
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
            takePictureLauncher.launch(takePictureIntent);
        }
    }
    // Sprwdzanie czy ciag skłąsa się z samych białych znaków.
    public boolean isOnlyWhitespace(String title) {
        return title != null && title.matches("^\\s*$");
    }
    // sprawdzanei czy na poczatku ciągu są białe znaki.
    public static boolean startsWithWhitespace(String input) {
        return input != null && input.matches("^\\s+.*");
    }

    public void resetForm(){
       boxName = "";
       boxNameValue.setText(boxName);
       opis = "";
       boxInformation.setText(opis);
       boxBackgroundColor = Color.parseColor("#FFFFFF");
        int dpValue = 30;
        float pxValue = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
       boxVidget.setBackgroundColor(boxBackgroundColor);
       // Ustaw tło jako GradientDrawable
       GradientDrawable drawable = new GradientDrawable();
       drawable.setColor(boxBackgroundColor);
       drawable.setCornerRadius(pxValue); // Ustaw promień zaokrąglenia
       boxVidget.setBackground(drawable); // Ustaw tło widżetu
       textColor = Color.parseColor("#36115D");
       boxDescription.setTextColor(textColor);
       dateView.setTextColor(textColor);
       titleTextView.setTextColor(textColor);
       titleTextView.setText(R.string.box_one);
       informationNameView.setText(R.string.podaj_poprawna_nazwe_kolekcji);
       imageBitmap = null;
       boxIconImage.setImageResource(R.drawable.boxicon);
    }


}