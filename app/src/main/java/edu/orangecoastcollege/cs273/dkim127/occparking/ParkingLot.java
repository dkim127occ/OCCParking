package edu.orangecoastcollege.cs273.dkim127.occparking;

import android.os.Parcel;
import android.os.Parcelable;


public class ParkingLot implements Parcelable
{

    private String name;

    public ParkingLot()
    {
        name = "";
    }


    /*
    -------------------- Parcelable implements --------------------
     */
    private ParkingLot(Parcel source)
    {
        name = source.readString();
    }

    public static final Parcelable.Creator<ParkingLot> CREATOR = new Creator<ParkingLot>() {
        @Override
        public ParkingLot createFromParcel(Parcel source) {
            return new ParkingLot(source);
        }

        @Override
        public ParkingLot[] newArray(int size) {
            return new ParkingLot[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
