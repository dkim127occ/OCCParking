package edu.orangecoastcollege.cs273.dkim127.occparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LotCDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lot_c_details);
        Intent intent = getIntent();
        ParkingLot lot =  intent.getParcelableExtra(ParkingLot.TAG);

        TextView lotCDetailTextView = (TextView) findViewById(R.id.lotCDetailTextView);
        lotCDetailTextView.setText(getString(R.string.parking_c_fmt, lot.getFilled() * 100.0 / lot.getCapacity()));

        TextView lotCFreeTextView = (TextView) findViewById(R.id.lotCFreeTextView);
        lotCFreeTextView.setText(getString(R.string.free_fmt, lot.getCapacity() - lot.getFilled()));

        TextView lotCOccupiedTextView = (TextView) findViewById(R.id.lotCOccupiedTextView);
        lotCOccupiedTextView.setText(getString(R.string.occupied_fmt, lot.getFilled()));

        TextView lotCCapacityTextView = (TextView) findViewById(R.id.lotCCapacityTextView);
        lotCCapacityTextView.setText(getString(R.string.capacity_fmt, lot.getCapacity()));
    }
}
