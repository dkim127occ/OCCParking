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

public class LotDDetailsActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private LatLng lotPosition;
    private ParkingLot lot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lot_d_details);

        SupportMapFragment lotDMapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.lotDMapFragment);

        lot = getIntent().getParcelableExtra(ParkingLot.TAG);

        TextView lotDTextView = (TextView) findViewById(R.id.lotDTextView);
        TextView lotDFreeTextView = (TextView) findViewById(R.id.lotDFreeTextView);
        TextView lotDOccupiedTextView = (TextView) findViewById(R.id.lotDOccupiedTextView);
        TextView lotDCapacityTextView = (TextView) findViewById(R.id.lotDCapacityTextView);

        double percentFilled = lot.getFilled() * 100.0 / lot.getCapacity();

        lotDTextView.setText(getString(R.string.parking_d_fmt, percentFilled));
        lotDFreeTextView.setText(getString(R.string.free_fmt, lot.getFree()));
        lotDOccupiedTextView.setText(getString(R.string.occupied_fmt, lot.getFilled()));
        lotDCapacityTextView.setText(getString(R.string.capacity_fmt, lot.getCapacity()));

        lotDMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Change the camera view to our current position:
        mMap = googleMap;

        lotPosition = new LatLng(33.667813, -117.910742); // Lot D

        CameraPosition cameraPosition = new CameraPosition.Builder().target(lotPosition).zoom(17.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition((cameraPosition));
        mMap.moveCamera(cameraUpdate);

        // Add marker to lot
        mMap.addMarker(new MarkerOptions().position(lotPosition).title(getString(R.string.lot_d)));
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
        intent.putExtra("image_id", R.drawable.lot_d);
        startActivity(intent);
    }
}
