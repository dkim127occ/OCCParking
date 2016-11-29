package edu.orangecoastcollege.cs273.dkim127.occparking;

import android.os.Parcel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by jkloppenburg1 on 11/22/2016.
 */
public class ParkingSpaceTest {

    private ParkingSpace myParkingSpace;
    float latitude;
    float longitude;
    int filled;

    @Before
    public void setUp() throws Exception {
        latitude = 1.0f;
        longitude = 2.0f;
        filled = 0;
        myParkingSpace = new ParkingSpace(latitude, longitude);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getId() throws Exception {

    }

    @Test
    public void getLatitude() throws Exception {
        assertEquals(latitude, myParkingSpace.getLatitude());
    }

    @Test
    public void getLongitude() throws Exception {
        assertEquals(longitude, myParkingSpace.getLongitude());
    }

    @Test
    public void isFilled() throws Exception {
        myParkingSpace.setFilled(ParkingSpace.FILLED);
        assertEquals(ParkingSpace.FILLED, myParkingSpace.isFilled());
    }

    @Test
    public void isEmpty() throws Exception {
        myParkingSpace.setFilled(ParkingSpace.EMPTY);
        assertEquals(ParkingSpace.EMPTY, myParkingSpace.isFilled());
    }


    @Test
    public void describeContents() throws Exception {

    }

    @Test
    public void writeToParcel() throws Exception {
        Parcel parcel = Parcel.obtain();
        myParkingSpace.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);

        ParkingSpace fromParcel = ParkingSpace.CREATOR.createFromParcel(parcel);
        assertEquals(myParkingSpace, fromParcel);
    }

}