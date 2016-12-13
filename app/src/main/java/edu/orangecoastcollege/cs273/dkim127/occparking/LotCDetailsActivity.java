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

public class LotCDetailsActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private LatLng lotPosition;
    private ParkingLot lot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lot_c_details);

        SupportMapFragment lotCMapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.lotCMapFragment);

        lotCMapFragment.getMapAsync(this);

        lot = getIntent().getParcelableExtra(ParkingLot.TAG);

        TextView lotCTextView = (TextView) findViewById(R.id.lotCDetailTextView);
        TextView lotCFreeTextView = (TextView) findViewById(R.id.lotCFreeTextView);
        TextView lotCOccupiedTextView = (TextView) findViewById(R.id.lotCOccupiedTextView);
        TextView lotCCapacityTextView = (TextView) findViewById(R.id.lotCCapacityTextView);

        double percentFilled = lot.getFilled() * 100.0 / lot.getCapacity();

        lotCTextView.setText(getString(R.string.parking_c_fmt, percentFilled));
        lotCFreeTextView.setText(getString(R.string.free_fmt, lot.getFree()));
        lotCOccupiedTextView.setText(getString(R.string.occupied_fmt, lot.getFilled()));
        lotCCapacityTextView.setText(getString(R.string.capacity_fmt, lot.getCapacity()));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Change the camera view to our current position:
        mMap = googleMap;

        lotPosition = new LatLng(33.668098, -117.908650); // Lot C


        CameraPosition cameraPosition = new CameraPosition.Builder().target(lotPosition).zoom(17.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition((cameraPosition));
        mMap.moveCamera(cameraUpdate);

        // Add marker to lot
        mMap.addMarker(new MarkerOptions().position(lotPosition).title(getString(R.string.lot_c)));
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
        intent.putExtra("image_id", R.drawable.lot_c);
        startActivity(intent);
    }
}
