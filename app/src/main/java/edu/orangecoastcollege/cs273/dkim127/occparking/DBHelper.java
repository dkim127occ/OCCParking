package edu.orangecoastcollege.cs273.dkim127.occparking;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.StringDef;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper
{
    @StringDef({ADAMS_TABLE, A_TABLE, B_TABLE, C_TABLE, D_TABLE, E_TABLE, G_TABLE})
    public @interface Lot{}
    private static final String ADAMS_TABLE = "Adams";
    private static final String A_TABLE = "A";
    private static final String B_TABLE = "B";
    private static final String C_TABLE = "C";
    private static final String D_TABLE = "D";
    private static final String E_TABLE = "E";
    private static final String G_TABLE = "G";

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
                + PARKING_LOT_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_SPACE_TYPE + " TEXT, "
                + FIELD_LATITUDE + " REAL, "
                + FIELD_LONGITUDE + " REAL, "
                + FIELD_FILLED + " INTEGER " + ")";
        db.execSQL(createQuery);

        // A table
        createQuery = "CREATE TABLE " + A_TABLE + "("
                + PARKING_LOT_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_SPACE_TYPE + " TEXT, "
                + FIELD_LATITUDE + " REAL, "
                + FIELD_LONGITUDE + " REAL, "
                + FIELD_FILLED + " INTEGER " + ")";
        db.execSQL(createQuery);

        // B table
        createQuery = "CREATE TABLE " + B_TABLE + "("
                + PARKING_LOT_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_SPACE_TYPE + " TEXT, "
                + FIELD_LATITUDE + " REAL, "
                + FIELD_LONGITUDE + " REAL, "
                + FIELD_FILLED + " INTEGER " + ")";
        db.execSQL(createQuery);

        // C table
        createQuery = "CREATE TABLE " + C_TABLE + "("
                + PARKING_LOT_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_SPACE_TYPE + " TEXT, "
                + FIELD_LATITUDE + " REAL, "
                + FIELD_LONGITUDE + " REAL, "
                + FIELD_FILLED + " INTEGER " + ")";
        db.execSQL(createQuery);

        // D table
        createQuery = "CREATE TABLE " + D_TABLE + "("
                + PARKING_LOT_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_SPACE_TYPE + " TEXT, "
                + FIELD_LATITUDE + " REAL, "
                + FIELD_LONGITUDE + " REAL, "
                + FIELD_FILLED + " INTEGER " + ")";
        db.execSQL(createQuery);

        // E table
        createQuery = "CREATE TABLE " + E_TABLE + "("
                + PARKING_LOT_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_SPACE_TYPE + " TEXT, "
                + FIELD_LATITUDE + " REAL, "
                + FIELD_LONGITUDE + " REAL, "
                + FIELD_FILLED + " INTEGER " + ")";
        db.execSQL(createQuery);

        // G table
        createQuery = "CREATE TABLE " + G_TABLE + "("
                + PARKING_LOT_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
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

    public ParkingLot getLot(@Lot String lotName)
    {
        // TODO: this is wrong as heck
        ArrayList<ParkingSpace> rows = new ArrayList<>();

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
                rows.add(space);
            }
            while(cursor.moveToNext());
        }
        ParkingLot lot = new ParkingLot();
        return lot;
    }

    public ArrayList<ParkingLot> getAllLots()
    {
        // TODO: gotta implement this
        ArrayList<ParkingLot> allLots = new ArrayList<>();

        return allLots;
    }
}
