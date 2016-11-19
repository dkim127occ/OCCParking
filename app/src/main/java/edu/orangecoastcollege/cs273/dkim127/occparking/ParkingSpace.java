package edu.orangecoastcollege.cs273.dkim127.occparking;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;

/**
 * An object that represents an individual parking space.
 */
public class ParkingSpace implements Parcelable
{
    private int id;
    private int filled;
    private double x;
    private double y;

    @IntDef({FILLED, EMPTY})
    public @interface State {}
    public static final int FILLED = 1;
    public static final int EMPTY = 0;

    public ParkingSpace(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the ID of this parking space
     * @return ID of the parking space
     */
    public int getId()
    {
        return id;
    }

    /**
     * Returns the GPS x-coordinate of the space
     * @return X-coords
     */
    public double getX()
    {
        return x;
    }

    /**
     * Returns the GPS y-coordinate of the parking space
     * @return Y-coords
     */
    public double getY()
    {
        return y;
    }

    /**
     * Returns an integer depending on the filled state of this parking lot
     * @return 0 for empty, 1 for filled
     */
    public int isFilled()
    {
        return filled;
    }

    /**
     * Sets the filled state of this parking space using the argument provided.
     * @param filled new filled state
     */
    public void setFilled(@State int filled)
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
