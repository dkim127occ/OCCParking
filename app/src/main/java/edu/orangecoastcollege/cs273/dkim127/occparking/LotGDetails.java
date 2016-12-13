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

public class LotGDetails extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng lotPosition;
    private ParkingLot lot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lot_g_details);

        SupportMapFragment lotGMapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.lotGMapFragment);

        lot = getIntent().getParcelableExtra(ParkingLot.TAG);

        TextView lotGTextView = (TextView) findViewById(R.id.lotGTextView);
        TextView lotGFreeTextView = (TextView) findViewById(R.id.lotGFreeTextView);
        TextView lotGOccupiedTextView = (TextView) findViewById(R.id.lotGOccupiedTextView);
        TextView lotGCapacityTextView = (TextView) findViewById(R.id.lotGCapacityTextView);

        double percentFilled = lot.getFilled() * 100.0 / lot.getCapacity();

        lotGTextView.setText(getString(R.string.parking_g_fmt, percentFilled));
        lotGFreeTextView.setText(getString(R.string.free_fmt, lot.getFree()));
        lotGOccupiedTextView.setText(getString(R.string.occupied_fmt, lot.getFilled()));
        lotGCapacityTextView.setText(getString(R.string.capacity_fmt, lot.getCapacity()));

        lotGMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Change the camera view to our current position:
        mMap = googleMap;

        lotPosition = new LatLng(33.673720, -117.908795); // Lot G

        CameraPosition cameraPosition = new CameraPosition.Builder().target(lotPosition).zoom(17.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition((cameraPosition));
        mMap.moveCamera(cameraUpdate);

        // Add marker to lot
        mMap.addMarker(new MarkerOptions().position(lotPosition).title(getString(R.string.lot_g)));
    }

    /**
     * opens the FindSpaceActivity when clicked.
     * @param view the View associated with the activity
     */
    public void findSpace(View view)
    {
        Intent intent = new Intent(this, FindSpaceActivity.class);

        intent.putExtra("lotPosition", lotPosition);
        intent.putExtra(ParkingLot.TAG, lot);
        startActivity(intent);
    }

    /**
     * opens the StatisticsActivity when clicked.
     * @param view the view associated with the activity.
     */
    public void viewStats(View view)
    {
        Intent intent = new Intent(this, StatisticsActivity.class);
        intent.putExtra(ParkingLot.TAG, lot);
        intent.putExtra("image_id", R.drawable.lot_g);
        startActivity(intent);
    }
}
