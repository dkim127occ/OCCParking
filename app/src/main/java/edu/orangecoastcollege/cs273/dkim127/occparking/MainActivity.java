package edu.orangecoastcollege.cs273.dkim127.occparking;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView lotAdamsTextView;
    private TextView lotATextView;
    private TextView lotBTextView;
    private TextView lotCTextView;
    private TextView lotDTextView;
    private TextView lotETextView;
    private TextView lotGTextView;
    private ImageView mapMaskView;

    ArrayList<ParkingLot> parkingLotArrayList;
    Context context;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        // link views with controls
        lotAdamsTextView = (TextView) findViewById(R.id.lotAdamsTextView);
        lotATextView = (TextView) findViewById(R.id.lotATextView);
        lotBTextView = (TextView) findViewById(R.id.lotBTextView);

        mapMaskView = (ImageView) findViewById(R.id.mapImageView);

        mapMaskView.setOnTouchListener(touchListener);

        // DB related stuff
        deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);
        db.importDatabaseFromCsv("lot_a.csv", DBHelper.A_TABLE);
        db.importDatabaseFromCsv("lot_b.csv", DBHelper.B_TABLE);
        db.importDatabaseFromCsv("lot_c.csv", DBHelper.C_TABLE);

        parkingLotArrayList = db.getAllLots();
    }


    /**
     * Returns a hexadecimal color value of the tapped area given the resource ID of tapped image and coordinates.
     * @param hotSpotID resource ID of the ImageView to fetch the color from
     * @param x x-coordinate of the tapped region
     * @param y y-coordinate of the tapped region
     * @return RGB in hex without the alpha value
     */
    public int getHotSpotColor(int hotSpotID, int x, int y)
    {
        ImageView img = (ImageView) findViewById(hotSpotID);
        img.setDrawingCacheEnabled(true);
        Bitmap hotSpots = Bitmap.createBitmap(img.getDrawingCache());
        img.setDrawingCacheEnabled(false);
        return hotSpots.getPixel(x, y) & 0xFFFFFF;
    }

    /**
     * Returns a ParkingLot object that shares the same name as the argument.
     * @param tableName name of the parking lot
     * @return ParkingLot with the same name from the array list, null if not found
     */
    public ParkingLot findLotByName(String tableName)
    {
        for (ParkingLot lot : parkingLotArrayList)
        {
            if (lot.getName().equals(tableName))
            {
                return lot;
            }
        }
        return null;
    }

    /**
     * Listener for tapping regions in the main parking lot imageview
     */
    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            final int action = event.getAction();
            final int x = (int)event.getX();
            final int y = (int)event.getY();

            if (action == MotionEvent.ACTION_UP) {
                //Log.i("MainActivity", "THIS SHOULD BE RUNNING");
                int touchColor = getHotSpotColor(R.id.mapMaskImageView, x, y);
                int tolerance = 0xf;

                Intent intent = new Intent();
                ParkingLot parkingLot;

                if (ColorTool.closeMatch(ColorTool.ADAMS, touchColor, tolerance))
                {
                    intent = new Intent(context, LotAdamsDetailActivity.class);
                    parkingLot = findLotByName(DBHelper.ADAMS_TABLE);
                }
                else if (ColorTool.closeMatch(ColorTool.A, touchColor, tolerance))
                {
                    intent = new Intent(context, LotADetailsActivity.class);
                    parkingLot = findLotByName(DBHelper.A_TABLE);
                }
                else if (ColorTool.closeMatch(ColorTool.B, touchColor, tolerance))
                {
                    intent = new Intent(context, LotBDetailsActivity.class);
                    parkingLot = findLotByName(DBHelper.B_TABLE);
                }
                else if (ColorTool.closeMatch(ColorTool.C, touchColor, tolerance))
                {
                    intent = new Intent(context, LotCDetailsActivity.class);
                    parkingLot = findLotByName(DBHelper.C_TABLE);
                }
                else if (ColorTool.closeMatch(ColorTool.D, touchColor, tolerance))
                {
                    intent = new Intent(context, LotDDetailsActivity.class);
                    parkingLot = findLotByName(DBHelper.D_TABLE);
                }
                else if (ColorTool.closeMatch(ColorTool.E, touchColor, tolerance))
                {
                    intent = new Intent(context, LotEDetails.class);
                    parkingLot = findLotByName(DBHelper.E_TABLE);
                }
                else if (ColorTool.closeMatch(ColorTool.G, touchColor, tolerance))
                {
                    intent = new Intent(context, LotGDetails.class);
                    parkingLot = findLotByName(DBHelper.G_TABLE);
                }
                else
                {
                    // invalid color region
                    // terrible hack!
                    Log.i("MainActivity", "invalid color region " + Integer.toHexString(touchColor));
                    intent = new Intent();
                    return true;
                }
                Log.i("MainActivity", "starting intent...");
                intent.putExtra(ParkingLot.TAG, parkingLot);
                startActivity(intent);
            }
            return true;
        }
    };
}
