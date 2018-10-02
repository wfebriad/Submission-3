package id.web.wfebriadi.submissionkamus.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;

import id.web.wfebriadi.submissionkamus.model.EnglishModel;
import id.web.wfebriadi.submissionkamus.model.IndonesiaModel;

import static android.provider.BaseColumns._ID;
import static id.web.wfebriadi.submissionkamus.database.DatabaseContract.KamusColumns.TRANSLATE;
import static id.web.wfebriadi.submissionkamus.database.DatabaseContract.KamusColumns.WORD;
import static id.web.wfebriadi.submissionkamus.database.DatabaseContract.TABLE_ENGLISH_INDONESIA;
import static id.web.wfebriadi.submissionkamus.database.DatabaseContract.TABLE_INDONESIA_ENGLISH;

public class KamusHelper {
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public KamusHelper(Context context){
        this.context = context;
    }
    public KamusHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        databaseHelper.close();
    }

    //---------INDONESIA
    public ArrayList<IndonesiaModel> getDataByNameIndonesia(String word){
        String result = "";
        Cursor cursor = database.query(TABLE_INDONESIA_ENGLISH,null,WORD+" LIKE ?",new String[]{word + "%"},null,null, _ID + " ASC", null );
        cursor.moveToFirst();
        ArrayList<IndonesiaModel> arrayListIndonesia = new ArrayList<>();
        IndonesiaModel indonesiaModel;
        if (cursor.getCount() > 0 ){
            do {
                indonesiaModel = new IndonesiaModel();//indonesiamodel.java baris 56-65 error
                indonesiaModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                indonesiaModel.setWord(cursor.getString(cursor.getColumnIndexOrThrow(WORD)));
                indonesiaModel.setTranslate(cursor.getString(cursor.getColumnIndexOrThrow(TRANSLATE)));

                arrayListIndonesia.add(indonesiaModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayListIndonesia;
    }

    //----------ENGLISH
    public ArrayList<EnglishModel> getDataByNameEnglish(String word){
        String result = "";
        Cursor cursor = database.query(TABLE_ENGLISH_INDONESIA, null, WORD+" LIKE ?", new String[]{word + "%"}, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<EnglishModel> arrayListEnglish = new ArrayList<>();
        EnglishModel englishModel;
        if (cursor.getCount() > 0 ){
            do {
                englishModel = new EnglishModel();//englishmodel.java baris 42-50 error
                englishModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                englishModel.setWord(cursor.getString(cursor.getColumnIndexOrThrow(WORD)));
                englishModel.setTranslate(cursor.getString(cursor.getColumnIndexOrThrow(TRANSLATE)));

                arrayListEnglish.add(englishModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayListEnglish;
    }

    //----------INDONESIA
    public ArrayList<IndonesiaModel> getAllDataIndonesia(){
        Cursor cursor = database.query(TABLE_INDONESIA_ENGLISH, null, null, null, null, null, _ID+ " ASC", null);
        cursor.moveToFirst();
        ArrayList<IndonesiaModel> arrayListIndonesia = new ArrayList<>();
        IndonesiaModel indonesiaModel;
        if (cursor.getCount() > 0){
            do {
                indonesiaModel = new IndonesiaModel();
                indonesiaModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                indonesiaModel.setWord(cursor.getString(cursor.getColumnIndexOrThrow(WORD)));
                indonesiaModel.setTranslate(cursor.getString(cursor.getColumnIndexOrThrow(TRANSLATE)));

                arrayListIndonesia.add(indonesiaModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayListIndonesia;
    }

    //----------ENGLISH
    public ArrayList<EnglishModel> getAllDataEnglish(){
        Cursor cursor = database.query(TABLE_ENGLISH_INDONESIA, null, null, null, null, null, _ID+" ASC", null);
        cursor.moveToFirst();
        ArrayList<EnglishModel> arrayListEnglish = new ArrayList<>();
        EnglishModel englishModel;
        if (cursor.getCount() > 0){
            do {
                englishModel = new EnglishModel();
                englishModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                englishModel.setWord(cursor.getString(cursor.getColumnIndexOrThrow(WORD)));
                englishModel.setTranslate(cursor.getString(cursor.getColumnIndexOrThrow(TRANSLATE)));

                arrayListEnglish.add(englishModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayListEnglish;
    }

    //----------INDONESIA
    public void insertIndonesia(IndonesiaModel indonesiaModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(WORD, indonesiaModel.getWord());
        contentValues.put(TRANSLATE, indonesiaModel.getTranslate());
        database.insert(TABLE_INDONESIA_ENGLISH, null, contentValues);
    }

    //----------ENGLISH
    public void insertEnglish(EnglishModel englishModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(WORD, englishModel.getWord());
        contentValues.put(TRANSLATE, englishModel.getTranslate());
        database.insert(TABLE_ENGLISH_INDONESIA, null, contentValues);
    }

    public void beginTransaction(){
        database.beginTransaction();
    }

    public void setTransactionSuccessEnglish(){
        database.setTransactionSuccessful();
    }
    public void setTransactionSuccessIndonesia(){
        database.setTransactionSuccessful();
    }

    public void endTransaction(){
        database.endTransaction();
    }

    //---------INDONESIA
    public void insertTransactionIndonesia(IndonesiaModel indonesiaModel){
        String sql = "INSERT INTO "+ TABLE_INDONESIA_ENGLISH + " (" + WORD + "," + TRANSLATE + ") VALUES (? ,?)";
        SQLiteStatement stmtIndonesia = database.compileStatement(sql);
        stmtIndonesia.bindString(1, indonesiaModel.getWord());
        stmtIndonesia.bindString(2, indonesiaModel.getTranslate());
        stmtIndonesia.execute();
        stmtIndonesia.clearBindings();
    }
    //---------ENGLISH
    public void insertTransactionEnglish(EnglishModel englishModel){
        String sql = "INSERT INTO "+ TABLE_ENGLISH_INDONESIA + " (" + WORD + "," + TRANSLATE + ") VALUES (? ,?)";
        SQLiteStatement stmtEnglish = database.compileStatement(sql);
        stmtEnglish.bindString(1, englishModel.getWord());
        stmtEnglish.bindString(2, englishModel.getTranslate());
        stmtEnglish.execute();
        stmtEnglish.clearBindings();
    }
}
