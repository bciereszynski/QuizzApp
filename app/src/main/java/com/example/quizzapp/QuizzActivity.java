package com.example.quizzapp;

import static android.view.View.GONE;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.quizzapp.models.quizz.IQuizz;

public class QuizzActivity extends AppCompatActivity {

    private IQuizz quizz;
    private FragmentManager fragmentManager;
    private FragmentQuizz fragmentq;
    private Button next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        //binding
        next = findViewById(R.id.quizz_button);
        fragmentq = new FragmentQuizz();
        Fragment fragment = new FragmentSettings();


        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .attach(fragmentq)
                .add(R.id.fragment_container, fragment)
                .commit();

        next.setOnClickListener(v -> {
            quizz = ((FragmentSettings) fragment).getQuizz();

            fragmentq.setQuizz(quizz);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragmentq)
                    .commit();
            next.setVisibility(GONE);
        });
    }
}
