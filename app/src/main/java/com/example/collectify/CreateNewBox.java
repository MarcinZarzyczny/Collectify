package com.example.collectify;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.Manifest;
import android.widget.TextView;

import com.mrudultora.colorpicker.ColorPickerPopUp;

import org.jetbrains.annotations.Nullable;

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
    private ImageView imageView;
    private CardView boxVidget;
    private TextView boxTitle;
    private ActivityResultLauncher<Intent> takePictureLauncher;

    private int boxBackgroundColor = Color.parseColor("#FF36115D");
    ;
    private int textColor = Color.parseColor("#FF36115D");
    public String boxName = "";

    private String boxInformation = "";

    private int boxPhoto = 1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        takePictureLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        assert data != null;
                        Bundle extras = data.getExtras();
                        assert extras != null;
                        Bitmap imageBitmap = (Bitmap) extras.get("data");
                        imageView.setImageBitmap(imageBitmap);
                    }
                }
        );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_new_box, container, false);
    }  public void onResume() {
        super.onResume();


        imageView = requireActivity().findViewById(R.id.boxPhoto);
        ImageButton captureButton = requireActivity().findViewById(R.id.addPhotoButton);

        TextView textView = requireActivity().findViewById(R.id.boxName);

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
        boxTitle = requireActivity().findViewById(R.id.boxTitle);
        TextView boxContents = requireActivity().findViewById(R.id.boxContents);
        TextView boxCreationDate = requireActivity().findViewById(R.id.boxCreationDate);
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
                        boxTitle.setTextColor(textColor);
                        boxContents.setTextColor(textColor);
                        boxCreationDate.setTextColor(textColor);
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
    TextView boxNameTwo = requireActivity().findViewById(R.id.boxName);
        boxNameTwo.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // Możesz użyć tej metody, jeśli potrzebujesz
        }

        // Sprawdzanie poprawności loginu
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            boxName = boxNameTwo.getText().toString();
            boxTitle.setText( boxName);
        }

        @Override
        public void afterTextChanged(Editable s) {
            // creatingLogin.setText(creatingLogi2);
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




}