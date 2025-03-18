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
    private String name = null;
    private final Date boxCreateDate = new Date(); // Data utworzenia
    private int photo = 0; // Zdjęcie (możesz użyć innego typu, jeśli to konieczne)
    private final Bitmap imageBitmap;

    private String boxInformation = null; // Informacje o pudełku
    private int boxBackgroundColor = 0; // Kolor tła
    private int textColor = 0; // Kolor tekstu
    public ArrayList<Object> boxList = new ArrayList<>();
    private final int elements = boxList.size();


    // Konstruktor klasy Box
    public Box(String name, String boxInformation, int boxBackgroundColor, int textColor, Bitmap imageBitmap) {
        this.name = name;
        this.imageBitmap = imageBitmap;
        this.boxInformation = boxInformation;
        this.boxBackgroundColor = boxBackgroundColor;
        this.textColor = textColor;
        Log.d(TAG, "--------------");
        Log.d(TAG, "Obiekt utworzony");

    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
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

        // Tworzenie ImageView
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(400, 400));
        if (imageBitmap != null) {
            imageView.setImageBitmap(this.imageBitmap); // Ustaw obrazek
        }else{
            imageView.setImageResource(R.drawable.boxicon); // Ustaw obrazek
        }
        imageView.setVisibility(View.VISIBLE);
        imageView.setPadding(10, 10, 10, 10);

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
        titleTextView.setText(this.name);
        titleTextView.setTextColor(this.textColor);
        titleTextView.setTextSize(20);
        titleTextView.setTypeface(null, Typeface.BOLD);
        titleTextView.setShadowLayer(1, 0, 0, 0xFF050505);

        // Tworzenie TextView dla ilości
        TextView quantityTextView = new TextView(context);
        quantityTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        quantityTextView.setText(String.format("%d szt.", this.elements));
        quantityTextView.setTextColor(this.textColor);
        quantityTextView.setTextSize(15);

        // Tworzenie TextView dla data utworzenia
        TextView quantityTextView2 = new TextView(context);
        quantityTextView2.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        quantityTextView2.setText(String.format("Utworzono: %s ",sdf.format(boxCreateDate)));
        quantityTextView2.setTextColor(this.textColor);
        quantityTextView2.setTextSize(15);

        // Dodawanie TextView do układu tekstowego
        textLayout.addView(titleTextView);
        textLayout.addView(quantityTextView);
        textLayout.addView(quantityTextView2);

        // Dodawanie ImageView i układu tekstowego do wewnętrznego układu
        innerLayout.addView(imageView);
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
