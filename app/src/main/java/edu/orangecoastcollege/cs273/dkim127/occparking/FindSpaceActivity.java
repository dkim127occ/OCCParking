package edu.orangecoastcollege.cs273.dkim127.occparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import static edu.orangecoastcollege.cs273.dkim127.occparking.R.id.lotAMapFragment;

public class FindSpaceActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private LatLng lotPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_space);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.mapFragment);

        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Intent intent = getIntent();
        lotPosition = (LatLng) intent.getParcelableExtra("lotPosition");

        CameraPosition cameraPosition = new CameraPosition.Builder().target(lotPosition).zoom(17.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition((cameraPosition));
        mMap.moveCamera(cameraUpdate);

    }
}
