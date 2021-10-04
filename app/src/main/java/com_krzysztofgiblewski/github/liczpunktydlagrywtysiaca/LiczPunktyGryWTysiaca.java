package com_krzysztofgiblewski.github.liczpunktydlagrywtysiaca;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class LiczPunktyGryWTysiaca extends AppCompatActivity {

    private Button przycisk,
            buttonReset;
    private EditText editTextName1,
            editTextName2,
            editTextName3, editTekstNumberPunktyDoWygranej,
            editTextIloscGraczy;

    private TextView textViewPunktyPierwszego,
            textViewPunktyDrugiego,
            textViewPunktyTrzeciego,
            editTextNowePunktyPierwszego,
            editTextNowePunktyDrugiego,
            editTextNowePunktyTrzeciego;

    private Switch switchSprawdz;

    private int liczKolejki = 2;

    private int iluGraczy = 3;

    private int punktyDoWygranej = 1000;

    private int starePunktyPierwszego,
            starePunktyDrugiego,
            starePunktyTrzeciego,
            nowePunktyPierwszego,
            nowePunktyDrugiego,
            nowePunktyTrzeciego,
            sumaPunktuwPierwszego,
            sumaPunktuwDrugiego,
            sumaPunktuwTrzeciego = 0;

    private boolean sprawdzaj = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licz_punkty_gry_wtysiaca);
// ustawiam editText i textView i button powiazuje z r.id.button itd
        setupWszystkichElementow();
// ukrywam gorna belke z nazwa apki
        getSupportActionBar().hide();


        przycisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// jak brakuje wpisanych punktow
                sprawdzCzyWypelnione();
// jesli wszystkie punkty sa ok to sprawdzam
                jakWszystkieWypelnioneToDzialaj();

            }


        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// tworze okno dialogowe dla potwierdzenia czy restartować
                AlertDialog.Builder oknoDialogowe = new AlertDialog.Builder(LiczPunktyGryWTysiaca.this);
                oknoDialogowe.setTitle(getString(R.string.textDialogBoxTytul));
                oknoDialogowe.setMessage(getString(R.string.textDialogBoxText));
                oknoDialogowe.setPositiveButton(getString(R.string.textButtonaTak), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resetGry();
                    }
                });
                oknoDialogowe.setNegativeButton(getString(R.string.textButtonaNie), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(LiczPunktyGryWTysiaca.this, getString(R.string.textToastuZostawiamJakJest), Toast.LENGTH_SHORT).show();
                    }
                });
// i wyświetlam okno dialogowe
                oknoDialogowe.show();

            }
        });
