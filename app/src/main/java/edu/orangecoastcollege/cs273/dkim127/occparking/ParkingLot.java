package edu.orangecoastcollege.cs273.dkim127.occparking;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * An object that represents an entire parking lot comprised of "rows" of parking spaces.
 */

public class ParkingLot implements Parcelable
{
    public static final String TAG = "ParkingLot";

    private String name;
    private ParkingSpace[][] rows;

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
        return null;
    }


    /*
    -------------------- Parcelable implements --------------------
     */
    private ParkingLot(Parcel source)
    {
        // ORDER: name, length of rows, (length of rows[i], rows[i]) x length
        name = source.readString();
        int rowsLen = source.readInt();
        rows = new ParkingSpace[rowsLen][];
        for (int i = 0; i < rowsLen; i++)
        {
            int rowLen = source.readInt();
            rows[i] = new ParkingSpace[rowLen];
            source.readTypedArray(rows[i], ParkingSpace.CREATOR);
        }
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

        // ORDER: name, length of rows, (length of rows[i], rows[i]) x length
        dest.writeString(name);
        dest.writeInt(rows.length);
        for (ParkingSpace[] row : rows)
        {
            dest.writeInt(row.length);
            dest.writeTypedArray(row, flags);
        }

    }
}
