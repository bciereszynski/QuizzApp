package com.example.quizzapp;

import static android.view.View.GONE;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzapp.models.difficulty.Hard;
import com.example.quizzapp.models.difficulty.Medium;
import com.example.quizzapp.models.mode.Test;
import com.example.quizzapp.models.quizz.EN_PL_Quizz;
import com.example.quizzapp.models.Observer;
import com.example.quizzapp.models.quizz.Quizz;

import java.util.ArrayList;
import java.util.List;

public class QuizzActivity extends AppCompatActivity {

    private Quizz quizz;
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
        //Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
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
