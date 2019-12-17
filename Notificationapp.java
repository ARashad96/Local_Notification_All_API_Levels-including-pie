package com.arashad96.androiddeveloperintermidatekit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Notificationapp extends AppCompatActivity {
    Button github;
    Button info;
    Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_notification);

        click = findViewById(R.id.click);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check for the os version
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, 0);

                    Notification notification = new Notification.Builder(getApplicationContext(), "channel_1")
                            .setContentTitle("I am notification")
                            .setContentText("Here is the text of my notification")
                            .setContentIntent(pendingIntent)
                            //.addAction(android.R.drawable.sym_action_chat,"Chat",pendingIntent)
                            .setSmallIcon(R.drawable.profile)
                            .build();

                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                    NotificationChannel channel = new NotificationChannel("channel_1", "Channel for my notification", NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(channel);

                    notificationManager.notify(1, notification);
                } else {
                    Intent notificationIntent = new Intent(Intent.ACTION_VIEW);
                    PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, 0);

                    android.app.Notification notification1 = new NotificationCompat.Builder(getApplicationContext())
                            .setContentTitle("I am old API")
                            .setAutoCancel(true)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                            .addAction(android.R.drawable.ic_menu_view, "View details", contentIntent)
                            .setContentIntent(contentIntent)
                            .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000}).build();
                    NotificationManager notificationManager =
                            (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.notify(1, notification1);
                }

            }
        });
        github = findViewById(R.id.github);
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ARashad96/Local_Notification_All_API_Levels-including-pie"));
                startActivity(intent);
            }
        });
        info = findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new android.app.AlertDialog.Builder(Notificationapp.this)
                        .setIcon(R.drawable.profile)
                        .setTitle("App info")
                        .setMessage("This app is a simple notification using notificationmanager.")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
            }
        });
    }
}
