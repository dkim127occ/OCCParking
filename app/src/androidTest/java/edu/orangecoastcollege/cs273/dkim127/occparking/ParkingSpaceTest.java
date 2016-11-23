package edu.orangecoastcollege.cs273.dkim127.occparking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by jkloppenburg1 on 11/22/2016.
 */
public class ParkingSpaceTest {

    private ParkingSpace myParkingSpace;
    double x;
    double y;

    @Before
    public void setUp() throws Exception {
        x = 1.0;
        y = 2.0;
        myParkingSpace = new ParkingSpace(x, y);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getId() throws Exception {

    }

    @Test
    public void getX() throws Exception {
        assertEquals(x, myParkingSpace.getX());
    }

    @Test
    public void getY() throws Exception {
        assertEquals(y, myParkingSpace.getY());
    }

    @Test
    public void isFilled() throws Exception {

    }

    @Test
    public void setFilled() throws Exception {

    }

    @Test
    public void describeContents() throws Exception {

    }

    @Test
    public void writeToParcel() throws Exception {

    }

}