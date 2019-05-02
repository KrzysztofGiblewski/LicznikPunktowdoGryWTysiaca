package com_krzysztofgiblewski.github.liczpunktydlagrywtysiaca;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LiczPunktyGryWTysiaca extends AppCompatActivity {

    private Button przycisk;
    private EditText editTextName1, editTextName2, editTextName3;
    private TextView textViewPunktyPierwszego, textViewPunktyDrugiego, textViewPunktyTrzeciego;
    private EditText editTextNowePunktyPierwszego, editTextNowePunktyDrugiego, editTextNowePunktyTrzeciego;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licz_punkty_gry_wtysiaca);
        textViewPunktyPierwszego = (TextView) findViewById(R.id.textViewPunktyPierwszego);

        przycisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
