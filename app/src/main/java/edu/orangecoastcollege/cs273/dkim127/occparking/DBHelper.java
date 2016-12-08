package edu.orangecoastcollege.cs273.dkim127.occparking;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.StringDef;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;


public class DBHelper extends SQLiteOpenHelper
{
    @StringDef({ADAMS_TABLE, A_TABLE, B_TABLE, C_TABLE, D_TABLE, E_TABLE, G_TABLE})
    public @interface Lot{}
    static final String ADAMS_TABLE = "Adams";
    static final String A_TABLE = "A";
    static final String B_TABLE = "B";
    static final String C_TABLE = "C";
    static final String D_TABLE = "D";
    static final String E_TABLE = "E";
    static final String G_TABLE = "G";

    private static final String PARKING_LOT_KEY_FIELD_ID = "_id";
    private static final String FIELD_SPACE_TYPE = "type";
    private static final String FIELD_LATITUDE = "latitude";
    private static final String FIELD_LONGITUDE = "longitude";
    private static final String FIELD_FILLED = "filled";


    static final String DATABASE_NAME = "OCCParking";
    private static final int DATABASE_VERSION = 1;

    private Context mContext;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // Adams table
        String createQuery = "CREATE TABLE " + ADAMS_TABLE + "("
                + PARKING_LOT_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_SPACE_TYPE + " TEXT, "
                + FIELD_LATITUDE + " REAL, "
                + FIELD_LONGITUDE + " REAL, "
                + FIELD_FILLED + " INTEGER " + ")";
        db.execSQL(createQuery);

        // A table
        createQuery = "CREATE TABLE " + A_TABLE + "("
                + PARKING_LOT_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_SPACE_TYPE + " TEXT, "
                + FIELD_LATITUDE + " REAL, "
                + FIELD_LONGITUDE + " REAL, "
                + FIELD_FILLED + " INTEGER " + ")";
        db.execSQL(createQuery);

        // B table
        createQuery = "CREATE TABLE " + B_TABLE + "("
                + PARKING_LOT_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_SPACE_TYPE + " TEXT, "
                + FIELD_LATITUDE + " REAL, "
                + FIELD_LONGITUDE + " REAL, "
                + FIELD_FILLED + " INTEGER " + ")";
        db.execSQL(createQuery);

        // C table
        createQuery = "CREATE TABLE " + C_TABLE + "("
                + PARKING_LOT_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_SPACE_TYPE + " TEXT, "
                + FIELD_LATITUDE + " REAL, "
                + FIELD_LONGITUDE + " REAL, "
                + FIELD_FILLED + " INTEGER " + ")";
        db.execSQL(createQuery);

        // D table
        createQuery = "CREATE TABLE " + D_TABLE + "("
                + PARKING_LOT_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_SPACE_TYPE + " TEXT, "
                + FIELD_LATITUDE + " REAL, "
                + FIELD_LONGITUDE + " REAL, "
                + FIELD_FILLED + " INTEGER " + ")";
        db.execSQL(createQuery);

        // E table
        createQuery = "CREATE TABLE " + E_TABLE + "("
                + PARKING_LOT_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_SPACE_TYPE + " TEXT, "
                + FIELD_LATITUDE + " REAL, "
                + FIELD_LONGITUDE + " REAL, "
                + FIELD_FILLED + " INTEGER " + ")";
        db.execSQL(createQuery);

