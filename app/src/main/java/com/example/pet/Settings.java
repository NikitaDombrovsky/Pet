package com.example.pet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pet.Daily.ProfileActivity;

public class Settings extends AppCompatActivity {

    EditText email;
    EditText password;
    EditText Petname;
    Spinner BreedName;
    EditText gender;
    EditText Age;
    EditText colour;

    EditText Weight;


    private static final String PREFS_FILE = "Account"; // Название "Базы данных" (Поменяешь и все сотрется)
    private static final String PREF_NAME = "Name"; // Название поля в БД, например Имя
    private static final String PREF_MAIL = "Mail"; // Название поля в БД, например Имя
    private static final String PREF_PASS = "Pass"; // Название поля в БД, например Имя
    private static final String PREF_TYPE = "Type"; // Название поля в БД, например Имя
    private static final String PREF_GENDER = "Gender"; // Название поля в БД, например Имя
    private static final String PREF_AGE = "Age"; // Название поля в БД, например Имя
    private static final String PREF_COLOUR = "Colour"; // Название поля в БД, например Имя

    private static final String PREF_WEIGHT = "Weight"; // Название поля в БД, например Имя

    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        settings = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);

        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        Petname = findViewById(R.id.etPetName);
        BreedName = findViewById(R.id.etBreedName);
        gender = findViewById(R.id.etGender);
        Age = findViewById(R.id.etAge);
        colour = findViewById(R.id.etColour);

        Weight = findViewById(R.id.etWeight);


// Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.planets_array,
                android.R.layout.simple_spinner_item
        );
// Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
        BreedName.setAdapter(adapter);


    }

    public void Save(View view) {
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        Petname = findViewById(R.id.etPetName);
        BreedName = findViewById(R.id.etBreedName);
        gender = findViewById(R.id.etGender);
        Age = findViewById(R.id.etAge);
        colour = findViewById(R.id.etColour);

        Weight = findViewById(R.id.etWeight);


        String mail = email.getText().toString();
        String pass = password.getText().toString();
        String petname = Petname.getText().toString();
        String breedname = BreedName.getSelectedItem().toString();
        String gend = gender.getText().toString();
        String age = Age.getText().toString();
        String color = colour.getText().toString();

        String weight = Weight.getText().toString();


        if (mail.isEmpty() || pass.isEmpty() || petname.isEmpty() || breedname.isEmpty() || gend.isEmpty() || age.isEmpty() || color.isEmpty() ||  weight.isEmpty()){
            Toast.makeText(this, "Поля введены неверно!", Toast.LENGTH_SHORT).show();
        }
        else{
// сохраняем его в настройках
            SharedPreferences.Editor prefEditor = settings.edit();
            prefEditor.putString(PREF_MAIL, mail);
            prefEditor.putString(PREF_PASS, pass);
            prefEditor.putString(PREF_NAME, petname);
            prefEditor.putString(PREF_TYPE, breedname);
            prefEditor.putString(PREF_GENDER, gend);
            prefEditor.putString(PREF_AGE, age);
            prefEditor.putString(PREF_COLOUR, color);

            prefEditor.putString(PREF_WEIGHT, weight);   // PREF_NAME - наше название поля в БД; переменная name - данные которые мы сохраняем

            // Все твои данные
            prefEditor.apply();

            startActivity(new Intent(this, ProfileActivity.class));
        }




    }

    public void BackBut(View view) {
        startActivity(new Intent(this, ProfileActivity.class));
    }
}