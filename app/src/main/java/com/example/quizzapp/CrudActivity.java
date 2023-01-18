package com.example.quizzapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzapp.database.EnglishWord;
import com.example.quizzapp.database.EnglishWordViewModel;
import com.example.quizzapp.database.PolishWord;
import com.example.quizzapp.database.PolishWordViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CrudActivity extends AppCompatActivity {

    public static final int NEW_BOOK_ACTIVITY_REQUEST_CODE = 1;

    RecyclerView recycleViewPl;
    RecyclerView recycleViewEn;
    FloatingActionButton addButton;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PolishWordViewModel polishWordViewModel;
        polishWordViewModel = new ViewModelProvider(this).get(PolishWordViewModel.class);

        EnglishWordViewModel englishWordViewModel;
        englishWordViewModel = new ViewModelProvider(this).get(EnglishWordViewModel.class);

        setContentView(R.layout.activity_crud);
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrudActivity.this,AddWordActivity.class);
                startActivity(intent);
            }
        });

        recycleViewPl = findViewById(R.id.recyclerViewPl);
        final AdapterPl adapterPl = new AdapterPl(this.getApplicationContext());
        recycleViewPl.setAdapter(adapterPl);
        recycleViewPl.setLayoutManager(new LinearLayoutManager(this));
        polishWordViewModel.findAll().observe(this,adapterPl::setPolishWordList);

        recycleViewEn = findViewById(R.id.recyclerViewEn);
        final AdapterEn adapterEn = new AdapterEn(this.getApplicationContext());
        recycleViewEn.setAdapter(adapterEn);
        recycleViewEn.setLayoutManager(new LinearLayoutManager(this));
        englishWordViewModel.findAll().observe(this,adapterEn::setEnglishWordList);
    }
    public class AdapterPl extends RecyclerView.Adapter<AdapterPl.MyViewHolder> {

        private List<PolishWord> polishWordList;
        Context context;
        public AdapterPl(Context ct){
            this.context = ct;
        }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(getLayoutInflater(),parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            if(polishWordList!=null){
                PolishWord polishWord = polishWordList.get(position);
                holder.bind(polishWord);
            }
            else Toast.makeText(context,"Nie ma co wyswietlic",Toast.LENGTH_SHORT).show();
        }

        @Override
        public int getItemCount() {
            if(polishWordList!=null){
                return polishWordList.size();
            }
            else {
                return 0;
            }
        }

        private class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView Text1,Text2;
            private Button deleteButton,editButton;
            public MyViewHolder(LayoutInflater layoutInflater, ViewGroup parent) {
                super(layoutInflater.inflate(R.layout.word_list_item,parent,false));
                Text1 = itemView.findViewById(R.id.translation);
                Text2 = itemView.findViewById(R.id.word);
                deleteButton = itemView.findViewById(R.id.deleteButton);
                editButton = itemView.findViewById(R.id.editButton);
            }
            public void bind(PolishWord polishWord){
                Text1.setText(polishWord.getContent());
                Text2.setText(polishWord.getTranslation());
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"Usun element "+polishWord.getContent().toString(),Toast.LENGTH_SHORT).show();
                    }
                });
                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"Edycja elementu "+polishWord.getContent().toString(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        void setPolishWordList(List<PolishWord> polishWordList){
            this.polishWordList = polishWordList;
            notifyDataSetChanged();
        }

    }
    public class AdapterEn extends RecyclerView.Adapter<AdapterEn.MyViewHolder> {

        private List<EnglishWord> englishWordList;
        Context context;
        public AdapterEn(Context ct){
            this.context = ct;
        }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(getLayoutInflater(),parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            if(englishWordList!=null){
                EnglishWord englishWord = englishWordList.get(position);
                holder.bind(englishWord);


            }
            else Toast.makeText(context,"Nie ma co wyswietlic",Toast.LENGTH_SHORT).show();
        }

        @Override
        public int getItemCount() {
            if(englishWordList!=null){
                return englishWordList.size();
            }
            else {
                return 0;
            }
        }

        private class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView Text1,Text2;
            private Button deleteButton,editButton;
            public MyViewHolder(LayoutInflater layoutInflater, ViewGroup parent) {
                super(layoutInflater.inflate(R.layout.word_list_item,parent,false));
                Text1 = itemView.findViewById(R.id.translation);
                Text2 = itemView.findViewById(R.id.word);
                deleteButton = itemView.findViewById(R.id.deleteButton);
                editButton = itemView.findViewById(R.id.editButton);
            }
            public void bind(EnglishWord englishWord){
                Text1.setText(englishWord.getContent());
                Text2.setText(englishWord.getTranslation());
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"Usun element "+englishWord.getContent().toString(),Toast.LENGTH_SHORT).show();
                    }
                });
                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"Edycja elementu "+englishWord.getContent().toString(),Toast.LENGTH_SHORT).show();
                    }
                });


            }
        }
        void setEnglishWordList(List<EnglishWord> englishWord){
            this.englishWordList = englishWord;
            notifyDataSetChanged();
        }

    }

}