package edu.orangecoastcollege.cs273.dkim127.occparking;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * An object that represents an individual parking space.
 */
public class ParkingSpace implements Parcelable
{
    private int id;
    private int filled;
    private double x;
    private double y;

    public ParkingSpace(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public int getId()
    {
        return id;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public int isFilled()
    {
        return filled;
    }

    public void setFilled(int filled)
    {
        this.filled = filled;
    }

    // -------------------- Parcelable implements --------------------

    public ParkingSpace(Parcel source)
    {
        id = source.readInt();
        filled = source.readInt();
        x = source.readDouble();
        y = source.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(filled);
        dest.writeDouble(x);
        dest.writeDouble(y);
    }

    public static final Parcelable.Creator<ParkingSpace> CREATOR = new Creator<ParkingSpace>() {
        @Override
        public ParkingSpace createFromParcel(Parcel source) {
            return new ParkingSpace(source);
        }

        @Override
        public ParkingSpace[] newArray(int size) {
            return new ParkingSpace[0];
        }
    };
}
