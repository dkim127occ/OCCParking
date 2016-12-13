package edu.orangecoastcollege.cs273.dkim127.occparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.stats.StatsEvent;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class LotADetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng lotPosition;
    private ParkingLot lot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lot_adetails);

        SupportMapFragment lotAMapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.lotAMapFragment);
        lot = getIntent().getParcelableExtra(ParkingLot.TAG);

        TextView lotATextView = (TextView) findViewById(R.id.lotATextView);
        TextView lotAFreeTextView = (TextView) findViewById(R.id.lotAFreeTextView);
        TextView lotAOccupiedTextView = (TextView) findViewById(R.id.lotAOccupiedTextView);
        TextView lotACapacityTextView = (TextView) findViewById(R.id.lotACapacityTextView);

        double percentFilled = lot.getFilled() * 100.0 / lot.getCapacity();

        lotATextView.setText(getString(R.string.parking_a_fmt, percentFilled));
        lotAFreeTextView.setText(getString(R.string.free_fmt, lot.getFree()));
        lotAOccupiedTextView.setText(getString(R.string.occupied_fmt, lot.getFilled()));
        lotACapacityTextView.setText(getString(R.string.capacity_fmt, lot.getCapacity()));

        lotAMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Change the camera view to our current position:
        mMap = googleMap;

        lotPosition = new LatLng(33.670884, -117.908454); // Lot A

        CameraPosition cameraPosition = new CameraPosition.Builder().target(lotPosition).zoom(17.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition((cameraPosition));
        mMap.moveCamera(cameraUpdate);

        // Add marker to lot
        mMap.addMarker(new MarkerOptions().position(lotPosition).title(getString(R.string.lot_a)));
    }

    public void findSpace(View view)
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
        intent.putExtra("image_id", R.drawable.lot_a);
        startActivity(intent);
    }
}
