package edu.orangecoastcollege.cs273.dkim127.occparking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class LotEDetails extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lot_e_details);

        SupportMapFragment lotEMapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.lotEMapFragment);

        lotEMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Change the camera view to our current position:
        mMap = googleMap;

        //LatLng lotPosition = new LatLng(33.672789, -117.912411); // Adams Lot
        //LatLng lotPosition = new LatLng(33.670884, -117.908454); // Lot A
        //LatLng lotPosition = new LatLng(33.669554, -117.908283); // Lot B
        //LatLng lotPosition = new LatLng(33.668098, -117.908650); // Lot C
        //LatLng lotPosition = new LatLng(33.667813, -117.910742); // Lot D
        LatLng lotPosition = new LatLng(33.668529, -117.914318); // Lot E
        //LatLng lotPosition = new LatLng(33.673720, -117.908795); // Lot G

        CameraPosition cameraPosition = new CameraPosition.Builder().target(lotPosition).zoom(17.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition((cameraPosition));
        mMap.moveCamera(cameraUpdate);
    }
}
