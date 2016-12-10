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

    private String name;
    private ParkingSpace[] spaces;
    private int capacity;
    private int filled;

    // statistics
    private double avgFilled;

    public ParkingLot()
    {
        name = "";
        avgFilled = capacity - Math.random() * 100;
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

    public void setName(String name)
    {
        this.name = name;
    }

    public void setParkingSpaces(ParkingSpace[] spaces)
    {
        this.spaces = spaces;
        capacity = spaces.length;
        filled = 0;
        for (ParkingSpace space : spaces)
        {
            if (space.isFilled() == 1)
            {
                filled++;
            }
        }
    }

    /**
     * Returns the name of this parking lot (Adams, A, B, C, etc).
     * @return the name of this parking lot
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the number of filled spaces
     * @return number of spaces filled
     */
    public int getFilled() {
        return filled;
    }

    /**
     * Returns the total number of parking spaces available
     * @return total number of spaces
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns the simulated average number of filled spaces for this lot.
     * @return simulated average of filled lots
     */
    public double getAvgFilled()
    {
        return avgFilled;
    }



    /*
    -------------------- Parcelable implements --------------------
     */
    private ParkingLot(Parcel source)
    {
        // ORDER: id, name, length of spaces, array of spaces, capacity, filled
        name = source.readString();
        int rowsLen = source.readInt();
        spaces = new ParkingSpace[rowsLen];
        source.readTypedArray(spaces, ParkingSpace.CREATOR);
        capacity = source.readInt();
        filled = source.readInt();
        avgFilled = source.readDouble();
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
        dest.writeString(name);
        dest.writeInt(spaces.length);
        dest.writeTypedArray(spaces, flags);
        dest.writeInt(capacity);
        dest.writeInt(filled);
        dest.writeDouble(avgFilled);
    }
}
