package com.example.quizzapp;

import androidx.annotation.NonNull;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzapp.database.PolishWord;
import com.example.quizzapp.database.PolishWordViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CrudActivity extends AppCompatActivity {

    RecyclerView recycleViewPl;
    RecyclerView recycleViewEn;
    FloatingActionButton addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PolishWordViewModel polishWordViewModel;
        polishWordViewModel = new ViewModelProvider(this).get(PolishWordViewModel.class);

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
        final Adapter adapter = new Adapter(this.getApplicationContext());
        recycleViewPl.setAdapter(adapter);
        recycleViewPl.setLayoutManager(new LinearLayoutManager(this));
        polishWordViewModel.findAll().observe(this,adapter::setPolishWordList);

        recycleViewEn = findViewById(R.id.recyclerViewEn);
        final Adapter adapter2 = new Adapter(this.getApplicationContext());
        recycleViewEn.setAdapter(adapter);
        recycleViewPl.setLayoutManager(new LinearLayoutManager(this));
        polishWordViewModel.findAll().observe(this,adapter::setPolishWordList);
    }
    public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

        private List<PolishWord> polishWordList;
        Context context;
        public Adapter(Context ct){
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
            public MyViewHolder(LayoutInflater layoutInflater, ViewGroup parent) {
                super(layoutInflater.inflate(R.layout.table_row,parent,false));
                Text1 = itemView.findViewById(R.id.translation);
                Text2 = itemView.findViewById(R.id.word);
            }
            public void bind(PolishWord polishWord){
                Text1.setText(polishWord.getContent());
                Text2.setText(polishWord.getTranslation());
            }
        }
        void setPolishWordList(List<PolishWord> polishWordList){
            this.polishWordList = polishWordList;
            notifyDataSetChanged();
        }

    }

}