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
    private String type;
    private float latitude;
    private float longitude;
    private int filled;


    @IntDef({FILLED, EMPTY})
    public @interface State {}
    public static final int FILLED = 1;
    public static final int EMPTY = 0;

    public ParkingSpace(float latitude, float longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public ParkingSpace(int id, String type, float latitude, float longitude, int filled)
    {
        this(latitude, longitude);
        this.id = id;
        this.type = type;
        this.filled = filled;
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
     * Returns the GPS latitude of the space
     * @return Latitude in float
     */
    public float getLatitude()
    {
        return latitude;
    }

    /**
     * Returns the GPS longitude of the parking space
     * @return Longitude in float
     */
    public float getLongitude()
    {
        return longitude;
    }

    /**
     * Returns an integer depending on the filled state of this parking lot
     * @return 0 for empty, 1 for filled
     */
    public int isFilled()
    {
        return filled;
    }

    public String getType()
    {
        return type;
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
        type = source.readString();
        latitude = source.readFloat();
        longitude = source.readFloat();
        filled = source.readInt();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(type);
        dest.writeFloat(latitude);
        dest.writeFloat(longitude);
        dest.writeInt(filled);
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
