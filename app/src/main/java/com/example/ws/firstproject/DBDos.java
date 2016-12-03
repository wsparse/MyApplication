package com.example.ws.firstproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by WS on 2016/12/2.
 */

public class DBDos{
    SQLiteDatabase db;
    DataHelper dataHelper;


    public DBDos(Context context){
        dataHelper = new DataHelper(context,"order.db",null,1);
        db = dataHelper.getWritableDatabase();
    }

    public void insertListToDB(List<OrderEntity> entityList){
        for(OrderEntity orderEntity:entityList){
            insertOrderEntity(orderEntity);
        }
        Log.e("ws","循环将泛型为OrderEntity的集合添加到数据库成功！");
    }

    public List<OrderEntity> queryAll() {
        List<OrderEntity> entityList = new ArrayList<>();
        String sql = "select * from " + TableContants.ORDRE_TABLE + "";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            String sender_num = cursor.getString(cursor.getColumnIndex(TableContants.SENDER_NUM));
            String order_num = cursor.getString(cursor.getColumnIndex(TableContants.ORDER_NUM));
            String sender = cursor.getString(cursor.getColumnIndex(TableContants.SENDER));
            String sender_phone = cursor.getString(cursor.getColumnIndex(TableContants.SENDER_PHONE));
            String reciver = cursor.getString(cursor.getColumnIndex(TableContants.RECIVER));
            String reciver_num = cursor.getString(cursor.getColumnIndex(TableContants.RECIVER_NUM));
            String send_date = cursor.getString(cursor.getColumnIndex(TableContants.SEND_DATE));
            String order_note = cursor.getString(cursor.getColumnIndex(TableContants.ORDER_NOTE));
            List<OrderEntity.Detail> detailList = new ArrayList<>();
            String sqls = "select * from " + TableContants.DETAIL_TABLE + " where " + TableContants.ORDER_NUM + " = " + order_num;
            Cursor cursor1 = db.rawQuery(sqls, null);
            while (cursor1.moveToNext()) {
                String order_nums = cursor1.getString(cursor1.getColumnIndex(TableContants.ORDER_NUM));
                String save_code = cursor1.getString(cursor1.getColumnIndex(TableContants.SAVE_CODE));
                String goods_name = cursor1.getString(cursor1.getColumnIndex(TableContants.GOODS_NAME));
                String form = cursor1.getString(cursor1.getColumnIndex(TableContants.FORM));
                String model = cursor1.getString(cursor1.getColumnIndex(TableContants.MODEL));
                String unit = cursor1.getString(cursor1.getColumnIndex(TableContants.UNIT));
                String count = cursor1.getString(cursor1.getColumnIndex(TableContants.COUNT));
                String price = cursor1.getString(cursor1.getColumnIndex(TableContants.PRICE));
                String effect_date = cursor1.getString(cursor1.getColumnIndex(TableContants.EFFECT_DATE));
                String send_address = cursor1.getString(cursor1.getColumnIndex(TableContants.SEND_ADDRESS));
                String arrive_date = cursor1.getString(cursor1.getColumnIndex(TableContants.ARRIVE_DATE));
                String productor = cursor1.getString(cursor1.getColumnIndex(TableContants.PRODUCTOR));
                String start_tempreture = cursor1.getString(cursor1.getColumnIndex(TableContants.START_TEMPERATURE));
                String end_tempreture = cursor1.getString(cursor1.getColumnIndex(TableContants.END_TEMPERATURE));
                String product_num = cursor1.getString(cursor1.getColumnIndex(TableContants.PRODUCT_NUM));
                String detail_note = cursor1.getString(cursor1.getColumnIndex(TableContants.DETAIL_NOTE));
                OrderEntity.Detail detail = new OrderEntity.Detail(order_nums,save_code,goods_name,form,model,unit,count,price,effect_date,send_address,arrive_date,productor,start_tempreture,end_tempreture,product_num,detail_note);
                detailList.add(detail);
            }
            Log.e("ws","detailList的长度为："+detailList.size());
            OrderEntity orderEntity = new OrderEntity(sender_num,order_num,sender,sender_phone,reciver,reciver_num,send_date,order_note,detailList);
            entityList.add(orderEntity);
        }
        Log.e("ws","entityList的长度为："+entityList.size());
        return entityList;
    }

    public synchronized void insertOrderEntity(OrderEntity orderEntity){
        //String sql = "insert into "+TableContants.ORDRE_TABLE+" values()";
        db.beginTransaction();
        try {
            long insert = db.insert(TableContants.ORDRE_TABLE, null, orderEntityToValues(orderEntity));
            Log.e("ws","插入表ORDER_TABLE得到的返回码为: "+insert);
            //String order_num = orderEntity.order_num;
            List<OrderEntity.Detail> detailList = orderEntity.detailList;
            for(OrderEntity.Detail detail : detailList){
                long insert1 = db.insert(TableContants.DETAIL_TABLE, null, detailToValues(detail));
                Log.e("ws","插入表DETAIL_TABLE得到的返回码为: "+insert1);
            }
            Log.e("ws","插入订单号为"+orderEntity.order_num+"的orderEntity实体对象成功！");
            db.setTransactionSuccessful();
            Log.e("ws","同步执行插入数据成功！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            Log.e("ws","结束事务");
            //结束事务
        }
    }

    public ContentValues detailToValues(OrderEntity.Detail detail){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableContants.ORDER_NUM,detail.order_num);
        contentValues.put(TableContants.SAVE_CODE,detail.save_code);
        contentValues.put(TableContants.GOODS_NAME,detail.goods_name);
        contentValues.put(TableContants.FORM,detail.form);
        contentValues.put(TableContants.MODEL,detail.model);
        contentValues.put(TableContants.UNIT,detail.unit);
        contentValues.put(TableContants.COUNT,detail.count);
        contentValues.put(TableContants.PRICE,detail.price);
        contentValues.put(TableContants.EFFECT_DATE,detail.effect_date);
        contentValues.put(TableContants.SEND_ADDRESS,detail.send_address);
        contentValues.put(TableContants.ARRIVE_DATE,detail.arrive_date);
        contentValues.put(TableContants.PRODUCTOR,detail.productor);
        contentValues.put(TableContants.START_TEMPERATURE,detail.start_temperature);
        contentValues.put(TableContants.END_TEMPERATURE,detail.end_temperature);
        contentValues.put(TableContants.PRODUCT_NUM,detail.product_num);
        contentValues.put(TableContants.DETAIL_NOTE,detail.detail_note);
        return contentValues;
    }

    public ContentValues orderEntityToValues(OrderEntity orderEntity){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableContants.SENDER_NUM,orderEntity.sender_num);
        contentValues.put(TableContants.ORDER_NUM,orderEntity.order_num);
        contentValues.put(TableContants.SENDER,orderEntity.sender);
        contentValues.put(TableContants.SENDER_PHONE,orderEntity.sender_phone);
        contentValues.put(TableContants.RECIVER,orderEntity.reciver);
        contentValues.put(TableContants.RECIVER_NUM,orderEntity.reciver_num);
        contentValues.put(TableContants.SEND_DATE,orderEntity.send_date);
        contentValues.put(TableContants.ORDER_NOTE,orderEntity.order_note);
        return contentValues;
    }
}
