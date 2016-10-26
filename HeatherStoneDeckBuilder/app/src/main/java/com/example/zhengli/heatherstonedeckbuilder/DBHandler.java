package com.example.zhengli.heatherstonedeckbuilder;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zhengli on 2016-04-11.
 */
public class DBHandler extends SQLiteOpenHelper{

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "CardSet";

    //Column Name
    private static final String KEY_URL = "url";
    private static final String Card_NAME = "name";

    //Table Name
    private static final String CARD_SHOPS = "Card";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + CARD_SHOPS + "("
                + Card_NAME + " TEXT,"
                + KEY_URL + " TEXT" + ")";

        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + CARD_SHOPS);
       // Creating tables again
        onCreate(db);
    }

    public void addCard(CardSet cardset) throws Exception {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String [] cardSetArray = cardset.getCardURLSet();
        for(int i = 0; i<cardSetArray.length; i ++){
            values.put(KEY_URL, cardSetArray[i]);
        }

        // Inserting Row


    }
}
