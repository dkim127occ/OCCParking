package edu.orangecoastcollege.cs273.dkim127.occparking;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by frddy on 12/9/2016.
 */

public class ParkingLotAdapter extends ArrayAdapter<ParkingLot> {

    private Context mContext;
    private int mResourceId;
    private List<ParkingLot> mAllParkingLots;

    private ImageView parkingLotItemImageView;
    private TextView lotTypesTextView;
    private TextView parkingLotItemNameTextView;
    private TextView lotItemFreeValueTextView;
    private TextView lotItemFillValueTextView;

    private int standard;
    private int coin;
    private int staff;
    private int twentyMin;
    private int handicap;
    private int ev;

    public ParkingLotAdapter(Context context, int resourceId, List<ParkingLot> allParkingLots) {

        super(context, resourceId, allParkingLots);
        mContext = context;
        mResourceId = resourceId;
        mAllParkingLots = allParkingLots;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        final ParkingLot selectedParking = mAllParkingLots.get(pos);
        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        parkingLotItemImageView = (ImageView) view.findViewById(R.id.parkingLotItemImageView);
        lotTypesTextView = (TextView) view.findViewById(R.id.lotTypesTextView);
        if(selectedParking.getName() == "Adams") {
            parkingLotItemImageView.setImageResource(R.drawable.adams);
            lotTypesTextView.setText(R.string.adams_types);
        }
        else if(selectedParking.getName() == "A") {
            parkingLotItemImageView.setImageResource(R.drawable.lot_a);
            lotTypesTextView.setText(R.string.lot_a_types);
        }
        else if(selectedParking.getName() == "B") {
            parkingLotItemImageView.setImageResource(R.drawable.lot_b);
            lotTypesTextView.setText(R.string.lot_b_types);
        }
        else if(selectedParking.getName() == "C") {
            parkingLotItemImageView.setImageResource(R.drawable.lot_c);
            lotTypesTextView.setText(R.string.lot_c_types);
        }
        else if(selectedParking.getName() == "D") {
            parkingLotItemImageView.setImageResource(R.drawable.lot_d);
            lotTypesTextView.setText(R.string.lot_d_type);
        }
        else if(selectedParking.getName() == "E") {
            parkingLotItemImageView.setImageResource(R.drawable.lot_e);
            lotTypesTextView.setText(R.string.lot_e_types);
        }
        else {
            parkingLotItemImageView.setImageResource(R.drawable.lot_g);
            lotTypesTextView.setText(R.string.lot_g_types);
        }

        parkingLotItemNameTextView = (TextView) view.findViewById(R.id.parkingLotItemNameTextView);
        parkingLotItemNameTextView.setText(selectedParking.getName());

        lotItemFreeValueTextView = (TextView) view.findViewById(R.id.lotItemFreeValueTextView);
        lotItemFreeValueTextView.setText(String.valueOf(selectedParking.getFree()));
        lotItemFillValueTextView = (TextView) view.findViewById(R.id.lotItemFillValueTextView);
        lotItemFillValueTextView.setText(String.valueOf(selectedParking.getCapacity() - selectedParking.getFree()));

        view.setTag(selectedParking);

        return view;
    }
}
