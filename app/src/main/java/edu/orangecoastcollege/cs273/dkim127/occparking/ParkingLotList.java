package edu.orangecoastcollege.cs273.dkim127.occparking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class ParkingLotList extends AppCompatActivity {

    ArrayList<ParkingLot> mParkingLotArrayList;
    DBHelper db;

    private ParkingLotAdapter mParkingLotAdapter;
    private ListView parkingLotListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_lot_list);

        db = new DBHelper(this);
        mParkingLotArrayList = db.getAllLots();

        mParkingLotAdapter = new ParkingLotAdapter(this, R.layout.parking_lot_list_item, mParkingLotArrayList);
        parkingLotListView = (ListView) findViewById(R.id.parkingLotListView);
        parkingLotListView.setAdapter(mParkingLotAdapter);

    }

    public void openDetails(View view)
    {
        if (view instanceof LinearLayout) {
            LinearLayout selectedLinearLayout = (LinearLayout) view;
            ParkingLot lot = (ParkingLot) selectedLinearLayout.getTag();

            Intent intent = new Intent();
            if (lot.getName() == "Adams")
                intent = new Intent(this, LotAdamsDetailActivity.class);
            else if (lot.getName() == "A")
                intent = new Intent(this, LotADetailsActivity.class);
            else if (lot.getName() == "B")
                intent = new Intent(this, LotBDetailsActivity.class);
            else if (lot.getName() == "C")
                intent = new Intent(this, LotCDetailsActivity.class);
            else if (lot.getName() == "D")
                intent = new Intent(this, LotDDetailsActivity.class);
            else if (lot.getName() == "E")
                intent = new Intent(this, LotEDetails.class);
            else
                intent = new Intent(this, LotGDetails.class);

            intent.putExtra(lot.TAG, lot);
            startActivity(intent);
        }
    }
}
