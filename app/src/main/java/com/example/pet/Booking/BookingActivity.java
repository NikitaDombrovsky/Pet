package com.example.pet.Booking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pet.Daily.ProfileActivity;
import com.example.pet.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BookingActivity extends AppCompatActivity {
    BottomNavigationView navigation;
    EditText Notes;
    Button btnBook;
    boolean state = true;
    CalendarView calendarView;

    String selectedDate;
    String inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_booking);

        Executor executor = Executors.newSingleThreadExecutor();
        EventRepository repository = new EventRepository(getApplicationContext());


        navigation = findViewById(R.id.mainNavigation);
        Notes = findViewById(R.id.notesInput);
        btnBook = findViewById(R.id.btnBook);
        calendarView = findViewById(R.id.calendarView);



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        selectedDate = sdf.format(new Date(calendarView.getDate()));
        Log.d("DATE1", selectedDate);

        changeDate(executor, repository);

        //selectedDate = calendarView.getDate()

        navigation.setOnItemSelectedListener(menuItem -> {
            if (menuItem.getTitle().equals("Профиль")) {
                startActivity(new Intent(this, ProfileActivity.class));
            }
            if (menuItem.getTitle().equals("Календарь")) {
                startActivity(new Intent(this, BookingActivity.class));
            }


            return true;
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                state = !state;
                changeEnable(state);
                if (!state && !Notes.getText().toString().isEmpty()) {
                    inputText = Notes.getText().toString();
                    //selectedDate = String.valueOf(calendarView.getDate());

                    // когда нажали "Сохранить"
                    executor.execute(() -> {
                        repository.saveEvent(selectedDate, inputText);
                    });
                }

            }
        });
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                selectedDate = String.format("%04d-%02d-%02d",
                        year, (month + 1), dayOfMonth);

                // Теперь formattedDate → "yyyy-MM-dd"
                Log.d("DATE", selectedDate);


                changeDate(executor, repository);




/*                String mockDate  = mockDate(i2);

                if(mockDate == null){
                    Notes.setText("");
                    changeEnable(true);
                }
                else {
                    Notes.setText(mockDate);
                    changeEnable(false);
                }*/


            }
        });
    }

    private void changeDate(Executor executor, EventRepository repository) {
        // когда выбрали дату
        executor.execute(() -> {
            Event e = repository.getEvent(selectedDate);
                runOnUiThread(() -> {
                    if (e == null || e.text.isEmpty()) {
                        Notes.setText("");
                        changeEnable(true);
                    } else {
                        Notes.setText(e.text);
                        changeEnable(false);
                    }
                });

        });
    }

    //    public String mockDate(int i2){
//        if (i2 % 2 == 0){
//            return "test";
//        }
//        else {
//            return null;
//        }
//    }
    public void changeEnable(Boolean state) {
        if (state) {
            Notes.setEnabled(true);
            btnBook.setText("Сохранить заметку");
        } else {
            Notes.setEnabled(false);
            btnBook.setText("Изменить заметку");
        }

    }
}