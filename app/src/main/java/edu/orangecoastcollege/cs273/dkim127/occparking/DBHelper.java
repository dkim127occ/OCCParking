package edu.orangecoastcollege.cs273.dkim127.occparking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper
{
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

    }
}
