package edu.orangecoastcollege.cs273.dkim127.occparking;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * An object that represents an entire parking lot comprised of "rows" of parking spaces.
 */

public class ParkingLot implements Parcelable
{
    public static final String TAG = "ParkingLot";

    private int id;
    private String name;
    // TODO: make this 1D
    private ParkingSpace[] spaces;
    private int capacity;
    private int filled;

    public ParkingLot()
    {
        name = "";
    }

    /**
     * Returns a ParkingSpace object given the latitude and longitude coordinates.
     * @param latitude latitude of parking space
     * @param longitude longitude of parking space
     * @return Parking space in that coordinate, or null if not found
     */
    public ParkingSpace getParkingSpaceAt(float latitude, float longitude)
    {

        for (ParkingSpace parkingSpace : spaces)
        {
            if (parkingSpace.getLatitude() == latitude && parkingSpace.getLongitude() == longitude)
            {
                return parkingSpace;
            }
        }

        Log.e("ParkingLot", "invalid coordinates");
        return null;
    }

    /**
     * Returns a ParkingSpace object given an id number
     * @param id ID number of the parking space
     * @return Parking space with the correct ID, null otherwise
     */
    public ParkingSpace getParkingSpaceByID(int id)
    {
        for (ParkingSpace parkingSpace : spaces)
        {
            if (parkingSpace.getId() == id)
            {
                return parkingSpace;
            }
        }

        Log.e("ParkingLot", "invalid ID");
        return null;
    }

    public void setParkingspaces(ParkingSpace[] spaces)
    {
        this.spaces = spaces;
    }

    /**
     * Returns the name of this parking lot (Adams, A, B, C, etc).
     * @return the name of this parking lot
     */
    public String getName()
    {
        return name;
    }



    /*
    -------------------- Parcelable implements --------------------
     */
    private ParkingLot(Parcel source)
    {
        // ORDER: id, name, length of spaces, array of spaces, capacity, filled
        id = source.readInt();
        name = source.readString();
        int rowsLen = source.readInt();
        spaces = new ParkingSpace[rowsLen];
        source.readTypedArray(spaces, ParkingSpace.CREATOR);
        capacity = source.readInt();
        filled = source.readInt();
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
    public void writeToParcel(Parcel dest, int flags)
    {
        // ORDER: id, name, length of spaces, array of spaces, capacity, filled
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(spaces.length);
        dest.writeTypedArray(spaces, flags);
        dest.writeInt(capacity);
        dest.writeInt(filled);
    }
}
