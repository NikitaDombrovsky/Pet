package com.example.pet.Daily;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pet.Booking.BookingActivity;
import com.example.pet.R;
import com.example.pet.Settings;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ProfileActivity extends AppCompatActivity {


    String today = LocalDate.now().toString();
    BottomNavigationView navigation;
    TextView PetName;
    TextView Gender;
    TextView Type;
    TextView Age;
    TextView Weight;
    TextView ProfileName;
    TextView Breed;
    TextView firstTask;
    TextView secondTask;
    TextView thirdTask;
    CheckBox FirstCheck;
    CheckBox SecondCheck;
    CheckBox ThirdCheck;
    TextView Completed;

    private static final String PREFS_FILE = "Account"; // Название "Базы данных" (Поменяешь и все сотрется)
    private static final String PREF_NAME = "Name"; // Название поля в БД, например Имя
    private static final String PREF_MAIL = "Mail"; // Название поля в БД, например Имя
    private static final String PREF_PASS = "Pass"; // Название поля в БД, например Имя
    private static final String PREF_TYPE = "Type"; // Название поля в БД, например Имя
    private static final String PREF_GENDER = "Gender"; // Название поля в БД, например Имя
    private static final String PREF_AGE = "Age"; // Название поля в БД, например Имя
    private static final String PREF_COLOUR = "Colour"; // Название поля в БД, например Имя

    private static final String PREF_WEIGHT = "Weight"; // Название поля в БД, например Имя
    private static final String PREF_PROFILE = "NameProfile";
    SharedPreferences settings;

    GoalsRepository repository;
    Executor executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        settings = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);

        repository = new GoalsRepository(this);

        executor = Executors.newSingleThreadExecutor();

        navigation = findViewById(R.id.mainNavigation);
        PetName = findViewById(R.id.Name);
        Gender = findViewById(R.id.Gender);
        Type = findViewById(R.id.Type);
        Age = findViewById(R.id.Age);
        Weight = findViewById(R.id.Weight);
        ProfileName = findViewById(R.id.profileTitle);
        firstTask = findViewById(R.id.eattask);
        secondTask = findViewById(R.id.walktask);
        thirdTask = findViewById(R.id.caretask);
        FirstCheck = findViewById(R.id.checkSnack);
        SecondCheck = findViewById(R.id.checkWalk);
        ThirdCheck = findViewById(R.id.checkBath);
        Completed = findViewById(R.id.Comleted);


        // получаем сохраненное имя
        navigation = findViewById(R.id.mainNavigation);
        PetName = findViewById(R.id.Name);
        Gender = findViewById(R.id.Gender);
        Type = findViewById(R.id.Type);
        Age = findViewById(R.id.Age);
        Weight = findViewById(R.id.Weight);
        ProfileName = findViewById(R.id.profileTitle);
        Breed = findViewById(R.id.Breed);


        String petName = settings.getString(PREF_NAME, "не определено");
        String gender = settings.getString(PREF_GENDER, "не определено");
        String type = settings.getString(PREF_TYPE, "не определено");
        String age = settings.getString(PREF_AGE, "не определено");
        String weight = settings.getString(PREF_WEIGHT, "не определено");
        String breed = settings.getString(PREF_COLOUR, "не определено");
        // PREF_NAME - наше название поля в БД; второе - значение "по умолчанию" если данных нет
        PetName.setText(petName);
        Gender.setText(gender);
        Type.setText(type);
        Age.setText(age);
        Weight.setText(weight);
        ProfileName.setText(petName);
        Breed.setText(breed);

        showTasks(type);


        FirstCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton compoundButton, boolean b) {
                if (b) {
                    Toast.makeText(getApplicationContext(), "+++", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "---", Toast.LENGTH_SHORT).show();
                }

            }


        });


        navigation.setOnItemSelectedListener(menuItem -> {
            if (menuItem.getTitle().equals("Профиль")) {
                startActivity(new Intent(this, ProfileActivity.class));
            }
            if (menuItem.getTitle().equals("Календарь")) {
                startActivity(new Intent(this, BookingActivity.class));
            }


            return true;
        });



/*            switch (menuItem.getTitle()){
                case "":
                    startActivity(new Intent(this,LoginActivity.class));
                    break;

            }*/
        // Загрузка задач
        executor.execute(() -> {
            List<DailyTask> tasks = repository.loadOrCreateDailyTasks(today);

            runOnUiThread(() -> showTasks(type));
        });


// Обновление стрика (вызываешь при запуске или при открытии экрана целей)
        executor.execute(() -> {
            repository.updateStreakForToday(today);
        });

    }

    private void showTasks(String type) {
        if (type.equals("Кошка")) {
            firstTask.setText("Охотничья прогулка \uD83C\uDFA3");
            secondTask.setText("Пищевая головоломка \uD83E\uDDE0");
            thirdTask.setText("Сеанс груминга \uD83D\uDEC1");
        }
        if (type.equals("Собака")) {
            firstTask.setText("Прогулка с «делом» \uD83D\uDDFA\uFE0F");
            secondTask.setText("Обед не из миски  \uD83E\uDDB4");
            thirdTask.setText("Вечерний уход \uD83D\uDEC1");
        }
        if (type.equals("Грызуны")) {
            firstTask.setText("Прогулка-исследование \uD83D\uDD75\uFE0F");
            secondTask.setText("Свежий ужин  \uD83E\uDD52");
            thirdTask.setText("Чистка и купание \uD83C\uDFDC\uFE0F");
        }
        if (type.equals("Рыбки")) {
            firstTask.setText("Смена обстановки \uD83C\uDF19");
            secondTask.setText("Кормление-развлечение \uD83C\uDFAA");
            thirdTask.setText("Чистая «ванна» \uD83D\uDCA7");
        }
        if (type.equals("Птицы")) {
            firstTask.setText("Прогулка по комнате ✈\uFE0F");
            secondTask.setText("Разнообразный стол  \uD83C\uDF4E");
            thirdTask.setText("Водные процедуры \uD83D\uDCA6");
        }
    }

    // Обновление задачи
//    void onTaskChecked(DailyTask task) {
//        task.done = !task.done;
//
//        executor.execute(() -> {
//            repository.updateTask(task);
//        });
//    }

    public void SetBut(View view) {
        startActivity(new Intent(this, Settings.class));

    }
}



