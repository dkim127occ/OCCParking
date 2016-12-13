package edu.orangecoastcollege.cs273.dkim127.occparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static edu.orangecoastcollege.cs273.dkim127.occparking.R.id.lotAMapFragment;

public class FindSpaceActivity extends AppCompatActivity implements OnMapReadyCallback, AdapterView.OnItemSelectedListener{

    private GoogleMap mMap;
    private LatLng lotPosition;
    private Spinner spaceTypeSpinner;
    private ParkingLot lot;
    private String selectedType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_space);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.mapFragment);

        mapFragment.getMapAsync(this);


        spaceTypeSpinner = (Spinner) findViewById(R.id.spaceTypeSpinner);
        spaceTypeSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                    R.array.space_type, android.R.layout.simple_spinner_item);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spaceTypeSpinner.setAdapter(spinnerAdapter);

        lot = getIntent().getParcelableExtra(ParkingLot.TAG);

        selectedType = "normal";
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Intent intent = getIntent();
        lotPosition = intent.getParcelableExtra("lotPosition");

        CameraPosition cameraPosition = new CameraPosition.Builder().target(lotPosition).zoom(17.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition((cameraPosition));
        mMap.moveCamera(cameraUpdate);

        markSpace();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position)
        {
            case 0:
                selectedType = "normal";
                break;
            case 1:
                selectedType = "coin";
                break;
            case 2:
                selectedType = "twentyMin";
                break;
            case 3:
                selectedType = "handicap";
                break;
            case 4:
                selectedType = "staff";
                break;
            default:
                selectedType = "normal";
        }

        markSpace();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * Marks the map with a ParkingSpaceObject
     */
    public void markSpace()
    {
        mMap.clear();

        ParkingSpace parkingSpace = lot.findOpenParkingSpace(selectedType);
        if (parkingSpace == null)
        {
            Toast.makeText(this, "No open space available.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            LatLng spacePosition = new LatLng(parkingSpace.getLatitude(), parkingSpace.getLongitude());
            mMap.addMarker(new MarkerOptions().position(spacePosition).title(getString(R.string.open_space)));
        }
    }
}
