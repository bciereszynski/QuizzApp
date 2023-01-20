package com.example.quizzapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.quizzapp.models.difficulty.Hard;
import com.example.quizzapp.models.difficulty.Medium;
import com.example.quizzapp.models.mode.Learn;
import com.example.quizzapp.models.mode.Test;
import com.example.quizzapp.models.quizz.EN_PL_Quizz;
import com.example.quizzapp.models.quizz.PL_EN_Quizz;
import com.example.quizzapp.models.quizz.Quizz;

public class FragmentSettings extends Fragment implements AdapterView.OnItemSelectedListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        Spinner spinnerMode = view.findViewById(R.id.spinnerSettingsMode);
        ArrayAdapter<CharSequence> adaptermode = ArrayAdapter.createFromResource(getActivity(),R.array.modeChoice, android.R.layout.simple_spinner_item);
        adaptermode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMode.setAdapter(adaptermode);
        spinnerMode.setOnItemSelectedListener(this);

        Spinner spinnerPLEN = view.findViewById(R.id.spinnerSettingsPLEN);
        ArrayAdapter<CharSequence> adapterPLEN = ArrayAdapter.createFromResource(getActivity(),R.array.translationChoice, android.R.layout.simple_spinner_item);
        adapterPLEN.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPLEN.setAdapter(adapterPLEN);
        spinnerPLEN.setOnItemSelectedListener(this);


        Spinner spinnerDifficulty = view.findViewById(R.id.spinnerSettingsDifficluty);
        ArrayAdapter<CharSequence> adapterDifficulty = ArrayAdapter.createFromResource(getActivity(),R.array.difficultyChoice, android.R.layout.simple_spinner_item);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(adapterDifficulty);
        spinnerDifficulty.setOnItemSelectedListener(this);

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

    }

    public Quizz getQuizz(){
        Quizz quizz = new EN_PL_Quizz();
        quizz.setDifficulty(new Hard());
        quizz.setMode(new Learn());
        return quizz;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}