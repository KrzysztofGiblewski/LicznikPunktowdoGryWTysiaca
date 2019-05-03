package com_krzysztofgiblewski.github.liczpunktydlagrywtysiaca;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LiczPunktyGryWTysiaca extends AppCompatActivity {

    private Button przycisk;
    private EditText editTextName1, editTextName2, editTextName3;
    private TextView textViewPunktyPierwszego, textViewPunktyDrugiego, textViewPunktyTrzeciego;
    private EditText editTextNowePunktyPierwszego, editTextNowePunktyDrugiego, editTextNowePunktyTrzeciego;

    private int liczKolejki = 2;
    private int starePunktyPierwszego = 0;
    private int starePunktyDrugiego = 0;
    private int starePunktyTrzeciego = 0;
    private int nowePunktyPierwszego = 0;
    private int nowePunktyDrugiego = 0;
    private int nowePunktyTrzeciego = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licz_punkty_gry_wtysiaca);

        editTextName1 = (EditText) findViewById(R.id.editTextName1);
        editTextName2 = (EditText) findViewById(R.id.editTextName2);
        editTextName3 = (EditText) findViewById(R.id.editTextName3);

        textViewPunktyPierwszego = (TextView) findViewById(R.id.textViewPunktyPierwszego);
        textViewPunktyDrugiego = (TextView) findViewById(R.id.textViewPunktyDrugiego);
        textViewPunktyTrzeciego = (TextView) findViewById(R.id.textViewPunktyTrzeciego);

        editTextNowePunktyPierwszego = (EditText) findViewById(R.id.editTextNoweZdobytePunktyPierwszego);
        editTextNowePunktyDrugiego = (EditText) findViewById(R.id.editTextNoweZdobytePunktyDrugiego);
        editTextNowePunktyTrzeciego = (EditText) findViewById(R.id.editTextNoweZdobytePunktyTrzeciego);

        przycisk = (Button) findViewById(R.id.button);


        przycisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //jak brakuje wpisanych punktow
                sprawdzCzyWypelnione();
                //jesli wszystkie punkty sa ok to sprawdzam
                if (editTextNowePunktyPierwszego.length() > 0 && editTextNowePunktyDrugiego.length() > 0 && editTextNowePunktyTrzeciego.length() > 0) {

                    nowePunktyPierwszego = Integer.valueOf(editTextNowePunktyPierwszego.getText().toString());
                    nowePunktyDrugiego = Integer.valueOf(editTextNowePunktyDrugiego.getText().toString());
                    nowePunktyTrzeciego = Integer.valueOf(editTextNowePunktyTrzeciego.getText().toString());

                    starePunktyPierwszego = Integer.valueOf(textViewPunktyPierwszego.getText().toString());
                    starePunktyDrugiego = Integer.valueOf(textViewPunktyDrugiego.getText().toString());
                    starePunktyTrzeciego = Integer.valueOf(textViewPunktyTrzeciego.getText().toString());


                    int sumaPunktuwPierwszego = starePunktyPierwszego + nowePunktyPierwszego;
                    int sumaPunktuwDrugiego = starePunktyDrugiego + nowePunktyDrugiego;
                    int sumaPunktuwTrzeciego = starePunktyTrzeciego + nowePunktyTrzeciego;

                    textViewPunktyPierwszego.setText(String.valueOf(sumaPunktuwPierwszego));
                    textViewPunktyDrugiego.setText(String.valueOf(sumaPunktuwDrugiego));
                    textViewPunktyTrzeciego.setText(String.valueOf(sumaPunktuwTrzeciego));

                    sprawdzKtoWygral(sumaPunktuwPierwszego, sumaPunktuwDrugiego, sumaPunktuwTrzeciego);
                    pokarzCzyjaKolej();

                }


            }

            private void sprawdzCzyWypelnione() {
                if (editTextNowePunktyPierwszego.length() == 0)
                    Toast.makeText(getApplicationContext(), "nie wpisałeś " + editTextName1.getText().toString() + " punktów", Toast.LENGTH_SHORT).show();
                if (editTextNowePunktyDrugiego.length() == 0)
                    Toast.makeText(getApplicationContext(), "nie wpisałeś " + editTextName2.getText().toString() + "  punktów", Toast.LENGTH_SHORT).show();
                if (editTextNowePunktyTrzeciego.length() == 0)
                    Toast.makeText(getApplicationContext(), "nie wpisałeś " + editTextName3.getText().toString() + "  punktów", Toast.LENGTH_LONG).show();
            }

            private void pokarzCzyjaKolej() {
//                licze do trzech i co trzy restartuje
                if (liczKolejki > 3)
                    liczKolejki = 1;
//          sprawdzam czyja kolej i zmienia mu kolor i pogrubienie
                if (liczKolejki == 1) {
                    editTextName1.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    editTextName1.setTextColor(Color.RED);
                    editTextName2.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    editTextName2.setTextColor(Color.BLACK);
                    editTextName3.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    editTextName3.setTextColor(Color.BLACK);
                }
                if (liczKolejki == 2) {
                    editTextName2.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    editTextName2.setTextColor(Color.RED);
                    editTextName3.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    editTextName3.setTextColor(Color.BLACK);
                    editTextName1.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    editTextName1.setTextColor(Color.BLACK);
                }
                if (liczKolejki == 3) {
                    editTextName3.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    editTextName3.setTextColor(Color.RED);
                    editTextName1.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    editTextName1.setTextColor(Color.BLACK);
                    editTextName2.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    editTextName2.setTextColor(Color.BLACK);
                }
                //na wyjsciu zwiekszam licznik o jeden
                liczKolejki += 1;
            }
        });
    }

    private void sprawdzKtoWygral(int sumaPunktuwPierwszego, int sumaPunktuwDrugiego, int sumaPunktuwTrzeciego) {
        if (sumaPunktuwPierwszego < 1000)
            editTextNowePunktyPierwszego.setText("");
        else editTextNowePunktyPierwszego.setText("Wygrał");
        if (sumaPunktuwDrugiego < 1000)
            editTextNowePunktyDrugiego.setText("");
        else editTextNowePunktyDrugiego.setText("Wygrał");
        if (sumaPunktuwTrzeciego < 1000)
            editTextNowePunktyTrzeciego.setText("");
        else editTextNowePunktyTrzeciego.setText("Wygrał");
    }
}