// tu ustawiam swich tak żeby zmieniał zmienną sprawdzaj na true lub false
        switchSprawdz = findViewById(R.id.switchSprawdz);
        switchSprawdz.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switchSprawdz.isChecked()) {
                    sprawdzaj = true;
                } else {
                    sprawdzaj = false;
                }
            }
        });

    }


    // Zapobieganie znikaniu punktów zapisuje putString pod kluczem "aaa"
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("aaa", textViewPunktyPierwszego.getText().toString());
        outState.putString("bbb", textViewPunktyDrugiego.getText().toString());
        outState.putString("ccc", textViewPunktyTrzeciego.getText().toString());

        outState.putInt("liczKolejki", liczKolejki);
    }

    // po obrubeniu ekranu wskakuje ten stan z saveInstance.getString i po kluczy "aaa" pobiera zachowany stan
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textViewPunktyPierwszego.setText(savedInstanceState.getString("aaa"));
        textViewPunktyDrugiego.setText(savedInstanceState.getString("bbb"));
        textViewPunktyTrzeciego.setText(savedInstanceState.getString("ccc"));
        liczKolejki = savedInstanceState.getInt("liczKolejki");
        pokarzCzyjaKolej();

    }


    private void resetGry() {
        liczKolejki = 2;
        sumaPunktuwPierwszego = 0;
        sumaPunktuwDrugiego = 0;
        sumaPunktuwTrzeciego = 0;
        textViewPunktyPierwszego.setText(String.valueOf(sumaPunktuwPierwszego));
        textViewPunktyDrugiego.setText(String.valueOf(sumaPunktuwDrugiego));
        textViewPunktyTrzeciego.setText(String.valueOf(sumaPunktuwTrzeciego));
        editTextName1.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        editTextName1.setTextColor(Color.RED);
        editTextName2.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        editTextName2.setTextColor(Color.BLACK);
        editTextName3.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        editTextName3.setTextColor(Color.BLACK);
        editTextNowePunktyPierwszego.setText("");
        editTextNowePunktyDrugiego.setText("");
        editTextNowePunktyTrzeciego.setText("");

    }

    private void jakWszystkieWypelnioneToDzialaj() {
        if (editTextNowePunktyPierwszego.length() > 0 && editTextNowePunktyDrugiego.length() > 0 && editTextNowePunktyTrzeciego.length() > 0) {
            pobierzIUstawWszystkiePola();
            sprawdzKtoWygral(sumaPunktuwPierwszego, sumaPunktuwDrugiego, sumaPunktuwTrzeciego);
            pokarzCzyjaKolej();
            // i dodaj kolejke
            liczKolejki++;


        }
    }

    private void setupWszystkichElementow() {
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
        buttonReset = (Button) findViewById(R.id.buttonReset);

        editTextIloscGraczy = (EditText) findViewById(R.id.editTextIloscGraczy);

        editTekstNumberPunktyDoWygranej = (EditText) findViewById(R.id.editTextNPDoW);
    }

    private void sprawdzCzyWypelnione() {
        iluGraczy = Integer.parseInt(editTextIloscGraczy.getText().toString());
        if (iluGraczy < 3)
            editTextNowePunktyTrzeciego.setText("0");
        if (iluGraczy < 2)
            editTextNowePunktyDrugiego.setText("0");

        if (editTextNowePunktyPierwszego.length() == 0) {
            Toast.makeText(getApplicationContext(), getString(R.string.tekstNieWpisales) + editTextName1.getText().toString() + getString(R.string.textPuntow), Toast.LENGTH_SHORT).show();
        }
        if (editTextNowePunktyDrugiego.length() == 0) {
            Toast.makeText(getApplicationContext(), getString(R.string.tekstNieWpisales) + editTextName2.getText().toString() + getString(R.string.textPuntow), Toast.LENGTH_SHORT).show();
        }
        if (editTextNowePunktyTrzeciego.length() == 0) {
            Toast.makeText(getApplicationContext(), getString(R.string.tekstNieWpisales) + editTextName3.getText().toString() + getString(R.string.textPuntow), Toast.LENGTH_LONG).show();
        }
    }

    private void pokarzCzyjaKolej() {
//                licze do trzech i co ilosc graczy domyslnie trzech resetuje
        if (liczKolejki > iluGraczy) {
            liczKolejki = 1;
        }
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


    }

    private void pobierzIUstawWszystkiePola() {
        nowePunktyPierwszego = Integer.parseInt(editTextNowePunktyPierwszego.getText().toString());
        nowePunktyDrugiego = Integer.parseInt(editTextNowePunktyDrugiego.getText().toString());
        nowePunktyTrzeciego = Integer.parseInt(editTextNowePunktyTrzeciego.getText().toString());

        starePunktyPierwszego = Integer.parseInt(textViewPunktyPierwszego.getText().toString());
        starePunktyDrugiego = Integer.parseInt(textViewPunktyDrugiego.getText().toString());
        starePunktyTrzeciego = Integer.parseInt(textViewPunktyTrzeciego.getText().toString());

        sumaPunktuwPierwszego = starePunktyPierwszego + nowePunktyPierwszego;
        sumaPunktuwDrugiego = starePunktyDrugiego + nowePunktyDrugiego;
        sumaPunktuwTrzeciego = starePunktyTrzeciego + nowePunktyTrzeciego;

        textViewPunktyPierwszego.setText(String.valueOf(sumaPunktuwPierwszego));
        textViewPunktyDrugiego.setText(String.valueOf(sumaPunktuwDrugiego));
        textViewPunktyTrzeciego.setText(String.valueOf(sumaPunktuwTrzeciego));
    }

    private void sprawdzKtoWygral(int sumaPunktuwPierwszego, int sumaPunktuwDrugiego, int sumaPunktuwTrzeciego) {
        // punkty ktore mozna ustawic po przekroczeniu ktorych wyskoczy wygrana
        punktyDoWygranej = Integer.parseInt(editTekstNumberPunktyDoWygranej.getText().toString());
//  jesli sprawdzaj = true w wersji skruconej
        if (sprawdzaj) {
            if (sumaPunktuwPierwszego < punktyDoWygranej) {
                editTextNowePunktyPierwszego.setText("");
            } else {
                editTextNowePunktyPierwszego.setText(getString(R.string.textWygral));
            }
            if (sumaPunktuwDrugiego < punktyDoWygranej) {
                editTextNowePunktyDrugiego.setText("");
            } else {
                editTextNowePunktyDrugiego.setText(getString(R.string.textWygral));
            }
            if (sumaPunktuwTrzeciego < punktyDoWygranej) {
                editTextNowePunktyTrzeciego.setText("");
            } else {
                editTextNowePunktyTrzeciego.setText(getString(R.string.textWygral));
            }
        } else {
            editTextNowePunktyPierwszego.setText("");
            editTextNowePunktyDrugiego.setText("");
            editTextNowePunktyTrzeciego.setText("");
        }

    }
}
