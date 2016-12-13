package edu.orangecoastcollege.cs273.dkim127.occparking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LotAdamsDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng lotPosition;
    private ParkingLot lot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lot_adams_detail);

        SupportMapFragment lotAdamsMapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.lotAdamsMapFragment);

        lot = getIntent().getParcelableExtra(ParkingLot.TAG);

        TextView adamsTextView = (TextView) findViewById(R.id.adamsDetailTextView);
        TextView adamsFreeTextView = (TextView) findViewById(R.id.adamsFreeTextView);
        TextView adamsOccupiedTextView = (TextView) findViewById(R.id.adamsOccupiedTextView);
        TextView adamsCapacityTextView = (TextView) findViewById(R.id.adamsCapacityTextView);

        double percentFilled = lot.getFilled() * 100.0 / lot.getCapacity();

        adamsTextView.setText(getString(R.string.parking_adams_fmt, percentFilled));
        adamsFreeTextView.setText(getString(R.string.free_fmt, lot.getFree()));
        adamsOccupiedTextView.setText(getString(R.string.occupied_fmt, lot.getFilled()));
        adamsCapacityTextView.setText(getString(R.string.capacity_fmt, lot.getCapacity()));

        lotAdamsMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // change the camera view to the current location
        mMap = googleMap;

        lotPosition = new LatLng(33.672789, -117.912411); // Adams Lot
        CameraPosition cameraPosition = new CameraPosition.Builder().target(lotPosition).zoom(17.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);

        // Add marker to lot
        mMap.addMarker(new MarkerOptions().position(lotPosition).title(getString(R.string.lot_adams)));

    }

    public void findSpace(View viw)
    {
        Intent intent = new Intent(this, FindSpaceActivity.class);

        intent.putExtra("lotPosition", lotPosition);
        intent.putExtra(ParkingLot.TAG, lot);
        startActivity(intent);
    }

    public void viewStats(View view)
    {
        Intent intent = new Intent(this, StatisticsActivity.class);
        intent.putExtra(ParkingLot.TAG, lot);
        intent.putExtra("image_id", R.drawable.adams);
        startActivity(intent);
    }
}
