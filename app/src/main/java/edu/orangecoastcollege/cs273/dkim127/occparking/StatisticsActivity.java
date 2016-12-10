package edu.orangecoastcollege.cs273.dkim127.occparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Intent intent = getIntent();

        ParkingLot lot = intent.getParcelableExtra(ParkingLot.TAG);
        int imageId = intent.getIntExtra("image_id", R.mipmap.ic_launcher);

        ImageView statsImageView = (ImageView) findViewById(R.id.statsImageView);
        TextView statsTitleTextView = (TextView) findViewById(R.id.statsTitleTextView);
        TextView statsCapacityTextView = (TextView) findViewById(R.id.statsCapacityTextView);
        TextView statsOccupiedTextView = (TextView) findViewById(R.id.statsOccupiedTextView);
        TextView statsFreeTextView = (TextView) findViewById(R.id.statsFreeTextView);
        TextView statsFilledTextView = (TextView) findViewById(R.id.statsFilledTextView);

        statsTitleTextView.setText(getString(R.string.lot_stats, lot.getName().substring(lot.getName().indexOf('_') + 1)));
        statsCapacityTextView.setText(getString(R.string.capacity_fmt, lot.getCapacity()));
        statsOccupiedTextView.setText(getString(R.string.avg_filled_fmt, lot.getAvgFilled()));
        statsFreeTextView.setText(getString(R.string.avg_free_fmt, lot.getCapacity() - lot.getAvgFilled()));
        statsFilledTextView.setText(getString(R.string.avg_filled_fmt, lot.getAvgFilled() / lot.getCapacity()));
    }
}
