package com.example.ws.firstproject;

import java.util.List;

/**
 * Created by WS on 2016/12/2.
 */

public class OrderEntity {

    public String sender_num;//发送单号
    public String order_num;//订单号 ，两张表查询的纽带。详情表中也应该有该属性
    public String sender;//发货人
    public String sender_phone;//发货人联系方式
    public String reciver;//收货人
    public String reciver_num; //发货人联系方式
    public String send_date;//发货日期
    public String order_note; //订单表备注;
    public List<Detail> detailList; // 详情列表
    public OrderEntity(String sender_num , String order_num, String sender, String sender_phone, String reciver, String reciver_num, String send_date, String order_note, List<Detail> detailList){

        this.sender_num = sender_num;
        this.order_num = order_num;
        this.sender = sender;
        this.sender_phone = sender_phone;
        this.reciver = reciver;
        this.reciver_num = reciver_num;
        this.send_date = send_date;
        this.order_note = order_note;
        this.detailList = detailList;
    }

    public static class Detail{
        public String order_num;//订单号 ，两张表查询的纽带。详情表中也应该有该属性
        public String save_code; //存货编码
        public String goods_name;//商品名称
        public String form; //规格
        public String model; // 型号
        public String unit ; // 单位
        public String count; //数量
        public String price; //单价
        public String effect_date; //有效期
        public String send_address; //送达地址
        public String arrive_date; //送达日期
        public String productor; //生产厂家
        public String start_temperature; //启运温度
        public String end_temperature; //送达温度
        public String product_num; //生产批次号
        public String detail_note;//详情表备注
        public Detail(String order_num,String save_code,String goods_name,String form, String model,String unit,String count,String price,
                      String effect_date,String send_address,String arrive_date,String productor,String start_temperature,String end_temperature,String product_num,String detail_note){
            this.order_num = order_num;
            this.save_code = save_code;
            this.goods_name = goods_name;
            this.form = form;
            this.model = model;
            this.unit = unit;
            this.count = count;
            this.price = price;
            this.effect_date = effect_date;
            this.send_address = send_address;
            this.arrive_date = arrive_date;
            this.productor = productor;
            this.start_temperature = start_temperature;
            this.end_temperature = end_temperature;
            this.product_num = product_num;
            this.detail_note = detail_note;

        }

        @Override
        public String toString() {
            return "Detail{" +
                    "order_num='" + order_num + '\'' +
                    ", save_code='" + save_code + '\'' +
                    ", goods_name='" + goods_name + '\'' +
                    ", form='" + form + '\'' +
                    ", model='" + model + '\'' +
                    ", unit='" + unit + '\'' +
                    ", count='" + count + '\'' +
                    ", price='" + price + '\'' +
                    ", effect_date='" + effect_date + '\'' +
                    ", send_address='" + send_address + '\'' +
                    ", arrive_date='" + arrive_date + '\'' +
                    ", productor='" + productor + '\'' +
                    ", start_temperature='" + start_temperature + '\'' +
                    ", end_temperature='" + end_temperature + '\'' +
                    ", product_num='" + product_num + '\'' +
                    ", detail_note='" + detail_note + '\'' +
                    '}';
        }
    }


}
