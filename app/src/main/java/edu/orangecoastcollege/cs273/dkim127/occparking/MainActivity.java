package edu.orangecoastcollege.cs273.dkim127.occparking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView lotAdamsTextView;
    private TextView lotATextView;
    private TextView lotBTextView;
    private TextView lotCTextView;
    private TextView lotDTextView;
    private TextView lotETextView;
    private TextView lotGTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // link views with controls
        lotAdamsTextView = (TextView) findViewById(R.id.lotAdamsTextView);
        lotATextView = (TextView) findViewById(R.id.lotATextView);
        lotBTextView = (TextView) findViewById(R.id.lotBTextView);
    }
}