        // G table
        createQuery = "CREATE TABLE " + G_TABLE + "("
                + PARKING_LOT_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_SPACE_TYPE + " TEXT, "
                + FIELD_LATITUDE + " REAL, "
                + FIELD_LONGITUDE + " REAL, "
                + FIELD_FILLED + " INTEGER " + ")";
        db.execSQL(createQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + ADAMS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + A_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + B_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + C_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + D_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + E_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + G_TABLE);
        onCreate(db);
    }

    /**
     * Returns a ParkingLot object that matches the given lot name.
     * @param lotName name of the parking lot
     * @return ParkingLot instance, or null if not found
     */
    public ParkingLot getLot(@Lot String lotName)
    {
        // TODO: this is wrong as heck
        ArrayList<ParkingSpace> spaces = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.query(
                lotName,
                new String[] {PARKING_LOT_KEY_FIELD_ID, FIELD_SPACE_TYPE, FIELD_LATITUDE, FIELD_LONGITUDE, FIELD_FILLED},
                null,
                null,
                null, null, null, null
        );


        if (cursor.moveToFirst())
        {
            do
            {
                ParkingSpace space =
                        new ParkingSpace(cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getFloat(2),
                                cursor.getFloat(3),
                                cursor.getInt((4)));

                spaces.add(space);
            }
            while(cursor.moveToNext());
        }

        ParkingLot lot = new ParkingLot();
        lot.setParkingSpaces(spaces.toArray(new ParkingSpace[spaces.size()]));
        lot.setName(lotName);
        return lot;
    }

    /**
     * Gets all the parking lots in this database.
     * @return ArrayList of all ParkingLot objects
     */
    public ArrayList<ParkingLot> getAllLots()
    {
        // TODO: gotta implement this
        ArrayList<ParkingLot> allLots = new ArrayList<>();

        allLots.add(getLot(ADAMS_TABLE));
        allLots.add(getLot(A_TABLE));
        allLots.add(getLot(B_TABLE));
        allLots.add(getLot(C_TABLE));
        allLots.add(getLot(D_TABLE));
        allLots.add(getLot(E_TABLE));
        allLots.add(getLot(G_TABLE));

        return allLots;
    }

    /**
     * Adds the given parking space to the database.
     * @param parkingSpace ParkingSpace object to add to the database
     * @param tableName name of the table to insert into
     */
    public void addParkingSpace(ParkingSpace parkingSpace, @Lot String tableName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PARKING_LOT_KEY_FIELD_ID, parkingSpace.getId());
        values.put(FIELD_SPACE_TYPE, parkingSpace.getType());
        values.put(FIELD_LATITUDE, parkingSpace.getLatitude());
        values.put(FIELD_LONGITUDE, parkingSpace.getLongitude());
        values.put(FIELD_FILLED, parkingSpace.isFilled());

        db.insert(tableName, null, values);
        db.close();
    }

    /**
     * Updates the given ParkingSpace object
     * @param space ParkingSpace object to update
     * @param tableName name of the table where this object should be
     */
    public void updateParkingSpace(ParkingSpace space, @Lot String tableName)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_FILLED, space.isFilled());
        values.put(FIELD_SPACE_TYPE, space.getType());
        values.put(FIELD_LATITUDE, space.getLatitude());
        values.put(FIELD_LONGITUDE, space.getLongitude());

        db.update(tableName, values, PARKING_LOT_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(space.getId())});
        db.close();
    }

    /**
     * Imports the database tables from .csv files.
     * @param csvFileName name of the csv file
     * @param tableName name of the table to populate
     * @return true if successful, false otherwise
     */
    public boolean importDatabaseFromCsv(String csvFileName, @Lot String tableName)
    {
        AssetManager am = mContext.getAssets();
        InputStream inStream = null;
        try
        {
            inStream = am.open(csvFileName);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line;
        try
        {
            while((line = buffer.readLine()) != null)
            {
                String[] fields = line.split(",");
                if (fields.length != 5)
                {
                    Log.d("ParkingLot", "skipping bad csv row: " + Arrays.toString(fields));
                    continue;
                }
                int id = Integer.parseInt(fields[0].trim());
                String spaceType = fields[1].trim();
                float latitude = Float.parseFloat(fields[2].trim());
                float longitude = Float.parseFloat(fields[3].trim());
                int filled = Integer.parseInt(fields[4].trim());

                addParkingSpace(new ParkingSpace(id, spaceType, latitude, longitude, filled), tableName);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
