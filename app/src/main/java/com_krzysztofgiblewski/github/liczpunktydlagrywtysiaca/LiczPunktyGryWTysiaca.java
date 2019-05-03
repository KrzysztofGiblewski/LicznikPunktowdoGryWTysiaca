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
                if (editTextNowePunktyPierwszego.length() == 0)
                    Toast.makeText(getApplicationContext(), "nie wpisałeś " + editTextName1.getText().toString() + " punktów", Toast.LENGTH_LONG).show();
                if (editTextNowePunktyDrugiego.length() == 0)
                    Toast.makeText(getApplicationContext(), "nie wpisałeś " + editTextName2.getText().toString() + "  punktów", Toast.LENGTH_LONG).show();
                if (editTextNowePunktyTrzeciego.length() == 0)
                    Toast.makeText(getApplicationContext(), "nie wpisałeś " + editTextName3.getText().toString() + "  punktów", Toast.LENGTH_LONG).show();
                //jesli wszystkie punkty sa ok
                if (editTextNowePunktyPierwszego.length() > 0 && editTextNowePunktyDrugiego.length() > 0 && editTextNowePunktyTrzeciego.length() > 0) {

                    int nowePunktyPierwszego = Integer.valueOf(editTextNowePunktyPierwszego.getText().toString());
                    int nowePunktyDrugiego = Integer.valueOf(editTextNowePunktyDrugiego.getText().toString());
                    int nowePunktyTrzeciego = Integer.valueOf(editTextNowePunktyTrzeciego.getText().toString());

                    int starePunktyPierwszego = Integer.valueOf(textViewPunktyPierwszego.getText().toString());
                    int starePunktyDrugiego = Integer.valueOf(textViewPunktyDrugiego.getText().toString());
                    int starePunktyTrzeciego = Integer.valueOf(textViewPunktyTrzeciego.getText().toString());

                    int sumaPunktuwPierwszego = starePunktyPierwszego + nowePunktyPierwszego;
                    int sumaPunktuwDrugiego = starePunktyDrugiego + nowePunktyDrugiego;
                    int sumaPunktuwTrzeciego = starePunktyTrzeciego + nowePunktyTrzeciego;

                    textViewPunktyPierwszego.setText(String.valueOf(sumaPunktuwPierwszego));
                    textViewPunktyDrugiego.setText(String.valueOf(sumaPunktuwDrugiego));
                    textViewPunktyTrzeciego.setText(String.valueOf(sumaPunktuwTrzeciego));

                    if (sumaPunktuwPierwszego < 1000)
                        editTextNowePunktyPierwszego.setText("");
                    else editTextNowePunktyPierwszego.setText("Wygrał");
                    if (sumaPunktuwDrugiego < 1000)
                        editTextNowePunktyDrugiego.setText("");
                    else editTextNowePunktyDrugiego.setText("Wygrał");
                    if (sumaPunktuwTrzeciego < 1000)
                        editTextNowePunktyTrzeciego.setText("");
                    else editTextNowePunktyTrzeciego.setText("Wygrał");

                    if (liczKolejki > 3)
                        liczKolejki = 1;

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


                    liczKolejki += 1;
                }


            }
        });
    }
}
