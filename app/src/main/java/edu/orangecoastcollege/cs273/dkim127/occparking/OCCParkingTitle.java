package edu.orangecoastcollege.cs273.dkim127.occparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class OCCParkingTitle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_occparking_title);

        TimerTask mapTask = new TimerTask() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(OCCParkingTitle.this, MainActivity.class));
            }
        };

        Timer timer = new Timer();
        timer.schedule(mapTask, 2000);
    }
}

