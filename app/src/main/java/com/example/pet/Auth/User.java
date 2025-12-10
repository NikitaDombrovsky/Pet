package com.example.pet.Auth;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class Pet {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String email;
    public String petName;

    public User(String username, String petName) {
        this.username = username;
        this.petName = petName;
    }
}