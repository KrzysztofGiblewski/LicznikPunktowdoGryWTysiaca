package com_krzysztofgiblewski.github.liczpunktydlagrywtysiaca;

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

    private boolean pierwszy = true;
    private boolean drogi = false;
    private boolean trzeci = false;

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


                if (editTextNowePunktyPierwszego.length() == 0)
                    Toast.makeText(getApplicationContext(), "nie wpisałeś " + editTextName1.getText().toString() + " punktów", Toast.LENGTH_LONG).show();
                if (editTextNowePunktyDrugiego.length() == 0)
                    Toast.makeText(getApplicationContext(), "nie wpisałeś " + editTextName2.getText().toString() + "  punktów", Toast.LENGTH_LONG).show();
                if (editTextNowePunktyTrzeciego.length() == 0)
                    Toast.makeText(getApplicationContext(), "nie wpisałeś " + editTextName3.getText().toString() + "  punktów", Toast.LENGTH_LONG).show();

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

                    editTextNowePunktyPierwszego.setText("");
                    editTextNowePunktyDrugiego.setText("");
                    editTextNowePunktyTrzeciego.setText("");
                }


            }
        });
    }
}
