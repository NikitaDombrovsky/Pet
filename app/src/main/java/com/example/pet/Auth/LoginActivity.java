package com.example.pet.Auth;

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

import com.example.pet.Daily.ProfileActivity;
import com.example.pet.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    UserRepository userRepository;

    EditText emailEditText;
    EditText passwordEditText;
    EditText petNameEditText;
    Spinner petTypeSpinner;
    EditText petGenderEditText;
    // TODO
    //Spinner petGenderEditText;
    EditText petAgeEditText;
    EditText petBreedEditText;


    EditText Weight;
    PetRepository repository;
    Executor executor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        try {
            repository = new PetRepository(this);
            userRepository = new UserRepository(this);
            executor = Executors.newSingleThreadExecutor();
        } catch (Exception e) {
            e.printStackTrace();
        }

        emailEditText = findViewById(R.id.etEmail);
        passwordEditText = findViewById(R.id.etPassword);
        petNameEditText = findViewById(R.id.etPetName);
        petTypeSpinner = findViewById(R.id.etBreedName);
        petGenderEditText = findViewById(R.id.etGender);
        petAgeEditText = findViewById(R.id.etAge);
        petBreedEditText = findViewById(R.id.etColour);

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
        petTypeSpinner.setAdapter(adapter);

        Button btnLogin = findViewById(R.id.btnSignup);


        btnLogin.setOnClickListener(v -> {
            SignUp();
        });
    }

    public void SignUp() {

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        String petName = petNameEditText.getText().toString();
        String petGender = petGenderEditText.getText().toString();
        String age = petAgeEditText.getText().toString();
        String petBreed = petBreedEditText.getText().toString();
        String weight = Weight.getText().toString();

        String type = petTypeSpinner.getSelectedItem().toString();

        if (email.isEmpty() || password.isEmpty() || petName.isEmpty() || type.isEmpty() || petGender.isEmpty() || age.isEmpty() || petBreed.isEmpty() || weight.isEmpty()) {
            Toast.makeText(this, "Поля введены неверно!", Toast.LENGTH_SHORT).show();
        } else {

            userRepository.saveUser(email, password);
            Pet pet = new Pet(petName, type, petGender, age, petBreed, weight);

            executor.execute(() -> repository.saveOrUpdatePet(pet));

            startActivity(new Intent(this, ProfileActivity.class));
        }


    }

    public void akfk(View view) {
        startActivity(new Intent(this, ProfileActivity.class));
    }
}