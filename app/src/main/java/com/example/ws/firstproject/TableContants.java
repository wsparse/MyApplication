package com.example.ws.firstproject;

/**
 * Created by WS on 2016/12/2.
 */

public class TableContants {

   //两张表的表名
    public static final String ORDRE_TABLE = "order_table"; //订单表表名
    public static final String DETAIL_TABLE = "detail_table"; //详情表表名

    //主表 ORDER_TABLE
    public static final String SENDER_NUM = "sender_num"; //发送单号
    public static final String ORDER_NUM = "order_num"; //订单号 ，两张表查询的纽带。详情表中也应该有该属性
    public static final String SENDER = "sender";//发货人
    public static final String SENDER_PHONE = "sender_phone";//发货人联系方式
    public static final String RECIVER = "reciver";  //收货人
    public static final String RECIVER_NUM = "reciver_num"; //发货人联系方式
    public static final String SEND_DATE = "send_date";//发货日期
    public static final String ORDER_NOTE = "order_note"; //订单表备注



    //详情表
    public static final String _ID = "_id"; //序号  设置为自增无需添加到实体类的属性当中
    /*public static final String ORDER_NUM = "order_num"; //订单号 ，两张表查询的纽带。详情表中也应该有该属性*/
    public static final String SAVE_CODE = "save_code"; //存货编码
    public static final String GOODS_NAME = "goods_name"; //商品名称
    public static final String FORM = "form"; //规格
    public static final String MODEL = "model"; // 型号
    public static final String UNIT = "unit" ; // 单位
    public static final String COUNT = "count"; //数量
    public static final String PRICE = "price"; //单价
    public static final String EFFECT_DATE = "effect_date"; //有效期
    public static final String SEND_ADDRESS = "send_address"; //送达地址
    public static final String ARRIVE_DATE = "arrive_date"; //送达日期
    public static final String PRODUCTOR = "productor"; //生产厂家
    public static final String START_TEMPERATURE = "start_temperature"; //启运温度
    public static final String END_TEMPERATURE = "end_temperature"; //送达温度
    public static final String PRODUCT_NUM = "product_num"; //生产批次号
    public static final String DETAIL_NOTE = "detail_note";//详情表备注

}
