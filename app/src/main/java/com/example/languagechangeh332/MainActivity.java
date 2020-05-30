package com.example.languagechangeh332;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Spinner mLanguagesSpinner;
    private Button mChangeLanguageButton;
    private Spinner mColorsSpinner;
    private Button mChangeColorButton;
    private String language;
    public static int sTheme=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(sTheme!=-1){
            setTheme(sTheme);
        }
        setContentView(R.layout.activity_main);
        init();


    }

    private void init() {
        mLanguagesSpinner=findViewById(R.id.languageSpinner);
        mChangeLanguageButton=findViewById(R.id.langugeChange);
        mColorsSpinner=findViewById(R.id.colorSpinner);
        mChangeColorButton=findViewById(R.id.colorChange);
        language=Locale.getDefault().getLanguage();
        initLanguageSpinner();
        initColorsSpinner();
        mChangeLanguageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Locale locale = new Locale(language);
                Configuration config = new Configuration();
                config.setLocale(locale);
                getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                recreate();
            }
        });




        mChangeColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });
    }

    private void initColorsSpinner() {
        ArrayAdapter<CharSequence> adapterColors = ArrayAdapter.createFromResource(this, R.array.colors, android.R.layout.simple_spinner_item);
        adapterColors.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mColorsSpinner.setAdapter(adapterColors);

        mColorsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                switch (position)
                {

                    case 0:
                        sTheme=R.style.AppTheme;
                        break;
                    case 1:
                        sTheme=R.style.ThemeBlack;
                        break;
                    case 2:
                        sTheme=R.style.ThemeGreen;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



    private void initLanguageSpinner() {
        ArrayAdapter<CharSequence> adapterLanguages = ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);
        adapterLanguages.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLanguagesSpinner.setAdapter(adapterLanguages);

        mLanguagesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String[] countries = getResources().getStringArray(R.array.languages);
                String str=countries[position];
                switch (str) {
                    case "Русский":
                        language="ru";
                        break;
                    case "English":
                        language="en";
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                language=Locale.getDefault().getLanguage();
            }
        });
    }
}
