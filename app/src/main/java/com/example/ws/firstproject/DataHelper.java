package com.example.ws.firstproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by WS on 2016/12/2.
 */

public class DataHelper extends SQLiteOpenHelper {

    public DataHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlOrderTable = "create table "
                 +TableContants.ORDRE_TABLE+"("
                 +TableContants.SENDER_NUM+" varchar(10) unique, "
                 +TableContants.ORDER_NUM+" varchar(10), "
                 +TableContants.SENDER+" varchar(10), "
                 +TableContants.SENDER_PHONE+" varchar(10), "
                 +TableContants.RECIVER+" varchar(10), "
                 +TableContants.RECIVER_NUM+" varchar(10), "
                 +TableContants.SEND_DATE+" varchar(10), "
                 +TableContants.ORDER_NOTE+" varchar(10)) ";

        String sqlDetailTable = "create table "
                +TableContants.DETAIL_TABLE+"("
                +TableContants._ID+" integer primary key autoincrement, "
                +TableContants.ORDER_NUM+" varchar(10), "
                +TableContants.SAVE_CODE+" varchar(10), "
                +TableContants.GOODS_NAME+" varchar(10), "
                +TableContants.FORM+" varchar(10), "
                +TableContants.MODEL+" varchar(10),"
                +TableContants.UNIT+" varchar(10), "
                +TableContants.COUNT+" varchar(10), "
                +TableContants.PRICE+" varchar(10), "
                +TableContants.EFFECT_DATE+" varchar(10), "
                +TableContants.SEND_ADDRESS+" varchar(10), "
                +TableContants.ARRIVE_DATE+" varchar(10), "
                +TableContants.PRODUCTOR+" varchar(10), "
                +TableContants.START_TEMPERATURE+" varchar(10),"
                +TableContants.END_TEMPERATURE+" varchar(10),"
                +TableContants.PRODUCT_NUM+" varchar(10), "
                +TableContants.DETAIL_NOTE+" varchar(10) ";
        db.execSQL(sqlOrderTable);
        db.execSQL(sqlDetailTable);
        Log.e("ws","创建了两张表");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
