package com.example.quizzapp;

import static android.view.View.GONE;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.quizzapp.models.quizz.IQuizz;

public class QuizzActivity extends AppCompatActivity {

    private IQuizz quizz;
    private FragmentManager fragmentManager;
    private FragmentQuizz fragmentq;
    private Button next;
    private NotificationManagerCompat notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        //Notification
        String CHANNEL_ID = "5";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notification";
            String description = "Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setContentTitle("QuizzApp")
                .setContentText("W trakcie quizzu...")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());

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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        notificationManager.cancel(1);
    }
}
