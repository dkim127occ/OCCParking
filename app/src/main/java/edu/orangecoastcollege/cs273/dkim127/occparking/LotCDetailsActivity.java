package edu.orangecoastcollege.cs273.dkim127.occparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lot_c_details);

        SupportMapFragment lotCMapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.lotCMapFragment);

        lotCMapFragment.getMapAsync(this);

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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Change the camera view to our current position:
        mMap = googleMap;

        //LatLng lotPosition = new LatLng(33.672789, -117.912411); // Adams Lot
        //LatLng lotPosition = new LatLng(33.670884, -117.908454); // Lot A
        //LatLng lotPosition = new LatLng(33.669554, -117.908283); // Lot B
        lotPosition = new LatLng(33.668098, -117.908650); // Lot C
        //LatLng lotPosition = new LatLng(33.667813, -117.910742); // Lot D
        //LatLng lotPosition = new LatLng(33.668529, -117.914318); // Lot E
        //LatLng lotPosition = new LatLng(33.673720, -117.908795); // Lot G

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

        startActivity(intent);
    }
}
