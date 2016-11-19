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
    private ParkingSpace[][] rows;
    private int capacity;
    private int filled;

    public ParkingLot()
    {
        name = "";
    }

    /**
     * Returns a ParkingSpace object given the x and y GPS coordinates.
     * @param x X-coord of parking space
     * @param y Y-coord of parking space
     * @return Parking space in that coordinate, or null if not found
     */
    public ParkingSpace getParkingSpaceAt(double x, double y)
    {
        for (ParkingSpace[] row : rows)
        {
            for (ParkingSpace parkingSpace : row)
            {
                if (parkingSpace.getX() == x && parkingSpace.getY() == y)
                {
                    return parkingSpace;
                }
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
        for (ParkingSpace[] row : rows)
        {
            for (ParkingSpace parkingSpace : row)
            {
                if (parkingSpace.getId() == id)
                {
                    return parkingSpace;
                }
            }
        }
        Log.e("ParkingLot", "invalid ID");
        return null;
    }


    /**
     * Returns a specified row of the parking lot containing parking spaces
     * @param idx row number of the parking space
     * @return ParkingSpace[] at that index, or null if out of bounds
     */
    public ParkingSpace[] getParkingRowAt(int idx)
    {
        if (idx < rows.length)
        {
            return rows[idx];
        }
        Log.e("ParkingLot", "invalid row index");
        return null;
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
        // ORDER: id, name, length of rows, (length of rows[i], rows[i]) x length, capacity, filled
        id = source.readInt();
        name = source.readString();
        int rowsLen = source.readInt();
        rows = new ParkingSpace[rowsLen][];
        for (int i = 0; i < rowsLen; i++)
        {
            int rowLen = source.readInt();
            rows[i] = new ParkingSpace[rowLen];
            source.readTypedArray(rows[i], ParkingSpace.CREATOR);
        }
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
        // ORDER: id, name, length of rows, (length of rows[i], rows[i]) x length, capacity, filled
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(rows.length);
        for (ParkingSpace[] row : rows)
        {
            dest.writeInt(row.length);
            dest.writeTypedArray(row, flags);
        }
        dest.writeInt(capacity);
        dest.writeInt(filled);
    }
}
