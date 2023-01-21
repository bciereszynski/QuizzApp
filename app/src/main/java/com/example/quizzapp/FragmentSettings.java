package com.example.quizzapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.quizzapp.models.difficulty.Difficulty;
import com.example.quizzapp.models.difficulty.Hard;
import com.example.quizzapp.models.difficulty.Medium;
import com.example.quizzapp.models.quizz.mode.Learn;
import com.example.quizzapp.models.quizz.mode.Mode;
import com.example.quizzapp.models.quizz.mode.Test;
import com.example.quizzapp.models.quizz.EN_PL_Quizz;
import com.example.quizzapp.models.quizz.PL_EN_Quizz;
import com.example.quizzapp.models.quizz.Quizz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentSettings extends Fragment {
    private List<Difficulty> difficulties = new ArrayList<Difficulty>(Arrays.asList(new Medium(), new Hard()));
    private List<Quizz> quizzes = new ArrayList<Quizz>(Arrays.asList(new PL_EN_Quizz(), new EN_PL_Quizz()));
    private List<Mode> modes = new ArrayList<>(Arrays.asList(new Test(), new Learn()));
    private Spinner spinnerDifficulty;
    private Spinner spinnerLang;
    private Spinner spinnerMode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        spinnerMode = view.findViewById(R.id.spinnerSettingsMode);
        ArrayAdapter<Mode> adapterMode = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, modes);
        adapterMode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMode.setAdapter(adapterMode);

        spinnerLang = view.findViewById(R.id.spinnerSettingsPLEN);
        ArrayAdapter<Quizz> adapterLang = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, quizzes);
        adapterLang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLang.setAdapter(adapterLang);


        spinnerDifficulty = view.findViewById(R.id.spinnerSettingsDifficluty);
        ArrayAdapter<Difficulty> adapterDifficulty = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, difficulties);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(adapterDifficulty);

        return view;
    }


    public Quizz getQuizz() {
        Quizz quizz = (Quizz) spinnerLang.getSelectedItem();
        quizz.setDifficulty((Difficulty) spinnerDifficulty.getSelectedItem());
        quizz.setMode((Mode) spinnerMode.getSelectedItem());
        return quizz;
    }

}