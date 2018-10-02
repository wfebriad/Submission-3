package id.web.wfebriadi.submissionkamus.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static id.web.wfebriadi.submissionkamus.database.DatabaseContract.KamusColumns.TRANSLATE;
import static id.web.wfebriadi.submissionkamus.database.DatabaseContract.KamusColumns.WORD;
import static id.web.wfebriadi.submissionkamus.database.DatabaseContract.TABLE_ENGLISH_INDONESIA;
import static id.web.wfebriadi.submissionkamus.database.DatabaseContract.TABLE_INDONESIA_ENGLISH;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "dbkamus";
    private static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_ENGLISH = "create table " + TABLE_ENGLISH_INDONESIA + " (" +
            _ID + " integer primary key autoincrement, " +
            WORD + " text not null, " +
            TRANSLATE + " text not null);";

    public static String CREATE_TABLE_INDONESIA = "create table " + TABLE_INDONESIA_ENGLISH + " (" +
            _ID+ " integer primary key autoincrement, " +
            WORD + " text not null, " +
            TRANSLATE + " text not null);";

    public DatabaseHelper( Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ENGLISH);
        db.execSQL(CREATE_TABLE_INDONESIA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_ENGLISH_INDONESIA);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_INDONESIA_ENGLISH);
        onCreate(db);
    }

}
