package com.example.pet.OnBoadring;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pet.Auth.LoginActivity;
import com.example.pet.Auth.UserRepository;
import com.example.pet.Daily.ProfileActivity;
import com.example.pet.R;

public class MainActivity extends AppCompatActivity {

    UserRepository userRepository;
    Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        try {
            userRepository = new UserRepository(this);


            if (userRepository.isLoggedIn()) {
                startActivity(new Intent(this, ProfileActivity.class));

            } else {
                setContentView(R.layout.activity_main);
                btnStart = findViewById(R.id.btnStart);
                btnStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    }
                });

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}