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
    private String language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        
    }

    private void init() {
        mLanguagesSpinner=findViewById(R.id.languageSpinner);
        mChangeLanguageButton=findViewById(R.id.langugeChange);
        language=Locale.getDefault().getLanguage();
        initSpinner();
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

    }



    private void initSpinner() {
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
