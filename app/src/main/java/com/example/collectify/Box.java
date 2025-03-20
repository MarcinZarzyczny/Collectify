package com.example.collectify;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Box extends ViewModel {
    private String containerName = null;
    private final Date boxCreateDate = new Date(); // Data utworzenia
    private int photo = 0; // Zdjęcie (możesz użyć innego typu, jeśli to konieczne)
    private final Bitmap imageBitmap;

    private String boxInformation = null; // Informacje o pudełku
    private int boxBackgroundColor = 0; // Kolor tła
    private int textColor = 0; // Kolor tekstu
    public ArrayList<Object> boxList = new ArrayList<>();
    private int elements = boxList.size();



    // Konstruktor klasy Box
    public Box(String name, String boxInformation, int boxBackgroundColor, int textColor, Bitmap imageBitmap) {
        this.containerName = name;
        this.imageBitmap = imageBitmap;
        this.boxInformation = boxInformation;
        this.boxBackgroundColor = boxBackgroundColor;
        this.textColor = textColor;
        Log.d(TAG, "--------------");
        Log.d(TAG, "Obiekt utworzony");

    }
    public String getContainerName() {
        return this.containerName;
    }
    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }
    // Gettery i settery dla nowych pól
    public Date getBoxCreateDate() {
        return boxCreateDate;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getBoxInformation() {
        return boxInformation;
    }

    public void setBoxInformation(String boxInformation) {
        this.boxInformation = boxInformation;
    }

    public int getBoxBackgroundColor() {
        return boxBackgroundColor;
    }

    public void setBoxBackgroundColor(int boxBackgroundColor) {
        this.boxBackgroundColor = boxBackgroundColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void createBox(ViewGroup myLayout, Context context) {

        // Tworzenie CardView
        CardView cardView = new CardView(context);
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        cardView.setLayoutParams(cardParams);

        // Ustawienie koloru tła za pomocą setCardBackgroundColor
        cardView.setCardBackgroundColor(this.boxBackgroundColor); // Użyj setCardBackgroundColor

        int marginInDpTwo = 5; // Margines w dp
        float marginInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, marginInDpTwo, context.getResources().getDisplayMetrics());
        LinearLayout.LayoutParams paramsTwo = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, // Szerokość
                ViewGroup.LayoutParams.WRAP_CONTENT); // Wysokość
        paramsTwo.setMargins((int) marginInPx, (int) marginInPx, (int) marginInPx, (int) marginInPx);

        // Przypisanie LayoutParams do widoku
        cardView.setLayoutParams(paramsTwo); // 'yourView' to widok, dla którego ustawiasz marginesy
        cardView.setCardElevation(16);
        cardView.setRadius(100); // Ustaw promień zaokrąglenia

        // Tworzenie wewnętrznego układu
        LinearLayout innerLayout = new LinearLayout(context);
        innerLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        innerLayout.setOrientation(LinearLayout.HORIZONTAL);
        paramsTwo.setMargins((int) marginInPx, (int) marginInPx, (int) marginInPx, (int) marginInPx);

        // Tworzenie CardView
        CardView cardViewTwo = new CardView(context);
        // Ustal rozmiar w dp

        // Ustaw LayoutParams z wysokością w px
        LinearLayout.LayoutParams cardParamsTwo = new LinearLayout.LayoutParams(
                300, // Szerokość
                300// Wysokość
        );

        cardViewTwo.setLayoutParams(cardParamsTwo);
        float marginLeft = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, context.getResources().getDisplayMetrics());
        cardParamsTwo.setMargins((int) marginLeft, (int) marginInPx, (int) marginInPx, (int) marginInPx);
        // Przypisanie LayoutParams do widoku
        cardViewTwo.setCardElevation(16);
        cardViewTwo.setRadius(100); // Ustaw promień zaokrąglenia


        // Tworzenie ImageView
        ImageView boxIconImage = new ImageView(context);

        int imageSize = 300;
        int imageWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, imageSize, context.getResources().getDisplayMetrics());
        int imageHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, imageSize, context.getResources().getDisplayMetrics());
        boxIconImage.setLayoutParams(new LinearLayout.LayoutParams(imageWidth, imageHeight));

        int marginInDptThree = 10; // Margines w dp
        if (imageBitmap != null) {
            boxIconImage.setImageBitmap(this.imageBitmap); // Ustaw obrazek
            int marginInDptWO = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, marginInDptThree, context.getResources().getDisplayMetrics());

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            params.setMargins(marginInDptWO, marginInDptWO, marginInDptWO, marginInDptWO); // Ustawienie marginesów (lewy, górny, prawy, dolny)
            boxIconImage.setLayoutParams(params);

        } else {
            boxIconImage.setImageResource(R.drawable.boxicon); // Ustaw obrazek
            int marginInDptWO = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, marginInDptThree, context.getResources().getDisplayMetrics());
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            imageParams.setMargins(marginInDptWO, marginInDptWO, marginInDptWO, marginInDptWO); // Ustawienie marginesów (lewy, górny, prawy, dolny)
            boxIconImage.setLayoutParams(imageParams); // Przypisanie LayoutParams do ImageView


        }
        boxIconImage.setVisibility(View.VISIBLE);
        // Ustawienie marginesów

        cardViewTwo.addView(boxIconImage);

        //Ustawianie rozmiaru czcionki
        marginInDptThree = 5; // Margines w sp
        int fontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, marginInDptThree, context.getResources().getDisplayMetrics());

        // Tworzenie układu tekstowego
        LinearLayout textLayout = new LinearLayout(context);
        textLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        textLayout.setOrientation(LinearLayout.VERTICAL);
        textLayout.setGravity(Gravity.CENTER);

        // Tworzenie TextView dla tytułu
        TextView titleTextView = new TextView(context);
        titleTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        titleTextView.setText(this.containerName);
        titleTextView.setTextColor(this.textColor);
        titleTextView.setTextSize(20);
        titleTextView.setPadding(20, 0,0,0);
        titleTextView.setTypeface(null, Typeface.BOLD);
        titleTextView.setShadowLayer(1, 0, 0, 0xFF050505);

        // Tworzenie TextView dla ilości
        TextView quantityTextView = new TextView(context);
        quantityTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        quantityTextView.setText(String.format("%d szt.", this.elements));
        quantityTextView.setTextColor(this.textColor);
        quantityTextView.setPadding(20, 0,0,0);

        quantityTextView.setTextSize(fontSize);

        // Tworzenie TextView dla data utworzenia
        TextView quantityTextView2 = new TextView(context);
        quantityTextView2.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        quantityTextView2.setText(String.format("Utworzono: %s ",sdf.format(boxCreateDate)));
        quantityTextView2.setTextColor(this.textColor);
        quantityTextView2.setPadding(20, 0,0,0);
        quantityTextView2.setTextSize(fontSize);

        // Dodawanie TextView do układu tekstowego
        textLayout.addView(titleTextView);
        textLayout.addView(quantityTextView);
        textLayout.addView(quantityTextView2);

        // Dodawanie ImageView i układu tekstowego do wewnętrznego układu
        innerLayout.addView(cardViewTwo);
        innerLayout.addView(textLayout);

        // Dodawanie wewnętrznego układu do CardView
        cardView.addView(innerLayout);

        // Ustaw marginesy dla CardView
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        // Ustaw marginesy (górny i dolny) na 5dp
        int marginInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, context.getResources().getDisplayMetrics());
        int marginInDpthree = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, context.getResources().getDisplayMetrics());
        int marginInDpFour= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, context.getResources().getDisplayMetrics());

        params.setMargins(marginInDpFour, marginInDp, marginInDpFour, marginInDpthree);

        // Zastosuj LayoutParams do cardView
        cardView.setLayoutParams(params);

        // Dodawanie cardView do myLayout
        myLayout.addView(cardView);

    }

}
