package edu.orangecoastcollege.cs273.dkim127.occparking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by frddy on 11/22/2016.
 */
public class ParkingLotTest {

    private ParkingLot mParkingLot;

    @Before
    public void setUp() throws Exception {
        mParkingLot = new ParkingLot();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getParkingSpaceAt() throws Exception {

    }

    @Test
    public void getParkingSpaceByID() throws Exception {

    }

    @Test
    public void getParkingRowAt() throws Exception {

    }

    @Test
    public void getName() throws Exception {
        assertEquals("Title not set correctly.", "", mParkingLot.getName());
    }

    @Test
    public void describeContents() throws Exception {
        assertEquals("Function dose not return 0.", 0, mParkingLot.describeContents());
    }

    @Test
    public void writeToParcel() throws Exception {

    }

}