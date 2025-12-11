package com.example.pet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pet.Auth.Pet;
import com.example.pet.Auth.PetRepository;
import com.example.pet.Auth.UserRepository;
import com.example.pet.Daily.ProfileActivity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Settings extends AppCompatActivity {

    EditText emailEditText;
    EditText passwordEditText;
    EditText petNameEditText;
    Spinner petTypeSpinner;
    EditText petGenderEditText;
    EditText petAgeEditText;
    EditText petBreedEditText;

    EditText petWeightEditText;

    Button btnSave;
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
    UserRepository userRepository;
    PetRepository petRepository;
    Executor executor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);

        try {
            userRepository = new UserRepository(this);
            petRepository = new PetRepository(this);
            executor = Executors.newSingleThreadExecutor();
        } catch (Exception e) {
            e.printStackTrace();
        }


        btnSave = findViewById(R.id.btnSignup);
        btnSave.setOnClickListener(v -> {
            updateData();
        });

        settings = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);

        emailEditText = findViewById(R.id.etEmail);
        passwordEditText = findViewById(R.id.etPassword);
        petNameEditText = findViewById(R.id.etPetName);
        petTypeSpinner = findViewById(R.id.etBreedName);
        petGenderEditText = findViewById(R.id.etGender);
        petAgeEditText = findViewById(R.id.etAge);
        petBreedEditText = findViewById(R.id.etColour);

        petWeightEditText = findViewById(R.id.etWeight);


// Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.planets_array,
                android.R.layout.simple_spinner_item
        );
// Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
        petTypeSpinner.setAdapter(adapter);

        getData();

    }

    public void Save(View view) {
//        emailEditText = findViewById(R.id.etEmail);
//        passwordEditText = findViewById(R.id.etPassword);
//        petNameEditText = findViewById(R.id.etPetName);
//        petTypeSpinner = findViewById(R.id.etBreedName);
//        petGenderEditText = findViewById(R.id.etGender);
//        petAgeEditText = findViewById(R.id.etAge);
//        petBreedEditText = findViewById(R.id.etColour);

        petWeightEditText = findViewById(R.id.etWeight);


        String mail = emailEditText.getText().toString();
        String pass = passwordEditText.getText().toString();
        String petname = petNameEditText.getText().toString();
        String breedname = petTypeSpinner.getSelectedItem().toString();
        String gend = petGenderEditText.getText().toString();
        String age = petAgeEditText.getText().toString();
        String color = petBreedEditText.getText().toString();

        String weight = petWeightEditText.getText().toString();


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

    private void updateData(){
        String userEmail = emailEditText.getText().toString();
        String userPassword = passwordEditText.getText().toString();
        String petname = petNameEditText.getText().toString();
        String breedname = petTypeSpinner.getSelectedItem().toString();
        String gend = petGenderEditText.getText().toString();
        String age = petAgeEditText.getText().toString();
        String color = petBreedEditText.getText().toString();

        String weight = petWeightEditText.getText().toString();



        if (userEmail.isEmpty() || userPassword.isEmpty() || petname.isEmpty() || breedname.isEmpty() || gend.isEmpty() || age.isEmpty() || color.isEmpty() ||  weight.isEmpty()){
            Toast.makeText(this, "Поля введены неверно!", Toast.LENGTH_SHORT).show();
        }
        else {
            userRepository.saveUser(userEmail, userPassword);

            Pet pet = new Pet(
                    petNameEditText.getText().toString(),
                    petTypeSpinner.getSelectedItem().toString(),
                    petGenderEditText.getText().toString(),
                    petAgeEditText.getText().toString(),
                    petBreedEditText.getText().toString(),
                    petWeightEditText.getText().toString()
            );
            executor.execute(() -> petRepository.saveOrUpdatePet(pet));
            startActivity(new Intent(this, ProfileActivity.class));
        }

    }
    private void getData(){

        String userEmail = userRepository.getEmail();
        String userPassword = userRepository.getPassword();

        emailEditText.setText(userEmail);
        passwordEditText.setText(userPassword);


        Pet pet = petRepository.getPet();

        petNameEditText.setText(pet.petName);
        //petTypeSpinner
        petGenderEditText.setText(pet.gender);
        petNameEditText.setText(pet.petName);

        petAgeEditText.setText(pet.age);
        petBreedEditText.setText(pet.breed);
        petWeightEditText.setText(pet.weight);

       // userRepository.saveUser(email, password);
    }

    public void BackBut(View view) {
        startActivity(new Intent(this, ProfileActivity.class));
    }
}