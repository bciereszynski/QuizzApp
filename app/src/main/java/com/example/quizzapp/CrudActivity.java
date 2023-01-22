package com.example.quizzapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizzapp.database.EnglishWord;
import com.example.quizzapp.database.EnglishWordViewModel;
import com.example.quizzapp.database.PolishWord;
import com.example.quizzapp.database.PolishWordViewModel;
import com.example.quizzapp.database.Word;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CrudActivity extends AppCompatActivity {

    private Word editPolishWord;
    private Word editEnglishWord;

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_POLISH_ACTIVITY_REQUEST_CODE = 2;
    public static final int EDIT_ENGLISH_ACTIVITY_REQUEST_CODE = 3;
    public static final String EXTRA_WORD = "pb.edu.pl.EXTRA_WORD";
    public static final String EXTRA_TRANSLATION = "pb.edu.pl.EXTRA_TRANSLATION";
    public static final String EXTRA_REQUEST_CODE = "pb.edu.pl.REQUEST_CODE";


    PolishWordViewModel polishWordViewModel;
    EnglishWordViewModel englishWordViewModel;

    RecyclerView recycleViewPl;
    RecyclerView recycleViewEn;
    FloatingActionButton addButton;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            String wordOut = data.getStringExtra(CrudActivity.EXTRA_WORD);
            String translationOut = data.getStringExtra(CrudActivity.EXTRA_TRANSLATION);
            PolishWord polishWordToAdd = new PolishWord(wordOut, translationOut);
            polishWordViewModel.insert(polishWordToAdd);
        }
        if (resultCode == 3) {
            String wordOut = data.getStringExtra(CrudActivity.EXTRA_WORD);
            String translationOut = data.getStringExtra(CrudActivity.EXTRA_TRANSLATION);
            EnglishWord englishWordToAdd = new EnglishWord(wordOut, translationOut);
            englishWordViewModel.insert(englishWordToAdd);
        }
        if (resultCode == 4) {
            String wordOut = data.getStringExtra(CrudActivity.EXTRA_WORD);
            String translationOut = data.getStringExtra(CrudActivity.EXTRA_TRANSLATION);
            editPolishWord.setContent(wordOut);
            editPolishWord.setTranslation(translationOut);
            polishWordViewModel.update((PolishWord) editPolishWord);
            editPolishWord = null;
        }
        if (resultCode == 5) {
            String wordOut = data.getStringExtra(CrudActivity.EXTRA_WORD);
            String translationOut = data.getStringExtra(CrudActivity.EXTRA_TRANSLATION);
            editEnglishWord.setContent(wordOut);
            editEnglishWord.setTranslation(translationOut);
            englishWordViewModel.update((EnglishWord) editEnglishWord);
            editEnglishWord = null;
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        polishWordViewModel = new ViewModelProvider(this).get(PolishWordViewModel.class);
        englishWordViewModel = new ViewModelProvider(this).get(EnglishWordViewModel.class);

        setContentView(R.layout.activity_crud);
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(CrudActivity.this, AddWordActivity.class);
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);

        });

        recycleViewPl = findViewById(R.id.recyclerViewPl);
        final AdapterPl adapterPl = new AdapterPl();
        recycleViewPl.setAdapter(adapterPl);
        recycleViewPl.setLayoutManager(new LinearLayoutManager(this));
        polishWordViewModel.findAll().observe(this, adapterPl::setPolishWordList);

        recycleViewEn = findViewById(R.id.recyclerViewEn);
        final AdapterEn adapterEn = new AdapterEn();
        recycleViewEn.setAdapter(adapterEn);
        recycleViewEn.setLayoutManager(new LinearLayoutManager(this));
        englishWordViewModel.findAll().observe(this, adapterEn::setEnglishWordList);
    }

    public class AdapterPl extends RecyclerView.Adapter<AdapterPl.CrudViewHolder> {

        private List<PolishWord> polishWordList;

        @NonNull
        @Override
        public CrudViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CrudViewHolder(getLayoutInflater(), parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CrudViewHolder holder, int position) {
            if (polishWordList != null) {
                PolishWord polishWord = polishWordList.get(position);
                holder.bind(polishWord);
            }
        }

        @Override
        public int getItemCount() {
            if (polishWordList != null) {
                return polishWordList.size();
            } else {
                return 0;
            }
        }

        private class CrudViewHolder extends RecyclerView.ViewHolder {
            private final TextView wordContent;
            private final TextView wordTranslation;
            private final Button deleteButton;
            private final Button editButton;

            public CrudViewHolder(LayoutInflater layoutInflater, ViewGroup parent) {
                super(layoutInflater.inflate(R.layout.word_list_item, parent, false));
                wordContent = itemView.findViewById(R.id.word);
                wordTranslation = itemView.findViewById(R.id.translation);
                deleteButton = itemView.findViewById(R.id.deleteButton);
                editButton = itemView.findViewById(R.id.editButton);
            }

            public void bind(PolishWord polishWord) {
                wordContent.setText(polishWord.getContent());
                wordTranslation.setText(polishWord.getTranslation());
                deleteButton.setOnClickListener(v -> polishWordViewModel.delete(polishWord));
                editButton.setOnClickListener(v -> {

                    editPolishWord = polishWord;
                    Intent intent = new Intent(CrudActivity.this, EditWordActivity.class);
                    intent.putExtra(EXTRA_WORD, polishWord.getContent());
                    intent.putExtra(EXTRA_TRANSLATION, polishWord.getTranslation());
                    intent.putExtra(EXTRA_REQUEST_CODE, EDIT_POLISH_ACTIVITY_REQUEST_CODE);
                    startActivityForResult(intent, EDIT_POLISH_ACTIVITY_REQUEST_CODE);


                });
            }
        }

        void setPolishWordList(List<PolishWord> polishWordList) {
            this.polishWordList = polishWordList;
            notifyDataSetChanged();
        }

    }

    public class AdapterEn extends RecyclerView.Adapter<AdapterEn.CrudViewHolder> {

        private List<EnglishWord> englishWordList;

        @NonNull
        @Override
        public CrudViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CrudViewHolder(getLayoutInflater(), parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CrudViewHolder holder, int position) {
            if (englishWordList != null) {
                EnglishWord englishWord = englishWordList.get(position);
                holder.bind(englishWord);
            }
        }

        @Override
        public int getItemCount() {
            if (englishWordList != null) {
                return englishWordList.size();
            } else {
                return 0;
            }
        }

        private class CrudViewHolder extends RecyclerView.ViewHolder {
            private final TextView wordContext;
            private final TextView wordTranslation;
            private final Button deleteButton;
            private final Button editButton;

            public CrudViewHolder(LayoutInflater layoutInflater, ViewGroup parent) {
                super(layoutInflater.inflate(R.layout.word_list_item, parent, false));
                wordContext = itemView.findViewById(R.id.word);
                wordTranslation = itemView.findViewById(R.id.translation);
                deleteButton = itemView.findViewById(R.id.deleteButton);
                editButton = itemView.findViewById(R.id.editButton);
            }

            public void bind(EnglishWord englishWord) {
                wordContext.setText(englishWord.getContent());
                wordTranslation.setText(englishWord.getTranslation());
                deleteButton.setOnClickListener(v ->

                        englishWordViewModel.delete(englishWord)

                );
                editButton.setOnClickListener(v -> {

                    editEnglishWord = englishWord;
                    Intent intent = new Intent(CrudActivity.this, EditWordActivity.class);
                    intent.putExtra(EXTRA_WORD, englishWord.getContent());
                    intent.putExtra(EXTRA_TRANSLATION, englishWord.getTranslation());
                    intent.putExtra(EXTRA_REQUEST_CODE, EDIT_ENGLISH_ACTIVITY_REQUEST_CODE);
                    startActivityForResult(intent, EDIT_ENGLISH_ACTIVITY_REQUEST_CODE);

                });


            }
        }

        void setEnglishWordList(List<EnglishWord> englishWord) {
            this.englishWordList = englishWord;
            notifyDataSetChanged();
        }

    }

}