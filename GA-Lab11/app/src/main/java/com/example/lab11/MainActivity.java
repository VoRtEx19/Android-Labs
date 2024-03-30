package com.example.lab11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(view -> {

            Context context = MainActivity.this;
            NotificationChannel newnotchan= null;
            if (Build.VERSION.SDK_INT >=
                    Build.VERSION_CODES.O) {
                newnotchan= new
                        NotificationChannel("mychannel1","mychannel",NotificationManager
                        .IMPORTANCE_HIGH);
                AudioAttributes audioAttributes = new
                        AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build();

                newnotchan.setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                                +"://"+getPackageName()+"/"+R.raw.notify),
                        audioAttributes);
            }
            NotificationManager notificationManager = (NotificationManager)
                    getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(newnotchan);
            }
            Notification notification = null;
            if (Build.VERSION.SDK_INT >=
                    Build.VERSION_CODES.O) {
                notification = new
                        Notification.Builder(context,"mychannel1")
                        .setContentTitle("Новое сообщение!")
                        .setContentText("Приветствую!")
                        .setTicker("Новое сообщение")
                        .setChannelId("mychannel1")
                        .setSmallIcon(android.R.drawable.ic_dialog_alert)
                        .setOngoing(true)
                        .setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                                +"://"+getPackageName()+"/"+R.raw.notify))
                        .build();
            }
            else
            {
                context = MainActivity.this;
                notification = new Notification.Builder(context)
                        .setContentTitle("Новое сообщение!")
                        .setContentText("Приветствую!")
                        .setTicker("Новое сообщение")
                        .setSmallIcon(android.R.drawable.ic_dialog_alert)

                        .setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                                +"://"+getPackageName()+"/"+R.raw.notify))
                        .build();
                notificationManager = (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);


            }
            notificationManager.notify(0, notification);
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        try {
            dialog.setMessage(getTitle().toString() + " ver. " +
                    getPackageManager().getPackageInfo(getPackageName(), 0).versionName +
                    "\r\n\nУведомление:" +
                    "\r\n\nАвтор - Светличный Лев БПИ225");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        dialog.setTitle("О программе");
        dialog.setNeutralButton("ОК", (dialog1, i) -> dialog1.dismiss());
        dialog.setIcon(R.mipmap.ic_launcher_round);
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
        return super.onOptionsItemSelected(item);
    }
}